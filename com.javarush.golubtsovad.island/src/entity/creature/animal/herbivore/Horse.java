package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Horse extends Herbivore{
    public Horse(Location l) {
        super(l);
        eatingProbabilities = Settings.HORSE_EATING_PROBABILITIES;
        this.animalType = CreatureType.HORSE;
        weight = Settings.horseWeight;
        maxSatiety = Settings.horseSatiety;
        satiety = Settings.horseSatiety;
        moveSpeed = Settings.horseSpeed;
    }
    public Animal getChild(){
        return new Horse(location);
    }
}
