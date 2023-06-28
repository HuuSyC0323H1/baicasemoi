package View;


import Manager.ManagerHienthi;
import Manager.ManagerProduct;
import Manager.ManagerUser;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    private ManagerUser managerUser;
    private ManagerProduct managerProduct;
    private ManagerHienthi managerHienthi;

    @Override
    public void start(Stage primaryStage) {
        // Khởi tạo các đối tượng quản lý
        managerUser = new ManagerUser();
        managerProduct = new ManagerProduct();
        managerHienthi = new ManagerHienthi();

        // Tạo lầu của ứng dụng
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        // Tạo phần đầu tiên của giao diện với biểu tượng và tiêu đề
        VBox header = new VBox();
        header.setPadding(new Insets(20));
        header.setAlignment(Pos.CENTER);

        Text title = new Text("miniMart");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.web("#38AFD4"));

        Label logo = new Label("M");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        logo.setTextFill(Color.web("#38AFD4"));

        header.getChildren().addAll(logo, title);

        root.setTop(header);

        // Tạo phần chính của giao diện để đăng nhập
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label userName = new Label("Tên đăng nhập:");
        userName.setTextFill(Color.web("#38AFD9"));
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Mật khẩu:");
        pw.setTextFill(Color.web("#38AFD4"));
        grid.add(pw, 0, 2);

        PasswordField passwordBox = new PasswordField();
        grid.add(passwordBox, 1, 2);

        Button btn = new Button("Đăng nhập");
        btn.setStyle("-fx-background-color: #38AFD4; -fx-text-fill: white;");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(event -> {
//            if (event.getSource() == btn && event.getCode() == KeyCode.LEFT) {
                String username = userTextField.getText();
                String password = passwordBox.getText();

                if (managerUser.login(username, password) && managerUser.hasPermission()) {
                    switch (username) {
                        case "admin1" -> managerHienthi.quyenCeo();
//                        case "admin2" -> managerHienthi.quyenKT();
//                        case "admin3" -> managerHienthi.quyenLT();
                        default -> {
                            actiontarget.setFill(Color.GREEN);
                            actiontarget.setText("Chào mừng quý khách đã đến miniMart của chúng tôi");
                            managerProduct.Show();
                        }
                    }
                } else {
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Thông tin đăng nhập không hợp lệ");
                }
                primaryStage.close();
                userTextField.setText("");
                passwordBox.setText("");
//            }
        });
        btn.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btn);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });

        btn.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btn);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        // Thêm nút "Đăng ký" để đăng ký tài khoản
        Button btnRegister = new Button("Đăng ký");
        btnRegister.setStyle("-fx-background-color: #38AFD4; -fx-text-fill: white;");
        HBox hbBtnRegister = new HBox(10);
        hbBtnRegister.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnRegister.getChildren().add(btnRegister);
        grid.add(hbBtnRegister, 1, 5);

        btnRegister.setOnAction(event -> managerUser.addUser());
        btnRegister.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnRegister);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });

        btnRegister.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnRegister);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });

        root.setCenter(grid);

        // Hiển thị cửa sổ
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("miniMart");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}