package main;

import java.sql.*;
import java.util.*;

public class HistoryAccessObject {

    Set<History> getHistory() {
        DatabaseConnection connector = new DatabaseConnection();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM calculator");
            Set calculateResult = new HashSet();
            while (rs.next()) {
                History hist = extractHistoryFromResultSet(rs);
                calculateResult.add(hist);
            }
            return calculateResult;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
