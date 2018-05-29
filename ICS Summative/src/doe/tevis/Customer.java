package doe.tevis;

public class Customer {
	// Strings, long, and int to hold customer data.
	private String fName;
	private String lName;
	private String city;
	private String pCode;
	private long ccn;
	private int id;

	/**
	 * Constructor method for the Customer object.
	 * 
	 * @param first
	 *            First name of the customer.
	 * @param last
	 *            Last name of the customer.
	 * @param place
	 *            City of the customer.
	 * @param postal
	 *            Postal code of the customer.
	 * @param credit
	 *            Credit card number of the customer.
	 * @param num
	 *            id of the customer.
	 */
	public Customer(String first, String last, String place, String postal, long credit, int num) {

		fName = first;
		lName = last;
		city = place;
		pCode = postal;
		ccn = credit;
		id = num;

	}

	/**
	 * Accessor method to get the first name and the last name of the customer.
	 * 
	 * @return The first name and the last name with a space in between.
	 */
	public String getName() {

		return fName + " " + lName;

	}

	/**
	 * Accessor method to get the city of the customer.
	 * 
	 * @return The city of the customer.
	 */
	public String getCity() {

		return city;

	}

	/**
	 * Accessor method for the postal code of the customer.
	 * 
	 * @return The postal code of the customer.
	 */
	public String getCode() {

		return pCode;

	}

	/**
	 * Accessor method for the credit card number of the customer.
	 * 
	 * @return The credit card number of the customer.
	 */
	public long getCredit() {

		return ccn;

	}

	/**
	 * Accessor method for the unique id of the customer.
	 * 
	 * @return The id of the customer.
	 */
	public int getID() {

		return id;

	}

}
