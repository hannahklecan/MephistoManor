
public class Room {

    private final String [] ROOM_TYPES = {"foyer","bedroom", "kitchen", "hallway", "cellar", "study"};

    private Item item1 = new Item();
    private Item item2 = new Item();
    private Item item = new Item();

    private Item [] itemsInRoom = {item1,item2};

    private Enemy monster = new Enemy();

    private String roomName;

    private boolean northExit;
    private boolean southExit;
    private boolean eastExit;
    private boolean westExit;
    private boolean status; //set exits or not

    //determines if room has monster
    private boolean isHaunted = false;

    private boolean hasItem = false;

    private boolean [] exits = new boolean[4];

    Room(){

        setRoomName();
        this.northExit = false;
        this.southExit = false;
        this.eastExit = false;
        this.westExit = false;

        this.exits[0] = northExit;
        this.exits[1] = southExit;
        this.exits[2] = eastExit;
        this.exits[3] = westExit;

        this.status =false;

        setMonster();
        setItem();
    }

    public void setMonster(){

        //int monsterChance = IR4.getRandomNumber(0,4);

        //if(monsterChance > 2){
            this.isHaunted = true;
        //}

        //sets monster object
        monster.setName();
        monster.setAttackHP();
        monster.setHealth();

    }

    public void setItem(){

        this.hasItem = true;

        item.setItemName("Healing Potion");
        item.setItemType(0);
        itemsInRoom[0] = item;

        //item2.setItemName("precious stone");
        //item2.setItemType(2);
        //itemsInRoom[1] = item2;

    }

    public Item getItem(){
        return item;
    }

    public void resetMonster(){
        this.isHaunted = false;
    }

    public void resetItemStatus(){
        this.hasItem = false;
    }

    public void setRoomName(){
        //randomly chooses a room type from the available strings
        //should this go in default constructor?
        this.roomName = ROOM_TYPES[IR4.getRandomNumber(0, ROOM_TYPES.length-1)];
    }

    public void setExits(){
        this.exits[0] = this.northExit;
        this.exits[1] = this.southExit;
        this.exits[2] = this.eastExit;
        this.exits[3] = this.westExit;
    }

    public void setNorthExit(boolean isExit){
        this.northExit = isExit;
    }
    public void setSouthExit(boolean isExit){
        this.southExit = isExit;
    }
    public void setEastExit(boolean isExit){
        this.eastExit = isExit;
    }
    public void setWestExit(boolean isExit){
        this.westExit = isExit;
    }
    public void setStatus(boolean isSet){
        this.status = isSet;
    }

    public Enemy getMonster(){
        return monster;
    }
    public Item [] getItems(){
        return itemsInRoom;
    }
    public String getRoomName(){
        return roomName;
    }

    public boolean getNorth(){
        return northExit;
    }
    public boolean getSouth(){
        return southExit;
    }
    public boolean getEast(){
        return eastExit;
    }
    public boolean getWest(){
        return westExit;
    }

    public boolean[] getExits(){
        return exits;
    }

    public boolean getStatus(){
        return status;
    }

    public boolean getIsHaunted(){
        return isHaunted;
    }

    public boolean getHasItem(){
        return hasItem;
    }




}