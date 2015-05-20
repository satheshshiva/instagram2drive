package com.instagram2drive.json.responses.flickr;

import java.util.List;

public class Photos {
	private List<Photo> photo;

	public List<Photo> getPhoto() {
		return photo;
	}

	public void setPhoto(List<Photo> photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Photos [photo=").append(photo).append("]");
		return builder.toString();
	}
	
	
}
