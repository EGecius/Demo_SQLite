package com.egecius.demo_sqlite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import db.PetsStoreImpl;

public class MainActivity extends AppCompatActivity {

	public static final int YORKSHIRE = 1;
	public static final int FEMALE = 2;
	PetsStoreImpl petsStore;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupButton();

		initStore();
		addSinglePet();
	}

	private void setupButton() {
		findViewById(R.id.read_button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				readPets();
			}
		});
	}

	private void initStore() {
		petsStore = new PetsStoreImpl(getApplicationContext());
	}

	private void addSinglePet() {
		Log.v("Eg:MainActivity:34", "addSinglePet");
		Pet jorke = new Pet(-1, "jorke", YORKSHIRE, FEMALE, 6);
		petsStore.add(jorke);
	}


	private void readPets() {
		List<Pet> read = petsStore.read();

		Log.i("Eg:MainActivity:41", "readPets read " + read);
	}
}
