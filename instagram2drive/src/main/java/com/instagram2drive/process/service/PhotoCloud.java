package com.instagram2drive.process.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

public interface PhotoCloud {

	List<String> getPopularPhotoUrls() throws HttpException, IOException;
	List<String> getTagSearch(String tag) throws HttpException, IOException;
}
