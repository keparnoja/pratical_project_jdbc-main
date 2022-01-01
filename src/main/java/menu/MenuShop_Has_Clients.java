package menu;

import model.Shop_Has_Clients;
import model.Shop_Type;
import persistence.RepositoryShop_Has_Clients;
import persistence.RepositoryShop_Type;

import java.util.Scanner;

public class MenuShop_Has_Clients {
    private RepositoryShop_Has_Clients repositoryShop_has_clients;

    public MenuShop_Has_Clients() {
        repositoryShop_has_clients = new RepositoryShop_Has_Clients();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Shop_has_clients: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert Shop_has_clients");

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

        Shop_Has_Clients shop_has_clients = new Shop_Has_Clients();



        System.out.println("Type the shopId");
        int shopid = input.nextInt();
        shop_has_clients.setShopId(shopid);

        System.out.println("Type the clientsId");
        int clients = input.nextInt();
        shop_has_clients.setClientID(clients);


        repositoryShop_has_clients.insertShop(shop_has_clients);

    }
}
