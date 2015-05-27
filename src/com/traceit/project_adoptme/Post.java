package com.traceit.project_adoptme;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

public class Post extends Activity {
	private static int RESULT_LOAD_IMAGE = 1;

	// private static final int CAMERA_CAPTURE_REQ_CODE = 100;

	public static final int MEDIA_TYPE_IMAGE = 1;
	String picturePath;
	// private Uri fileUri;
	String str1;
	Spinner s1;
	EditText e1, e2, e3, e4, e5, e6;
	Button b1, b2, b3;
	byte[] image;
	ImageView imageView;
	protected ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.post_ad);
		setTitle(" Post Ad ");
		s1 = (Spinner) findViewById(R.id.spinner);
		e1 = (EditText) findViewById(R.id.post_age);
		e2 = (EditText) findViewById(R.id.post_description);
		b2 = (Button) findViewById(R.id.post_from_gallery);
		e3 = (EditText) findViewById(R.id.post_owner_name);
		e4 = (EditText) findViewById(R.id.post_owner_email);
		e5 = (EditText) findViewById(R.id.post_owner_phone);
		e6 = (EditText) findViewById(R.id.post_owner_address);
		imageView = (ImageView) findViewById(R.id.post_imageview);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar2);

		b3 = (Button) findViewById(R.id.post_submit);
		List<String> categories = new ArrayList<String>();
		categories.add("Dog");
		categories.add("Cat");
		categories.add("Birds");
		categories.add("Others");

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Post.this,
				android.R.layout.simple_spinner_dropdown_item, categories);

		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(dataAdapter);

		s1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				str1 = arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		b2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_LOAD_IMAGE);

				ImageView imageView = (ImageView) findViewById(R.id.post_imageview);
				imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			}
		});

		b3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					image = readInFile(picturePath);
				} catch (Exception e) {
					e.printStackTrace();
				}

				ParseFile file = new ParseFile("Picture.jpeg", image);
				file.saveInBackground();
				mProgressBar.setVisibility(View.VISIBLE);

				AdParse ad = new AdParse();
				ad.setAge(e1.getText().toString());
				ad.setCategory(str1.toString());
				ad.setDescription(e2.getText().toString());
				ad.setName(e3.getText().toString());
				ad.setEmail(e4.getText().toString());
				ad.setPhone(e5.getText().toString());
				ad.setPhotoFile(file);

				ad.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException arg0) {
						// TODO Auto-generated method stub
						if (arg0 == null) {

							Toast.makeText(getApplicationContext(),
									"Posted Successfully", Toast.LENGTH_SHORT)
									.show();
							mProgressBar.setVisibility(View.INVISIBLE);

							Intent i = new Intent(Post.this, MainActivity.class);
							startActivity(i);
							finish();
						} else {
							Toast.makeText(getApplicationContext(),
									"Error !!" + arg0.getMessage(),
									Toast.LENGTH_SHORT).show();

						}

					}
				});

				/*
				 * ParseObject gameScore = new ParseObject("Ads");
				 * gameScore.put("category", str1.toString());
				 * gameScore.put("age", e1.getText().toString());
				 * gameScore.put("Image", file); gameScore.put("description",
				 * e2.getText().toString()); gameScore.put("owner_name",
				 * e3.getText().toString()); gameScore.put("owner_email", );
				 * gameScore.put("owner_phone", e5.getText().toString());
				 * gameScore.put("owner_address", e6.getText().toString());
				 * gameScore.saveInBackground(new SaveCallback() {
				 * 
				 * @Override public void done(ParseException e) { // TODO
				 * Auto-generated method stub
				 * 
				 * if (e == null) { Toast.makeText(getApplicationContext(),
				 * "Posted Successfully", Toast.LENGTH_SHORT) .show();
				 * mProgressBar.setVisibility(View.INVISIBLE);
				 * 
				 * Intent i = new Intent(Post.this, MainActivity.class);
				 * startActivity(i); finish(); } else {
				 * Toast.makeText(getApplicationContext(), "Error !!",
				 * Toast.LENGTH_SHORT).show(); } } });
				 */

			}

		});

	}

	private byte[] readInFile(String path) throws IOException {
		// TODO Auto-generated method stub
		byte[] data = null;
		File file = new File(path);
		InputStream input_stream = new BufferedInputStream(new FileInputStream(
				file));
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		data = new byte[16384]; // 16K
		int bytes_read;
		while ((bytes_read = input_stream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, bytes_read);
		}
		input_stream.close();
		return buffer.toByteArray();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImageUri = data.getData();
			picturePath = getPath(selectedImageUri);
			Toast.makeText(Post.this, picturePath, Toast.LENGTH_SHORT).show();

			imageView.setImageURI(selectedImageUri);

		}
	}

	@SuppressWarnings("deprecation")
	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
