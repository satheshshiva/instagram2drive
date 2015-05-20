package com.instagram2drive.components;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class GoogleGetToken implements Callable{

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {

		PostMethod post = new PostMethod("https://accounts.google.com/o/oauth2/token");

        NameValuePair[] data = {
          new NameValuePair("code", "joe"),
          new NameValuePair("client_id", "805405795878-h5o08fb2264gmc0fsnpsedhih4f8hcfg.apps.googleusercontent.com"),
          new NameValuePair("client_secret", "exMwUlUFY7smAa1HKz7RRyfg"),
          new NameValuePair("redirect_uri", "http://localhost:8081/googleAuthRedirectUrl"),
          new NameValuePair("grant_type", "authorization_code")
        };

        post.setRequestBody(data);
        
		// execute method and handle any error responses.
		InputStream in = post.getResponseBodyAsStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(in, writer);
		writer.toString();

		return writer.toString();
	}

}
