package dao;

import connection.DataBase;
import model.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProviderDAO extends AbstractDAO<Provider> {

    @Override
    protected String getTableName() {
        return "provider"; // Nombre de la tabla asociada a Provider
    }

    @Override
    protected Provider mapResultSetToEntity(ResultSet rs) throws SQLException {
        Provider provider = new Provider();
        provider.setIdProvider(rs.getInt("id_provider"));           // Columna "id_provider"
        provider.setName(rs.getString("name"));                     // Columna "name"
        // Mapear otros campos según sea necesario...
        return provider;
    }

    @Override
    public Provider create(Provider provider) throws SQLException {
        // Se incluye el campo 'address' en la inserción
        String sql = "INSERT INTO " + getTableName() + " (name, address, id_user) VALUES (?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, provider.getName());
            // Si address es nulo o vacío, se asigna "ingresar direccion"
            String address = provider.getAddress();
            if (address == null || address.trim().isEmpty()) {
                address = "ingresar direccion";
            }
            stmt.setString(2, address);

            if (provider.getUser() == null) {
                throw new SQLException("El objeto Provider no tiene asignado un User.");
            }
            stmt.setInt(3, provider.getUser().getIdUser());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        provider.setIdProvider(generatedKeys.getInt(1));
                    }
                }
                return provider;
            } else {
                throw new SQLException("No se pudo insertar el proveedor, no se afectaron filas.");
            }
        }
    }

    @Override
    public Provider update(Provider provider) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET name = ?, address = ?, id_user = ? WHERE id_provider = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, provider.getName());
            String address = provider.getAddress();
            if (address == null || address.trim().isEmpty()) {
                address = "ingresar direccion";
            }
            stmt.setString(2, address);
            if (provider.getUser() == null) {
                throw new SQLException("El objeto Provider no tiene asignado un User.");
            }
            stmt.setInt(3, provider.getUser().getIdUser());
            stmt.setInt(4, provider.getIdProvider());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return provider;
            } else {
                throw new SQLException("No se pudo actualizar el proveedor con id " + provider.getIdProvider());
            }
        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_provider = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
