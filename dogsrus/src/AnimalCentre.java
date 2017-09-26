import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;

/**
 * 
 * To model a Animal Centre - holds collection of animals
 * 
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 * 
 */
public class AnimalCentre {
	private String centreName;
	private ArrayList<Animal> animal;
	private int capacity;
	private ArrayList<String> kennelsName;

	/**
	 * Creates a kennel with a default size 30
	 */

	public AnimalCentre() {
		/*
		 * hold 3o animals
		 */
		this(30);
	}

	/**
	 * Create a Animal centre
	 * 
	 * @param animals
	 *            The capacity of the kennel
	 */
	public AnimalCentre(int animalsSize) {
		// nextFreeLocation = 0;
		// no animals in collection at start
		capacity = animalsSize;
		animal = new ArrayList<Animal>(capacity);

	}

	/**
	 * Gets all the kennels name set to different animal
	 * 
	 * @return kennelsName animal's kennel name
	 */
	public ArrayList<String> getName() {
		kennelsName = new ArrayList<String>();
		for (Animal a : animal) {
			kennelsName.add(a.getKennel().getKennelname());
		}

		return kennelsName;
	}

	/**
	 * Set new kennel name
	 * 
	 * @param theName
	 *            Animal centre name
	 */
	public void setKennelName(String theName) {

		
		Animal changeAnimalKennel = null;
		String previousKennelName;
			do{
		 previousKennelName = JOptionPane
				.showInputDialog("Enter previous Kennel name");
		if (previousKennelName == null) {
			int reply = JOptionPane.showConfirmDialog(null,
					"quit?", "Quit",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				return;
			}
		}
		}while(previousKennelName == null || previousKennelName.isEmpty());
		for (Animal a : animal) {
			if (a.getKennel().getKennelname().equals(previousKennelName)) {
				changeAnimalKennel = a;
			}

		}
		if (changeAnimalKennel == null) {
			JOptionPane.showMessageDialog(null, previousKennelName,
					"does not contain such kennel", JOptionPane.ERROR_MESSAGE);
		} else {
			changeAnimalKennel.getKennel().setKennelname(theName);

		}

	}

	/**
	 * Set the maximum size of the centre(number of animals)
	 * 
	 * @param capacity
	 *            The max no. of animals
	 */
	public void setAnimalCentreCapacity(int capacity) {

		if (animal.size() < capacity) {
			this.capacity = capacity;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"The capacity(number) you enter is smaller than the animal residing right now!\nPlease check and enter again\n")
					.append("The number you enter is: ").append(capacity)
					.append("Knennel in the centre is: ").append(animal.size());
			JOptionPane.showMessageDialog(null, sb.toString(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Maximum number of animal that animal centre can house
	 * 
	 * @return capacity The max number of animal
	 */
	public int getAnimalCentreCapacity() {
		return capacity;
	}

	/**
	 * This method returns the number of animal in a kennel
	 * 
	 * @return int (animal.size()) Current number of animal in the kennel
	 */

	public int getNumofAnimal() {
		return animal.size();
	}

	/**
	 * Enables a user to add a animal to the Kennel
	 * 
	 * @param theAnimal
	 *            A new animal to home
	 */

	public void addAnimal(Animal theAnimal) {

		animal.add(theAnimal);
		Collections.sort(animal, new Comparator<Animal>() {
			// Sorting the animals with alphabetical comparing their name
			public int compare(Animal a1, Animal a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});

	}

	/**
	 * Enables a user to delete a animal from the centre
	 * 
	 * @param who
	 *            animal to remove
	 */
	public void removeAnimal(String who) {
		ArrayList<Animal> animalName = new ArrayList<Animal>();
		// search animal by the name

		for (Animal a : animal) {
			/*
			 * all the animal that has similar names will be added in the
			 * ArrayList called animalName
			 */
			if (who.toUpperCase().equals(a.getName().toUpperCase())) {
				/*
				 * Comparing user input with the name of animals that are inside
				 * the centre
				 */
				animalName.add(a);
				// adding to the ArrayList animalName
			}
		}
		if (animalName.isEmpty()) {
			// Animal not in the cage
			JOptionPane.showMessageDialog(null, "cannot remove " + who
					+ " - not in Animal Centre", "Removing",
					JOptionPane.ERROR_MESSAGE);
		} else if (animalName.size() == 1) {
			// Animal name found and have only 1 animal with that name
			animal.remove(animalName.get(0));
			// Remove the animals
			JOptionPane.showMessageDialog(null, "Removed " + who, "Removing",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			// There are other animals with the same name
			String rmAnimalKennelName;
			// Hold the kennel name of the animal we want to remove
			do {
				rmAnimalKennelName = JOptionPane.showInputDialog(
						"Kennel name: ", "Enter Kennel name");
				// Ask the unique kennel name of the animal
				if (rmAnimalKennelName == null) {
					// If user close or cancel the dialog box return to main
					// menu
					return;
				}
				for (Animal a : animalName) {

					if (a.getKennel().getKennelname()
							.equals(rmAnimalKennelName)
							&& a.getName().toUpperCase().equals(who)) {
						/*
						 * Comparing kennel name with the kennel name of the all
						 * the animal that are inside the centre
						 */
						animal.remove(a);
					} else {
						JOptionPane.showMessageDialog(null,
								"Error provided details didn't match", "Error",
								JOptionPane.ERROR_MESSAGE);
						rmAnimalKennelName = "";
						// setting rmAnimalKennelName ("") so we can loop

					}
				}
			} while (rmAnimalKennelName.equals(""));

		}
	}

	/**
	 * Returns an array of animals in the centre
	 * 
	 * @return result An array of all the animals residing in the centre
	 */

	public Animal[] obtainAllanimal() {
		Animal[] result = new Animal[animal.size()];
		result = animal.toArray(result);
		return result;
	}

	/**
	 * Only returns those dogs who like bones
	 * 
	 * @return result An ArrayList of dogs. If no dogs like bones then returns
	 *         an empty array (size 0)
	 */
	public ArrayList<Dog> obtainDogsWhoLikeBones() {
		ArrayList<Dog> result = new ArrayList<Dog>();
		for (Animal a : animal) {
			if (a instanceof Dog) {
				// Only get object create from Dog class
				Dog dog = (Dog) a;
				if (dog.isLikesBones()) {
					result.add(dog);
				}
			}
		}

		return result;
	}

	/**
	 * Only returns those Cats that share run
	 * 
	 * @return result an ArrayList of Animals (Cats) that share runs. if there
	 *         is no cats that like to share runs return empty ArrayList (size
	 *         0)
	 */
	public ArrayList<Animal> obtainCatsWhoShareRuns() {
		ArrayList<Animal> result = new ArrayList<Animal>();
		for (Animal a : animal) {
			if (a instanceof Cat) {
				// Only get object create from Cat class
				if (a.getanimalMovement()) {
					result.add(a);
				}
			}
		}
		return result;
	}

	/**
	 * Only returns those bird that can fly
	 * 
	 * @return result an ArrayList of Animals (Bird) that can fly if there is no
	 *         cats that like to share runs return empty ArrayList (size 0)
	 */
	public ArrayList<Animal> obtainBirdsThatFly() {
		ArrayList<Animal> result = new ArrayList<Animal>();
		for (Animal a : animal) {
			if (a instanceof Bird) {
				// Only get object create from Bird class
				if (a.getanimalMovement()) {
					result.add(a);
				}

			}
		}
		return result;
	}

	/**
	 * search animals with their name
	 * 
	 * @param name
	 * @return animals with the user input names
	 */
	public ArrayList<Animal> search(String name) {

		ArrayList<Animal> result = new ArrayList<Animal>();
		for (Animal a : animal) {
			if (name.toUpperCase().equals(a.getName().toUpperCase())) {
				result.add(a);
			}
		}
		return result;
	}

	/**
	 * Get the name of the centre that is housing the animals
	 * 
	 * @return centreName
	 */

	public String getCentreName() {
		return centreName;
	}

	/**
	 * Set the name of the animal centre that is housing the animals
	 * 
	 * @param centreName
	 *            Animal centre name
	 */
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	/**
	 * Print the detail information about AnimaCentre
	 * 
	 * @return String showing all the information in the kennel
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Data in ").append(centreName).append(":")
				.append("\nAnimal capped: ").append(getAnimalCentreCapacity())
				.append("\nNumber of animals in the animal centre:")
				.append(getNumofAnimal() + "\n");
		for (Animal a : animal) {
			sb.append("\n").append(a);
		}
		return sb.toString();
	}

}
