package com.instagram2drive.json.responses.flickr;

public class Photo {
	private String farm;
	private String id;
	private String server;
	private String secret;
	private String title;
	private String owner;
	
	
	public String getFarm() {
		return farm;
	}


	public void setFarm(String farm) {
		this.farm = farm;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getServer() {
		return server;
	}


	public void setServer(String server) {
		this.server = server;
	}


	public String getSecret() {
		return secret;
	}


	public void setSecret(String secret) {
		this.secret = secret;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Photo [farm=").append(farm).append(", id=").append(id)
				.append(", server=").append(server).append(", secret=")
				.append(secret).append(", title=").append(title)
				.append(", owner=").append(owner).append("]");
		return builder.toString();
	}
	
	
	
	
}
