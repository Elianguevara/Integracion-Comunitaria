package dao;

import connection.DataBase;
import model.Postulation;
import model.Petition;
import model.Provider;
import model.User;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class PostulationDAO extends AbstractDAO<Postulation> {

    @Override
    protected String getTableName() {
        return "postulation"; // Nombre de la tabla en la base de datos
    }

    @Override
    protected Postulation mapResultSetToEntity(ResultSet rs) throws SQLException {
        Postulation post = new Postulation();
        post.setIdPostulation(rs.getInt("idpostulation"));

        // 1) Petición
        Petition petition = new Petition();
        petition.setIdPetition(rs.getInt("id_petition"));
        post.setPetition(petition);

        // 2) Provider
        Provider provider = new Provider();
        provider.setIdProvider(rs.getInt("id_provider"));
        post.setProvider(provider);

        // 3) Campos simples
        post.setWinner(rs.getString("winner"));    // "YES"/"NO" u otro valor
        post.setProposal(rs.getString("proposal"));
        post.setCost(rs.getInt("cost"));
        post.setIdState(rs.getString("id_state"));
        post.setState(rs.getString("state"));
        post.setCurrent(rs.getString("current"));  // "YES"/"NO" u otro valor

        // 4) Auditoría: id_user_create, id_user_update
        int userCreateId = rs.getInt("id_user_create");
        if (!rs.wasNull()) {
            User userCreate = new User();
            userCreate.setIdUser(userCreateId);
            post.setIdUserCreate(userCreate);
        }

        int userUpdateId = rs.getInt("id_user_update");
        if (!rs.wasNull()) {
            User userUpdate = new User();
            userUpdate.setIdUser(userUpdateId);
            post.setIdUserUpdate(userUpdate);
        }

        // 5) Fechas de creación y actualización
        post.setDateCreate(rs.getTimestamp("date_create"));
        post.setDateUpdate(rs.getTimestamp("date_update"));

        return post;
    }

    @Override
    public Postulation create(Postulation entity) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " ("
                + "id_petition, id_provider, winner, proposal, cost, "
                + "id_state, state, current, "
                + "id_user_create, id_user_update, date_create, date_update"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // id_petition
            stmt.setInt(1, (entity.getPetition() != null) ? entity.getPetition().getIdPetition() : 0);

            // id_provider
            stmt.setInt(2, (entity.getProvider() != null) ? entity.getProvider().getIdProvider() : 0);

            // winner, proposal, cost, id_state, state, current
            stmt.setString(3, entity.getWinner());
            stmt.setString(4, entity.getProposal());
            stmt.setInt(5, entity.getCost());
            stmt.setString(6, entity.getIdState());
            stmt.setString(7, entity.getState());
            stmt.setString(8, entity.getCurrent());

            // id_user_create, id_user_update
            stmt.setInt(9, (entity.getIdUserCreate() != null) ? entity.getIdUserCreate().getIdUser() : 0);
            stmt.setInt(10, (entity.getIdUserUpdate() != null) ? entity.getIdUserUpdate().getIdUser() : 0);

            // date_create, date_update (si no vienen, usamos la fecha actual)
            stmt.setTimestamp(11, (entity.getDateCreate() != null) ? entity.getDateCreate() : new Timestamp(System.currentTimeMillis()));
            stmt.setTimestamp(12, (entity.getDateUpdate() != null) ? entity.getDateUpdate() : new Timestamp(System.currentTimeMillis()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        entity.setIdPostulation(rs.getInt(1));
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public Postulation update(Postulation entity) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET "
                + "id_petition = ?, "
                + "id_provider = ?, "
                + "winner = ?, "
                + "proposal = ?, "
                + "cost = ?, "
                + "id_state = ?, "
                + "state = ?, "
                + "current = ?, "
                + "id_user_update = ?, "
                + "date_update = ? "
                + "WHERE idpostulation = ?";

        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, (entity.getPetition() != null) ? entity.getPetition().getIdPetition() : 0);
            stmt.setInt(2, (entity.getProvider() != null) ? entity.getProvider().getIdProvider() : 0);
            stmt.setString(3, entity.getWinner());
            stmt.setString(4, entity.getProposal());
            stmt.setInt(5, entity.getCost());
            stmt.setString(6, entity.getIdState());
            stmt.setString(7, entity.getState());
            stmt.setString(8, entity.getCurrent());

            // Se asume que solo se actualiza id_user_update, no id_user_create
            stmt.setInt(9, (entity.getIdUserUpdate() != null) ? entity.getIdUserUpdate().getIdUser() : 0);

            // Actualizamos la fecha de modificación
            stmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));

            // Filtro por PK
            stmt.setInt(11, entity.getIdPostulation());

            stmt.executeUpdate();
        }
        return entity;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE idpostulation = ?";
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
}
