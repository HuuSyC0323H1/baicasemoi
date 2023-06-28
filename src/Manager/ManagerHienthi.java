package Manager;

import Login_logOut.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;


public class ManagerHienthi {
    ManagerUser managerUser = new ManagerUser();

    public void quyenCeo() {
        // Tạo một Stage mới để hiển thị giao diện quản lý người dùng
        Stage userManagementStage = new Stage();
        userManagementStage.setTitle("Quản lý người dùng");
        userManagementStage.setWidth(1000);
        userManagementStage.setHeight(600);

        // Tạo layout cho giao diện quản lý người dùng
        BorderPane userManagementLayout = new BorderPane();

        // Tạo một TableView để hiển thị danh sách người dùng
        TableView<User> userTableView = new TableView<>();

        // Tạo các cột cho TableView
        TableColumn<User, String> usernameColumn = new TableColumn<>("Tên Nhân viên");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<User, Boolean> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, Boolean> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<User, Boolean> basicColumn = new TableColumn<>("BasicSalary");
        basicColumn.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        TableColumn<User, Boolean> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        // Thêm các cột vào TableView
        userTableView.getColumns().addAll(usernameColumn, emailColumn,phoneColumn,basicColumn,roleColumn);

        // Thêm dữ liệu vào TableView
        ObservableList<User> userList = FXCollections.observableArrayList();
        userList.addAll(managerUser.getUserList());
        userTableView.setItems(userList);

        // Thêm TableView vào layout
        userManagementLayout.setCenter(userTableView);
        // Tạo một nút "Thêm người dùng" để thêm người dùng mới
        Button addUserButton = new Button("Thêm");
        addUserButton.setOnAction(event -> showAddEmp());
        Button addXoaButton = new Button("Xóa");
        addXoaButton.setOnAction(event -> managerUser.removeEmp());
        Button addSuaButton = new Button("Sửa");
        addXoaButton.setOnAction(event -> managerUser.UpdateEmp());
        Button addFindButton = new Button("Tìm kiếm");
        addXoaButton.setOnAction(event -> managerUser.findEmp("CEO"));

        // Thêm nút "Thêm người dùng" vào layout
        HBox buttonBox = new HBox(addUserButton, addXoaButton,addSuaButton,addFindButton);
        userManagementLayout.setBottom(buttonBox);
        // Tạo một Sceneđể đưa layout vào
        Scene userManagementScene = new Scene(userManagementLayout);
        // Đặt Scene vào Stage và hiển thị Stage
        userManagementStage.setScene(userManagementScene);
        userManagementStage.show();
    }
    private void showAddEmp(){
        Stage userEmpStage = new Stage();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        final Text actiontarget = new Text();
        actiontarget.setFill(Color.FIREBRICK);
        grid.add(actiontarget, 1, 6);
        userEmpStage.setTitle("Quản lý người dùng");
        userEmpStage.setWidth(400);
        userEmpStage.setHeight(240);
        userEmpStage.setTitle("Thêm người dùng mới");
        userEmpStage.initModality(Modality.WINDOW_MODAL);
        Window userManagementScene = new Stage();
        userEmpStage.initOwner(userManagementScene);

// Tạo layout cho cửa sổ nhập thông tin người dùng mới
        VBox addUserLayout = new VBox();

// Tạo các trường để nhập thông tin người dùng mới
        TextField nameTextField = new TextField();
        nameTextField.setStyle("-fx-text-fill: black; -fx-font-size: 14px");
        nameTextField.setPromptText("Tên nhân viên");

        TextField emailTextField = new TextField();
        emailTextField.setStyle("-fx-text-fill: black; -fx-font-size: 14px");
        emailTextField.setPromptText("Email");

        TextField phoneTextField = new TextField();
        phoneTextField.setStyle("-fx-text-fill: black; -fx-font-size: 14px");
        phoneTextField.setPromptText("Số điện thoại");

        TextField basicSalaryTextField = new TextField();
        basicSalaryTextField.setStyle("-fx-text-fill: black; -fx-font-size: 14px");
        basicSalaryTextField.setPromptText("Lương cơ bản");

        TextField roleTextField = new TextField();
        roleTextField.setStyle("-fx-text-fill: black; -fx-font-size: 14px");
        roleTextField.setPromptText("Vị trí");

// Tạo một nút "Lưu" để lưu thông tin người dùng mới
        Button saveButton = new Button("Lưu lại");
        saveButton.setStyle("-fx-background-color: #38AFD4; -fx-text-fill: white;");
        Button cancelButton = new Button("Hủy ");
        cancelButton.setStyle("-fx-background-color: #38AFD4; -fx-text-fill: white;");
        HBox hbox = new HBox(10,saveButton,cancelButton);
        hbox.setAlignment(Pos.CENTER);

        saveButton.setOnAction(event -> {
            // Lấy thông tin từ các trường
            String name = nameTextField.getText();
            String email = emailTextField.getText();
            String phone = phoneTextField.getText();
            double basicSalary;
            try {
                basicSalary = Double.parseDouble(basicSalaryTextField.getText());
            }catch (NumberFormatException e){
                actiontarget.setFill(Color.GREEN);
                actiontarget.setStyle("-fx-font-size: 12px");
                actiontarget.setText("Vui lòng nhập lại");
                return;
            }
            String role = roleTextField.getText();

            // Thêm người dùng mới vào danh sách
            User newUser = new User(name, email, phone, role, basicSalary);
            managerUser.addUser(newUser);

//            userTableView.setItems(userList);

            actiontarget.setFill(Color.GREEN);
            actiontarget.setText("Chào mừng quý khách đã đến miniMart của chúng tôi");
            // Đóng cửa sổ nhập thông tin người dùng mới
            userEmpStage.close();
        });
        cancelButton.setOnAction(actionEvent -> userEmpStage.close());

        // Thêm các trường và nút "Lưu" vào layout
        addUserLayout.getChildren().addAll(nameTextField, emailTextField, phoneTextField,roleTextField, basicSalaryTextField, hbox, actiontarget);
        Scene addUserScene = new Scene(addUserLayout);

        // Đặt Scene vào Stage và hiển thị Stage
        userEmpStage.setScene(addUserScene);
        userEmpStage.show();
    }
}
