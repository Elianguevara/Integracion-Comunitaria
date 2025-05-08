package service;

import dao.TypePetitionDAO;
import model.TypePetition;
import java.sql.SQLException;
import java.util.List;

/**
 * Servicio específico para la entidad TypePetition.
 */
public class TypePetitionService extends AbstractService<TypePetition> {

    private TypePetitionDAO typePetitionDAO;

    public TypePetitionService() {
        super(new TypePetitionDAO());
        this.typePetitionDAO = (TypePetitionDAO) this.dao;
    }

    /**
     * Crea un nuevo TypePetition en la base de datos y retorna la entidad con su ID.
     */
    public TypePetition createTypePetition(TypePetition typePetition) throws ServiceException {
        try {
            return typePetitionDAO.create(typePetition);
        } catch (SQLException ex) {
            throw new ServiceException("Error al crear el TypePetition", ex);
        }
    }

    /**
     * Actualiza un TypePetition existente.
     */
    public TypePetition updateTypePetition(TypePetition typePetition) throws ServiceException {
        try {
            return typePetitionDAO.update(typePetition);
        } catch (SQLException ex) {
            throw new ServiceException("Error al actualizar el TypePetition", ex);
        }
    }

    /**
     * Elimina un TypePetition por su ID.
     */
    public void deleteTypePetition(int id) throws ServiceException {
        try {
            if (!typePetitionDAO.delete(id)) {
                throw new ServiceException("No se pudo eliminar el TypePetition con id " + id);
            }
        } catch (SQLException ex) {
            throw new ServiceException("Error al eliminar el TypePetition", ex);
        }
    }

    /**
     * Busca un TypePetition por su ID.
     */
    public TypePetition getTypePetitionById(int id) throws ServiceException {
        try {
            return typePetitionDAO.findById(id);
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener el TypePetition con id " + id, ex);
        }
    }

    /**
     * Retorna todos los TypePetition de la base de datos.
     */
    public List<TypePetition> getAllTypePetitions() throws ServiceException {
        try {
            return typePetitionDAO.findAll();
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener la lista de TypePetition", ex);
        }
    }

    // Métodos de la interfaz AbstractService
    @Override
    public TypePetition save(TypePetition entity) throws ServiceException {
        return createTypePetition(entity);
    }

    @Override
    public TypePetition update(TypePetition entity) throws ServiceException {
        return updateTypePetition(entity);
    }

    @Override
    public boolean delete(int id) throws ServiceException {
        deleteTypePetition(id);
        return true;
    }

    @Override
    public TypePetition findById(int id) throws ServiceException {
        return getTypePetitionById(id);
    }

    @Override
    public List<TypePetition> findAll() throws ServiceException {
        return getAllTypePetitions();
    }
}
