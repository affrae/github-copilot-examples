import java.sql.*;
import java.util.Scanner;

public class CBDEM1 {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Establish connection to database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Determine maximum employee number
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(EMPNO) FROM EMP");
            int maxEmpNo = 0;
            if (rs.next()) {
                maxEmpNo = rs.getInt(1);
            }

            // Query user for employee data
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
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}