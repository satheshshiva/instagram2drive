package com.instagram2drive.json.responses;

import oAuth.oAuth2Response;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/** Authentication response from Instagram
 * 
 * @author Sathesh
 *
 */
@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstagramAuthResponse extends oAuth2Response{

	private InstagramUser user;
	
	public InstagramUser getUser() {
		return user;
	}

	public void setUser(InstagramUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstagramAuthResponse [user=").append(user)
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
