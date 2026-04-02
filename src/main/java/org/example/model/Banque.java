package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Banque {
//    private List<Guichet> guichets = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public Banque() {
    }

    public void ajouterClient(Client c) {
        clients.add(c);
    }

//    public void ajouterGuichet() {
//        guichets.add(new Guichet(this));
//    }

    public  Client getClientFromNumber(int numToCheck){
        for (Client c : clients) {
            if (c.getNumeroCompte() == numToCheck){
                return c;
            }
        }
        throw new RuntimeException("inexistant");
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
