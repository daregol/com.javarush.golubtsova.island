package entity;

public class Island {

    private int columnsCount;
    private int rowsCount;

    public Location[][] locations;

    public Island(int columnsCount, int rowsCount) {
        System.out.println("Создание острова " + columnsCount + "x" + rowsCount + " клеток");
        this.columnsCount = columnsCount;
        this.rowsCount = rowsCount;
        locations = new Location[columnsCount][rowsCount];
        initializeIsland();
    }

    public void initializeIsland(){
        for (int i = 0; i < columnsCount; i++){
            for (int j = 0; j < rowsCount; j++){
                locations[i][j] = new Location(i, j);
            }
        }
    }

    public Location getLocation(int x, int y){
        if (x < columnsCount && y < rowsCount && x >= 0 && y >= 0)
            return locations[x][y];
        else
            return null;
    }
}
