package model;

/**
 * Entidad que representa la calificación (puntaje y comentario) otorgada a un proveedor.
 */
public class GradeProvider extends BaseEntity {
    // Identificador único de la calificación de proveedor.
    private Integer idGradeProvider;

    // Proveedor que recibió la calificación.
    private Provider provider;

    // Grado o puntaje otorgado al proveedor.
    private Grade grade;

    // Comentario o reseña sobre el proveedor.
    private String comment;

    public Integer getIdGradeProvider() {
        return this.idGradeProvider;
    }

    public void setIdGradeProvider(Integer idGradeProvider) {
        this.idGradeProvider = idGradeProvider;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
