package dao;

import connection.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que implementa GenericDAO con la lógica genérica común.
 * Proporciona implementación genérica para operaciones de búsqueda (findById, findAll)
 * y gestión de conexiones JDBC. Las subclases concretas deben implementar detalles específicos
 * para las operaciones de guardado (save), actualización (update) y eliminación (delete).
 *
 * @param <T> Tipo de la entidad manejada por el DAO.
 */
public abstract class AbstractDAO<T> implements GenericDAO<T> {

    /**
     * Método que debe proporcionar el nombre de la tabla asociada a la entidad T.
     *
     * @return nombre de la tabla en la base de datos.
     */
    protected abstract String getTableName();

    /**
     * Método que debe mapear un ResultSet a una instancia de la entidad T.
     *
     * @param rs ResultSet obtenido de una consulta.
     * @return una nueva instancia de T con los datos del ResultSet.
     * @throws SQLException si ocurre un error al leer el ResultSet.
     */
    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    @Override
    public T findById(int id) throws SQLException {
        T entity = null;
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
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

    @Override
    public List<T> findAll() throws SQLException {
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try (Connection conn = DataBase.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T entity = mapResultSetToEntity(rs);
                lista.add(entity);
            }
        }
        return lista;
    }

    /**
     * Guarda una nueva entidad en la base de datos y retorna la entidad con el ID asignado.
     * La implementación concreta dependerá de cada DAO.
     */
    @Override
    public abstract T create(T entity) throws SQLException;

    /**
     * Actualiza una entidad existente en la base de datos y retorna la entidad actualizada.
     * La implementación concreta dependerá de cada DAO.
     */
    @Override
    public abstract T update(T entity) throws SQLException;

    /**
     * Elimina una entidad de la base de datos por su identificador.
     */
    @Override
    public abstract boolean delete(int id) throws SQLException;
}
