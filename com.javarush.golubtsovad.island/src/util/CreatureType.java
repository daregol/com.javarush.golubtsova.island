package util;

public enum CreatureType {

    WOLF(Settings.wolfCount),
    BOA(Settings.boaCount),
    FOX(Settings.foxCount),
    BEAR(Settings.bearCount),
    EAGLE(Settings.eagleCount),
    HORSE(Settings.horseCount),
    DEER(Settings.deerCount),
    RABBIT(Settings.rabbitCount),
    MOUSE(Settings.mouseCount),
    GOAT(Settings.goatCount),
    SHEEP(Settings.sheepCount),
    BOAR(Settings.boarCount),
    BUFFALO(Settings.buffaloCount),
    DUCK(Settings.duckCount),
    CATERPILLAR(Settings.caterpillarCount),
    PLANT(Settings.plantCount);

    public final int maxQuantity;

    // Конструктор для инициализации полей
    CreatureType(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
