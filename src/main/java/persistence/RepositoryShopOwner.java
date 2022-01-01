package persistence;

import model.ShopOwner;
import model.Shop_Type;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositoryShopOwner {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;

    public RepositoryShopOwner() {
        connection = DBUtil.getDBConnection();
    }

    public void insertShop(ShopOwner shopOwner) {
        String sql = "INSERT INTO ShopOwner (FirstName , LastName)  values (?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,shopOwner.getFirstName());
            ps.setString(2,shopOwner.getLastName());


            ps.executeUpdate();
            System.out.println("data added to table");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
