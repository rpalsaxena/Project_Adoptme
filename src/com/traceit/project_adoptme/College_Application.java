package com.traceit.project_adoptme;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

public class College_Application extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		ParseObject.registerSubclass(AdParse.class);

		/*
		 * Add Parse initialization code here
		 */
		Parse.initialize(this, "QheOxUmzGuKK8GXvVYsdzeZ76rO8PG2MbuIMf9CX",
				"tCeorUzjF3CYFsjezYc3WT2i58e8XfS961WiIEzx");

		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);

		ParseObject testObject = new ParseObject("asdf");
		testObject.put("foo", "bar");

		testObject.saveInBackground();
	}
}
