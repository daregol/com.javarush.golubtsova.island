package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Duck extends Herbivore{
    public Duck(Location l) {
        super(l);
        eatingProbabilities = Settings.DUCK_EATING_PROBABILITIES;
        this.animalType = CreatureType.DUCK;
        weight = Settings.duckWeight;
        maxSatiety = Settings.duckSatiety;
        satiety = Settings.duckSatiety;
        moveSpeed = Settings.duckSpeed;
    }
    public Animal getChild(){
        return new Duck(location);
    }
}
