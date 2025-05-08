package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProviderOffersView extends JFrame {

    private JTable tblPeticiones;
    private JButton btnEnviarPropuesta, btnVerDetalles;

    public ProviderOffersView() {
        setTitle("Mis Ofertas");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("Peticiones Disponibles", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        mainPanel.add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Cliente", "Ubicación", "Descripción", "Fecha Inicio", "Fecha Fin"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        tblPeticiones = new JTable(model);
        tblPeticiones.setRowHeight(30);
        tblPeticiones.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(tblPeticiones);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnEnviarPropuesta = new JButton("Enviar Propuesta");
        btnVerDetalles = new JButton("Ver Detalles");

        styleButton(btnEnviarPropuesta);
        styleButton(btnVerDetalles);

        btnPanel.add(btnVerDetalles);
        btnPanel.add(btnEnviarPropuesta);

        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}


