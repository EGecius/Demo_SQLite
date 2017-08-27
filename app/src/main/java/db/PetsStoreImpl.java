package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
		ContentValues values = new ContentValues();
		values.put(COLLUMN_NAME, pet.name);
		values.put(COLLUMN_BREED, pet.breed);
		values.put(COLLUMN_GENDER, pet.gender);
		values.put(COLLUMN_WEIGHT, pet.weight);

		db.insert(TABLE_NAME, null, values);
	}

	@Override
	public List<Pet> read() {
		return null;
	}

	@Override
	public void remove(final Pet pet) {

		String id = String.valueOf(pet.id);
		db.delete(TABLE_NAME, COLLUMN_ID, id, new S);

	}

	@Override
	public void update(final Pet pet) {

	}
}
