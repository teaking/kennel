import java.util.ArrayList;

/**
 *
 * 
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Dog extends Animal {

	private boolean likesBones;
	private boolean walk;

	/**
	 * 
	 * @param type
	 *            Dog
	 * @param kennel
	 * @param name
	 * @param owners
	 * @param food
	 * @param mealsPerDay
	 * @param walk
	 * @param likesBones
	 *            some dog's likes bones (dog specific)
	 */
	public Dog(String type, Kennel kennel, String name,
			ArrayList<Owner> owners, String food, int mealsPerDay,
			boolean walk, boolean likesBones) {
		super(type, kennel, name, owners, food, mealsPerDay, walk);
		this.likesBones = likesBones;
		this.walk = walk;
		// kennel = new Kennel();

	}

	/**
	 * Constructor for the animal Dog
	 */
	public Dog() {
	}

	/**
	 * Does the dog like bones?
	 * 
	 * @return true if he does
	 */
	public boolean isLikesBones() {
		return likesBones;
	}

	/**
	 * set whether dog likes bones
	 * 
	 * @param likesBones
	 */
	public void setLikesBones(boolean likesBones) {
		this.likesBones = likesBones;
	}

	/*
	 * Sets animal movement Dog need walk? (non-Javadoc)
	 * 
	 * @see Animal#setAnimalMovement(boolean)
	 */
	@Override
	public void setAnimalMovement(boolean value) {
		this.walk = value;
	}

	/*
	 * Gets animal movement Dog need walk? (non-Javadoc)
	 * 
	 * @see Animal#getanimalMovement()
	 */
	@Override
	public boolean getanimalMovement() {
		return walk;
	}

	/**
	 * A basic implementation to just return all the data in string form
	 */

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nKennel Name:").append(kennel.getKennelname())
				.append("\nDog name: ").append(name)
				.append("\nOriginal Owner: ").append(owner)
				.append("\nFavourite food: ").append(preferFood)
				.append("\nFood Per Day: ").append(foodPerDay);
		if (likesBones) {
			sb.append("\n").append(name).append(" loves bones.");
		} else {
			sb.append("\n").append(name).append(" hates bones.");
		}
		if (walk) {
			sb.append("\n").append(name).append(" needs to take walk.");
		} else {
			sb.append("\n").append(name).append(" doesn't need to take walk.");
		}
		return (sb.toString());

	}

}
