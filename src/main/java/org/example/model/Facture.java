package org.example.model;

public class Facture {
    private double dons;
    private double montant;
    private double taxes;
    private Methode methode;
    private final double DIVISEUR_COMPTANT = 1.00;
    private final double DIVISEUR_CREDIT = 1.03;
    private final double DIVISEUR_DEBIT = 1.01;


    public Facture(double montant, double taxes, Methode methode) {
        this.montant = montant;
        this.taxes = taxes;
        this.methode = methode;
    }

    public double calculerDons() {
        double diviseur = 0;
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
        this.dons = (0.02 * (montant + taxes) / diviseur);
        return this.dons;
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
