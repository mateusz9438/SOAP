package hello;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import countries.core.common.User;

@Component
public class ActiveUsersRepository {
	private static Map<String, User> activeUsers = new HashMap<>();
	private static Map<String,String> loginUuid = new HashMap<>();
	
	public String loginUser(String login, String password){	
		User newUser = RegisterUsersRepository.getUser(login,password);
		if(newUser!=null)
		{
			if(activeUsers.get(login)==null){
			String uuid = generateUuid();
			activeUsers.put(login, newUser);
			loginUuid.put(login,uuid);
			return uuid;
			}
			return null;
		}
		return null;
	}
	public String logoutUser(String uuid ,String login){
		if(!verifyUuid(uuid)){
			return null;
		}
		if(!verifyLogin(login)){
			return null;
		}
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


	private boolean verifyUuid(String uuid) {
		Collection<String> uuids = getLoginUuid().values();
		if (uuids.contains(uuid)) {
			return true;
		}
		return false;
	}
	private boolean verifyLogin(String login) {
		if (getLoginUuid().containsKey(login)) {
			return true;
		}
		return false;
	}
}

