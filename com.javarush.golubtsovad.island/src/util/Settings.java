package util;

import java.util.HashMap;

public class Settings {

  public final static int columnsCount = 20;
  public final static int rowsCount = 100;

  public final static double chanceOfReproducing = 0.25;
  public final static double chanceToMove = 0.5;
  public final static double chanceToSpawn = 0.3;

  public final static int wolfWeight = 50;
  public final static int boaWeight = 15;
  public final static int foxWeight = 8;
  public final static int bearWeight = 500;
  public final static int eagleWeight = 6;
  public final static int horseWeight = 400;
  public final static int deerWeight = 300;
  public final static int rabbitWeight = 2;
  public final static double mouseWeight = 0.05;
  public final static int goatWeight = 60;
  public final static int sheepWeight = 70;
  public final static int boarWeight = 400;
  public final static int buffaloWeight = 700;
  public final static int duckWeight = 1;
  public final static double caterpillarWeight = 0.01;

  public final static int plantCount = 200;
  public final static int wolfCount = 30;
  public final static int boaCount = 30;
  public final static int foxCount = 30;
  public final static int bearCount = 5;
  public final static int eagleCount = 20;
  public final static int horseCount = 20;
  public final static int deerCount = 20;
  public final static int rabbitCount = 150;
  public final static int mouseCount = 500;
  public final static int goatCount = 140;
  public final static int sheepCount = 140;
  public final static int boarCount = 50;
  public final static int buffaloCount = 10;
  public final static int duckCount = 200;
  public final static int caterpillarCount = 1000;


  public final static int wolfSpeed = 3;
  public final static int boaSpeed = 1;
  public final static int foxSpeed = 2;
  public final static int bearSpeed = 2;
  public final static int eagleSpeed = 3;
  public final static int horseSpeed = 4;
  public final static int deerSpeed = 4;
  public final static int rabbitSpeed = 2;
  public final static int mouseSpeed = 1;
  public final static int goatSpeed = 3;
  public final static int sheepSpeed = 3;
  public final static int boarSpeed = 2;
  public final static int buffaloSpeed = 3;
  public final static int duckSpeed = 4;
  public final static int caterpillarSpeed = 0;

  public final static int wolfSatiety = 8;
  public final static int boaSatiety = 3;
  public final static int foxSatiety = 2;
  public final static int bearSatiety = 80;
  public final static int eagleSatiety = 1;
  public final static int horseSatiety = 60;
  public final static int deerSatiety = 50;
  public final static double rabbitSatiety = 0.45;
  public final static double mouseSatiety = 0.01;
  public final static int goatSatiety = 10;
  public final static int sheepSatiety = 15;
  public final static int boarSatiety = 50;
  public final static int buffaloSatiety = 100;
  public final static double duckSatiety = 0.15;
  public final static int caterpillarSatiety = 0;

  public static final HashMap<CreatureType, Double> WOLF_EATING_PROBABILITIES = new HashMap<>();
  static {
    WOLF_EATING_PROBABILITIES.put(CreatureType.HORSE, 0.10);
    WOLF_EATING_PROBABILITIES.put(CreatureType.DEER, 0.15);
    WOLF_EATING_PROBABILITIES.put(CreatureType.RABBIT, 0.60);
    WOLF_EATING_PROBABILITIES.put(CreatureType.MOUSE, 0.80);
    WOLF_EATING_PROBABILITIES.put(CreatureType.GOAT, 0.60);
    WOLF_EATING_PROBABILITIES.put(CreatureType.SHEEP, 0.70);
    WOLF_EATING_PROBABILITIES.put(CreatureType.BOAR, 0.15);
    WOLF_EATING_PROBABILITIES.put(CreatureType.BUFFALO, 0.10);
    WOLF_EATING_PROBABILITIES.put(CreatureType.DUCK, 0.40);
  }

  public static final HashMap<CreatureType, Double> BOA_EATING_PROBABILITIES = new HashMap<>();
  static {
    BOA_EATING_PROBABILITIES.put(CreatureType.FOX, 0.15);
    BOA_EATING_PROBABILITIES.put(CreatureType.RABBIT, 0.20);
    BOA_EATING_PROBABILITIES.put(CreatureType.MOUSE, 0.40);
    BOA_EATING_PROBABILITIES.put(CreatureType.DUCK, 0.10);
  }

  public static final HashMap<CreatureType, Double> FOX_EATING_PROBABILITIES = new HashMap<>();
  static {
    FOX_EATING_PROBABILITIES.put(CreatureType.RABBIT, 0.70);
    FOX_EATING_PROBABILITIES.put(CreatureType.MOUSE, 0.90);
    FOX_EATING_PROBABILITIES.put(CreatureType.DUCK, 0.60);
    FOX_EATING_PROBABILITIES.put(CreatureType.CATERPILLAR, 0.40);
  }

  public static final HashMap<CreatureType, Double> BEAR_EATING_PROBABILITIES = new HashMap<>();
  static {
    BEAR_EATING_PROBABILITIES.put(CreatureType.BOA, 0.80);
    BEAR_EATING_PROBABILITIES.put(CreatureType.HORSE, 0.40);
    BEAR_EATING_PROBABILITIES.put(CreatureType.DEER, 0.80);
    BEAR_EATING_PROBABILITIES.put(CreatureType.RABBIT, 0.80);
    BEAR_EATING_PROBABILITIES.put(CreatureType.MOUSE, 0.90);
    BEAR_EATING_PROBABILITIES.put(CreatureType.GOAT, 0.70);
    BEAR_EATING_PROBABILITIES.put(CreatureType.SHEEP, 0.70);
    BEAR_EATING_PROBABILITIES.put(CreatureType.BOAR, 0.50);
    BEAR_EATING_PROBABILITIES.put(CreatureType.BUFFALO, 0.20);
    BEAR_EATING_PROBABILITIES.put(CreatureType.DUCK, 0.10);
  }

  public static final HashMap<CreatureType, Double> EAGLE_EATING_PROBABILITIES = new HashMap<>();
  static {
    EAGLE_EATING_PROBABILITIES.put(CreatureType.FOX, 0.10);
    EAGLE_EATING_PROBABILITIES.put(CreatureType.RABBIT, 0.90);
    EAGLE_EATING_PROBABILITIES.put(CreatureType.MOUSE, 0.90);
    EAGLE_EATING_PROBABILITIES.put(CreatureType.DUCK, 0.80);
  }

  public static final HashMap<CreatureType, Double> HORSE_EATING_PROBABILITIES = new HashMap<>();
  static {
    HORSE_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> DEER_EATING_PROBABILITIES = new HashMap<>();
  static {
    DEER_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> RABBIT_EATING_PROBABILITIES = new HashMap<>();
  static {
    RABBIT_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> MOUSE_EATING_PROBABILITIES = new HashMap<>();
  static {
    MOUSE_EATING_PROBABILITIES.put(CreatureType.CATERPILLAR, 0.9);
    MOUSE_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> GOAT_EATING_PROBABILITIES = new HashMap<>();
  static {
    GOAT_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> SHEEP_EATING_PROBABILITIES = new HashMap<>();
  static {
    SHEEP_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> BOAR_EATING_PROBABILITIES = new HashMap<>();
  static {
    BOAR_EATING_PROBABILITIES.put(CreatureType.MOUSE, 0.5);
    BOAR_EATING_PROBABILITIES.put(CreatureType.CATERPILLAR, 0.9);
    BOAR_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> BUFFALO_EATING_PROBABILITIES = new HashMap<>();
  static {
    BUFFALO_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> DUCK_EATING_PROBABILITIES = new HashMap<>();
  static {
    DUCK_EATING_PROBABILITIES.put(CreatureType.CATERPILLAR, 0.9);
    DUCK_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }

  public static final HashMap<CreatureType, Double> CATERPILLAR_EATING_PROBABILITIES = new HashMap<>();
  static {
    CATERPILLAR_EATING_PROBABILITIES.put(CreatureType.PLANT, 1.0);
  }
}