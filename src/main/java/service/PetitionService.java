package service;

import dao.PetitionDAO;
import model.Petition;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio específico para la entidad Petition.
 */
public class PetitionService extends AbstractService<Petition> {

    private final PetitionDAO petitionDAO;

    public PetitionService() {
        super(new PetitionDAO());
        this.petitionDAO = (PetitionDAO) this.dao;
    }

    public Petition createPetition(Petition petition) throws ServiceException {
        try {
            return petitionDAO.create(petition);
        } catch (SQLException ex) {
            throw new ServiceException("Error al crear la petición", ex);
        }
    }

    public Petition updatePetition(Petition petition) throws ServiceException {
        try {
            return petitionDAO.update(petition);
        } catch (SQLException ex) {
            throw new ServiceException("Error al actualizar la petición", ex);
        }
    }

    public void deletePetition(int petitionId) throws ServiceException {
        try {
            if (!petitionDAO.delete(petitionId)) {
                throw new ServiceException("No se pudo eliminar la petición con id " + petitionId);
            }
        } catch (SQLException ex) {
            throw new ServiceException("Error al eliminar la petición", ex);
        }
    }

    public Petition getPetitionById(int petitionId) throws ServiceException {
        try {
            return petitionDAO.findById(petitionId);
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener la petición con id " + petitionId, ex);
        }
    }

    public List<Petition> getAllPetitions() throws ServiceException {
        try {
            return petitionDAO.findAll();
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener la lista de peticiones", ex);
        }
    }

    public List<Petition> findByCustomerId(int customerId) throws ServiceException {
        try {
            return petitionDAO.findByCustomerId(customerId);
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener las peticiones del cliente", ex);
        }
    }

    /**
     * Obtiene todas las peticiones (todas, de todos los clientes) con JOIN a Customer,
     * para así tener también "customer_name" en el objeto 'Petition'.
     */
    public List<Petition> getAllPetitionsWithCustomers() throws ServiceException {
        try {
            return petitionDAO.findAllWithCustomers();
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener peticiones con info de clientes", ex);
        }
    }

    // Implementación de los métodos genéricos
    @Override
    public Petition save(Petition entity) throws ServiceException {
        return createPetition(entity);
    }

    @Override
    public Petition update(Petition entity) throws ServiceException {
        return updatePetition(entity);
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        deletePetition(id);
        return true;
    }

    @Override
    public Petition findById(int id) throws ServiceException {
        return getPetitionById(id);
    }

    @Override
    public List<Petition> findAll() throws ServiceException {
        return getAllPetitions();
    }
}
