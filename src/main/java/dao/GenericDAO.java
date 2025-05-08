package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz genérica DAO que define operaciones CRUD básicas para una entidad.
 * @param <T> Tipo de la entidad manejada por este DAO.
 */
public interface GenericDAO<T> {
    /**
     * Guarda una nueva entidad en la base de datos y la retorna con el ID asignado.
     * @param entity la entidad a guardar.
     * @return la entidad guardada (con su ID asignado).
     * @throws SQLException en caso de error al acceder a la base de datos.
     */
    T create(T entity) throws SQLException;

    /**
     * Actualiza una entidad existente en la base de datos y retorna la entidad actualizada.
     * @param entity la entidad con los nuevos datos.
     * @return la entidad actualizada.
     * @throws SQLException en caso de error al acceder a la base de datos.
     */
    T update(T entity) throws SQLException;

    /**
     * Elimina una entidad de la base de datos por su identificador.
     * @param id el identificador de la entidad a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws SQLException en caso de error al acceder a la base de datos.
     */
    boolean delete(int id) throws SQLException;

    /**
     * Busca una entidad por su identificador.
     * @param id el identificador de la entidad.
     * @return la entidad encontrada o null si no se encuentra.
     * @throws SQLException en caso de error al acceder a la base de datos.
     */
    T findById(int id) throws SQLException;

    /**
     * Obtiene todas las entidades del tipo T desde la base de datos.
     * @return una lista de todas las entidades.
     * @throws SQLException en caso de error al acceder a la base de datos.
     */
    List<T> findAll() throws SQLException;
}
