package model;

import util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryRating {
//    public void updateStockProduct(int customerId, int newStatus) {
//        connection.getTransaction().begin();
//        int result = connection.createQuery("UPDATE Customer p SET p.accountStatus = :newValue WHERE p.id = :id")
//                .setParameter("newValue", newStatus)
//                .setParameter("id", customerId)
//                .executeUpdate();
//        connection.getTransaction().commit();
//        if (result > 0) {
//            System.out.println("\nCustomer status updated with success.");
//        }
//    }

//    public Customer findCustomerById(int id) {
//        return connection.find(Customer.class, id);
//    }
//
//    //READ
//    //https://www.baeldung.com/hibernate-aggregate-functions
//    public long getTotalCustomerActives() {
//        return (long) connection.createQuery("SELECT count(c) from Customer c WHERE c.accountStatus = 1")
//                .getSingleResult();
//    }
//
//    public List<Object[]> getTotalCustomerActivesAndNotActives() {
//        String sql = "SELECT c.accountStatus, count(c) from Customer c GROUP BY c.accountStatus";
//        return connection.createQuery(sql).getResultList();
//    }
//
//    public void updatePhoneNumberByCustomerId(int customerId, String phoneNumber) {
//        connection.getTransaction().begin();
//        int result = connection.createQuery("UPDATE Customer c SET c.phoneNumber = :newValue WHERE c.id = :id")
//                .setParameter("newValue", phoneNumber)
//                .setParameter("id", customerId)
//                .executeUpdate();
//        connection.getTransaction().commit();
//        if (result > 0) {
//            System.out.println("\n Customer phone number successfully updated!");
//        }
//
//    }

    public List<Integer[]> getTotalCustomerActivesAndNotActives() {
        String sql = "Select account_status, count(account_status) as total from customer group by account_status";
        List<Integer[]> list = new ArrayList<>();
        try {
            PreparedStatement pstmt = DBUtil.getDBConnection().prepareStatement(sql);

            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                Integer[] obj = new Integer[2];

                obj[0] = resultSet.getInt("account_status");
                obj[1] = resultSet.getInt("total");
                list.add(obj);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    //       String sql "IF NOT EXISTS (SELECT NULL FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS\n"
//                + "	         WHERE CONSTRAINT_SCHEMA = DATABASE()\n"
//                + "	        AND CONSTRAINT_TYPE = 'FOREIGN KEY'\n"
//                + "	        AND CONSTRAINT_NAME = 'bid_ibfk_3') THEN\n"
//                + "	ALTER TABLE `bid` ADD CONSTRAINT `bid_ibfk_3`\n"
//                + "	 FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`);\n"
//                + "	END IF\n";
}
