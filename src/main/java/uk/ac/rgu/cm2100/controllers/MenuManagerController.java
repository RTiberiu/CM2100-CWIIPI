package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuManagerController extends Controller<MenuManager> {

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

    @FXML private void removeItemFromMenu() {
        IMenuItem selected = (IMenuItem) listItems.getSelectionModel().getSelectedItem();
        model.removeItem(selected);
    }

    @FXML private void addPizzaToOrder() throws IOException {
        Stage stage = (Stage) btnAddPizza.getScene().getWindow();
        mainController.changeScene("createPizza", stage);
    }

    @Override
    public void setModel(MenuManager model) {
        this.model = model;

        listItems.setItems(FXCollections.observableList(model.getItems()));
        this.model.addPropertyChangeSupportListener((evt) -> {
            System.out.println("Event triggered from Menu Manager Controller");
            listItems.setItems(FXCollections.observableList(model.getItems()));
        });
    }

}
