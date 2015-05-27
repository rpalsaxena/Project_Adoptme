package com.traceit.project_adoptme;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class AdAdapter extends ParseQueryAdapter<AdParse> {

	public AdAdapter(Context c) {
		super(c, new ParseQueryAdapter.QueryFactory<AdParse>() {
			String s = CustomAdapter.s;

			public ParseQuery<AdParse> create() {
				ParseQuery<AdParse> query = new ParseQuery<AdParse>("Ads");
				query.whereEqualTo("category", s);
				query.setLimit(100);
				query.orderByDescending("createAt");

				return query;
			}
		});
	}

	
	@Override
	public View getItemView(AdParse arg0, View arg1, ViewGroup arg2) {

		if (arg1 == null) {
			arg1 = View.inflate(getContext(), R.layout.ad_single, null);
		}
		super.getItemView(arg0, arg1, arg2);
		
		ParseImageView pImg = (ParseImageView) arg1.findViewById(R.id.Adimage);
		ParseFile pfile = arg0.getParseFile("Image");
		pImg.setDrawingCacheEnabled(true);
		if(pfile != null)
		{
			pImg.setParseFile(pfile);
			pImg.loadInBackground(new GetDataCallback() {
				
				@Override
				public void done(byte[] arg0, ParseException arg1) {
																																																						
				}
			});
		}
		TextView t= (TextView) arg1.findViewById(R.id.Details);
		t.setText(arg0.getDescription());
		
		TextView t1,t2,t3,t4,t5,t6,t7;
		t1 = (TextView) arg1.findViewById(R.id.abc1);
		t1.setText(arg0.getAge());
		
		t2 = (TextView) arg1.findViewById(R.id.abc2);
		t2.setText(arg0.getCategory());
		
		t3 = (TextView) arg1.findViewById(R.id.abc3);
		t3.setText(arg0.getEmail());
		
		t4 = (TextView) arg1.findViewById(R.id.abc4);
		t4.setText(arg0.getName());
		
		t5 = (TextView) arg1.findViewById(R.id.abc5);
		t5.setText(arg0.getPhone());
		
		t6 = (TextView) arg1.findViewById(R.id.abc6);
		t6.setText(arg0.getAddress());
		
		t7 =(TextView) arg1.findViewById(R.id.abcdate);
		t7.setText(arg0.getDate());
		return arg1;
	}
}
