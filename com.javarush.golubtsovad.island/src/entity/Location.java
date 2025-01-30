package entity;

import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import util.AnimalFactory;
import util.CreatureType;
import util.Settings;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    private final int x;
    private final int y;

    Plant plant;
    HashMap<CreatureType, List<Animal>> animalsMap = new HashMap<>();
    AnimalFactory factory = new AnimalFactory();

    public Location(int x, int y){
        this.x = x;
        this.y = y;
        plant = new Plant();
        initMap();
        for(CreatureType a : CreatureType.values()) {
            if(a == CreatureType.PLANT) { continue; }

            for (int i = 0; i < 3 && (ThreadLocalRandom.current().nextDouble() < Settings.chanceToSpawn); i++) {
                animalsMap.get(a).add(factory.createAnimal(a, this));
            }
        }
    }

    public HashMap<CreatureType, List<Animal>> getAnimalsMap() {
        return animalsMap;
    }

    public Plant getPlant() {
        return plant;
    }

    public void initMap() {
        for(CreatureType a : CreatureType.values()) {
            if(a == CreatureType.PLANT) { continue; }
            animalsMap.put(a, new ArrayList<Animal>());
        }
    }

    public void restorePlant(){
        plant.setQuantity(plant.getQuantity() + 1);
        if (plant.getQuantity() > 200)
            plant.setQuantity(200);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void addAnimal(Animal animal){
        if (animalsMap.get(animal.getAnimalType()).size() < animal.getMaxCount()) {
            animalsMap.get(animal.getAnimalType()).add(animal);
        }
        return;
    }

    public void removeAnimal(Animal animal){
        if (!(animalsMap.get(animal.getAnimalType()).isEmpty())) {
            animalsMap.get(animal.getAnimalType()).remove(animal);
        }
    }
}
