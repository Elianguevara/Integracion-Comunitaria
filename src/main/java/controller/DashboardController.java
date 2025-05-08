package controller;

import model.*;
import service.CustomerService;
import service.PetitionService;
import service.ServiceException;
import service.TypePetitionService;
import view.*;

import javax.swing.*;
import java.util.List;

public class DashboardController {

    private DashboardView dashboardView;
    private String role;
    private User loggedUser;         // Usuario que inició sesión
    private Customer loggedCustomer; // Si el rol es cliente
    private Provider loggedProvider; // Si el rol es proveedor

    public DashboardController(User loggedUser, Customer loggedCustomer, String role) {
        this.loggedUser = loggedUser;
        this.loggedCustomer = loggedCustomer;
        this.role = role;
        // Crea la vista del Dashboard con el nombre y rol del usuario
        this.dashboardView = new DashboardView(loggedUser.getName(), role);
        initController();
    }

    /**
     * Configura los listeners para los botones del menú lateral.
     */
    private void initController() {
        // Listener para "Mis Peticiones"
        dashboardView.getBtnMisPeticiones().addActionListener(e -> openMyPetitions());

        // Listener para "Perfil"
        dashboardView.getBtnPerfil().addActionListener(e -> openProfile());

        // Listener para "Mis Ofertas"
        dashboardView.getBtnMisOfertas().addActionListener(e -> openProviderOffers());

        // Listener para "Mis Postulaciones"
        dashboardView.getBtnMisPostulaciones().addActionListener(e -> openProviderPostulations());
    }

    /**
     * Muestra la vista del Dashboard.
     */
    public void showDashboard() {
        // Antes de hacer visible el Dashboard, cargamos todas las peticiones si es cliente
        if (role.equalsIgnoreCase("cliente")) {
            try {
                List<Petition> allPetitions = new PetitionService().getAllPetitionsWithCustomers();
                dashboardView.populateCenterPanelWithPetitions(allPetitions);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        dashboardView.setVisible(true);
    }

    /**
     * Abre la vista de "Mis Peticiones" pasándole el objeto Customer completo.
     */
    public void openMyPetitions() {
        if (role.equalsIgnoreCase("cliente") && loggedCustomer != null) {
            // 1. Crea la vista de peticiones para clientes
            MyPetitionsView petitionsView = new MyPetitionsView(loggedCustomer, role);

            // 2. Carga la lista de tipos de petición para poblar el combo
            TypePetitionService typePetitionService = new TypePetitionService();
            try {
                List<TypePetition> listaTipos = typePetitionService.getAllTypePetitions();
                petitionsView.populateTypeCombo(listaTipos);
            } catch (ServiceException ex) {
                System.err.println("Error al cargar tipos de petición: " + ex.getMessage());
            }

            // 3. Crea el controlador de peticiones
            PetitionService petitionService = new PetitionService();
            MyPetitionsController controller = new MyPetitionsController(
                    petitionsView, petitionService, loggedCustomer
            );

            // 4. Muestra la vista de peticiones
            petitionsView.setVisible(true);
        } else {
            System.out.println("El rol actual no permite abrir 'Mis Peticiones' o no hay cliente logueado.");
        }
    }

    /**
     * Abre la vista de "Perfil" según el rol del usuario.
     */
    public void openProfile() {
        if (role.equalsIgnoreCase("proveedor")) {
            Provider provider = loggedProvider;
            if (provider == null) {
                // Ejemplo dummy en caso de no tenerlo
                provider = new Provider();
                provider.setIdProvider(123);
                provider.setName("Charly's Carpentery");
                provider.setAddress("Av. Siempre Viva 742");
                provider.setGpsLat(-34.6037f);
                provider.setGpsLong(-58.3816f);
            }
            ProviderProfileController providerController = new ProviderProfileController(provider, loggedUser);
            ProviderProfileView profileView = new ProviderProfileView(provider);
            providerController.setView(profileView);
            providerController.initData(); // Cargar datos en los combo boxes
            profileView.setVisible(true);

        } else if (role.equalsIgnoreCase("cliente")) {
            CustomerProfileController customerController = new CustomerProfileController(new CustomerService());
            CustomerProfileView profileView = new CustomerProfileView(loggedCustomer, customerController);
            profileView.setVisible(true);
        } else {
            System.out.println("Rol desconocido. No se puede abrir perfil.");
        }
    }

    /**
     * Abre la vista de "Mis Ofertas" para que el proveedor pueda hacer propuestas.
     */
    public void openProviderOffers() {
        if (role.equalsIgnoreCase("proveedor")) {
            ProviderOffersView offersView = new ProviderOffersView();
            // Aquí puedes agregar lógica adicional para cargar datos en la tabla de ofertas.
            offersView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    dashboardView,
                    "La vista 'Mis Ofertas' está disponible solo para proveedores.",
                    "Acceso Denegado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    /**
     * Abre la vista de "Mis Postulaciones" para hacer seguimiento a las propuestas.
     */
    public void openProviderPostulations() {
        if (role.equalsIgnoreCase("proveedor")) {
            ProviderPostulationsView postulationsView = new ProviderPostulationsView();
            // Aquí puedes agregar lógica adicional para cargar datos en la tabla de postulaciones.
            postulationsView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    dashboardView,
                    "La vista 'Mis Postulaciones' está disponible solo para proveedores.",
                    "Acceso Denegado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
