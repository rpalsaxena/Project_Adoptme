package com.traceit.project_adoptme;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Ads")
public class AdParse extends ParseObject {

	public AdParse() {
		// TODO Auto-generated constructor stub
	}

	public String getAge() {
		return getString("age");

	}

	public String getCategory() {
		return getString("category");

	}

	public String getDescription() {
		return getString("description");

	}

	public String getAddress() {
		return getString("owner_address");

	}

	public String getEmail() {
		return getString("owner_email");

	}

	public String getName() {
		return getString("owner_name");

	}

	public String getPhone() {
		return getString("owner_phone");

	}

	public ParseFile getPhotoFile() {
		return getParseFile("Image");
	}

	public void setAge(String age) {
		put("age", age);
	}

	public void setCategory(String age) {
		put("category", age);
	}

	public void setDescription(String age) {
		put("description", age);
	}

	public void setAddress(String age) {
		put("owner_address", age);
	}

	public void setEmail(String age) {
		put("owner_email", age);
	}

	public void setName(String age) {
		put("owner_name", age);
	}

	public String getDate() {
		return getString("createdAt");
	}

	public void setdate(String age) {
		put("createdAt", age);
	}

	public void setPhone(String age) {
		put("owner_phone", age);
	}

	public void setPhotoFile(ParseFile file) {
		put("Image", file);
	}

}
