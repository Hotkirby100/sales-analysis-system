package doe.tevis;

/**
 * Class to hold postal codes in order to compare later.
 * 
 * @author Tevis Doe
 *
 */
public class PostalCode {
	// Strings and doubles to hold data.
	private String pCode;
	private String place;
	private String city;
	private double latitude;
	private double longitude;

	/**
	 * Constructor method for the PostalCode object.
	 * 
	 * @param c
	 *            Postal code.
	 * @param p
	 *            Place.
	 * @param ci
	 *            City.
	 * @param la
	 *            Latitude.
	 * @param lo
	 *            Longitude.
	 */
	public PostalCode(String c, String p, String ci, double la, double lo) {

		pCode = c;
		place = p;
		city = ci;
		latitude = la;
		longitude = lo;

	}

	/**
	 * Accessor method for the postal code.
	 * 
	 * @return The postal code.
	 */
	public String getCode() {

		return pCode;

	}

	/**
	 * Method to compare the inputed string to the code held by the object.
	 * 
	 * @param nCode
	 *            The inputed string to compare to.
	 * @return True or false depending on whether the inputed string matches the
	 *         postal code.
	 */
	public boolean validateCode(String nCode) {

		if (nCode.equals(pCode)) {

			return true;

		}

		return false;

	}

}
