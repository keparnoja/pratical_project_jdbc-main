package menu;

import model.ClientByShop;
import model.Clients;
import persistence.RepositoryClients;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuClients {
    private RepositoryClients repositoryClients;
    public MenuClients() {
        repositoryClients   = new RepositoryClients();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Customer: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert Client");
        System.out.println("2: List total active clients");
        System.out.println("3: List total active and not active clients With names and more");
        System.out.println("4: show all shops and how many clients they have");
        System.out.println("5: how many clients shop has by shop name");
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
                    saveClient(input);
                    break;
                case 2:
                    ShowClientAmoount();
                case 3:
                    getShopClients();
                    break;
                case 4:
                    getShopClientsTotal();
                    break;
                case 5:
                    getShopClientsTotalFilter(input);
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
    public void ShowClientAmoount(){
        repositoryClients.clientAmount();
    }

    public void saveClient(Scanner input) {
        Clients client = new Clients();

        boolean validFirstName = false;
        boolean validLastname = false;
        boolean validYear= false;
        String FirstName;
        String lastName;
        String dateOfBirth;

        while (!validFirstName){
            System.out.println("Type the first name");
            FirstName = input.next();
            boolean result = false;
            result = validateName(FirstName);
            if (result == true){

                client.setFirstName(FirstName);
                validFirstName = true;
            }
        }


        validFirstName =false;
        while(!validLastname){
            System.out.println("Type the Last name");
            lastName = input.next();
            boolean result = validateName(lastName);
            if (result == true){
                client.setLastName(lastName);
                validLastname =true;
            }

        }
        validLastname = false;


        while (!validYear){
            System.out.println("Type the Date of birth");
            dateOfBirth = input.next();
            Date date = Date.valueOf(dateOfBirth);
            boolean result = validateDate(date);
            if(result == true){
                validYear = true;
                client.setDateOfBirth(date);
            }

        }



        repositoryClients.insertClient(client);

    }
    public boolean validateName(String firstname){
        boolean X =false;
        Pattern pattern = Pattern.compile("^([a-zA-Z]{3,20})$");
        Matcher matcher = pattern.matcher(firstname);



        if (matcher.find()){
            X =true;
        }
        else{
            X = false;
        }
        return X;
    }
    public boolean validateDate(Date date){
        boolean X = false;
        LocalDate localDate = LocalDate.now();
        Date date1 = Date.valueOf(localDate);
        System.out.println(date1);
        if (date1.after(date)){
            X = true;
        }else{
            X = false;
        }

        return  X;

    }
    public void getShopClients(){
       List<ClientByShop> list= repositoryClients.getShopClients();
       for (int i = 0; i< list.size(); i++){
           System.out.println(list.get(i).getShopName() + " " + list.get(i).getClientName());
       }

    }
    public void getShopClientsTotal(){
        List<ClientByShop> list= repositoryClients.getShopClientsTotal();
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i).getShopName() + " " + list.get(i).getTotal());
        }

    }
    public void getShopClientsTotalFilter(Scanner input){
        System.out.println("Type Shop Name");
        String name = input.next();
        List<ClientByShop> list= repositoryClients.getShopClientsTotalFilter(name);
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i).getShopName() + " " + list.get(i).getTotal());
        }

    }



}
























