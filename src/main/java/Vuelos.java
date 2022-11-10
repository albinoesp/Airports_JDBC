import java.sql.*;
import java.util.Scanner;

public class Vuelos {

    public static void main(String[] args) {

        final String URL_DB = "jdbc:postgresql://localhost:5432/Airports";
        //final String URL_DB = "jdbc:mysql://localhost/produccion";
        final String USER_DB = "postgres";
        final String PASS_DB = "ad0r0Dormir";
        Scanner sc = new Scanner(System.in);
        String ciudad_llegada;



        try {
            // Cargar driver de JDBC
            // para la base de datos apropiada.
            Class.forName("org.postgresql.Driver");

        } catch (Exception ex) {
            System.out.println("No se encontró el driver JDBC.");
            System.exit(1);
        }

        Connection conn = null;
        PreparedStatement comando = null;
        ResultSet rs = null;

        try {
            // Abrir conexión
            // USER_DB: Usuario valido en PostgreSQL
            conn = DriverManager.getConnection(
                    URL_DB,
                    USER_DB,
                    PASS_DB);
            //Pedir la ciudad de llegada del aeropuerto
            System.out.print("\nIngrese la ciudad a la que desea ir: ");
            ciudad_llegada = sc.next();

            String sql = "SELECT * FROM flights "
                    + "WHERE ciudad_llegada = ?";

            comando = conn.prepareStatement(sql);

            comando.setString(1, ciudad_llegada);

            rs = comando.executeQuery();

            // Procesar ressultado
            while (rs.next()) {

                String nombre = rs.getString("number");
                String airline = rs.getString("airline");
                String ciudad_p = rs.getString("ciudad_partida");
                String ciudad_l = rs.getString("ciudad_llegada");
                String fecha_p = rs.getString("fecha_partida");
                String fecha_l = rs.getString("fecha_llegada");
                String hora_p = rs.getString("hora_partida");
                String hora_l = rs.getString("hora_llegada");

                System.out.print(nombre);
                System.out.print("\t");
                System.out.print(airline);
                System.out.print("\t");
                System.out.println(ciudad_p);
                System.out.println("\t");
                System.out.println(ciudad_l);
                System.out.println("\t");
                System.out.println(fecha_p);
                System.out.println("\t");
                System.out.println(fecha_l);
                System.out.println("\t");
                System.out.println(hora_p);
                System.out.println("\t");
                System.out.println(hora_l);
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.exit(1);
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (comando != null) {
                try {
                    comando.close();
                } catch (SQLException sqlEx) {
                } // ignore

                comando = null;
            }
        }
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error");
        }

    }
}
