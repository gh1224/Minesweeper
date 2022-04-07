package application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CoverHandlers {
    private static final int LEN = Main.LEN;
    private static final int MENU_THICK = Main.MENU_THICK;
    private static final int X_START = Main.X_START;
    private static final int Y_START = Main.Y_START;

    private final Image COVER_IMG;
    private final Image FLAG_IMG;
    private final Image PRESSED_IMG;

    private static Form[][] data = Main.data;
    private static Painter painter = Controller.painter;
    private static Controller control;

    public EventHandler<MouseEvent> open;
    public EventHandler<MouseEvent> pressed;
    public EventHandler<MouseEvent> dragPressed;
    public EventHandler<MouseEvent> exited;
    public EventHandler<MouseEvent> detected;

    public CoverHandlers(Controller c) {
        control = c;
        COVER_IMG = c.COVER_IMG;
        FLAG_IMG = c.FLAG_IMG;
        PRESSED_IMG = c.PRESSED_IMG;

        open = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView cover = (ImageView) event.getSource();
                    if (cover.getImage().equals(FLAG_IMG))
                        return;

                    int x = (int) ((cover.getX() - X_START) / LEN);
                    int y = (int) ((cover.getY() - (MENU_THICK + Y_START)) / LEN);

                    if (data[x][y].isMine()) {
                        control.finish(false);
                        data[x][y].setRedMine();
                    } else {
                        control.open(x, y);
                        if (control.isFinish())
                            control.finish(true);
                    }
                }
            }
        };

        exited = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView cover = (ImageView) event.getSource();
                    if (!cover.getImage().equals(FLAG_IMG))
                        cover.setImage(COVER_IMG);
                }
            }
        };

        pressed = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView cover = (ImageView) event.getSource();
                    if (!cover.getImage().equals(FLAG_IMG))
                        cover.setImage(PRESSED_IMG);
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    ImageView iv = (ImageView) event.getSource();
                    if (iv.getImage().equals(FLAG_IMG)) {
                        iv.setImage(COVER_IMG);
                        painter.setSeg1(control.plusFlag());
                    } else {
                        iv.setImage(FLAG_IMG);
                        painter.setSeg1(control.minusFlag());
                    }
                }
            }
        };

        dragPressed = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    ImageView cover = (ImageView) event.getSource();
                    if (!cover.getImage().equals(FLAG_IMG))
                        cover.setImage(PRESSED_IMG);
                }
            }
        };

        detected = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                ImageView cover = (ImageView) event.getSource();
                if (!cover.getImage().equals(FLAG_IMG))
                    cover.startFullDrag();
            }
        };
    }
}
