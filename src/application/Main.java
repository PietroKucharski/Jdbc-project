package application;

import db.DB;
import db.DbException;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Connection connection = null;
        PreparedStatement st = null;

        try {
            connection = DB.getConnection();

            /*st = connection.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, " +
                    "DepartmentId) VALUES (?, ?, ?, ?, ?)");*/

            /*st = connection.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, " +
                    "DepartmentId) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            LocalDate birthDate = LocalDate.parse("13/06/2002", dtf);
            st.setString(1, "Pietro Kucharski");
            st.setString(2, "pietro@email.com");
            st.setDate(3, java.sql.Date.valueOf(birthDate));
            st.setDouble(4, 3000.00);
            st.setInt(5, 4); */

            st = connection.prepareStatement("insert into department (Name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rown affected");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
