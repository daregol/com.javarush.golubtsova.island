package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.predator.Bear;
import util.CreatureType;
import util.Settings;

public class Mouse extends Herbivore{
    public Mouse(Location l) {
        super(l);
        eatingProbabilities = Settings.MOUSE_EATING_PROBABILITIES;
        this.animalType = CreatureType.MOUSE;
        weight = Settings.mouseWeight;
        maxSatiety = Settings.mouseSatiety;
        satiety = Settings.mouseSatiety;
        moveSpeed = Settings.mouseSpeed;
        animalCount = Settings.mouseCount;
    }
    public Animal getChild(){
        return new Mouse(location);
    }
    public String getImage(){
        return "🐁";
    }
}
