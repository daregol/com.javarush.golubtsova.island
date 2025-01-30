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

    public synchronized void update() {
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
                    int cnt = 0;
                    do {
                        randomInt = ThreadLocalRandom.current().nextInt(animals.size());
                        otherAnimal = animals.get(randomInt);
                        cnt++;
                    } while((animal.getAnimalType() == otherAnimal.getAnimalType()) && cnt < 10000 &&
                            !animal.compareLocations(otherAnimal.getLocation()));

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
                    if(animal instanceof Predator) {
                        animal.eat(otherAnimal);
                    }

                    //Движение животного
                    for(int i = 0; i < animal.getMaxSpeed() && ThreadLocalRandom.current().nextDouble() < Settings.chanceToMove; i++) {
                        if(i==0) {
                            //System.out.println(animal.getImage() + " подвигался!");
                        }
                        Direction d;
                        do {
                            int randomDirection = ThreadLocalRandom.current().nextInt(0, 4);
                            d = Direction.values()[randomDirection];
                            animal.move(island, d);
                        } while (!(animal.move(island, d)));
                    }

                    //Размножение животных
                    int sameAnimal = 0;
                    for (Animal a : animals) {
                        if (animal.getAnimalType() == a.getAnimalType() && animal.compareLocations(a.getLocation())) {
                            sameAnimal++;
                        }
                    }

                    for (Animal animalToReproduce : animals) {
                        if(animal.getAnimalType() == animalToReproduce.getAnimalType() &&
                                animal.compareLocations(animalToReproduce.getLocation())) {
                            Animal child = animal.reproduce();
                            if (child != null && animal.getMaxCount() >= sameAnimal && sameAnimal > 1) {
                                //System.out.println(animal.getImage() + " размножился!");
                                loc.addAnimal(child);
                                break;
                            }
                            else if(child == null) {
                                break;
                            }
                        }
                    }


                }

            }
        }
    }
}