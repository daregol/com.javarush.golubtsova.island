package entity.creature.animal;

import entity.Island;
import entity.Location;
import entity.creature.Creature;
import entity.creature.plant.Plant;
import util.CreatureType;
import util.Direction;
import util.Settings;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Creature {

    protected HashMap<CreatureType, Double> eatingProbabilities = new HashMap<>();
    protected double satiety;
    protected double maxSatiety;
    protected int moveSpeed;
    protected double weight;
    protected double currentWeight;
    public CreatureType animalType;
    protected Location location;
    protected int animalCount;
    private boolean alive = true;

    public Animal(Location l){
         this.location = l;
    }

    public CreatureType getAnimalType() {
        return animalType;
    }

    public void decreaseSatiety(){
        satiety-=0.2*maxSatiety;
        if (satiety <= 0){
            System.out.println(this.getImage() + " умер от голода(");
            die();
        }
    }

    public void eat(Creature c) {
        if (!alive)
            return;
        if (c instanceof Plant){
            Plant plant = (Plant) c;
            double eaten = maxSatiety - satiety;
            if (plant.getQuantity() == 0){
                return;
            }
            if (plant.getQuantity() >= eaten){
                satiety += eaten;
                plant.setQuantity(plant.getQuantity() - (int) Math.ceil(eaten));
            }
            else if (plant.getQuantity() < eaten){
                satiety += plant.getQuantity();
                plant.setQuantity(0);
            }
            System.out.println(this.getImage() + " съел " + plant.getImage());
        }
        if (c instanceof Animal){
            Animal animal = (Animal) c;
            double probability = eatingProbability(animal);
            if (ThreadLocalRandom.current().nextDouble() < probability) {
                System.out.println(this.getImage() + " съел " + animal.getImage());
                satiety += animal.weight;
                if (satiety > maxSatiety) {
                    satiety = maxSatiety;
                }
                animal.die();
            }
        }
        return;
    }

    public boolean move(Island island, Direction d) {
        Location targetLocation;
        switch (d) {
            case UP:
                targetLocation = island.getLocation(location.getX(), location.getY()+1);
                break;
            case DOWN:
                targetLocation = island.getLocation(location.getX(), location.getY()-1);
                break;
            case LEFT:
                targetLocation = island.getLocation(location.getX()-1, location.getY());
                break;
            case RIGHT:
                targetLocation = island.getLocation(location.getX()+1, location.getY());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + d);
        }
        if (targetLocation != null){
            location.removeAnimal(this);
            targetLocation.addAnimal(this);
            this.location = targetLocation;
            return true;
        }
        return false;
    }

    public Animal reproduce() {
        if (!alive || location == null) {
            return null;
        }

        if (ThreadLocalRandom.current().nextDouble() < Settings.chanceOfReproducing) {
            Animal child = getChild();
            return child;
        }
        return null;
    }

    public boolean isAlive() {
        return alive;
    }

    public Location getLocation() {
        return location;
    }

    public boolean compareLocations(Location location) {
        if (this.location.getX() == location.getX() && this.location.getY() == location.getY()) {return true;}
        return false;
    }

    void die() {
        alive = false;
        //System.out.println(this.getImage() + " умер(");
    }

    public void updateWeight(){
        currentWeight = weight - (maxSatiety - satiety);
    }

    public int getMaxCount(){
        return animalCount;
    }

    public int getMaxSpeed(){
        return moveSpeed;
    }

    public double eatingProbability(Animal animal) {
        return eatingProbabilities.getOrDefault(animal.getAnimalType(), 0.0);
    }

    public abstract String getImage();

    public abstract Animal getChild();
}
