package org.example.controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.model.TicketMachine;
import org.example.model.TicketMachineLot;

/**
 * Graphical controls and command line controls offering a choice of ticket vending machines.
 */
public class TicketMachinesController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Ticket Vending Machines");

        TicketMachine ticketMachine1 = new TicketMachine();
        TicketMachine ticketMachine2 = new TicketMachine();
        TicketMachineLot ticketMachineLot = new TicketMachineLot();

        TabPane root = new TabPane();

        Tab tabOfMachine2 = new Tab();
        tabOfMachine2.setText("Machine 2");
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/view/TicketMachine2.fxml"));
        fxmlLoader2.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                TicketMachineGraphicalController ticketMachine2GraphicalController = new TicketMachineGraphicalController(ticketMachineLot, ticketMachine2);
                return ticketMachine2GraphicalController;
            }
        });
        tabOfMachine2.setContent(fxmlLoader2.load());

        Tab tabOfMachine1 = new Tab();
        tabOfMachine1.setText("Machine 1");
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/TicketMachine.fxml"));
        fxmlLoader1.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                TicketMachineGraphicalController ticketMachine1GraphicalController = new TicketMachineGraphicalController(ticketMachineLot, ticketMachine1);
                ChangeListener<Boolean> handlerOfMachine2Opening = new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        root.getTabs().add(tabOfMachine2); // @learning This code does not execute multiple times once machine is opened unlike before, even though java.util.List.add(E) prevents from adding multiple tabs
                    }
                };
                ticketMachine1GraphicalController.openMachine2OnDemand(handlerOfMachine2Opening);
                return ticketMachine1GraphicalController;
            }
        });
        tabOfMachine1.setContent(fxmlLoader1.load());
        root.getTabs().add(tabOfMachine1);

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
