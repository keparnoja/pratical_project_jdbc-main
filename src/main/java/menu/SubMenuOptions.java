package menu;

import util.DBUtil;

import java.util.Scanner;

public class SubMenuOptions {
    boolean exit = false;
    private MenuClients menuClients;
    private MenuShop menuShop;
    private MenuProducer menuProducer;
    private  MenuShop_Type shop_type;
    private  MenuCategory menuCategory;
    private  MenuShop_Has_Clients shop_has_clients;
    private  MenuShopOwner menuShopOwner;
    private  MenuProducts menuProducts;
    public SubMenuOptions() {
        this.menuClients = new MenuClients();
        this.menuShop = new MenuShop();
        this.menuProducer = new MenuProducer();
        this.shop_type = new MenuShop_Type();
        this.menuCategory = new MenuCategory();
        this.shop_has_clients = new MenuShop_Has_Clients();
        this.menuShopOwner = new MenuShopOwner();
        this.menuProducts = new MenuProducts();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Main menu ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Sub Menu - Client");
        System.out.println("2: Sub Menu - Shop");
        System.out.println("3: Sub Menu - Producer");
        System.out.println("4: Sub Menu - Shop_Type");
        System.out.println("5: Sub Menu - Category");
        System.out.println("6: Sub Menu - ShopClients");
        System.out.println("7: Sub Menu - ShopOwner");
        System.out.println("8: Sub Menu - Products");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }

    public void menuChoice(Scanner input) {
        while(!exit) {
            System.err.println(exit);
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    this.menuClients.menuChoice(input);
                    break;
                case 2:
                    this.menuShop.menuChoice(input);
                    break;
                case 3:
                    this.menuProducer.menuChoice(input);
                    break;
                case 4:
                    shop_type.menuChoice(input);
                    break;
                case 5:
                    menuCategory.menuChoice(input);
                    break;
                case 6:
                    shop_has_clients.menuChoice(input);
                    break;
                case 7:
                    menuShopOwner.menuChoice(input);
                    break;
                case 8:
                    menuProducts.menuChoice(input);
                    break;
                case 9:
                    break;
                case 10:

                    break;
                case 100:
                    exit = true;
                    System.out.println("System closed");
                    break;
//                default:
//                    System.out.println("\nSorry, please enter valid Option");
//                    menuChoice(input);
            }
        }
    }
}
