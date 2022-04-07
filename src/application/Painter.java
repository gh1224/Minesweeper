package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Painter {
    private static final int LEN = Main.LEN;
    private static final int MENU_THICK = Main.MENU_THICK;
    private static final int VERT_THICK = Main.VERT_THICK;
    private static final int HOR_THICK = Main.HOR_THICK;
    private static final int Y_START = Main.Y_START;
    private static final int NUM_X = Main.NUM_X;
    private static final int NUM_Y = Main.NUM_Y;
    private static final int CRACK_X = Main.CRACK_X;
    private static final int CRACK_Y = Main.CRACK_Y;
    private static final int PADDING = Main.PADDING;
    private static final int SEG_X = Main.SEG_X;
    private static final int SEG_Y = Main.SEG_Y;
    private static final int FACE_LEN = Main.FACE_LEN;
    private static final int INFO_X = Main.INFO_X;
    private static final int INFO_Y = Main.INFO_Y;

    public final Image VERT_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\border_vert.png"));
    public final Image HOR_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\border_hor.png"));
    public final Image LEFTBOTTOM_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\corner_bottom_left.png"));
    public final Image RIGHTBOTTOM_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\corner_bottom_right.png"));
    public final Image LEFTTOP_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\corner_up_left.png"));
    public final Image RIGHTTOP_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\corner_up_right.png"));
    public final Image LEFTHOR_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\t_left.png"));
    public final Image RIGHTHOR_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\t_right.png"));
    public final Image SEGBG_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\nums_background_black.png"));
    public final Image[] SEG_IMG = {
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d0.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d1.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d2.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d3.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d4.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d5.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d6.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d7.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d8.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d9.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\d-.png"))
    };
    public final Image[] FACE_IMG = {
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_unpressed.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_pressed.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_lose.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_win.png")),
    };

    private static AnchorPane pane = Main.pane;

    private AnchorPane infoPane = new AnchorPane();
    private ImageView[] seg1 = new ImageView[4];
    private ImageView[] seg2 = new ImageView[4];
    private ImageView face = new ImageView(FACE_IMG[0]);

    public void set() {
        for (int i = 0; i < 3; i++)
            seg1[i] = new ImageView();

        drawWall();
        drawInfo();
        setEventHandler();
    }

    private void drawWall() {
        ImageView leftWall1 = new ImageView(VERT_IMG);
        leftWall1.setFitWidth(VERT_THICK);
        leftWall1.setFitHeight(INFO_Y);
        leftWall1.setX(0);
        leftWall1.setY(MENU_THICK + HOR_THICK);
        pane.getChildren().add(leftWall1);

        ImageView rightWall1 = new ImageView(VERT_IMG);
        rightWall1.setFitWidth(VERT_THICK);
        rightWall1.setFitHeight(INFO_Y);
        rightWall1.setX(VERT_THICK + INFO_X);
        rightWall1.setY(MENU_THICK + HOR_THICK);
        pane.getChildren().add(rightWall1);

        ImageView leftWall2 = new ImageView(VERT_IMG);
        leftWall2.setFitWidth(VERT_THICK);
        leftWall2.setFitHeight(LEN * Main.Y_NUM);
        leftWall2.setX(0);
        leftWall2.setY(MENU_THICK + Y_START);
        pane.getChildren().add(leftWall2);

        ImageView rightWall2 = new ImageView(VERT_IMG);
        rightWall2.setFitWidth(VERT_THICK);
        rightWall2.setFitHeight(LEN * Main.Y_NUM);
        rightWall2.setX(VERT_THICK + INFO_X);
        rightWall2.setY(MENU_THICK + Y_START);
        pane.getChildren().add(rightWall2);

        ImageView topWall = new ImageView(HOR_IMG);
        topWall.setFitWidth(INFO_X);
        topWall.setFitHeight(HOR_THICK);
        topWall.setX(VERT_THICK);
        topWall.setY(MENU_THICK);
        pane.getChildren().add(topWall);

        ImageView bottomWall = new ImageView(HOR_IMG);
        bottomWall.setFitWidth(INFO_X);
        bottomWall.setFitHeight(HOR_THICK);
        bottomWall.setX(VERT_THICK);
        bottomWall.setY(MENU_THICK + Y_START + LEN * Main.Y_NUM);
        pane.getChildren().add(bottomWall);

        ImageView midWall = new ImageView(HOR_IMG);
        midWall.setFitWidth(INFO_X);
        midWall.setFitHeight(HOR_THICK);
        midWall.setX(VERT_THICK);
        midWall.setY(MENU_THICK + Y_START - HOR_THICK);
        pane.getChildren().add(midWall);

        ImageView leftBottomWall = new ImageView(LEFTBOTTOM_IMG);
        leftBottomWall.setFitWidth(VERT_THICK);
        leftBottomWall.setFitHeight(HOR_THICK);
        leftBottomWall.setX(0);
        leftBottomWall.setY(MENU_THICK + Y_START + LEN * Main.Y_NUM);
        pane.getChildren().add(leftBottomWall);

        ImageView rightBottomWall = new ImageView(RIGHTBOTTOM_IMG);
        rightBottomWall.setFitWidth(VERT_THICK);
        rightBottomWall.setFitHeight(HOR_THICK);
        rightBottomWall.setX(VERT_THICK + INFO_X);
        rightBottomWall.setY(MENU_THICK + Y_START + LEN * Main.Y_NUM);
        pane.getChildren().add(rightBottomWall);

        ImageView leftTopWall = new ImageView(LEFTTOP_IMG);
        leftTopWall.setFitWidth(VERT_THICK);
        leftTopWall.setFitHeight(HOR_THICK);
        leftTopWall.setX(0);
        leftTopWall.setY(MENU_THICK);
        pane.getChildren().add(leftTopWall);

        ImageView rightTopWall = new ImageView(RIGHTTOP_IMG);
        rightTopWall.setFitWidth(VERT_THICK);
        rightTopWall.setFitHeight(HOR_THICK);
        rightTopWall.setX(VERT_THICK + INFO_X);
        rightTopWall.setY(MENU_THICK);
        pane.getChildren().add(rightTopWall);

        ImageView leftMidWall = new ImageView(LEFTHOR_IMG);
        leftMidWall.setFitWidth(VERT_THICK);
        leftMidWall.setFitHeight(HOR_THICK);
        leftMidWall.setX(0);
        leftMidWall.setY(MENU_THICK + Y_START - HOR_THICK);
        pane.getChildren().add(leftMidWall);

        ImageView rightMidWall = new ImageView(RIGHTHOR_IMG);
        rightMidWall.setFitWidth(VERT_THICK);
        rightMidWall.setFitHeight(HOR_THICK);
        rightMidWall.setX(VERT_THICK + INFO_X);
        rightMidWall.setY(MENU_THICK + Y_START - HOR_THICK);
        pane.getChildren().add(rightMidWall);
    }

    private void drawInfo() {
        infoPane.setStyle("-fx-background-color: #b3b3b3");
        infoPane.setPrefWidth(INFO_X);
        infoPane.setPrefHeight(INFO_Y);
        infoPane.setLayoutX(VERT_THICK);
        infoPane.setLayoutY(MENU_THICK + HOR_THICK);
        pane.getChildren().add(infoPane);

        seg1[3] = new ImageView(SEGBG_IMG);
        seg1[3].setFitWidth(SEG_X);
        seg1[3].setFitHeight(SEG_Y);
        seg1[3].setX(PADDING);
        seg1[3].setY(PADDING);
        infoPane.getChildren().add(seg1[3]);

        seg2[3] = new ImageView(SEGBG_IMG);
        seg2[3].setFitWidth(SEG_X);
        seg2[3].setFitHeight(SEG_Y);
        seg2[3].setX(INFO_X - PADDING - SEG_X);
        seg2[3].setY(PADDING);
        infoPane.getChildren().add(seg2[3]);

        face.setFitWidth(FACE_LEN);
        face.setFitHeight(FACE_LEN);
        face.setX((INFO_X - FACE_LEN) / 2);
        face.setY(PADDING);
        infoPane.getChildren().add(face);

        for (int i = 0; i < 3; i++) {
            seg1[i] = new ImageView();
            seg1[i].setFitWidth(NUM_X);
            seg1[i].setFitHeight(NUM_Y);
            seg1[i].setX(PADDING + (2 - i) * (NUM_X + CRACK_X) + CRACK_X * 2);
            seg1[i].setY(PADDING + CRACK_Y * 2);
            infoPane.getChildren().add(seg1[i]);

            seg2[i] = new ImageView();
            seg2[i].setFitWidth(NUM_X);
            seg2[i].setFitHeight(NUM_Y);
            seg2[i].setX(INFO_X - PADDING - (i + 2) * CRACK_X - (i + 1) * NUM_X);
            seg2[i].setY(PADDING + CRACK_Y * 2);
            infoPane.getChildren().add(seg2[i]);
        }
    }

    public void setSeg1(int num) {
        if (num >= 0) {
            for (int i = 0; i < 3; i++)
                seg1[i].setImage(SEG_IMG[(int) (num / Math.pow(10, i)) % 10]);
        } else {
            num = Math.abs(num);
            for (int i = 0; i < 3; i++)
                seg1[i].setImage(SEG_IMG[(int) (num / Math.pow(10, i)) % 10]);

            seg1[2].setImage(SEG_IMG[10]);
        }
    }

    public void resetSeg2() {
        for (int i = 0; i < 3; i++)
            seg2[i].setImage(SEG_IMG[0]);
    }

    public void setSeg2(int num) {
        if (num >= 999) {
            for (int i = 0; i < 3; i++)
                seg2[i].setImage(SEG_IMG[9]);
        } else {
            for (int i = 0; i < 3; i++)
                seg2[i].setImage(SEG_IMG[(int) (num / Math.pow(10, i)) % 10]);
        }
    }

    public void winFace() {
        face.setImage(FACE_IMG[3]);
    }

    public void loseFace() {
        face.setImage(FACE_IMG[2]);
    }

    private void setEventHandler() {
        FaceHandlers handle = new FaceHandlers();
        face.setOnMousePressed(handle.pressed);
        face.setOnMouseExited(handle.unpressed);
        face.setOnMouseClicked(handle.clicked);
        face.setOnDragDetected(handle.detected);
        face.setOnMouseDragReleased(handle.reset);
        face.setOnMouseDragEntered(handle.pressed);
        face.setOnMouseDragExited(handle.unpressed);
    }
}
