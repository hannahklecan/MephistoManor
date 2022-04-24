import java.util.ArrayList;

public class GameCommandControl {

    //add available action cmds for move + direction
    public static final String [] actions = { "check room", "move", "display game stats", "use potion","quit"};

    public static final String INVALID_COMMAND = "invalid";

                                                 //n s e w
    public static final int [] directionNumbers = {1,2,3,4};
    public static final String [] direction = {"n","s","e","w"};


    /**
     * Gets a validated command from the user to be returned to the main game loop.
     * @return playerEntry the validated command entered by the user.
     */
    public static String getCommandFromPlayer(){

        displayCommands();

        String playerEntry = IR4.getString("What would you like to do?");
        playerEntry = playerEntry.toLowerCase();
        playerEntry = playerEntry.trim();
        playerEntry = getValidCommand(playerEntry);

        //if the command is invalid, "invalid" is returned from function
        while(playerEntry.equals(INVALID_COMMAND)){
            System.err.println("Invalid command entered.");
            playerEntry = IR4.getString("What would you like to do?");
            playerEntry = playerEntry.toLowerCase();
            playerEntry = playerEntry.trim();
            playerEntry = getValidCommand(playerEntry);
        }

        return playerEntry;
    }


    public static String getValidCommand(String playerEntry){


        for(int i = 0; i < actions.length; i++) {

            //for two word commands, split at the space to compare the first or second word to the player entry
            String [] twoWordCommand = actions[i].split(" ",2);

            //simply checks for matching action string
            if (playerEntry.equalsIgnoreCase(actions[i])){
                return actions[i];
            }
            //if the string entered was short, check for possible matching action string
            else if(playerEntry.length() <= 4){
                if(playerEntry.startsWith(actions[i].substring(0,1))) {
                    return actions[i];
                }
            }
            //if the user enters a string starting with the command it is valid & converted
            else if(playerEntry.startsWith(actions[i])){
                return actions[i];
            }

            //checks for two word commands that have may have been shorted to one word
            if(twoWordCommand.length > 1){
                if(playerEntry.startsWith(twoWordCommand[0])|| playerEntry.startsWith(twoWordCommand[1])){
                    return actions[i];
                }
                //checks for shorted version of the second word in two-command
                if(playerEntry.length() <= 8){
                    if(playerEntry.startsWith(twoWordCommand[1].substring(0,1))) {
                        return actions[i];
                    }
                }
            }

            //user can enter number of command based on position in list
            if(playerEntry.equals(Integer.toString(i+1))){
                return actions[i];
            }


        }

        //if none of the cases match, the entered command is invalid
        return INVALID_COMMAND;
    }


    public static String executeCommand(String playerEntry, Player player, GameMap map){

        if(playerEntry.equalsIgnoreCase(actions[0])){
            //checkRoom
            checkRoom(map, player);
        }
        else if(playerEntry.equalsIgnoreCase(actions[1])){
            //move
            movePlayer(map);
        }
        else if(playerEntry.equalsIgnoreCase(actions[2])){
            //display inventory
            displayInventory(player);
        }

        else if(playerEntry.equalsIgnoreCase(actions[3])){
            //use potion
            usePotion(player);
        }

        else if(playerEntry.equalsIgnoreCase(actions[4])){
            //TODO: refactor this method...
            if(!quitGame(player)){
                return "no";
            }
            //quit
        }

        return playerEntry;
    }

    //TODO: Create an inventory sort method based on item type to display inventory items as shown below
    //ex: Spells:
    //    -exorcism spell..
    //    Potions:
    //    -mana............
    //    Treasures:
    //    -amulet..........
    /**
     * Displays the player's inventory. Calls methods from the Player class to display the inventory items.
     * @param player the Player object representing the player
     */
    public static void displayInventory(Player player){

        ArrayList<Item> inventory = new ArrayList<Item>();
        inventory = player.getInventory();
        int potions = 0;

        System.out.println("***************************");
        System.out.println("*" + player.getName() + "'s Game Stats ");
        System.out.println("***************************");

        System.out.println("*Health Points: " + player.getHealth());

        System.out.println("\n*Inventory:");
        for(int i = 0; i< inventory.size(); i++){

            if(inventory.get(i).getItemType()==0){
                potions++;
                if(i==1) {
                    System.out.print("-" + inventory.get(i).getItemName());
                    System.out.print(":" + inventory.get(i).getItemDescription());
                }
            }
            else{
                System.out.print("-" + inventory.get(i).getItemName());
                System.out.print(":" + inventory.get(i).getItemDescription() + "\n");
            }
        }

        if(potions >1){
            System.out.print(" (x " + potions + ")\n\n");
        }
        else{
            System.out.println("\n");
        }

        System.out.println("*Monsters Defeated: " + player.getMonstersDefeated());
        System.out.println("*Defeat " + player.getRemainingToExit() +" more monster(s) to exit the manor alive.");
        System.out.println("***************************");
        //System.out.println();

    }

    /**
     * Displays the available commands to the user. Might need to be dynamically allocated
     */
    public static void displayCommands(){

        System.out.println("\nThese are the commands available: ");
        for(int i = 0; i < actions.length; i++){
            System.out.println("- " + actions[i]);
        }
    }

    public static boolean quitGame(Player player){

        String msg = "";

        if(player.getCanExit()){
            msg = "If you quit your progress will be lost! Are you sure? Y/N: ";
        }
        else{
            msg = "Are you sure you wish to quit the game? Your soul will leave this earthly plane if you quit now... Y/N:";
        }

        if(IR4.getYorN( msg)){
            return true;
        }
        return false;
    }

    public static void movePlayer(GameMap map){

        int direction = 0;

        map.displayMap();
        map.displayExits();

        direction = map.getValidExit();

        map.updateCurrentRoom(direction);

        map.displayMap();

    }

    public static void checkRoom(GameMap map, Player player){

        Enemy monster = new Enemy();
        monster = map.getCurrentRoom().getMonster();

        if(map.getCurrentRoom().getIsHaunted()){

            System.out.println("You have encountered a " + monster.getName() + "," + monster.getEnemyDescription());

            //System.out.println(monster.getName() + ":" + monster.getEnemyDescription());
            System.out.println("Enemy health: " + monster.getHealth());
            System.out.println("Your health: " + player.getHealth());

            //Fight.getActionFromPlayer(player);
            Fight.executeFightCommand(player, map, monster);

        }
        else if(map.getCurrentRoom().getHasItem()){

            Item item = map.getCurrentRoom().getItem();
            System.out.println("You have found a " + item.getItemName() + "!");
            player.addToInventory(item);

            System.out.println("              :~:  ");
            System.out.println("              | |  ");
            System.out.println("             .' `. ");
            System.out.println("            '  +  ;");
            System.out.println("            |     |");
            System.out.println("            `.._..'");

            map.getCurrentRoom().resetItemStatus();

        }
        else{
            System.out.println("There is nothing else in this room!");
        }


    }


    public static void usePotion(Player player){

        if(player.getHasPotion()){
            player.usePotion();
            System.out.println("You have used a healing potion and increased your hp to " + player.getHealth() + "!");
        }
        else{
            System.out.println("You do not have any potions!");
            System.out.println("Explore some rooms to find potions.");
        }

    }




}
