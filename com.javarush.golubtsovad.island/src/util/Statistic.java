package util;

import entity.Island;
import entity.Location;
import entity.creature.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class Statistic {

    private Island island;

    public Statistic(Island island) {
        this.island = island;
    }

    public synchronized void print() {
        printStatistics();
        printMap();
    }

    private void printStatistics() {
        int totalAnimals = 0;
        int totalPlants = 0;
        System.out.println("Статистика по каждой локации \uD83D\uDC47 ");
        for (int x = 0; x < Settings.columnsCount; x++) {
            for (int y = 0; y < Settings.rowsCount; y++) {
                Location loc = island.getLocation(x, y);
                System.out.print("(" + x + ";" + y + ")");
                for (CreatureType creatureType : CreatureType.values()) {
                    if(creatureType == CreatureType.PLANT) {continue;}
                    if (loc.getAnimalsMap().get(creatureType).isEmpty()) {continue;}
                    System.out.print(loc.getAnimalsMap().get(creatureType).get(0).getImage() +
                            "(" + loc.getAnimalsMap().get(creatureType).size() + ") ");
                }
                System.out.println();
            }
        }

        for (int x = 0; x < Settings.columnsCount; x++) {
            for (int y = 0; y < Settings.rowsCount; y++) {
                Location loc = island.getLocation(x, y);
                List<Animal> animals = new ArrayList<>();
                for (CreatureType creatureType : CreatureType.values()) {
                    if(creatureType == CreatureType.PLANT) { continue; }
                    animals.addAll(loc.getAnimalsMap().get(creatureType));
                }
                totalAnimals += animals.size();
                totalPlants += loc.getPlant().getQuantity();

            }
        }

        System.out.println("КОЛИЧЕСТВО Животных: " + totalAnimals + ", Растений: " + totalPlants);
    }



    private void printMap() {
        System.out.println("-------------------------------------- Остров --------------------------------------");
        for (int x = 0; x < Settings.columnsCount; x++) {
            for (int y = 0; y < Settings.rowsCount; y++) {
                Location loc = island.getLocation(x, y);
                List<Animal> animals = new ArrayList<>();
                for (CreatureType creatureType : CreatureType.values()) {
                    if(creatureType == CreatureType.PLANT) { continue; }
                    animals.addAll(loc.getAnimalsMap().get(creatureType));
                }

                if (!animals.isEmpty()) {
                    System.out.print(animals.get(0).getImage());

                } else if (loc.getPlant().getQuantity() > 0) {
                    System.out.print("🌱");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------");
    }
}
