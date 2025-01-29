package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Caterpillar extends Herbivore{
    public Caterpillar(Location l) {
        super(l);
        eatingProbabilities = Settings.CATERPILLAR_EATING_PROBABILITIES;
        this.animalType = CreatureType.CATERPILLAR;
        weight = Settings.caterpillarWeight;
        maxSatiety = Settings.caterpillarSatiety;
        satiety = Settings.caterpillarSatiety;
        moveSpeed = Settings.caterpillarSpeed;
    }
    public Animal getChild(){
        return new Caterpillar(location);
    }
}
