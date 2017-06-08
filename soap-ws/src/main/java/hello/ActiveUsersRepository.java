package hello;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;
import io.spring.guides.gs_producing_web_service.User;

@Component
public class ActiveUsersRepository {
	private static Map<String, User> activeUsers = new HashMap<>();
	private static Map<String,String> loginUuid = new HashMap<>();
	
	public String loginUser(String login, String password){	
		User newUser = RegisterUsersRepository.getUser(login,password);
		if(newUser!=null)
		{
			String uuid = generateUuid();
			activeUsers.put(login, newUser);
			loginUuid.put(login,uuid);
			return uuid;
		}
		return null;
	}
	public String logoutUser(String login){
		activeUsers.remove(login);
		loginUuid.remove(login);
		return login;
	}
	
	private String generateUuid() {
		StringBuffer uuid = new StringBuffer();
		Random generator = new Random();
		for (int i = 0; i < 10; i++) {
			int number = generator.nextInt(10);
			uuid.append(number);
		}
		return uuid.toString();
	}
	public static Map<String, String> getLoginUuid() {
		return loginUuid;
	}
	
}
