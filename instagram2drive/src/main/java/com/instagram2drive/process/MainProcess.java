package com.instagram2drive.process;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

import com.instagram2drive.appln.Constants;
import com.instagram2drive.appln.MyApplication;
import com.instagram2drive.network.HttpClientService;
import com.instagram2drive.network.NetworkCall;
import com.instagram2drive.process.service.FileManagement;
import com.instagram2drive.process.service.PhotoCloud;
import com.instagram2drive.process.service.CloudService;
import com.instgram2drive.user.User;

/** This class performs the main processing the downloading the instagram pictures and uploading it to the drive
 * 
 * @author Sathesh
 *
 */
public class MainProcess implements Callable, Constants{

	private static final Logger log = Logger.getLogger(MainProcess.class);

	private MyApplication appln ;
	private HashMap<String,User> userMap;

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		// TODO Auto-generated method stub

		User user;

		try {
			appln = MyApplication.getInstance();

			//if user detail exists in the singleton object then do processing
			if(appln!=null){
				userMap = appln.getUserMap() ;

				if(userMap !=null ){
					user=userMap.get("defaultUser");
					if(user!=null){
						doProcess(user);
					}
					else{
						log.error("Cannot get defaultUser");
						return "Error Occured \n\nErrorMessage: No user logged in" ;
					}
				}
				else{
					log.error("userMap is null");
					return "Error Occured \n\nErrorMessage: No user logged in" ;
				}
			}
			else{
				log.error("Cannot get the application instance");
				return "Error Occured \n\nErrorMessage: Cannot get the application instance" ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error Occured \n\nErrorMessage " + e.getMessage();
		}
		return "Successful!\n\nPlease check your Google Drive";
	}

	/** Private method for the main processing
	 * @param user 
	 */
	private void doProcess(User user) throws Exception{

		String diretoryName;

		//initialize
		NetworkCall httpCall = new HttpClientService();
		FileManagement localFileManagement = new LocalFileManagement();
		CloudService googleCloud = new GoogleDriveService(user);
		PhotoCloud instagramService = new InstagramService(user);
		PhotoCloud flickrService = new FlickrService();

		//form the temporary work folder name
		diretoryName= LOCAL_DOWNLOAD_LOCATION + localFileManagement.getRandomWorkFolderName() + "/";

		//INSTAGRAM
		processPhotoService(httpCall, instagramService, googleCloud, diretoryName, INSTAGRAM_FILE_NAME_PREFIX, GOOGLE_INSTAGRAM_FOLDERNAME);

		//FLICKR
		processPhotoService(httpCall, flickrService, googleCloud, diretoryName, FLICKR_FILE_NAME_PREFIX, GOOGLE_FLICKR_FOLDERNAME);

		//delete the temporary work directory
		localFileManagement.deleteFileOrFolder(diretoryName);
	}

	/** download from photo services and upload to Google drive
	 * 
	 * @param httpCall
	 * @param photoService
	 * @param googleCloud
	 * @param diretoryName
	 * @param localFilenamePrefix
	 * @param googleFolderName
	 * @throws Exception
	 */
	private void processPhotoService(NetworkCall httpCall, PhotoCloud photoService, CloudService googleCloud, String diretoryName, String localFilenamePrefix,String googleFolderName) throws Exception {
		String url, title, path, googleFolderId;
		List<String> urls;
		//network call - get the lift of popular photo urls
		urls = photoService.getPopularPhotoUrls();

		//network call - delete the existing folder with old files
		googleCloud.deleteFolder(googleFolderName);

		//network call - google drive - create a new folder
		googleFolderId=googleCloud.createFolder(googleFolderName).getId();
		//download each URL to the work folder and upload to the cloud
		for(int i=0; i< urls.size(); i++){
			url = urls.get(i);

			// form the local folder and file name
			title=localFilenamePrefix+(i+1);
			path=diretoryName+title+FILE_NAME_EXTENSION;

			// download the file to the work directory
			httpCall.downloadFile(url, new File(path));

			// upload the file to the cloud
			googleCloud.upload(title, googleFolderId, path, true);
		}
	}
}
