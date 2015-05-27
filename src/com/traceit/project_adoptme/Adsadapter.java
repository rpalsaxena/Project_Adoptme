package com.traceit.project_adoptme;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.parse.ParseObject;

public class Adsadapter extends ArrayAdapter<ParseObject> {
	protected Context mContext;
	protected LayoutInflater mInflater;
	protected ParseObject[] mUsers;
	protected ArrayList<ParseObject> mUserRelations;

	public Adsadapter(Context context, ArrayList<ParseObject> userRelations) {
		super(context, R.layout.ad_single, userRelations);
		mContext = context;

		mUserRelations = userRelations;
		mInflater = LayoutInflater.from(mContext); 
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	ViewHolder holder;
	if(convertView == null)
	{

		convertView = mInflater.inflate(R.layout.ad_single, null);
  
		holder = new ViewHolder();                
		holder.emailLabel = (TextView) convertView.findViewById(R.id.Details);               
		/*holder.thumbnail = (SmartImageView) convertView.findViewById(R.id.Adimage);
		*/convertView.setTag(holder);
	}

	else {                
	    holder = (ViewHolder) convertView.getTag();
	} 
		
		
		
		
		return super.getView(position, convertView, parent);
	}

	private static class ViewHolder {            
	    TextView emailLabel;  /*          
	    SmartImageView thumbnail;*/ 
	    CheckBox checkbox;
	}

}
