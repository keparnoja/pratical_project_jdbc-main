package persistence;

import model.ClientByShop;
import model.Clients;
import model.Shop;
import util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepositoryClients {
    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;
    public RepositoryClients() {
        connection = DBUtil.getDBConnection();
    }

    public void insertClient(Clients client) {
      String sql = "INSERT INTO clients (FirstName,LastName, DateOfBirth, DateOfRegister)  values (?,?,?,?)";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setDate(3, (Date) client.getDateOfBirth());
            ps.setDate(4, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            System.out.println("data added to table");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void clientAmount(){
        String sql = "SELECT COUNT(ClientsId) AS numberOfClients FROM db_pratical_project_jdbc.clients";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            while (rs.next()){
                System.out.println("numberOfClients " + rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public List<ClientByShop>getShopClients(){
        List<ClientByShop> list = new ArrayList<>();
        String sql = "SELECT S.ShopName, Cl.FirstName" +
                " FROM  Clients AS Cl"+
                " INNER JOIN Shop_Has_Clients AS SHC ON SHC.ClientsId = Cl.ClientsId"+
                " INNER JOIN Shop AS S ON S.ShopId = SHC.ShopId";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ClientByShop clientByShop = new ClientByShop();
                clientByShop.setClientName(resultSet.getString("Cl.FirstName"));
                clientByShop.setShopName(resultSet.getString("S.ShopName"));
                list.add(clientByShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ClientByShop> getShopClientsTotal(){
        List<ClientByShop> list = new ArrayList<>();
        String sql = "SELECT S.ShopName, count(Cl.FirstName) as total " +
                " FROM  Clients AS Cl"+
                " INNER JOIN Shop_Has_Clients AS SHC ON SHC.ClientsId = Cl.ClientsId"+
                " INNER JOIN Shop AS S ON S.ShopId = SHC.ShopId" +
                " GROUP BY S.ShopName";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ClientByShop clientByShop = new ClientByShop();
                clientByShop.setTotal(resultSet.getInt("total"));
                clientByShop.setShopName(resultSet.getString("S.ShopName"));
                list.add(clientByShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<ClientByShop> getShopClientsTotalFilter(String s){
        List<ClientByShop> list = new ArrayList<>();
        String sql = "SELECT S.ShopName, count(Cl.FirstName) as total " +
                " FROM  Clients AS Cl"+
                " INNER JOIN Shop_Has_Clients AS SHC ON SHC.ClientsId = Cl.ClientsId"+
                " INNER JOIN Shop AS S ON S.ShopId = SHC.ShopId" +
                " WHERE S.ShopName  = ?" +
                " GROUP BY S.ShopName"
                ;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setString(1, s);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                ClientByShop clientByShop = new ClientByShop();

                clientByShop.setTotal(resultSet.getInt("total"));
                clientByShop.setShopName(resultSet.getString("S.ShopName"));
                list.add(clientByShop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
