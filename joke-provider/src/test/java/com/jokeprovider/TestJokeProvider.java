package com.jokeprovider;

import org.junit.Test;


public class TestJokeProvider {

    @Test
    public void testgetJoke_returnJoke() {
        String joke = JokeProvider.getJoke();
        assert joke != null;
    }
}