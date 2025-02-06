package util;

import entity.Island;
import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Herbivore;
import entity.creature.animal.predator.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadLocalRandom;

public class Manager {

    private final Island island;

    public Manager(Island island) {
        this.island = island;
    }

    // Обновляет состояние острова, распределяя работу по локациям на пул потоков
    public void update() {
        final int totalLocations = Settings.columnsCount * Settings.rowsCount;
        CountDownLatch latch = new CountDownLatch(totalLocations);
        AtomicInteger completedCycles = new AtomicInteger(0);
        Lock lock = new ReentrantLock();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Для каждой локации отправляем задачу на обновление
        for (int x = 0; x < Settings.columnsCount; x++) {
            for (int y = 0; y < Settings.rowsCount; y++) {
                final int col = x;
                final int row = y;
                executor.submit(() -> {
                    try {
                        // Блокировка на время работы с данной локацией (при необходимости)
                        lock.lock();
                        Location loc = island.getLocation(col, row);
                        updateAnimals(loc);
                        updatePlant(loc);
                        completedCycles.incrementAndGet();
                    } finally {
                        lock.unlock();
                        latch.countDown();
                    }
                });
            }
        }

        try {
            // Ожидаем завершения всех задач для локаций
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
        System.out.println("Завершено циклов обновления: " + completedCycles.get());
    }

    public void updatePlant(Location loc) {
        loc.restorePlant();
    }

    public void updateAnimals(Location loc) {
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