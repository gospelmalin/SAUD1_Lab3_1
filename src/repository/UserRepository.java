package repository;

import model.User;

public class UserRepository {

	public String add(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.addUser(u1);
		return message;	
	}
	
	public String update(User u1) {
		RESTClient rc = new RESTClient();
		String message = rc.updateUser(u1);
		return message;	
	}

}
