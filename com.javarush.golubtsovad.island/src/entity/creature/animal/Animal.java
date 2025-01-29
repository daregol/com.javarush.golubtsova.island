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
        --satiety;
        if (satiety <= 0)
            die();
    }

    // ДЕФОЛТНАЯ РЕАЛИЗАЦИЯ
    // КТО ИМЕННО ЭТОТ Creature БУДЕТ ВЛИЯТЬ НА ФОРМАТ ПОЕДАНИЯ
    // КОГДА СТАНЕТ ПОНЯТНО КТО КОНКРЕТНО ЭТО Creature
    // МЫ МОЖЕМ ОПРЕДЕЛИТЬ ВЕРОЯТНОСТЬ ЕГО ПОЕДАНИЯ И РЕАЛИЗОВАТЬ ЭТУ ЛОГИКУ

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
                //sout овца съела...
            }
            System.out.println("КТО-ТО ПОЕЛ");
        }
        else if (c instanceof Animal){
            Animal animal = (Animal) c;
            double probability = eatingProbability(animal);
            if (ThreadLocalRandom.current().nextDouble() < probability) {
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
            System.out.println("КТО-ТО ПОДВИГАЛСЯ");
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
            //System.out.println(this.getSymbol() + " размножился!");
            System.out.println("КТО-ТО ПОТРАХАЛСЯ");
            return child;
        }
        return null;
    }

    public boolean isAlive() {
        return alive;
    }

    void die() {
        alive = false;
        System.out.println("КТО-ТО УМЕР");
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

    protected double eatingProbability(Creature creature) {
        return eatingProbabilities.getOrDefault(getAnimalType(), 0.0);
    }

    public abstract Animal getChild();
}
