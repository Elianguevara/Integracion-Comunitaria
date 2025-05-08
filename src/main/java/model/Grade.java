package model;

/**
 * Entidad que representa un grado o puntaje de calificación, posiblemente con un color asociado.
 */
public class Grade extends BaseEntity {
    // Identificador único de la calificación (grado).
    private Integer idGrade;

    // Número o valor de la calificación.
    private Integer gradeNumber;

    // Color asociado a esta calificación.
    private Color color;

    public Integer getIdGrade() {
        return this.idGrade;
    }

    public void setIdGrade(Integer idGrade) {
        this.idGrade = idGrade;
    }

    public Integer getGradeNumber() {
        return this.gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
