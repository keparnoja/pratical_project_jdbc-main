package menu;

import model.*;
import persistence.RepositaryProducer;
import persistence.RepositoryProduct;

import java.nio.file.Files;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class MenuProducts {
    private RepositoryProduct repositoryProduct;

    public MenuProducts() {
        repositoryProduct = new RepositoryProduct();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Producer: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert product");
        System.out.println("2: print product amount by date");
        System.out.println("3: print products with all info");
        System.out.println("4: Print Product Amount By Category");
        System.out.println("5: Print All Product Names By Date");
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
                    saveProduct(input);
                    break;
                case 2:
                    getProductAmountByRelease(input);
                    break;
                case 3:
                    getExtraInfo();
                    break;
                case 4:
                    printProductAmountBelongToCategory();
                    break;
                case 5:
                    printEveryProductByDate(input);
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

    public void saveProduct(Scanner input) {
        Products products = new Products();
        String Name;
        System.out.println("Type the productName");
        Name = input.next();
        products.setProductName(Name);


        System.out.println("Type the supply");
        int Supply = input.nextInt();
        products.setSupplyAmount(Supply);

        System.out.println("Type the releaseDate");
        String close = input.next();
        products.setReleaseDate(Date.valueOf((close)));


        System.out.println("Type the categoryId");
        int CatId = input.nextInt();
        products.setCategoryID(CatId);
        repositoryProduct.insertProduct(products);
    }
    public void getProductAmountByRelease(Scanner input){
        System.out.println("Type Date");
        String name = input.next();
        List<Produ> list= repositoryProduct.getProductAmountDate(name);
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i).getDate() + " " + list.get(i).getTotal());
        }

    }
    public void getExtraInfo(){
        List<Produ> list = repositoryProduct.PrintInfo();
        for (int i = 0; i<list.size(); i++){
            System.out.println("Product name : " + list.get(i).getName() +
                    ", Supply Amount : " + list.get(i).getSupply()+
                    ", Release Date : " + list.get(i).getDate()+
                    ", Category  Name : " + list.get(i).getCategoryName()+
                    ", Producer Name : " + list.get(i).getProducerName());
        }
    }
    public void printProductAmountBelongToCategory(){
        List<Produ> list = repositoryProduct.ProductsBelongCategory();
        for (int i = 0; i<list.size(); i++){
            System.out.println("Category Name " + list.get(i).getCategoryName()+ ", amount of different products " + list.get(i).getTotal());

        }
    }
    public void printEveryProductByDate(Scanner input){
        System.out.println("Type Product Date");
        String date = input.next();
        List<Products> list= repositoryProduct.getEveryProductByDate(Date.valueOf(date));
        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i).getProductName());
        }

    }
}
