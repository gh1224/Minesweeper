package application;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Timer;
import java.util.TimerTask;


public class Controller
{
    private static final int LEN = Main.LEN;
    private static final int MENU_THICK = Main.MENU_THICK;
    private static final int X_START = Main.X_START;
    private static final int Y_START = Main.Y_START;

    public final Image COVER_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\cover.png"));
    public final Image FLAG_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\flag.png"));
    public final Image PRESSED_IMG = new Image(getClass().getClassLoader().getResourceAsStream("res\\open0.png"));

    private static AnchorPane pane = Main.pane;
    private static int[] minePos;
    public static Form[][] data;
    public static Painter painter = new Painter();
    private static Board board = new Board();

    public ImageView[][] cover;
    private boolean[][] hide;
    private int flagNum;
    private int cnt;
    public Timer timer;
    public TimerTask timerTask;
    public int sec;
    private boolean game;

    public Controller() {
        timer = new Timer();
    }

    public void play()
    {
        set();
        init();
    }

    public void set()
    {
        data = Main.data;
        painter.set();
        board.set();

        cover = new ImageView[Main.X_NUM][Main.Y_NUM];

        for (int i = 0; i < Main.X_NUM; i++)
            for (int j = 0; j < Main.Y_NUM; j++)
            {
                cover[i][j] = new ImageView();
                cover[i][j].setX(X_START + i * LEN);
                cover[i][j].setY(MENU_THICK + Y_START + j * LEN);
                cover[i][j].setFitWidth(LEN);
                cover[i][j].setPreserveRatio(true);
            }
    }

    public void init()
    {
        timer.cancel();
        timer = new Timer();
        timerTask = new TimerTask()
        {
            public void run()
            {
                Platform.runLater(new Runnable()
                {
                    public void run()
                    {
                        if (sec < 1000)
                            painter.setSeg2(++sec);
                    }
                });
            }
        };
        sec = 0;
        painter.resetSeg2();
        board.init();

        hide = new boolean[Main.X_NUM][Main.Y_NUM];

        for (int i = 0; i < Main.X_NUM; i++)
            for (int j = 0; j < Main.Y_NUM; j++)
            {
                cover[i][j].setImage(COVER_IMG);
                if (!pane.getChildren().contains(cover[i][j]))
                    pane.getChildren().add(cover[i][j]);

                hide[i][j] = true;
            }
        setEventHandler();
        game = false;
        cnt = 0;
        flagNum = Main.MINE_NUM;
        painter.setSeg1(flagNum);
    }

    public void open(int x, int y)
    {
        if (!game)
        {
            game = true;
            timer.schedule(timerTask, 0, 1000);
        }
        if (cover[x][y].getImage().equals(FLAG_IMG))
            painter.setSeg1(++flagNum);
        pane.getChildren().remove(cover[x][y]);
        hide[x][y] = false;
        cnt++;

        if (data[x][y].isZero())
        {
            if (x != 0 && y != 0 && hide[x - 1][y - 1])
                open(x - 1, y - 1);
            if (y != 0 && hide[x][y - 1])
                open(x, y - 1);
            if (x != Main.X_NUM - 1 && y != 0 && hide[x + 1][y - 1])
                open(x + 1, y - 1);
            if (x != 0 && hide[x - 1][y])
                open(x - 1, y);
            if (x != Main.X_NUM - 1 && hide[x + 1][y])
                open(x + 1, y);
            if (x != 0 && y != Main.Y_NUM - 1 && hide[x - 1][y + 1])
                open(x - 1, y + 1);
            if (y != Main.Y_NUM - 1 && hide[x][y + 1])
                open(x, y + 1);
            if (x != Main.X_NUM - 1 && y != Main.Y_NUM - 1 && hide[x + 1][y + 1])
                open(x + 1, y + 1);
        }
    }

    public boolean isFinish()
    {
        return cnt == Main.X_NUM * Main.Y_NUM - Main.MINE_NUM;
    }

    public void finish(boolean success)
    {
        timer.cancel();
        game = false;
        minePos = Board.minePos;

        if (success)
        {
            for (int num : minePos)
                cover[num % Main.X_NUM][num / Main.X_NUM].setImage(FLAG_IMG);
            painter.winFace();
        }
        else
        {
            for (int num : minePos)
            {
                if (cover[num % Main.X_NUM][num / Main.X_NUM].getImage().equals(FLAG_IMG))
                    data[num % Main.X_NUM][num / Main.X_NUM].setXMine();
                pane.getChildren().remove(cover[num % Main.X_NUM][num / Main.X_NUM]);
            }
            painter.loseFace();
        }

        for (ImageView[] ivArr : cover)
            for (ImageView iv : ivArr)
            {
                iv.setOnMousePressed(null);
                iv.setOnMouseExited(null);
                iv.setOnMouseClicked(null);
                iv.setOnDragDetected(null);
                iv.setOnMouseDragEntered(null);
                iv.setOnMouseDragExited(null);
                iv.setOnMouseDragReleased(null);
            }
    }

    private void setEventHandler()
    {
        CoverHandlers handle = new CoverHandlers(this);
        for (ImageView[] ivArr : cover)
            for (ImageView iv : ivArr)
            {
                iv.setOnMousePressed(handle.pressed);
                iv.setOnMouseExited(handle.exited);
                iv.setOnMouseClicked(handle.open);
                iv.setOnDragDetected(handle.detected);
                iv.setOnMouseDragEntered(handle.dragPressed);
                iv.setOnMouseDragExited(handle.exited);
                iv.setOnMouseDragReleased(handle.open);
            }
    }

    public int plusFlag()
    {
        return ++flagNum;
    }

    public int minusFlag()
    {
        return --flagNum;
    }
}