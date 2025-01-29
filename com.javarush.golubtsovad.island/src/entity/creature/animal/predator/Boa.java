package entity.creature.animal.predator;

import entity.Location;
import entity.creature.animal.Animal;
import util.CreatureType;
import util.Settings;

public class Boa extends Predator{
    public Boa(Location l) {
        super(l);
        eatingProbabilities = Settings.BOA_EATING_PROBABILITIES;
        this.animalType = CreatureType.BOA;
        weight = Settings.boaWeight;
        maxSatiety = Settings.boaSatiety;
        satiety = Settings.boaSatiety;
        moveSpeed = Settings.boaSpeed;
    }
    public Animal getChild(){
        return new Boa(location);
    }
}
