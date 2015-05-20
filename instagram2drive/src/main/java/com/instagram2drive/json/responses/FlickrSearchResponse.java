package com.instagram2drive.json.responses;

import com.instagram2drive.json.responses.flickr.Photos;

public class FlickrSearchResponse {
	private Photos photos;

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlickrSearchResponse [photos=").append(photos)
				.append("]");
		return builder.toString();
	}
	
	
}
