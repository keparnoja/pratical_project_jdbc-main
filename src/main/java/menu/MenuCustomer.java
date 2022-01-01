package menu;

import java.util.List;
import java.util.Scanner;
import model.Customer;
import model.ReportRatingByCustomer;
import persistence.RepositoryCustomer;

public class MenuCustomer {
    RepositoryCustomer repositoryCustomer = new RepositoryCustomer();

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Customer: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: List all customers");
        System.out.println("101 - Return to Main Menu");
        System.out.println("\n***************************************************");

        System.out.println("Type the Customer menu option");
        return input.nextInt();
    }

    protected void menuChoice(Scanner input) {
        boolean exit;
        do {
             exit = false;
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    menuListAllCustomers(input);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 101:
                    exit=true;
                    MainMenu.getMainMenu();
                    break;
//                default:
//                    System.out.println("\nSorry, please enter valid Option");
//                    menuChoice(input);
            }// End of switch statement
        } while (!exit);
    }

    private void menuListAllCustomers(Scanner input) {
        List<Customer> listCustomer = repositoryCustomer.listAllCustomers();

        if (listCustomer.size() > 0) {
            System.out.println("\nList of Customers:");
            for (Customer cust : listCustomer) {
                System.out.println(cust.toString());
            }
        } else {
            System.out.println("\nNo customers registered\n");
        }
    }

    public void saveClient(Scanner input) {
        System.out.println("Type the first name");

    }

}
