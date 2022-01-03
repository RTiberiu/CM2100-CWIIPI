package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.Order;
import uk.ac.rgu.cm2100.model.managers.OrderManager;
import java.io.IOException;

public class CreateOrderController extends Controller<OrderManager> {

    // Local variables and @FXML elements.
    private MainController mainController;
    private Order currentOrder;
    @FXML private ListView listItems, listCurrentOrder;
    @FXML private Button btnReturnOrderManager;

    /**
     * Assign the main controller to allow communication between controllers
     * @param mainController
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    /**
     * Change to the Order Manager tab in the main screen (mainScene.fxml).
     * @throws IOException
     */
    @FXML private void returnToOrderManager() throws IOException {
        Stage stage = (Stage) btnReturnOrderManager.getScene().getWindow();
        mainController.changeToMainScreen(stage, 0);
    }

    /**
     * Remove the selected IMenuItem from the currentOrder object
     */
    @FXML private void onRemoveItemFromOrder() {
        IMenuItem item = (IMenuItem) listCurrentOrder.getSelectionModel().getSelectedItem();
        currentOrder.removeItem(item);
    }

    /**
     * Add the selected IMenuItem to the currentOrder object
     */
    @FXML private void onAddItemToOrder() {
        // Get selected item and add it to the order if it's not null
        IMenuItem item =(IMenuItem) listItems.getSelectionModel().getSelectedItem();
        if (item != null) {
            currentOrder.addItem(item);
        }
    }

    // If order is not empty, add it to the model

    /**
     * Add order to the model, if the order is not empty.
     * @throws IOException
     */
    @FXML private void onAddOrder() throws IOException {
        if (currentOrder.getItems().size() != 0) {
            model.addOrder(currentOrder);
            returnToOrderManager();
        }
    }

    /**
     * Set the model for the controller, initialize the currentOrder object, and add the event
     * listeners.
     * @param model The manager containing the information.
     */
    @Override
    public void setModel(OrderManager model) {
        this.model = model;
        listItems.setItems(FXCollections.observableList(mainController.getAvailableItems()));

        // Initialize new Order
        currentOrder = new Order();

        /*
         * Add an event listener on the model to track any changes in the available items and
         * update the listItems ListView
         */
        this.model.addPropertyChangeSupportListener((evt) -> {
            listItems.setItems(FXCollections.observableList(mainController.getAvailableItems()));
        });

        /*
         * Add an event listener on the current order to track any changes in items and update
         * the listCurrentOrder ListView
         */
        this.currentOrder.addPropertyChangeSupportListener((evt) -> {
            listCurrentOrder.setItems(FXCollections.observableList(currentOrder.getItems()));
        });
    }
}
