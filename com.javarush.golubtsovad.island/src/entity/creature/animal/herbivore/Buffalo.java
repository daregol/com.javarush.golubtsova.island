package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Buffalo extends Herbivore{
    public Buffalo(Location l) {
        super(l);
        eatingProbabilities = Settings.BUFFALO_EATING_PROBABILITIES;
        this.animalType = CreatureType.BUFFALO;
        weight = Settings.buffaloWeight;
        maxSatiety = Settings.buffaloSatiety;
        satiety = Settings.buffaloSatiety;
        moveSpeed = Settings.buffaloSpeed;
        animalCount = Settings.buffaloCount;
    }
    public Animal getChild(){
        return new Buffalo(location);
    }
    public String getImage(){
        return "🐃";
    }
}
