package com.tia102g4.util;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class Base64Util {
	
	// 將 Base64 字符串轉換為 Byte[]
	public Byte[] base64ToByteArray(String base64String) {

		byte[] byteArray = Base64.getDecoder().decode(base64String);

		Byte[] byteObjectArray = new Byte[byteArray.length];

		for (int i = 0; i < byteArray.length; i++) {
			byteObjectArray[i] = byteArray[i];
		}

		return byteObjectArray;

	}

	// 將 Byte[] 轉換為 Base64 字符串
	public String byteArrayTobase64(Byte[] byteObjectArray) {

		byte[] byteArray = new byte[byteObjectArray.length];

		for (int i = 0; i < byteObjectArray.length; i++) {
			byteArray[i] = byteObjectArray[i];
		}
		String base64String = Base64.getEncoder().encodeToString(byteArray);

		return base64String;

	}
}
