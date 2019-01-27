package com.jokeprovider;


public class JokeProvider {

	public static String getJoke() {
		String[] jokes = {
			"My dog is an awesome fashion adviser. Every time I ask him what I look like in my clothes, he says “WOW!” | Source: https://short-funny.com/one-liners.php Great jokes and sayings.",
			"Why are eggs not very much into jokes? Because they could crack up. | Source: https://short-funny.com/one-liners.php Great jokes and sayings.",
			"Meanwhile in a parallel universe: “Oh for God’s sake! Where are all these extra single socks coming from?!” | Source: https://short-funny.com/one-liners.php Great jokes and sayings.",
			"What do you call a boomerang that doesn't come back? - A stick. | Source: https://short-funny.com/one-liners.php Great jokes and sayings.",
			"A pig stands in front of an electric socket: “Oh no, who put you into that wall?!” | Source: https://short-funny.com/one-liners-2.php Great jokes and sayings.",
			"Where do fish sleep? -  In the RiverBed. | Source: https://short-funny.com/one-liners-2.php Great jokes and sayings."
		};
		int jokePosition = (int) Math.floor(Math.random() * jokes.length);

		return jokes[jokePosition];
	}
}