import java.util.ArrayList;

public class Player {

    private int MAX_HP = 666;
    private int POTION_HP = 10;

    private String name;
    private int health;
    private ArrayList <Item> inventory = new ArrayList<Item>();
    private int mostersDefeated;
    private int remainingToExit = 3;

    private boolean canExit = false;
    private boolean hasPotion = false;

    Player(){
        name = "none";
        health = 666;
        mostersDefeated = 0;
    }
    Player(String name, int health){
        this.name = name;
        this.health = health;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setHealth(int health){
        this.health = health;
    }

    public void setMonsters(int increment){
        mostersDefeated = mostersDefeated + increment;
    }

    public String getName(){
        return name;
    }
    public int getHealth(){
        return health;
    }
    public int getMonstersDefeated(){
        return mostersDefeated;
    }
    public ArrayList<Item>getInventory(){
        return inventory;
    }

    public void addToInventory(Item item){
        this.inventory.add(item);
    }

    public void displayInventory(){

        for(int i = 0; i< inventory.size(); i++){
            System.out.print("-" + inventory.get(i).getItemName());
            System.out.print(":" + inventory.get(i).getItemDescription() + "\n");
        }
    }

    public void displaySpells(){

        System.out.println();
        System.out.println(name + "'s Spells:");

        for(int i = 0; i< inventory.size(); i++){
            if(inventory.get(i).getItemType() == 1) {
                System.out.print("-" + inventory.get(i).getItemName());
                System.out.print(":" + inventory.get(i).getItemDescription() + "\n");
            }
        }

        System.out.println();

    }

    public Item getSpell(){
        //currently only one item in inventory that is a spell
        return inventory.get(0);
    }


    public void reduceHealth(int hitPoints){

        this.health = this.health - hitPoints;
    }

    public void usePotion(){
        this.health = this.health + POTION_HP;
        if(this.health > MAX_HP){
            this.health = MAX_HP;
        }
        hasPotion = false;
        inventory.remove(1);
    }


    public boolean getCanExit(){
        if(mostersDefeated >= 3){
            this.canExit = true;
        }
        return canExit;
    }

    public boolean getHasPotion(){
        for(int i = 0; i< inventory.size(); i++){
            if(inventory.get(i).getItemType()==0){
                this.hasPotion = true;
            }
        }
        return hasPotion;
    }

    public void setRemainingToExit(int decrement){

        this.remainingToExit = remainingToExit - decrement;
        if(remainingToExit <0){
            this.remainingToExit = 0;
        }
    }

    public int getRemainingToExit(){

        return remainingToExit;
    }
}