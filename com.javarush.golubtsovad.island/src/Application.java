import entity.Island;
import util.Manager;
import util.Runner;
import util.Settings;

public class Application {
    public static void main(String[] args) {
        // ТОЧКА СБОРКИ И СТАРТА МОЕГО ПРИЛОЖЕНИЯ

        Island island = new Island(Settings.columnsCount, Settings.rowsCount);
        Manager animalManager = new Manager(island);
        Runner runner = new Runner(island);

        runner.start();

    }
}
