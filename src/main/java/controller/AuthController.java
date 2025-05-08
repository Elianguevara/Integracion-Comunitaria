package controller;

import dao.CustomerDAO;
import dao.UserProfileDAO;
import model.Customer;
import model.User;
import model.UserProfile;
import service.UserService;
import view.*;

import javax.swing.JOptionPane;

public class AuthController {
    private final UserService userService;
    private static LoginView loginView;
    private RegisterView registerView;
    private PasswordRecoveryView recoveryView;

    public AuthController() {
        this.userService = new UserService();
    }

    public static void startApp() {
        AuthController controller = new AuthController();
        loginView = new LoginView(controller);
        loginView.setVisible(true);
        loginView.setLocationRelativeTo(null);
    }

    public void login(String email, String password) {
        try {
            User user = userService.authenticate(email, password);
            if (user != null) {
                // Cerramos la vista login
                loginView.dispose();

                // Se consulta el perfil del usuario para obtener el rol (ya lo tienes)
                UserProfileDAO profileDAO = new UserProfileDAO();
                UserProfile profile = profileDAO.findByUserId(user.getIdUser());
                String role = (profile != null) ? profile.getRoleType().getValue() : "Sin rol";

                // Si el rol es "cliente", obtener el Customer real
                Customer realCustomer = null;
                if (role.equalsIgnoreCase("cliente")) {
                    // Buscar en la tabla "customer" por id_user
                    // Supongamos que en tu CustomerDAO tienes un método findByUserId(userId)
                    realCustomer = new CustomerDAO().findByUserId(user);

                }

                // crear el DashboardController pasando el Customer
                DashboardController dashboardController = new DashboardController(user, realCustomer, role);
                dashboardController.showDashboard();

            } else {
                JOptionPane.showMessageDialog(loginView, "Credenciales inválidas. Por favor, intente de nuevo.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(loginView, "Error al iniciar sesión: " + ex.getMessage());
        }
    }



    public void openRegisterView() {
        if (registerView == null) {
            registerView = new RegisterView(this);
        }
        if (loginView != null) {
            loginView.setVisible(false);
        }
        registerView.setVisible(true);
    }

    public void register(String name, String lastname, String email, String password, model.RoleType role) {
        try {
            User createdUser = userService.registerUser(name, lastname, email, password, role);
            if (createdUser != null) {
                JOptionPane.showMessageDialog(registerView, "Registro exitoso. Ahora puede iniciar sesión.");
                registerView.dispose();
                if (loginView == null || !loginView.isDisplayable()) {
                    loginView = new LoginView(this);
                }
                loginView.setVisible(true);
                loginView.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(registerView, "No se pudo registrar el usuario. Verifique los datos o use otro email.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(registerView, "Error en el registro: " + ex.getMessage());
        }
    }

    public void openRecoveryView() {
        if (recoveryView == null) {
            recoveryView = new PasswordRecoveryView(this);
        }
        if (loginView != null) {
            loginView.setVisible(false);
        }
        recoveryView.setVisible(true);
    }

    public void recoverPassword(String email) {
        try {
            boolean success = userService.generateRecoveryToken(email) != null;
            if (success) {
                JOptionPane.showMessageDialog(recoveryView, "Se ha enviado un enlace de recuperación a su correo.");
                recoveryView.dispose();
                if (loginView == null || !loginView.isDisplayable()) {
                    loginView = new LoginView(this);
                }
                loginView.setVisible(true);
                loginView.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(recoveryView, "No existe un usuario con ese email.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(recoveryView, "Error en la recuperación: " + ex.getMessage());
        }
    }
}
