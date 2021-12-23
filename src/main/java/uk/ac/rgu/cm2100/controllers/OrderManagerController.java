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
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.Order;
import uk.ac.rgu.cm2100.model.managers.OrderManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static uk.ac.rgu.cm2100.MainApp.main;
import static uk.ac.rgu.cm2100.MainApp.menu;

public class OrderManagerController extends Controller<OrderManager> {

    private MainController mainController;
    @FXML private ListView listOrders, listOrderDetails;
    @FXML private Label totalOrder;
    @FXML private Button btnAddOrder;

    /**
     * Assign the main controller to allow communication between controllers
     * @param mainController
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

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

    // Change scene by calling .changeScene from MainController
    public void changeToAddOrderScene() throws IOException {
        Stage stage = (Stage) btnAddOrder.getScene().getWindow();
        System.out.println("Main Controller from button change");
        System.out.println(mainController);
        mainController.changeScene("createOrder", stage);
    }
    
    public List<String> getSimpleOrderList() {
        int numberOfOrders = this.model.getOrders().size();
        List<String> simpleOrderList = new ArrayList<>();
        for (int x = 0; x < numberOfOrders; x ++) {
            simpleOrderList.add("Order " + (x + 1));
        }
        return simpleOrderList;
    }

    @Override
    public void setModel(OrderManager model) {
        System.out.println("Setting OrderManagerController!");
        this.model = model;
        // Get all orders
        System.out.println(this.model);
        System.out.println(model);
        listOrders.setItems(FXCollections.observableList(getSimpleOrderList()));
        this.model.addPropertyChangeSupportListener((evt) -> {
            System.out.println("Event listener from OrderManagerController!");
            System.out.println("Before setting items");
            System.out.println(listOrders.getItems());
            listOrders.setItems(FXCollections.observableList(getSimpleOrderList()));
            System.out.println("After setting items");
            System.out.println(listOrders.getItems());
        });
    }

}


