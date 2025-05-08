package dao;

import connection.DataBase;
import model.Profession;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessionDAO extends AbstractDAO<Profession> {

    @Override
    protected String getTableName() {
        return "profession";
    }

    @Override
    protected Profession mapResultSetToEntity(ResultSet rs) throws SQLException {
        Profession profession = new Profession();
        profession.setIdProfession(rs.getInt("id_profession"));
        profession.setName(rs.getString("name"));
        int catId = rs.getInt("id_category");
        if (!rs.wasNull()) {
            Category category = new Category();
            category.setIdCategory(catId);
            category.setName(rs.getString("category_name")); // O usa la columna 'name'
            profession.setCategory(category);
        }
        return profession;
    }

    @Override
    public Profession create(Profession entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " (name, id_category) VALUES (?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getName());
            if(entity.getCategory() != null && entity.getCategory().getIdCategory() != null) {
                stmt.setInt(2, entity.getCategory().getIdCategory());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al crear la profesión, no se afectó ninguna fila.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setIdProfession(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al crear la profesión, no se obtuvo el ID.");
                }
            }
        }
        return entity;
    }

    @Override
    public Profession update(Profession entity) throws SQLException {
        if(entity.getIdProfession() == null) {
            throw new SQLException("No se puede actualizar una profesión sin id_profession.");
        }
        String sql = "UPDATE " + getTableName() + " SET name = ?, id_category = ? WHERE id_profession = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getName());
            if(entity.getCategory() != null && entity.getCategory().getIdCategory() != null) {
                stmt.setInt(2, entity.getCategory().getIdCategory());
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.setInt(3, entity.getIdProfession());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al actualizar la profesión, no se afectó ninguna fila.");
            }
        }
        return entity;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_profession = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
