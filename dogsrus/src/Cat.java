import java.util.ArrayList;

public class Cat extends Animal {
	private boolean run;

	/**
	 * Constructor to create Cats
	 * 
	 * @param type
	 *            Cat
	 * @param kennel
	 * @param name
	 * @param owners
	 * @param food
	 * @param mealsPerDay
	 * @param run
	 */
	public Cat(String type, Kennel kennel, String name,
			ArrayList<Owner> owners, String food, int mealsPerDay, boolean run) {
		super(type, kennel, name, owners, food, mealsPerDay, run);
		this.run = run;
	}

	/**
	 * Constructor to create Cats
	 */
	public Cat() {

	}

	/*
	 * Set Cat movement Cat share runs? (non-Javadoc)
	 * 
	 * @see Animal#setAnimalMovement(boolean)
	 */
	@Override
	public void setAnimalMovement(boolean value) {
		run = value;
	}

	/*
	 * Get Cat movement Cat share runs? (non-Javadoc)
	 * 
	 * @see Animal#getanimalMovement()
	 */
	@Override
	public boolean getanimalMovement() {
		return run;
	}

	/*
	 * Print all the information about Cat (non-Javadoc)
	 * 
	 * @see Animal#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nKennel name:").append(kennel.getKennelname())
				.append("\nCat name:").append(name)
				.append("\nOriginal Owner: ").append(owner)
				.append("\nFavourite food: ").append(preferFood)
				.append("\nFood Per Day: ").append(foodPerDay);
		if (run) {
			sb.append("\n").append(name).append(" likes to share run.");
		} else {
			sb.append("\n").append(name).append(" must not share run.");
		}
		return (sb.toString());

	}

}
