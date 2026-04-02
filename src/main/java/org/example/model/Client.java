package org.example.model;

public class Client {
    private int numeroCompte;
    private double balance;

    public Client(int numeroCompte) {
        this.numeroCompte = numeroCompte;
        balance = 0;
    }

    public String depot(double montant) {
        if (montant < 0) {
            return "Echec: nombre négatif interdit";
        } else {
            balance += montant;
            return "dépot réussi";
        }
    }

    public String retrait(double montant) {
        if (montant < 0) {
            return "Echec: nombre négatif interdit";
        } else if (montant > balance) {
            return "Echec: balance insuffisant";
        } else if ((montant % 20 != 0)) {
            return "Echec: le montant nest pas un multiple de 20";
        } else {
            balance -= montant;
            return "retrait réussi";
        }
    }

    public int getNumeroCompte() {
        return numeroCompte;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
