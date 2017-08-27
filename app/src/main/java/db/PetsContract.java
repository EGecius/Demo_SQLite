package db;

import android.provider.BaseColumns;

/**
 * Contract for how pets database should work.
 */
public class PetsContract {

	public static final class PetsEntry implements BaseColumns {
		public static final String TABLE_NAME = "pets";
		public static final String COLLUMN_ID = BaseColumns._ID;
		public static final String COLLUMN_NAME = "name";
		public static final String COLLUMN_BREED = "breed";
		public static final String COLLUMN_GENDER = "gender";
		public static final String COLLUMN_WEIGHT = "weight";
	}
}
