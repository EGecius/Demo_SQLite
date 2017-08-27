package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static db.PetsContract.PetsEntry.COLLUMN_BREED;
import static db.PetsContract.PetsEntry.COLLUMN_GENDER;
import static db.PetsContract.PetsEntry.COLLUMN_ID;
import static db.PetsContract.PetsEntry.COLLUMN_NAME;
import static db.PetsContract.PetsEntry.COLLUMN_WEIGHT;
import static db.PetsContract.PetsEntry.TABLE_NAME;

/**
 * Abstracts away creation of tables
 */
public class PetsDbHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "pets.db";

	public PetsDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase db) {

		String SQL_CREATE_COMMAND = "CREATE TABLE " + TABLE_NAME + "(" +
				COLLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				COLLUMN_NAME + " TEXT NOT NULL, " +
				COLLUMN_BREED + "breed INTEGER, " +
				COLLUMN_GENDER + "gender INTEGER NOT NULL, " +
				COLLUMN_WEIGHT + "weight INTEGER DEFAULT 0)";

		db.execSQL(SQL_CREATE_COMMAND);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
		// The database is still at version 1, so there's nothing to do be done here.
	}
}
