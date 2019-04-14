/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Henkilosto;
import model.Osa;
import model.Paketti;
import model.TietokonekauppaDAO;
import model.Tilaus;
import model.Tilaus_rivi;
import view.View;
import view.loginView;

/**
 * 
 * @author Sami Sikkilä
 */
public class Controller {
    private static Controller INSTANCE = null;
    /**
     * Käyttöliittymä
     */
    View gui;
    /**
     * Tietokannan kanssa asioiva DataAccesObject
     */
    TietokonekauppaDAO dao;
    
    private Controller() {
        this.dao = new TietokonekauppaDAO();
    }
    
    /**
     * Konstruktori
     * @param gui Ohjemiston käyttöliittymäluokka
     */
    /*
    public Controller(View gui) {
        this.gui = gui;
        this.dao = new TietokonekauppaDAO();
    }
    */
    
    public static synchronized Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }
    
    public void setGui(View gui) {
        this.gui = gui;
    }
    
    public void reconnectDAO() {
        this.dao = new TietokonekauppaDAO();
    }
    
    public void enableLoginScreen(loginView loginscreen) {
        Boolean bTemp = this.dao.isSessionFactoryConnected();
        //Disabloi painikkeet jos tietokantayhteyttä ei ole luotu
        //btnLogin.setEnabled(bTemp);
    }
    
    public void loginUser(loginView loginscreen, Stage primaryStage, String nimi, String salasana) {
        Henkilosto user = dao.haeKayttaja(nimi, salasana);
        
        //user.getRooli();
        
        //Kirjautuminen epäonnistui
        if (user == null) {
            //Ilmoita virheestä ja tyhjennä tekstikentät
            loginscreen.setErrorMessage("Salasana väärin.");
        } else {
            
            //Luo view
            Stage Viewclass = new Stage();
            View v = new View(this);
            setGui(v);
            v.start(Viewclass);
            primaryStage.close();
        }
    }
    
    /**
     * Hae tietokoneiden nimet ComboBoxiin
     * @param box ComboBox, johon nimet halutaan tuoda
     */
    public void getAllComputerNames(ComboBox box) {
        for (Paketti paketti : dao.readPaketit()) {
            box.getItems().add(paketti);
        }
        box.getSelectionModel().selectFirst();
    }
    
    /**
     * Funktio hakee käyttöliittymässä olevista tietokentistä tarvittavat tiedot ja luo niiden perusteella tilauksen
     */
    public void createOrder() {
        //Luo Tilaus_rivi lista productista
        List<Tilaus_rivi> tilaukset = gui.getTilaukset();
        
        //Tarkista että listassa on ainakin yksi tilaus
        if (tilaukset.isEmpty() == true) {
            //Ilmoita viewille että tilausrivejä ei ole yhtään
            //gui.setMessagebox("Tilaus lista on tyhjä!");
        } else {
            dao.luoTilaus(tilaukset);
        }
    }
    
    /**
     * Luo ohjelmistoon käyttäjä
     * @param henkilo  Henkilö, joka halutaan luoda
     */
    public void createUser(Henkilosto henkilo) {
        dao.luoHenkilo(henkilo);
    }
    
    /**
     * Hakee käyttöliittymästä valitun paketin ja hakee tietokannasta haluttuun tekstikenttään kyseisen paketin hinnan
     * @param PriceTxt Tekstikenttä johon hinta halutaan tuoda
     */
    public void getPrice(TextField PriceTxt) {
        double hinta = dao.haePaketinHinta(gui.getValitunPaketinIndex() + 1);
        System.out.println(gui.getValitunPaketinIndex());
        
        PriceTxt.setText("" + hinta);
    }
    
    /**
     * Hakee tietokannasta kaikki tiettyä tyyppiä vastaavat osat
     * @param tyyppi Osat jotka tietokannasta halutaan hakea
     * @return Lista halutuista osista
     */
    public ArrayList<Osa> getOsat(String tyyppi) {
        ArrayList<Osa> osat = new ArrayList<>();
        
        for (Osa osa : dao.getOsat(tyyppi)) {
            osat.add(new Osa(osa.getOsaNimi(), osa.getOsaHinta(), osa.getVarastoMaara(), osa.getTyyppi()));
        }
        return osat;
    }
    
    /**
     * Hae tilaukset tietokannasta
     * @return Lista kaikista tietokannassa olevista tilauksista
     */
    public ArrayList<Tilaus> getTilaukset() {
        ArrayList<Tilaus> tilaukset = new ArrayList<>();
        
        for (Tilaus tilaus : dao.readTilaukset()) {
            tilaukset.add(tilaus);
        }
        return tilaukset;
    }
}
