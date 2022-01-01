package menu;

import model.Category;
import model.Produ;
import model.Shop;
import persistence.CategoryRepository;
import persistence.RepositoryShop;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuCategory {
    private CategoryRepository categoryRepository;

    public MenuCategory() {
        categoryRepository  = new CategoryRepository();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Menu Category: ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Insert Category");
        System.out.println("2: Update Category");
        System.out.println("101 - Return to Main Menu");
        System.out.println("\n***************************************************");

        System.out.println("Type the Category menu option");
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
                    printProductAmountBelongToCategory();
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
    public void saveShop(Scanner input) {
        boolean valid1= false;
        Category category = new Category();
        String categoryx;


        while (!valid1){
            System.out.println("Type the category");
            categoryx = input.next();
            boolean result = validateName(categoryx);
            if (result == true){
                category.setCategory(categoryx);
                valid1 =true;
            }
        }
        valid1 = false;








        categoryRepository.insertShop(category);

    }
    public void updateShopOpening(Scanner input){
        System.out.println("Type the Category Id");
        int id = input.nextInt();
        Category resultShop = categoryRepository.findById(id);
        if (resultShop ==null){
            System.out.println("Category not registered on our database");
        }else{
            boolean valid1= false;
            Category category = new Category();
            String categoryx;

            while (!valid1){
                System.out.println("Type the category");
                categoryx = input.next();
                boolean result = validateName(categoryx);
                if (result == true){
                    category.setCategory(categoryx);

                    valid1 =true;categoryRepository.updateCustomerStatus(category);
                }
            }
        }
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
    public void printProductAmountBelongToCategory(){
        List<Produ> list = categoryRepository.ProductsBelongCategory();
        for (int i = 0; i<list.size(); i++){
            System.out.println("Category Name " + list.get(i).getCategoryName()+ ", amount of different products " + list.get(i).getTotal());
        }
    }

}
