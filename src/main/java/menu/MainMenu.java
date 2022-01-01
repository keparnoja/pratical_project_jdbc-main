package menu;

import java.util.Scanner;
import persistence.DatabaseManager;
import persistence.DatabaseUpdate;

public class MainMenu {
    private static DatabaseManager databaseManager;
    private static SubMenuOptions subMenuOptions;
    private static Scanner input;
    private static DatabaseUpdate databaseUpdate;



    public static void main(String[] args) {
        initSystem();
    }

    public static void getMainMenu() {
        subMenuOptions.menuChoice(input);
    }

    public static void initSystem() {
        databaseManager = new DatabaseManager();
        databaseManager.initTables();
        databaseUpdate= new DatabaseUpdate();
        databaseUpdate.updateTables();

        input = new Scanner(System.in);
        subMenuOptions = new SubMenuOptions();
        subMenuOptions.menuChoice(input);
    }

 }
