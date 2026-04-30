package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactureTest {
    Facture f;

    @BeforeEach
    void setup() {
        f = new Facture("12345678", "Terry Davis", 100, 15, null);
    }

    @Test
    void givenFactureSansMethode_whenCalculerFacture_thenDonsSontDe0() {
        f.calculerDons();

        assertEquals(0, f.getDons());
    }

    @Test
    void givenFactureAvecMethodeComptant_whenCalculerFacture_thenDonsSontDeLaBonneValeure() {
        f.setMethode(Methode.COMPTANT);

        f.calculerDons();

        assertEquals(2.3, f.getDons());
    }

    @Test
    void givenFactureAvecMethodeCredit_whenCalculerFacture_thenDonsSontDeLaBonneValeure() {
        f.setMethode(Methode.CREDIT);

        f.calculerDons();

        assertEquals(2.233009708737864, f.getDons());
    }

    @Test
    void givenFactureAvecMethodeDebit_whenCalculerFacture_thenDonsSontDeLaBonneValeure() {
        f.setMethode(Methode.DEBIT);

        f.calculerDons();

        assertEquals(2.277227722772278, f.getDons());
    }

    @Test
    void givenDonnesDUneFactureNonRemplis_whenCompilerFacture_thenRetourneUnStringDeMessagesDErreurs() {
        String messages = f.compilerDonnes("12345678", "Nom Prenom", "000.00", "0.00");

        assertEquals(
                "veuillez entrer des valeur non-nulles dans taxes et montant\n\n" +
                        "veuillez entrer un nom\n\n" +
                        "veuillez selectionner un mode de paiement, aucun mode n'est actuelement selectionner\n\n"
                , messages);
    }

    @Test
    void givenFactureRemplisSaufeQueTaxesPlusGrandeQueLeMontant_whenCompilerFacture_thenRetourneUnStringDeMessagesDErreurs() {
        f.setMethode(Methode.COMPTANT);
        String messages = f.compilerDonnes("00000000", "Terry Davis", "15.00", "100.00");

        assertEquals(
                "erreur: les taxes sont plus grande que le montant de la facture\n\n" +
                        "veuillez entrer un numero de facture(lettres et/ou chiffres), le numero est actuellement 00000000\n\n"
                , messages);
    }

    @Test
    void givenFactureRemplisSaufeQueTaxesNegatives_whenCompilerFacture_thenRetourneUnStringAvecLErreureNegative() {
        f.setMethode(Methode.COMPTANT);
        String messages = f.compilerDonnes("12345678", "Terry Davis", "100", "-15");

        assertEquals(
                "veuillez entrer des nombres non-négatifs dans montant et taxes\n\n"
                , messages);
    }
}