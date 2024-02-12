import java.sql.*;

public class Intercambio {
    // Suponiendo que el motor de base de datos es MySQL
    private static final String JDBC_DRIVER = "jdbc:mysql://localhost/NOMBRE_DE_LA_BASE_DE_DATOS";
    private static final String USER = "usuario";
    private static final String PASS = "contraseña";

    private Connection conn;

    public static void main(String[] args) {
        try {
            Intercambio intercambio = new Intercambio();
            intercambio.ejecutarIntercambio("nombre_de_la_tabla", "columnaA", 1, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ejecutarIntercambio(String tabla, String columna, int id1, int id2) throws SQLException {
        conectar();
        int valor1 = obtenerValorColumna(tabla, columna, id1);
        int valor2 = obtenerValorColumna(tabla, columna, id2);

        try {
            if (valor1 != -1 && valor2 != -1) {
                intercambiarValores(tabla, columna, id1, valor2);
                intercambiarValores(tabla, columna, id2, valor1);
                System.out.println("Los datos de la columna '" + columna + "' han sido intercambiados con éxito.");
            } else {
                System.out.println("Error: No se pudieron obtener los valores de la columna '" + columna + "'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        desconectar();
    }

    private void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_DRIVER, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            //En caso de que no se pueda conectar a la base de datos, no tiene sentido seguir con el programa por lo que se debe expandir el error.
            throw new SQLException("Error al conectar a la base de datos.");
        }
    }

    private int obtenerValorColumna(String tabla, String columna, int id) {
        int valor = -1;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + columna + " FROM " + tabla + " WHERE id = " + id);
            if (rs.next()) {
                valor = rs.getInt(columna);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valor;
    }

    private void intercambiarValores(String tabla, String columna, int id, int nuevoValor) {
        ejecutarUpdate("UPDATE " + tabla + " SET " + columna + " = " + nuevoValor + " WHERE id = " + id);
    }

    private void ejecutarUpdate(String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void desconectar() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
