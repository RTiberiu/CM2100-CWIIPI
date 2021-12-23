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
import java.util.ArrayList;
import java.util.List;

public class CreateOrderController extends Controller<OrderManager> {

    private MainController mainController;
    private List<IMenuItem> currentItems;

    @FXML private ListView listItems, listCurrentOrder;
    @FXML private Button btnReturnOrderManager, btnAddToOrder, btnRemoveItem;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    // TODO Change to mainScene by invoking .changeToMainScreen(window);
    @FXML private void returnToOrderManager() throws IOException {
        Stage stage = (Stage) btnReturnOrderManager.getScene().getWindow();
        // mainController.changeScene("orderManager", window);
        System.out.println(stage);
        mainController.changeToMainScreen(stage);
    }

    @FXML private void onRemoveItemFromOrder() {
        Object item = listCurrentOrder.getSelectionModel().getSelectedItem();
        currentItems.remove(item);
        listCurrentOrder.setItems(FXCollections.observableList(currentItems));
    }

    @FXML private void onAddItemToOrder() {
        // Get selected item and add it to the order
        Object item = listItems.getSelectionModel().getSelectedItem();
        currentItems.add((IMenuItem) item);
        listCurrentOrder.setItems(FXCollections.observableList(currentItems));
    }

    @FXML private void onAddOrder() throws IOException {
        if (currentItems.size() != 0) {
            Order order = new Order();
            currentItems.forEach((iMenuItem -> order.addItem(iMenuItem)));
            model.addOrder(order);
            returnToOrderManager();
        }
    }

    @Override
    public void setModel(OrderManager model) {
        this.model = model;
        List<IMenuItem> itemsList = mainController.getAvailableItems();
        listItems.setItems(FXCollections.observableList(itemsList));

        // Initialize the current arraylist for the items
        currentItems = new ArrayList<>();

        // Add event listener
        this.model.addPropertyChangeSupportListener((evt) -> {
            System.out.println("Event listener triggered - CreateOrderController");
            listItems.setItems(FXCollections.observableList(mainController.getAvailableItems()));
        });
    }
}
