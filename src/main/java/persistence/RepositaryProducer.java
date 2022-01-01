package persistence;

import model.Producer;
import model.Shop;
import util.DBUtil;

import java.sql.*;

public class RepositaryProducer {

    private Connection connection;
    PreparedStatement pstmt;
    ResultSet resultSet;

    public RepositaryProducer() {
        connection = DBUtil.getDBConnection();
    }

    public void insertProducer(Producer producer) {
        String sql = "INSERT INTO Producer (ProducerName, FoundedIn)  values (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, producer.getName());
            ps.setDate(2, (Date) producer.getFoundedIn());
            ps.executeUpdate();
            System.out.println("data added to table");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateProducerName(Producer producer) {
        String sql = "UPDATE procedure SET ProducerName = ? WHERE ProducerId = ?";
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setString(1, producer.getName());
            pstmt.setInt(2, producer.getProducerId());
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Customer status updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Producer findById(int producerId) {
        String sql = "SELECT * FROM Producer WHERE ProducerId = ?";
        Producer producer = null;
        try {
            pstmt = DBUtil.getDBConnection().prepareStatement(sql);
            pstmt.setInt(1, producerId);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                producer = new Producer();
                producer.setName(resultSet.getString("ProducerName"));
                producer.setFoundedIn(resultSet.getDate("FoundedIn"));

                ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return producer;
    }
}
