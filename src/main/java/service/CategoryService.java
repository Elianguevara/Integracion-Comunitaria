package service;

import dao.CategoryDAO;
import model.Category;

public class CategoryService extends AbstractService<Category> {

    public CategoryService() {
        super(new CategoryDAO());
    }

    @Override
    public Category save(Category entity) throws ServiceException {
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            throw new ServiceException("El nombre de la categoría es obligatorio.");
        }
        return super.save(entity);
    }

    @Override
    public Category update(Category entity) throws ServiceException {
        if (entity.getIdCategory() == null) {
            throw new ServiceException("No se puede actualizar una categoría sin ID.");
        }
        return super.update(entity);
    }
}
