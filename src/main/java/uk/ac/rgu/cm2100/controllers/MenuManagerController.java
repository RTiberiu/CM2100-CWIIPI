package uk.ac.rgu.cm2100.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

public class MenuManagerController extends Controller<MenuManager> {

    private MainController mainController;
    @FXML private ListView listItems;

    /**
     * Assign the main controller to allow communication between controllers
     * @param mainController
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void setModel(MenuManager model) {
        this.model = model;
    }

}
