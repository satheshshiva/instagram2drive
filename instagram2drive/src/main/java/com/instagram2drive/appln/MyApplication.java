package com.instagram2drive.appln;

import java.util.HashMap;

import com.instgram2drive.user.User;

/** This is a singleton  class used for persisting objects in the application across session even after page redirections
 * 
 * @author Sathesh
 *
 */
public class MyApplication {

	private static MyApplication myApplication = null;
	
	
	//singleton instance
	public static MyApplication getInstance(){
		if(myApplication == null){
			myApplication = new MyApplication();
		}

		return myApplication;
	}
	
	//stores a map of signed in users
	private HashMap<String,User> userMap;	//<username, User>

	public HashMap<String,User> getUserMap() {
		return userMap;
	}

	public void setUserMap(HashMap<String,User> userMap) {
		this.userMap = userMap;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserMap [userMap=").append(userMap).append("]");
		return builder.toString();
	}
	
	
}
