import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Controller.Controller;
import Model.Model;
import View.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/MainForm.fxml"));
        primaryStage.setTitle("Моделеривание 01");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        View.setStage(primaryStage);

        Model model = new Model();
        Controller controller = new Controller();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
