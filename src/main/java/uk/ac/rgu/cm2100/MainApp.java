package uk.ac.rgu.cm2100;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import uk.ac.rgu.cm2100.controllers.MainController;
import uk.ac.rgu.cm2100.model.*;
import uk.ac.rgu.cm2100.model.managers.MenuManager;
import uk.ac.rgu.cm2100.model.managers.OrderManager;

/**
 * MainApp.java, edited by Tiberiu Rociu
 * Component 2 Part 1 - 17/12/2021
 */

public class MainApp extends Application {

    /* These static models can be used across different views while persisting data */
    public static final MenuManager menu = new MenuManager();
    public static final OrderManager orderManager = new OrderManager();

    /* The scene that is currently displayed */
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        /*
         * -------------------------------------------------------------------------------
         * Add sample information to the project section -- START
         * -------------------------------------------------------------------------------
         */
        // Create pizza, drinks and sides
        Pizza margherita = new Pizza("Margherita", Crust.THIN);
        Pizza pepperoni = new Pizza("Pepperoni", Crust.THICK);
        margherita.addToppings(new PizzaTopping("Cheese", 5), new PizzaTopping("Basil", 2));
        pepperoni.addToppings(new PizzaTopping("Cheese", 5), new PizzaTopping("Pepperoni", 10), new PizzaTopping("Tomato Sauce", 5));
        Drink cola = new Drink("Cola", 70);
        Drink whiskey = new Drink("Whiskey", 99);
        Side fries = new Side("Fries", 10);
        Side sweetPotato = new Side("Sweet Potato", 10);

        // Add items and toppings to the Menu Manager
        menu.addItem(margherita);
        menu.addItem(pepperoni);
        menu.addItem(cola);
        menu.addItem(whiskey);
        menu.addItem(fries);
        menu.addItem(sweetPotato);
        menu.addTopping(new PizzaTopping("Cheese", 5));
        menu.addTopping(new PizzaTopping("Basil", 2));
        menu.addTopping(new PizzaTopping("Pepperoni", 10));
        menu.addTopping(new PizzaTopping("Tomato Sauce", 5));

        // Create orders and add items to them
        Order order = new Order();
        Order order1 = new Order();
        Order order2 = new Order();
        order.addItem(pepperoni);
        order.addItem(cola);
        order.addItem(whiskey);
        order.addItem(whiskey);
        order.addItem(fries);
        order.addItem(sweetPotato);
        order.addItem(margherita);
        order.addItem(pepperoni);
        order.addItem(cola);
        order.addItem(whiskey);
        order.addItem(sweetPotato);
        order.addItem(margherita);
        order1.addItem(cola);
        order1.addItem(whiskey);
        order2.addItem(sweetPotato);
        order2.addItem(pepperoni);
        order2.addItem(pepperoni);

        // Add orders to Order Manager
        orderManager.addOrder(order);
        orderManager.addOrder(order1);
        orderManager.addOrder(order2);
        /*
         * -------------------------------------------------------------------------------
         *  Add sample information to the project section -- END
         * -------------------------------------------------------------------------------
         */


        // Get fxmlLoader for mainScene.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("mainScene.fxml"));

        // Create the main controller and add data to it before adding it to the fxml file
        MainController mainController = new MainController();
        mainController.linkFxmlWithModel("createOrder", orderManager);
        mainController.linkFxmlWithModel("createPizza", menu);
        mainController.linkFxmlWithModel("menuManager", menu);
        mainController.linkFxmlWithModel("orderManager", orderManager);
        mainController.linkFxmlWithModel("addToppings", menu);

        // Assign the main controller to the fxml file, and load the file
        fxmlLoader.setController(mainController);
        Parent parent = fxmlLoader.load();

        // Display screen
        stage.setScene(new Scene(parent, 1300, 700));
        stage.setTitle("Restaurant Manager");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
