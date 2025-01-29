package entity.creature.animal.predator;

import entity.Location;
import entity.creature.animal.Animal;
import util.CreatureType;
import util.Settings;

public class Bear extends Predator{
    public Bear(Location l) {
        super(l);
        eatingProbabilities = Settings.BEAR_EATING_PROBABILITIES;
        this.animalType = CreatureType.BEAR;
        weight = Settings.bearWeight;
        maxSatiety = Settings.bearSatiety;
        satiety = Settings.bearSatiety;
        moveSpeed = Settings.bearSpeed;
    }
    public Animal getChild(){
        return new Bear(location);
    }
}
