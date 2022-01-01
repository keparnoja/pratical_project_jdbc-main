package persistence;

import model.Shop;
import model.Shop_Type;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryShop_Type {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;
    public RepositoryShop_Type() {
        connection = DBUtil.getDBConnection();
    }
    public void insertShop(Shop_Type shop_Type) {
        String sql = "INSERT INTO Shop_Type (Type)  values (?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,shop_Type.getType());

            ps.executeUpdate();
            System.out.println("data added to table");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public Shop_Type findById(int shop_TypeId) {
        String sql = "SELECT * FROM Shop_Type WHERE Shop_TypeId = ?";
        Shop_Type shop_Type = null;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setInt(1, shop_TypeId);

            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                shop_Type = new Shop_Type();
                shop_Type.setType(resultSet.getString("Type"));
                ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop_Type;
    }
    public void updateCustomerStatus(Shop_Type shop_Type) {
        String sql = "UPDATE Shop_Type SET Type = ? WHERE Shop_TypeId = ?";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setString(1, shop_Type.getType());
            pstmt.setInt(2, shop_Type.getShopId() );
            int result = pstmt.executeUpdate();
            if(result > 0) {
                System.out.println("ShopType status updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
