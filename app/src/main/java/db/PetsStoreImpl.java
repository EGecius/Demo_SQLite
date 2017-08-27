package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.egecius.demo_sqlite.Pet;

import java.util.ArrayList;
import java.util.List;

import static db.PetsContract.PetsEntry.COLUMN_BREED;
import static db.PetsContract.PetsEntry.COLUMN_GENDER;
import static db.PetsContract.PetsEntry.COLUMN_ID;
import static db.PetsContract.PetsEntry.COLUMN_NAME;
import static db.PetsContract.PetsEntry.COLUMN_WEIGHT;
import static db.PetsContract.PetsEntry.TABLE_NAME;

/**
 * Stores pets information in a database
 */
public class PetsStoreImpl implements PetsStore {

	private final SQLiteDatabase db;

	public PetsStoreImpl(final Context context) {
		db = new PetsDbHelper(context).getWritableDatabase();
	}

	@Override
	public void add(final Pet pet) {
		ContentValues values = createContentValues(pet);
		db.insert(TABLE_NAME, /*nullColumnHack*/ null, values);
	}

	@NonNull
	private ContentValues createContentValues(final Pet pet) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, pet.name);
		values.put(COLUMN_BREED, pet.breed);
		values.put(COLUMN_GENDER, pet.gender);
		values.put(COLUMN_WEIGHT, pet.weight);
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

		int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
		String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
		int breed = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BREED));
		int gender = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
		int weight = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WEIGHT));

		cursor.close();

		return new Pet(id, name, breed, gender, weight);
	}

	@Override
	public void remove(final Pet pet) {
		String id = String.valueOf(pet.id);
		String[] petIdValue = {id};
		db.delete(TABLE_NAME, COLUMN_ID, petIdValue);
	}

	@Override
	public void update(final Pet pet) {

		ContentValues values = createContentValues(pet);

		String id = String.valueOf(pet.id);
		String[] petIdValue = {id};
		db.update(TABLE_NAME, values, COLUMN_ID, petIdValue);
	}
}
