package main;

import java.sql.*;
import java.util.*;

public class HistoryAccessObject {

    public final String URL = "jdbc:mysql://localhost:3306/calculator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public final String USER = "admin";
    public final String PASS = "admin";
    public DatabaseConnection connector;
    public Connection connection;

    private History extractHistoryFromResultSet(ResultSet rs) throws SQLException {
        History hist = new History(rs.getString("result"));
        return hist;
    }

    public HistoryAccessObject() {
        this.connector = new DatabaseConnection(URL, USER, PASS);
        this.connection = connector.getConnection();
    }

    public Set<History> getHistory() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT result FROM calculator");
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

    public boolean insertHistory(History hist) {
        this.connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO calculator (result) VALUES (?)");
            ps.setString(1, hist.getResult());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean clearAll() {
        this.connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("DELETE FROM calculator");

            if (i >= 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
