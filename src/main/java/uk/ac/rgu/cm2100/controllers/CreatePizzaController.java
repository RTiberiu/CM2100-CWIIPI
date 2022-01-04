package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.Crust;
import uk.ac.rgu.cm2100.model.Pizza;
import uk.ac.rgu.cm2100.model.PizzaTopping;
import uk.ac.rgu.cm2100.model.managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * CreatePizzaController.java, created by Tiberiu Rociu
 * Component 2 Part 1 - 17/12/2021
 */
public class CreatePizzaController extends Controller<MenuManager> {

    // Local variables and @FXML elements.
    private MainController mainController;
    private Pizza currentPizza;
    @FXML private ListView listToppings, listCurrentPizza;
    @FXML private Button btnReturnToMenuManager, btnAddToppings;
    @FXML private ChoiceBox choiceCrust;
    @FXML private TextArea textPizzaName;

    /**
     * Assign the main controller to allow communication between controllers
     * @param mainController
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Add the selected PizzaTopping to the currentPizza object
     */
    @FXML private void addToppingToPizza() {
        PizzaTopping selected = (PizzaTopping) listToppings.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentPizza.addToppings(selected);
        }
    }

    /**
     * Remove the selected PizzaTopping from the currentPizza object
     */
    @FXML private void removeToppingFromPizza() {
        PizzaTopping selected = (PizzaTopping) listCurrentPizza.getSelectionModel().getSelectedItem();
        if (selected != null) {
            currentPizza.removeTopping(selected);
        }
    }

    /**
     * When all information needed is available, set the name of the pizza, set the crust, add it
     * to the model and change to the Menu Manager tab in the main scene (mainScene.fxml) by
     * calling returnToMenuManager().
     * @throws IOException
     */
    @FXML private void addPizzaToMenu() throws IOException {
        String pizzaName = textPizzaName.getText().trim();
        // Check if any fields are empty before creating the pizza
        if (currentPizza.getToppings().size() != 0 && choiceCrust.getValue() != null && pizzaName.length() != 0) {
            currentPizza.setName(pizzaName);
            currentPizza.setCrust((Crust) choiceCrust.getValue());
            model.addItem(currentPizza);
            returnToMenuManager();
        }
    }

    /**
     * Change to the Menu Manager tab in the main screen (mainScene.fxml).
     * @throws IOException
     */
    @FXML private void returnToMenuManager() throws IOException {
        Stage stage = (Stage) btnReturnToMenuManager.getScene().getWindow();
        mainController.changeToMainScreen(stage, 1);
    }

    /**
     * Change scene to addToppings.fxml by calling .changeScene from MainController
     * @throws IOException
     */
    @FXML private void addToppings() throws IOException {
        Stage stage = (Stage) btnAddToppings.getScene().getWindow();
        mainController.changeScene("addToppings", stage);
    }

    /**
     * Set the model for the controller, initialize the currentPizza object, and add the event
     * listeners.
     * @param model The manager containing the information.
     */
    @Override
    public void setModel(MenuManager model) {
        this.model = model;

        // Adding toppings to list and crust choices
        listToppings.setItems(FXCollections.observableList(model.getToppings()));
        choiceCrust.setItems(FXCollections.observableList(new ArrayList<>(Arrays.asList(Crust.values()))));

        // Initialize the current pizza object
        currentPizza = new Pizza();

        /*
         * Add an event listener on the model to track any changes in the available toppings and
         * update the listToppings ListView
         */
        this.model.addPropertyChangeSupportListener((evt) -> {
            listToppings.setItems(FXCollections.observableList(model.getToppings()));
        });

        /*
         * Add an event listener on the current pizza to track any changes in toppings and update
         * the listCurrentPizza ListView
         */
        this.currentPizza.addPropertyChangeSupportListener((evt) -> {
            listCurrentPizza.setItems(FXCollections.observableList(currentPizza.getToppings()));
        });
    }
}
