package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    // TODO Better way to match controller with model, as CreateOrderController can't be assigned
    //  a model this way.
    // Matches an object's name with the name of a model and returns the found model
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
        orderManagerController.setMainController(this);
        menuManagerController.setMainController(this);
    }

    // TODO Change scenes from MainController.
    // TODO Assign mainController with each change in scene
    // Change scene
    public void changeScene(String scene, Stage window) throws IOException {
        System.out.println("Change Scene!");
        // Display the scene
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(scene + ".fxml"));
        Parent parent = fxmlLoader.load();
        window.setScene(new Scene(parent, 1300, 800));

        // Get model for controller and set it
        Controller controller = fxmlLoader.getController();
        Model model = matchClassWithModel(controller);

        // TESTING CREATE ORDER CONTROLLER - DELETE AFTER
        controller.setModel(models.get(0));
        // Assign mainController
        controller.setMainController(this);

//
//        // Match model with controller name
//        controller.setModel(matchClassWithModel(controller));

    }

    // Get menu item list from MenuManagerController
    public List<IMenuItem> getMenuItemList() {
        return menuManagerController.model.getItems();
    }


}
