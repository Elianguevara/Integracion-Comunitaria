package service;

import dao.CityDAO;
import model.City;

import java.util.List;

/**
 * Servicio concreto para la entidad City.
 */
public class CityService extends AbstractService<City> {

    // Referencia al DAO específico de City
    private final CityDAO cityDAO;

    /**
     * Constructor que inicializa la superclase con el DAO específico de City.
     */
    public CityService() {
        super(new CityDAO());
        this.cityDAO = (CityDAO) this.dao;
    }

    /**
     * Ejemplo de sobreescritura del método save con validaciones adicionales.
     */
    @Override
    public City save(City entity) throws ServiceException {
        // Validación de ejemplo
        if (entity.getName() == null || entity.getName().isEmpty()) {
            throw new ServiceException("El nombre de la ciudad es obligatorio.");
        }
        // Delegamos la lógica principal a la clase padre (AbstractService)
        return super.save(entity);
    }

    /**
     * Ejemplo de sobreescritura del método update con validaciones adicionales.
     */
    @Override
    public City update(City entity) throws ServiceException {
        // Validación de ejemplo
        if (entity.getIdCity() == null) {
            throw new ServiceException("No se puede actualizar una City sin ID.");
        }
        return super.update(entity);
    }

    /**
     * Ejemplo de sobreescritura del método delete con validaciones adicionales.
     */
    @Override
    public boolean delete(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("ID inválido para eliminar City.");
        }
        return super.delete(id);
    }

    /**
     * Reutilizamos el método findById de la superclase, pero
     * podríamos añadir lógica adicional si fuera necesario.
     */
    @Override
    public City findById(int id) throws ServiceException {
        return super.findById(id);
    }

    /**
     * Reutilizamos el método findAll de la superclase, pero
     * podríamos añadir lógica adicional si fuera necesario.
     */
    @Override
    public List<City> findAll() throws ServiceException {
        return super.findAll();
    }

    /**
     * Ejemplo de método adicional específico para City.
     * No está implementado en el AbstractDAO, así que se haría una consulta directa
     * o se crearía un método en CityDAO para soportar esta operación.
     */
    public List<City> findByDepartmentId(int departmentId) throws ServiceException {
        // Podrías implementar un método en CityDAO, por ejemplo:
        // return cityDAO.findByDepartmentId(departmentId);
        throw new ServiceException("Método no implementado.");
    }
}
