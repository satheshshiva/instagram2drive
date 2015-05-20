package com.instgram2drive.user;

import java.util.HashMap;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.instagram2drive.appln.MyApplication;
import com.instagram2drive.json.responses.GoogleAuthResponse;
import com.instagram2drive.json.responses.InstagramAuthResponse;

/** This class writes the user id of InstagramAuthResponse to the singleton MyApplication object for persistence
 * 
 * @author Sathesh
 *
 */
public class StoreGoogleUser implements Callable{

	private MyApplication myAppln;

	/*@Autowired
	public void setUsersList(MyApplication usersList){
		this.usersList = usersList;

	}*/

	/** Update the instagram user details in singleton object and return the payload
	 * 
	 */
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub

		HashMap<String, User> hashMapUsers =null;
		User user;
		GoogleAuthResponse googleAuthResponse;
		
		try {
		//get the singleton object
		myAppln = MyApplication.getInstance();
			if(myAppln != null ){
				
				//gets the userMap hashmap or creates a new one
				if(myAppln.getUserMap() != null){
					hashMapUsers = myAppln.getUserMap();
				}
				
				// get the google response json pojo object from the payload
				googleAuthResponse = (GoogleAuthResponse)eventContext.getMessage().getInvocationProperty("googleAuthResponse");
				
				//if the user name not null then persist the token
				if(googleAuthResponse!=null && hashMapUsers!=null){
					user=hashMapUsers.get("defaultUser");
					//access token
					user.setGoogleAccessToken(googleAuthResponse.getAccess_token());
					//refresh token
					user.setGoogleRefreshToken(googleAuthResponse.getRefresh_token());

				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return payload
		return eventContext.getMessage().getPayload();
	}
}
