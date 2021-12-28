/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.model.managers;

import java.util.ArrayList;
import java.util.List;
import uk.ac.rgu.cm2100.model.Model;
import uk.ac.rgu.cm2100.model.IMenuItem;
import uk.ac.rgu.cm2100.model.PizzaTopping;

/**
 *
 * @author mark
 */
public class MenuManager extends Model {
    
    private List<IMenuItem> items;
    
    private List<PizzaTopping> availableToppings;
    
    public MenuManager(){
        this.items = new ArrayList<>();
        this.availableToppings = new ArrayList<>();
    }
    
    public void addItem(IMenuItem item){
        this.items.add(item);
        this.firePropertyChange("items");
    }

    // If item is present in list, remove it and call and trigger event
    public void removeItem(IMenuItem item) {
        if (this.items.contains(item)) {
            this.items.remove(item);
            this.firePropertyChange("items");
        }
    }
    
    public List<IMenuItem> getItems(){
        return this.items;
    }
    
    public void addTopping(PizzaTopping topping){
        this.availableToppings.add(topping);
        this.firePropertyChange("availableToppings");
    }
    
    public List<PizzaTopping> getToppings(){
        return this.availableToppings;
    }

    // If item is present in list, remove it and call and trigger event
    public void removeTopping(PizzaTopping item) {
        if (this.availableToppings.contains(item)) {
            this.availableToppings.remove(item);
            this.firePropertyChange("availableToppings");
        }
    }
}
