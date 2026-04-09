package org.example.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.model.Facture;
import org.example.model.Methode;

import static java.lang.Double.parseDouble;

public class AppGraphicalController {
    public double total = 0.0;
    public Facture factureActuel = new Facture(0, 0, null);

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
    private TextArea message;

    @FXML
    void comptantRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.COMPTANT);
        resetForm(comptant);
//        comptant.setDisable(true); //grise la radio
    }

    @FXML
    void creditRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.CREDIT);
        resetForm(credit);
    }

    @FXML
    void debitRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.DEBIT);
        resetForm(debit);
    }

    @FXML
    void registerF(ActionEvent event) {
        System.out.println("enter");
        if (getData()) {
            enregistrerFacture();
            resetForm(null);
        }
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

    public void resetForm(RadioButton selected) {

        comptant.setSelected(false);
        credit.setSelected(false);
        debit.setSelected(false);

        if (selected != null) {
            selected.setSelected(true);

        } else {
            factureActuel.setMontant(0);
            factureActuel.setTaxes(0);
            this.message.setText(" ");
        }
    }

    private void enregistrerFacture() {
        total += factureActuel.getDons();
    }

    private boolean getData() {
        boolean aFonctionner = true;
        try {
            factureActuel.setMontant(parseDouble(montantF.getText()));
            factureActuel.setTaxes(parseDouble(taxeF.getText()));
        } catch (NumberFormatException f) {
            this.message.setText("veuillez entrer un nombre valide ex 1.69");
            aFonctionner = false;
        } catch (NullPointerException n) {
            this.message.setText("");
            aFonctionner = false;
        }

        return aFonctionner;
    }
}
