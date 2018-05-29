package doe.tevis;

/**
 * Class to hold sales data from a file.
 * 
 * @author Tevis Doe
 *
 */
public class Sale {
	// Long to hold the number of sales, String to hold a postal code.
	private long numSale;
	private String pCode;

	/**
	 * Constructor method for the Sale object.
	 * 
	 * @param code
	 *            Postal code of the sale location.
	 * @param s
	 *            Number of sales.
	 */
	public Sale(String code, long s) {

		pCode = code;
		numSale = s;

	}
	/**
	 * Accessor method for the number of sales.
	 * @return The number of sales.
	 */
	public long getSales() {

		return numSale;

	}
	/**
	 * Get the first digit of the number of sales, required for Benford's law.
	 * @return The first digit of the number of sales.
	 */
	public int firstDigit() {

		return Integer.parseInt(Long.toString(numSale).substring(0, 1));

	}

}
