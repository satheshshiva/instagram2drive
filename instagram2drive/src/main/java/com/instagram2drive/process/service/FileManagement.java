package com.instagram2drive.process.service;

import java.io.File;

public interface FileManagement {
	
	String getRandomWorkFolderName();
	boolean deleteFileOrFolder(String path);

}
