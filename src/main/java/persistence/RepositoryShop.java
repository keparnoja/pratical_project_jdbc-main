package persistence;

import model.*;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositoryShop {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;
    public RepositoryShop() {
        connection = DBUtil.getDBConnection();
    }

    public void insertShop(Shop shop) {
        String sql = "INSERT INTO Shop (ShopName,OpeningTime, ClosingTime, ShopOwnerId, Shop_TypeId)  values (?,?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,shop.getShopName());
            ps.setTime(2, shop.getOpeningTime());
            ps.setTime(3, shop.getClosingTime());
            ps.setInt(4, shop.getShopOwnerId());
            ps.setInt(5, shop.getShop_TypeId());
            ps.executeUpdate();
            System.out.println("data added to table");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public Shop findById(int shopId) {
        String sql = "SELECT * FROM Shop WHERE ShopId = ?";
        Shop shop = null;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setInt(1, shopId);

            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                shop = new Shop();
                shop.setShopName(resultSet.getString("ShopName"));
                shop.setOpeningTime(resultSet.getTime("OpeningTime"));
                shop.setClosingTime(resultSet.getTime("ClosingTime"));
                shop.setShopOwnerId(resultSet.getInt("ShopOwnerId"));
                shop.setShop_TypeId(resultSet.getInt("Shop_TypeId"));
;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return shop;
    }
    public void updateCustomerStatus(Shop shop) {
        String sql = "UPDATE Shop SET OpeningTime = ? WHERE ShopId = ?";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setTime(1, shop.getOpeningTime());
            pstmt.setInt(2, shop.getShopId() );
            int result = pstmt.executeUpdate();
            if(result > 0) {
                System.out.println("Customer status updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Shopu>PrintInfo(){
        List<Shopu> list = new ArrayList<>();
        String sql = "SELECT S.ShopName, S.OpeningTime, ST.Type, SO.FirstName, SO.LastName" +
                " FROM Shop AS S" +
                " INNER JOIN Shop_Type AS ST ON ST.Shop_TypeId = S.Shop_TypeId"+
                " INNER JOIN ShopOwner AS SO ON SO.ShopOwnerId = S.ShopOwnerId";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Shopu shopu = new Shopu();
                shopu.setShopName(resultSet.getString("S.ShopName"));
                shopu.setOpening(resultSet.getTime("s.OpeningTime"));
                shopu.setShopType(resultSet.getString("ST.Type"));
                shopu.setFirstName(resultSet.getString("SO.FirstName"));
                shopu.setLastName(resultSet.getString("SO.LastName"));
                list.add(shopu);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  list;
    }
    public List<Shopu>PrintShopOwner(){
        List<Shopu> list = new ArrayList<>();
        String sql = "SELECT S.ShopName, SO.FirstName, SO.LastName" +
                " FROM Shop AS S" +
                " INNER JOIN ShopOwner AS SO ON SO.ShopOwnerId = S.ShopOwnerId";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Shopu shopu = new Shopu();
                shopu.setShopName(resultSet.getString("S.ShopName"));
                shopu.setFirstName(resultSet.getString("SO.FirstName"));
                shopu.setLastName(resultSet.getString("SO.LastName"));
                list.add(shopu);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return  list;
    }

}


































