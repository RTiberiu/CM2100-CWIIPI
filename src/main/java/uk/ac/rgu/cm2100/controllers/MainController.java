package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
    @FXML private TabPane mainTabPane;
    @FXML private Tab tabMenuManager;
    @FXML private Tab tabOrderManager;

    // Constructor
    public MainController() {
        this.models = new HashMap<>();
    }

    // Add fxml file names and their respective model to a map
    public void linkFxmlWithModel(String fxml, Model model) {
        models.put(fxml, model);
    }

    // Assign main controllers and set models
    @FXML private void initialize() throws IOException {
        System.out.println("Initialize first screen...");
        Controller orderController = orderManagerController;
        Controller menuController = menuManagerController;
        orderController.setMainController(MainController.this);
        menuController.setMainController(MainController.this);
        orderController.setModel(models.get("orderManager"));
        //  TODO Might have to delete the setModel for menuManager
        menuController.setModel(models.get("menuManager"));
    }

    // Change scene, trigger setModel and setMainController
    public void changeScene(String scene, Stage stage) throws IOException {
        System.out.println("Change Scene!");
        // Display the scene
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(scene + ".fxml"));
        Parent parent = fxmlLoader.load();
        stage.getScene().setRoot(parent);

        // Get FXML file controller
        Controller controller = fxmlLoader.getController();

        // If Controller's model is null, assign model and set the main Controller
        if (controller.model == null) {
            // Assign mainController
            controller.setMainController(MainController.this);
            controller.setModel(models.get(scene));
        }
    }

    // Resume to main screen
    public void changeToMainScreen(Stage stage, int tab) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("mainScene.fxml"));
        fxmlLoader.setController(MainController.this);
        Parent parent = fxmlLoader.load();
        stage.getScene().setRoot(parent);

        // Select tab
        mainTabPane.getSelectionModel().select(tab);
        System.out.println("Changed to main screen!");
    }

    // Allow other Controllers to get items from MenuManagerController
    public List<IMenuItem> getAvailableItems() {
        return menuManagerController.model.getItems();
    }


}
