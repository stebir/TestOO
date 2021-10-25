package gui;
   
import business.Freizeitbad;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class FreizeitbaederAnwendersystem {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     				= new  Pane();
    private Label lblEingabe    	 	= new Label("Eingabe");
    private Label lblAnzeige   	 	    = new Label("Anzeige");
    private Label lblName 				= new Label("Name:");
    private Label lblGeoeffnetVon   	= new Label("Geöffnet von:");
    private Label lblGeoeffnetBis  	 	= new Label("Geöffnet bis:");
    private Label lblBeckenlaenge   	= new Label("Beckenlänge:");
    private Label lblWassTemperatur  	= new Label("Wassertemperatur:");
    private TextField txtName 	 		= new TextField();
    private TextField txtGeoeffnetVon	= new TextField();
    private TextField txtGeoeffnetBis	= new TextField();
    private TextField txtBeckenlaenge	= new TextField();
    private TextField txtWassTemperatur	= new TextField();
    private TextArea txtAnzeige  		= new TextArea();
    private Button btnEingabe 		 	= new Button("Eingabe");
    private Button btnAnzeige 		 	= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  	= new MenuBar();
    private Menu mnDatei             	= new Menu("Datei");
    private MenuItem mnItmCsvExport 	= new MenuItem("csv-Export");
    private MenuItem mnItmTxtExport 	= new MenuItem("txt-Export");
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ Freizeitbad
    private Freizeitbad freizeitbad;
    
    public FreizeitbaederAnwendersystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 560, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Freizeitbädern");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
    	// Labels
       	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24);
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(310);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblGeoeffnetVon.setLayoutX(20);
    	lblGeoeffnetVon.setLayoutY(130);
    	lblGeoeffnetBis.setLayoutX(20);
    	lblGeoeffnetBis.setLayoutY(170);
    	lblBeckenlaenge.setLayoutX(20);
    	lblBeckenlaenge.setLayoutY(210);
    	lblWassTemperatur.setLayoutX(20);
    	lblWassTemperatur.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblGeoeffnetVon, lblGeoeffnetBis,
       		lblBeckenlaenge, lblWassTemperatur);
    
    	// Textfelder
     	txtName.setLayoutX(130);
    	txtName.setLayoutY(90);
    	txtGeoeffnetVon.setLayoutX(130);
    	txtGeoeffnetVon.setLayoutY(130);
    	txtGeoeffnetBis.setLayoutX(130);
    	txtGeoeffnetBis.setLayoutY(170);
      	txtBeckenlaenge.setLayoutX(130);
    	txtBeckenlaenge.setLayoutY(210);
    	txtWassTemperatur.setLayoutX(130);
    	txtWassTemperatur.setLayoutY(250);
     	pane.getChildren().addAll( 
     		txtName, txtGeoeffnetVon, txtGeoeffnetBis,
     		txtBeckenlaenge, txtWassTemperatur);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(310);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(220);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(310);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menu
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvExport);
  	    this.mnDatei.getItems().add(mnItmTxtExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeFreizeitbadAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeFreizeitbaederAn();
	        } 
   	    });  
    }
    
    private void nehmeFreizeitbadAuf(){
    	try{
    		this.freizeitbad = new Freizeitbad(
    			txtName.getText(), 
   	            txtGeoeffnetVon.getText(),
   	            txtGeoeffnetBis.getText(),
    		    txtBeckenlaenge.getText(),
    		    txtWassTemperatur.getText());
    		zeigeInformationsfensterAn("Das Freizeitbad wurde aufgenommen!");
       	}
       	catch(PlausiException exc){
       		zeigeFehlermeldungsfensterAn(exc.getPlausiTyp() + "er ", exc.getMessage());
     	}
    }
   
    private void zeigeFreizeitbaederAn(){
    	if(this.freizeitbad != null){
    		txtAnzeige.setText(
    			this.freizeitbad.gibFreizeitbadZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde kein Freizeitbad aufgenommen!");
    	}
    }	

    void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String fehlertyp, String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	fehlertyp + "Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
