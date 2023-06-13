package Azure;

import java.sql.*;
import java.util.Scanner;

public class CBDEM1 {
    private static final String DB_URL = "jdbc:sqlserver://yourserver.database.windows.net:1433;database=EMPDB";
    private static final String DB_USER = "username@yourserver";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        try {
            // Load SQL Server JDBC driver and establish connection to database
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Determine maximum employee number
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(EMPNO) FROM EMP");
            int maxEmpNo = 0;
            if (rs.next()) {
                maxEmpNo = rs.getInt(1);
            }

            // Query user for employee data
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter employee name: ");
                String ename = scanner.nextLine();
                if (ename.isEmpty()) {
                    break;
                }

                System.out.print("Enter employee job: ");
                String job = scanner.nextLine();

                System.out.print("Enter employee salary: ");
                int sal = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter employee dept: ");
                String dname = scanner.nextLine();

                // Insert employee row
                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO EMP (EMPNO, ENAME, JOB, SAL, DEPTNO) VALUES (?, ?, ?, ?, (SELECT DEPTNO FROM DEPT WHERE DNAME = ?))");
                int empNo = maxEmpNo + 1;
                insertStmt.setInt(1, empNo);
                insertStmt.setString(2, ename);
                insertStmt.setString(3, job);
                insertStmt.setInt(4, sal);
                insertStmt.setString(5, dname);
                try {
                    insertStmt.executeUpdate();
                    System.out.println(ename + " added to " + dname + " department as employee #" + empNo);
                    maxEmpNo = empNo;
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Close database connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}