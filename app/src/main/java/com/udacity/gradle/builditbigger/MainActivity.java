package com.udacity.gradle.builditbigger;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.jokeui.JokeActivity;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.udacity.gradle.builditbigger.backend.jokesApi.JokesApi;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private ProgressBar fetchJokeProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        fetchJokeProgressBar = findViewById(R.id.fetchJokeProgressBar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new JokesAsyncTask().execute();
    }

    class JokesAsyncTask extends AsyncTask<Void, Void, String> {
        private JokesApi jokesApi = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showLoadingBar();
        }

        @Override
        protected String doInBackground(Void... voids) {
            if(jokesApi == null) {
                JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("http://192.168.43.157:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                jokesApi = builder.build();
            }

            try {
                return jokesApi.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String joke) {
            super.onPostExecute(joke);
            hideLoadingBar();

            Intent intent = new Intent(MainActivity.this, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_EXTRA, joke);
            startActivity(intent);
        }
    }

    private void hideLoadingBar() {
        fetchJokeProgressBar.setVisibility(View.GONE);
    }

    private void showLoadingBar() {
        fetchJokeProgressBar.setVisibility(View.VISIBLE);
    }
}
