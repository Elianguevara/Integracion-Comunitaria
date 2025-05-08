package view;

import model.Category;
import model.Provider;
import model.Profession;
import model.TypeProvider;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProviderProfileView extends JFrame {

    private static final Color DARK_BASE = new Color(44, 62, 80);
    private static final Color ACCENT = new Color(52, 152, 219);
    private static final Color WHITE = Color.WHITE;

    private JLabel lblNameValue;
    private JLabel lblAddressValue;
    private JLabel lblGpsLatValue;
    private JLabel lblGpsLonValue;

    private JComboBox<TypeProvider> cmbTypeProvider;
    private JComboBox<Category> cmbCategory;
    private JComboBox<Profession> cmbProfession;
    private JLabel lblAvailabilityValue;

    private JButton btnEditBasic;
    private JButton btnEditSettings;
    private JButton btnDelete;

    private Provider provider;

    public ProviderProfileView(Provider provider) {
        super("Perfil de Proveedor - " + provider.getName());
        this.provider = provider;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(DARK_BASE);
        topBar.setPreferredSize(new Dimension(1000, 60));
        topBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel lblTitle = new JLabel("Perfil de Proveedor", SwingConstants.LEFT);
        lblTitle.setForeground(WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        topBar.add(lblTitle, BorderLayout.WEST);

        add(topBar, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(WHITE);

        JPanel leftPanel = createBasicInfoPanel();
        mainPanel.add(leftPanel);

        JPanel rightPanel = createProviderSettingsPanel();
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        bottomPanel.setBackground(WHITE);

        btnDelete = new JButton("Eliminar Proveedor");
        styleAccentButton(btnDelete);
        btnDelete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que deseas eliminar este proveedor?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                System.out.println("Eliminar Provider con id: " + provider.getIdProvider());
                dispose();
            }
        });

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

        lblNameValue = new JLabel(provider.getName());
        lblAddressValue = new JLabel(provider.getAddress() != null ? provider.getAddress() : "Sin dirección");
        lblGpsLatValue = new JLabel(String.valueOf(provider.getGpsLat() != null ? provider.getGpsLat() : "N/A"));
        lblGpsLonValue = new JLabel(String.valueOf(provider.getGpsLong() != null ? provider.getGpsLong() : "N/A"));

        lblNameValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblAddressValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblGpsLatValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblGpsLonValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panel.add(createFieldPanel("Nombre/Razón Social:", lblNameValue));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Dirección:", lblAddressValue));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("GPS Lat:", lblGpsLatValue));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("GPS Lon:", lblGpsLonValue));
        panel.add(Box.createVerticalStrut(20));

        btnEditBasic = new JButton("Editar Información Básica");
        styleNeutralButton(btnEditBasic);
        btnEditBasic.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditBasic.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Editar Información Básica de " + provider.getName());
        });
        panel.add(btnEditBasic);

        return panel;
    }

    private JPanel createProviderSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.LIGHT_GRAY, 1),
                "Configuración del Proveedor"
        ));

        // ComboBox para TypeProvider
        TypeProvider defaultType = new TypeProvider();
        defaultType.setIdTypeProvider(0);
        defaultType.setType("Seleccione Tipo");
        TypeProvider tp1 = new TypeProvider();
        tp1.setIdTypeProvider(1);
        tp1.setType("Tipo 1");
        TypeProvider tp2 = new TypeProvider();
        tp2.setIdTypeProvider(2);
        tp2.setType("Tipo 2");
        TypeProvider tp3 = new TypeProvider();
        tp3.setIdTypeProvider(3);
        tp3.setType("Tipo 3");
        TypeProvider[] tipos = {defaultType, tp1, tp2, tp3};
        cmbTypeProvider = new JComboBox<>(tipos);
        if (provider.getTypeProvider() != null) {
            cmbTypeProvider.setSelectedItem(provider.getTypeProvider());
        } else {
            cmbTypeProvider.setSelectedIndex(0);
        }

        // ComboBox para Category
        Category defaultCat = new Category();
        defaultCat.setIdCategory(0);
        defaultCat.setName("Seleccione Categoría");
        Category cat1 = new Category();
        cat1.setIdCategory(1);
        cat1.setName("Categoría 1");
        Category cat2 = new Category();
        cat2.setIdCategory(2);
        cat2.setName("Categoría 2");
        Category cat3 = new Category();
        cat3.setIdCategory(3);
        cat3.setName("Categoría 3");
        Category[] categorias = {defaultCat, cat1, cat2, cat3};
        cmbCategory = new JComboBox<>(categorias);
        if (provider.getCategory() != null) {
            cmbCategory.setSelectedItem(provider.getCategory());
        } else {
            cmbCategory.setSelectedIndex(0);
        }

        // ComboBox para Profession (inicialmente deshabilitado)
        Profession defaultProf = new Profession();
        defaultProf.setIdProfession(0);
        defaultProf.setName("Seleccione Profesión");
        cmbProfession = new JComboBox<>(new Profession[]{defaultProf});
        cmbProfession.setEnabled(false);

        // Listener para cmbCategory para actualizar cmbProfession según la categoría seleccionada
        cmbCategory.addActionListener(e -> {
            Category selectedCategory = (Category) cmbCategory.getSelectedItem();
            if (selectedCategory != null && selectedCategory.getIdCategory() != 0) {
                Profession[] profesiones = getProfessionsByCategory(selectedCategory);
                cmbProfession.setModel(new DefaultComboBoxModel<>(profesiones));
                cmbProfession.setEnabled(true);
            } else {
                cmbProfession.setModel(new DefaultComboBoxModel<>(new Profession[]{defaultProf}));
                cmbProfession.setEnabled(false);
            }
        });

        // Disponibilidad (label)
        String availabilityStr = (provider.getAvailability() != null)
                ? provider.getAvailability().toString()
                : "N/A";
        lblAvailabilityValue = new JLabel(availabilityStr);
        lblAvailabilityValue.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        panel.add(createFieldPanel("Tipo de Proveedor:", cmbTypeProvider));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Categoría:", cmbCategory));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Profesión:", cmbProfession));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFieldPanel("Disponibilidad:", lblAvailabilityValue));
        panel.add(Box.createVerticalStrut(20));

        btnEditSettings = new JButton("Editar Configuración");
        styleNeutralButton(btnEditSettings);
        btnEditSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditSettings.addActionListener(e -> {
            provider.setTypeProvider((TypeProvider) cmbTypeProvider.getSelectedItem());
            provider.setCategory((Category) cmbCategory.getSelectedItem());
            provider.setProfession((Profession) cmbProfession.getSelectedItem());
            JOptionPane.showMessageDialog(this,
                    "Configuración actualizada para " + provider.getName());
        });
        panel.add(btnEditSettings);

        return panel;
    }

    private Profession[] getProfessionsByCategory(Category category) {
        if (category.getIdCategory() == 1) {
            Profession p1 = new Profession();
            p1.setIdProfession(1);
            p1.setName("Carpintero");
            Profession p2 = new Profession();
            p2.setIdProfession(2);
            p2.setName("Ebanista");
            return new Profession[]{new Profession() {{
                setIdProfession(0);
                setName("Seleccione Profesión");
            }}, p1, p2};
        } else if (category.getIdCategory() == 2) {
            Profession p3 = new Profession();
            p3.setIdProfession(3);
            p3.setName("Electricista");
            return new Profession[]{new Profession() {{
                setIdProfession(0);
                setName("Seleccione Profesión");
            }}, p3};
        } else if (category.getIdCategory() == 3) {
            Profession p4 = new Profession();
            p4.setIdProfession(4);
            p4.setName("Fontanero");
            Profession p5 = new Profession();
            p5.setIdProfession(5);
            p5.setName("Instalador de aire acondicionado");
            return new Profession[]{new Profession() {{
                setIdProfession(0);
                setName("Seleccione Profesión");
            }}, p4, p5};
        }
        Profession defaultProf = new Profession();
        defaultProf.setIdProfession(0);
        defaultProf.setName("Seleccione Profesión");
        return new Profession[]{defaultProf};
    }

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

    // Métodos para habilitar/deshabilitar la edición en la vista (si se necesitan)
    private void setEditableBasicInfo(boolean editable) {
        // En esta vista la sección básica es solo de visualización.
    }

    private void setEditableAdditionalInfo(boolean editable) {
        cmbTypeProvider.setEnabled(editable);
        cmbCategory.setEnabled(editable);
        cmbProfession.setEnabled(editable);
    }

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

    // Métodos públicos para que el controlador pueda poblar los combo boxes

    public void populateTypeProviders(java.util.List<TypeProvider> typeProviders) {
        cmbTypeProvider.setModel(new DefaultComboBoxModel<>(typeProviders.toArray(new TypeProvider[0])));
    }

    public void populateCategories(java.util.List<Category> categories) {
        cmbCategory.setModel(new DefaultComboBoxModel<>(categories.toArray(new Category[0])));
    }

    public void populateProfessionsForCategory(java.util.List<Profession> professions) {
        if (professions == null || professions.isEmpty()) {
            Profession defaultProf = new Profession();
            defaultProf.setIdProfession(0);
            defaultProf.setName("Seleccione Profesión");
            cmbProfession.setModel(new DefaultComboBoxModel<>(new Profession[]{defaultProf}));
            cmbProfession.setEnabled(false);
        } else {
            cmbProfession.setModel(new DefaultComboBoxModel<>(professions.toArray(new Profession[0])));
            cmbProfession.setEnabled(true);
        }
    }

    public static void main(String[] args) {
        Provider fakeProvider = new Provider();
        fakeProvider.setIdProvider(123);
        fakeProvider.setName("Charly's Carpentry");
        fakeProvider.setAddress("Av. Siempre Viva 742");
        fakeProvider.setGpsLat(-34.6037f);
        fakeProvider.setGpsLong(-58.3816f);
        TypeProvider tp = new TypeProvider();
        tp.setIdTypeProvider(2);
        tp.setType("Tipo 2");
        fakeProvider.setTypeProvider(tp);
        Category cat = new Category();
        cat.setIdCategory(1);
        cat.setName("Categoría 1");
        fakeProvider.setCategory(cat);
        Profession prof = new Profession();
        prof.setIdProfession(3);
        prof.setName("Profesión 3");
        fakeProvider.setProfession(prof);


        SwingUtilities.invokeLater(() -> {
            ProviderProfileView view = new ProviderProfileView(fakeProvider);
            view.setVisible(true);
        });
    }
}
