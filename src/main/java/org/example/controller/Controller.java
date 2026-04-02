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

/**
 * Graphical controls and command line controls offering a choice of ticket vending machines.
 */
public class Controller extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("compileur de factures");


        TabPane root = new TabPane();


        Tab tabOfGuichet1 = new Tab();
        tabOfGuichet1.setText("Machine 1");
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/view/vue.fxml"));
//        fxmlLoader1.setControllerFactory(c -> {
//            return new AppGraphicalController();
//        });
        fxmlLoader1.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> param) {
                AppGraphicalController app1GraphicalController = new AppGraphicalController();
                ChangeListener<Boolean> selectAccount = new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        // @learning This code does not execute multiple times once machine is opened unlike before, even though java.util.List.add(E) prevents from adding multiple tabs
                    }
                };
                return app1GraphicalController;
            }
        });
        tabOfGuichet1.setContent(fxmlLoader1.load());
        root.getTabs().add(tabOfGuichet1);

        Scene scene = new Scene(root, 600, 429);
        scene.getStylesheets().add("/view/drip.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
