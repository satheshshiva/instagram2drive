package com.instagram2drive.json.responses;

import oAuth.oAuth2Response;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


/** Authentication response from Google
 * 
 * @author Sathesh
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleAuthResponse extends oAuth2Response{

	private String error;
	private String error_description;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GoogleAuthResponse [error=").append(error)
				.append(", error_description=").append(error_description)
				.append(", getAccess_token()=").append(getAccess_token())
				.append(", getRefresh_token()=").append(getRefresh_token())
				.append(", getToken_type()=").append(getToken_type())
				.append(", getExpires_in()=").append(getExpires_in())
				.append(", getCode()=").append(getCode())
				.append(", getError_type()=").append(getError_type())
				.append(", getError_message()=").append(getError_message())
				.append(", getClass()=").append(getClass())
				.append(", hashCode()=").append(hashCode())
				.append(", toString()=").append(super.toString()).append("]");
		return builder.toString();
	}


	
}
