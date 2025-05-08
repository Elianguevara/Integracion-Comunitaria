package dao;

import connection.DataBase;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class CategoryDAO extends AbstractDAO<Category> {

    @Override
    protected String getTableName() {
        return "category";
    }

    @Override
    protected Category mapResultSetToEntity(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setIdCategory(rs.getInt("id_category"));
        category.setName(rs.getString("name"));
        return category;
    }

    @Override
    public Category create(Category entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " (name) VALUES (?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getName());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al crear la categoría, no se afectó ninguna fila.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setIdCategory(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al crear la categoría, no se obtuvo el ID.");
                }
            }
        }
        return entity;
    }

    @Override
    public Category update(Category entity) throws SQLException {
        if (entity.getIdCategory() == null) {
            throw new SQLException("No se puede actualizar una categoría sin id_category.");
        }
        String sql = "UPDATE " + getTableName() + " SET name = ? WHERE id_category = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getIdCategory());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al actualizar la categoría, no se afectó ninguna fila.");
            }
        }
        return entity;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_category = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
