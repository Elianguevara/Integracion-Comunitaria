package dao;

import model.User;
import connection.DataBase;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DAO concreto para la entidad User. Implementa las operaciones CRUD y métodos de autenticación.
 */
public class UserDAO extends AbstractDAO<User> {

    @Override
    protected String getTableName() {
        return "user";  // Nombre de la tabla
    }

    @Override
    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
        User user = new User();
        // Se usan los nombres de columna según el MER
        user.setIdUser(rs.getInt("id_user"));
        user.setName(rs.getString("name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setToken(rs.getString("token"));
        Timestamp ts = rs.getTimestamp("date_token");
        if (ts != null) {
            user.setDateToken(ts.toLocalDateTime());
        }
        return user;
    }

    @Override
    public User create(User user) throws SQLException {
        // Se insertan los campos name, last_name, email, password, username, enabled e id_profile.
        String sql = "INSERT INTO " + getTableName() + " (name, last_name, email, password, username, enabled, id_profile) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName()); // Asumiendo que getLastName() retorna el apellido
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            // Si no se tiene username, se puede usar el email como valor predeterminado
            stmt.setString(5, "por defecto");
            // Por defecto, se asume que el usuario está habilitado (true)
            stmt.setBoolean(6, true);
            // Asigna un perfil predeterminado (por ejemplo, 1)
            stmt.setInt(7, 0);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Se asume que el campo autogenerado es "id_user"
                        user.setIdUser(generatedKeys.getInt(1));
                    }
                }
                return user;
            } else {
                throw new SQLException("Error al insertar el usuario, no se afectaron filas.");
            }
        }
    }



    @Override
    public User update(User user) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET name = ?, email = ?, password = ? WHERE id_user = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getIdUser());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return user;
            } else {
                throw new SQLException("No se pudo actualizar el usuario con id " + user.getIdUser());
            }
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_user = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Busca un usuario por su email.
     */
    public User findByEmail(String email) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE email = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToEntity(rs);
                }
            }
        }
        return user;
    }

    /**
     * Actualiza la contraseña de un usuario, reiniciando token y fecha.
     */
    public boolean updatePassword(int userId, String newPassword) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET password = ?, token = NULL, date_token = NULL WHERE id_user = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Genera y guarda un token de recuperación para el usuario con el email dado.
     */
    public String generateToken(String email) throws SQLException {
        String token = UUID.randomUUID().toString();
        String sql = "UPDATE " + getTableName() + " SET token = ?, date_token = ? WHERE email = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, email);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                return token;
            }
        }
        return null;
    }

    /**
     * Valida si un token es válido (no expirado).
     */
    public boolean validateToken(String token) throws SQLException {
        String sql = "SELECT date_token FROM " + getTableName() + " WHERE token = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Timestamp ts = rs.getTimestamp("date_token");
                    if (ts != null) {
                        LocalDateTime tokenTime = ts.toLocalDateTime();
                        LocalDateTime now = LocalDateTime.now();
                        if (tokenTime.plusHours(24).isBefore(now)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Busca un usuario por su token de recuperación.
     */
    public User findByToken(String token) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE token = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, token);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToEntity(rs);
                }
            }
        }
        return user;
    }
}
