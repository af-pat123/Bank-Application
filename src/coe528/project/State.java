/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author afzal
 */
public abstract class State {
    
    public abstract double doPurchase(double amount);
 
    
    
    public abstract State Validate(State st, double balance);
        
        
    
    
}
