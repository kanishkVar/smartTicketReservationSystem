public class DatabaseConnection {
  public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/ticketdb";
    String user = "root";
    String pass = "root";
    return DriverManager.getConnection(url, user, pass);
  }
}
