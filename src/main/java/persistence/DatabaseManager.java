package persistence;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private Connection connection;

    public DatabaseManager() {
        DBUtil.createDatabase();
        connection = DBUtil.getDBConnection();
    }
    // create new tables
    private void createTableShop_Type() {
        String sql = "CREATE TABLE IF NOT EXISTS Shop_Type (\n"
                + "	Shop_TypeId int NOT NULL AUTO_INCREMENT,\n"
                + "	Type varchar(45) DEFAULT NULL,\n"
                + "	 PRIMARY KEY (Shop_TypeId)\n"
                + ");";

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableShopOwner() {
        String sql = "CREATE TABLE IF NOT EXISTS ShopOwner (\n"
                + "	ShopOwnerId int NOT NULL AUTO_INCREMENT,\n"
                + "	FirstName varchar(45) NOT NULL,\n"
                + "	LastName varchar(45) NOT NULL,\n"
                + "	PRIMARY KEY (ShopOwnerId)\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableClients() {
        String sql = "CREATE TABLE IF NOT EXISTS Clients (\n"
                + "	ClientsId int NOT NULL AUTO_INCREMENT,\n"
                + "	FirstName varchar(45) NOT NULL,\n"
                + "	LastName varchar(45) NOT NULL,\n"
                + "	DateOfBirth DATE NOT NULL,\n"
                + "	PRIMARY KEY (ClientsId)\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableShop() {
        String sql = "CREATE TABLE IF NOT EXISTS Shop (\n"
                + "	ShopId int NOT NULL AUTO_INCREMENT,\n"
                + "	ShopName varchar(45) NOT NULL,\n"
                + "	OpeningTime TIME NOT NULL,\n"
                + "	ClosingTime TIME NOT NULL,\n"
                + " ShopOwnerId int NULL,\n"
                + " Shop_TypeId int NULL,\n"

                + "	PRIMARY KEY (ShopId)\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableShop_Has_Clients() {
        String sql = "CREATE TABLE IF NOT EXISTS Shop_Has_Clients (\n"
                + "	ShopId int NOT NULL,\n"
                + " ClientsId int NULL \n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createTableShop_Has_Products() {
        String sql = "CREATE TABLE IF NOT EXISTS Shop_Has_Products (\n"
                + "	ShopId int NOT NULL,\n"
                + " ProductsId int NOT NULL\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createTableCategory() {
        String sql = "CREATE TABLE IF NOT EXISTS Category (\n"
                + "	CategoryId int NOT NULL AUTO_INCREMENT,\n"
                + " Category varchar(45) NOT NULL,\n"
                + "	PRIMARY KEY (CategoryId)\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTableProducts() {
        String sql = "CREATE TABLE IF NOT EXISTS Products (\n"
                + "	ProductsId int NOT NULL AUTO_INCREMENT ,\n"
                + " ProductName varchar(45) NOT NULL,\n"
                + "	SupplyAmount int NOT NULL ,\n"
                + "	ReleaseDate DATE NOT NULL,\n"
                + "	CategoryId int NOT NULL ,\n"
                + "	PRIMARY KEY (ProductsId)\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createTableProducts_Has_Producers() {
        String sql = "CREATE TABLE IF NOT EXISTS Products_Has_Producers (\n"
                + "	ProductsId int NOT NULL ,\n"
                + "	ProducerId int NOT NULL \n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private void createTableProducer() {
        String sql = "CREATE TABLE IF NOT EXISTS Producer (\n"
                + "	ProducerId int NOT NULL AUTO_INCREMENT,\n"
                + " ProducerName varchar(45) NOT NULL,\n"
                + "	FoundedIn DATE NOT NULL,\n"
                + "	PRIMARY KEY (ProducerId)\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    private void addConstrainst() {
        String sql1 = " ALTER TABLE shop ADD CONSTRAINT Shop_Type_Shop_fkey  FOREIGN KEY (Shop_TypeId) REFERENCES shop_type(Shop_TypeId);";
        String sql2 = "ALTER TABLE Shop ADD CONSTRAINT rating_ShopOwnerId_fkey FOREIGN KEY (ShopOwnerId) REFERENCES ShopOwner(ShopOwnerId);";
        String sql3 = "ALTER TABLE shop_has_clients ADD CONSTRAINT shop_has_clients_shopX_fkey FOREIGN KEY (ShopId) REFERENCES shop(ShopId);";
        String sql4 = "ALTER TABLE shop_has_clients ADD CONSTRAINT shop_has_clients_clientsX_fkey FOREIGN KEY (ClientsId) REFERENCES clients(ClientsId);";
        String sql5 = "ALTER TABLE shop_has_products ADD CONSTRAINT shop_has_products_shopX_fkey FOREIGN KEY (ShopId) REFERENCES shop(ShopId);";
        String sql6 = "ALTER TABLE shop_Has_Products ADD CONSTRAINT shop_has_products_productsX_fkey FOREIGN KEY (ProductsId) REFERENCES products(ProductsId);";
        String sql7 = "ALTER TABLE products ADD CONSTRAINT products_categoryX_fkey FOREIGN KEY (CategoryId) REFERENCES category(CategoryId);";
        String sql8 = "ALTER TABLE products_has_producers ADD CONSTRAINT products_has_producers_productsX_fkey FOREIGN KEY (ProductsId) REFERENCES products(ProductsId);";
        String sql9 = "ALTER TABLE products_has_producers ADD CONSTRAINT products_has_producers_producersX_fkey FOREIGN KEY (ProducerId) REFERENCES producer(ProducerId);";

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.executeUpdate(sql4);
            stmt.executeUpdate(sql5);
            stmt.executeUpdate(sql6);
            stmt.executeUpdate(sql7);
            stmt.executeUpdate(sql8);
            stmt.executeUpdate(sql9);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initTables() {
        createTableShop_Type();
        createTableShopOwner();
        createTableClients();
        createTableShop();
        createTableShop_Has_Clients();
        createTableShop_Has_Products();
        createTableCategory();
        createTableProducts();
        createTableProducts_Has_Producers();
        createTableProducer();
        addConstrainst();
    }
}
