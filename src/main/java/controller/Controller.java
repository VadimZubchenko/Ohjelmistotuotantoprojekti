/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Henkilosto;
import model.Paketti;
import model.TietokonekauppaDAO;
import model.Tilaus_rivi;
import view.View;

/**
 *
 * @author zigis
 */
public class Controller {
    View gui;
    TietokonekauppaDAO dao;
    
    public Controller(View gui) {
        this.gui = gui;
        this.dao = new TietokonekauppaDAO();
    }
    
    public Controller() {
        this.dao = new TietokonekauppaDAO();
    }
    
    public void getAllComputerNames(ComboBox box) {
        for (Paketti paketti : dao.readPaketit()) {
            box.getItems().add(paketti.getPaketinNimi());
        }
        box.getSelectionModel().selectFirst();
    }
    
    public void createComputer() {
        
    }
    
    public void createOrder(List<Tilaus_rivi> tilaukset) {
        //Tarkista että listassa on ainakin yksi tilaus
        if (tilaukset.isEmpty() == true) {
            //Ilmoita viewille että tilausrivejä ei ole yhtään
            //gui.setMessagebox("Tilaus lista on tyhjä!");
        } else {
            dao.luoTilaus(tilaukset);
        }

    }
    
    public void createUser(Henkilosto henkilo) {
        dao.luoHenkilo(henkilo);
    }
    
    public void getPrice(TextField PriceTxt) {
        Double hinta = dao.haePaketinHinta(gui.getValitunPaketinIndex() + 1);
        System.out.println(gui.getValitunPaketinIndex());
        
       PriceTxt.setText("" + hinta * gui.getOrderAmount());
    }
    
}
