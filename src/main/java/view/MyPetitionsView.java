package view;

import model.Customer;
import model.Petition;
import model.TypePetition; // Clase que representa la fila de type_petition

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

public class MyPetitionsView extends JFrame {

    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    private JTable tablePeticiones;
    private PeticionesTableModel tableModel;

    // Campos de formulario (sin el campo Cliente)
    private JComboBox<TypePetition> cmbTipo;  // ComboBox para "Tipo"
    private JTextField txtDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;

    // Botones CRUD
    private JButton btnCrear;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private Customer loggedCustomer;

    /**
     * Constructor de la vista, recibiendo el cliente logueado y el rol.
     */
    public MyPetitionsView(Customer customer, String role) {
        super("Mis Peticiones");
        this.loggedCustomer = customer;
        initComponents(customer.getName(), role);
    }

    /**
     * Inicializa la interfaz gráfica.
     */
    private void initComponents(String userName, String role) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ----------------------------------------------------------------
        // 1. Barra superior (Top Bar)
        // ----------------------------------------------------------------
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(900, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblWelcome = new JLabel("¡Bienvenido, " + userName + "!", SwingConstants.LEFT);
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

        // ----------------------------------------------------------------
        // 2. Tabla de peticiones (centro)
        // ----------------------------------------------------------------
        tableModel = new PeticionesTableModel();
        tablePeticiones = new JTable(tableModel);
        tablePeticiones.setFillsViewportHeight(true);
        tablePeticiones.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(tablePeticiones);
        add(scrollPane, BorderLayout.CENTER);

        // ----------------------------------------------------------------
        // 3. Panel inferior: formulario + botones
        // ----------------------------------------------------------------
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- muestra primero el campo "Tipo" ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(new JLabel("Tipo:"), gbc);

        cmbTipo = new JComboBox<>();
        cmbTipo.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        panelFormulario.add(cmbTipo, gbc);

        // --- Luego el campo "Descripción" ---
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulario.add(new JLabel("Descripción:"), gbc);

        txtDescripcion = new JTextField(30);
        gbc.gridx = 1;
        panelFormulario.add(txtDescripcion, gbc);

        // Fecha Inicio
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulario.add(new JLabel("Fecha Inicio (yyyy-MM-dd):"), gbc);

        txtFechaInicio = new JTextField(30);
        gbc.gridx = 1;
        panelFormulario.add(txtFechaInicio, gbc);

        // Fecha Fin
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulario.add(new JLabel("Fecha Fin (yyyy-MM-dd):"), gbc);

        txtFechaFin = new JTextField(30);
        gbc.gridx = 1;
        panelFormulario.add(txtFechaFin, gbc);

        // Panel de botones
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

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(WHITE);
        panelInferior.add(panelFormulario, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ACCENT, 2),
                "Detalles de la Petición",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16),
                ACCENT
        ));

        add(panelInferior, BorderLayout.SOUTH);

        // ----------------------------------------------------------------
        // 4. Listener para cargar datos al seleccionar una fila
        // ----------------------------------------------------------------
        tablePeticiones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablePeticiones.getSelectedRow() != -1) {
                int selectedRow = tablePeticiones.getSelectedRow();
                Petition petition = tableModel.getPetitionAt(selectedRow);
                txtDescripcion.setText(petition.getDescription());
                if(petition.getTypePetition() != null) {
                    setSelectedTypePetitionById(petition.getTypePetition().getIdTypePetition());
                } else {
                    cmbTipo.setSelectedIndex(-1);
                }
                txtFechaInicio.setText(petition.getDateSince().toString());
                txtFechaFin.setText(petition.getDateUntil().toString());
            }
        });
    }

    /**
     * Estilo para los botones.
     */
    private void styleButton(JButton button) {
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // ------------------------------------------------------------
    // Métodos para popular y gestionar el combo "Tipo"
    // ------------------------------------------------------------
    public void populateTypeCombo(List<TypePetition> types) {
        DefaultComboBoxModel<TypePetition> model = new DefaultComboBoxModel<>();
        for (TypePetition t : types) {
            model.addElement(t);
        }
        cmbTipo.setModel(model);
    }

    public TypePetition getSelectedTypePetition() {
        return (TypePetition) cmbTipo.getSelectedItem();
    }

    private void setSelectedTypePetitionById(int idType) {
        ComboBoxModel<TypePetition> model = cmbTipo.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            TypePetition tp = model.getElementAt(i);
            if (tp.getIdTypePetition() == idType) {
                cmbTipo.setSelectedIndex(i);
                return;
            }
        }
        cmbTipo.setSelectedIndex(-1);
    }

    // ------------------------------------------------------------
    // Getters para que el controlador acceda a los componentes
    // ------------------------------------------------------------
    public JTable getTablePeticiones() { return tablePeticiones; }
    public PeticionesTableModel getTableModel() { return tableModel; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }
    public JComboBox<TypePetition> getCmbTipo() { return cmbTipo; }
    public JTextField getTxtFechaInicio() { return txtFechaInicio; }
    public JTextField getTxtFechaFin() { return txtFechaFin; }
    public JButton getBtnCrear() { return btnCrear; }
    public JButton getBtnActualizar() { return btnActualizar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }

    /**
     * Asigna la lista de peticiones al TableModel para mostrarlas en la tabla.
     */
    public void setPeticiones(List<Petition> peticiones) {
        tableModel.setPetitions(peticiones);
    }
}
