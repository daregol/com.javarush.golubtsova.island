package util;

import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.*;
import entity.creature.animal.predator.*;
import entity.Location;

public class AnimalFactory {
    public Animal createAnimal(CreatureType animalType, Location location){
        Animal animal = switch (animalType){
            case WOLF -> new Wolf(location);
            case BOA -> new Boa(location);
            case FOX -> new Fox(location);
            case BEAR -> new Bear(location);
            case EAGLE -> new Eagle(location);
            case HORSE -> new Horse(location);
            case DEER -> new Deer(location);
            case RABBIT -> new Rabbit(location);
            case MOUSE -> new Mouse(location);
            case GOAT -> new Goat(location);
            case SHEEP -> new Sheep(location);
            case BOAR -> new Boar(location);
            case BUFFALO -> new Buffalo(location);
            case DUCK -> new Duck(location);
            case CATERPILLAR -> new Caterpillar(location);
            default -> null;
        };
        return animal;
    }
}
