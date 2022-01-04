/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.rgu.cm2100.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Provides a base class for MVC models, adding PropertyChangeSupport
 * @author mark
 */
public abstract class Model {
    
    protected PropertyChangeSupport pcs;
    
    public Model(){
        this.pcs = new PropertyChangeSupport(this);
    }


    public final void addPropertyChangeSupportListener(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Fire the property change to the event listeners
     * @param name The name of the property changed
     */
    protected final void firePropertyChange(String name) {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, name, null, null);
        this.pcs.firePropertyChange(evt);
    }
}
