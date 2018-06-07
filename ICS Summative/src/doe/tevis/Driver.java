package doe.tevis;

import java.util.*;
import java.io.*;

/**
 * Driver class for the sales analysis project.
 * 
 * @author Tevis Doe
 *
 */
public class Driver {
	// unique id for every single customer inputed.
	private static int id;

	public static void main(String[] args) {
		// Arraylists to hold Customers, the Postalcode data, and the sales
		// data.
		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<PostalCode> postalCodes = new ArrayList<>();
		ArrayList<Sale> sales = new ArrayList<>();
		// load the sales data.
		inputSales(sales);
		// load the postal codes from the file into the program.
		loadCodes(postalCodes);

		Scanner in = new Scanner(System.in);
		// A string is used instead of an integer here, since it's easier
		// to handle if a user doesn't enter a number.
		String uchoice = "0";
		// This is the main menu, which will keep repeating until the user
		// wishes to exit.
		while (!uchoice.equals("5")) {
			System.out.println(
					"\nSales Analysis System\n1. Input Customer Info\n2. Generate Customer Data File\n3. Sales Report\n4. Check for Fraud\n5. Quit");
			System.out.println("Enter your option now:");
			uchoice = in.nextLine();
			// Input a new customer.
			if (uchoice.equals("1")) {

				newCustomer(customers, postalCodes);
				// Generate the customer data file.
			} else if (uchoice.equals("2")) {

				generateCustomerFile(customers);
				// Print out total sales.
			} else if (uchoice.equals("3")) {

				System.out.println("Total amount of sales: " + displaySales(sales) + " sales.");
				// Check the sales data vs. Benford's law.
			} else if (uchoice.equals("4")) {

				if (benfordCheck(sales) == true) {

					System.out.println("First digit occurance in between 29% and 32%, unlikely that there is fraud.");

				} else {
					// For the when the user hasn't provided sales data yet.
					if (sales.size() == 0) {

						System.out.println("No sales data loaded!");

					}
					// For when the sales data simply doesn't follow Benford's
					// law.
					else {

						System.out.println("First digit occurance outside of 29% to 32%, fraudulence is likely.");

					}

				}
				// Exit the program.
			} else if (uchoice.equals("5")) {

				System.out.println("Program exiting...");
				// Error handling for invalid choice.
			} else {

				System.out.println("Invalid Input, please try again.");

			}
		}

	}

	private static long displaySales(ArrayList<Sale> sales) {

		long sale = 0;

		for (int i = 0; i < sales.size(); i++) {

			sale += sales.get(i).getSales();

		}

		return sale;

	}

	/**
	 * Method to check if the inputed sales data follows Benford's law.
	 * 
	 * @param sales
	 *            ArrayList that contains the sale data.
	 * @return Boolean on whether the sales data follows Benford's law.
	 */
	private static boolean benfordCheck(ArrayList<Sale> sales) {
		// Make sure the sale data is actually loaded.
		if (sales.size() != 0) {
			// Array to hold the number of times a digit occurs.
			double[] fdigits = new double[9];
			// For loop running through the sales data.
			for (int i = 0; i < sales.size(); i++) {
				// Check each first digit of sales, and add to array
				// depending on the first digit.
				if (sales.get(i).firstDigit() == 1) {

					fdigits[0]++;

				} else if (sales.get(i).firstDigit() == 2) {

					fdigits[1]++;

				} else if (sales.get(i).firstDigit() == 3) {

					fdigits[2]++;

				} else if (sales.get(i).firstDigit() == 4) {

					fdigits[3]++;

				} else if (sales.get(i).firstDigit() == 5) {

					fdigits[4]++;

				} else if (sales.get(i).firstDigit() == 6) {

					fdigits[5]++;

				} else if (sales.get(i).firstDigit() == 7) {

					fdigits[6]++;

				} else if (sales.get(i).firstDigit() == 8) {

					fdigits[7]++;

				} else if (sales.get(i).firstDigit() == 9) {

					fdigits[8]++;

				}

			}

			System.out.println("Percentage of times each leading digit comes up: ");

			double[] percentage = new double[9];
			// Calculate the percentage of each first digit occurrence.
			for (int i = 0; i < fdigits.length; i++) {

				percentage[i] = fdigits[i] / sales.size() * 100;

				System.out.print(i + 1 + ": ");
				System.out.format("%.2f%n", percentage[i]);

			}
			// If the number of times the digit 1 appears has a percentage in
			// between 29 and 30 inclusive, return true, otherwise return false.
			if ((fdigits[0] / sales.size()) * 100 >= 29 || (fdigits[0] / sales.size()) * 100 <= 32) {

				return true;
			}

			return false;

		}

		return false;

	}

	/**
	 * Method to load data from the provided sales data.
	 * 
	 * @param sales
	 *            ArrayList to contain the sales data.
	 */
	private static void inputSales(ArrayList<Sale> sales) {
		// New file taking data from sales.csv in the root folder.
		File pFile = new File("sales.csv");
		// Error handling for FileNotFoundException.
		try {
			// Scanner to read data from the file.
			Scanner input = new Scanner(pFile);
			String data[];
			String hold;
			// Skip header.
			input.nextLine();
			// While loop to run through the entire file.
			while (input.hasNextLine()) {
				// Take the next line of the data.
				hold = input.nextLine();
				// Split the data via a comma.
				data = hold.split(",");
				// Input the sales data into the sales ArrayList.
				sales.add(new Sale(data[0], Long.parseLong(data[1])));

			}

		} catch (FileNotFoundException e) {
			// Error message.
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method that writes customer data into a csv file.
	 * 
	 * @param customers
	 *            Customer data ArrayList.
	 */
	private static void generateCustomerFile(ArrayList<Customer> customers) {

		Scanner input = new Scanner(System.in);
		// Get file name and path from user.
		System.out.println("Enter the path and name of the output file: (Ex. f:\\\\output.txt)");
		String fileName = input.nextLine();
		// Create a file based on this name.
		File outFile = new File(fileName);
		// Error handling for IOException.
		try {
			// FileWriter & BufferWriter to write to file.
			FileWriter out = new FileWriter(outFile);
			BufferedWriter writeFile = new BufferedWriter(out);
			// Go through all of the customers ArrayList.
			for (int i = 0; i < customers.size(); i++) {
				// Write to one line of the file in the order of:
				// ID, Name, City, PostalCode, Credit Card Number.
				writeFile.write(
						customers.get(i).getID() + "," + customers.get(i).getName() + "," + customers.get(i).getCity()
								+ "," + customers.get(i).getCode() + "," + customers.get(i).getCredit());
				// Move to the next line.
				writeFile.newLine();
			}

			writeFile.close();
			System.out.println("Sucessfully wrote to file!");

		} catch (IOException e) {

			System.out.println(e.getMessage());

		}

	}

	/**
	 * Method to read data from the provided postal codes list.
	 * 
	 * @param postalCodes
	 *            ArrayList to hold postal codes, among other data.
	 */
	private static void loadCodes(ArrayList<PostalCode> postalCodes) {
		// Read from provided file.
		File pFile = new File("postal_codes.csv");
		// FileNotFoundException handling.
		try {
			// The rest of this is similar to the reading of the sales data.
			Scanner input = new Scanner(pFile);
			String data[];
			String hold;

			input.nextLine();

			while (input.hasNextLine()) {

				hold = input.nextLine();
				// The file splits data using the symbol |, however that is a
				// special character.
				// In order to make sure the program reads the file correctly,
				// two backslashes
				// are needed to split the data.
				data = hold.split("\\|");
				// Input data in the order of:
				// Postal Code, Place, City, Latitude, Longitude
				postalCodes.add(new PostalCode(data[0], data[1], data[2], Double.parseDouble(data[3]),
						Double.parseDouble(data[4])));

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to allow the user to input customer data.
	 * 
	 * @param customers
	 *            ArrayList to hold customer data.
	 * @param postalCodes
	 *            ArrayList that holds postal codes, in order to compare them to
	 *            user inputed ones.
	 */
	private static void newCustomer(ArrayList<Customer> customers, ArrayList<PostalCode> postalCodes) {

		String uchoice = "y";
		// This while loop allows the user to create multiple customers without
		// returning to the main menu.
		while (uchoice.equals("y")) {
			Scanner in = new Scanner(System.in);
			// Increment the unique id number for the customer.
			id++;
			// Gather the first name of the customer.
			System.out.println("Enter the customer's first name: ");
			String fName = in.nextLine();
			// Make sure the user inputs only the first name by disallowing
			// spaces.
			while (fName.contains(" ")) {

				System.out.println("Please input the customer's first name only. Try again.");
				fName = in.nextLine();

			}
			// Last name.
			System.out.println("Enter the customer's last name: ");
			String lName = in.nextLine();
			while (lName.contains(" ")) {

				System.out.println("Please input the customer's last name only. Try again.");
				lName = in.nextLine();

			}
			// Get city from user.
			System.out.println("Enter where the customer's city.");
			String place = in.nextLine();
			// Get postal code from the user.
			System.out.println("Enter the customer's postal code.");
			String pCode = in.nextLine();
			// Make sure the postal code is valid.
			while (!validatePostal(pCode, postalCodes)) {
				// Make the user type again if the postal code isn't valid.
				System.out.println("Invalid postal code, please try again.");
				pCode = in.nextLine();

			}
			// Credit card number.
			System.out.println("Enter the customer's credit card number.");
			String cc = in.nextLine();
			// Validate the credit card number.
			while (!validateCCN(cc)) {

				System.out.println("Invalid credit card number, try again.");
				cc = in.nextLine();

			}
			// Input data into customer ArrayList in order of:
			// First name, Last name, City, Postal code, Credit card number, id
			customers.add(new Customer(fName, lName, place, pCode, Long.parseLong(cc), id));
			// Get whether the user wants to add another customer or not.
			System.out.println("Would you like to input another customer? (y)es or (n)o");
			uchoice = in.nextLine();
		}
	}

	/**
	 * Method to validate postal codes that the user inputs.
	 * 
	 * @param pCode
	 *            User inputed postal code to verify.
	 * @param postalCodes
	 *            ArrayList filled with valid postal codes.
	 * @return True or false based on whether the postal code is valid or not.
	 */
	private static boolean validatePostal(String pCode, ArrayList<PostalCode> postalCodes) {
		// Check if the postal code is three characters or more.
		if (pCode.length() >= 3) {
			// Get the first 3 characters of the postal code to validate.
			String compareCode = pCode.substring(0, 3);
			// Run through the postal code ArrayList.
			for (int i = 0; i < postalCodes.size(); i++) {
				// Compare user inputed postal code to current code in ArrayList
				if (postalCodes.get(i).validateCode(compareCode)) {
					// As soon as one of the codes is compared to be true, then
					// exit the method and
					// return true.
					return true;

				}

			}

		}
		// If none of the codes validate, then return false.
		return false;
	}

	/**
	 * Method to validate the user inputed credit card number using Luhn's
	 * Algorithm.
	 * 
	 * @param cc
	 *            User inputed credit card number to validate.
	 * @return True or false based on whether the credit card number is valid or
	 *         not.
	 */
	private static boolean validateCCN(String cc) {
		// Array and ArrayList to hold numbers.
		int[] reverse = new int[cc.length()];
		ArrayList<Integer> evens = new ArrayList<>();
		// InputMistmatchException handling in case the user put any letters
		// into the
		// credit card number.
		try {
			// Make sure the number isn't negative.
			if (Integer.parseInt(cc) >= 0) {
				// Reverse the credit card number.
				for (int i = cc.length() - 1; i >= 0; i--) {

					reverse[i] = Character.getNumericValue(cc.charAt(i));

				}

				int sum1 = 0;
				// Run through the credit card number.
				for (int i = 0; i < reverse.length; i++) {
					// If the index of the for list is even, add the current
					// number to
					// sum1.
					if ((i % 2) == 0) {

						sum1 += reverse[i];

					}
					// Otherwise, add the number multiplied by two to the evens
					// ArrayList.
					else if ((i % 2) != 0) {

						evens.add(reverse[i] * 2);

					}

				}

				int sum2 = 0;
				// Run through the evens ArrayList.
				for (int i = 0; i < evens.size(); i++) {
					// If the number is above ten, add 1 to the last number in
					// the element and add
					// that to sum2.
					if (evens.get(i) >= 10) {

						sum2 += (evens.get(i) % 10) + 1;

					}
					// Otherwise just add the number to sum2.
					else {

						sum2 += evens.get(i);

					}

				}
				// If the sum of sum1 and sum 2 has a remainder of 0 when
				// divided by 10, and the
				// sum of them aren't zero, then return true.
				if ((sum1 + sum2) % 10 == 0 && sum1 + sum2 != 0) {

					return true;

				}
				// Otherwise return false.
				return false;
			}

			return false;

		} catch (NumberFormatException e) {

			return false;

		}

	}

}
