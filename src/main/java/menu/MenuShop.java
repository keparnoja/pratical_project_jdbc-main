package menu;

import model.Clients;
import model.Produ;
import model.Shop;
import model.Shopu;
import persistence.RepositoryClients;
import persistence.RepositoryShop;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class MenuShop {
    private RepositoryShop repositoryShop;
    public MenuShop() {
        repositoryShop  = new RepositoryShop();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Shop: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert shop");
        System.out.println("2: Update shop opening time");
        System.out.println("3: shops with a lot of info about them");
        System.out.println("4: shop name with owner names only");
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
                    saveShop(input);
                    break;
                case 2:
                    updateShopOpening(input);
                    break;
                case 3:
                    getExtraInfo();
                    break;
                case 4:
                    getShopsWithOwners();
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
    public void saveShop(Scanner input) {
        Shop shop = new Shop();
        System.out.println("Type the name");
        String shopname = input.next();
        shop.setShopName(shopname);

        System.out.println("Type the opening time");
        String open = input.next();
        shop.setOpeningTime(Time.valueOf((open)));

        System.out.println("Type the closing time");
        String close = input.next();
        shop.setClosingTime(Time.valueOf((close)));

        System.out.println("Type ownerId");
        int owner = input.nextInt();
        shop.setShopOwnerId(owner);

        System.out.println("Type typeId");
        int type = input.nextInt();
        shop.setShop_TypeId(type);


        repositoryShop.insertShop(shop);

    }
    public void updateShopOpening(Scanner input){
        System.out.println("Type the shop Id");
        int id = input.nextInt();
        Shop resultShop = repositoryShop.findById(id);
        if (resultShop ==null){
            System.out.println("Shop not registered on our database");
        }else{
            System.out.println("Type the new time");
            String newTime = input.next();
            Shop shop = new Shop();
            shop.setOpeningTime(Time.valueOf(newTime));
            repositoryShop.updateCustomerStatus(shop);
        }
    }
    public void getExtraInfo(){
        List<Shopu> list = repositoryShop.PrintInfo();
        for (int i = 0; i<list.size(); i++){
            System.out.println("Shop name : " + list.get(i).getShopName() +
                    ", Opening Time : " + list.get(i).getOpening()+
                    ", Type : " + list.get(i).getShopType()+
                    ", Owner First Name : " + list.get(i).getFirstName()+
                    ", Owner Last Name : " + list.get(i).getLastName());
        }
    }

    public void getShopsWithOwners(){
        List<Shopu> list = repositoryShop.PrintInfo();
        for (int i = 0; i<list.size(); i++){
            System.out.println("Shop name : " + list.get(i).getShopName() +
                    ", Owner First Name : " + list.get(i).getFirstName()+
                    ", Owner Last Name : " + list.get(i).getLastName());
        }
    }
}
