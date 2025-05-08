package view;

import model.Petition;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class DashboardView extends JFrame {

    // Paleta de colores
    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    // Botones que se exponen con getters
    private JButton btnMisPeticiones;
    private JButton btnPerfil;
    private JButton btnMisOfertas;
    private JButton btnMisPostulaciones;

    // Panel central (almacenado como atributo para poder actualizarlo)
    private JPanel centerPanel;

    public DashboardView(String name, String role) {
        super("Dashboard - Integración Comunitaria - " + name + " (" + role + ")");
        initComponents(name, role);
    }

    private void initComponents(String name, String role) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ---------------------------
        // 1. Top Bar
        // ---------------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(1200, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblWelcome = new JLabel("¡Bienvenido, " + name + "!", SwingConstants.LEFT);
        lblWelcome.setForeground(WHITE);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topBar.add(lblWelcome, BorderLayout.WEST);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        searchPanel.setOpaque(false);
        JTextField txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBackground(WHITE);
        txtSearch.setForeground(Color.DARK_GRAY);
        txtSearch.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JButton btnSearch = new JButton("Buscar");
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        styleButton(btnSearch);
        btnSearch.setPreferredSize(new Dimension(90, 35));

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        topBar.add(searchPanel, BorderLayout.CENTER);

        JLabel lblUserInfo = new JLabel(role + "  ", SwingConstants.RIGHT);
        lblUserInfo.setForeground(WHITE);
        lblUserInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        topBar.add(lblUserInfo, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // ---------------------------
        // 2. Menú lateral
        // ---------------------------
        JPanel sideMenu = createSideMenu(role);
        add(sideMenu, BorderLayout.WEST);

        // ---------------------------
        // 3. Panel central (inicialmente con mensaje)
        // ---------------------------
        centerPanel = createCenterPanel(role);
        add(centerPanel, BorderLayout.CENTER);

        // ---------------------------
        // 4. Panel de Filtros
        // ---------------------------
        JPanel rightPanel = createFilterPanel();
        add(rightPanel, BorderLayout.EAST);
    }

    private JPanel createSideMenu(String role) {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(220, getHeight()));
        sideMenu.setBackground(DARK_BASE);
        sideMenu.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        Dimension btnSize = new Dimension(200, 40);

        // Botón "Mis Peticiones"
        btnMisPeticiones = new JButton("Mis Peticiones");
        // Botón "Perfil"
        btnPerfil = new JButton("Perfil");
        // Botón "Mis Ofertas"
        btnMisOfertas = new JButton("Mis Ofertas");
        // Botón "Mis Postulaciones"
        btnMisPostulaciones = new JButton("Mis Postulaciones");

        // Otros botones
        JButton btnPropuestasRecibidas = new JButton("Propuestas Recibidas");
        JButton btnSolicitudesDisponibles = new JButton("Peticiones Disponibles");
        JButton btnAgenda = new JButton("Agenda / Disponibilidad");
        JButton btnPortafolio = new JButton("Portafolio");
        JButton btnNotificaciones = new JButton("Notificaciones");
        JButton btnHistorial = new JButton("Historial");

        JButton[] buttons;
        if (role.equalsIgnoreCase("cliente")) {
            buttons = new JButton[]{
                    btnMisPeticiones,
                    btnPropuestasRecibidas,
                    btnNotificaciones,
                    btnHistorial,
                    btnPerfil
            };
        } else if (role.equalsIgnoreCase("proveedor")) {
            buttons = new JButton[]{
                    btnMisOfertas,
                    btnSolicitudesDisponibles,
                    btnMisPostulaciones,
                    btnAgenda,
                    btnPortafolio,
                    btnNotificaciones,
                    btnHistorial,
                    btnPerfil
            };
        } else {
            buttons = new JButton[]{
                    btnMisPeticiones,
                    btnPropuestasRecibidas,
                    btnMisOfertas,
                    btnSolicitudesDisponibles,
                    btnMisPostulaciones,
                    btnAgenda,
                    btnPortafolio,
                    btnNotificaciones,
                    btnHistorial,
                    btnPerfil
            };
        }

        for (JButton btn : buttons) {
            btn.setMaximumSize(btnSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(ACCENT);
            btn.setForeground(WHITE);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            btn.setOpaque(true);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sideMenu.add(btn);
            sideMenu.add(Box.createVerticalStrut(10));
        }
        sideMenu.add(Box.createVerticalGlue());
        return sideMenu;
    }

    private JPanel createCenterPanel(String role) {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(WHITE);

        if (role.equalsIgnoreCase("cliente")) {
            // Inicialmente, mostramos un mensaje de carga en el centro
            JLabel lbl = new JLabel("Aquí se mostrarán las peticiones", SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
            centerPanel.add(lbl, BorderLayout.CENTER);
        } else if (role.equalsIgnoreCase("proveedor")) {
            JScrollPane scroll = createCardListPanel(
                    "Últimas Peticiones",
                    new String[]{"Petición #201", "Petición #202", "Petición #203", "Petición #204", "Petición #205", "Petición #206"}
            );
            centerPanel.add(scroll, BorderLayout.CENTER);
        } else {
            // Rol "admin" u otro
            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.setBackground(WHITE);

            JScrollPane scroll1 = createCardListPanel(
                    "Últimas Postulaciones",
                    new String[]{"Postulación #101", "Postulación #102", "Postulación #103"}
            );
            JScrollPane scroll2 = createCardListPanel(
                    "Últimas Peticiones",
                    new String[]{"Petición #201", "Petición #202", "Petición #203", "Petición #204"}
            );

            JPanel panel1 = new JPanel(new BorderLayout());
            panel1.setBackground(WHITE);
            panel1.add(scroll1, BorderLayout.CENTER);

            JPanel panel2 = new JPanel(new BorderLayout());
            panel2.setBackground(WHITE);
            panel2.add(scroll2, BorderLayout.CENTER);

            container.add(panel1);
            container.add(Box.createVerticalStrut(20));
            container.add(panel2);

            JScrollPane scrollMixto = new JScrollPane(container);
            scrollMixto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            centerPanel.add(scrollMixto, BorderLayout.CENTER);
        }
        return centerPanel;
    }

    private JScrollPane createCardListPanel(String titulo, String[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                titulo
        ));

        for (String item : items) {
            panel.add(createCard(item, "Descripción breve de " + item));
            panel.add(Box.createVerticalStrut(10));
        }
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scroll;
    }

    // Se conserva una única versión de createCard
    private JPanel createCard(String title, String description) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        card.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        card.add(lblTitle);

        JLabel lblDesc = new JLabel(description);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
        card.add(lblDesc);

        return card;
    }

    private JPanel createFilterPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(250, getHeight()));
        rightPanel.setBackground(DARK_BASE);

        JLabel lblFilterTitle = new JLabel("Filtros", SwingConstants.CENTER);
        lblFilterTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFilterTitle.setForeground(WHITE);
        lblFilterTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        rightPanel.add(lblFilterTitle);

        JPanel filterInnerPanel = new JPanel();
        filterInnerPanel.setLayout(new BoxLayout(filterInnerPanel, BoxLayout.Y_AXIS));
        filterInnerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        filterInnerPanel.setBackground(DARK_BASE);

        JLabel lblCat = new JLabel("Categoría:");
        lblCat.setForeground(WHITE);
        lblCat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblCat.setAlignmentX(Component.LEFT_ALIGNMENT);

        JComboBox<String> cmbCategory = new JComboBox<>(new String[]{"Todas", "Supermercado", "Full Week", "Mochilas"});
        cmbCategory.setAlignmentX(Component.LEFT_ALIGNMENT);
        cmbCategory.setPreferredSize(new Dimension(200, 30));
        cmbCategory.setMaximumSize(new Dimension(200, 30));
        cmbCategory.setBackground(ACCENT);
        cmbCategory.setForeground(WHITE);
        cmbCategory.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbCategory.setFocusable(false);

        JLabel lblPrecio = new JLabel("Rango de precio:");
        lblPrecio.setForeground(WHITE);
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pricePanel.setBackground(DARK_BASE);

        JTextField txtMin = new JTextField("Min", 4);
        styleTextField(txtMin);

        JLabel lblGuion = new JLabel("-");
        lblGuion.setForeground(WHITE);

        JTextField txtMax = new JTextField("Max", 4);
        styleTextField(txtMax);

        pricePanel.add(txtMin);
        pricePanel.add(lblGuion);
        pricePanel.add(txtMax);

        JButton btnFiltrar = new JButton("Aplicar");
        styleButton(btnFiltrar);

        filterInnerPanel.add(lblCat);
        filterInnerPanel.add(cmbCategory);
        filterInnerPanel.add(Box.createVerticalStrut(15));
        filterInnerPanel.add(lblPrecio);
        filterInnerPanel.add(pricePanel);
        filterInnerPanel.add(Box.createVerticalStrut(15));
        filterInnerPanel.add(btnFiltrar);

        rightPanel.add(filterInnerPanel);
        rightPanel.add(Box.createVerticalGlue());

        return rightPanel;
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(ACCENT);
        textField.setForeground(WHITE);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setCaretColor(WHITE);
        textField.setBorder(new LineBorder(WHITE, 1));
        textField.setPreferredSize(new Dimension(50, 28));
        textField.setMaximumSize(new Dimension(50, 28));
        textField.setHorizontalAlignment(JTextField.CENTER);
    }

    private void styleButton(JButton button) {
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setMaximumSize(new Dimension(100, 35));
        button.setOpaque(true);
    }

    // Getters para los botones (para que el controlador los use)
    public JButton getBtnMisPeticiones() {
        return btnMisPeticiones;
    }

    public JButton getBtnPerfil() {
        return btnPerfil;
    }

    public JButton getBtnMisOfertas() {
        return btnMisOfertas;
    }

    public JButton getBtnMisPostulaciones() {
        return btnMisPostulaciones;
    }

    /**
     *  Método público para poblar el panel central con todas las peticiones ordenadas por fecha de creación.
     * Ahora, además de la descripción y la fecha, también mostramos el nombre del cliente.
     */
    public void populateCenterPanelWithPetitions(List<Petition> petitions) {
        JPanel newCenterPanel = new JPanel(new BorderLayout());
        newCenterPanel.setBackground(WHITE);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(WHITE);

        for (Petition p : petitions) {
            String title = p.getDescription();

            // Subtítulo con fecha y nombre del cliente
            // (asegúrate de que p.getCustomer() no sea null y de que tenga un nombre)
            String subTitle = "Creado: " + p.getDateCreate()
                    + "  |  Cliente: " + (p.getCustomer() != null ? p.getCustomer().getName() : "Desconocido");

            JPanel card = createCard(title, subTitle);
            cardsPanel.add(card);
            cardsPanel.add(Box.createVerticalStrut(10));
        }

        JScrollPane scroll = new JScrollPane(cardsPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        newCenterPanel.add(scroll, BorderLayout.CENTER);

        // Reemplazar el panel central actual
        getContentPane().remove(centerPanel);
        centerPanel = newCenterPanel;
        add(centerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
