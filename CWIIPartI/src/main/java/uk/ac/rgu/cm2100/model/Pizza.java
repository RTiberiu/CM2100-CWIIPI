/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author marks
 */
public class Pizza extends Model implements IMenuItem{

    private String name;
    private Crust crust;
    private List<PizzaTopping> toppings;
    
    private final int BASE_PRICE = 200; //base price for all pizzas before toppings

    // Constructors
    public Pizza() {
        this.toppings = new ArrayList<>();
    }

    public Pizza(String name, Crust crust){
        this.name = name;
        this.crust = crust;
        this.toppings = new ArrayList<>();
    }

    // Setters & getters
    public void setName(String name) {
        this.name = name;
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public List<PizzaTopping> getToppings() {
        return toppings;
    }

    // Methods
    // Add topping and trigger event listeners
    public void addToppings(PizzaTopping... toppings){
        this.toppings.addAll(Arrays.asList(toppings));
        this.firePropertyChange("toppings");
    }

    // Remove topping and trigger event listeners
    public void removeTopping(PizzaTopping topping) {
        this.toppings.remove(topping);
        this.firePropertyChange("toppings");
    }
    
    @Override
    public int price(){
        
        int price = BASE_PRICE;
        
        for(PizzaTopping topping : this.toppings){
            price += topping.getPrice();
        }      
        
        return price;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String description() {
        String toReturn = this.name + "\t" + this.price() + "p\n" + "Crust: " + this.crust + "\nToppings:";
        
        for(PizzaTopping topping : this.toppings){
            toReturn += topping + ", ";
        }
        
        return toReturn;
    }
    
    @Override
    public String toString(){
        return this.name + "\t" + this.price() + "p";
    }


}
