package uk.ac.rgu.cm2100;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import uk.ac.rgu.cm2100.controllers.Controller;
import uk.ac.rgu.cm2100.model.*;
import uk.ac.rgu.cm2100.model.managers.MenuManager;
import uk.ac.rgu.cm2100.model.managers.OrderManager;

/**
 * MainApp.java, edited by Tiberiu Rociu
 * Component 2 Part 1 - 17/12/2021-
 */

public class MainApp extends Application {

    /* These static models can be used across different views while persisting data */
    public static final MenuManager menu = new MenuManager();
    public static final OrderManager orderManager = new OrderManager();

    /* The scene that is currently displayed */
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        // -------------------------------------------------------------------------------
        // TESTING ######################################################################
        // Test orders
        // Test Pizza class
        Pizza margherita = new Pizza("Margherita", Crust.THIN);
        Pizza pepperoni = new Pizza("Pepperoni", Crust.THICK);
        margherita.addToppings(new PizzaTopping("Cheese", 5), new PizzaTopping("Basil", 2));
        pepperoni.addToppings(new PizzaTopping("Cheese", 5), new PizzaTopping("Pepperoni", 10), new PizzaTopping("Tomato Sauce", 5));

        // Test Drink and Side class
        Drink cola = new Drink("Cola", 70);
        Drink whiskey = new Drink("Whiskey", 99);
        Side fries = new Side("Fries", 10);
        Side sweetPotato = new Side("Sweet", 10);

        // Add items to order
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

        order1.addItem(cola);
        order1.addItem(whiskey);

        order2.addItem(sweetPotato);
        order2.addItem(pepperoni);
        order2.addItem(pepperoni);
        order.addItem(pepperoni);
        order.addItem(cola);
        order.addItem(whiskey);
        order.addItem(sweetPotato);
        order.addItem(margherita);
        // Add order to orderManager
        orderManager.addOrder(order);
        orderManager.addOrder(order1);
        orderManager.addOrder(order2);

        // TESTING ######################################################################
        // -------------------------------------------------------------------------------
        scene = new Scene(loadFXML("orderManager", orderManager), 1300, 800);

        // TODO Fix the error when adding the .css file
        // scene.getStylesheets().add("src/main/resources/styles/default.css"); //loads a default
        // stylesheet -
        // helps fix some character encoding issues on some platforms...

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root of the scene using the given fxml file and model
     *
     * @param fxml
     * @param model
     * @throws IOException
     */
    public static void setRoot(String fxml, Model model) throws IOException {

        /* quick hack to strip the .fxml extension if provided by mistake */
        if(fxml.endsWith(".fxml")){
            fxml = fxml.split(".")[0];
        }

        scene.setRoot(loadFXML(fxml, model));
    }

    /**
     * Loads the given fxml file and sets the given model in the controller
     *
     * @param fxml
     * @param model
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml, Model model) throws IOException {

        /* Create the FXMLLoader and load the given fxml file */
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));

        /* Load the fxml into a parent */
        Parent parent = fxmlLoader.load();

        /* Get the controller and set the model */
        Controller controller = fxmlLoader.getController();
        controller.setModel(model);

        /* Return the parent */
        return parent;
    }

    public static void main(String[] args) {
        // Launch window
        launch();
    }

}
