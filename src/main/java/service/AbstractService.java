package service;

import java.util.List;
import dao.GenericDAO;  // Importamos la interfaz genérica de DAO

/**
 * Implementación abstracta de la interfaz GenericService.
 * Provee la lógica común utilizando un GenericDAO para realizar las operaciones CRUD.
 * @param <T> el tipo de entidad que maneja este servicio.
 */
public abstract class AbstractService<T> implements GenericService<T> {

    /** DAO genérico para realizar operaciones de persistencia. */
    protected GenericDAO<T> dao;

    /**
     * Constructor que recibe un DAO genérico para inicializar el servicio.
     * @param dao implementación de GenericDAO específica para la entidad T.
     */
    protected AbstractService(GenericDAO<T> dao) {
        this.dao = dao;
    }

    @Override
    public T save(T entity) throws ServiceException {
        try {
            // Delegamos la operación de guardado al DAO
            return dao.create(entity);
        } catch (Exception e) {
            // Gestionamos la excepción envolviéndola en ServiceException
            throw new ServiceException("Error al guardar la entidad " + entity.getClass().getSimpleName(), e);
        }
    }

    @Override
    public T update(T entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la entidad " + entity.getClass().getSimpleName(), e);
        }
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la entidad de id " + id, e);
        }
    }

    @Override
    public T findById(int id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            throw new ServiceException("Error al buscar la entidad de id " + id, e);
        }
    }

    @Override
    public List<T> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (Exception e) {
            throw new ServiceException("Error al listar las entidades de tipo " +
                    getClass().getSimpleName().replace("Service", ""), e);
        }
    }
}
