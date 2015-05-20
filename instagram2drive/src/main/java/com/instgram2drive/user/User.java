package com.instgram2drive.user;

public class User {
	private String instagramUserId;
	private String instagramAccessToken;
	private String instagramRefreshToken;
	private String googleAccessToken;
	private String googleRefreshToken;
	public String getInstagramUserId() {
		return instagramUserId;
	}
	public void setInstagramUserId(String instagramUserId) {
		this.instagramUserId = instagramUserId;
	}
	public String getInstagramAccessToken() {
		return instagramAccessToken;
	}
	public void setInstagramAccessToken(String instagramAccessToken) {
		this.instagramAccessToken = instagramAccessToken;
	}
	public String getInstagramRefreshToken() {
		return instagramRefreshToken;
	}
	public void setInstagramRefreshToken(String instagramRefreshToken) {
		this.instagramRefreshToken = instagramRefreshToken;
	}
	public String getGoogleAccessToken() {
		return googleAccessToken;
	}
	public void setGoogleAccessToken(String googleAccessToken) {
		this.googleAccessToken = googleAccessToken;
	}
	public String getGoogleRefreshToken() {
		return googleRefreshToken;
	}
	public void setGoogleRefreshToken(String googleRefreshToken) {
		this.googleRefreshToken = googleRefreshToken;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [instagramUserId=").append(instagramUserId)
				.append(", instagramAccessToken=").append(instagramAccessToken)
				.append(", instagramRefreshToken=")
				.append(instagramRefreshToken).append(", googleAccessToken=")
				.append(googleAccessToken).append(", googleRefreshToken=")
				.append(googleRefreshToken).append("]");
		return builder.toString();
	}
	
}
