package service;

/**
 * Excepción personalizada para la capa de servicios, que envuelve errores ocurridos en las operaciones de negocio.
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(String message) {
        super(message);
    }
}
