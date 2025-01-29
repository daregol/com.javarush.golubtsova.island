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

                if (!animals.isEmpty() && !(loc.getPlant().getQuantity() == 0)) {
                    System.out.print(animals.get(0).getImage());

                } else if (!(loc.getPlant().getQuantity() == 0)) {
                    System.out.print("🌱");
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------");
    }
}
