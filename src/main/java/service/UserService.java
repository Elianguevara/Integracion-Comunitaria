package service;

import dao.CustomerDAO;
import dao.ProviderDAO;
import dao.UserDAO;
import dao.UserProfileDAO;
import model.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Servicio específico para la entidad User.
 * Utiliza UserDAO para realizar operaciones CRUD y de autenticación.
 */
public class UserService extends AbstractService<User> {

    private UserDAO userDAO;

    public UserService() {
        super(new UserDAO());
        this.userDAO = (UserDAO) this.dao;
    }

    /**
     * Registra un nuevo usuario y retorna la entidad con su ID asignado.
     * Se valida que los datos del usuario sean correctos antes de persistir.
     */
    public User createUser(User user) throws ServiceException {
        // Validamos que el objeto sea correcto
        if (!user.isValidUser()) {
            if (user.getEmail() == null || !user.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                throw new ServiceException("El email no es válido.");
            }
            throw new ServiceException("Los datos del usuario no son válidos. Revise nombre, apellido, email y contraseña.");
        }
        try {
            User createdUser = userDAO.create(user);
            if (createdUser != null) {
                return createdUser;
            } else {
                throw new ServiceException("No se pudo registrar el usuario");
            }
        } catch (SQLException ex) {
            throw new ServiceException("Error al crear el usuario", ex);
        }
    }

    /**
     * Registra un nuevo usuario y crea su perfil asignándole el rol indicado.
     * Realiza validaciones para que:
     *  - El email no esté ya registrado.
     *  - La contraseña tenga más de 4 caracteres.
     *  - El nombre y el apellido no contengan números.
     *
     * @param name Nombre del usuario.
     * @param lastname Apellido del usuario.
     * @param email Email del usuario.
     * @param password Contraseña del usuario.
     * @param role Rol a asignar (por ejemplo, CLIENTE o PROVEEDOR).
     * @return El objeto User creado.
     * @throws ServiceException si ocurre un error durante el registro o si alguna validación falla.
     */
    public User registerUser(String name, String lastname, String email, String password, RoleType role) throws ServiceException {
        // Validar que el nombre solo contenga letras
        if (name == null || !name.matches("[\\p{L}]+(?: [\\p{L}]+)*")) {
            throw new ServiceException("El nombre debe contener solo letras.");
        }
        // Validar que el apellido solo contenga letras
        if (lastname == null || !lastname.matches("[\\p{L}]+(?: [\\p{L}]+)*")) {
            throw new ServiceException("El apellido debe contener solo letras.");
        }
        // Validar la longitud de la contraseña (mínimo 5 caracteres)
        if (password == null || password.length() < 4) {
            throw new ServiceException("La contraseña debe tener al menos 4 caracteres.");
        }
        // Validar que el email no esté ya registrado
        try {
            if (userDAO.findByEmail(email) != null) {
                throw new ServiceException("El email ya está registrado.");
            }
        } catch (SQLException e) {
            throw new ServiceException("Error al verificar el email existente.", e);
        }

        // Capitalizar: primera letra en mayúscula y el resto en minúscula para nombre y apellido
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        lastname = lastname.substring(0, 1).toUpperCase() + lastname.substring(1).toLowerCase();

        // Crear el usuario
        User newUser = new User(name, lastname, email, password);
        User createdUser = createUser(newUser);

        // Crear el perfil del usuario
        UserProfile profile = new UserProfile();
        profile.setEmail(createdUser.getEmail());
        profile.setIsAdmin(0); // Por defecto, no es administrador
        profile.setRoleType(role);
        profile.setUser(createdUser);

        UserProfileDAO userProfileDAO = new UserProfileDAO();
        try {
            userProfileDAO.create(profile);
        } catch (SQLException e) {
            throw new ServiceException("Error al crear el perfil de usuario", e);
        }

        // Si el rol es CLIENTE, crear el registro en la tabla 'customer'
        if (role == RoleType.CLIENTE) {
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = new Customer();
            customer.setName(createdUser.getName() + " " + createdUser.getLastName());
            customer.setEmail(createdUser.getEmail());
            customer.setIdUser(createdUser);
            try {
                customerDAO.create(customer);
            } catch (SQLException e) {
                throw new ServiceException("Error al crear el registro de cliente", e);
            }
        }

        // Si el rol es PROVEEDOR, crear el registro en la tabla 'provider'
        if (role == RoleType.PROVEEDOR) {
            ProviderDAO providerDAO = new ProviderDAO();
            Provider provider = new Provider();
            // Por ejemplo, se guarda "Nombre Apellido" como nombre del proveedor
            provider.setName(createdUser.getName() + " " + createdUser.getLastName());
            provider.setUser(createdUser);
            try {
                providerDAO.create(provider);
            } catch (SQLException e) {
                throw new ServiceException("Error al crear el registro de proveedor", e);
            }
        }
        return createdUser;
    }



    public User updateUser(User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (SQLException ex) {
            throw new ServiceException("Error al actualizar el usuario", ex);
        }
    }

    public void deleteUser(int userId) throws ServiceException {
        try {
            if (!userDAO.delete(userId)) {
                throw new ServiceException("No se pudo eliminar el usuario con id " + userId);
            }
        } catch (SQLException ex) {
            throw new ServiceException("Error al eliminar el usuario", ex);
        }
    }

    public User authenticate(String email, String password) throws ServiceException {
        try {
            User user = userDAO.findByEmail(email);
            if (user != null && user.checkPassword(password)) {
                return user;
            }
            return null;
        } catch (SQLException ex) {
            throw new ServiceException("Error al autenticar el usuario", ex);
        }
    }

    public String generateRecoveryToken(String email) throws ServiceException {
        try {
            String token = userDAO.generateToken(email);
            if (token == null) {
                throw new ServiceException("No se pudo generar el token: el email no existe");
            }
            return token;
        } catch (SQLException ex) {
            throw new ServiceException("Error al generar el token de recuperación", ex);
        }
    }

    public boolean validateRecoveryToken(String token) throws ServiceException {
        try {
            return userDAO.validateToken(token);
        } catch (SQLException ex) {
            throw new ServiceException("Error al validar el token", ex);
        }
    }

    public User findByToken(String token) throws ServiceException {
        try {
            return userDAO.findByToken(token);
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener el usuario con token " + token, ex);
        }
    }

    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.findAll();
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener la lista de usuarios", ex);
        }
    }

    public User getUserById(int userId) throws ServiceException {
        try {
            return userDAO.findById(userId);
        } catch (SQLException ex) {
            throw new ServiceException("Error al obtener el usuario con id " + userId, ex);
        }
    }
}
