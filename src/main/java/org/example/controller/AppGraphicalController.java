package org.example.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    public void autoUpdateMontant(InputMethodEvent event) {
        updateDonsActuel();
    }

    @FXML
    void comptantRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.COMPTANT);
        resetForm(comptant);
        updateDonsActuel();
    }

    @FXML
    void creditRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.CREDIT);
        resetForm(credit);
        updateDonsActuel();
    }

    @FXML
    void debitRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.DEBIT);
        resetForm(debit);
        updateDonsActuel();
    }

    @FXML
    void registerF(ActionEvent event) {
        enregistrerFacture();
        resetForm(null);
    }

    //todo rechercher "texte si vide"/"placeholder"
    @FXML
    void selectMontant(MouseEvent event) {
        if (this.montantF.getSelectedText().equals(this.montantF.getText())) {
            this.montantF.deselect();
        } else if (this.montantF.getSelectedText().isEmpty()) {
            this.montantF.selectAll();
        }
    }

    @FXML
    void selectNom(MouseEvent event) {
        if (this.nomF.getSelectedText().equals(this.nomF.getText())) {
            this.nomF.deselect();
        } else if (this.nomF.getSelectedText().isEmpty()) {
            this.nomF.selectAll();
        }
    }

    @FXML
    void selectTaxe(MouseEvent event) {
        if (this.taxeF.getSelectedText().equals(this.taxeF.getText())) {
            this.taxeF.deselect();
        } else if (this.taxeF.getSelectedText().isEmpty()) {
            this.taxeF.selectAll();
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
            factureActuel.setMethode(null);
            factureActuel.setNom("Nom Prenom");
            factureActuel.calculerDons();

            nomF.setText("Nom Prenom");
            montantF.setText("Total avant Taxes");
            taxeF.setText("Taxes");
            donUnique.setText("inconnu");
            this.message.setText(" ");
        }
    }

    private void enregistrerFacture() {
        if (factureActuel.getDons() != 0) {
            total += factureActuel.getDons();

            BigDecimal valeur = BigDecimal.valueOf(total);
            valeur = valeur.setScale(2, RoundingMode.HALF_UP);
            donTotal.setText(valeur.toString() + "$");

            register.setDisable(true);
        }
    }

    @FXML
    private void updateDonsActuel() {
        String message = factureActuel.compilerDonnes(nomF.getText(), montantF.getText(), taxeF.getText());
        this.message.setText(message);
        if (message.length() > 1) {
            donUnique.setText("inconnu");
            register.setDisable(true);
        } else {
            BigDecimal valeur = BigDecimal.valueOf(factureActuel.getDons());
            valeur = valeur.setScale(2, RoundingMode.HALF_UP);

            donUnique.setText(valeur.toString() + "$");
            register.setDisable(false);
        }
    }
}
