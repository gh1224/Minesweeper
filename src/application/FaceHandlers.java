package application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class FaceHandlers {
    public final Image[] FACE_IMG = {
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_unpressed.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_pressed.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_lose.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\face_win.png")),
    };

    private static Controller control;

    public EventHandler<MouseEvent> reset;
    public EventHandler<MouseEvent> pressed;
    public EventHandler<MouseEvent> unpressed;
    public EventHandler<MouseEvent> detected;
    public EventHandler<MouseEvent> clicked;

    public FaceHandlers() {
        control = Main.control;

        clicked = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView face = (ImageView) event.getSource();
                    control.init();
                    face.setImage(FACE_IMG[0]);
                }
            }
        };

        pressed = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView face = (ImageView) event.getSource();
                    face.setImage(FACE_IMG[1]);
                }
            }
        };

        unpressed = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView face = (ImageView) event.getSource();
                    face.setImage(FACE_IMG[0]);
                }
            }
        };

        detected = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView face = (ImageView) event.getSource();
                face.startFullDrag();
            }
        };

        reset = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY);
                    control.init();
            }
        };
    }
}
