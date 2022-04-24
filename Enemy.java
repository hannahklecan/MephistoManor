
public class Enemy {

    private static int MAX_HEALTH = 80;
    private static int MIN_HEALTH = 20;
    private static int MAX_ATTACK = 20;
    private static int MIN_ATTACK = 1;

    private final String [] ENEMY_TYPES = {"Demon", "Ghost", "Vampire", "Mutant Rat"};
    private final String [] ENEMY_TEXT = {" an evil tormenting spirit, capable of possessing weakened humans.",
            " the vengeful, resurrected spirit of a dead person.",
            " an undead creature sustained by the blood of humans.",
            " a twisted and grotesque creature resembling a rat."};

    private final String[] ENEMY_ATTACK = {"inferno blast","soul suck","draining bite","poisonous bite"};

    private String name;
    private String enemyDescription;
    private int health;
    private int attackHP;
    private String attackName;

    Enemy(){
        name = "mononoke";
        enemyDescription = "none";
        health = 100;
        attackHP = 0;
        attackName = "none";
    }

    Enemy(String name, String enemyDescription, int health, int attackHp, String attackName){
        this.name = name;
        this.enemyDescription = enemyDescription;
        this.health = health;
        this.attackHP = attackHP;
        this.attackName = attackName;
    }


    //set the name, description and attack type for this enemy based on the randomly chosen index
    //set at the same time because parallel arrays are used for each of these strings
    public void setName(){
        int enemyIndex = IR4.getRandomNumber(0,ENEMY_TYPES.length-1);
        this.name = ENEMY_TYPES[enemyIndex];
        setEnemyDescription(enemyIndex);
        setAttackName(enemyIndex);
        setAttackHP();
    }
    public void setEnemyDescription(int enemyIndex){
        this.enemyDescription = ENEMY_TEXT[enemyIndex];
    }
    public void setAttackName(int enemyIndex){
        this.attackName = ENEMY_ATTACK[enemyIndex];
    }


    public void setHealth(){
        this.health = IR4.getRandomNumber(MAX_HEALTH, MIN_HEALTH);
    }
    public void setAttackHP(){
        this.attackHP = IR4.getRandomNumber(MAX_ATTACK, MIN_ATTACK);
    }

    public void reduceHealth(int hitPoints){

        this.health = this.health - hitPoints;
    }


    public String getName(){
        return name;
    }
    public String getEnemyDescription(){
        return enemyDescription;
    }
    public int getHealth(){
        return health;
    }
    public int getAttackHP(){
        return attackHP;
    }
    public String getAttackName(){
        return attackName;
    }

}