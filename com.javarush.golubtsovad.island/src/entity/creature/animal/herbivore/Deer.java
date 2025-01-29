package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Deer extends Herbivore{
    public Deer(Location l) {
        super(l);
        eatingProbabilities = Settings.DEER_EATING_PROBABILITIES;
        this.animalType = CreatureType.DEER;
        weight = Settings.deerWeight;
        maxSatiety = Settings.deerSatiety;
        satiety = Settings.deerSatiety;
        moveSpeed = Settings.deerSpeed;
        animalCount = Settings.deerCount;
    }
    public Animal getChild(){
        return new Deer(location);
    }
    public String getImage(){
        return "🦌";
    }
}
