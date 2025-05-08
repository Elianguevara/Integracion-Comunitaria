package service;

import dao.ProfessionDAO;
import model.Profession;
import service.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessionService extends AbstractService<Profession> {

    public ProfessionService() {
        super(new ProfessionDAO());
    }

    @Override
    public Profession save(Profession entity) throws ServiceException {
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            throw new ServiceException("El nombre de la profesión es obligatorio.");
        }
        return super.save(entity);
    }

    @Override
    public Profession update(Profession entity) throws ServiceException {
        if (entity.getIdProfession() == null) {
            throw new ServiceException("No se puede actualizar una profesión sin ID.");
        }
        return super.update(entity);
    }

    /**
     * Retorna una lista de profesiones que pertenecen a la categoría especificada.
     * En este ejemplo se filtra en memoria usando findAll(); idealmente, se debería implementar
     * esta consulta en el DAO para mayor eficiencia.
     *
     * @param idCategory Identificador de la categoría.
     * @return Lista de profesiones asociadas a la categoría.
     * @throws ServiceException Si ocurre algún error durante la operación.
     */
    public List<Profession> findByCategory(int idCategory) throws ServiceException {
        try {
            List<Profession> allProfessions = super.findAll();
            return allProfessions.stream()
                    .filter(p -> p.getCategory() != null &&
                            p.getCategory().getIdCategory() != null &&
                            p.getCategory().getIdCategory() == idCategory)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Error al buscar profesiones por categoría", e);
        }
    }
}
