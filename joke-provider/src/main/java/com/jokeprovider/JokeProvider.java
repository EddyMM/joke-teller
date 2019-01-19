package com.jokeprovider;


public class JokeProvider {

	public static String getJoke() {
		String[] jokes = {"JK_A", "JK_B", "JK_C", "JK_D", "JK_E", "JK_F"};
		int jokePosition = (int) Math.floor(Math.random() * jokes.length);

		return jokes[jokePosition];
	}
}