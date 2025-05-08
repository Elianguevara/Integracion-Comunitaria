package dao;

import connection.DataBase;
import model.*;

import java.sql.*;

public class CustomerDAO extends AbstractDAO<Customer> {

    @Override
    protected String getTableName() {
        return "customer"; // Nombre de la tabla asociada a Customer
    }

    @Override
    protected Customer mapResultSetToEntity(ResultSet rs) throws SQLException {
        Customer customer = new Customer();

        // Campos obligatorios
        customer.setIdCustomer(rs.getInt("id_customer"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));

        // Otros campos que se usan en la actualización:
        customer.setPhone(rs.getString("phone"));
        customer.setAddress(rs.getString("adress")); // Nota: en la BD se llama "adress"

        // Mapear el género (si existe)
        int genderId = rs.getInt("id_gender_type");
        if (!rs.wasNull()) {
            Gender gender = new Gender();
            gender.setIdGender(genderId);
            // Si en la consulta se trae también el nombre del género, por ejemplo en una unión,
            // podrías hacer: gender.setGender(rs.getString("gender"));
            customer.setGender(gender);
        }

        // Mapear la ciudad (si existe)
        int cityId = rs.getInt("id_city");
        if (!rs.wasNull()) {
            // Se asume que tienes una entidad City con al menos el id.
            City city = new City();
            city.setIdCity(cityId);
            // Opcional: city.setName(rs.getString("city"));
            customer.setCity(city);
        }

        // Mapear campos de auditoría (si existen)
        customer.setDateUpdate(rs.getTimestamp("date_update"));
        customer.setDateCreate(rs.getTimestamp("date_create"));

        // Otros campos opcionales:
        int dni = rs.getInt("dni");
        if (!rs.wasNull()) {
            customer.setDni(dni);
        }
        customer.setGpsLat(rs.getString("gps_lat"));
        customer.setGpsLon(rs.getString("gps_lon"));

        // Fecha de nacimiento (date_year)
        customer.setDateYear(rs.getTimestamp("date_year"));

        // Mapear el usuario propietario (id_user)
        int userId = rs.getInt("id_user");
        if (!rs.wasNull()) {
            User user = new User();
            user.setIdUser(userId);
            customer.setIdUser(user);
        }

        return customer;
    }


    @Override
    public Customer create(Customer customer) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " (name, email, id_user) VALUES (?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            // Asegurarse de que el Customer tenga asignado un User
            if (customer.getIdUser() == null) {
                throw new SQLException("El objeto Customer no tiene asignado un User.");
            }
            stmt.setInt(3, customer.getIdUser().getIdUser());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error al insertar el cliente, no se afectaron filas.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setIdCustomer(generatedKeys.getInt(1));
                }
            }
            return customer;
        }
    }


    @Override
    public Customer update(Customer customer) throws SQLException {
        // Validar que el Customer tenga un id válido.
        if (customer.getIdCustomer() == null) {
            throw new SQLException("No se puede actualizar un Customer sin id_customer.");
        }
        // Validar que se tenga asignado el usuario logueado (el mismo que actualizará)
        if (customer.getIdUser() == null) {
            throw new SQLException("El objeto Customer no tiene asignado un User.");
        }

        // Actualizamos únicamente el usuario actualizador y la fecha de actualización.
        Timestamp now = new Timestamp(System.currentTimeMillis());
        customer.setIdUserUpdate(customer.getIdUser());
        customer.setDateUpdate(now);

        // Se actualizan todos los datos del formulario excepto la ciudad (id_city)
        String sql = "UPDATE " + getTableName() + " SET "
                + "name = ?, "               // Nombre
                + "email = ?, "              // Email
                + "phone = ?, "              // Teléfono
                + "adress = ?, "             // Dirección (nota: en la BD se llama 'adress')
                + "id_gender_type = ?, "      // Género
                + "dni = ?, "                // DNI
                + "date_year = ?, "          // Fecha de Nacimiento
                + "id_user_update = ?, "     // Usuario que actualiza
                + "date_update = ? "         // Fecha de actualización
                + "WHERE id_customer = ?";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 1) Nombre
            stmt.setString(1, customer.getName());
            // 2) Email
            stmt.setString(2, customer.getEmail());
            // 3) Teléfono
            stmt.setString(3, customer.getPhone());
            // 4) Dirección
            stmt.setString(4, customer.getAddress());
            // 5) Género (id_gender_type)
            if (customer.getGender() != null && customer.getGender().getIdGender() != null) {
                stmt.setInt(5, customer.getGender().getIdGender());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            // 6) DNI
            if (customer.getDni() != null) {
                stmt.setInt(6, customer.getDni());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            // 7) Fecha de Nacimiento (date_year)
            if (customer.getDateYear() != null) {
                stmt.setTimestamp(7, customer.getDateYear());
            } else {
                stmt.setNull(7, Types.TIMESTAMP);
            }
            // 8) Usuario actualizador (id_user_update)
            stmt.setInt(8, customer.getIdUserUpdate().getIdUser());
            // 9) Fecha de actualización
            stmt.setTimestamp(9, customer.getDateUpdate());
            // 10) Condición WHERE: id_customer
            stmt.setInt(10, customer.getIdCustomer());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return customer;
            } else {
                throw new SQLException("No se pudo actualizar el Customer con id " + customer.getIdCustomer());
            }
        }
    }






    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id_customer = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    /**
     * Override porque nuestra PK es 'id_profile', no 'id'.
     */
    @Override
    public Customer findById(int id) throws SQLException {
        Customer entity = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE id_customer = ?";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entity = mapResultSetToEntity(rs);
                    // Si deseas mapear más campos manualmente (por ej. gpsLat, gpsLon), hazlo aquí o en mapResultSetToEntity.
                }
            }
        }
        return entity;
    }


    public Customer findByUserId(User user) throws SQLException {
        // Prepara la sentencia SELECT para buscar por id_user
        String sql = "SELECT * FROM " + getTableName() + " WHERE id_user = ?";
        Customer customer = null;

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Se asume que 'user.getIdUser()' retorna el identificador (int) del usuario
            stmt.setInt(1, user.getIdUser());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Reutiliza el método mapResultSetToEntity(rs) para mapear las columnas
                    customer = mapResultSetToEntity(rs);

                    // Si necesitas mapear más campos que no cubres en mapResultSetToEntity(...),
                    // puedes hacerlo aquí, por ejemplo:
                    // customer.setGpsLat(rs.getString("gps_lat"));
                    // customer.setGpsLon(rs.getString("gps_lon"));
                    // ...
                }
            }
        }

        return customer; // retornará null si no encontró ningún registro
    }

}

