import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JCheckBox;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
//allows to scroll in the message box
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class runs a Kennel
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.1 (16th March 2015)
 */
public class AnimalCentreDemo {
	private String filename; // holds the name of the file
	private AnimalCentre animalCentre; // holds the kennel

	/*
	 * Notice how we can make this private, since we only call from main which
	 * is in this class. We don't want this class to be used by any other class.
	 */

	public AnimalCentreDemo() {

		do {
			filename = JOptionPane.showInputDialog(
					"Please enter the filename of kennel information: ",
					"Filename");
			if (filename == null) {
				int reply = JOptionPane.showConfirmDialog(null,
						"Canceling will exit the program!", "Quit",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					// user want to exit the program
					System.exit(0);
				}
			}

		} while (filename == null || filename.isEmpty());
		// until filename is empty or null loop continuously

		animalCentre = new AnimalCentre();
	}

	/*
	 * initialise() method runs from the main and reads from a file Intitalise
	 * all the animal reading from the file the user input
	 */
	protected void initialise() {
		Animal animal = null;

		JOptionPane.showMessageDialog(null, "Using file " + filename, filename,
				JOptionPane.INFORMATION_MESSAGE);

		// Using try-with-resource (see my slides from session 15)
		try (FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				Scanner infile = new Scanner(br)) {
			String centreName = infile.nextLine();
			int kennelSize = infile.nextInt();
			infile.nextLine();
			animalCentre.setAnimalCentreCapacity(kennelSize);
			int numAnimal = infile.nextInt();
			infile.nextLine();
			animalCentre.setCentreName(centreName);
			for (int i = 0; i < numAnimal; i++) {

				String type = infile.nextLine();

				String kennelName = infile.nextLine();
				Kennel kennels = new Kennel(kennelName);

				String animalName = infile.nextLine();

				int numOwners = infile.nextInt();
				infile.nextLine();
				ArrayList<Owner> owners = new ArrayList<>();
				for (int oCount = 0; oCount < numOwners; oCount++) {
					String ownerName = infile.nextLine();
					String phone = infile.nextLine();
					Owner owner = new Owner(ownerName, phone);
					owners.add(owner);
				}

				String favFood = infile.nextLine();

				int feedsPerDay = infile.nextInt();
				infile.nextLine();

				boolean movement = infile.nextBoolean();
				infile.nextLine();

				switch (type) {
				case "DOG":
					animal = new Dog();
					boolean likesBones = infile.nextBoolean();
					infile.nextLine();

					// animal.animalSpecific(likesBones);
					animal.setType(type);
					animal.setOwner(owners);
					// animal.
					animal.setKennel(kennels);
					animal.setName(animalName);
					animal.setPreferFood(favFood);
					animal.setFoodPerDay(feedsPerDay);
					animal.setAnimalMovement(movement);
					((Dog) animal).setLikesBones(likesBones);
					break;

				case "CAT":
					animal = new Cat();
					animal.setType(type);
					animal.setOwner(owners);
					animal.setKennel(kennels);
					animal.setName(animalName);
					animal.setPreferFood(favFood);
					animal.setFoodPerDay(feedsPerDay);
					animal.setAnimalMovement(movement);
					break;
				case "BIRD":
					animal = new Bird();
					animal.setType(type);
					animal.setOwner(owners);
					animal.setKennel(kennels);
					animal.setName(animalName);
					animal.setPreferFood(favFood);
					animal.setFoodPerDay(feedsPerDay);
					animal.setAnimalMovement(movement);
					break;
				default:
					System.out.println("non such type!");

				}

				animalCentre.addAnimal(animal);
			}

		} catch (FileNotFoundException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("The file: ")
					.append(filename)
					.append("\n" + e.getMessage())
					.append("\nDoes not exist.\nAssuming first use and an empty file.")
					.append("\nIf this is not the first use then have you accidentally deleted the file?");
			JOptionPane.showMessageDialog(null, sb.toString(),
					"Error \"FileNotFoundException\"",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "IOException",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	/*
	 * runMenu() method runs from the main and allows entry of data etc
	 */
	protected void runMenu() {
		Object selectedValue;

		do {

			do {
				// Getting animal centre name to use it in the main menu
				String animalCentreName = animalCentre.getCentreName();
				if (animalCentreName == null || animalCentreName.equals("null")) {
					animalCentreName = "Animal Centre";

				}
				final Object[] possibleValues = { 1, 2, 3, 4, 5, 6, 7, 8, 9,
						10, 11 };
				// Option the user can choose from
				selectedValue = JOptionPane.showInputDialog(null, printMenu(),
						animalCentreName, JOptionPane.INFORMATION_MESSAGE,
						null, possibleValues, possibleValues[0]);
				if (selectedValue == null) {
					// if user choose to close or cancel the dialog box
					int save = JOptionPane
							.showConfirmDialog(null, "Save the data?", "Save?",
									JOptionPane.YES_NO_OPTION);
					// Prompt user to whether save the data
					if (save == JOptionPane.YES_OPTION) {
						// if yes save data in a file
						saveData();
					}
					int close = JOptionPane.showConfirmDialog(null,
							"Really Quit ?", "Quit", JOptionPane.YES_NO_OPTION);
					// Prompt user to ask if they want to quit
					if (close == JOptionPane.YES_OPTION) {
						// if yes then quit
						JOptionPane.showMessageDialog(null,
								"**********Good Bye***********", "Closing down DogRusApplication",
								JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
						// Shut down the program
					}
				}

			} while (selectedValue == null);

			switch ((int) selectedValue) {
			// casting selecetedValue to int
			case 1:
				//looking for the space
				Animal animal = null;
				admitAnimal(animal);
				break;
			case 2:
				printBirdThatFly();
				break;
			case 3:
				printCatShareRun();
				break;

			case 4:
				printDogsWithBones();
				break;
			case 5:
				searchForAnimal();
				break;
			case 6:
				removeAnimal();
				break;
			case 7:
				printallAnimal();
				break;
			case 8:
				setAnimalCapacity();

				break;
			case 9:
				changeKennelName();
				break;
			case 10:
				changeCentreName();
				break;
			case 11:
				saveData();
				break;
			}

		} while (true);
		// Run continuously
	}

	/*
	 * Setting animal capacity of the animal centre
	 */
	private void setAnimalCapacity() {

		int max = 0;
		boolean success = true;
		do {

			try {
				do {
					max = Integer.parseInt(JOptionPane.showInputDialog(
							"Enter the capacity!\nPrevious Capacity: "
									+ animalCentre.getAnimalCentreCapacity()
									+ "\nNumber of animal in the Centre: "
									+ animalCentre.getNumofAnimal(),
							"Must input Number"));

				} while (max < 1);
				// if user enter 0 it will provide the error to the user
				// must be positive number
				success = false;
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null,
						"Not an Integer\n" + e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
				if (e.getMessage().equals("null")) {
					// if user choose to close or cancel the dialog box
					int reply = JOptionPane.showConfirmDialog(null,
							"Quitting?", "Quit", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						// user want to exit the program
						return;
					}
				}
			}
		} while (success);

		animalCentre.setAnimalCentreCapacity(max);
	}

	/*
	 * Print all dogs that likes bone
	 */
	private void printDogsWithBones() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Dog> dogsWithBones = animalCentre.obtainDogsWhoLikeBones();
		if (dogsWithBones.isEmpty()) {
			JOptionPane.showMessageDialog(null, "None dog likes bones",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
		} else {
			for (Animal a : dogsWithBones) {
				sb.append(a);
			}
			JTextArea dogLikesBones = new JTextArea(sb.toString());
			JScrollPane scrollPane = new JScrollPane(dogLikesBones);
			dogLikesBones.setLineWrap(true);
			dogLikesBones.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(null, scrollPane,
					"Printing dogs that like bones",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	/*
	 * Printing all Bird that fly
	 */
	private void printBirdThatFly() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Animal> birdsThatFly = animalCentre.obtainBirdsThatFly();
		if (birdsThatFly.isEmpty()) {
			JOptionPane.showMessageDialog(null, "None bird flies",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
		} else {
			for (Animal a : birdsThatFly) {
				sb.append(a);
			}
			JTextArea dogLikesBones = new JTextArea(sb.toString());
			JScrollPane scrollPane = new JScrollPane(dogLikesBones);
			dogLikesBones.setLineWrap(true);
			dogLikesBones.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(null, scrollPane,
					"Printing bird that flies",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	/*
	 * Printing cat that share runs
	 */
	private void printCatShareRun() {
		StringBuilder sb = new StringBuilder();
		ArrayList<Animal> animalShareRuns = animalCentre.obtainCatsWhoShareRuns();
		if (animalShareRuns.isEmpty()) {
			JOptionPane.showMessageDialog(null, "None cat wants to share run",
					"INFORMATION", JOptionPane.INFORMATION_MESSAGE);
		} else {
			for (Animal a : animalShareRuns) {
				sb.append(a);
			}
			JTextArea catShareRuns = new JTextArea(sb.toString());
			JScrollPane scrollPane = new JScrollPane(catShareRuns);
			catShareRuns.setLineWrap(true);
			catShareRuns.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(null, scrollPane,
					"Printing Cat that share runs",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/*
	 * Printing all the animals that is/are in the centre
	 */
	private void printallAnimal() {
		JTextArea printall = new JTextArea(animalCentre.toString());
		JScrollPane scrollPane = new JScrollPane(printall);
		// Using JScrollPane to scroll and view all the animal
		printall.setLineWrap(true);
		printall.setWrapStyleWord(true);
		scrollPane.setPreferredSize(new Dimension(800, 500));
		JOptionPane.showMessageDialog(null, scrollPane,
				"Printing all animals inside kennel",
				JOptionPane.INFORMATION_MESSAGE);

	}

	/*
	 * Saving data in file
	 */
	private void saveData() {
		try (FileWriter fw = new FileWriter(filename);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter outfile = new PrintWriter(bw);) {
			outfile.println(animalCentre.getCentreName());
			outfile.println(animalCentre.getAnimalCentreCapacity());
			outfile.println(animalCentre.getNumofAnimal());
			Animal[] animal = animalCentre.obtainAllanimal();
			/*
			 * Obtaining all the animal and saving all the details about animal
			 * in file
			 */
			for (Animal a : animal) {
				outfile.println(a.getType());
				outfile.println(a.getKennel().getKennelname());
				outfile.println(a.getName());
				Owner[] owners = a.getOwner();
				outfile.println(owners.length);
				for (Owner o : owners) {
					outfile.println(o.getName());
					outfile.println(o.getPhone());
				}
				outfile.println(a.getPreferFood());
				outfile.println(a.getFoodPerDay());
				outfile.println(a.getanimalMovement());
				if (a.getType().equals("DOG")) {
					outfile.println(((Dog) a).isLikesBones());
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Problem when trying to write to file: " + filename + "\n"
							+ e.getMessage(), "alert",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	/*
	 * Removing animal from the Animal Centre
	 */
	private void removeAnimal() {
		String animaltoberemoved;
		// Getting name of the animal that needs to removed
		animaltoberemoved = JOptionPane.showInputDialog(null,
				"Enter the name of the animal you want to remove",
				"Animal name here");
		if (animaltoberemoved == null) {
			// if user chose close or cancel, return to main menu
			return;
		}

		animalCentre.removeAnimal(animaltoberemoved);

	}

	/*
	 * Searching animal that are in the centre
	 */
	private void searchForAnimal() {
		StringBuilder sb = new StringBuilder("");
		final Object[] possibleValues = { "CAT", "DOG", "BIRD", "ALL" };
		Object selectedValue = JOptionPane.showInputDialog(null,
				"\"Cat\", \"Dog\", \"Bird\"", "Searcing Animal",
				JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
				possibleValues[0]);
		if (selectedValue == null) {
			// if user chose close or cancel, return to main menu
			return;
		}

		String animalName = JOptionPane
				.showInputDialog("Enter the name of the animal you want to search: ");
		if (animalName == null) {
			// if user chose close or cancel, return to main menu
			return;
		}

		ArrayList<Animal> animal = animalCentre.search(animalName);
		switch (selectedValue.toString()) {
		case "CAT":
			// Searching for animal CAT
			Iterator<Animal> catIterator = animal.iterator();
			while (catIterator.hasNext()) {
				Animal animals = catIterator.next();
				if (animals instanceof Cat) {
					Cat cat = (Cat) animals;
					if (cat.getName().toUpperCase()
							.equals(animalName.toUpperCase())) {
						sb.append(cat);
					}
				}
			}
			break;
		case "DOG":
			// Searching for animal DOG
			Iterator<Animal> dogIterator = animal.iterator();
			while (dogIterator.hasNext()) {
				Animal animals = dogIterator.next();
				if (animals instanceof Dog) {
					Dog dog = (Dog) animals;
					if (dog.getName().toUpperCase()
							.equals(animalName.toUpperCase())) {
						sb.append(dog);
					}
				}
			}
			break;
		case "Bird":
			// Searching for animal BIRD
			Iterator<Animal> birdIterator = animal.iterator();
			while (birdIterator.hasNext()) {
				Animal animals = birdIterator.next();
				if (animals instanceof Bird) {
					Bird bird = (Bird) animals;
					if (bird.getName().toUpperCase()
							.equals(animalName.toUpperCase())) {
						sb.append(bird);
					}
				}
			}
			break;
		case "ALL":
			for (Animal a : animal) {
				sb.append(a);
			}
			break;
		}

		if (!sb.toString().equals("") && sb != null) {
			JTextArea searchAnimal = new JTextArea(sb.toString());
			JScrollPane scrollPane = new JScrollPane(searchAnimal);
			searchAnimal.setLineWrap(true);
			searchAnimal.setWrapStyleWord(true);
			scrollPane.setPreferredSize(new Dimension(500, 500));
			JOptionPane.showMessageDialog(null, scrollPane, "Searched results",
					JOptionPane.INFORMATION_MESSAGE);

		} else {

			JOptionPane.showMessageDialog(null, "Sorry no such animal named : "
					+ animalName + " found!", "Alert",
					JOptionPane.ERROR_MESSAGE);

		}

	}

	/*
	 * change the name of the Animal centre
	 */
	private void changeCentreName() {
		String centreName;
		// Naming Animal centre

		do {
			centreName = JOptionPane.showInputDialog("Enter new centre name ",
					"Centre name here");

			if (centreName == null) {
				// if user choose close or cancel, return to main menu
				return;
			} else {
				animalCentre.setCentreName(centreName);
				// Setting centre name
			}

		} while (centreName.isEmpty());

	}

	/*
	 * Change the name of the kennels that animals are residing
	 */
	private void changeKennelName() {
		String name;
		//kennel name
		do{
	
		 name = JOptionPane.showInputDialog(
				"Enter the new name for the kennel", "Kennel name here");
		if (name == null) {
			int reply = JOptionPane.showConfirmDialog(null,
					"quit?", "Quit",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				return;
			}
		}
		}
		while (name == null || name.isEmpty());
		animalCentre.setKennelName(name);
	}

	/*
	 * admit animal in the centre
	 */
	private void admitAnimal(Animal animal) {

		boolean animalMovement = false;
		// animal movement
		String animalType;
		// animal type
		String animalName;
		// cat name
		ArrayList<Owner> owns;
		// save owner's name and phone number
		String favFood;
		// animal's favourite food
		int numTimesEat;
		// animal eat per day
		Kennel kennels;
		// animal kennel

		if(animalCentre.getNumofAnimal() >=animalCentre.getAnimalCentreCapacity()){
			//Looking for space if no space return to main menu
			JOptionPane.showMessageDialog(null,
					"Sorry no space left", "no more room",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		JCheckBox likeBones = new JCheckBox("Like bones?");
		JCheckBox fly = new JCheckBox("Does it fly?");
		int option = 0;
		Object[] possibleValues = { "CAT", "DOG", "BIRD" };
		Object selectedValue = JOptionPane.showInputDialog(null,
				"Pick animal type", "Animal Type",
				JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
				possibleValues[0]);
		if (selectedValue == null) {
			// break out of loop as the user cancel the option menu
			return;
		}

		do {
			// Creating a field where user can input value
			JTextField field1 = new JTextField();
			JTextField field2 = new JTextField();
			JTextField field3 = new JTextField();

			switch (selectedValue.toString()) {
			case "CAT":
				JCheckBox shareRuns = new JCheckBox("Like to share runs?");
				animal = new Cat();
				// creating animal Cat
				Object[] promptForCat = { "Animal Type", selectedValue,
						"Kennel Name:", kennelNameRecommend(), "Cat Name:",
						field1, "Favourite food:", field2,
						"number of times needs to feed:", field3, shareRuns };
				option = JOptionPane.showConfirmDialog(null, promptForCat,
						"Admiting Cat", JOptionPane.OK_CANCEL_OPTION);
				animalMovement = shareRuns.isSelected();
				break;
			case "DOG":
				JCheckBox likeWalks = new JCheckBox("Like to take walk?");
				animal = new Dog();
				// creating animal Dog
				Object[] promptForDog = { "Animal Type", selectedValue,
						"Kennel Name:",kennelNameRecommend(), "Dog Name:",
						field1, "Favourite food:", field2,
						"Number of times needs to feed:", field3, likeWalks,
						likeBones };
				option = JOptionPane.showConfirmDialog(null, promptForDog,
						"Admiting Dog", JOptionPane.OK_CANCEL_OPTION);
				animalMovement = likeWalks.isSelected();
				boolean lb = likeBones.isSelected();
				// Dog like bone?
				((Dog) animal).setLikesBones(lb);
				// casting animal Dog
				break;

			case "BIRD":
				animal = new Bird();
				// creating animal Bird
				Object[] promptForBird = { "Animal Type", selectedValue,
						"Cage Name:", kennelNameRecommend(), "Bird Name:",
						field1, "Favourite food:", field2,
						"Number of times needs to feed:", field3, fly };
				option = JOptionPane.showConfirmDialog(null, promptForBird,
						"Admiting Bird", JOptionPane.OK_CANCEL_OPTION);
				animalMovement = fly.isSelected();
				break;
			}

			if (option == JOptionPane.CANCEL_OPTION) {
				// If user close or cancel the dialog return to main menu
				return;
			}
			animalType = selectedValue.toString();
			kennels = new Kennel(CheckKennelName(kennelNameRecommend()));
			animalName = field1.getText();
			favFood = field2.getText();
			String value4 = field3.getText();
			numTimesEat = EatPerDay(value4);

		} while (animalName.isEmpty() || favFood.isEmpty() || numTimesEat == 0);
		// Setting animal type
		animal.setType(animalType);
		// Setting kennel to animal
		animal.setKennel(kennels);
		// Setting animal name
		animal.setName(animalName);

		// animal.setAnimalMovement(animalMove);

		// Setting owners
		owns = getOwners();
		// Setting owner name
		animal.setOwner(owns);
		// Setting animal's prefer food
		animal.setPreferFood(favFood);
		// Setting animal's prefer food
		animal.setFoodPerDay(numTimesEat);
		// Setting animal movement
		animal.setAnimalMovement(animalMovement);

		// This can be improved
		// (InputMismatchException?)
		animalCentre.addAnimal(animal);

	}

	/*
	 * Getting new kennel Name
	 */
	private String kennelNameRecommend() {
		// ArrayList<String> kennelsNameInCentre = new ArrayList<String>();
		int size = animalCentre.getNumofAnimal();
		// getting the animal size
		size = size + 1;
		String animalsInCentre = Integer.toString(size);
		// parsing int to String
		return animalsInCentre;
		// returning animalsInCentre as the kennel name

	}

	/*
	 * checking whether the name provided is already registered in the animal
	 * centre
	 */
	private String CheckKennelName(String kennelName) {
		ArrayList<String>kennelsNameInCentre = new ArrayList<String>();
		kennelsNameInCentre = animalCentre.getName();
		if (kennelsNameInCentre.contains(kennelName)) 
			/*
			 * Checking whether the kennel name is already registered in the
			 * animal centre. Every kennel should be unique for each animal
			 */
			do{
			kennelName = JOptionPane.showInputDialog(
					"Please enter new name for the kennel\nProvided name already registered: " + kennelName,
					"Must input Kennel Name");
		}while(kennelsNameInCentre.contains(kennelName) || kennelName == null || kennelName.isEmpty());
			// if the name exists then output dialog ox asking different name
		return kennelName;

	}

	/*
	 * Getting Number of times animal need to eat per day
	 */
	private int EatPerDay(String value) {
		int numTimesAmimalEAT;

		try {
			numTimesAmimalEAT = Integer.parseInt(value);
			// parsing string value to int
			if (numTimesAmimalEAT < 1) {
				// animals can't be left hungry and starving (0 times or
				// negative number)
				JOptionPane
						.showMessageDialog(
								null,
								"Enter number more than zero as animal's need to eat \"number of times needs to feed:\" ",
								"\"Error\":Animal needs to eat",
								JOptionPane.INFORMATION_MESSAGE);
				numTimesAmimalEAT = 0;
				// setting the numTimesAmimalEAT to 0, and later compare the
				// value and loop

			}
		} catch (NumberFormatException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Please enter number instead of text in \"number of times needs to feed:\" field",
							"\"Error\":User input text instead of number"
									+ e.getMessage(), JOptionPane.ERROR_MESSAGE);
			numTimesAmimalEAT = 0;
			// setting the numTimesAmimalEAT to 0, and later compare the value
			// and loop
		}
		return numTimesAmimalEAT;

	}

	/*
	 * Getting owner info
	 */
	private ArrayList<Owner> getOwners() {
		ArrayList<Owner> owners = new ArrayList<Owner>();
		String response;
		// response for if there are more owners
		Object selectedValue;
		// boolean answer;
		do {

			String ownName = JOptionPane.showInputDialog("Owner Name:");
			String ownPhone = JOptionPane.showInputDialog("Owner Phone:");
			// above can be left blank as some animal may won't have owners

			Owner own = new Owner(ownName, ownPhone);
			// creating owner
			owners.add(own);
			// Adding owner to the owners ArrayList
			do {
				Object[] possibleValues = { "YES", "N0" };
				selectedValue = JOptionPane.showInputDialog(null,
						"Another owner? (YES/N0)", "Input",
						JOptionPane.INFORMATION_MESSAGE, null, possibleValues,
						possibleValues[0]);
			} while (selectedValue == null);
			response = selectedValue.toString();
			// user needs to choose between "YES" or "NO"
		} while (!response.equals("N0"));
		return owners;
	}

	/*
	 * Provide menu option
	 */
	private String printMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("1  -  Admit new animal ")
				.append("\n2  -  Print bird that fly")
				.append("\n3  -  Print cat that shares runs")
				.append("\n4  -  Print all dogs who like bones")
				.append("\n5  -  Search for Animal ")
				.append("\n6  -  Remove animal")
				.append("\n7  -  Display all the animal")
				.append("\n8  -  Set animal capacity in the centre")
				.append("\n9  -  Set up Kennel name")
				.append("\n10 - Set up centre name").append("\n11 - save");

		return sb.toString();
	}
}
