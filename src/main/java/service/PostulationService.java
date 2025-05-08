package service;

import dao.PostulationDAO;
import model.Postulation;

public class PostulationService extends AbstractService<Postulation> {

    public PostulationService() {
        super(new PostulationDAO());
    }

    // Aquí puedes agregar métodos de negocio específicos para Postulation, si es necesario.
}
