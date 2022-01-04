package uk.ac.rgu.cm2100.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uk.ac.rgu.cm2100.model.Order;
import uk.ac.rgu.cm2100.model.managers.OrderManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderManagerController extends Controller<OrderManager> {

    // Local variables and @FXML elements.
    private MainController mainController;
    @FXML private ListView listOrders, listOrderDetails;
    @FXML private Label totalOrder;
    @FXML private Button btnAddOrder;

    /**
     * Assign the main controller to allow communication between controllers.
     * @param mainController The main controller to be set
     */
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Display the details of each order in the details ListView.
     */
    @FXML private void onOrderSelect() {
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

    /**
     * Change scene to createOrder.fxml by calling .changeScene from MainController
     * @throws IOException
     */
    public void changeToAddOrderScene() throws IOException {
        Stage stage = (Stage) btnAddOrder.getScene().getWindow();
        mainController.changeScene("createOrder", stage);
    }

    /**
     * Get a simple order list from all the available order objects. This will be in the form of:
     * Order 1, Order 2, etc.
     * @return A String List containing the simple orders.
     */
    public List<String> getSimpleOrderList() {
        int numberOfOrders = this.model.getOrders().size();
        List<String> simpleOrderList = new ArrayList<>();
        for (int x = 0; x < numberOfOrders; x ++) {
            simpleOrderList.add("Order " + (x + 1));
        }
        return simpleOrderList;
    }

    /**
     * Set the model for the controller and add an event listener.
     * @param model The manager containing the information.
     */
    @Override
    public void setModel(OrderManager model) {
        this.model = model;
        // Assign all orders to the ListOrders ListView
        listOrders.setItems(FXCollections.observableList(getSimpleOrderList()));
        this.model.addPropertyChangeSupportListener((evt) -> {
            listOrders.setItems(FXCollections.observableList(getSimpleOrderList()));
        });
    }

}


