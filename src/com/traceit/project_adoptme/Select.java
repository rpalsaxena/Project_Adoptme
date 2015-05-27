package com.traceit.project_adoptme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.ParseUser;

public class Select extends Activity {

	Button b1, b2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.select);

		b1 = (Button) findViewById(R.id.select_login);
		b2 = (Button) findViewById(R.id.select_signup);
		if (ParseUser.getCurrentUser() != null) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}

		else{
			b1.setOnClickListener(new OnClickListener() {
		

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Select.this, AuthenticateActivity.class);
				i.putExtra("PASS", "LOGIN");
				startActivity(i);
			}
		});
		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Select.this, AuthenticateActivity.class);
				i.putExtra("PASS", "SIGNUP");
				startActivity(i);

			}
		});
		}

	}
}
