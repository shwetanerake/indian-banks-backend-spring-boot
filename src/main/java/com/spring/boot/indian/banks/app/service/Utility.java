package com.spring.boot.indian.banks.app.service;

import org.springframework.http.ResponseEntity;

import com.spring.boot.indian.banks.app.response.ApiResponse;

public class Utility {

	public static ResponseEntity<Object> buildResponseEntity(ApiResponse apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}