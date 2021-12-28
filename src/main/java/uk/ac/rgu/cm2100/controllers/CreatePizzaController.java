package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.FontSmoothingType;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.Crust;
import uk.ac.rgu.cm2100.model.Pizza;
import uk.ac.rgu.cm2100.model.PizzaTopping;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CreatePizzaController extends Controller<MenuManager> {

    private MainController mainController;
    private ArrayList<PizzaTopping> toppings;
    @FXML private ListView listToppings, listCurrentPizza;
    @FXML private Button btnReturnToMenuManager, btnAddToppings;
    @FXML private ChoiceBox choiceCrust;
    @FXML private TextArea textPizzaName;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML private void addToppingToPizza() {
        PizzaTopping selected = (PizzaTopping) listToppings.getSelectionModel().getSelectedItem();
        if (selected != null) {
            toppings.add(selected);
            listCurrentPizza.setItems(FXCollections.observableList(toppings));
        }
    }

    @FXML private void removeToppingFromPizza() {
        PizzaTopping selected = (PizzaTopping) listCurrentPizza.getSelectionModel().getSelectedItem();
        if (selected != null) {
            toppings.remove(selected);
            listCurrentPizza.setItems(FXCollections.observableList(toppings));
        }
    }

    // TODO Add toppings arrayList to a new Pizza object
    @FXML private void addPizzaToMenu() throws IOException {
        // Create pizza, add items to it, add it to the menu and switch to menu manager
        if (toppings.size() != 0 && choiceCrust.getValue() != null && textPizzaName.getText().length() != 0) {
             Pizza pizza = new Pizza(textPizzaName.getText(), (Crust) choiceCrust.getValue());
             toppings.forEach(pizza::addToppings);
             model.addItem(pizza);
             returnToMenuManager();
        }
    }

    @FXML private void returnToMenuManager() throws IOException {
        Stage stage = (Stage) btnReturnToMenuManager.getScene().getWindow();
        mainController.changeToMainScreen(stage, 1);
    }

    @FXML private void addToppings() throws IOException {
        Stage stage = (Stage) btnAddToppings.getScene().getWindow();
        mainController.changeScene("addToppings", stage);
    }


    @Override
    public void setModel(MenuManager model) {
        this.model = model;

        // Adding toppings to list and crust choices
        listToppings.setItems(FXCollections.observableList(model.getToppings()));
        choiceCrust.setItems(FXCollections.observableList(new ArrayList<>(Arrays.asList(Crust.values()))));
        toppings = new ArrayList<>();
        this.model.addPropertyChangeSupportListener((evt) -> {
            System.out.println("Event listener triggered Create Pizza Controller");
            listToppings.setItems(FXCollections.observableList(model.getToppings()));
        });
    }
}
