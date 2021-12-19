package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.MainApp;
import uk.ac.rgu.cm2100.model.Order;
import uk.ac.rgu.cm2100.model.managers.OrderManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static uk.ac.rgu.cm2100.MainApp.menu;

public class OrderManagerController extends Controller<OrderManager> {

    @FXML
    private ListView listOrders, listOrderDetails, listItems;

    @FXML
    private Label totalOrder;

    @FXML
    private Button btnAddOrder, btnReturnOrderManager;

    public void onOrderSelect() {
        System.out.println("Triggered!");
        // Get order from selected String
        String selected = (String) listOrders.getSelectionModel().getSelectedItem();

        if (selected != null) {
            int orderIndex = Integer.parseInt(selected.split(" ")[1]) - 1;
            Order currentOrder = OrderManagerController.this.model.getOrders().get(orderIndex);

            // Get array from toString method
            ArrayList<String> individualItems =
                    new ArrayList<String>(Arrays.asList(currentOrder.toString().split("\\n")));

            // Add all items besides the last one (which is total)
            listOrderDetails.setItems(FXCollections.observableList(individualItems.subList(0,
                    individualItems.size() - 1)));

            // Add total from individualItems array
            totalOrder.setText(individualItems.get(individualItems.size() - 1));
        }
    }

    public void changeToAddOrderScene() throws IOException {
        Parent parent = FXMLLoader.load(MainApp.class.getResource("createOrder.fxml"));
        Stage window = (Stage) btnAddOrder.getScene().getWindow();
        window.setScene(new Scene(parent, 1300, 800));
        // Initialize list
        // initializeAddOrderScene();
    }

    public void initializeAddOrderScene() {
        System.out.println("Initialize!");
        // TODO Study more about MVC and eventListeners, as I'm sure I'm doing something wrong
        // TODO Check if there's a better way of changing the scenes
        // TODO After that, find a way to update the ListView listItems with the menu MenuManager
        //  . Currently I have access to menu because it's imported from MainApp, but I'm not sure
        //  if that's the best solution.
    }

    // TODO Delete this and remove it from the createOrder.fxml. I'm using this to check if the
    //  ListView listItems is null.
    public void testList() {
        System.out.println("Clicked this bitch!");
        System.out.println(listItems);
    }

    public void returnToOrderManager() throws IOException {
        Parent parent = FXMLLoader.load(MainApp.class.getResource("orderManager.fxml"));
        Stage window = (Stage) btnReturnOrderManager.getScene().getWindow();
        window.setScene(new Scene(parent, 1300, 800));
    }

    @Override
    public void setModel(OrderManager model) {
        this.model = model;

        // Get all orders
        int numberOfOrders = OrderManagerController.this.model.getOrders().size();
        List<String> simpleOrderList = new ArrayList<>();
        for (int x = 0; x < numberOfOrders; x ++) {
            simpleOrderList.add("Order " + (x + 1));
        }
        listOrders.setItems(FXCollections.observableList(simpleOrderList));
        this.model.addPropertyChangeListener((evt -> {
            listOrders.setItems(FXCollections.observableList(simpleOrderList));
            OrderManagerController.this.onOrderSelect();
        }));
    }

}


