package ProjectPoised;

import java.io.*;
import java.util.*;

public class Poised {

	public static void main(String[] args) {

		// try-catch
		try {
			// read file
			File file = new File("ProjectDetails.txt");

			// write to file
			FileWriter fwProjectDetails = new FileWriter("ProjectDetails.txt", true);

			// write to file
			FileWriter fwCompleteProject = new FileWriter("CompleteProjects.txt", true);

			// Scanner
			Scanner input = new Scanner(System.in);

			long contractorNumber = 0;

			// menu
			System.out.println("""
					* Capture new Project - (a)
					* Update a Project - (b)
					""");

			System.out.println("Enter Option: ");

			String menu = input.nextLine();

			while (true) {

				if (menu.equalsIgnoreCase("a")) {

					Scanner architectInput = new Scanner(System.in);

					// Architecture
					System.out.println("Enter Architecture Details");
					System.out.println("Name: ");
					String architectName = architectInput.nextLine();
					System.out.println("Telephone Number (Leave out 0): ");
					long architectNumber = architectInput.nextLong();
					System.out.println("Email Address: ");
					String architectEmail = architectInput.next();
					System.out.println("Physical Address: ");
					String architectAddress = architectInput.next();

					Scanner contractorInput = new Scanner(System.in);

					// Contractor
					System.out.println("Enter Contractor Details");
					System.out.println("Name: ");
					String contractorName = contractorInput.next();
					System.out.println("Telephone Number (Leave out 0): ");
					contractorNumber = contractorInput.nextLong();
					System.out.println("Email Address: ");
					String contractorEmail = contractorInput.next();
					System.out.println("Physical Address: ");
					String contractorAddress = contractorInput.next();

					Scanner customerInput = new Scanner(System.in);

					// Customer
					System.out.println("Enter Customer Details");
					System.out.println("Name: ");
					String customerName = customerInput.next();
					System.out.println("Telephone Number (Leave out 0): ");
					long customerNumber = customerInput.nextLong();
					System.out.println("Email Address: ");
					String customerEmail = customerInput.next();
					System.out.println("Physical Address: ");
					String customerAddress = customerInput.next();

					// Objects
					PoisedPerson architect = new PoisedPerson(architectName, architectNumber, architectEmail,
							architectAddress);
					PoisedPerson contractor = new PoisedPerson(contractorName, contractorNumber, contractorEmail,
							contractorAddress);
					PoisedPerson customer = new PoisedPerson(customerName, customerNumber, customerEmail,
							customerAddress);

					Scanner projectInput = new Scanner(System.in);

					// getEquation user input - details of project
					System.out.println("Enter Project Details: ");
					System.out.println("Project Number: ");
					int projectNumber = projectInput.nextInt();
					System.out.println("Project Name: ");
					String projectName = projectInput.next();
					System.out.println("Building Type: ");
					String buildingType = projectInput.next();
					System.out.println("Physical Address: ");
					String projectAddress = projectInput.next();
					System.out.println("ERF Number: ");
					int erfNumber = projectInput.nextInt();
					System.out.println("Total Fee: ");
					int feeTotal = projectInput.nextInt();
					System.out.println("Total Fee Paid to Date: ");
					int feePaidDate = projectInput.nextInt();
					System.out.println("Deadline (yyyy-mm-dd): ");
					String deadLine = projectInput.next();

					// project object
					Details Details = new Details(projectNumber, projectName, buildingType, projectAddress, erfNumber,
							feeTotal, feePaidDate, deadLine);

					// write to files
					fwProjectDetails.append(Details.DetailsPoised() + architect.poisedPerson()
							+ contractor.poisedPerson() + customer.poisedPerson() + "\r\n");

					fwProjectDetails.close();
				}

				// menu for updating project details
				else if (menu.equalsIgnoreCase("b")) {

					System.out.println("""
							* Update Deadline of Project - 1
							* Update Total Fee Paid to Date - 2
							* Update Contractor's Details - 3
							* Finalizing the Project - 4
							* Project Incomplete - 5
							* Projects past Deadline - 6
							""");

					System.out.println("Enter Menu Option: ");

					int menu2 = input.nextInt();

					// Options of menu
					if (menu2 == 1) {

						System.out.println("Enter Project Number: ");
						String menuProjectNumber = input.next();
						System.out.println("Enter new Deadline: ");
						String detailsIndex = input.next();
						int index = 7;

						Details.updateDetails(file, menuProjectNumber, index, detailsIndex);
					}

					// fee total Paid
					else if (menu2 == 2) {

						System.out.println("Enter Project Number: ");
						String menuProjectNumber = input.next();
						System.out.println("Enter Fee Paid: ");
						String detailsIndex = input.next();
						int index = 6;

						Details.updateDetails(file, menuProjectNumber, index, detailsIndex);
					}

					// contractor's details
					else if (menu2 == 3) {

						System.out.println("Enter Project Number: ");
						String menuProjectNumber = input.next();
						System.out.println("Enter Contractor's new Number: ");
						String detailsIndex = input.next();
						int index = 13;

						Details.updateDetails(file, menuProjectNumber, index, detailsIndex);
					}

					// Finalized Project
					else if (menu2 == 4) {

						System.out.println("Enter Project Number: ");
						String menuProjectNumber = input.next();
						System.out.println("Enter Date of Finalized Project: ");
						String dateComplete = input.next();

						Details.CompleteProjects(file, fwCompleteProject, menuProjectNumber, dateComplete);
					}

					// Incomplete Projects
					else if (menu2 == 5) {

						String incompleteProjects = "c";

						Details.viewListProjects(file, incompleteProjects);
					}

					// Past deadline
					else if (menu2 == 6) {

						String viewDeadlineProjects = "d";

						Details.viewListProjects(file, viewDeadlineProjects);
					}

					// incorrect option
					else {

						System.out.println("Please enter a Correct Option (1 - 6): ");
					}
				}

				break;

			}
		} catch (IOException e) {

			System.out.println("File not Found");

		} catch (NoSuchElementException e) {

			System.out.println("Scanner Cannot Read Input");
			e.printStackTrace();
		}
	}
}
