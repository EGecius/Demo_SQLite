package com.egecius.demo_sqlite;

/**
 * Data class for a single pet
 */
public class Pet {

	public final int id;
	public final String name;
	public final int breed;
	public final int gender;
	public final int weight;

	public Pet(final int id, final String name, final int breed, final int gender, final int weight) {
		this.id = id;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		this.weight = weight;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (!(o instanceof Pet)) return false;

		final Pet pet = (Pet) o;

		if (id != pet.id) return false;
		if (breed != pet.breed) return false;
		if (gender != pet.gender) return false;
		if (weight != pet.weight) return false;
		return name != null ? name.equals(pet.name) : pet.name == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + breed;
		result = 31 * result + gender;
		result = 31 * result + weight;
		return result;
	}

	@Override
	public String toString() {
		return "Pet{" +
				"id=" + id +
				", name='" + name + '\'' +
				", breed=" + breed +
				", gender=" + gender +
				", weight=" + weight +
				'}';
	}
}
