package controller;

import model.Customer;
import model.Petition;
import model.TypePetition;
import service.PetitionService;
import view.MyPetitionsView;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class MyPetitionsController {

    private MyPetitionsView view;
    private PetitionService petitionService;
    private Customer loggedCustomer;

    public MyPetitionsController(MyPetitionsView view, PetitionService petitionService, Customer loggedCustomer) {
        this.view = view;
        this.petitionService = petitionService;
        this.loggedCustomer = loggedCustomer;
        initController();
        loadPetitions();  // Carga las peticiones del cliente
    }

    private void initController() {
        view.getBtnCrear().addActionListener(e -> createPetition());
        view.getBtnActualizar().addActionListener(e -> updatePetition());
        view.getBtnEliminar().addActionListener(e -> deletePetition());
        view.getBtnLimpiar().addActionListener(e -> clearForm());
    }

    /**
     * Carga las peticiones del cliente y actualiza la tabla.
     */
    private void loadPetitions() {
        try {
            List<Petition> petitions = petitionService.findByCustomerId(loggedCustomer.getIdCustomer());
            view.setPeticiones(petitions);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al cargar las peticiones: " + ex.getMessage());
        }
    }

    /**
     * Crea una nueva petición con los datos ingresados en la vista.
     */
    private void createPetition() {
        try {
            Petition petition = new Petition();
            petition.setDescription(view.getTxtDescripcion().getText());

            // Obtiene el TypePetition seleccionado en el combo
            TypePetition selectedType = view.getSelectedTypePetition();
            if (selectedType != null) {
                petition.setTypePetition(selectedType);
            } else {
                JOptionPane.showMessageDialog(view, "Por favor seleccione un tipo de petición.");
                return;
            }

            // Convierte las fechas (en formato yyyy-MM-dd) a Date
            petition.setDateSince(Date.valueOf(view.getTxtFechaInicio().getText()));
            petition.setDateUntil(Date.valueOf(view.getTxtFechaFin().getText()));

            // Asigna el cliente logueado directamente (objeto completo)
            petition.setCustomer(loggedCustomer);

            // Envía la petición al servicio para guardarla
            Petition created = petitionService.save(petition);
            JOptionPane.showMessageDialog(view, "Petición creada exitosamente (ID=" + created.getIdPetition() + ").");
            loadPetitions();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al crear la petición: " + ex.getMessage());
        }
    }

    /**
     * Actualiza la petición seleccionada.
     */
    private void updatePetition() {
        int selectedRow = view.getTablePeticiones().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una petición para actualizar.");
            return;
        }
        try {
            Petition selectedPetition = view.getTableModel().getPetitionAt(selectedRow);
            selectedPetition.setDescription(view.getTxtDescripcion().getText());

            // Obtiene el TypePetition seleccionado
            TypePetition selectedType = view.getSelectedTypePetition();
            if (selectedType != null) {
                selectedPetition.setTypePetition(selectedType);
            }

            selectedPetition.setDateSince(Date.valueOf(view.getTxtFechaInicio().getText()));
            selectedPetition.setDateUntil(Date.valueOf(view.getTxtFechaFin().getText()));

            // El cliente se mantiene igual
            petitionService.update(selectedPetition);

            JOptionPane.showMessageDialog(view, "Petición actualizada exitosamente.");
            loadPetitions();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar la petición: " + ex.getMessage());
        }
    }

    /**
     * Elimina la petición seleccionada.
     */
    private void deletePetition() {
        int selectedRow = view.getTablePeticiones().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Seleccione una petición para eliminar.");
            return;
        }
        try {
            Petition selectedPetition = view.getTableModel().getPetitionAt(selectedRow);

            int confirm = JOptionPane.showConfirmDialog(view,
                    "¿Está seguro de eliminar esta petición?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                petitionService.delete(selectedPetition.getIdPetition());
                JOptionPane.showMessageDialog(view, "Petición eliminada exitosamente.");
                loadPetitions();
                clearForm();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar la petición: " + ex.getMessage());
        }
    }

    /**
     * Limpia los campos del formulario y des-selecciona la fila de la tabla.
     */
    private void clearForm() {
        view.getTxtDescripcion().setText("");
        view.getCmbTipo().setSelectedIndex(-1);
        view.getTxtFechaInicio().setText("");
        view.getTxtFechaFin().setText("");
        view.getTablePeticiones().clearSelection();
    }
}
