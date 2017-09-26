import java.util.ArrayList;

public abstract class Animal {
	protected String type;
	protected String preferFood;
	protected ArrayList<Owner> owner;
	protected int foodPerDay;
	protected String name;
	protected Kennel kennel;

	/**
	 * Constructor for the animal
	 * 
	 * @param type
	 *            the animal's type (cats or dogs or birds)?
	 * @param kennels
	 *           animals reside
	 * @param name
	 *            The animal's name
	 * @param owners
	 *            A list of original owners: a copy is made
	 * @param food
	 *            The kind of food it eats
	 * @param mealsPerDay
	 *            Number of feeds per day
	 */
	public Animal() {
		type = "";
		name = "";
		kennel = null;
		owner = null;
		preferFood = "";
		this.foodPerDay = 0;
	}

	public Animal(String type, Kennel kennels, String name,
			ArrayList<Owner> owners, String food, int mealsPerDay, boolean run) {
		this.type = type;
		this.name = name;
		this.kennel = kennels;

		// We make a true copy of the owners ArrayList to make sure that we
		// don't break encapsulation: i.e. don't share object references with
		// other code
	
		this.owner = new ArrayList<Owner>();
		for (Owner o : owners) {
			Owner copy = new Owner(o.getName(), o.getPhone());
			owner.add(copy);
		}
		this.preferFood = food;
		this.foodPerDay = mealsPerDay;
		// this.run = run;
	}

	/**
	 * The food the animal likes to eat
	 * 
	 * @return The food
	 */
	protected String getPreferFood() {
		return preferFood;
	}

	/**
	 * What's his/her favourite food?
	 * 
	 * @param food
	 *            The food he/she likes
	 */
	protected void setPreferFood(String preferFood) {
		this.preferFood = preferFood;
	}

	/**
	 * Get the number of feeds per day the animal is fed
	 * 
	 * @return The number of feeds per day
	 */

	protected int getFoodPerDay() {
		return foodPerDay;
	}

	/**
	 * Set how many times a day to feed the animal
	 * 
	 * @param feeds
	 *            The number of feeds per day
	 */

	protected void setFoodPerDay(int foodPerDay) {
		this.foodPerDay = foodPerDay;
	}

	/**
	 * Get the name of the animal
	 * @return
	 */

	protected String getName() {
		return name;
	}

	/**
	 * Set animal name
	 * 
	 * @param name
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * Get animal type
	 * 
	 * @return type (Dog/Cat/Bird)
	 */
	protected String getType() {
		return type;
	}

	/**
	 * Set animal type
	 * 
	 * @param type (Dog/Cat/Bird)
	 */
	protected void setType(String type) {
		this.type = type;
	}

	/**
	 * Set animal movement 
	 * Cat
	 * 	share runs?
	 * Dog
	 * 	likes to walk?
	 * Bird
	 * 	fly?
	 * @param value
	 */
	protected abstract void setAnimalMovement(boolean value);

	/**
	 * Get animal movement
	 * @return
	 */
	protected abstract boolean getanimalMovement();

	/**
	 * Returns a copy of the original owners
	 * 
	 * @return A copy of the original owners as an array
	 */

	protected Owner[] getOwner() {
		Owner[] result = new Owner[owner.size()];
		result = owner.toArray(result);
		return result;
	}

	/**
	 * Set owner 
	 * @param owner
	 */

	protected void setOwner(ArrayList<Owner> owner) {
		this.owner = owner;
	}

	/**
	 * Get Kennel
	 * @return kennel 
	 * 	
	 */
	protected Kennel getKennel() {
		return kennel;

	}

	/**
	 * Set kennel
	 * @param kennel
	 * 			Animal residing
	 */				
	
	protected void setKennel(Kennel kennel) {
		this.kennel = kennel;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nKennel name: ").append(kennel.getKennelname()).append("\nAnimal name: ").append(name).append("\nOriginal Owner: ")
				.append(owner).append("\nFavfood: ").append(preferFood)
				.append("\nFoodPerDay: ").append(foodPerDay);

		return sb.toString();

	}

}
