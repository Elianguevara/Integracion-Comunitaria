package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Entidad que representa a un usuario del sistema (por ejemplo, administrador o usuario autenticado).
 */
public class User extends BaseEntity {
    // Identificador único del usuario.
    private Integer idUser;
    // Nombre del usuario.
    private String name;
    // Apellido del usuario.
    private String lastname;
    // Correo electrónico del usuario.
    private String email;
    // Contraseña del usuario.
    private String password;
    // Token de sesión o recuperación.
    private String token;
    // Fecha de generación del token.
    private LocalDateTime dateToken;

    public User() { }

    public User(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(User idUserCreate, User idUserUpdate, Timestamp dateCreate, Timestamp dateUpdate,
                String name, String lastname, String email, String password) {
        super(idUserCreate, idUserUpdate, dateCreate, dateUpdate);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public Integer getIdUser() {
        return idUser;
    }
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDateToken() {
        return dateToken;
    }
    public void setDateToken(LocalDateTime dateToken) {
        this.dateToken = dateToken;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la del usuario.
     * En un caso real, la contraseña estaría cifrada.
     */
    public boolean checkPassword(String inputPassword) {
        return inputPassword != null && inputPassword.equals(this.password);
    }

    /**
     * Validación básica del email.
     */
    public boolean isValidEmail() {
        return email != null && email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

    /**
     * Válida que los campos obligatorios estén presentes.
     */
    public boolean isValidUser() {
        return name != null && !name.isEmpty() &&
                lastname != null && !lastname.isEmpty() &&
                email != null && !email.isEmpty() &&
                password != null && !password.isEmpty() &&
                isValidEmail();
    }
}
