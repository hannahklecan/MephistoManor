
public class Item {

    public final static int HEAL = 0;
    public final static int WEAPON = 1;
    public final static int TREASURE = 2;

    private String itemName;
    private String itemDescription;
    private int type;
    private int damage;

    Item(){
        itemName = "none";
        itemDescription = "n/a";
        type = -1;
        damage = 0;
    }

    Item(String itemName, String itemDescription, int type){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.type = type;
        setDamage(0,100);
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setItemDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }
    public void setItemType(int type){
        this.type = type;
    }


    public String getItemName(){
        return itemName;
    }
    public String getItemDescription(){
        if(type == HEAL){
            return " a magic potion that heals up to 10hp.";
        }

        return itemDescription;
    }
    public int getItemType(){
        return type;
    }


    public void setDamage(int min, int max){
        this.damage = getRandomNumber(min, max);
    }

    public int getDamage(){
        return damage;
    }

    public int getRandomNumber (int low, int high){
        return (int)(Math.random() * (high - low)) + low;
    }


}