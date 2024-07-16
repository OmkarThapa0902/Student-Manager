package com.manager.utility;

public class Messages {
	private String contentString;
	private String typeString;
	public String getContentString() {
		return contentString;
	}
	public void setContentString(String contentString) {
		this.contentString = contentString;
	}
	public String getTypeString() {
		return typeString;
	}
	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}
	@Override
	public String toString() {
		return "Messages [contentString=" + contentString + ", typeString=" + typeString + "]";
	}
	public Messages(String contentString, String typeString) {
		super();
		this.contentString = contentString;
		this.typeString = typeString;
	}
	public Messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Messages(String string) {
		// TODO Auto-generated constructor stub
		this.contentString = string;
	}
	

}