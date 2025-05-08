package dao;

import connection.DataBase;
import model.TypeProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeProviderDAO extends AbstractDAO<TypeProvider> {

    @Override
    protected String getTableName() {
        return "type_provider";
    }

    @Override
    protected TypeProvider mapResultSetToEntity(ResultSet rs) throws SQLException {
        TypeProvider typeProvider = new TypeProvider();
        typeProvider.setIdTypeProvider(rs.getInt("id_type_provider"));
        typeProvider.setType(rs.getString("type"));
        typeProvider.setBusiness(rs.getShort("business"));
        typeProvider.setTransport(rs.getShort("transport"));
        return typeProvider;
    }

    @Override
    public TypeProvider create(TypeProvider entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " (type, business, transport) VALUES (?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getType());
            stmt.setShort(2, entity.getBusiness() != null ? entity.getBusiness() : 0);
            stmt.setShort(3, entity.getTransport() != null ? entity.getTransport() : 0);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al crear el tipo de proveedor, no se afectó ninguna fila.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setIdTypeProvider(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al crear el tipo de proveedor, no se obtuvo el ID.");
                }
            }
        }
        return entity;
    }

    @Override
    public TypeProvider update(TypeProvider entity) throws SQLException {
        if (entity.getIdTypeProvider() == null) {
            throw new SQLException("No se puede actualizar un tipo de proveedor sin id_type_provider.");
        }
        String sql = "UPDATE " + getTableName() + " SET type = ?, business = ?, transport = ? WHERE id_type_provider = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getType());
            stmt.setShort(2, entity.getBusiness() != null ? entity.getBusiness() : 0);
            stmt.setShort(3, entity.getTransport() != null ? entity.getTransport() : 0);
            stmt.setInt(4, entity.getIdTypeProvider());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al actualizar el tipo de proveedor, no se afectó ninguna fila.");
            }
        }
        return entity;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_type_provider = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
