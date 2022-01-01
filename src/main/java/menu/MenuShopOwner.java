package menu;

import model.ShopOwner;
import model.Shop_Type;
import persistence.RepositoryShopOwner;
import persistence.RepositoryShop_Type;

import java.util.Scanner;

public class MenuShopOwner {

    private RepositoryShopOwner repositoryShopOwner;

    public MenuShopOwner() {
        repositoryShopOwner = new RepositoryShopOwner();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu ShopOwner: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert ShopOwner");
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

    public void saveShop_Type(Scanner input) {

        ShopOwner shopOwner = new ShopOwner();


        System.out.println("Type the FName");
        String FN = input.next();
        shopOwner.setFirstName(FN);
        System.out.println("Type the LName");
        String LN = input.next();
        shopOwner.setLastName(LN);
        repositoryShopOwner.insertShop(shopOwner);


    }
}
