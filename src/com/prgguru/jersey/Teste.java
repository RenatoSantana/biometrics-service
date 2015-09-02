package com.prgguru.jersey;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

public class Teste {

	public static void main(String[] args) {

		String string = "Javacodegeeks";

		// Get bytes from string

		byte[] byteArray = Base64.encodeBase64(string.getBytes());

		// Print the encoded byte array

		System.out.println(Arrays.toString(byteArray));

		// Print the encoded string

		String encodedString = new String(byteArray);

		System.out.println(string + " = " + encodedString);
		
		
		byte[] byteArray2 = Base64.decodeBase64(encodedString);
		
		System.out.println(Arrays.toString(byteArray2));
		
		String encodedStrin2 = new String(byteArray2);

		System.out.println(string + " = " + encodedStrin2);
		
		
		
		byte[] byteArray3 = Base64.encodeBase64(encodedStrin2.getBytes());

		System.out.println(Arrays.toString(byteArray3));
		
		
		
	}
}