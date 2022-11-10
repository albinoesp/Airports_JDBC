import java.sql.*;
import java.util.Scanner;

public class AeropuertosIns {

    public static void main(String[] args) {
        final String URL_DB = "jdbc:postgresql://localhost:5432/Airports";
        //final String URL_DB = "jdbc:mysql://localhost/produccion";
        final String USER_DB = "postgres";
        final String PASS_DB = "ad0r0Dormir";
        Scanner sc = new Scanner(System.in);
        String airport_code, city, state, name;

        //Pedir el código del aeropuerto
        System.out.print("Ingrese el código del aeropuerto: (3 caracteres): ");
        airport_code = sc.next().toUpperCase();
        while (airport_code.length() != 3) {
            System.out.println("Error. Deben ser 3 caracteres");
            System.out.print("\nIngrese el código del aeropuerto: (3 caracteres): ");
            airport_code = sc.next().toUpperCase();
        }

        //Pedir la ciudad del aeropuerto
        System.out.print("\nIngrese la ciudad del aeropuerto: ");
        city = sc.next();

        //Pedir el estado del aeropuerto
        System.out.print("\nIngrese el estado del aeropuerto: ");
        state = sc.next();

        //Pedir el nombre del aeropuerto
        System.out.print("\nIngrese el nombre del aeropuerto: ");
        name = sc.next();
        name.replace("\n", " ");

        try {
            // Cargar driver de JDBC
            // para la base de datos apropiada.
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            System.out.println("No se encontró el driver JDBC.");
            System.exit(1);
        }

        Connection conn = null;
        try {
            // Abrir conexión
            // USER_DB: Usuario valido en PostgreSQL
            conn = DriverManager.getConnection(
                    URL_DB,
                    USER_DB,
                    PASS_DB);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        }
        PreparedStatement stmt;
        try {
            // Crear el comando para ejecución
            stmt = conn.prepareStatement("INSERT INTO airport(airport_code, city, state, name) VALUES (?,?,?,?)");
            stmt.setString(1, airport_code);
            stmt.setString(2, city);
            stmt.setString(3, state);
            stmt.setString(4, name);
            System.out.println("Comando: INSERT INTO airport(airport_code, city, state, name) VALUES ("
                    + airport_code + ", "
                    + city + ", "
                    + state + ", "
                    + name + ")");
            // Ejecutar comando
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        }
        try {

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }
}
