package hello;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.User;

@Component
public class RegisterUsersRepository {

	private static Map<String, User> registerUsers = new HashMap<>();
	
	@PostConstruct
	public void initData() {
		User user1 = new User();
		user1.setLogin("user1");
		user1.setPassword("user1");
		registerUsers.put(user1.getLogin(), user1);
		
		User user2 = new User();
		user2.setLogin("user2");
		user2.setPassword("user2");
		registerUsers.put(user2.getLogin(), user2);
		
		User user3 = new User();
		user3.setLogin("user3");
		user3.setPassword("user3");
		registerUsers.put(user3.getLogin(), user3);
				
	}
	private static User findRegisterUser(String login) {
		Assert.notNull(login, "The login must not be null");
		return registerUsers.get(login);
	}
	public static User getUser(String login,String password){
		User user = findRegisterUser(login);
		if(user!=null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}
	
}
