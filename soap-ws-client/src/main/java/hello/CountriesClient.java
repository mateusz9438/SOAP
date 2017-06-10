package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import countries.core.GetCountryRequest;
import countries.core.GetCountryResponse;

public class CountriesClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(CountriesClient.class);

	public GetCountryResponse getCountry(String name) {
		GetCountryRequest request = new GetCountryRequest();
		request.setName(name);
		log.info("Requesting for " + name);
		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://localhost:8080/ws", request,
				new SoapActionCallback("http://www.webserviceX.NET/GetCountryRequest"));
		return response;

	}

}
