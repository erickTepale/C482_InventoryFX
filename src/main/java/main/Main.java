package main;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import part.InHouse;
import part.Outsourced;
import part.Part;
import part.PartController;
import product.Product;
import utilities.DisplayTable;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadPrimaryStage(primaryStage);
        DisplayTable.loadPrelimData();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void loadPrimaryStage(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
