package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static int X_NUM = 30;
    public static int Y_NUM = 16;
    public static int MINE_NUM = 99;
    public static final int LEN = 34;
    public static final int MENU_THICK = 32;
    public static final int VERT_THICK = 24;
    public static final int HOR_THICK = 22;
    public static final int PADDING = 7;
    public static final int NUM_X = 25;
    public static final int NUM_Y = 50;
    public static final int CRACK_X = 3;
    public static final int CRACK_Y = 3;
    public static final int SEG_X = NUM_X * 3 + CRACK_X * 6;
    public static final int SEG_Y = NUM_Y + CRACK_Y * 4;
    public static final int INFO_X = LEN * X_NUM;
    public static final int INFO_Y = SEG_Y + PADDING * 2;
    public static final int FACE_LEN = SEG_Y;
    public static final int X_START = VERT_THICK;
    public static final int Y_START = INFO_Y + HOR_THICK * 2;

    public static AnchorPane pane = new AnchorPane();
    public static Scene scene = new Scene(pane, VERT_THICK * 2 + INFO_X, MENU_THICK + Y_START + HOR_THICK + LEN * Y_NUM);
    public static Controller control = new Controller();
    public static Form[][] data = new Form[X_NUM][Y_NUM];

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });





        MenuBar menuBar = new MenuBar();
        menuBar.setPrefSize(VERT_THICK * 2 + INFO_X, MENU_THICK);
        menuBar.setStyle("-fx-background-color: white;");
        pane.getChildren().add(menuBar);

        Menu menu1 = new Menu("게임");
        Menu menu2 = new Menu("도움말");
        menuBar.getMenus().addAll(menu1, menu2);

        MenuItem menuItem1 = new MenuItem("사용자 조정");
        MenuItem menuItem2 = new MenuItem("Item 2");
        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);
        menuItem1.setOnAction(e -> {
            AnchorPane controlPane = new AnchorPane();
            Scene controlScene = new Scene(controlPane, 400, 250);
            Stage controlStage = new Stage();
            controlStage.setScene(controlScene);
            controlStage.show();

            Label l1 = new Label("가로 :");
            Label l2 = new Label("세로 :");
            Label l3 = new Label("지뢰 :");
            l1.setLayoutX(50);
            l1.setLayoutY(50);
            l2.setLayoutX(200);
            l2.setLayoutY(50);
            l3.setLayoutX(200 - 125 / 2);
            l3.setLayoutY(100);

            TextField tf1 = new TextField(Integer.toString(X_NUM));
            TextField tf2 = new TextField(Integer.toString(Y_NUM));
            TextField tf3 = new TextField(Integer.toString(MINE_NUM));
            tf1.setPrefSize(75, 24);
            tf2.setPrefSize(75, 24);
            tf3.setPrefSize(75, 24);
            tf1.setLayoutX(100);
            tf1.setLayoutY(50);
            tf2.setLayoutX(250);
            tf2.setLayoutY(50);
            tf3.setLayoutX(250 - 125 / 2);
            tf3.setLayoutY(100);

            Button btn = new Button("확인");
            btn.setPrefSize(50, 30);
            btn.setLayoutX(200 - 50 / 2);
            btn.setLayoutY(170);
            btn.setOnAction(e2 -> {
                X_NUM = Integer.parseInt(tf1.getText());
                Y_NUM = Integer.parseInt(tf2.getText());
                MINE_NUM = Integer.parseInt(tf3.getText());
//                control.play();
                controlStage.close();
            });

            controlPane.getChildren().addAll(l1, l2, l3, tf1, tf2, tf3, btn);
        });

        control.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}