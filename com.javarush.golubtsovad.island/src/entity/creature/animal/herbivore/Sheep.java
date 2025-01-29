package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Sheep extends Herbivore{

    public Sheep(Location l) {
        super(l);
        eatingProbabilities = Settings.SHEEP_EATING_PROBABILITIES;
        this.animalType = CreatureType.SHEEP;
        weight = Settings.sheepWeight;
        maxSatiety = Settings.sheepSatiety;
        satiety = Settings.sheepSatiety;
        moveSpeed = Settings.sheepSpeed;
        animalCount = Settings.sheepCount;
    }
    public Animal getChild(){
        return new Sheep(location);
    }
    public String getImage(){
        return "🐑";
    }
}
