package br.com.webscrap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

	private static final String TAG = SplashActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

//		Glide.with(SplashActivity.this).load("http://gifdanceparty.giphy.com/assets/dancers/smooch.gif").into((ImageView) findViewById(R.id.image_logo));

		new AsyncTask<Void, Void, Boolean>() {
			@Override
			protected Boolean doInBackground(Void... params) {

				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					Log.e(TAG, e.getMessage());
				}

				return true;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				startActivity(new Intent(getBaseContext(), HomeActivity.class));
				finish();
			}
		}.execute();
	}
}