/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * Overview: The responsibility of this class is to implement all the methods
 * that a customer can do in the bank account program. This class is mutable as
 * the level instance is not final and can be changed
 *
 * Abstract Function: AF(r) = r.customerFile.userName + r.customerFile.password
 * + r.account.getBalance + r.getLevel
 *
 * Rep Invariant: RI(r) = r.customerFile!= NULL && (First two lines of
 * r.CustomerFile)!=null r.account!=null && r.level!=null
 *
 * @author a297pate
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer {

    private State Level;
    private final BankAccount account;
    private final File customerFile;


    /**
     *Effects: Creates a customer and initializes a level based on the balance
     *Requires: Balance and customerFile cannot be null
     */
    public Customer(String Username, String Password, Double Balance, File customerFile) {

        if (Balance < 10000) {
            this.Level = new Silver();
        } else if (Balance < 02000) {
            this.Level = new Gold();
        } else {
            this.Level = new Platinum();
        }

        this.account = new BankAccount(Balance);
        this.customerFile = customerFile;

    }

    /**
     *Effects:  Checks if the string inputed is a valid positive number, if it is it adds it to the acount balance and returns true, otherwise it returns false
     *Modifies: Changes the instance variable balance in account and also returns a new level 
     */
    public boolean depositMoney(String amount) {

        double DAmount;

        try {
            DAmount = Double.valueOf(amount);

        } catch (NumberFormatException e) {
            return false;

        }

        if (DAmount < 0) {
            return false;
        }

        this.account.setBalance(this.getBalance() + DAmount);
        this.Level = this.Level.Validate(this.Level, this.getBalance());
        return true;

    }

    /**
     *Effects:  Checks if the string inputed is a valid withdraw request, if it is it subtracts it from the acount balance and returns true, otherwise it returns false
     *Modifies: Changes the instance variable balance in account and also returns a new level 
     */
    public boolean withdrawMoney(String amount) {

        double DAmount;

        try {
            DAmount = Double.valueOf(amount);

        } catch (NumberFormatException e) {
            return false;

        }

        if (DAmount <= this.getBalance()) {
            this.account.setBalance(this.getBalance() - DAmount);
            this.Level = this.Level.Validate(this.Level, this.getBalance());
            return true;
        }
        return false;
    }

    /**
     *Effects: Returns balance in the Bank Account
     *Requires this.account!=null and this.acount.Balance!= null
     */
    public double getBalance() {
        return this.account.getBalance();
    }

    /**
     *Effects: Returns string representation of level
     *Requires: this.Level!=null 
     */
    public String getLevel() {
        if (this.Level instanceof Silver) {
            return ("Silver");
        } else if (this.Level instanceof Gold) {
            return ("Gold");
        }

        return ("Platinum");

    }

    /**
     *Effects:  Checks if the string inputed is a valid purchase amount, if it is it subtracts a number from the acount balance and returns true, otherwise it returns false
     *Modifies: Changes the instance variable balance in account and also returns a new level 
     */
    public boolean makePurchase(String amount) {

        double DAmount;

        try {
            DAmount = Double.valueOf(amount);

        } catch (NumberFormatException e) {
            return false;

        }

        if (DAmount <= 50) {
            return false;
        }

        DAmount = this.Level.doPurchase(DAmount);

        if (DAmount <= this.account.getBalance()) {
            this.account.setBalance(this.account.getBalance() - DAmount);
            this.Level = this.Level.Validate(this.Level, this.getBalance());
            return true;
        }

        return false;

    }

    /**
     *Effects:  Writes the new balance to the file 
     *Modifies: Changes the text in this.customerFile
     */
    public void Logout() {

        Scanner read = null;
        try {
            read = new Scanner(this.customerFile);
        } catch (FileNotFoundException ex) {
            System.out.println("file fail");
        }
        String Username = read.nextLine();
        String Password = read.nextLine();
        read.nextLine();
        read.close();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.customerFile.getPath(), "UTF-8");
        } catch (Exception e) {
            System.out.println("file fail");
        }

        writer.println(Username);
        writer.println(Password);
        writer.println("Customer");
        writer.println(this.account.getBalance());
        writer.close();

    }

    /**
     *Effects:  Returns a string representation of the customer
     */
    @Override
    public String toString() {
        Scanner read = null;
        try {
            read = new Scanner(this.customerFile);
        } catch (FileNotFoundException ex) {
            System.out.println("file fail");
        }
        String Username = read.nextLine();
        String Password = read.nextLine();

        return "Username: " + Username + ", Password: " + Password + ", Account Balance: " + this.account.getBalance() + ", Level: " + this.getLevel();
    }

    /**
     *Effects:  Check if the class is represented properly
     */
    public boolean repOk() {

        if (this.customerFile == null) {
            return false;
        }

        Scanner read = null;
        try {
            read = new Scanner(this.customerFile);
        } catch (FileNotFoundException ex) {
            System.out.println("file fail");
        }
        String Username = read.nextLine();
        String Password = read.nextLine();

        if (Username != null && Password != null && this.Level != null && this.account != null) {
            return true;
        }

        return false;

    }

}
