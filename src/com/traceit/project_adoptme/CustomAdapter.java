package com.traceit.project_adoptme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams") public class CustomAdapter extends BaseAdapter {

	static String s;

	String[] result;
	Context context;
	int[] imageId;
	private static LayoutInflater inflater = null;

	public CustomAdapter(MainActivity mainActivity, String[] prgmNameList,
			int[] prgmImages) {

		result = prgmNameList;
		context = mainActivity;
		imageId = prgmImages;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return result.length;
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class Holder {
		TextView tv;
		ImageView img;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder = new Holder();
		View rowView;

		rowView = inflater.inflate(R.layout.grid, null);
		holder.tv = (TextView) rowView.findViewById(R.id.textView1);
		holder.img = (ImageView) rowView.findViewById(R.id.imageView1);

		holder.tv.setText(result[position]);
		holder.img.setImageResource(imageId[position]);

		rowView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Toast.makeText(context, "You Clicked "+result[position],
				// Toast.LENGTH_LONG).show();
				s = result[position];
				Intent i = new Intent(context, AdList.class);
				i.putExtra("Category", result[position]);
				context.startActivity(i);
			}
		});

		return rowView;
	}

}