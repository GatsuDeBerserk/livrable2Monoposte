package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactureTest {
    Facture f;

    @BeforeEach
    void setup() {
        f = new Facture("Terry Davis", 100, 15, null);
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
    void given() {
    }
}