package com.tweetApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response {

	private String message;
	private ResponseStatus status;
	private String timestamp;

	public Response(String message, ResponseStatus status) {
		this.message = message;
		this.status = status;
	}
}
