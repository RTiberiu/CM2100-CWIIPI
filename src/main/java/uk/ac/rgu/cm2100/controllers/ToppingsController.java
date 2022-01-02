package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.PizzaTopping;
import uk.ac.rgu.cm2100.model.managers.MenuManager;
import java.io.IOException;
import java.util.List;

public class ToppingsController extends Controller<MenuManager> {

    // Local variables and @FXML elements.
    private MainController mainController;
    @FXML private ListView listTotalToppings;
    @FXML private TextArea textToppingName, textToppingPrice;
    @FXML private Button btnReturnToPizzaScene;

    /**
     * Assign the main controller to allow communication between controllers.
     * @param mainController The main controller to be set
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Add topping to the model, if it doesn't exist already (same name and same price).
      */
    @FXML private void addTopping() {
        // Check if the name field and the price field are not empty
        String toppingName = textToppingName.getText().trim();
        if(toppingName.length() != 0 && textToppingPrice.getText().length() != 0) {
            try {
                // Check if the same topping with the same price exists
                List<PizzaTopping> toppings = model.getToppings();
                PizzaTopping pizzaTopping = new PizzaTopping(toppingName, Integer.parseInt(textToppingPrice.getText()));
                boolean existing = false;
                for (PizzaTopping topping : toppings) {
                    if (topping.toString().contains(pizzaTopping.toString())) {
                        existing = true;
                    }
                }

                // Add topping if already not in the list
                if (!existing) {
                    model.addTopping(pizzaTopping);
                }
            } catch (Exception InvocationTargetException) {
                System.out.println("Topping price is not an integer!");
            }
        }
    }

    /**
     * Remove topping from model if selection is not null.
     */
    @FXML private void removeTopping() {
        PizzaTopping selected = (PizzaTopping) listTotalToppings.getSelectionModel().getSelectedItem();
        if (selected != null) {
            model.removeTopping(selected);
        }
    }

    /**
     * Return to the pizza scene (createPizza.fxml).
     * @throws IOException
     */
    @FXML private void returnToPizzaScene() throws IOException {
        Stage stage = (Stage) btnReturnToPizzaScene.getScene().getWindow();
        mainController.changeScene("createPizza", stage);
    }

    /**
     * Set the model for the controller and add an event listener.
     * @param model The manager containing the information.
     */
    @Override
    public void setModel(MenuManager model) {
        this.model = model;

        listTotalToppings.setItems(FXCollections.observableList(model.getToppings()));
        this.model.addPropertyChangeSupportListener((evt) -> {
            listTotalToppings.setItems(FXCollections.observableList(model.getToppings()));
        });
    }
}
