package com.example.jokeui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "JOKE_EXTRA";
    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        initUI();

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(JOKE_EXTRA)) {
            String joke = intent.getStringExtra(JOKE_EXTRA);
            showJoke(joke);
        }
    }

    private void showJoke(String joke) {
        jokeTextView.setText(joke);
    }

    private void initUI() {
        jokeTextView = findViewById(R.id.jokeTextView);
    }
}
