package controller;

import model.Petition;
import model.Postulation;
import service.PostulationService;
import view.ProviderPostulationsView;

import javax.swing.*;
import java.util.List;

public class ProviderPostulationsController {
    private ProviderPostulationsView view;
    private PostulationService postulationService;

    public ProviderPostulationsController(ProviderPostulationsView view, PostulationService service) {
        this.view = view;
        this.postulationService = service;
        initController();
        loadPostulations(); // Cargar postulaciones al iniciar
    }

    private void initController() {
        view.getBtnCrear().addActionListener(e -> createPostulation());
        view.getBtnActualizar().addActionListener(e -> updatePostulation());
        view.getBtnEliminar().addActionListener(e -> deletePostulation());
        view.getBtnLimpiar().addActionListener(e -> clearForm());
    }

    private void loadPostulations() {
        List<Postulation> list = postulationService.findAll();
        view.setPostulations(list);
    }

    private void createPostulation() {
        try {
            Postulation p = new Postulation();
            // petition seleccionada en el combo
            p.setPetition((Petition) view.getCmbPeticion().getSelectedItem());
            p.setProposal(view.getTxtProposal().getText());
            p.setCost(Integer.parseInt(view.getTxtCost().getText()));
            p.setState(view.getTxtState().getText());
            // ... Asignar dateCreate, idProvider, etc. según tu lógica

            postulationService.save(p);
            JOptionPane.showMessageDialog(view, "Postulación creada con éxito.");
            loadPostulations();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al crear la postulación: " + ex.getMessage());
        }
    }

    private void updatePostulation() {
        int selectedRow = view.getTblPostulaciones().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Seleccione una postulación para actualizar.");
            return;
        }
        Postulation selected = view.getTableModel().getPostulationAt(selectedRow);
        selected.setPetition((Petition) view.getCmbPeticion().getSelectedItem());
        selected.setProposal(view.getTxtProposal().getText());
        selected.setCost(Integer.parseInt(view.getTxtCost().getText()));
        selected.setState(view.getTxtState().getText());
        // ... Ajustar la lógica que necesites

        try {
            postulationService.update(selected);
            JOptionPane.showMessageDialog(view, "Postulación actualizada con éxito.");
            loadPostulations();
            clearForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar la postulación: " + ex.getMessage());
        }
    }

    private void deletePostulation() {
        int selectedRow = view.getTblPostulaciones().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Seleccione una postulación para eliminar.");
            return;
        }
        Postulation selected = view.getTableModel().getPostulationAt(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(view,
                "¿Está seguro de eliminar la postulación?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                postulationService.delete(selected.getIdPostulation());
                JOptionPane.showMessageDialog(view, "Postulación eliminada con éxito.");
                loadPostulations();
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error al eliminar la postulación: " + ex.getMessage());
            }
        }
    }

    private void clearForm() {
        view.getCmbPeticion().setSelectedIndex(-1);
        view.getTxtProposal().setText("");
        view.getTxtCost().setText("");
        view.getTxtState().setText("");
        view.getTblPostulaciones().clearSelection();
    }
}
