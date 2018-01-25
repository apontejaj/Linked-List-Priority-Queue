import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main { // CLI to implement the program
	
	MyLinkedList inmigrationList;
	String name;
	String surname;
	int passport;
	int priority;

	public static void main(String[] args) {
		
		new Main();

	}
	
	// Default constructor. Setting list.
	public Main() {
		inmigrationList = new MyLinkedList();
		menu();
		
	}
	
	// Displaying the menu and performing depending on user's selection
	public void menu() {
		
		boolean stillHere = true;
		
		do { // Infinite loop to go back to the menu after every transaction
			System.out.println("Welcome to Immigration");
			System.out.println("What do you want to do?");
			System.out.println("1) Add a new person to the list"); // Done
			System.out.println("2) Remove first person from the list"); // Done
			System.out.println("3) Check position of a person"); // Done
			System.out.println("4) Add a new person to the list in a particular position"); // Done
			System.out.println("5) Remove a person by id"); //Done
			System.out.println("6) Cut off the list");//Done
			System.out.println("7) Update information on one person");
			System.out.println("8) Print the whole list");
			System.out.println("9) Close the program");
			
			String optionSelected = askingUser();
			
			if (optionSelected.equals("1")) {
				addingPerson();
			}
			else if (optionSelected.equals("2")) {
				inmigrationList.remove();
			}
			else if (optionSelected.equals("3")) {
				checkPosition();
			}
			else if (optionSelected.equals("4")) {
				addingPersonInPosition();
			}
			else if (optionSelected.equals("5")) {
				removingPerson();
			}
			else if (optionSelected.equals("6")) {
				cuttingOff();
			}
			else if (optionSelected.equals("7")) {
				updatingPerson(); 
			}
			else if (optionSelected.equals("8")) {
				System.out.println(inmigrationList.FullList());
			}
			else if (optionSelected.equals("9")) {
				stillHere = false;
			}
			else {
				System.out.println("Please select a valid option"); // Validation
			}
			
		} while (stillHere);
		
	}
	
	// Method to allow user to type answers onto the keyboard
	public String askingUser() {
		
		String answer = "";
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		try {
			answer = br.readLine();
			
		} catch (Exception e) {}
		
		return answer;
	}
	
	// Adding a new person to the list
	public void addingPerson() {
		collectingData();		
		int newId = inmigrationList.add(new Person(name, surname, passport, priority));
		System.out.println("The ID of the new person is " + newId);
		
	}
	
	// Checking the position of a person given their id
	public void checkPosition() {
		System.out.println("What is the id of the new person?");
		
		int id = askingInt();
		System.out.println("The person with the ID " + id + " is in position " + inmigrationList.find(id));
		
	}
	
	// Adding a person in a particular position
	public void addingPersonInPosition() {
		collectingData();
		
		System.out.println("What position do you want to put the person on?");
		
		int position = askingInt();
		
		try {
			int newId = inmigrationList.insert(new Person(name, surname, passport, priority), position);
			System.out.println("The ID of the new person is " + newId);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// Removing a person given their id
	public void removingPerson() {
		System.out.println("What is the id of the person to remove?");
		
		int id = askingInt();
		try {
			inmigrationList.removeAt(inmigrationList.find(id));
			System.out.println("The person has been removed");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// Cutting off the list by a given number of people
	public void cuttingOff() {
		System.out.println("How many of last people do you want to remove?");

		int quantity = askingInt();
		inmigrationList.removeManyLast(quantity);
	}
	
	// updating a particular person
	public void updatingPerson() {
		System.out.println("What is the id of the person to update?");
		Person updatedPerson = null;
		
		do {
			int id = askingInt();
			updatedPerson = inmigrationList.getById(id);
			if (updatedPerson == null) {
				System.out.println("There is no user with that id. Try again");
			}
		} while (updatedPerson == null);
				
		int position = inmigrationList.getPosition(updatedPerson);
		int originalPriority = updatedPerson.getPriority();
		
		collectingData();
		updatedPerson.setName(name);
		updatedPerson.setSurname(surname);
		updatedPerson.setPassport(passport);
		updatedPerson.setPriority(priority);
		
		if (originalPriority != updatedPerson.getPriority() ) {
			inmigrationList.removeAt(position);
			inmigrationList.add(updatedPerson);
		}
		
	}
	
	// Asking the user the information regarding the new person.
	public void collectingData() {
		System.out.println("What is the name of the person?");
		name = askingUser();
		System.out.println("What is the surname of the person?");
		surname = askingUser();
		System.out.println("What is the passport number of the person?");
		passport = askingInt();
		
		System.out.println("What's the priority for the new person?");
		System.out.println("1) Top priority - People with children under 1 year");
		System.out.println("2) Medium priority - Peeple with disability");
		System.out.println("3) Nomal priority - General Public");
		
		do {
			
			priority = askingInt();
			if (priority > 3 || priority < 1) {
				System.out.println("We need a value between 1 and 3. Try again");
			}
			
		} while (priority > 3 || priority < 1);
		
	}
	
	// Validating a value typed in by the user is an integer
	public int askingInt() {
		
		String value = "";
		do {
			value = askingUser();
			
			if(!value.matches("[0-9]+")) {
				System.out.println("We need a value that is only numbers. Try again");
			}
			
		}while(!value.matches("[0-9]+"));
		
		return Integer.parseInt(value);
	}

}
