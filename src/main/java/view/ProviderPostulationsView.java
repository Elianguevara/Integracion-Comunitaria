package view;

import model.Petition;
import model.Postulation;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

public class ProviderPostulationsView extends JFrame {

    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    private PostulationsTableModel tableModel;
    private JTable tblPostulaciones;

    // Campos de formulario para crear/editar una Postulation
    private JComboBox<Petition> cmbPeticion;
    private JTextField txtProposal;
    private JTextField txtCost;
    private JTextField txtState;

    // Botones CRUD
    private JButton btnCrear;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public ProviderPostulationsView() {
        super("Mis Postulaciones");
        initComponents();
    }

    private void initComponents() {
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --------------------------------------------------------------
        // 1. Barra superior (similar a MyPetitionsView)
        // --------------------------------------------------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(900, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblTitulo = new JLabel("Mis Postulaciones", SwingConstants.LEFT);
        lblTitulo.setForeground(WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topBar.add(lblTitulo, BorderLayout.WEST);

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

        JLabel lblUserInfo = new JLabel("Proveedor  ", SwingConstants.RIGHT);
        lblUserInfo.setForeground(WHITE);
        lblUserInfo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        topBar.add(lblUserInfo, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // --------------------------------------------------------------
        // 2. Tabla de Postulaciones (centro)
        // --------------------------------------------------------------
        tableModel = new PostulationsTableModel();
        tblPostulaciones = new JTable(tableModel);
        tblPostulaciones.setFillsViewportHeight(true);
        tblPostulaciones.setRowHeight(30);

        // Renderizado de la columna "Estado" (índice 3) con colores
        DefaultTableCellRenderer estadoRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected && value != null) {
                    String estado = value.toString().toLowerCase();
                    switch (estado) {
                        case "ganadora":
                            c.setBackground(new Color(46, 204, 113));
                            c.setForeground(Color.WHITE);
                            break;
                        case "rechazada":
                            c.setBackground(new Color(231, 76, 60));
                            c.setForeground(Color.WHITE);
                            break;
                        default:
                            c.setBackground(Color.WHITE);
                            c.setForeground(Color.BLACK);
                            break;
                    }
                } else if (!isSelected) {
                    // Si no hay valor, fondo blanco
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        };
        // La columna "Estado" es la 4ta (índice 3)
        tblPostulaciones.getColumnModel().getColumn(3).setCellRenderer(estadoRenderer);

        JScrollPane scrollPane = new JScrollPane(tblPostulaciones);
        add(scrollPane, BorderLayout.CENTER);

        // --------------------------------------------------------------
        // 3. Panel inferior: formulario + botones
        // --------------------------------------------------------------
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(WHITE);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Petición
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Petición:"), gbc);

        cmbPeticion = new JComboBox<>();
        cmbPeticion.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        panelFormulario.add(cmbPeticion, gbc);

        // Propuesta
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Propuesta:"), gbc);

        txtProposal = new JTextField(30);
        gbc.gridx = 1;
        panelFormulario.add(txtProposal, gbc);

        // Costo
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Costo:"), gbc);

        txtCost = new JTextField(30);
        gbc.gridx = 1;
        panelFormulario.add(txtCost, gbc);

        // Estado
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("Estado:"), gbc);

        txtState = new JTextField(30);
        gbc.gridx = 1;
        panelFormulario.add(txtState, gbc);

        // Borde y título
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ACCENT, 2),
                "Detalles de la Postulación",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                ACCENT
        ));

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(WHITE);

        btnCrear = new JButton("Crear");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        styleButton(btnCrear);
        styleButton(btnActualizar);
        styleButton(btnEliminar);
        styleButton(btnLimpiar);

        panelBotones.add(btnCrear);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        panelInferior.add(panelFormulario, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Aplica el mismo estilo de botón que en MyPetitionsView.
     */
    private void styleButton(JButton button) {
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ----------------------------------------------------------------
    // Métodos para interactuar con la tabla y el formulario
    // ----------------------------------------------------------------
    public void setPostulations(List<Postulation> list) {
        tableModel.setPostulations(list);
    }

    public PostulationsTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTblPostulaciones() {
        return tblPostulaciones;
    }

    // Combo para seleccionar la petición a la que se postula
    public JComboBox<Petition> getCmbPeticion() {
        return cmbPeticion;
    }
    public void populatePetitions(List<Petition> petitions) {
        DefaultComboBoxModel<Petition> model = new DefaultComboBoxModel<>();
        for (Petition p : petitions) {
            model.addElement(p);
        }
        cmbPeticion.setModel(model);
    }

    // Campos de texto
    public JTextField getTxtProposal() {
        return txtProposal;
    }
    public JTextField getTxtCost() {
        return txtCost;
    }
    public JTextField getTxtState() {
        return txtState;
    }

    // Botones
    public JButton getBtnCrear() {
        return btnCrear;
    }
    public JButton getBtnActualizar() {
        return btnActualizar;
    }
    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
}
