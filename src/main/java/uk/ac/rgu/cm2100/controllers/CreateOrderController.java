package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

import java.util.List;

public class CreateOrderController extends Controller<MenuManager> {

    private MainController mainController;

    @FXML private ListView listItems;
    @FXML private Button btnReturnOrderManager;

    /**
     * Assign the main controller to allow communication between controllers
     * @param mainController
     */
    public void assignMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private void returnToOrderManager() {

    }

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void setModel(MenuManager model) {
        List<IMenuItem> itemsList = model.getItems();
        listItems.setItems(FXCollections.observableList(itemsList));
    }
}
