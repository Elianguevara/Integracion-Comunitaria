package dao;

import connection.DataBase;
import model.City;
import model.Department;
import model.User;

import java.sql.*;

/**
 * DAO concreto para manejar operaciones CRUD de la entidad City.
 */
public class CityDAO extends AbstractDAO<City> {

    @Override
    protected String getTableName() {
        // Nombre exacto de la tabla en la BD
        return "city";
    }

    @Override
    protected City mapResultSetToEntity(ResultSet rs) throws SQLException {
        City city = new City();

        // Mapear columnas
        city.setIdCity(rs.getInt("id_city"));
        city.setName(rs.getString("name"));

        // Mapeo del departamento
        Department department = new Department();
        department.setIdDepartment(rs.getInt("id_department"));
        city.setDepartment(department);

        User user=new User();


        city.setPostalCode(rs.getString("postal_code"));

        city.setDateCreate(rs.getTimestamp("date_create"));
        city.setDateUpdate(rs.getTimestamp("date_update"));

        return city;
    }

    @Override
    public City create(City city) throws SQLException {
        // Validar que City tenga asignado un Department
        if (city.getDepartment() == null || city.getDepartment().getIdDepartment() == null) {
            throw new SQLException("El objeto City no tiene asignado un Department.");
        }
        String sql = "INSERT INTO " + getTableName() +
                " (name, id_department, postal_code, id_user_create, date_create) " +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, city.getName());
            stmt.setInt(2, city.getDepartment().getIdDepartment());
            stmt.setString(3, city.getPostalCode());
            stmt.setInt(4, city.getIdUserCreate() != null ? city.getIdUserCreate().getIdUser() : 0);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(5, city.getDateCreate() != null ? city.getDateCreate() : now);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al insertar la ciudad, no se afectaron filas.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setIdCity(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al crear la ciudad, no se obtuvo el ID.");
                }
            }
        }
        return city;
    }

    @Override
    public City update(City city) throws SQLException {
        // Validar que City tenga un id válido.
        if (city.getIdCity() == null) {
            throw new SQLException("No se puede actualizar una City sin id_city.");
        }
        // Actualizar los campos modificables.
        String sql = "UPDATE " + getTableName() +
                " SET name = ?, id_department = ?, postal_code = ?, " +
                "     id_user_update = ?, date_update = ? " +
                " WHERE id_city = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, city.getName());
            stmt.setInt(2, city.getDepartment().getIdDepartment());
            stmt.setString(3, city.getPostalCode());
            stmt.setInt(4, city.getIdUserUpdate() != null ? city.getIdUserUpdate().getIdUser() : 0);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            stmt.setTimestamp(5, city.getDateUpdate() != null ? city.getDateUpdate() : now);
            stmt.setInt(6, city.getIdCity());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar la ciudad con id " + city.getIdCity());
            }
        }
        return city;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_city = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    @Override
    public City findById(int id) throws SQLException {
        City entity = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE id_city = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entity = mapResultSetToEntity(rs);
                }
            }
        }
        return entity;
    }
}
