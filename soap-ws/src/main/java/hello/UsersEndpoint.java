package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import io.spring.guides.gs_producing_web_service.LoginUserRequest;
import io.spring.guides.gs_producing_web_service.LoginUserResponse;

@Endpoint
public class UsersEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

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

}
