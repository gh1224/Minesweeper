package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Form
{
    private static final int LEN = Main.LEN;
    private static final int MENU_THICK = Main.MENU_THICK;
    private static final int X_START = Main.X_START;
    private static final int Y_START = Main.Y_START;

    public final Image[] IMG_TYPE = {
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open0.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open1.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open2.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open3.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open4.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open5.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open6.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open7.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\open8.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\mine.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\mine_red.png")),
            new Image(getClass().getClassLoader().getResourceAsStream("res\\mine_wrong.png"))
    };

    private static AnchorPane pane = Main.pane;

    private ImageView block = new ImageView();
    private int val;

    public void init()
    {
        val = 0;
    }

    public void setType(int x, int y)
    {
        if (val > 9)
            val = 9;

        block.setImage(IMG_TYPE[val]);
        block.setX(X_START + x * LEN);
        block.setY(MENU_THICK + Y_START + y * LEN);
        block.setFitWidth(LEN);
        block.setPreserveRatio(true);
    }

    public void setMine()
    {
        val = 9;
    }

    public boolean isMine()
    {
        return val == 9;
    }

    public boolean isZero()
    {
        return val == 0;
    }

    public void setRedMine()
    {
        block.setImage(IMG_TYPE[10]);
    }

    public void setXMine()
    {
        block.setImage(IMG_TYPE[11]);
    }

    public void plus()
    {
        val++;
    }

    public void addToPane()
    {
        pane.getChildren().add(block);
    }
}
