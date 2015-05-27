package com.traceit.project_adoptme;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseImageView;

/*
author 		: Rahul Saxena 
Location	: Delhi
*/

public class AdList extends ListActivity {

	// private ParseQueryAdapter<AdParse> mainAdapter;

	private AdAdapter adadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);

		getListView().setClickable(true);

		/*
		 * mainAdapter = new ParseQueryAdapter<AdParse>(this, AdParse.class);
		 */
		adadapter = new AdAdapter(this);

		adadapter.loadObjects();
		setListAdapter(adadapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);

		TextView t, t1, t2, t3, t4, t5, t6;
		String s, s1, s2, s3, s4, s5, s6;

		t = (TextView) v.findViewById(R.id.Details);
		s = t.getText().toString();

		t1 = (TextView) v.findViewById(R.id.abc1);
		s1 = t1.getText().toString();

		t2 = (TextView) v.findViewById(R.id.abc2);
		s2 = t2.getText().toString();

		t3 = (TextView) v.findViewById(R.id.abc3);
		s3 = t3.getText().toString();

		t4 = (TextView) v.findViewById(R.id.abc4);
		s4 = t4.getText().toString();

		t5 = (TextView) v.findViewById(R.id.abc5);
		s5 = t5.getText().toString();

		t6 = (TextView) v.findViewById(R.id.abc6);
		s6 = t6.getText().toString();

		Intent i = new Intent(AdList.this, Single_ad.class);
		i.putExtra("details", s);
		i.putExtra("age", s1);
		i.putExtra("category", s2);
		i.putExtra("email", s3);
		i.putExtra("name", s4);
		i.putExtra("phone", s5);
		i.putExtra("address", s6);

		ParseImageView img = (ParseImageView) v.findViewById(R.id.Adimage);
		img.setDrawingCacheEnabled(true);
		Bitmap bmp = img.getDrawingCache();

		Bundle extras = new Bundle();
		extras.putParcelable("Bitmap", bmp);
		i.putExtras(extras);

		startActivity(i);

	}
}
