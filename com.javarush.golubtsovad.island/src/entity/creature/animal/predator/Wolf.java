package entity.creature.animal.predator;

import entity.Location;
import entity.creature.animal.Animal;
import util.CreatureType;
import util.Settings;

public class Wolf extends Predator{

    public Wolf(Location l) {
        super(l);
        eatingProbabilities = Settings.WOLF_EATING_PROBABILITIES;
        this.animalType = CreatureType.WOLF;
        weight = Settings.wolfWeight;
        maxSatiety = Settings.wolfSatiety;
        satiety = Settings.wolfSatiety;
        moveSpeed = Settings.wolfSpeed;
        animalCount = Settings.wolfCount;
    }
    public Animal getChild(){
        return new Wolf(location);
    }

    public String getImage(){
        return "🐺";
    }
}
