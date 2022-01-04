package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.managers.MenuManager;
import java.io.IOException;


public class MenuManagerController extends Controller<MenuManager> {

    // Local variables and @FXML elements.
    private MainController mainController;
    @FXML private ListView listItems;
    @FXML private Button btnAddPizza;

    /**
     * Assign the main controller to allow communication between controllers
     * @param mainController
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Remove the selected IMenuItem from the model.
     */
    @FXML private void removeItemFromMenu() {
        IMenuItem selected = (IMenuItem) listItems.getSelectionModel().getSelectedItem();
        model.removeItem(selected);
    }

    /**
     * Change scene to createPizza.fxml by calling .changeScene from MainController
     * @throws IOException
     */
    @FXML private void addPizzaToOrder() throws IOException {
        Stage stage = (Stage) btnAddPizza.getScene().getWindow();
        mainController.changeScene("createPizza", stage);
    }

    /**
     * Set the model for the controller and add an event listener.
     * @param model The manager containing the information.
     */
    @Override
    public void setModel(MenuManager model) {
        this.model = model;
        listItems.setItems(FXCollections.observableList(model.getItems()));
        this.model.addPropertyChangeSupportListener((evt) -> {
            listItems.setItems(FXCollections.observableList(model.getItems()));
        });
    }

}
