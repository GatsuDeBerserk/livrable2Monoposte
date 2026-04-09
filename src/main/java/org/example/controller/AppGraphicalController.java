package org.example.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.example.model.Facture;
import org.example.model.Methode;

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
    private TextField montantFacture;

    @FXML
    private TextField nomFacture;

    @FXML
    private Button enregistrer;

    @FXML
    private TextField taxeFacture;

    @FXML
    private TextArea message;

    @FXML
    void comptantRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.COMPTANT);
        renitialiserFormulaire(comptant);
        majDonsActuel();
    }

    @FXML
    void creditRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.CREDIT);
        renitialiserFormulaire(credit);
        majDonsActuel();
    }

    @FXML
    void debitRadio(ActionEvent event) {
        factureActuel.setMethode(Methode.DEBIT);
        renitialiserFormulaire(debit);
        majDonsActuel();
    }

    @FXML
    void enregistrerFacture(ActionEvent event) {
        enregistrerFacture();
        renitialiserFormulaire(null);
    }

    //todo rechercher "texte si vide"/"placeholder"
    @FXML
    void selectionnerMontant(MouseEvent event) {
        if (this.montantFacture.getSelectedText().equals(this.montantFacture.getText())) {
            this.montantFacture.deselect();
        } else if (this.montantFacture.getSelectedText().isEmpty()) {
            this.montantFacture.selectAll();
        }
    }

    @FXML
    void selectionnerNom(MouseEvent event) {
        if (this.nomFacture.getSelectedText().equals(this.nomFacture.getText())) {
            this.nomFacture.deselect();
        } else if (this.nomFacture.getSelectedText().isEmpty()) {
            this.nomFacture.selectAll();
        }
    }

    @FXML
    void selectionnerTaxe(MouseEvent event) {
        if (this.taxeFacture.getSelectedText().equals(this.taxeFacture.getText())) {
            this.taxeFacture.deselect();
        } else if (this.taxeFacture.getSelectedText().isEmpty()) {
            this.taxeFacture.selectAll();
        }
    }

    @FXML
    void initialize() {
        assert comptant != null : "fx:id=\"comptant\" was not injected: check your FXML file 'vue.fxml'.";
        assert credit != null : "fx:id=\"credit\" was not injected: check your FXML file 'vue.fxml'.";
        assert debit != null : "fx:id=\"debit\" was not injected: check your FXML file 'vue.fxml'.";
        assert donTotal != null : "fx:id=\"donTotal\" was not injected: check your FXML file 'vue.fxml'.";
        assert donUnique != null : "fx:id=\"donUnique\" was not injected: check your FXML file 'vue.fxml'.";
        assert montantFacture != null : "fx:id=\"montantF\" was not injected: check your FXML file 'vue.fxml'.";
        assert nomFacture != null : "fx:id=\"nomF\" was not injected: check your FXML file 'vue.fxml'.";
        assert enregistrer != null : "fx:id=\"register\" was not injected: check your FXML file 'vue.fxml'.";
        assert taxeFacture != null : "fx:id=\"taxeF\" was not injected: check your FXML file 'vue.fxml'.";

    }

    public void renitialiserFormulaire(RadioButton selected) {

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

            nomFacture.setText("Nom Prenom");
            montantFacture.setText("Total avant Taxes");
            taxeFacture.setText("Taxes");
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

            enregistrer.setDisable(true);
        }
    }

    @FXML
    private void majDonsActuel() {
        String message = factureActuel.compilerDonnes(nomFacture.getText(), montantFacture.getText(), taxeFacture.getText());
        this.message.setText(message);
        if (message.length() > 1) {
            donUnique.setText("inconnu");
            enregistrer.setDisable(true);
        } else {
            BigDecimal valeur = BigDecimal.valueOf(factureActuel.getDons());
            valeur = valeur.setScale(2, RoundingMode.HALF_UP);

            donUnique.setText(valeur.toString() + "$");
            enregistrer.setDisable(false);
        }
    }
}
