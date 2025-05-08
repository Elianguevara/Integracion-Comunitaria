package service;

import dao.ProviderDAO;
import model.Provider;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio específico para la entidad Provider.
 * Reutiliza la funcionalidad CRUD de AbstractService y delega en ProviderDAO.
 */
public class ProviderService extends AbstractService<Provider> {

    // Instancia específica del DAO para Provider
    private ProviderDAO providerDAO;

    /**
     * Constructor que inicializa el servicio de Provider con su DAO específico.
     */
    public ProviderService() {
        super(new ProviderDAO());  // ProviderDAO es la implementación DAO para Provider
        this.providerDAO = (ProviderDAO) this.dao;
    }

    /**
     * Crea un nuevo proveedor y retorna la entidad con su ID asignado.
     * @param provider Objeto Provider con los datos a registrar.
     * @return El objeto Provider creado.
     * @throws ServiceException si ocurre un error durante la operación.
     */
    public Provider createProvider(Provider provider) throws ServiceException {
        try {
            return providerDAO.create(provider);
        } catch (SQLException ex) {
            throw new ServiceException("Error al crear el proveedor", ex);
        }
    }

    /**
     * Actualiza los datos de un proveedor existente y retorna la entidad actualizada.
     * @param provider Objeto Provider con los datos actualizados (debe contener un ID válido).
     * @return El objeto Provider actualizado.
     * @throws ServiceException si ocurre un error durante la operación.
     */
    public Provider updateProvider(Provider provider) throws ServiceException {
        try {
            return providerDAO.update(provider);
        } catch (SQLException ex) {
            throw new ServiceException("Error al actualizar el proveedor", ex);
        }
    }

    /**
     * Elimina un proveedor de la base de datos dado su ID.
     * @param providerId Identificador del proveedor a eliminar.
     * @throws ServiceException si ocurre un error durante la operación.
     */
    public void deleteProvider(int providerId) throws ServiceException {
        try {
            if (!providerDAO.delete(providerId)) {
                throw new ServiceException("No se pudo eliminar el proveedor con id " + providerId);
            }
        } catch (SQLException ex) {
            throw new ServiceException("Error al eliminar el proveedor", ex);
        }
    }

    /**
     * Busca y retorna un proveedor por su ID.
     * @param providerId Identificador del proveedor a buscar.
     * @return Objeto Provider si se encuentra, o null de lo contrario.
     * @throws ServiceException si ocurre un error durante la búsqueda.
     */
    public Provider getProviderById(int providerId) throws ServiceException {
        try {
            return providerDAO.findById(providerId);
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener el proveedor con id " + providerId, ex);
        }
    }

    /**
     * Retorna una lista de todos los proveedores.
     * @return Lista de objetos Provider.
     * @throws ServiceException si ocurre un error durante la operación.
     */
    public List<Provider> getAllProviders() throws ServiceException {
        try {
            return providerDAO.findAll();
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener la lista de proveedores", ex);
        }
    }
}
