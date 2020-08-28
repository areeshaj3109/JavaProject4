/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinemasystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Dell
 */
public class customer {
    private String name, cnic, address, phone;
    private double amount;

    public customer(String name, String cnic, String address, String phone) {
        this.name = name;
        this.cnic = cnic;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public double getAmount(int n, String h) throws FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile("hallDetails.dat","rw");
        double cost;
        if(h.equals("Silver")) {
            file.seek(4);
            cost = file.readDouble();
        }
        else if(h.equals("Gold")) {
            file.seek(16);
            cost = file.readDouble();
        }
        else {
            file.seek(28);
            cost = file.readDouble();
        }
        amount = cost*n;
        return amount;
    }
    
    
}
