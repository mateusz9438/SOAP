package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import countries.core.common.LoginUserRequest;
import countries.core.common.LoginUserResponse;
import countries.core.common.LogoutUserRequest;
import countries.core.common.LogoutUserResponse;

@Endpoint
public class UsersEndpoint {
    private static final String NAMESPACE_URI = "core.countries";

    private ActiveUsersRepository activeUsersRepository;

    @Autowired
    public UsersEndpoint(ActiveUsersRepository activeUsersRepository) {
        this.activeUsersRepository = activeUsersRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginUserRequest")
    @ResponsePayload
    public LoginUserResponse loginUser(@RequestPayload LoginUserRequest request) {
        LoginUserResponse response = new LoginUserResponse();
        String uuid = activeUsersRepository.loginUser(request.getLogin(), request.getPassword());
        if (uuid != null) {
            response.setUuid(uuid);
            response.setMessage("Login successful");
            return response;
        }
        response.setMessage("Login failed");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "logoutUserRequest")
    @ResponsePayload
    public LogoutUserResponse logoutUser(@RequestPayload LogoutUserRequest request) {
        LogoutUserResponse response = new LogoutUserResponse();
        String login = activeUsersRepository.logoutUser(request.getUuid(),request.getLogin());
	    if(login!=null){
	        response.setLogin(login);
	        response.setMessage(login + "logout successful");
	        return response;
        }
        response.setMessage("you are not logged");
        return response;
    }


}
