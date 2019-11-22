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
public class Platinum extends State {

   
        
        @Override
       public double doPurchase(double amount){
           return amount;
       
       }
       
        @Override
       public State Validate(State st, double balance){
        
           if(balance<10000){
               return new Silver();
           }
           else if(balance<20000){
               return new Gold();
               
           }
           
           return st;
           
       }
}

