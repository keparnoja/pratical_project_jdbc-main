package persistence;

import model.Shop;
import model.Shop_Has_Clients;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepositoryShop_Has_Clients {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;
    public RepositoryShop_Has_Clients() {
        connection = DBUtil.getDBConnection();
    }
    public void insertShop(Shop_Has_Clients shop_has_clients) {
        String sql = "INSERT INTO Shop_Has_Clients (ShopId, ClientsId)  values (?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,shop_has_clients.getShopId());
            ps.setInt(2,shop_has_clients.getClientID());
            ps.executeUpdate();
            System.out.println("data added to table");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
