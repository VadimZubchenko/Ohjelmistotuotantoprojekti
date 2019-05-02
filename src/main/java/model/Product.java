/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RJulin
 */
public class Product {
    
    //Paketti
    private String name;
    private int amount;
    private double price;

    
    //Tilausrivi
    private Tilaus_rivi tilausrivi;

    public Product(Object objekti, int Samount) {
        if (objekti instanceof Paketti) {
            this.name = ((Paketti) objekti).getPaketinNimi();
            this.price =((Paketti) objekti).getPaketinHinta();

        }
        if (objekti instanceof Osa) {
            this.name = ((Osa) objekti).getOsaNimi();
            //this.amount = ((Osa) objekti).getVarastoMaara();
            this.price = ((Osa) objekti).getOsaHinta();
        }
        this.amount = Samount;
        this.tilausrivi = new Tilaus_rivi(objekti, Samount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int Samount) {
        this.amount = Samount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double Sprice) {
        this.price = Sprice;
    }


    public Tilaus_rivi getTilaus_rivi() {
        return tilausrivi;
    }

}
