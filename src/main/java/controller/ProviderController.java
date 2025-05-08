package controller;

import model.Provider;
import service.ProviderService;
import java.util.List;

/**
 * Controlador para la entidad Provider. Se encarga de recibir los datos de la vista
 * y delegar las operaciones de negocio a la capa de servicio (ProviderService).
 * Sigue el patrón MVC puro: no interactúa con la UI Swing directamente (no maneja eventos).
 * En su lugar, la vista utiliza un gestor de vistas (por ejemplo, ProviderViewManager)
 * que se comunica con este controlador.
 * La ocurrencia de errores en las operaciones se indica mediante excepciones controladas;
 * dichas excepciones son propagadas para que el gestor de vistas las capture
 * y notifique adecuadamente al usuario.
 */
public class ProviderController {
    private final ProviderService providerService;

    /**
     * Constructor que inicializa el controlador con el servicio de proveedores.
     * @param providerService instancia de ProviderService para realizar operaciones relacionadas con proveedores.
     */
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     * Crea un nuevo proveedor con los datos proporcionados desde la vista.
     * La validación de los datos se realiza en la capa de servicio.
     * @param provider objeto Provider con la información del nuevo proveedor.
     * @return el objeto Provider creado con su ID asignado.
     * @throws Exception si ocurre un error en la creación (datos inválidos o error de base de datos).
     */
    public Provider createProvider(Provider provider) throws Exception {
        // Delegar al servicio la lógica de creación y validación
        Provider createdProvider = providerService.createProvider(provider);
        return createdProvider;
    }

    /**
     * Actualiza un proveedor existente con los datos proporcionados.
     * La validación de los datos se realiza en la capa de servicio.
     * @param provider objeto Provider con los datos actualizados (debe contener un ID válido).
     * @return el objeto Provider actualizado.
     * @throws Exception si ocurre un error en la actualización (por ejemplo, el proveedor no existe o datos inválidos).
     */
    public Provider updateProvider(Provider provider) throws Exception {
        // Delegar al servicio la lógica de actualización y validación
        Provider updatedProvider = providerService.updateProvider(provider);
        return updatedProvider;
    }

    /**
     * Elimina un proveedor por su identificador único.
     * @param providerId identificador (ID) del proveedor a eliminar.
     * @throws Exception si ocurre un error en la eliminación (por ejemplo, si no se encuentra el proveedor).
     */
    public void deleteProvider(int providerId) throws Exception {
        // Delegar al servicio la lógica de eliminación
        providerService.deleteProvider(providerId);
    }

    /**
     * Busca y devuelve un proveedor por su identificador.
     * @param providerId identificador (ID) del proveedor a buscar.
     * @return el objeto Provider correspondiente al ID, o null si no se encuentra.
     * @throws Exception si ocurre un error durante la búsqueda.
     */
    public Provider getProviderById(int providerId) throws Exception {
        // Delegar al servicio la búsqueda del proveedor
        return providerService.getProviderById(providerId);
    }

    /**
     * Obtiene una lista de todos los proveedores.
     * @return lista de proveedores existentes.
     * @throws Exception si ocurre un error al obtener la lista de proveedores.
     */
    public List<Provider> getAllProviders() throws Exception {
        // Delegar al servicio la obtención de todos los proveedores
        return providerService.getAllProviders();
    }
}
