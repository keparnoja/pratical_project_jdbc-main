package persistence;

import model.ClientByShop;
import model.Produ;
import model.Producer;
import model.Products;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryProduct {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;

    public RepositoryProduct() {
        connection = DBUtil.getDBConnection();
    }

    public void insertProduct(Products products) {
        String sql = "INSERT INTO products (ProductName, SupplyAmount,ReleaseDate, CategoryId)  values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, products.getProductName());
            ps.setInt(2, products.getSupplyAmount());
            ps.setDate(3, (Date) products.getReleaseDate());
            ps.setInt(4, products.getCategoryID());
            ps.executeUpdate();
            System.out.println("data added to table");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Produ> getProductAmountDate(String s){
        List<Produ> list = new ArrayList<>();
        String sql = "SELECT P.ReleaseDate, count(P.productName) as total "+
                " From Products AS P"+
                " WHERE P.ReleaseDate = ?"+
                " GROUP BY P.ReleaseDate";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setString(1, s);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Produ produ = new Produ();
                produ.setTotal(resultSet.getInt("total"));
                produ.setDate(resultSet.getDate("P.ReleaseDate"));
                list.add(produ);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
       return list;
    }
    public List<Produ>PrintInfo(){
        List<Produ> list = new ArrayList<>();
        String sql = "SELECT P.ProductName, P.SupplyAmount, P.ReleaseDate, C.Category, PR.ProducerName" +
                " FROM products AS P" +
                " INNER JOIN Products_Has_Producers AS PHP ON PHP.ProductsId = P.ProductsID"+
                " INNER JOIN Producer AS PR ON PR.producerId = PHP.producerId" +
                " INNER JOIN Category AS C On C.CategoryId = P.CategoryId";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Produ produ = new Produ();
                produ.setDate(resultSet.getDate("P.ReleaseDate"));
                produ.setName(resultSet.getString("P.ProductName"));
                produ.setProducerName(resultSet.getString("PR.ProducerName"));
                produ.setSupply(resultSet.getInt("P.SupplyAmount"));
                produ.setCategoryName(resultSet.getString("C.Category"));
                list.add(produ);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  list;
    }
    public List<Produ>ProductsBelongCategory(){
        List<Produ> list = new ArrayList<>();
        String sql = "SELECT C.Category, count(P.ProductName) as total" +
                " FROM products AS P" +
                " INNER JOIN Category AS C On C.CategoryId = P.CategoryId" +
                " GROUP BY C.Category";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Produ produ = new Produ();
                produ.setTotal(resultSet.getInt("total"));
                produ.setCategoryName(resultSet.getString("C.Category"));
                list.add(produ);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  list;
    }
    public List<Products> getEveryProductByDate(Date s){
        List<Products> list = new ArrayList<>();
        String sql = "SELECT p.ProductName , p.ReleaseDate" +
                " FROM  Products AS p"+
                " WHERE p.ReleaseDate  = ?";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setDate(1,s);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Products products = new Products();

                products.setProductName(resultSet.getString("p.ProductName"));
                products.setReleaseDate(resultSet.getDate("p.ReleaseDate"));

                list.add(products);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
