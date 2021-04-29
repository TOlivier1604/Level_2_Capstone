package ProjectPoised;

public class PoisedPerson {

	// Attribute
	String personName;
	long personNumber;
	String personEmail;
	String personAddress;

	/**
	 * Constructor method
	 * 
	 * @param personName
	 * @param telNumber
	 * @param emailAddress
	 * @param personAddress
	 **/
	public PoisedPerson(String personName, long personNumber, String personEmail, String personAddress) {

		this.personName = personName;
		this.personNumber = personNumber;
		this.personEmail = personEmail;
		this.personAddress = personAddress;
	}

	/**
	 * method to get person details
	 * 
	 * @return person details
	 **/
	public String poisedPerson() {
		String output = personName + ", ";
		output += personNumber + ", ";
		output += personEmail + ", ";
		output += personAddress + ", ";

		return output;
	}

}
