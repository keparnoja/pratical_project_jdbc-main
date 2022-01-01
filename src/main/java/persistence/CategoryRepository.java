package persistence;

import model.Category;
import model.Produ;
import model.Shop;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;
    public CategoryRepository() {
        connection = DBUtil.getDBConnection();
    }

    public void insertShop(Category category) {
        String sql = "INSERT INTO Category (Category)  values (?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,category.getCategory());

            ps.executeUpdate();
            System.out.println("data added to table");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public Category findById(int categoryId) {
        String sql = "SELECT * FROM Category WHERE CategoryId = ?";
        Category category = null;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setInt(1, categoryId);

            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                category = new Category();
                category.setCategory(resultSet.getString("Category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }
    public void updateCustomerStatus(Category category) {
        String sql = "UPDATE Category SET Category = ? WHERE CategoryId = ?";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setString(1, category.getCategory());
            pstmt.setInt(2, category.getCategoryId());
            int result = pstmt.executeUpdate();
            if(result > 0) {
                System.out.println("Customer status updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Produ> ProductsBelongCategory(){
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
}
