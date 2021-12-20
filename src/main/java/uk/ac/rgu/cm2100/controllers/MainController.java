package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import uk.ac.rgu.cm2100.MainApp;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.Model;
import uk.ac.rgu.cm2100.model.managers.MenuManager;
import uk.ac.rgu.cm2100.model.managers.OrderManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private List<Model> models;
    @FXML private MenuManagerController menuManagerController;
    @FXML private OrderManagerController orderManagerController;

    // Constructor
    public MainController() {
        this.models = new ArrayList<>();
    }

    // Setter for models
    public void setModels(List<Model> models) {
        this.models = models;
    }

    // Add a single model to models
    public void addModel(Model model) {
        this.models.add(model);
    }

    // Initialize models to the other controllers
    // TODO Find a better way of initializing the models
    public void initializeModels() {
        System.out.println("Initialize models!");
        menuManagerController.setModel((MenuManager) matchClassWithModel(menuManagerController));
        orderManagerController.setModel((OrderManager) matchClassWithModel(orderManagerController));
    }

    private Model matchClassWithModel(Object controller) {
        Model output = null;
        String controllerName = controller.getClass().getSimpleName();
        for (Model model : models) {
            String modelName = model.getClass().getSimpleName();
            if (controllerName.contains(modelName)) {
                System.out.println(modelName);
                output = model;
            }
        }
        return output;
    }


    // Initialize MainController into orderManagerController and menuManagerController
    @FXML private void initialize() {
        System.out.println("Initialize...");
        orderManagerController.assignMainController(this);
        menuManagerController.assignMainController(this);
    }

    // Change scene
    public void changeScene(String scene) throws IOException {
        System.out.println("Change Scene!");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(scene + ".fxml"));
        Parent parent = fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        System.out.println(controller);

        // Match model with controller name
        controller.setModel(matchClassWithModel(controller));

    }

    // Get menu item list from MenuManagerController
    public List<IMenuItem> getMenuItemList() {
        return menuManagerController.model.getItems();
    }


}
