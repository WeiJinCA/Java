package AdvancedJavaProgramming.Networking;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Application {

    // IO streams
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane paneForTextField = new BorderPane();
        paneForTextField.setPadding(new Insets(5,5,5,5));
        paneForTextField.setStyle("-fx-border-color: green");
        paneForTextField.setLeft(new Label("Enter a raidus: "));

        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.setCenter(tf);

        BorderPane mainPane = new BorderPane();
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField);

        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();

        tf.setOnAction(e->{
            try{
                double radius = Double.parseDouble(tf.getText().trim());

                toServer.writeDouble(radius);
                toServer.flush();

                double area = fromServer.readDouble();

                ta.appendText("Radius is " + radius + "\n");
                ta.appendText("Area received from the server is " + area + '\n');
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        try{
            Socket socket = new Socket("localhost", 8000);
            // Create an input stream to receive data from the server
             fromServer = new DataInputStream(socket.getInputStream());

             // Create an output stream to send data to the server
             toServer = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
