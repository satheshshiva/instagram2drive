package com.instgram2drive.user;

import java.util.HashMap;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.instagram2drive.appln.MyApplication;
import com.instagram2drive.json.responses.InstagramAuthResponse;

/** This class updates the google user token to the singleton object for persistence
 * 
 * @author Sathesh
 *
 */
public class StoreInstagramUser implements Callable{

	private MyApplication myAppln;

	/*@Autowired
	public void setUsersList(MyApplication usersList){
		this.usersList = usersList;

	}*/

	/** Update the google user details in singleton object and return the payload
	 * 
	 */
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub

		HashMap<String, User> hashMapUsers =null;
		User user;
		InstagramAuthResponse instagramAuthResponse;

		try {
			//get the singleton object
			myAppln = MyApplication.getInstance();
			if(myAppln != null ){

				//gets the userMap hashmap or creates a new one
				if(myAppln.getUserMap() != null){
					hashMapUsers = myAppln.getUserMap();
				}
				else{
					hashMapUsers = new HashMap<String, User>();
					myAppln.setUserMap(hashMapUsers);
				}

				// get the instagram response json pojo object from the payload
				instagramAuthResponse = (InstagramAuthResponse)eventContext.getMessage().getInvocationProperty("instagramAuthResponse");

				//if the user name not null then persist the token
				if(instagramAuthResponse !=null && instagramAuthResponse.getUser() !=null && instagramAuthResponse.getUser().getUsername() != null){
					user= new User();
					//access token
					user.setInstagramAccessToken(instagramAuthResponse.getAccess_token());
					//refresh token
					user.setInstagramRefreshToken(instagramAuthResponse.getRefresh_token());
					//username
					user.setInstagramUserId(instagramAuthResponse.getUser().getUsername());
					//store the User object in map. <username, User>
					//hashMapUsers.put(instagramAuthResponse.getUser().getUsername(),  user);
					hashMapUsers.put("defaultUser",  user);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return payload
		return eventContext.getMessage().getPayload();
	}
}
