package menu;

import model.Category;
import model.Producer;
import model.Shop;
import persistence.RepositaryProducer;
import persistence.RepositoryShop;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuProducer {

    private RepositaryProducer repositaryProducer;

    public MenuProducer() {
        repositaryProducer = new RepositaryProducer();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Producer: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert producer");
        System.out.println("2: Update producer name");
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
                    saveProducer(input);
                    break;
                case 2:
                    updateProducerName(input);
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
                    exit = true;
                    MainMenu.getMainMenu();
                    break;
//                default:
//                    System.out.println("\nSorry, please enter valid Option");
//                    menuChoice(input);
            }// End of switch statement
        } while (!exit);
    }

    public void saveProducer(Scanner input) {

        boolean valid1 = false;
        Producer producer = new Producer();
        String categoryx;
        boolean validYear = false;


        while (!valid1) {
            System.out.println("Type the Producer name");
            String ProducerName = input.next();

            boolean result = validateName(ProducerName);
            if (result == true) {
                producer.setName(ProducerName);
                valid1 = true;
            }
        }
        valid1 = false;
        while (!validYear) {
            System.out.println("Type the Date of birth");
            String dateOfBirth = input.next();
            Date date = Date.valueOf(dateOfBirth);
            boolean result = validateDate(date);
            if (result == true) {
                validYear = true;
                producer.setFoundedIn(date);
            }
        }
        repositaryProducer.insertProducer(producer);

    }

    public void updateProducerName(Scanner input) {
        System.out.println("Type the Producer Id");
        int id = input.nextInt();
        Producer resultShop = repositaryProducer.findById(id);
        if (resultShop == null) {
            System.out.println("Producer not registered on our database");
        } else {
            System.out.println("Type the new time");
            String newTime = input.next();
            Producer producer = new Producer();
            producer.setName(newTime);
            repositaryProducer.updateProducerName(producer);
        }
    }

    public boolean validateName(String firstname) {
        boolean X = false;
        Pattern pattern = Pattern.compile("^([a-zA-Z]{3,20})$");
        Matcher matcher = pattern.matcher(firstname);


        if (matcher.find()) {
            X = true;
        } else {
            X = false;
        }
        return X;
    }

    public boolean validateDate(Date date) {
        boolean X = false;
        LocalDate localDate = LocalDate.now();
        Date date1 = Date.valueOf(localDate);
        System.out.println(date1);
        if (date1.after(date)) {
            X = true;
        } else {
            X = false;
        }

        return X;

    }

}
