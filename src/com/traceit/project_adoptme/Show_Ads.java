package com.traceit.project_adoptme;

/*
 This is not in use
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Show_Ads extends ListActivity {
	protected ProgressBar mProgressBar;
	Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.show_ads);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
	}

	@Override
	public void onResume() {
		super.onResume();
		getLatestPosts();
	}

	@SuppressWarnings("unchecked")
	protected void getLatestPosts() {
		mProgressBar.setVisibility(View.VISIBLE);

		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery("Ads");
		query.whereEqualTo("category", getIntent().getStringExtra("Category"));
		query.setLimit(100);
		query.orderByDescending("createAt");

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				if (e == null) {
					ArrayList<HashMap<String, String>> articles = new ArrayList<HashMap<String, String>>();
					ArrayList<HashMap<String, Bitmap>> images = new ArrayList<HashMap<String, Bitmap>>();
					for (ParseObject result : objects) {
						HashMap<String, String> article = new HashMap<String, String>();
						HashMap<String, Bitmap> im = new HashMap<String, Bitmap>();
						article.put("category", result.getString("category"));
						article.put("description",
								result.getString("description"));

						ParseFile file = result.getParseFile("");
						file.getDataInBackground(new GetDataCallback() {

							@Override
							public void done(byte[] arg0, ParseException arg1) {
									if (arg1 == null) {

									bitmap = BitmapFactory.decodeByteArray(
											arg0, 0, arg0.length);

								} else {

								}

							}
						});

						// article.put("image", bitmap);
						im.put("images", bitmap);
						images.add(im);

						articles.add(article);

					}
					mProgressBar.setVisibility(View.INVISIBLE);

					SimpleAdapter adapter = new SimpleAdapter(Show_Ads.this,
							articles, R.layout.ad_single, new String[] {
									"category" }, new int[] {
									R.id.Details });

					SimpleAdapter adapter1 = new SimpleAdapter(Show_Ads.this,
							images, R.layout.ad_single, new String[] {
									"images" }, new int[] {R.id.Adimage});

					setListAdapter(adapter1);
				} else {
					Log.e("TAG", "Exception caught!", e);
				}
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TextView urlLabel = (TextView) v.findViewById(android.R.id.text2);
		Intent intent = new Intent(Show_Ads.this, Single_ad.class);
		// intent.setData(Uri.parse(urlLabel.getText().toString()));
		startActivity(intent);
	}
}
