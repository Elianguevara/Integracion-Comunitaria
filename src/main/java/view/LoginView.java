package view;

import util.PlaceholderTextField;
import controller.AuthController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {
    private final AuthController controller;

    // Paleta de colores (idéntica a la del DashboardView)
    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    // Componentes principales
    private JLabel lblTitle;
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblForgotPassword;
    private JLabel lblRegister;

    public LoginView(AuthController controller) {
        super("Inicio de Sesión - Integración Comunitaria");
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // --------------------------------
        // 1. Top Bar (mismo estilo que Dashboard)
        // --------------------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(400, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); // Línea divisoria

        lblTitle = new JLabel("Bienvenido", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(WHITE);

        topBar.add(lblTitle, BorderLayout.CENTER);
        add(topBar, BorderLayout.NORTH);

        // --------------------------------
        // 2. Panel central (contenido)
        // --------------------------------
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // a) Campo de usuario con placeholder
        txtUser = new PlaceholderTextField();
        txtUser.setMaximumSize(new Dimension(300, 40));
        txtUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtUser.setBorder(BorderFactory.createTitledBorder("Usuario"));
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Ajustar fuente
        // O bien aplica un estilo unificado (ver método styleTextField() si prefieres)

        // b) Campo de contraseña
        txtPassword = new JPasswordField();
        txtPassword.setMaximumSize(new Dimension(300, 40));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // c) Botón de login
        btnLogin = new JButton("Entrar");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleButton(btnLogin); // Reutilizamos la lógica de estilo
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // d) Etiquetas para recuperar contraseña y registro
        lblForgotPassword = new JLabel("¿Perdiste tu contraseña?");
        styleLinkLabel(lblForgotPassword);

        lblRegister = new JLabel("¿No tenés Cuenta? Regístrate");
        styleLinkLabel(lblRegister);

        // Añadir los componentes con espaciado
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(txtUser);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(txtPassword);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(btnLogin);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(lblForgotPassword);
        centerPanel.add(Box.createVerticalStrut(5));
        centerPanel.add(lblRegister);
        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);

        // --------------------------------
        // 3. Listeners para acciones
        // --------------------------------
        btnLogin.addActionListener(e -> {
            String email = txtUser.getText().trim();
            String password = new String(txtPassword.getPassword());
            controller.login(email, password);
        });

        lblForgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView.this.dispose();
                controller.openRecoveryView();
            }
        });

        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView.this.dispose();
                controller.openRegisterView();
            }
        });
    }

    /**
     * Aplica estilo a un botón (color de acento y texto blanco), como en DashboardView.
     */
    private void styleButton(JButton button) {
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    /**
     * Aplica estilo a las etiquetas que actúan como "links" (azul, mano, fuente).
     */
    private void styleLinkLabel(JLabel label) {
        label.setForeground(ACCENT);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }


}
