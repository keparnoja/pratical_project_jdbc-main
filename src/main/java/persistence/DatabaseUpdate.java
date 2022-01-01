package persistence;

import util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUpdate {


    private Connection connection;
    public DatabaseUpdate() {
        DBUtil.createDatabase();
        connection = DBUtil.getDBConnection();
    }
    private void updateTableClients() {
        String sql = "SELECT IF((SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS\n" +
                "    WHERE\n" +
                "      (table_name = 'clients')\n" +
                "      AND (table_schema = DATABASE())\n" +
                "      AND (column_name = 'dateOfRegister')\n" +
                "  ) > 0,\n" +
                "  'SELECT 1',\n" +
                "  CONCAT('ALTER TABLE ', 'clients', ' ADD ', 'dateOfRegister', ' DATE NOT NULL;')\n" +
                ");\n";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public void updateTables(){
        updateTableClients();
    }

}
