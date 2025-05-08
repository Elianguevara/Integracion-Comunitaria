package view;

import controller.AuthController;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordRecoveryView extends JFrame {

    // Paleta de colores (misma que en DashboardView/LoginView/etc.)
    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    private JTextField txtEmail;
    private JButton btnSend, btnCancel;
    private AuthController controller;

    public PasswordRecoveryView(AuthController controller) {
        super("Recuperar Contraseña - Integración Comunitaria");
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        // Configuración de la ventana
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // --------------------------------
        // 1. Top Bar (mismo estilo)
        // --------------------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(400, 60));
        // Borde inferior para separar
        topBar.setBorder(new MatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblTitle = new JLabel("Recuperar Contraseña", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(WHITE);
        topBar.add(lblTitle, BorderLayout.CENTER);

        add(topBar, BorderLayout.NORTH);

        // --------------------------------
        // 2. Panel central con BoxLayout
        // --------------------------------
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Campo de email
        txtEmail = new JTextField();
        txtEmail.setBorder(BorderFactory.createTitledBorder("Ingrese su Email"));
        txtEmail.setMaximumSize(new Dimension(300, 40));
        txtEmail.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Botón Enviar (acento)
        btnSend = new JButton("Enviar");
        styleAccentButton(btnSend);

        // Botón Cancelar (neutral)
        btnCancel = new JButton("Cancelar");
        styleNeutralButton(btnCancel);

        // Agregar componentes con espacios
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(txtEmail);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(btnSend);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnCancel);
        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);

        // --------------------------------
        // 3. Listeners
        // --------------------------------
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                controller.recoverPassword(email);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PasswordRecoveryView.this.dispose();
                LoginView loginView = new LoginView(controller);
                loginView.setVisible(true);
                loginView.setLocationRelativeTo(null);
            }
        });
    }

    /**
     * Aplica estilo de botón con color de acento y texto blanco.
     */
    private void styleAccentButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(130, 35));
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    /**
     * Aplica estilo de botón neutral (gris).
     */
    private void styleNeutralButton(JButton button) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(130, 35));
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }


}
