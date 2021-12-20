package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

public class MenuManagerController extends Controller<MenuManager> {

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

    @Override
    public void setModel(MenuManager model) {
        System.out.println("Menu manager model was set!");
        this.model = model;
    }

}
