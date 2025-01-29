package entity.creature.animal.predator;

import entity.Location;
import entity.creature.animal.Animal;
import util.CreatureType;
import util.Settings;

public class Fox extends Predator{
    public Fox(Location l) {
        super(l);
        eatingProbabilities = Settings.FOX_EATING_PROBABILITIES;
        this.animalType = CreatureType.FOX;
        weight = Settings.foxWeight;
        maxSatiety = Settings.foxSatiety;
        satiety = Settings.foxSatiety;
        moveSpeed = Settings.foxSpeed;
    }
    public Animal getChild(){
        return new Fox(location);
    }
}
