package com.instagram2drive.appln;

public interface Constants {

	/* *********								*********							
	 * *********    LOCAL PROPERTIES			*********
	 * *********								*********
	 */
	public final String LOCAL_DOWNLOAD_LOCATION		= "src/main/resources/";
	public final String FILE_NAME_EXTENSION 		= ".jpg";
	public final String WORK_FOLDERNAME_PREFIX		= "work";
	public final String MIME_TYPE_IMAGE				= "image/jpeg";
	
	
	/* *********								*********
	 * *********    INSTAGRAM PROPERTIES		*********
	 * *********								*********
	 */
	public final String INSTAGRAM_TAG_SEARCH_URL	= "https://api.instagram.com/v1/tags/{searchTerm}/media/recent";
	public final String INSTAGRAM_POPULAR_PHOTOS_URL= "https://api.instagram.com/v1/media/popular?access_token=";
	public final int INSTAGRAM_MAX_PHOTOS			= 10;
	public final String INSTAGRAM_FILE_NAME_PREFIX	= "Instagram ";
	
	/* *********								*********
	 * *********    FLICKR PROPERTIES			*********
	 * *********								*********
	 */
	public final String FLICKR_POPULAR_PHOTOS_URL	= "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=5b240344006ffc6a6c9659e085003a8e&per_page=10&page=1&format=json";
	public final int FLICKR_MAX_PHOTOS				= 10;
	public final String FLICKR_FILE_NAME_PREFIX		= "Flickr ";
	
	/* *********								*********
	 * *********    GOOGLE PROPERTIES			*********
	 * *********								*********
	 */
	public final String GOOGLE_MIME_CREATE_FOLDER	= "application/vnd.google-apps.folder";
	public final String GOOGLE_INSTAGRAM_FOLDERNAME	= "Instagram2Drive";
	public final String GOOGLE_FLICKR_FOLDERNAME	= "Flickr2Drive";
}
