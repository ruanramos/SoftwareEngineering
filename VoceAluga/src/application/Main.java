import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    static final int APP_WIDTH = 600;
    static final int APP_HEIGHT = 400;

    Stage stage;
    Scene home = homeScene();
    Scene register = registrationScene();


    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Sistema de Locação");
        stage.setScene(home);
        stage.show();
    }

    private Scene homeScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Text sceneTitle = new Text("Tela inicial");
        grid.add(sceneTitle, 0, 0);

        Button registerBtn = new Button("Cadastrar");
        registerBtn.setOnAction(e -> stage.setScene(register));
        grid.add(registerBtn, 0, 1);

        return new Scene(grid, APP_WIDTH, APP_HEIGHT);
    }

    private Scene registrationScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Cadastro de novo cliente:");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label customerName = new Label("Nome do cliente: ");
        grid.add(customerName, 0, 1);

        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 1);

        Label customerCpf = new Label("CPF: ");
        grid.add(customerCpf, 0, 2);

        TextField cpfTextField = new TextField();
        grid.add(cpfTextField, 1, 2);

        Button customerBtn = new Button("Cadastrar");
        customerBtn.setOnAction(e ->
                System.out.println(nameTextField.getText() + " " +
                        cpfTextField.getText()));
        grid.add(customerBtn, 0, 3);

        Button homeBtn = new Button("Início");
        homeBtn.setOnAction(e ->
                stage.setScene(home));
        grid.add(homeBtn, 1, 3);

        return new Scene(grid, APP_WIDTH, APP_HEIGHT);
    }
}

