package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.egecius.demo_sqlite.Pet;

import java.util.List;

import static db.PetsContract.PetsEntry.*;

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
		return null;
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
