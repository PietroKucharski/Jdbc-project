package application;

import db.DB;
import db.DbException;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection connection;
        PreparedStatement st = null;

        try {
            connection = DB.getConnection();

            st = connection.prepareStatement("update seller set BaseSalary = BaseSalary + ? where (DepartmentId = ?)");

            st.setDouble(1, 200.0);
            st.setInt(2, 2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected" + rowsAffected);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
