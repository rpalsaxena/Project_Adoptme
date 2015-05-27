package com.traceit.project_adoptme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.parse.ParseUser;

public class MainActivity extends Activity {
	GridView gv;
	// Context context;
	Button b1;
	public static String[] prgmNameList = { "Birds", "Cat", "Dog", "Others" };
	public static int[] prgmImages = { R.drawable.birds, R.drawable.cat,
			R.drawable.dog, R.drawable.others };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);

		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(MainActivity.this, Post.class);
				startActivity(i);
			}
		});

		gv = (GridView) findViewById(R.id.gridView1);
		gv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		ParseUser.logOut();
		Intent intent = new Intent(this, Select.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		return true;

	}

	private long backPressedTime = 0; // used by onBackPressed()

	@Override
	public void onBackPressed() { // to prevent irritating accidental logouts
		long t = System.currentTimeMillis();
		if (t - backPressedTime > 2000) { // 2 secs
			backPressedTime = t;
			  moveTaskToBack(true); // exist app
			Toast.makeText(this, "Press back again to logout",
					Toast.LENGTH_SHORT).show();
			
		} else { // this guy is serious
			// clean up
			super.onBackPressed(); // bye
		}
	}
}