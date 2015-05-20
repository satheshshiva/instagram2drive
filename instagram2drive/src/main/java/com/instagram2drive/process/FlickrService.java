package com.instagram2drive.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram2drive.appln.Constants;
import com.instagram2drive.json.responses.FlickrSearchResponse;
import com.instagram2drive.json.responses.InstagramSearchPhotoResponse;
import com.instagram2drive.json.responses.flickr.Photo;
import com.instagram2drive.network.HttpClientService;
import com.instagram2drive.network.NetworkCall;
import com.instagram2drive.process.service.PhotoCloud;

/** Calls the Instagram service 
 * 
 * @author Sathesh
 *
 */
public class FlickrService implements PhotoCloud, Constants {
	
	public FlickrService(){
	}
	
	/** Returns a List of string of urls (size:10) containing the 10  popular photos
	 * 
	 */
	@Override
	public List<String> getPopularPhotoUrls() throws HttpException, IOException {
		
		String reponseBody="", url="";
		List<String> returnUrls = new ArrayList<String>();
		
		NetworkCall FlickrCall = new HttpClientService();
		
		//network call
		reponseBody = FlickrCall.httpGet(FLICKR_POPULAR_PHOTOS_URL);
		
		reponseBody = trimFlickrResponse(reponseBody);
		
		//mapping json to pojo
		ObjectMapper mapper = new ObjectMapper(); 
		// to prevent exception when encountering unknown property:
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//auto map json response and pojo object
		FlickrSearchResponse response = mapper.readValue(reponseBody, FlickrSearchResponse.class);
		
		//retrieve the photos from pojo and add it to the list of url's
		for(int i=0; i < FLICKR_MAX_PHOTOS; i++){
			if(response!=null
					&& response.getPhotos()!=null 
					&& response.getPhotos().getPhoto()!=null ){
				url = getFlickrUrl(response.getPhotos().getPhoto().get(i));
			}
			returnUrls.add(url);
		}
		return returnUrls;
	}

	/** trim the unncessary from json string
	 * 
	 * @param response
	 * @return
	 */
	private String trimFlickrResponse(String response) {
		// TODO Auto-generated method stub
		response = response.replaceAll("jsonFlickrApi", "");
		return response.substring(1, response.length()-1);
		
	}

	/** See https://www.flickr.com/services/api/misc.urls.html
	 * 
	 * @param photo
	 * @return
	 */
	private String getFlickrUrl(Photo photo) {
		// TODO Auto-generated method stub
		return "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
	}

	@Override
	public List<String> getTagSearch(String tag) {
		// TODO Implement this
		
		//To be implemented
		
		return null;
	}

}
