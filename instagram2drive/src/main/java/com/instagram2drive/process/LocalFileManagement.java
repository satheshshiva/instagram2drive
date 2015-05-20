package com.instagram2drive.process;

import java.io.File;
import java.util.Random;

import com.instagram2drive.appln.Constants;
import com.instagram2drive.process.service.FileManagement;

/** Class for local file management operations
 * 
 * @author Sathesh
 *
 */
public class LocalFileManagement implements FileManagement, Constants{

	/** generates a random work folder name
	 * 
	 * @return
	 */
	@Override
	public String getRandomWorkFolderName(){
		Random rand = new Random(); 
		int randVal = rand.nextInt(1000); 
		return WORK_FOLDERNAME_PREFIX + randVal;
	}

	@Override
	public boolean deleteFileOrFolder(String path) {
		File file = new File(path);
		return file.delete();
	}

}
