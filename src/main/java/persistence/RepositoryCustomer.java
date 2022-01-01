package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.ReportRatingByCustomer;
import util.DBUtil;

public class RepositoryCustomer {

    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;

    public RepositoryCustomer() {
        connection = DBUtil.getDBConnection();
    }

    public List<Customer> listAllCustomers() {
        List<Customer> customerList = new ArrayList<Customer>();
        String sql = "SELECT * FROM customer";
        try {
           pstmt = DBUtil.getDBConnection().prepareStatement(sql);

           resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                Customer customer = new Customer();
                customer.setAccountStatus(resultSet.getInt("account_status"));
                customer.setEmail(resultSet.getString("email"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setMiddleName(resultSet.getString("middle_name"));
                customer.setPassword(resultSet.getString("password"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setProfileImage(resultSet.getString("profile_image"));
                customer.setUserName(resultSet.getString("username"));
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customerList.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    public Customer findById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";
        Customer customer = null;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setInt(1, customerId);

            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                customer = new Customer();
                customer.setAccountStatus(resultSet.getInt("account_status"));
                customer.setEmail(resultSet.getString("email"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setMiddleName(resultSet.getString("middle_name"));
                customer.setPassword(resultSet.getString("password"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setProfileImage(resultSet.getString("profile_image"));
                customer.setUserName(resultSet.getString("username"));
                customer.setCustomerId(resultSet.getInt("customer_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    public void updateCustomerStatus(int customerId, int status) {
        String sql = "UPDATE customer SET account_status = ? WHERE customer_id = ?";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setInt(1, status);
            pstmt.setInt(2, customerId );
            int result = pstmt.executeUpdate();
            if(result > 0) {
                System.out.println("Customer status updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long getTotalCustomerActives() {
        String sql = "SELECT count(account_status) as total FROM customer WHERE account_status = 1";
        long result = 0;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getLong("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Integer[]> getTotalCustomerActivesAndNotActives() {
        List<Integer[]> list = new ArrayList<>();
        String sql = "SELECT account_status, count(account_status) as total " +
                " FROM customer" +
                " GROUP BY account_status";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
              Integer[] objList = new Integer[2];
              objList[0] = resultSet.getInt("account_status");
              objList[1] = resultSet.getInt("total");
              list.add(objList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateCustomerPhoneNumber(int customerId, String phoneNumber) {

    }

    public List<ReportRatingByCustomer> listRatingMenuByCustomer() {
        List<ReportRatingByCustomer> list = new ArrayList<>();
        String sql = "SELECT cus.first_name, rat.score, me.menu_name, res.name" +
                " FROM customer cus" +
                " INNER JOIN rating rat ON rat.customer_id = cus.customer_id" +
                " INNER JOIN menu me ON me.menu_id = rat.menu_id" +
                " INNER JOIN restaurant res ON res.restaurant_id = me.restaurant_id";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
               ReportRatingByCustomer report = new ReportRatingByCustomer();
               report.setCustomerName(resultSet.getString("cus.first_name"));
               report.setRating(resultSet.getInt("rat.score"));
               report.setMenuName(resultSet.getString("me.menu_name"));
               report.setRestaurant(resultSet.getString("res.name"));
               list.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
