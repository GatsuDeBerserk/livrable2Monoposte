package org.example.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * A transit ticket vending machine characterised by a transit route.
 */
public final class TicketMachine {

    /**
     * Routes that tickets can be issued for.
     */
    public enum ROUTES {
        /**
         * Route 1, David Parnas Alley
         */
        ROUTE_1,
        /**
         * Route 2, Knuth Ville
         */
        ROUTE_2
    }

    /**
     * Currently selected route for which tickets are to be issued.
     */
    private final SimpleStringProperty selectedRoute = new SimpleStringProperty();

    public TicketMachine() {
        selectedRoute.set("");
    }

    public SimpleStringProperty selectedRouteProperty() {
        return selectedRoute;
    }

    public void setSelectedRoute(ROUTES route) {
        selectedRoute.set(route.toString());
    }
}
