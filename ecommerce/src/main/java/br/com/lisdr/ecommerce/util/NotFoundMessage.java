package br.com.lisdr.ecommerce.util;

public class NotFoundMessage {
	private String message;
	
	public NotFoundMessage(String classname) {
		this.message = "Entity " + classname + " not found";
	}
	
	public String getMessage() {
		return this.message;
	}
}
