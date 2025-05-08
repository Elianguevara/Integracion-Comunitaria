package service;

import dao.TypeProviderDAO;
import model.TypeProvider;

public class TypeProviderService extends AbstractService<TypeProvider> {

    public TypeProviderService() {
        super(new TypeProviderDAO());
    }

    @Override
    public TypeProvider save(TypeProvider entity) throws ServiceException {
        if (entity.getType() == null || entity.getType().trim().isEmpty()) {
            throw new ServiceException("El tipo de proveedor es obligatorio.");
        }
        return super.save(entity);
    }

    @Override
    public TypeProvider update(TypeProvider entity) throws ServiceException {
        if (entity.getIdTypeProvider() == null) {
            throw new ServiceException("No se puede actualizar un tipo de proveedor sin ID.");
        }
        return super.update(entity);
    }
}
