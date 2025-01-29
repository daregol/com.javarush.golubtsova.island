package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Boar extends Herbivore{
    public Boar(Location l) {
        super(l);
        eatingProbabilities = Settings.BOAR_EATING_PROBABILITIES;
        this.animalType = CreatureType.BOAR;
        maxSatiety = Settings.boarSatiety;
        satiety = Settings.boarSatiety;
        weight = Settings.boarWeight;
        moveSpeed = Settings.boarSpeed;
        animalCount = Settings.boarCount;
    }
    public Animal getChild(){
        return new Boar(location);
    }
}
