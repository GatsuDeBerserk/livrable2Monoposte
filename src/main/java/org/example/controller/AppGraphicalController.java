package org.example.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.example.model.Factures;
import org.example.model.Methode;

public class AppGraphicalController {
    public Methode methode=null;
    public double total = 0.0;
    public List<Factures> historique;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton comptant;

    @FXML
    private RadioButton credit;

    @FXML
    private RadioButton debit;

    @FXML
    private Label donTotal;

    @FXML
    private Label donUnique;

    @FXML
    private TextField montantF;

    @FXML
    private TextField nomF;

    @FXML
    private Button register;

    @FXML
    private TextField taxeF;

    @FXML
    void comptantRadio(ActionEvent event) {
        methode = Methode.COMPTANT;
        System.out.println(methode);
        credit.setSelected(false);
        debit.setSelected(false);
//        comptant.setDisable(true); //grise la radio
    }

    @FXML
    void creditRadio(ActionEvent event) {
        methode = Methode.CREDIT;
        System.out.println(methode);
        comptant.setSelected(false);
        debit.setSelected(false);
    }

    @FXML
    void debitRadio(ActionEvent event) {
        methode = Methode.DEBIT;
        System.out.println(methode);
        comptant.setSelected(false);
        credit.setSelected(false);
    }

    @FXML
    void registerF(ActionEvent event) {
        System.out.println("enter");

        comptant.setSelected(false);
        credit.setSelected(false);
        debit.setSelected(false);
        methode = null;
    }

    @FXML
    void initialize() {
        assert comptant != null : "fx:id=\"comptant\" was not injected: check your FXML file 'vue.fxml'.";
        assert credit != null : "fx:id=\"credit\" was not injected: check your FXML file 'vue.fxml'.";
        assert debit != null : "fx:id=\"debit\" was not injected: check your FXML file 'vue.fxml'.";
        assert donTotal != null : "fx:id=\"donTotal\" was not injected: check your FXML file 'vue.fxml'.";
        assert donUnique != null : "fx:id=\"donUnique\" was not injected: check your FXML file 'vue.fxml'.";
        assert montantF != null : "fx:id=\"montantF\" was not injected: check your FXML file 'vue.fxml'.";
        assert nomF != null : "fx:id=\"nomF\" was not injected: check your FXML file 'vue.fxml'.";
        assert register != null : "fx:id=\"register\" was not injected: check your FXML file 'vue.fxml'.";
        assert taxeF != null : "fx:id=\"taxeF\" was not injected: check your FXML file 'vue.fxml'.";

    }

}
