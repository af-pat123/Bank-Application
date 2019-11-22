/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.io.File;
import java.io.PrintWriter;

/**
 *
 * @author a297pate
 */
public class Manager {
    private String Username;
    private String Password;
    private String Role;

    public Manager(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
        this.Role="Manager";
    }
    
    public void addCustomer(String Username, String Password){
        
        
        File customerFile=new File(Username+ ".txt");
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(customerFile.getPath(), "UTF-8");
        }
        catch(Exception e){
            System.out.println("file fail");
        }
       
        writer.println(Username);
        writer.println(Password);
        writer.println("Customer");
        writer.println("100");
        writer.close();
        
        
    }
    
    
    public void deleteCustomer(String Username){
        
       File file = new File(Username + ".txt"); 
          
                if(file.delete()) 
                { 
                    System.out.println("File deleted successfully"); 
                } 
                else
                { 
                    System.out.println("Failed to delete the file"); 
                }  
    }
    
    
}
