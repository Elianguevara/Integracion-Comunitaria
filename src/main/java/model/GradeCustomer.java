package model;

/**
 * Entidad que representa la calificación otorgada a un cliente por un proveedor.
 */
public class GradeCustomer extends BaseEntity {
    // Identificador único de la calificación de un cliente.
    private Integer idGradeCustomer;

    // Cliente que recibió la calificación.
    private Customer customer;

    // Grado o puntaje otorgado al cliente.
    private Grade grade;

    // Comentario o reseña sobre el cliente.
    private String comment;

    public Integer getIdGradeCustomer() {
        return this.idGradeCustomer;
    }

    public void setIdGradeCustomer(Integer idGradeCustomer) {
        this.idGradeCustomer = idGradeCustomer;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
