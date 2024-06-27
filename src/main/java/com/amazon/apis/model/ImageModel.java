package com.amazon.apis.model;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "image_model")
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long imageId;
	private String imageName;
	private String imageType;
	@Column(length = 50000000)
	private byte[] picByte;

	public ImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageModel(String imageName, String imageType, byte[] picByte) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.picByte = picByte;
	}

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	@Override
	public String toString() {
		return "ImageModel [imageId=" + imageId + ", imageName=" + imageName + ", imageType=" + imageType + ", picByte="
				+ Arrays.toString(picByte) + "]";
	}

}
