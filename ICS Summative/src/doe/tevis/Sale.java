package doe.tevis;

public class Sale {

	private long numSale;
	private String pCode;

	public Sale(String code, long s) {

		pCode = code;
		numSale = s;

	}

	public String getCode() {

		return pCode;

	}

	public long getSales() {

		return numSale;

	}

	public int firstDigit() {

		return Integer.parseInt(Long.toString(numSale).substring(0, 1));

	}

}
