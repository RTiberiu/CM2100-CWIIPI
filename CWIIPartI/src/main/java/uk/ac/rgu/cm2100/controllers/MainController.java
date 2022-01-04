package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.MainApp;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.Model;
import java.io.IOException;
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

    /**
     * Add fxml file names and their respective model to a map. This links each fxml file to a
     * model.
     * @param fxml String representing the fxml file.
     * @param model The manager containing the information.
     */
    public void linkFxmlWithModel(String fxml, Model model) {
        models.put(fxml, model);
    }


    /**
     * When mainScene.fxml is first initialized, call setMainController() and setModel() for the
     *  tab's (orderManager.fxml and menuManager.fxml) controllers. This ensures data is available
     *  for both tabs.
     * @throws IOException
     */
    @FXML private void initialize() {
        Controller orderController = orderManagerController;
        Controller menuController = menuManagerController;
        orderController.setMainController(MainController.this);
        menuController.setMainController(MainController.this);
        orderController.setModel(models.get("orderManager"));
        menuController.setModel(models.get("menuManager"));
    }

    /**
     * Change scene and trigger setModel() and setMainController() if they're unassigned.
     * @param scene String representing the scene to be changed to
     * @param stage Current stage
     * @throws IOException
     */
    public void changeScene(String scene, Stage stage) throws IOException {
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

    /**
     * Change to the mainScene.fxml and display the desired tab
     * @param stage Current stage
     * @param tab Integer representing the tab to be displayed
     * @throws IOException
     */
    public void changeToMainScreen(Stage stage, int tab) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("mainScene.fxml"));
        fxmlLoader.setController(MainController.this);
        Parent parent = fxmlLoader.load();
        stage.getScene().setRoot(parent);

        // Select tab
        mainTabPane.getSelectionModel().select(tab);
    }

    /**
    * Allow other controllers to get all IMenuItems from MenuManagerController
     * @return A list containing all IMenuItems
     */
    public List<IMenuItem> getAvailableItems() {
        return menuManagerController.model.getItems();
    }


}
