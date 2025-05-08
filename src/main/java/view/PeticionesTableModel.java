package view;

import model.Petition;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class PeticionesTableModel extends AbstractTableModel {

    private final String[] columnNames = {"Descripción", "Tipo", "Fecha Inicio", "Fecha Fin"};
    private List<Petition> petitions = new ArrayList<>();

    @Override
    public int getRowCount() {
        return petitions.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Petition p = petitions.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getDescription();
            case 1:
                // Si el objeto TypePetition tiene un método toString() que devuelve su nombre,
                // se puede usar directamente; de lo contrario, se muestra el nombre.
                return p.getTypePetition() != null ? p.getTypePetition().getName() : "";
            case 2:
                return p.getDateSince();
            case 3:
                return p.getDateUntil();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void setPetitions(List<Petition> petitions) {
        this.petitions = petitions;
        fireTableDataChanged();
    }

    public void addPetition(Petition petition) {
        this.petitions.add(petition);
        fireTableRowsInserted(petitions.size() - 1, petitions.size() - 1);
    }

    public Petition getPetitionAt(int rowIndex) {
        return petitions.get(rowIndex);
    }
}
