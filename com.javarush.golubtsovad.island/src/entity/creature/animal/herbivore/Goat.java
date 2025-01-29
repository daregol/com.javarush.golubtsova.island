package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Goat extends Herbivore{
    public Goat(Location l) {
        super(l);
        eatingProbabilities = Settings.GOAT_EATING_PROBABILITIES;
        this.animalType = CreatureType.GOAT;
        weight = Settings.goatWeight;
        maxSatiety = Settings.goatSatiety;
        satiety = Settings.goatSatiety;
        moveSpeed = Settings.goatSpeed;
        animalCount = Settings.goatCount;
    }
    public Animal getChild(){
        return new Goat(location);
    }
    public String getImage(){
        return "🐐";
    }
}
