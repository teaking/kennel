import java.util.ArrayList;

public class Bird extends Animal {
	private boolean fly;

	// Hold weather birds fly

	/**
	 * Constructor to create Bird animal
	 * 
	 * @param type 
	 * 				Bird
	 * @param kennel
	 * @param name
	 * @param owners
	 * @param food
	 * @param mealsPerDay
	 * @param fly
	 */

	public Bird(String type, Kennel kennel, String name, ArrayList<Owner> owners,
			String food, int mealsPerDay, boolean fly) {
		super(type, kennel, name, owners, food, mealsPerDay, fly);
		this.fly = fly;
		// cage = new Kennel();

	}

	public Bird() {

	}

	/*
	 * Get Bird Movement do bird fly? (non-Javadoc)
	 * 
	 * @see Animal#setAnimalMovement(boolean)
	 */
	@Override
	public void setAnimalMovement(boolean value) {
		fly = value;
	}

	/*
	 * Get Bird movement do animal fly? (non-Javadoc)
	 * 
	 * @see Animal#getanimalMovement()
	 */
	@Override
	public boolean getanimalMovement() {
		return fly;
	}

	/*
	 * Print all the information about bird (non-Javadoc)
	 * 
	 * @see Animal#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nKennel name:").append(kennel.getKennelname())
				.append("\nBird name:").append(name)
				.append("\nOriginal Owner: ").append(owner)
				.append("\nFavourite food: ").append(preferFood)
				.append("\nFood Per Day: ").append(foodPerDay);
		if (fly) {
			sb.append("\n").append(name).append(" :Yes it fly.");
		} else {
			sb.append("\n").append(name).append(" :No it won't fly.");
		}
		return (sb.toString());

	}

}
