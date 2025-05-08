package controller;

import model.Customer;
import service.CustomerService;
import service.ServiceException;

/**
 * Controlador para la vista CustomerProfileView.
 * Encapsula la lógica de interacción con el CustomerService.
 */
public class CustomerProfileController {

    private CustomerService customerService;

    public CustomerProfileController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Método que maneja la acción de 'guardar' (crear o actualizar un Customer).
     */
    public void handleSave(Customer customer) {
        try {
            if (customer.getIdCustomer() == null) {
                // Si no tiene ID, asumimos que es un nuevo registro:
                Customer creado = customerService.save(customer);
                System.out.println("Se creó el nuevo Customer con ID: " + creado.getIdCustomer());
            } else {
                // Si tiene ID, entonces es una actualización:
                Customer actualizado = customerService.update(customer);
                System.out.println("Se actualizó el Customer con ID: " + actualizado.getIdCustomer());
            }
        } catch (ServiceException e) {
            System.err.println("Error en handleSave: " + e.getMessage());
            // Aquí podrías notificar a la vista (JOptionPane.showMessageDialog...)
        }
    }

    /**
     * Método que maneja la acción de eliminar un Customer existente.
     */
    public void handleDelete(Customer customer) {
        try {
            if (customer.getIdCustomer() == null) {
                throw new ServiceException("No se puede eliminar un Customer sin ID asignado.");
            }
            boolean exito = customerService.delete(customer.getIdCustomer());
            if (exito) {
                System.out.println("Customer eliminado con ID: " + customer.getIdCustomer());
            } else {
                System.out.println("No se encontró Customer con ID: " + customer.getIdCustomer());
            }
        } catch (ServiceException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    /**
     * Método para buscar un Customer por ID.
     */
    public Customer handleFindById(int id) {
        try {
            return customerService.findById(id);
        } catch (ServiceException e) {
            System.err.println("Error al buscar Customer con ID: " + id + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Método para listar todos los Customers.
     */
    public void handleListAll() {
        try {
            customerService.findAll().forEach(c -> {
                System.out.println("Customer: " + c.getIdCustomer() + " - " + c.getName());
            });
        } catch (ServiceException e) {
            System.err.println("Error al listar todos los Customers: " + e.getMessage());
        }
    }
}
