package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import countries.core.common.AddCountryRequest;
import countries.core.common.AddCountryResponse;
import countries.core.common.Currency;
import countries.core.common.DeleteCountryRequest;
import countries.core.common.DeleteCountryResponse;
import countries.core.common.GetCountryRequest;
import countries.core.common.GetCountryResponse;
import countries.core.common.LoginUserRequest;
import countries.core.common.LoginUserResponse;
import countries.core.common.LogoutUserRequest;
import countries.core.common.LogoutUserResponse;


public class CountriesClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountriesClient.class);
    private static final String HOST = "http://localhost:8080/ws";
    private WebServiceTemplate wstemplate;

    public CountriesClient (Jaxb2Marshaller marshaller){
        wstemplate = new WebServiceTemplate(marshaller);
    }

    public GetCountryResponse getCountry(String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        log.info("Requesting for " + name);
        GetCountryResponse response = (GetCountryResponse) wstemplate.marshalSendAndReceive(HOST,request,
                new SoapActionCallback("http://www.webserviceX.NET/GetCountryRequest"));
        return response;
    }

    public LoginUserResponse loginUser(String login, String password) {
        LoginUserRequest request = new LoginUserRequest();
        request.setLogin(login);
        request.setPassword(password);
        log.info("Logging as " + login);
        LoginUserResponse response = (LoginUserResponse) wstemplate.marshalSendAndReceive(HOST, request,
                new SoapActionCallback("http://www.webserviceX.NET/LoginUserRequest"));
        return response;
    }
    public LogoutUserResponse logoutUser(String uuid,String login){
        LogoutUserRequest request = new LogoutUserRequest();
        request.setUuid(uuid);
        request.setLogin(login);
        log.info("Logout " + login);
        LogoutUserResponse response = (LogoutUserResponse) wstemplate.marshalSendAndReceive(HOST,request,
                new SoapActionCallback("http://www.webserviceX.NET/LoginUserRequest"));
        return response;
    }

    public AddCountryResponse addCountry(String uuid, String name, String capital, Currency currency, int population) {
        AddCountryRequest request = new AddCountryRequest();
        request.setUuid(uuid);
        request.setName(name);
        request.setCapital(capital);
        request.setCurrency(currency);
        request.setPopulation(population);
        log.info("Adding country " + name);
        AddCountryResponse response = (AddCountryResponse) wstemplate.marshalSendAndReceive(HOST, request,
                new SoapActionCallback("http://www.webserviceX.NET/AddCountryRequest"));
        return response;
    }

    public DeleteCountryResponse deleteCountry(String uuid, String name) {
        DeleteCountryRequest request = new DeleteCountryRequest();
        request.setUuid(uuid);
        request.setName(name);
        log.info("Deleting country " + name);
        DeleteCountryResponse response = (DeleteCountryResponse) wstemplate.marshalSendAndReceive(HOST, request,
                new SoapActionCallback("http://www.webserviceX.NET/DeleteCountryResponse"));
        return response;
    }

}
