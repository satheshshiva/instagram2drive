package com.instagram2drive.network;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.instagram2drive.process.MainProcess;

/** Network call using the included commons.apache.HttpClient library and Java NIO
 * 
 * @author Sathesh
 *
 */
public class HttpClientService implements NetworkCall{
	
	private static final Logger log = Logger.getLogger(MainProcess.class);
	
	@Override
	public String httpGet(String url) throws HttpException, IOException {
		
		// Create an instance of HttpClient.
	    HttpClient client = new HttpClient();
	    String responseBody="";

	    // Create a method instance.
	    GetMethod getMethod = new GetMethod(url);
	    
	    // Provide custom retry handler
	    getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	    		new DefaultHttpMethodRetryHandler(3, false));

	    try {
	      // Execute the method.
	      int statusCode = client.executeMethod(getMethod);

	      if (statusCode != HttpStatus.SC_OK) {
	    	  log.error("Get Method failed: " + getMethod.getStatusLine());
	      }

	      // Read the response body.
	      byte[] _responseBody = getMethod.getResponseBody();

	      responseBody = new String(_responseBody);

	    }
	    finally {
	      // Release the connection.
	    	getMethod.releaseConnection();
	    }
		return responseBody;
	}

	/** Download a file using Java NIO
	 * @throws IOException 
	 * 
	 */
	@Override
	public boolean downloadFile(String url, File file) throws IOException {
		FileUtils.copyURLToFile(new URL(url), file);
		return true;
	}  
}
