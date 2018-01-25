import java.util.Date;

public class Person {

	private Person nextPerson;
	private String name;
	private String surname;
	private int passport;
	private int id;
	private Date arrival;
	private static int counter = 0;
	private int priority;

	// Default Constructor. Setting all the attributes of the person
	public Person(String name, String surname, int passport, int priority) {
		
		this.id = counter;
		counter++;
		this.arrival = new Date();
		this.nextPerson = null;
		this.name = name;
		this.surname = surname;		
		this.passport = passport;
		this.priority = priority;
	}
	
	// Setters and getters
	public void setNextPerson(Person nextPerson) {
		this.nextPerson = nextPerson;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public void setPassport(int passport) {
		this.passport = passport;		
	}
	
	public void setPriority (int priority) {
		this.priority = priority;
	}
	
	public Person getNextPerson() {
		return nextPerson;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getArrival() {
		return arrival;
	}
	
	public int getPassport() {
		return passport;
	}
	
	public int getPriority() {
		return priority;
	}
	
}
