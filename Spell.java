public class Spell extends Item {

    private int damage;

    Spell(){
        this.damage = 0;
        setDamage(0,100);
        setItemType(1);
    }
    Spell(int damage){
        this.damage = damage;
        setDamage(0,100);
        setItemType(1);
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