package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.egecius.demo_sqlite.Pet;

import java.util.ArrayList;
import java.util.List;

import static db.PetsContract.PetsEntry.COLLUMN_BREED;
import static db.PetsContract.PetsEntry.COLLUMN_GENDER;
import static db.PetsContract.PetsEntry.COLLUMN_ID;
import static db.PetsContract.PetsEntry.COLLUMN_NAME;
import static db.PetsContract.PetsEntry.COLLUMN_WEIGHT;
import static db.PetsContract.PetsEntry.TABLE_NAME;

/**
 * Stores pets information in a database
 */
class PetsStoreImpl implements PetsStore {

	private final SQLiteDatabase db;

	public PetsStoreImpl(final Context context) {
		db = new PetsDbHelper(context).getWritableDatabase();
	}

	@Override
	public void add(final Pet pet) {
		ContentValues values = createContentValues(pet);
		db.insert(TABLE_NAME, null, values);
	}

	@NonNull
	private ContentValues createContentValues(final Pet pet) {
		ContentValues values = new ContentValues();
		values.put(COLLUMN_NAME, pet.name);
		values.put(COLLUMN_BREED, pet.breed);
		values.put(COLLUMN_GENDER, pet.gender);
		values.put(COLLUMN_WEIGHT, pet.weight);
		return values;
	}

	@Override
	public List<Pet> read() {
		Cursor cursor = db.query(TABLE_NAME, /*all columns*/ null, /* all rows*/ null, null, null,
				null, null);

		Pet pet = extractPetFromCursor(cursor);

		// TODO: 27/08/2017 return full list

		ArrayList<Pet> petsList = new ArrayList<>();
		petsList.add(pet);

		return petsList;
	}

	private Pet extractPetFromCursor(final Cursor cursor) {
		cursor.moveToFirst();

		int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLLUMN_ID));
		String name = cursor.getString(cursor.getColumnIndexOrThrow(COLLUMN_NAME));
		int breed = cursor.getInt(cursor.getColumnIndexOrThrow(COLLUMN_BREED));
		int gender = cursor.getInt(cursor.getColumnIndexOrThrow(COLLUMN_GENDER));
		int weight = cursor.getInt(cursor.getColumnIndexOrThrow(COLLUMN_WEIGHT));

		cursor.close();

		return new Pet(id, name, breed, gender, weight);
	}

	@Override
	public void remove(final Pet pet) {
		String id = String.valueOf(pet.id);
		String[] petIdValue = {id};
		db.delete(TABLE_NAME, COLLUMN_ID, petIdValue);
	}

	@Override
	public void update(final Pet pet) {

		ContentValues values = createContentValues(pet);

		String id = String.valueOf(pet.id);
		String[] petIdValue = {id};
		db.update(TABLE_NAME, values, COLLUMN_ID, petIdValue);
	}
}
