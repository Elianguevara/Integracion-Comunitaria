package view;

import model.Postulation;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TableModel para mostrar la lista de Postulations en la tabla.
 */
public class PostulationsTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Cliente", "Descripción", "Fecha Propuesta", "Estado"};
    private List<Postulation> postulations = new ArrayList<>();

    @Override
    public int getRowCount() {
        return postulations.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Postulation post = postulations.get(rowIndex);
        switch (columnIndex) {
            case 0:
                // Cliente (vía Petition -> Customer)
                if (post.getPetition() != null && post.getPetition().getCustomer() != null) {
                    return post.getPetition().getCustomer().getName();
                } else {
                    return "";
                }
            case 1:
                // Descripción de la petición
                return (post.getPetition() != null) ? post.getPetition().getDescription() : "";
            case 2:
                // Fecha propuesta (por ejemplo, dateCreate de la postulación)
                return post.getDateCreate();
            case 3:
                // Estado (campo state de la postulación)
                return post.getState();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Carga la lista de Postulations y refresca la tabla.
     */
    public void setPostulations(List<Postulation> postulations) {
        this.postulations = postulations;
        fireTableDataChanged();
    }

    /**
     * Retorna la Postulation en la fila indicada.
     */
    public Postulation getPostulationAt(int rowIndex) {
        return postulations.get(rowIndex);
    }
}
