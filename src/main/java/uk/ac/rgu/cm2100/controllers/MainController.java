package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.MainApp;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.Model;
import uk.ac.rgu.cm2100.model.Order;
import uk.ac.rgu.cm2100.model.managers.MenuManager;
import uk.ac.rgu.cm2100.model.managers.OrderManager;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainController {
    private HashMap<String, Model> models;
    @FXML private MenuManagerController menuManagerController;
    @FXML private OrderManagerController orderManagerController;

    // Constructor
    public MainController() {
        this.models = new HashMap<>();
    }

    // Add fxml file names and their respective model to a map
    public void linkFxmlWithModel(String fxml, Model model) {
        models.put(fxml, model);
    }

    // Assign main controllers and set models
    public void initializeFirstScreen() {
        System.out.println("Initialize first screen...");
        Controller orderController = orderManagerController;
        Controller menuController = menuManagerController;
        orderController.setMainController(MainController.this);
        menuController.setMainController(MainController.this);
        orderController.setModel(models.get("orderManager"));
        menuController.setModel(models.get("menuManager"));
    }

    // Change scene, trigger setModel and setMainController
    public void changeScene(String scene, Stage window) throws IOException {
        System.out.println("Change Scene!");
        // Display the scene
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(scene + ".fxml"));
        Parent parent = fxmlLoader.load();
        window.getScene().setRoot(parent);

        // Get model for controller and set it
        Controller controller = fxmlLoader.getController();

        // Assign mainController
        controller.setMainController(MainController.this);

        // Assign model from models
        controller.setModel(models.get(scene));
    }

    // TODO Figure out why the listview from orderManager is not displayed, even though the
    //  information prints from inside .setModel()
    // Resume to main screen
    public void changeToMainScreen(Stage window) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("mainScene.fxml"));
        Parent parent = fxmlLoader.load();
        window.getScene().setRoot(parent);

        MainController.this.initializeFirstScreen();
    }

    // Allow other Controllers to get items from MenuManagerController
    public List<IMenuItem> getAvailableItems() {
        return menuManagerController.model.getItems();
    }


}
