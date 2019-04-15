/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import model.Localization;
import model.Osa;
import model.Product;
import model.Tilaus;

/**
 *
 * @author RJulin
 */

/*
* Rakentaa käyttöliittymän Taloustiedot sivun
* Sivulla tarkastellaan myyntien ja tilauksien tietoja.
*/

public class Tab4 extends Tab {
    
    Scene scene;
    TabPane tabPane;
    ObservableList<Product> data;
    List<Product> tilausrivit;
    List<Osa> osaLista;
    ObservableList<Osa> osaData;
    List<Tilaus> tilausLista;
    ObservableList<Tilaus> tilausData;
    
    private final GridPane grid4 = new GridPane();
    private final Button btnSales = new Button();
    private final Button btnSalesPurchases = new Button();
    private final Button btnSummary = new Button();
    
    public Tab4(){
       createTab4();
    }
    
    private void createTab4() {
        grid4.setHgap(20); // Horizontal gap
        grid4.setVgap(0); // Vertical gap

        btnSales.setPrefSize(200, 100);
        //btnSales.setOnAction((event) -> {
        //    showSalesChart();
        //});
        grid4.add(btnSales, 0, 0);
        
        btnSalesPurchases.setPrefSize(200, 100);
     
        //btnSalesPurchases.setOnAction((event) -> {
        //    showPurchasesChart();
        //});
        grid4.add(btnSalesPurchases, 0, 1);

        btnSummary.setPrefSize(200, 100);
        grid4.add(btnSummary, 0, 2);

        this.setContent(grid4);
        
        localizationSetText();
    }
    
    public void localizationSetText() {
        Localization localization = Localization.getInstance();
        
        btnSales.setText(localization.getBundle().getString("btn_sales"));  // = .setText("Myynti");
        btnSalesPurchases.setText(localization.getBundle().getString("btn_sales_purchases"));  // = .setText("Osto");
        btnSummary.setText(localization.getBundle().getString("btn_summary"));  // = .setText("Yhteenveto ja budjetti");
        
        //lineChart.setTitle("Myyntitiedot");
        //series.setName("Myynnit");
        
        //series.setName("Ostot");
        //lineChart.setTitle("Ostotiedot");
    }
    
}
