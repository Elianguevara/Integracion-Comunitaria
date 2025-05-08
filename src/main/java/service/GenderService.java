package service;

import dao.GenderDAO;
import model.Gender;
import java.sql.SQLException;
import java.util.List;
/**
 * Servicio para la entidad Gender.
 * Utiliza las operaciones CRUD definidas en AbstractService y delega en GenderDAO.
 */
public class GenderService extends AbstractService<Gender>  {

    private GenderDAO genderDAO;
    /**
     * Constructor que inicializa el servicio con el DAO correspondiente.
     */
    public GenderService() {
        super(new GenderDAO());
    }

    public Gender createGender(Gender gender) throws SQLException {
        return genderDAO.create(gender);
    }

    public Gender updateGender(Gender gender) throws SQLException {
        return genderDAO.update(gender);
    }

    public boolean deleteGender(int idGender) throws SQLException {
        return genderDAO.delete(idGender);
    }

    public Gender getGenderById(int idGender) throws SQLException {
        return genderDAO.findById(idGender);
    }

    public List<Gender> getAllGenders() throws SQLException {
        return genderDAO.findAll();
    }
}
