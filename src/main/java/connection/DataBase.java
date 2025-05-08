package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {
    private static DataBase instance;

    private static final String USER = "ies9021_userdb";
    private static final String PASSWORD = "Xsw23edc.2025";
    private static final String DATABASE = "ies9021_coco";
    private static final String HOST = "ies9021.edu.ar";
    private static final String PORT = "3306";
    private static final String CONNECTION_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

    // Constructor privado para evitar instanciación directa
    private DataBase() {
    }

    // Método sincronizado para asegurar que solo exista una instancia (Singleton)
    public static synchronized DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    /**
     * Abre y retorna una nueva conexión a la base de datos.
     * Cada llamada devuelve una conexión nueva, que debe cerrarse utilizando try-with-resources en el código cliente.
     *
     * @return una conexión a la base de datos.
     * @throws SQLException si ocurre un error al abrir la conexión.
     */
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        Logger.getLogger(DataBase.class.getName()).log(Level.INFO, "Conexión abierta correctamente");
        return conn;
    }
}
