package dao;

import connection.DataBase;
import model.TypePetition;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO concreto para la entidad TypePetition.
 */
public class TypePetitionDAO extends AbstractDAO<TypePetition> {

    @Override
    protected String getTableName() {
        return "type_petition";
    }

    /**
     * Mapea un ResultSet a una instancia de TypePetition.
     */
    @Override
    protected TypePetition mapResultSetToEntity(ResultSet rs) throws SQLException {
        TypePetition typePetition = new TypePetition();

        // Campos de la tabla type_petition
        typePetition.setIdTypePetition(rs.getInt("id_type_petition"));
        typePetition.setName(rs.getString("name"));

        // Mapeo de campos de auditoría (BaseEntity)
        // Suponiendo que la tabla type_petition tiene id_user_create, id_user_update, date_create, date_update
        User userCreate = new User();
        userCreate.setIdUser(rs.getInt("id_user_create"));
        typePetition.setIdUserCreate(userCreate);

        User userUpdate = new User();
        userUpdate.setIdUser(rs.getInt("id_user_update"));
        typePetition.setIdUserUpdate(userUpdate);

        typePetition.setDateCreate(rs.getTimestamp("date_create"));
        typePetition.setDateUpdate(rs.getTimestamp("date_update"));

        return typePetition;
    }

    /**
     * Inserta un nuevo TypePetition en la base de datos y retorna la entidad con su ID generado.
     */
    @Override
    public TypePetition create(TypePetition entity) throws SQLException {
        String sql = "INSERT INTO type_petition "
                + "(name, id_user_create, id_user_update, date_create, date_update) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, entity.getName());
            // Si no manejas usuarios, podrías poner 0 o NULL
            stmt.setInt(2, entity.getIdUserCreate() != null ? entity.getIdUserCreate().getIdUser() : 0);
            stmt.setInt(3, entity.getIdUserUpdate() != null ? entity.getIdUserUpdate().getIdUser() : 0);
            stmt.setTimestamp(4, entity.getDateCreate());
            stmt.setTimestamp(5, entity.getDateUpdate());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Recuperar la clave primaria generada (id_type_petition)
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setIdTypePetition(generatedKeys.getInt(1));
                    }
                }
            }
        }
        return entity;
    }

    /**
     * Actualiza un TypePetition existente en la base de datos.
     */
    @Override
    public TypePetition update(TypePetition entity) throws SQLException {
        String sql = "UPDATE type_petition SET "
                + "name = ?, "
                + "id_user_create = ?, "
                + "id_user_update = ?, "
                + "date_create = ?, "
                + "date_update = ? "
                + "WHERE id_type_petition = ?";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getIdUserCreate() != null ? entity.getIdUserCreate().getIdUser() : 0);
            stmt.setInt(3, entity.getIdUserUpdate() != null ? entity.getIdUserUpdate().getIdUser() : 0);
            stmt.setTimestamp(4, entity.getDateCreate());
            stmt.setTimestamp(5, entity.getDateUpdate());
            stmt.setInt(6, entity.getIdTypePetition());

            stmt.executeUpdate();
        }
        return entity;
    }

    /**
     * Elimina un TypePetition por su ID.
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM type_petition WHERE id_type_petition = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Busca un TypePetition por su ID.
     */
    @Override
    public TypePetition findById(int id) throws SQLException {
        TypePetition typePetition = null;
        String sql = "SELECT * FROM type_petition WHERE id_type_petition = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    typePetition = mapResultSetToEntity(rs);
                }
            }
        }
        return typePetition;
    }

    /**
     * Retorna todos los registros de la tabla type_petition.
     */
    @Override
    public List<TypePetition> findAll() throws SQLException {
        List<TypePetition> list = new ArrayList<>();
        String sql = "SELECT * FROM type_petition";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToEntity(rs));
            }
        }
        return list;
    }
}
