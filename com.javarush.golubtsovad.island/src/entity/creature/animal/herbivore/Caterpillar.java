package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Caterpillar extends Herbivore{
    public int cnt_life;
    public Caterpillar(Location l) {
        super(l);
        cnt_life = 3;
        eatingProbabilities = Settings.CATERPILLAR_EATING_PROBABILITIES;
        this.animalType = CreatureType.CATERPILLAR;
        weight = Settings.caterpillarWeight;
        maxSatiety = Settings.caterpillarSatiety;
        satiety = Settings.caterpillarSatiety;
        moveSpeed = Settings.caterpillarSpeed;
        animalCount = Settings.caterpillarCount;
    }
    public Animal getChild(){
        return new Caterpillar(location);
    }

    public String getImage(){
        return "🐛";
    }

    @Override
    public void decreaseSatiety() {
        if(cnt_life == 0) { alive = false; }
        cnt_life--;
    }

}
