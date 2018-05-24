package doe.tevis;

import java.util.*;
import java.io.*;

public class Driver {

	private static int id;

	public static void main(String[] args) {

		ArrayList<Customer> customers = new ArrayList<>();
		ArrayList<PostalCode> postalCodes = new ArrayList<>();
		loadCodes(postalCodes);

		Scanner in = new Scanner(System.in);
		// A string is used instead of an integer here, since it's easier
		// to handle if a user doesn't enter a number.
		String uchoice = "0";
		// This is the main menu, which will keep repeating until the user
		// wishes to exit.
		while (!uchoice.equals("5")) {
			System.out.println(
					"Sales Analysis System\n1. Input Customer Info\n2. Generate Customer Data File\n3. Sales Report\n4. Check for Fraud\n5. Quit");
			System.out.println("Enter your option now:");
			uchoice = in.nextLine();

			if (uchoice.equals("1")) {

				newCustomer(customers, postalCodes);

			} else if (uchoice.equals("2")) {

				generateCustomerFile(customers);

			} else if (uchoice.equals("4")) {

			} else if (uchoice.equals("5")) {

				System.out.println("Program exiting...");

			} else {

				System.out.println("Invalid Input, please try again.");

			}
		}

	}

	private static void generateCustomerFile(ArrayList<Customer> customers) {

		Scanner in = new Scanner(System.in);

		System.out.println("Enter the path and name of the output file: (Ex. f:\\\\output.txt)");
		String fileName = in.nextLine();

		File outFile = new File(fileName);

		try {

			FileWriter out = new FileWriter(outFile);
			BufferedWriter writeFile = new BufferedWriter(out);
			

			for (int i = 0; i < customers.size(); i++) {

				writeFile.write(customers.get(i).getID() + "," + customers.get(i).getName() + "," + customers.get(i).getCity()
						+ "," + customers.get(i).getCode() + "," + customers.get(i).getCredit());
				writeFile.newLine();
			}
			
			writeFile.close();
			System.out.println("Sucessfully wrote to file!");

		} catch (IOException e) {

			System.out.println(e.getMessage());

		}

	}

	private static void loadCodes(ArrayList<PostalCode> postalCodes) {

		File pFile = new File("postal_codes.csv");

		try {

			Scanner in = new Scanner(pFile);
			String data[];
			String hold;

			in.nextLine();

			while (in.hasNextLine()) {

				hold = in.nextLine();
				data = hold.split("\\|");
				postalCodes.add(new PostalCode(data[0], data[1], data[2], Double.parseDouble(data[3]),
						Double.parseDouble(data[4])));

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void newCustomer(ArrayList<Customer> customers, ArrayList<PostalCode> postalCodes) {

		Scanner in = new Scanner(System.in);
		id++;

		System.out.println("Enter the customer's first name: ");
		String fName = in.nextLine();
		while (fName.contains(" ")) {

			System.out.println("Please input the customer's first name only. Try again.");
			fName = in.nextLine();

		}
		System.out.println("Enter the customer's last name: ");
		String lName = in.nextLine();
		while (lName.contains(" ")) {

			System.out.println("Please input the customer's last name only. Try again.");
			lName = in.nextLine();

		}

		System.out.println("Enter where the customer's city.");
		String place = in.nextLine();

		System.out.println("Enter the customer's postal code.");
		String pCode = in.nextLine();
		while (!validatePostal(pCode, postalCodes)) {

			System.out.println("Invalid postal code, please try again.");
			pCode = in.nextLine();

		}
		System.out.println("Enter the customer's credit card number.");
		String cc = in.nextLine();

		while (!validateCCN(cc)) {

			System.out.println("Invalid credit card number, try again.");
			cc = in.nextLine();

		}

		customers.add(new Customer(fName, lName, place, pCode, Long.parseLong(cc), id));

	}

	private static boolean validatePostal(String pCode, ArrayList<PostalCode> postalCodes) {

		if (pCode.length() >= 3) {

			String compareCode = pCode.substring(0, 3);

			for (int i = 0; i < postalCodes.size(); i++) {

				if (postalCodes.get(i).validateCode(compareCode)) {

					return true;

				}

			}

		}

		return false;
	}

	private static boolean validateCCN(String cc) {

		int[] reverse = new int[cc.length()];
		ArrayList<Integer> evens = new ArrayList<>();

		try {

			for (int i = cc.length() - 1; i >= 0; i--) {

				reverse[i] = Character.getNumericValue(cc.charAt(i));

			}

			int sum1 = 0;

			for (int i = 0; i < reverse.length; i++) {

				if ((i % 2) == 0) {

					sum1 += reverse[i];

				}

				else if ((i % 2) != 0) {

					evens.add(reverse[i] * 2);

				}

			}

			int sum2 = 0;

			for (int i = 0; i < evens.size(); i++) {

				if (evens.get(i) >= 10) {

					sum2 += (evens.get(i) % 10) + 1;

				}

				else {

					sum2 += evens.get(i);

				}

			}
			if ((sum1 + sum2) % 10 == 0 && sum1 + sum2 != 0) {

				return true;

			}

			return false;

		} catch (InputMismatchException e) {

			return false;

		}

	}

}
