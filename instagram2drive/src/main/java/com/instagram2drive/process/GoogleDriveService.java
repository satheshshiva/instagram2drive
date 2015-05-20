package com.instagram2drive.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import com.instagram2drive.appln.Constants;
import com.instagram2drive.process.service.CloudService;
import com.instgram2drive.user.User;

/**  GoogleDrive Connection
 * 
 * @author Sathesh
 *
 */
public class GoogleDriveService implements CloudService, Constants{

	private HttpRequestInitializer credential;
	private HttpTransport httpTransport;
	private JsonFactory jsonFactory;
	private Drive driveService;

	private static final Logger log = Logger.getLogger(MainProcess.class);

	public GoogleDriveService(User user){
		String googleAccessToken = user.getGoogleAccessToken();

		// initializing google api objects
		this.httpTransport = new NetHttpTransport();
		this.jsonFactory = new JacksonFactory();
		this.credential=new GoogleCredential().setAccessToken(googleAccessToken);
		this.driveService = new Drive.Builder(httpTransport, jsonFactory, credential).build();

	}

	/** Uploads given file to Google Drive
	 * 
	 * @param driveService
	 * @param title - title for filename to be saved in Google Drive
	 * @param localPath - local file path of the file to upload
	 * @param deleteLocalFile - deletes the local file after execution
	 * @throws Exception
	 */

	@Override
	public void upload(String title, String parentFolderId, String localPath, boolean deleteLocalFile) throws Exception{

		//create a google file object  
		File body = new File();
		body.setTitle(title);
		body.setMimeType(MIME_TYPE_IMAGE);

		// Set the parent folder.
		if (parentFolderId != null && parentFolderId.length() > 0) {
			body.setParents(
					Arrays.asList(new ParentReference().setId(parentFolderId)));
		}


		//create a javafile object
		java.io.File fileContent = new java.io.File(localPath);
		if(fileContent.exists()){
			FileContent mediaContent = new FileContent(MIME_TYPE_IMAGE, fileContent);

			//network call 
			File file = driveService.files().insert(body, mediaContent).execute();

			//local file deletion
			if(deleteLocalFile){
				fileContent.delete();
			}

			if(file != null && file.getId()!= null && !file.getId().equals("")){
				log.info("upload() -Uploaded " +localPath + " to Google drive");
			}
			else{
				log.error("upload() -Error uploading file " +localPath + " to Google drive " );
			}
		}
		else{
			log.error("upload() - File not found " +localPath );
		}

	}

	@Override
	public File createFolder(String folderName) throws IOException{
		File body = new File();
		body.setTitle(folderName);
		body.setMimeType(GOOGLE_MIME_CREATE_FOLDER);
		return driveService.files().insert(body).execute();
	}

	@Override
	public void deleteFolder(String folderName) throws IOException {
		//first search for folder and get the folder ids with the given name
		List<File> files = searchForFolders(folderName);
		for(File file : files){
			//delete folders
			driveService.files().delete(file.getId()).execute();
		}
	}

	// ...

	/**
	 * Retrieve a list of File resources.
	 *
	 * @param service Drive API service instance.
	 * @return List of File resources.
	 */
	@Override
	public List<File> searchForFolders(String folderName) throws IOException {
		List<File> result = new ArrayList<File>();
		Files.List request = driveService.files().list().setQ("mimeType='application/vnd.google-apps.folder' and title='" +folderName +"'");

		do {
			FileList files = request.execute();

			result.addAll(files.getItems());
			request.setPageToken(files.getNextPageToken());
		} while (request.getPageToken() != null &&
				request.getPageToken().length() > 0);

		return result;
	}

}
