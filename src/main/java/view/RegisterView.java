package view;

import controller.AuthController;
import model.RoleType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RegisterView extends JFrame {

    // Paleta de colores (igual que en DashboardView/LoginView)
    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    private JLabel lblTitle;
    private JTextField txtName;
    private JTextField txtLastName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnSubmit, btnCancel;
    private AuthController controller;

    // Componentes para la selección de rol
    private JRadioButton rdbtnCliente;
    private JRadioButton rdbtnProveedor;
    private ButtonGroup roleGroup;

    public RegisterView(AuthController controller) {
        super("Registro - Integración Comunitaria");
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 560);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // -----------------------
        // 1. Top Bar
        // -----------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(400, 60));
        // Borde inferior para separar visualmente
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        lblTitle = new JLabel("Registro de Usuario", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(WHITE);
        topBar.add(lblTitle, BorderLayout.CENTER);

        add(topBar, BorderLayout.NORTH);

        // -----------------------
        // 2. Panel central
        // -----------------------
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Estilo base para los bordes de cada campo
        Border fieldBorder = BorderFactory.createTitledBorder("");

        txtName = new JTextField();
        txtName.setBorder(BorderFactory.createTitledBorder("Nombre"));
        txtName.setMaximumSize(new Dimension(300, 40));
        txtName.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtLastName = new JTextField();
        txtLastName.setBorder(BorderFactory.createTitledBorder("Apellido"));
        txtLastName.setMaximumSize(new Dimension(300, 40));
        txtLastName.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtLastName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtEmail = new JTextField();
        txtEmail.setBorder(BorderFactory.createTitledBorder("Email"));
        txtEmail.setMaximumSize(new Dimension(300, 40));
        txtEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtPassword = new JPasswordField();
        txtPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
        txtPassword.setMaximumSize(new Dimension(300, 40));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBorder(BorderFactory.createTitledBorder("Confirmar Contraseña"));
        txtConfirmPassword.setMaximumSize(new Dimension(300, 40));
        txtConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // RadioButtons para la selección de rol
        rdbtnCliente = new JRadioButton("Cliente");
        rdbtnProveedor = new JRadioButton("Proveedor");
        roleGroup = new ButtonGroup();
        roleGroup.add(rdbtnCliente);
        roleGroup.add(rdbtnProveedor);
        // Seleccionamos uno por defecto
        rdbtnCliente.setSelected(true);

        // Ajustamos fuente (igual a la del resto)
        rdbtnCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rdbtnProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Panel para la selección de rol
        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        rolePanel.setBackground(WHITE);
        rolePanel.setBorder(BorderFactory.createTitledBorder("Seleccionar Rol"));
        rolePanel.add(rdbtnCliente);
        rolePanel.add(rdbtnProveedor);

        // Botón Registrar (acento)
        btnSubmit = new JButton("Registrar");
        styleAccentButton(btnSubmit);

        // Botón Cancelar (neutral)
        btnCancel = new JButton("Cancelar");
        styleNeutralButton(btnCancel);

        // Agregamos espaciados verticales y componentes
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(txtName);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtLastName);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtEmail);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtPassword);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtConfirmPassword);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(rolePanel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnSubmit);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnCancel);
        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);

        // -----------------------
        // 3. Listeners
        // -----------------------
        btnSubmit.addActionListener(e -> {
            String name = txtName.getText().trim();
            String lastname = txtLastName.getText().trim();
            String email = txtEmail.getText().trim();
            String password = new String(txtPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());

            // Validación de confirmación de contraseña
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this,
                        "Las contraseñas no coinciden. Por favor, verifíquelas.");
                return;
            }

            // Determinar el rol seleccionado
            RoleType selectedRole;
            if (rdbtnCliente.isSelected()) {
                selectedRole = RoleType.CLIENTE;
            } else if (rdbtnProveedor.isSelected()) {
                selectedRole = RoleType.PROVEEDOR;
            } else {
                // Valor por defecto si no hubiera ninguno seleccionado
                selectedRole = RoleType.AMBOS;
            }

            // Llamamos al controlador para registrar
            controller.register(name, lastname, email, password, selectedRole);
        });

        // Acción del botón Cancelar
        btnCancel.addActionListener(e -> {
            this.dispose();
            LoginView loginView = new LoginView(controller);
            loginView.setVisible(true);
            loginView.setLocationRelativeTo(null);
        });
    }

    /**
     * Botón con color de acento y texto blanco (igual estilo que en DashboardView/LoginView).
     */
    private void styleAccentButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    /**
     * Botón "neutral" para secundario (Cancelar).
     */
    private void styleNeutralButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }


}
