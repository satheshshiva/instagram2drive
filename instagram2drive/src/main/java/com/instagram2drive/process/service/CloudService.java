package com.instagram2drive.process.service;

import java.io.IOException;
import java.util.List;

import com.google.api.services.drive.model.File;


public interface CloudService {
	
	void upload(String title, String parentFolderId, String localPath,boolean deleteLocalFile) throws Exception;

	File createFolder(String folderName) throws IOException;

	void deleteFolder(
			String googleInstagramFoldername) throws IOException;

	List<File> searchForFolders(String folderName) throws IOException;
}
