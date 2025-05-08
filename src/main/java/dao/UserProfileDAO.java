package dao;

import connection.DataBase;
import model.RoleType;
import model.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserProfileDAO extends AbstractDAO<UserProfile> {

    @Override
    protected String getTableName() {
        // Nombre exacto de la tabla
        return "user_profile";
    }

    @Override
    protected UserProfile mapResultSetToEntity(ResultSet rs) throws SQLException {
        // Mapeamos columnas -> campos de UserProfile
        UserProfile userProfile = new UserProfile();
        userProfile.setIdUserProfile(rs.getInt("id_profile"));
        userProfile.setEmail(rs.getString("email"));
        userProfile.setIsAdmin(rs.getInt("is_admin"));
        userProfile.setRoleType(RoleType.fromValue(rs.getString("role_type")));
        // Si deseas mapear el User completo, puedes hacer un find en UserDAO con el user_id
        // int userId = rs.getInt("user_id");
        // ...
        return userProfile;
    }

    /**
     * Override porque nuestra PK es 'id_profile', no 'id'.
     */
    @Override
    public UserProfile findById(int id) throws SQLException {
        UserProfile userProfile = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE id_profile = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userProfile = mapResultSetToEntity(rs);
                }
            }
        }
        return userProfile;
    }

    /**
     * Crea un nuevo registro en 'user_profile' y retorna el objeto con su ID asignado.
     */
    @Override
    public UserProfile create(UserProfile userProfile) throws SQLException {
        String sql = "INSERT INTO user_profile (email, is_admin, role_type, user_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, userProfile.getEmail());
            stmt.setInt(2, userProfile.getIsAdmin());
            stmt.setString(3, userProfile.getRoleType().getValue());
            // Aquí asumimos que userProfile.getUser() no es null y que su ID ya está asignado
            stmt.setInt(4, userProfile.getUser().getIdUser());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al crear el perfil de usuario, no se afectaron filas.");
            }

            // Obtener la clave generada (id_profile)
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    userProfile.setIdUserProfile(generatedKeys.getInt(1));
                }
            }
            return userProfile;
        }
    }

    /**
     * Actualiza un registro en 'user_profile' y retorna el objeto actualizado.
     */
    @Override
    public UserProfile update(UserProfile userProfile) throws SQLException {
        String sql = "UPDATE user_profile SET email = ?, is_admin = ?, role_type = ? WHERE id_profile = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userProfile.getEmail());
            stmt.setInt(2, userProfile.getIsAdmin());
            stmt.setString(3, userProfile.getRoleType().getValue());
            stmt.setInt(4, userProfile.getIdUserProfile());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el perfil con id " + userProfile.getIdUserProfile());
            }
            return userProfile;
        }
    }

    /**
     * Elimina un registro de 'user_profile' por su id_profile.
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_profile = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    /**
     * Busca el perfil asociado a un usuario concreto (columna user_id).
     */
    public UserProfile findByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE user_id = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        }
        return null;
    }
}
