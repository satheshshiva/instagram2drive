package com.instagram2drive.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram2drive.appln.Constants;
import com.instagram2drive.json.responses.InstagramSearchPhotoResponse;
import com.instagram2drive.network.HttpClientService;
import com.instagram2drive.network.NetworkCall;
import com.instagram2drive.process.service.PhotoCloud;
import com.instgram2drive.user.User;

/** Calls the Instagram service 
 * 
 * @author Sathesh
 *
 */
public class InstagramService implements PhotoCloud, Constants {
	
	private String access_token;
	
	public InstagramService(User user){
		this.access_token = user.getInstagramAccessToken();
	}
	
	/** Returns a List of string of urls (size:10) containing the 10  popular photos
	 * 
	 */
	@Override
	public List<String> getPopularPhotoUrls() throws HttpException, IOException {
		
		String reponseBody="", url="";
		List<String> returnUrls = new ArrayList<String>();
		
		NetworkCall instagramCall = new HttpClientService();
		
		//network call
		reponseBody = instagramCall.httpGet(INSTAGRAM_POPULAR_PHOTOS_URL + access_token);

		//mapping json to pojo
		ObjectMapper mapper = new ObjectMapper(); 
		// to prevent exception when encountering unknown property:
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//auto map json response and pojo object
		InstagramSearchPhotoResponse response = mapper.readValue(reponseBody, InstagramSearchPhotoResponse.class);
		
		//retrieve the photos from pojo
		for(int i=0; i < INSTAGRAM_MAX_PHOTOS; i++){
			if(response!=null && response.getData()!=null 
					&& response.getData().get(i)!=null 
					&& response.getData().get(i).getImages()!=null 
					&& response.getData().get(i).getImages().getStandard_resolution()!=null 
					&& response.getData().get(i).getImages().getStandard_resolution().getUrl()!=null){
				url= response.getData().get(i).getImages().getStandard_resolution().getUrl();
			}
			returnUrls.add(url);
		}
		return returnUrls;
	}

	@Override
	public List<String> getTagSearch(String tag) {
		// TODO Implement this
		
		//To be implemented
		
		return null;
	}

}
