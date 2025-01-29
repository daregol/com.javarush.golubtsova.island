package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Rabbit extends Herbivore{
    public Rabbit(Location l) {
        super(l);
        eatingProbabilities = Settings.RABBIT_EATING_PROBABILITIES;
        this.animalType = CreatureType.RABBIT;
        weight = Settings.rabbitWeight;
        maxSatiety = Settings.rabbitSatiety;
        satiety = Settings.rabbitSatiety;
        moveSpeed = Settings.rabbitSpeed;
    }
    public Animal getChild(){
        return new Rabbit(location);
    }
}
