import javax.swing.JOptionPane;

public class DogsRUsApplication {

	public static void main(String[] args) {
		// Greeting dialog box
		JOptionPane.showMessageDialog(null, "**********HELLO***********",
				"Starting DogsRUsApplication", JOptionPane.ERROR_MESSAGE);
		AnimalCentreDemo demo = new AnimalCentreDemo();
		demo.initialise();
		// Initialise animals that are in the file every time it program run
		demo.runMenu();
		// Menu option
	
	}

}
