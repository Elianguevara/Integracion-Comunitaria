package service;

import java.util.List;

/**
 * Interfaz genérica de servicio que define operaciones CRUD básicas para una entidad.
 * @param <T> el tipo de la entidad que manejará el servicio.
 */
public interface GenericService<T> {
    /**
     * Guarda una nueva entidad en el sistema.
     * @param entity la entidad a guardar.
     * @return la entidad guardada (posiblemente con su ID asignado).
     * @throws ServiceException si ocurre un error durante la operación de guardado.
     */
    T save(T entity) throws ServiceException;

    /**
     * Actualiza una entidad existente.
     * @param entity la entidad con datos actualizados.
     * @return la entidad actualizada.
     * @throws ServiceException si ocurre un error durante la operación de actualización.
     */
    T update(T entity) throws ServiceException;

    /**
     * Elimina una entidad por su identificador.
     * @param id identificador de la entidad a eliminar.
     * @return true si la entidad fue eliminada, false en caso contrario.
     * @throws ServiceException si ocurre un error durante la operación de borrado.
     */
    boolean delete(int id) throws ServiceException;

    /**
     * Busca una entidad por su identificador.
     * @param id identificador de la entidad.
     * @return la entidad encontrada, o null si no existe.
     * @throws ServiceException si ocurre un error durante la operación de búsqueda.
     */
    T findById(int id) throws ServiceException;

    /**
     * Recupera todas las entidades del tipo manejado.
     * @return una lista de todas las entidades.
     * @throws ServiceException si ocurre un error durante la operación de listado.
     */
    List<T> findAll() throws ServiceException;
}
