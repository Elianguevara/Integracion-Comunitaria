package view;

import controller.CustomerProfileController;
import model.City;
import model.Customer;
import model.Gender;
import model.GenderEnum;
import service.CustomerService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomerProfileView extends JFrame {

    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    // Componentes para la sección "Información Básica"
    private JTextField txtName;
    private JTextField txtEmail;
    private JTextField txtPhone;
    private JTextField txtAddress;
    private JButton btnEditBasic;

    // Componentes para la sección "Información Adicional"
    // Se reemplaza el JTextField de género por un JComboBox
    private JComboBox<GenderEnum> cmbGender;
    private JTextField txtCity;
    private JTextField txtDateYear;
    private JTextField txtDni;
    private JButton btnEditConfig;

    // Botones inferiores
    private JButton btnSaveChanges;
    private JButton btnDelete;

    private Customer customer;
    private CustomerProfileController controller;  // Referencia al controlador

    public CustomerProfileView(Customer customer, CustomerProfileController controller) {
        super("Perfil de Cliente: "
                + (customer.getName() != null ? customer.getName() : "Sin nombre"));
        this.customer = customer;
        this.controller = controller;

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Barra superior
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(1000, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblTitle = new JLabel("Perfil de Cliente: " + customer.getName(), SwingConstants.LEFT);
        lblTitle.setForeground(WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topBar.add(lblTitle, BorderLayout.WEST);

        add(topBar, BorderLayout.NORTH);

        // Panel principal con dos secciones
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(WHITE);

        // Sección Izquierda: Información Básica
        JPanel leftPanel = createBasicInfoPanel();
        mainPanel.add(leftPanel);

        // Sección Derecha: Información Adicional
        JPanel rightPanel = createAdditionalInfoPanel();
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Panel inferior con botones "Guardar Cambios" y "Eliminar Cliente"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        bottomPanel.setBackground(WHITE);

        btnSaveChanges = new JButton("Guardar Cambios");
        styleAccentButton(btnSaveChanges);
        btnSaveChanges.addActionListener(e -> {
            // Actualizamos el objeto Customer con los datos de la vista
            updateCustomerFromFields();
            // Llamamos al controlador para que actualice en la BD
            controller.handleSave(customer);
            // Deshabilitamos campos (opcional)
            setEditableBasicInfo(false);
            setEditableAdditionalInfo(false);
        });

        btnDelete = new JButton("Eliminar Cliente");
        styleAccentButton(btnDelete);
        btnDelete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que deseas eliminar este cliente?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                controller.handleDelete(customer);
                dispose();
            }
        });

        bottomPanel.add(btnSaveChanges);
        bottomPanel.add(btnDelete);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createBasicInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                "Información Básica"
        ));

        txtName = new JTextField(customer.getName() != null ? customer.getName() : "");
        txtEmail = new JTextField(customer.getEmail() != null ? customer.getEmail() : "");
        txtPhone = new JTextField(customer.getPhone() != null ? customer.getPhone().toString() : "");
        txtAddress = new JTextField(customer.getAddress() != null ? customer.getAddress() : "");

        setEditableBasicInfo(false);

        panel.add(createFieldPanel("Nombre:", txtName));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Email:", txtEmail));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Teléfono:", txtPhone));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Dirección:", txtAddress));
        panel.add(Box.createVerticalStrut(20));

        btnEditBasic = new JButton("Editar Información Básica");
        styleNeutralButton(btnEditBasic);
        btnEditBasic.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditBasic.addActionListener(e -> setEditableBasicInfo(true));
        panel.add(btnEditBasic);

        return panel;
    }

    private JPanel createAdditionalInfoPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                "Información Adicional"
        ));

        // GÉNERO: Usamos un JComboBox con las opciones del enum
        cmbGender = new JComboBox<>(GenderEnum.values());
        // Si el Customer ya tiene un Gender asignado, lo seleccionamos en el combobox
        if (customer.getGender() != null && customer.getGender().getIdGender() != null) {
            switch (customer.getGender().getIdGender()) {
                case 1:
                    cmbGender.setSelectedItem(GenderEnum.MALE);
                    break;
                case 2:
                    cmbGender.setSelectedItem(GenderEnum.FEMALE);
                    break;
                case 3:
                    cmbGender.setSelectedItem(GenderEnum.OTHER);
                    break;
                default:
                    cmbGender.setSelectedIndex(-1);
            }
        } else {
            cmbGender.setSelectedIndex(-1);
        }

        // CIUDAD
        txtCity = new JTextField(customer.getCity() != null && customer.getCity().getName() != null
                ? customer.getCity().getName() : "");

        // Fecha de Nacimiento: mostramos solo la parte de la fecha "yyyy-MM-dd"
        if (customer.getDateYear() != null) {
            txtDateYear = new JTextField(customer.getDateYear().toLocalDateTime().toLocalDate().toString());
        } else {
            txtDateYear = new JTextField("");
        }
        txtDateYear.setToolTipText("Formato: yyyy-MM-dd (Ejemplo: 1980-10-15)");

        // DNI
        txtDni = new JTextField(customer.getDni() != null ? customer.getDni().toString() : "");

        setEditableAdditionalInfo(false);

        // En lugar de createFieldPanel para género con JTextField, creamos uno para el JComboBox
        panel.add(createFieldPanel("Género:", cmbGender));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Ciudad:", txtCity));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Fecha de Nacimiento:", txtDateYear));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("DNI:", txtDni));
        panel.add(Box.createVerticalStrut(20));

        btnEditConfig = new JButton("Editar Información Adicional");
        styleNeutralButton(btnEditConfig);
        btnEditConfig.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditConfig.addActionListener(e -> setEditableAdditionalInfo(true));
        panel.add(btnEditConfig);

        return panel;
    }

    /**
     * Actualiza el objeto Customer con los datos de los campos de la vista.
     */
    private void updateCustomerFromFields() {
        customer.setName(txtName.getText());
        customer.setEmail(txtEmail.getText());
        customer.setPhone(txtPhone.getText());
        customer.setAddress(txtAddress.getText());

        // Manejo de Género: se obtiene la selección del JComboBox y se convierte en objeto Gender.
        GenderEnum selectedGender = (GenderEnum) cmbGender.getSelectedItem();
        if (selectedGender != null) {
            Gender gender = new Gender();
            gender.setIdGender(selectedGender.getId());
            gender.setGender(selectedGender.getDescription());
            customer.setGender(gender);
        } else {
            customer.setGender(null);
        }

        // Manejo de Ciudad
        if (customer.getCity() == null) {
            customer.setCity(new City());
        }
        customer.getCity().setName(txtCity.getText());

        // Conversión de Fecha de Nacimiento en formato "yyyy-MM-dd"
        try {
            // Parseamos la fecha usando LocalDate y convertimos a Timestamp iniciando a la medianoche
            LocalDate localDate = LocalDate.parse(txtDateYear.getText());
            customer.setDateYear(Timestamp.valueOf(localDate.atStartOfDay()));
        } catch (DateTimeParseException ex) {
            customer.setDateYear(null);
        }

        // DNI
        try {
            customer.setDni(Integer.valueOf(txtDni.getText()));
        } catch (NumberFormatException ex) {
            customer.setDni(null);
        }
    }

    /**
     * Crea un panel horizontal que contenga una etiqueta y un componente (JTextField o JComboBox).
     */
    private JPanel createFieldPanel(String labelText, JComponent component) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(WHITE);

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        component.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        component.setPreferredSize(new Dimension(200, 25));

        fieldPanel.add(lbl);
        fieldPanel.add(component);
        return fieldPanel;
    }

    private void setEditableBasicInfo(boolean editable) {
        txtName.setEditable(editable);
        txtEmail.setEditable(editable);
        txtPhone.setEditable(editable);
        txtAddress.setEditable(editable);
    }

    private void setEditableAdditionalInfo(boolean editable) {
        // Para el JComboBox se usa setEnabled
        cmbGender.setEnabled(editable);
        txtCity.setEditable(editable);
        txtDateYear.setEditable(editable);
        txtDni.setEditable(editable);
    }

    // Estilos de botones:
    private void styleAccentButton(JButton button) {
        button.setBackground(ACCENT);
        button.setForeground(WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }

    private void styleNeutralButton(JButton button) {
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.DARK_GRAY);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
    }
}
