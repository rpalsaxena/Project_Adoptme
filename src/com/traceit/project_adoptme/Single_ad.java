package com.traceit.project_adoptme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Single_ad extends Activity {

	TextView t2, t3, t4, t5, t6, t7, t8;

	// TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_ads);

		// t1 = (TextView) findViewById(R.id.breed_name);

		t2 = (TextView) findViewById(R.id.breed_category);
		t3 = (TextView) findViewById(R.id.breed_age);
		t4 = (TextView) findViewById(R.id.breed_desc);

		t5 = (TextView) findViewById(R.id.owner_b_name);
		t6 = (TextView) findViewById(R.id.owner_b_contact_no);
		t7 = (TextView) findViewById(R.id.owner_b_email);
		t8 = (TextView) findViewById(R.id.owner_b_address);

		Intent i = getIntent();

		// t1.setText(i.getExtras().getString("name"));

		t2.setText(i.getExtras().getString("category").toString());
		t3.setText(i.getExtras().getString("age").toString());
		t4.setText(i.getExtras().getString("details").toString());
		t5.setText(i.getExtras().getString("name").toString());
		t6.setText(i.getExtras().getString("phone").toString());
		t7.setText(i.getExtras().getString("email").toString());
		t8.setText(i.getExtras().getString("address").toString());

		ImageView x = (ImageView) findViewById(R.id.breed_pic);
		Bundle extras = getIntent().getExtras();
		Bitmap bmp = (Bitmap) extras.getParcelable("Bitmap");
		x.setImageBitmap(bmp);

		t6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String uri = "tel:" + t6.toString().trim();
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse(uri));
				startActivity(intent);

			}
		});

		t7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri
						.fromParts("mailto", t7.toString(), null));
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
				startActivity(Intent
						.createChooser(emailIntent, "Send email..."));
			}
		});
		Toast.makeText(getApplicationContext(), t2.toString() ,
				Toast.LENGTH_LONG).show();
	}

}
