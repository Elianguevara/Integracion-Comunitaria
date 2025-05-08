package dao;

import connection.DataBase;
import model.Gender;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenderDAO extends AbstractDAO<Gender> {

    @Override
    protected String getTableName() {
        return "gender";  // Nombre de la tabla en la BD
    }

    @Override
    protected Gender mapResultSetToEntity(ResultSet rs) throws SQLException {
        Gender gender = new Gender();
        gender.setIdGender(rs.getInt("id_gender"));
        gender.setGender(rs.getString("gender"));
        // Si en BaseEntity tienes campos de auditoría, puedes mapearlos aquí (date_create, etc.)
        return gender;
    }

    @Override
    public Gender create(Gender entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " (gender) VALUES (?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getGender());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo insertar el género, filas afectadas = 0.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setIdGender(generatedKeys.getInt(1));
                }
            }
        }
        return entity;
    }

    @Override
    public Gender update(Gender entity) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET gender = ? WHERE id_gender = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entity.getGender());
            stmt.setInt(2, entity.getIdGender());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el género con id = " + entity.getIdGender());
            }
        }
        return entity;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_gender = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }

    // Los métodos findById y findAll se heredan de AbstractDAO si ya están implementados allí.
}
