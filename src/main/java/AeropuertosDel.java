import java.sql.*;
import java.util.Scanner;

public class AeropuertosDel {
    public static void main(String[] args) {
        final String URL_DB = "jdbc:postgresql://localhost:5432/Airports";
        //final String URL_DB = "jdbc:mysql://localhost/produccion";
        final String USER_DB = "postgres";
        final String PASS_DB = "ad0r0Dormir";
        Scanner sc = new Scanner(System.in);
        String airport_code;

        try {
            // Cargar driver de JDBC
            // para la base de datos apropiada.
            Class.forName("org.postgresql.Driver");

        } catch (Exception ex) {
            System.out.println("No se encontró el driver JDBC.");
            System.exit(1);
        }
        //Pedir el código del aeropuerto
        System.out.print("Ingrese el código del aeropuerto: (3 caracteres): ");
        airport_code = sc.next().toUpperCase();
        while (airport_code.length() != 3) {
            System.out.println("Error. Deben ser 3 caracteres");
            System.out.print("\nIngrese el código del aeropuerto: (3 caracteres): ");
            airport_code = sc.next().toUpperCase();
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
            stmt = conn.prepareStatement("DELETE FROM airport WHERE airport_code = ?");
            stmt.setString(1, airport_code);
            // Ejecutar comando
            System.out.println("DELETE FROM airport WHERE airport_code = " + airport_code );
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
