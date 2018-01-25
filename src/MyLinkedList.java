
public class MyLinkedList {
	private Person first;
	private Person last;
	private int peopleCount;

	// The default constructor initializes the empty list.
	// References to first and last person are null
	// People count is zero
	public MyLinkedList() {
		first = null;
		last = null;
		peopleCount = 0;
	}

	// This method inserts people on the list evaluating their priority
	public int add(Person person) {
		
		if(first == null) { // If the list is empty, add the person in the last position.
			addLast(person);
		}
		else { // If it is not empty, evaluate the priority
			
			int position = 1;
			Person currentPerson = first;
			boolean positionFound = false;
			
			if (person.getPriority() == 1) { // First case, person is top priority
				
				do {
					
					if (currentPerson.getPriority() == 1) { // If the person in the current position is top priority, go to next person
						
						if(currentPerson.getNextPerson() == null) { // If next person is null, it means we are at the end of the list
							addLast(person); 
							positionFound = true;
						}
						else{ // In other case, move to the next position
							currentPerson = currentPerson.getNextPerson();
							position += 1;
						}
						
					}
					else { // If the person in the current position is not top priority, insert the new person in this position
						insert(person, position);
						positionFound = true;
					}
					
				} while (!positionFound);
				
			}
			else if (person.getPriority() == 2) { // Second case, person is medium priority
				
				do {
					
					if (currentPerson.getPriority() == 1 || currentPerson.getPriority() == 2) { // If the person in the current position
																								// is top or medium priority,
																								// go to the next position
						if(currentPerson.getNextPerson() == null) { // If the next person is null, it means we are at the end of the list
							addLast(person);
							positionFound = true;
						}
						else{ // In other case, move to the next position
							currentPerson = currentPerson.getNextPerson();
							position += 1;
						}
						
					}
					else { // If the person in the current position is not top or medium priority, insert in this position
						insert(person, position);
						positionFound = true;
					}
					
				} while (!positionFound);
				
			}
			else if (person.getPriority() == 3) { // Third case, person is low priority
				addLast(person); // Add at the end of the list
				
			}
			
		}
		return person.getId();
		
	}
	
	// Adding one person at the back of the list
	// If first position is null, it means the list is empty and we need to set the first and the last person to the person
	public int addLast(Person person) {
		if(first == null) {
			first = person;
			last = first;
		}
		else {
			last.setNextPerson(person);
			last = person;			
		}
		peopleCount++;
		return person.getId();
	}
	
	// Adding person at the front of the list
	// If first position is null, it means the list is empty and we need to set the first and the last person to the person
	public int addFirst(Person person) {
		if(first == null) {
			first = person;
			last = first;
		}
		else {
			person.setNextPerson(first);
			first = person;
		}
		peopleCount++;
		return person.getId();
	}
	
	// Adding person in a particular position
	public int insert(Person person, int position) {
		if(position > size() + 1 ) { // Position bigger than size + 1 is not allowed
			throw new IllegalStateException("The queue is smaller than the position you are trying to insert at: " + position);
		}
		
		else if (position <= 0) { // Position 0 or lower are not allowed
			throw new IllegalStateException("You can't insert in anyone in a position lower than 1");
		}
		
		else if (position == size() + 1) { // If position is size + 1, put person at the end of the list
			addLast(person);
		}
		
		else if (position == 1) { // If position is 1, put that person first in the list
			addFirst(person);
		}
		else {	// To add someone in the nth position, we  stand on the (n-1)th position
			Person currentPerson = first;

			for(int i = 2; i < position; i++) {
				currentPerson = currentPerson.getNextPerson();
			}

			Person nextPerson = currentPerson.getNextPerson();
			currentPerson.setNextPerson(person);
			person.setNextPerson(nextPerson);
			peopleCount++;
		}
		return person.getId();
		
	}

	// Removing person at particular position
	public Person removeAt(int position) {
		if(first == null) { // If the list is empty, there is no one to remove
			throw new IllegalStateException("The LinkedList is empty and there are no items to remove");
		}
		
		else if (position <= 0) { // Position 0 or lower are not allowed
			throw new IllegalStateException("You can't delete a position lower than 1");
		}
		
		else if(position > size() ) { // Position bigger than size is not allowed
			throw new IllegalStateException("The queue is smaller than the position you are trying to insert at: " + position);
		}
		
		else if (position == 1) { // If position is 1, remove first
			Person toDelete = first;
			first = first.getNextPerson();
			peopleCount--;
			return toDelete;
			
		}
		else { // To delete the person in the nth position, we stand in that position and modify the reference of the previous position
			Person currentPerson = first;
			Person prevPerson = first;

			for(int i = 2; i <= position; i++) {
				prevPerson = currentPerson;
				currentPerson = currentPerson.getNextPerson();
			}

			prevPerson.setNextPerson(currentPerson.getNextPerson());
			peopleCount--;
			return currentPerson;
			
		}
		
	}

	// Removing the person in the first position
	public Person remove() {
		if(first == null) { // If the list is empty, there is no one to remove
			throw new IllegalStateException("The LinkedList is empty and there are no items to remove");
		}
		
		Person toRemove = first;
		
		if (peopleCount == 1) {	
			first = null;
			last = null;
			peopleCount--;
		}
		else {
			first = first.getNextPerson();
			peopleCount--;
		}

		return toRemove;
	}

	// Returns the person in a given position
	public Person get(int position) {
		if(first == null) { // If the list is empty, there in no one to return
			throw new IllegalStateException("The LinkedList is empty and there are no items to get");
		}
		else if (position <= 0) { // Position 0 or lower are not allowed
			throw new IllegalStateException("You can't delete a position lower than 1");
		}
		
		else if(position > size() ) { // Position bigger than size is not allowed
			throw new IllegalStateException("The queue is smaller than the position you are trying to insert at: " + position);
		}
		
		Person currentPerson = first;
		for(int i = 1; i <= size() && currentPerson != null; i++) {
			if(i == position) {
				return currentPerson;
			}

			currentPerson = currentPerson.getNextPerson();
		}

		//if we didn't find it then return null
		return null;
	}

	// Returning the position of a person given their id number
	public int find(int id) {
		if(first == null) {
			throw new IllegalStateException("The LinkedList is empty and there are no items to find");
		}

		Person currentPerson = first;
		for(int i = 1; i <= size(); i++) {
			if(currentPerson.getId() == id) {
				return i;
			}

			currentPerson = currentPerson.getNextPerson();
		}

		//if we didn't find it, then return -1
		return -1;
	}

	// Returning a string to print the whole list
	public String FullList() {
		if(first == null) {
			return "The list is empty";
		}
		else {
			int i = 1;
			String fullList = "";
			Person currentPerson = first;
			do {
				fullList += "Position: " + i + "\n";
				fullList += "ID: " + currentPerson.getId() + "\n";
				fullList += "Arrival: " + currentPerson.getArrival() + "\n";
				fullList += "Full Name: " + currentPerson.getName() +" "+ currentPerson.getSurname() + "\n";
				fullList += "Passport: " + currentPerson.getPassport() + "\n";
				fullList += "Priority: " + currentPerson.getPriority() + "\n \n";
				
				currentPerson = currentPerson.getNextPerson();
				i++;
			} while (i <= size());
			return fullList;
		}
		
	}
	
	// Remove certain amount of people from the end of the list
	public void removeManyLast(int quantity) {
		if(quantity > peopleCount) {
			throw new IllegalStateException("There is less than "+quantity+" elements.");
		}
		for (int i = 0; i < quantity; i++) {
			removeLast();
		}
	}
	
	// Remove last person of the list
	private void removeLast() {
		
		int beforLastPersonPosition = peopleCount - 1;
		boolean isPositionLast = beforLastPersonPosition >= 0 && beforLastPersonPosition < peopleCount;
		if(!isPositionLast) {
			throw new IllegalStateException("The LinkedList is empty and there are no items to remove");
		}
		
		if (peopleCount == 1) {
			remove();
		} else {
			Person beforeLast = get(beforLastPersonPosition);
			beforeLast.setNextPerson(null);
			last = beforeLast;
			peopleCount--;
		} 
	}
	
	// Returns a person given their id
	public Person getById(int id) {
		Person current = first;
		while (current != null) {
			if (current.getId() == id) {
				return current;
			}
			current = current.getNextPerson();
		}
		return null;
	}
	
	// Returns the position of a person
	public int getPosition(Person person) {
		int position = 1;
		Person current = first;
		while (current != null) {
			if (current.equals(person)) {
				return position;
			}
			current = current.getNextPerson();
			position++;
		}
		return -1;
	}
	
	// Returns last person on the list
	public Person getLast() {
		return last;
	}

	// Returns number of people on the list
	public int size(){
		return peopleCount;
	}
	
	// Returns first person on the list
	public Person getFirst() {
		return first;
	}
	
}
