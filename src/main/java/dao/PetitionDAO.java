package dao;

import connection.DataBase;
import model.Petition;
import model.TypePetition;
import model.User;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto para la entidad Petition.
 */
public class PetitionDAO extends AbstractDAO<Petition> {

    @Override
    protected String getTableName() {
        return "petition";
    }

    /**
     * Mapea un ResultSet a una instancia de Petition (usa alias "type_name" para el nombre de TypePetition).
     * Aquí normalmente NO viene el "customer_name".
     */
    @Override
    protected Petition mapResultSetToEntity(ResultSet rs) throws SQLException {
        Petition petition = new Petition();

        petition.setIdPetition(rs.getInt("id_petition"));
        petition.setDescription(rs.getString("description"));
        petition.setDateSince(rs.getDate("date_since"));
        petition.setDateUntil(rs.getDate("date_until"));
        petition.setDateCreate(rs.getTimestamp("date_create"));
        petition.setDateUpdate(rs.getTimestamp("date_update"));

        // Cargar Customer (solo id si no viene la columna "customer_name")
        Customer customer = new Customer();
        customer.setIdCustomer(rs.getInt("id_customer"));
        petition.setCustomer(customer);

        // Auditoría
        User userCreate = new User();
        userCreate.setIdUser(rs.getInt("id_user_create"));
        petition.setIdUserCreate(userCreate);

        User userUpdate = new User();
        userUpdate.setIdUser(rs.getInt("id_user_update"));
        petition.setIdUserUpdate(userUpdate);

        // TypePetition con alias "type_name"
        TypePetition tp = new TypePetition();
        tp.setIdTypePetition(rs.getInt("id_type_petition"));
        tp.setName(rs.getString("type_name"));
        petition.setTypePetition(tp);

        return petition;
    }

    /**
     * Mapea un ResultSet a una instancia de Petition **incluyendo** el nombre del customer ("customer_name").
     */
    private Petition mapResultSetToEntityWithCustomer(ResultSet rs) throws SQLException {
        Petition petition = new Petition();

        petition.setIdPetition(rs.getInt("id_petition"));
        petition.setDescription(rs.getString("description"));
        petition.setDateSince(rs.getDate("date_since"));
        petition.setDateUntil(rs.getDate("date_until"));
        petition.setDateCreate(rs.getTimestamp("date_create"));
        petition.setDateUpdate(rs.getTimestamp("date_update"));

        // Aquí sí cargamos el nombre del customer
        Customer customer = new Customer();
        customer.setIdCustomer(rs.getInt("id_customer"));
        customer.setName(rs.getString("customer_name"));
        petition.setCustomer(customer);

        // Auditoría
        User userCreate = new User();
        userCreate.setIdUser(rs.getInt("id_user_create"));
        petition.setIdUserCreate(userCreate);

        User userUpdate = new User();
        userUpdate.setIdUser(rs.getInt("id_user_update"));
        petition.setIdUserUpdate(userUpdate);

        // TypePetition con alias "type_name"
        TypePetition tp = new TypePetition();
        tp.setIdTypePetition(rs.getInt("id_type_petition"));
        tp.setName(rs.getString("type_name"));
        petition.setTypePetition(tp);

        return petition;
    }

    /**
     * Inserta una nueva Petition en la base de datos.
     * Retorna la entidad con el ID generado (id_petition) ya seteado.
     */
    @Override
    public Petition create(Petition entity) throws SQLException {
        String sql = "INSERT INTO petition "
                + "(id_type_petition, description, date_since, date_until, "
                + "id_user_create, id_user_update, date_create, date_update, id_customer) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, entity.getTypePetition() != null ? entity.getTypePetition().getIdTypePetition() : 0);
            stmt.setString(2, entity.getDescription());
            stmt.setDate(3, entity.getDateSince());
            stmt.setDate(4, entity.getDateUntil());
            stmt.setInt(5, entity.getIdUserCreate() != null ? entity.getIdUserCreate().getIdUser() : 0);
            stmt.setInt(6, entity.getIdUserUpdate() != null ? entity.getIdUserUpdate().getIdUser() : 0);
            stmt.setTimestamp(7, entity.getDateCreate());
            stmt.setTimestamp(8, entity.getDateUpdate());
            stmt.setInt(9, entity.getCustomer() != null ? entity.getCustomer().getIdCustomer() : 0);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setIdPetition(generatedKeys.getInt(1));
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Actualiza una Petition existente en la base de datos.
     * Retorna la entidad con los datos actualizados.
     */
    @Override
    public Petition update(Petition entity) throws SQLException {
        String sql = "UPDATE petition SET "
                + "id_type_petition = ?, "
                + "description = ?, "
                + "date_since = ?, "
                + "date_until = ?, "
                + "id_user_create = ?, "
                + "id_user_update = ?, "
                + "date_create = ?, "
                + "date_update = ?, "
                + "id_customer = ? "
                + "WHERE id_petition = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entity.getTypePetition() != null ? entity.getTypePetition().getIdTypePetition() : 0);
            stmt.setString(2, entity.getDescription());
            stmt.setDate(3, entity.getDateSince());
            stmt.setDate(4, entity.getDateUntil());
            stmt.setInt(5, entity.getIdUserCreate() != null ? entity.getIdUserCreate().getIdUser() : 0);
            stmt.setInt(6, entity.getIdUserUpdate() != null ? entity.getIdUserUpdate().getIdUser() : 0);
            stmt.setTimestamp(7, entity.getDateCreate());
            stmt.setTimestamp(8, entity.getDateUpdate());
            stmt.setInt(9, entity.getCustomer() != null ? entity.getCustomer().getIdCustomer() : 0);
            stmt.setInt(10, entity.getIdPetition());

            stmt.executeUpdate();
        }
        return entity;
    }

    /**
     * Elimina una Petition de la base de datos por su ID.
     * Retorna true si se eliminó al menos un registro.
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM petition WHERE id_petition = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Busca una Petition por su ID.
     */
    @Override
    public Petition findById(int id) throws SQLException {
        Petition entity = null;
        String sql = "SELECT * FROM petition WHERE id_petition = ?";
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

    /**
     * Retorna una lista de todas las peticiones ordenadas por fecha de creación (desc).
     * (Este método no obtiene "customer_name".)
     */
    @Override
    public List<Petition> findAll() throws SQLException {
        List<Petition> list = new ArrayList<>();
        String sql = "SELECT p.*, t.name as type_name "
                + "FROM petition p "
                + "JOIN type_petition t ON p.id_type_petition = t.id_type_petition "
                + "ORDER BY p.date_create DESC";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        }
        return list;
    }

    /**
     * Retorna una lista de peticiones asociadas a un cliente específico, ordenadas desc.
     */
    public List<Petition> findByCustomerId(int customerId) throws SQLException {
        List<Petition> petitions = new ArrayList<>();
        String sql = "SELECT p.*, t.name as type_name "
                + "FROM petition p "
                + "JOIN type_petition t ON p.id_type_petition = t.id_type_petition "
                + "WHERE p.id_customer = ? "
                + "ORDER BY p.date_create DESC";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    petitions.add(mapResultSetToEntity(rs));
                }
            }
        }
        return petitions;
    }

    /**
     * Retorna TODAS las peticiones con JOIN a Customer y TypePetition,
     * incluyendo "c.name AS customer_name", ordenadas desc.
     */
    public List<Petition> findAllWithCustomers() throws SQLException {
        List<Petition> list = new ArrayList<>();
        String sql = "SELECT p.*, t.name AS type_name, c.name AS customer_name " +
                "FROM petition p " +
                "JOIN type_petition t ON p.id_type_petition = t.id_type_petition " +
                "JOIN customer c ON p.id_customer = c.id_customer " +
                "ORDER BY p.date_create DESC";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEntityWithCustomer(rs));
            }
        }
        return list;
    }
}
