package org.example.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Double.parseDouble;

public class Facture {
    private double dons;
    private String nom;
    private double montant;
    private double taxes;
    private Methode methode;
    private final double DIVISEUR_COMPTANT = 1.00;
    private final double DIVISEUR_CREDIT = 1.03;
    private final double DIVISEUR_DEBIT = 1.01;


    public Facture(String nom, double montant, double taxes, Methode methode) {
        this.nom = nom;
        this.montant = montant;
        this.taxes = taxes;
        this.methode = methode;
    }

    public double calculerDons() {
        double diviseur = 0;
        if (this.methode != null) {
            switch (this.methode) {
                case DEBIT: {
                    diviseur = DIVISEUR_DEBIT;
                    break;
                }
                case CREDIT: {
                    diviseur = DIVISEUR_CREDIT;
                    break;
                }
                case COMPTANT: {
                    diviseur = DIVISEUR_COMPTANT;
                    break;
                }
            }
            BigDecimal valeur = BigDecimal.valueOf((0.02 * (montant + taxes) / diviseur));
            valeur = valeur.setScale(15, RoundingMode.HALF_UP);
            this.dons = valeur.doubleValue();
        } else {
            this.dons = 0;
        }
        return this.dons;
    }

    public String compilerDonnes(String nomF, String montantF, String taxeF) {
        String messages = "";
        try {
            this.setNom(nomF);
            this.setMontant(parseDouble(montantF));
            this.setTaxes(parseDouble(taxeF));
        } catch (Exception e) {
            messages += "veuillez entrer des nombre valide ex 1.69\n\n";
        }
        if (this.getTaxes() == 0 || this.getMontant() == 0) {
            messages += "veuillez entrer des valeur non-nulles dans taxes et montant\n\n";
        }
        if (this.getMontant() < this.getTaxes()) {
            messages += "erreur: les taxes sont plus grande que le montant de la facture\n\n";
        }
        if (this.getNom().equals("Nom Prenom") || this.getNom().isBlank()) {
            messages += "veuillez entrer un nom\n\n";
        }
        if (this.getMethode() == null) {
            messages += "veuillez selectionner une méthode\n\n";
        }
        if (this.getMontant() < 0 || this.getTaxes() < 0) {
            messages += "veuillez entrer des nombres non-négatifs dans montant et taxes\n\n";
        }
        return messages;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
        calculerDons();
    }

    public void setMontant(double montant) {
        this.montant = montant;
        calculerDons();
    }

    public void setMethode(Methode methode) {
        this.methode = methode;
        calculerDons();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public double getDons() {
        return dons;
    }

    public double getMontant() {
        return montant;
    }

    public double getTaxes() {
        return taxes;
    }

    public Methode getMethode() {
        return methode;
    }

}
