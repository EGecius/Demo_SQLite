package db;

import com.egecius.demo_sqlite.Pet;

import java.util.List;

/**
 * Class which stores information about pets
 */
public interface PetsStore {

	void add(Pet pet);

	/** Reads all pets available in the database */
	List<Pet> read();

	void remove (Pet pet);

	void update(Pet pet);
}
