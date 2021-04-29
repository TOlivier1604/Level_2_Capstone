package ProjectPoised;

import java.io.*;
import java.util.*;
import java.time.*;

public class Details {

	// Attributes
	int projectNumber;
	String projectName;
	String buildingType;
	String projectAddress;
	int erfNumber;
	int feeTotal;
	int feePaidDate;
	String deadLine;

	/**
	 * method update details
	 * 
	 * @param projectNumber
	 * @param projectName
	 * @param buldingType
	 * @param projectAddress
	 * @param erfNumber
	 * @param feeTotal
	 * @param feePaidDate
	 * @param deadline
	 **/

	public Details(int projectNumber, String projectName, String buildingType, String projectAddress, int erfNumber,
			int feeTotal, int feePaidDate, String deadLine) {

		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.buildingType = buildingType;
		this.projectAddress = projectAddress;
		this.erfNumber = erfNumber;
		this.feeTotal = feeTotal;
		this.feePaidDate = feePaidDate;
		this.deadLine = deadLine;
	}

	/**
	 * User details method
	 * 
	 * @return details
	 **/

	public String DetailsPoised() {
		String output = projectNumber + ", ";
		output += projectName + ", ";
		output += buildingType + ", ";
		output += projectAddress + ", ";
		output += erfNumber + ", ";
		output += feeTotal + ", ";
		output += feePaidDate + ", ";
		output += deadLine + ", ";

		return output;
	}

	/**
	 * method update and read details on file
	 * 
	 * @param file               read
	 * @param inputProjectNumber select project
	 * @param numIndex           update index
	 * @param detailsIndex       index details update
	 **/

	public static void updateDetails(File file, String inputProjectNum, int numIndex, String detailsIndex) {

		// try-catch
		try {

			// read file
			BufferedReader brFile = new BufferedReader(new FileReader(file));
			StringBuilder strB = new StringBuilder();

			// read contents
			while (brFile.ready()) {

				String content = brFile.readLine();

				if (content.contains(inputProjectNum)) {

					// split text
					// convert array to list
					// join string
					// new string to string builder
					String[] textSplit = content.split(", ");
					List<String> arr = Arrays.asList(textSplit);
					arr.set(numIndex, detailsIndex);
					String listJoin = String.join(",", arr);
					strB.append(listJoin).append("\n");
					System.out.println("Changes are Successful");
				}

				else {

					// content not needed back to StringBuilder
					strB.append(content).append("\n");
				}
			}

			// close reader
			brFile.close();

			// open file to write, write to file, close file
			BufferedWriter bwFile = new BufferedWriter(new FileWriter(file));
			bwFile.write(strB.toString());
			bwFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * finalized projects method and display invoice
	 * 
	 * @param file            read
	 * @param CompleteProject file
	 * @param projectNumber   select number
	 * @param dateComplete    date finished
	 **/
	public static void CompleteProjects(File file, FileWriter fwCompleteProject, String projectNumber,
			String dateComplete) {

		// try-catch
		try {

			// read file
			BufferedReader brFile = new BufferedReader(new FileReader(file));

			// split string to list store
			List<String> arr;

			while (brFile.ready()) {

				String content = brFile.readLine();

				if (content.contains(projectNumber)) {

					// text split and convert array to list
					String[] textSplit = content.split(", ");
					arr = Arrays.asList(textSplit);

					// index for fee due and fee paid
					// converting fee due and paid to integer
					// calculate fee need to pay
					String totalFee = arr.get(5);
					String paidFee = arr.get(6);
					int feeTotal = Integer.parseInt(totalFee);
					int feePaidDate = Integer.parseInt(paidFee);
					int feeOutstanding = (feeTotal - feePaidDate);

					// Project has outstanding fee
					// invoice print
					// mark project complete
					if (feeTotal > feePaidDate) {

						System.out.println("***INVOICE***");
						System.out.println("Customer Number: " + arr.get(17));
						System.out.println("Fees Outstanding: R" + feeOutstanding);

						fwCompleteProject.append("Complete: ").append(dateComplete).append("\n");
						fwCompleteProject.append(content).append("\n");

					}

					// project fully paid
					// mark project complete
					else {
						fwCompleteProject.append("Complete: ").append(dateComplete).append("\n");
						fwCompleteProject.append(content).append("\n");
					}
				}
			}

			// close file
			fwCompleteProject.close();
			brFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method read file, display list project
	 * 
	 * @param file        read
	 * @param viewProject
	 **/
	public static void viewListProjects(File file, String viewProject) {

		// try-catch
		try {

			// read file
			BufferedReader brFile = new BufferedReader(new FileReader(file));

			// Strings store
			StringBuilder needComplete = new StringBuilder();
			StringBuilder pastDeadline = new StringBuilder();

			while (brFile.ready()) {

				// split text
				// convert array to list
				// date index
				String content = brFile.readLine();
				String[] textSplit = content.split(", ");
				List<String> arr = Arrays.asList(textSplit);
				String date = arr.get(7);

				// converting string date to date type
				LocalDate dateA = LocalDate.parse(date);
				LocalDate dateB = LocalDate.now();

				// value > 0 - dateA is after dateB
				if (dateA.compareTo(dateB) > 0) {

					needComplete.append(arr).append("\n");
				}

				else if (dateA.compareTo(dateB) < 0) {

					pastDeadline.append(arr).append("\n");
				}
			}

			// view incompleteFiles
			if (viewProject.equalsIgnoreCase("c")) {

				System.out.println("List Incomplete Projects: ");
				System.out.println(needComplete);
			}

			// view past Deadline
			if (viewProject.equalsIgnoreCase("d")) {

				System.out.println("List Projects Past Deadline: ");
				System.out.println(pastDeadline);
			}

			// close file
			brFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
