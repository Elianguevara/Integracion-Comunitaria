package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

/**
 * Servicio concreto para la entidad Customer.
 */
public class CustomerService extends AbstractService<Customer> {

    // Referencia al DAO específico
    private final CustomerDAO customerDAO;

    /**
     * Constructor que inicializa la superclase con el DAO específico de Customer.
     */
    public CustomerService() {
        super(new CustomerDAO());
        this.customerDAO = (CustomerDAO) this.dao;
    }

    /**
     * Puedes añadir métodos adicionales propios de Customer,
     * o reescribir métodos de AbstractService con validaciones extra.
     */

    @Override
    public Customer save(Customer entity) throws ServiceException {
        // Validación de ejemplo
        if (entity.getName() == null || entity.getName().isEmpty()) {
            throw new ServiceException("El nombre del cliente es obligatorio.");
        }
        // Llamamos a la implementación genérica
        return super.save(entity);
    }

    @Override
    public Customer update(Customer entity) throws ServiceException {
        // Validación de ejemplo
        if (entity.getIdCustomer() == null) {
            throw new ServiceException("No se puede actualizar un Customer sin ID.");
        }
        return super.update(entity);
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("ID inválido para eliminar Customer.");
        }
        return super.delete(id);
    }

    public Customer findById(int id) throws ServiceException {
        return super.findById(id);
    }

    public List<Customer> findAll() throws ServiceException {
        return super.findAll();
    }

    /**
     * Podrías agregar un método adicional para buscar por email, etc.
     */
    public Customer findByEmail(String email) throws ServiceException {
        // Lógica adicional (ejemplo no implementado en AbstractDAO)
        throw new ServiceException("Método no implementado");
    }
}
