package entity.creature.animal.predator;

import entity.Location;
import entity.creature.animal.Animal;
import util.CreatureType;
import util.Settings;

public class Eagle extends Predator{
    public Eagle(Location l) {
        super(l);
        eatingProbabilities = Settings.EAGLE_EATING_PROBABILITIES;
        this.animalType = CreatureType.EAGLE;
        weight = Settings.eagleWeight;
        maxSatiety = Settings.eagleSatiety;
        satiety = Settings.eagleSatiety;
        moveSpeed = Settings.eagleSpeed;
    }
    public Animal getChild(){
        return new Eagle(location);
    }
}
