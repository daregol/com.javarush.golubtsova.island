package entity.creature.plant;

import entity.creature.Creature;

public class Plant extends Creature {
     int quantity;

     public Plant() {
         this.quantity = 50;
     }

     public void setQuantity(int _quantity) {
         quantity = _quantity;
     }

     public int getQuantity() {
        return quantity;
     }

}