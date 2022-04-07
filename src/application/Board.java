package application;

public class Board {
    private static Form[][] data;

    public static int[] minePos;

    public void set() {
        data = Main.data;
        for (int i = 0; i < Main.X_NUM; i++)
            for (int j = 0; j < Main.Y_NUM; j++)
                data[i][j] = new Form();
        addToPane();
    }

    public void init() {
        for (int i = 0; i < Main.X_NUM; i++)
            for (int j = 0; j < Main.Y_NUM; j++)
                data[i][j].init();

        minePos = new int[Main.MINE_NUM];
        for (int i : minePos)
            i = 0;
        makeMine(minePos);
        setMine(minePos);

        for (int i = 0; i < Main.X_NUM; i++)
            for (int j = 0; j < Main.Y_NUM; j++)
                data[i][j].setType(i, j);
    }

    public void addToPane() {
        for (int i = 0; i < Main.X_NUM; i++)
            for (int j = 0; j < Main.Y_NUM; j++)
                data[i][j].addToPane();
    }

    public void makeMine(int[] minePos) {
        int tot = Main.X_NUM * Main.Y_NUM;
        int[] base = new int[Main.MINE_NUM];
        int stop;

        for (int i = 0; i < Main.MINE_NUM; i++) {
            int a = (int) (Math.random() * (tot - i));
            for (stop = 0; stop < i; stop++) {
                if (base[stop] > a)
                    break;
            }

            minePos[i] = a + stop;
            for (int k = i; k > stop; k--)
                base[k] = base[k - 1] - 1;
            base[stop] = a;
        }
    }

    public void setMine(int[] minePos) {
        for (int i = 0; i < Main.MINE_NUM; i++) {
            int x = minePos[i] % Main.X_NUM;
            int y = minePos[i] / Main.X_NUM;

            data[x][y].setMine();

            if (x != 0 && y != 0)
                data[x - 1][y - 1].plus();
            if (y != 0)
                data[x][y - 1].plus();
            if (x != Main.X_NUM - 1 && y != 0)
                data[x + 1][y - 1].plus();
            if (x != 0)
                data[x - 1][y].plus();
            if (x != Main.X_NUM - 1)
                data[x + 1][y].plus();
            if (x != 0 && y != Main.Y_NUM - 1)
                data[x - 1][y + 1].plus();
            if (y != Main.Y_NUM - 1)
                data[x][y + 1].plus();
            if (x != Main.X_NUM - 1 && y != Main.Y_NUM - 1)
                data[x + 1][y + 1].plus();
        }
    }
}
