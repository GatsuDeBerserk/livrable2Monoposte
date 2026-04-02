package org.example.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.model.TicketMachine;
import org.example.model.TicketMachineLot;

/**
 * Graphical controls of a transit ticket vending machine offering to change transit route.
 */
public class TicketMachineGraphicalController {
    private TicketMachineLot ticketMachineLot;

    private final TicketMachine ticketMachine;
    @FXML
    private Text selectedRoute;
    @FXML
    private Button changeToRoute1;
    @FXML
    private Button changeToRoute2;

    public TicketMachineGraphicalController(TicketMachineLot ticketMachineLot, TicketMachine ticketMachine) {
        this.ticketMachineLot = ticketMachineLot;
        this.ticketMachine = ticketMachine;
    }

    private void handleRouteChangeToOne(ActionEvent e) {
        ticketMachine.setSelectedRoute(TicketMachine.ROUTES.ROUTE_1);
    }

    private void handleRouteChangeToTwo(ActionEvent e) {
        ticketMachine.setSelectedRoute(TicketMachine.ROUTES.ROUTE_2);
    }

    private void changedRoute(ObservableValue<? extends String> obs, String oldRoute, String newRoute) {
        if ("".equals(newRoute)) {
            selectedRoute.setText("NONE");
        } else {
            selectedRoute.setText(newRoute);
        }
    }

    public void openMachine2OnDemand(ChangeListener<Boolean> handlerOfMachine2Opening) {
        ticketMachineLot.handleOpeningOfMachineTwo(handlerOfMachine2Opening);
    }

    @FXML
    void openMachine2(ActionEvent event) {
        ticketMachineLot.setIsMachine2Demanded(true);
    }

    /**
     * Permits to change route on demand and to see selected route. [Permet de changer la route à la demande et de voir le changement de route sélectionnée.]
     */
    @FXML
    private void initialize() {
        changeToRoute1.setOnAction(this::handleRouteChangeToOne);
        changeToRoute2.setOnAction(this::handleRouteChangeToTwo);

        ticketMachine.selectedRouteProperty().addListener(this::changedRoute);

        ticketMachine.setSelectedRoute(TicketMachine.ROUTES.ROUTE_1);
    }
}
