package controller;

import model.Category;
import model.Profession;
import model.Provider;
import model.TypeProvider;
import model.User;
import service.CategoryService;
import service.ProfessionService;
import service.ProviderService;
import service.ServiceException;
import service.TypeProviderService;
import view.ProviderProfileView;

import java.util.List;

/**
 * Controlador para la vista ProviderProfileView.
 * Permite cargar los datos en los combo boxes y gestionar las acciones (guardar, eliminar).
 */
public class ProviderProfileController {

    private Provider provider;
    private ProviderProfileView view;
    private User loggedUser;  // Usuario que navega

    private ProviderService providerService; // Servicio para Provider (si se requiere)
    private TypeProviderService typeProviderService;
    private CategoryService categoryService;
    private ProfessionService professionService;

    /**
     * Constructor que recibe el Provider y el usuario que navega.
     */
    public ProviderProfileController(Provider provider, User loggedUser) {
        this.provider = provider;
        this.loggedUser = loggedUser;
        // Instanciamos los servicios correspondientes
        this.typeProviderService = new TypeProviderService();
        this.categoryService = new CategoryService();
        this.professionService = new ProfessionService();
        // Si se cuenta con un ProviderService, se podría inyectar aquí:
        // this.providerService = new ProviderService();
    }

    // Asigna la vista para que el controlador interactúe con ella.
    public void setView(ProviderProfileView view) {
        this.view = view;
    }

    /**
     * Carga los datos iniciales para los combo boxes desde la base de datos.
     */
    public void initData() {
        try {
            List<TypeProvider> typeProviders = typeProviderService.findAll();
            List<Category> categories = categoryService.findAll();
            // Inicialmente, el combo de Profession se deja vacío.
            view.populateTypeProviders(typeProviders);
            view.populateCategories(categories);
            view.populateProfessionsForCategory(null);
        } catch (ServiceException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
    }

    /**
     * Método a llamar cuando se selecciona una categoría en la vista.
     * Carga las profesiones correspondientes a la categoría seleccionada.
     * @param category La categoría seleccionada.
     */
    public void onCategorySelected(Category category) {
        try {
            // Se asume que ProfessionService tiene el método findByCategory(int idCategory)
            List<Profession> professions = professionService.findByCategory(category.getIdCategory());
            view.populateProfessionsForCategory(professions);
        } catch (ServiceException e) {
            System.err.println("Error al cargar profesiones: " + e.getMessage());
        }
    }

    /**
     * Maneja la acción de guardar o actualizar un Provider.
     */
    public void handleSave(Provider provider) {

        // Invoca el servicio para guardar/actualizar el provider.
        // Por ejemplo: Provider saved = providerService.save(provider);
    }

    /**
     * Maneja la acción de eliminar un Provider.
     */
    public void handleDelete(Provider provider) {

        // Invoca el servicio para eliminar el provider.
        // Por ejemplo: boolean success = providerService.delete(provider.getIdProvider());
    }
}
