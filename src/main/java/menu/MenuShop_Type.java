package menu;

import model.Category;
import model.Shop;
import model.Shop_Type;
import persistence.RepositoryShop;
import persistence.RepositoryShop_Type;

import java.sql.Time;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuShop_Type {

    private RepositoryShop_Type repositoryShop_Type;
    public MenuShop_Type() {
        repositoryShop_Type  = new RepositoryShop_Type();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Shop_Type: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert shop_Type");
        System.out.println("2: Update Category");
        System.out.println("101 - Return to Main Menu");
        System.out.println("\n***************************************************");

        System.out.println("Type the Shop_Type menu option");
        return input.nextInt();
    }
    protected void menuChoice(Scanner input) {
        boolean exit;
        do {
            exit = false;
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    saveShop_Type(input);
                    break;
                case 2:
                    updateShop_Type(input);
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
    public void saveShop_Type(Scanner input) {

        boolean valid1= false;
        Shop_Type shop_Type = new Shop_Type();
        String categoryx;

        while (!valid1){
            System.out.println("Type the Shop_type");
            categoryx = input.next();
            boolean result = validateName(categoryx);
            if (result == true){
                shop_Type.setType(categoryx);
                valid1 =true;
                repositoryShop_Type.insertShop(shop_Type);

            }
        }

    }
    public void updateShop_Type(Scanner input){
        System.out.println("Type the shop Id");
        int id = input.nextInt();
        Shop_Type resultShop_Type = repositoryShop_Type.findById(id);
        if (resultShop_Type ==null){
            System.out.println("Shop_Type not registered on our database");
        }else{
            boolean valid1= false;
            Shop_Type shop_type = new Shop_Type();
            String shop_typeX;

            while (!valid1){
                System.out.println("Type the shop_type");
                shop_typeX = input.next();
                boolean result = validateName(shop_typeX);
                if (result == true){
                    shop_type.setType(shop_typeX);

                    valid1 =true;
                    repositoryShop_Type.updateCustomerStatus(shop_type);
                }
        }
    }
    }

    public boolean validateName(String a){
        boolean X =false;
        Pattern pattern = Pattern.compile("^([a-zA-Z]{3,20})$");
        Matcher matcher = pattern.matcher(a);



        if (matcher.find()){
            X =true;
        }
        else{
            X = false;
        }
        return X;
    }
}
