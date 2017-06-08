package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import countries.core.common.AddCountryRequest;
import countries.core.common.AddCountryResponse;
import countries.core.common.Country;
import countries.core.common.DeleteCountryRequest;
import countries.core.common.DeleteCountryResponse;
import countries.core.common.GetCountryRequest;
import countries.core.common.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "core.countries";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addCountryRequest")
	@ResponsePayload
	public AddCountryResponse addCountry(@RequestPayload AddCountryRequest request) {
		AddCountryResponse response = new AddCountryResponse();
		Country country = countryRepository.addCountry(request.getUuid(),request.getName(),request.getCapital(),request.getCurrency(),request.getPopulation());
		if(country != null){
			response.setCountry(country);
			response.setMessage("Country added");
			return response;
		}
		response.setMessage("Access denied");
		return response;
		
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteCountryRequest")
	@ResponsePayload
	public DeleteCountryResponse deleteCountry(@RequestPayload DeleteCountryRequest request) {
		DeleteCountryResponse response = new DeleteCountryResponse();
		String countryName = countryRepository.deleteCountry(request.getUuid(),request.getName());
		if(countryName!=null){
			response.setMessage("Country removed");
			response.setName(countryName);
			return response;
		}
		response.setMessage("Access denied");
		return response;

	}
}
