package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.PizzaTopping;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ToppingsController extends Controller<MenuManager> {

    private MainController mainController;
    @FXML private ListView listTotalToppings;
    @FXML private TextArea textToppingName, textToppingPrice;
    @FXML private Button btnAddTopping, btnRemoveTopping, btnReturnToPizzaScene;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private void addTopping() {
        if(textToppingName.getText().length() != 0 && textToppingPrice.getText().length() != 0) {
            try {
                PizzaTopping pizzaTopping = new PizzaTopping(textToppingName.getText(),
                        Integer.parseInt(textToppingPrice.getText()));
                model.addTopping(pizzaTopping);
            } catch (Exception InvocationTargetException) {
                System.out.println("Topping price is not an integer!");
            }
        }
    }

    @FXML private void removeTopping() {
        PizzaTopping selected = (PizzaTopping) listTotalToppings.getSelectionModel().getSelectedItem();
        if (selected != null) {
            model.removeTopping(selected);
        }
    }

    @FXML private void returnToPizzaScene() throws IOException {
        Stage stage = (Stage) btnReturnToPizzaScene.getScene().getWindow();
        mainController.changeScene("createPizza", stage);
    }

    @Override
    public void setModel(MenuManager model) {
        this.model = model;

        listTotalToppings.setItems(FXCollections.observableList(model.getToppings()));
        this.model.addPropertyChangeSupportListener((evt) -> {
            System.out.println("Event listener from ToppingsController");
            listTotalToppings.setItems(FXCollections.observableList(model.getToppings()));
        });
    }
}
