package com.instagram2drive.network;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.httpclient.HttpException;

public interface NetworkCall {

	String httpGet(String url) throws HttpException, IOException;
	boolean downloadFile(String url, File file) throws IOException;
}
