package FinalProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
    Stage window;
    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Zachs Bank");

        button = new Button("Close Program");

        button.setOnAction(e -> AlertBox.display("window", "cool cool"));


        button.setOnAction(e -> {
            boolean result = ConfirmBox.display("Title", "are you sure?");
            System.out.println(result);
        });

         HBox topMenu = new HBox();
         Button buttonA = new Button("file");
         Button buttonB = new Button("edit");
         Button buttonC = new Button("view");
         topMenu.getChildren().addAll(buttonA, buttonB, buttonC);

         VBox leftMenu = new VBox();
        Button buttonD = new Button("Create checking account");
        Button buttonE = new Button("Create savings account");
        Button buttonF = new Button("Remove account");
        Button buttonG = new Button("Display accounts");
        leftMenu.getChildren().addAll(buttonD, buttonE, buttonF, buttonG);

        buttonD.setOnAction(e -> {
           AlertBox.display("Checking Account", "Your checking account has been created");
        });
        buttonE.setOnAction(e -> {
            AlertBox.display("Savings Account", "Your Savings account has been created");
        });
        buttonF.setOnAction(e -> {
            AlertBox.display("Account removed", "Your account has been removed");
        });
        buttonG.setOnAction(e -> {
            AlertBox.display("Display Accounts", BankTeller.displayAccounts());
        });


        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);




        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.show();




    }
    private void closeProgram(){
        System.out.println("account saved");
        window.close();
    }


}
