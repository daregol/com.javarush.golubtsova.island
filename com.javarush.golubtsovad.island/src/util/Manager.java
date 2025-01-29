package util;

import entity.Island;
import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Herbivore;
import entity.creature.animal.predator.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Manager {

    private final Island island;

    public Manager(Island island) {
        this.island = island;
    }

    public void update() {
        updateAnimals();
        updatePlant();
    }

    public void updatePlant() {
        for (int x = 0; x < Settings.columnsCount; x++) {
            for (int y = 0; y < Settings.rowsCount; y++) {
                Location loc = island.getLocation(x, y);
                loc.restorePlant();
            }
        }
    }

    public void updateAnimals() {
        for (int x = 0; x < Settings.columnsCount; x++) {
            for (int y = 0; y < Settings.rowsCount; y++) {
                Location loc = island.getLocation(x, y); //Работаем с каждой локацией
                List<Animal> animals = new ArrayList<>();

                for (CreatureType creatureType : CreatureType.values()) {
                    if(creatureType == CreatureType.PLANT) { continue; }
                    animals.addAll(loc.getAnimalsMap().get(creatureType));
                }

                for (Animal animal : animals) {
                    animal.decreaseSatiety();

                    if (!animal.isAlive()) {
                        loc.removeAnimal(animal);  //Если животное мёртвое, удаляем его
                        continue;
                    }

                    //Обновление веса с учётом текущей сытости
                    animal.updateWeight();

                    //Животное кушает
                    Animal otherAnimal;
                    int randomInt;
                    do {
                        randomInt = ThreadLocalRandom.current().nextInt(animals.size());
                        otherAnimal = animals.get(randomInt);
                    } while(animal.getAnimalType() == otherAnimal.getAnimalType());

                    if(animal instanceof Herbivore) {
                        if (animal.getAnimalType() == CreatureType.DUCK ||
                                animal.getAnimalType() == CreatureType.MOUSE ||
                                animal.getAnimalType() == CreatureType.BOAR) {
                            boolean randomBool = ThreadLocalRandom.current().nextBoolean();
                            if(randomBool) {
                                animal.eat(otherAnimal);
                            }
                            else {
                                animal.eat(loc.getPlant());
                            }
                        }
                        else {
                            animal.eat(loc.getPlant());
                        }
                    }
                    else if(animal instanceof Predator) {
                        animal.eat(otherAnimal);
                    }

                    //Движение животного
                    for(int i = 0; i < animal.getMaxSpeed() &&
                            ThreadLocalRandom.current().nextDouble() < Settings.chanceToMove; i++) {

                        Direction d;
                        do {
                            int randomDirection = ThreadLocalRandom.current().nextInt(0, 4);
                            d = Direction.values()[randomDirection];
                            animal.move(island, d);
                        } while (!(animal.move(island, d)));
                    }

                    //Размножение животных
                    Animal child = animal.reproduce();
                    if (child != null) {
                        //System.out.println(animal.getSymbol() + " размножился!");
                        loc.addAnimal(child);
                    }
                }
            }
        }
    }
}