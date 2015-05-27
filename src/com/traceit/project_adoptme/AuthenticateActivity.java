package com.traceit.project_adoptme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class AuthenticateActivity extends Activity {

	String pass;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	// private static final String PASS_PATTERN =
	// "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";

	protected String mAction;

	protected EditText mEmailField, mRetypeField;
	protected EditText mPasswordField;
	protected Button mButton;
	protected ProgressBar mProgressBar;

	String username, password, retype;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auth);
		pass = getIntent().getExtras().getString("PASS");
		mEmailField = (EditText) findViewById(R.id.auth_username);
		mPasswordField = (EditText) findViewById(R.id.auth_password);
		mRetypeField = (EditText) findViewById(R.id.auth_repass);
		mButton = (Button) findViewById(R.id.auth_submit_button);

		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);

		if (pass.equals("LOGIN")) {
			mButton.setText("Login");
			mRetypeField.setVisibility(View.INVISIBLE);
		} else {
			mButton.setText("Sign up");
			mRetypeField.setVisibility(View.VISIBLE);
		}

		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// mProgressBar.setVisibility(View.VISIBLE);

				username = mEmailField.getText().toString();
				password = mPasswordField.getText().toString();
				retype = mRetypeField.getText().toString();

				if (pass.equals("SIGNUP")) {
					if (!username.matches(EMAIL_PATTERN)) {
						new AlertDialog.Builder(AuthenticateActivity.this)
								.setTitle("Enter correct Email id")
								.setMessage("Enter correct email address!")
								.setPositiveButton(android.R.string.ok,
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface arg0,
													int arg1) {
												// TODO Auto-generated method
												// stub
												mEmailField.requestFocus();
											}
										})
								.setIcon(android.R.drawable.ic_dialog_alert)
								.show();

					} else if (!password.equals(retype)) {
						new AlertDialog.Builder(AuthenticateActivity.this)
								.setTitle("Enter correct password")
								.setMessage("Password not matching")
								.setPositiveButton(android.R.string.ok,
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

												mRetypeField.requestFocus();
											}
										})
								.setIcon(android.R.drawable.ic_dialog_alert)
								.show();
					}

					else {
						ParseUser user = new ParseUser();

						user.setUsername(username);
						user.setPassword(password);
						mProgressBar.setVisibility(View.VISIBLE);

						user.signUpInBackground(new SignUpCallback() {
							public void done(com.parse.ParseException e) {
								if (e == null) {
									// Hooray! Let them use the app now.

									startActivity(new Intent(
											AuthenticateActivity.this,
											MainActivity.class));
									mProgressBar.setVisibility(View.INVISIBLE);

								} else {
									// Sign up didn't succeed. Look at the
									// ParseException to figure out what went
									// wrong
									mProgressBar.setVisibility(View.INVISIBLE);

									Toast.makeText(AuthenticateActivity.this,
											"Sign up failed! Try again.",
											Toast.LENGTH_LONG).show();
								}
							}
						});
					}

				} else {
					if (!username.matches(EMAIL_PATTERN)) {
						new AlertDialog.Builder(AuthenticateActivity.this)
								.setTitle("Enter correct Email id")
								.setMessage("Enter correct email address!")
								.setPositiveButton(android.R.string.ok,
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int which) {

												mEmailField.requestFocus();
											}
										})
								.setIcon(android.R.drawable.ic_dialog_alert)
								.show();
						mProgressBar.setVisibility(View.INVISIBLE);
					}

					else {
						ParseUser.logInInBackground(username, password,
								new LogInCallback() {
									public void done(ParseUser user,
											com.parse.ParseException e) {
										mProgressBar
												.setVisibility(View.INVISIBLE);
										if (user != null) {
											// Hooray! The user is logged in.
											startActivity(new Intent(
													AuthenticateActivity.this,
													MainActivity.class));
										} else {
											// Login failed. Look at the
											// ParseException to see what
											// happened.
											Toast.makeText(
													AuthenticateActivity.this,
													"Login failed! Try again.",
													Toast.LENGTH_LONG).show();
										}
									}
								});
					}
				}
			}
		});

	}
}
