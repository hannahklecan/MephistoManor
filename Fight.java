public class Fight {


    public static final String[] fightActions = {"cast spell","use potion", "flee room", "display stats"};
    public static final String INVALID_COMMAND = "invalid";

    public static int MIN_ATTK = 5;
    public static int MAX_ATTK = 25;


    public static void executeFightCommand(Player player, GameMap map, Enemy monster){

        String playerEntry = getActionFromPlayer(player);

            if (playerEntry.equalsIgnoreCase(fightActions[0])) {
                //cast spell
                castSpell(player, monster, map);
            }
            else if (playerEntry.equalsIgnoreCase(fightActions[1])) {
                //use potion
                usePotion(player, monster, map);
            }
            else if (playerEntry.equalsIgnoreCase(fightActions[2])) {
                //move
                GameCommandControl.movePlayer(map);
            }
            else if (playerEntry.equalsIgnoreCase(fightActions[3])) {
                //display health stats
                displayStats(player,monster, map);
            }

    }

    public static void displayStats(Player player, Enemy monster, GameMap map){

        System.out.println("Enemy health: " + monster.getHealth());
        System.out.println("Your health: " + player.getHealth());

        if(monster.getHealth() > 0){
            executeFightCommand(player, map, monster);
        }
    }

    public static void castSpell(Player player, Enemy monster, GameMap map){

        Item playerSpell = new Spell();

        playerSpell = player.getSpell();
        int damage = IR4.getRandomNumber(10,30);

        System.out.println("You have cast the " + playerSpell.getItemName() + " spell, dealing " + damage + " damage to the " + monster.getName() + "!");
        monster.reduceHealth(damage);

        //System.out.println("????? ?? ?=??O)) ((03?=? ?? °¬° ??");
        System.out.println("/(°^°)/ --=.·`¸.·*´¨> <¨´*+.`·.=-- \\(OwO)\\"); //DrJava compatible

        int attack =IR4.getRandomNumber(MIN_ATTK,MAX_ATTK);

        System.out.println("The " + monster.getName() + " uses " + monster.getAttackName() + " dealing " + attack + " damage to you!");
        player.reduceHealth(attack);

        if(monster.getHealth() <= 0 ){

            player.setMonsters(1);
            player.setRemainingToExit(1);
            System.out.println("\nYou have defeated the " +monster.getName() +"!");
            map.getCurrentRoom().resetMonster();
        }
        else{
            executeFightCommand(player, map, monster);
        }

    }


    public static String displayFightCommands(){

        String display = "";

        //System.out.println("\nWhat will you do, "+ player.getName() +"?");
        for(int i = 0; i < fightActions.length; i++){
            //System.out.println("- " + fightActions[i]);
            display = display + "- " + fightActions[i] + "\n";
        }

        return display;
    }

    public static String getActionFromPlayer(Player player){

        String playerEntry = IR4.getString("\nWhat will you do, "+ player.getName() +"?\n" + displayFightCommands());
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


        for(int i = 0; i < fightActions.length; i++) {

            //for two word commands, split at the space to compare the first or second word to the player entry
            String [] twoWordCommand = fightActions[i].split(" ",2);

            //simply checks for matching action string
            if (playerEntry.equalsIgnoreCase(fightActions[i])){
                return fightActions[i];
            }
            //if the string entered was short, check for possible matching action string
            else if(playerEntry.length() <= 4){
                if(playerEntry.startsWith(fightActions[i].substring(0,1))) {
                    return fightActions[i];
                }
            }
            //if the user enters a string starting with the command it is valid & converted
            else if(playerEntry.startsWith(fightActions[i])){
                return fightActions[i];
            }

            //checks for two word commands that have may have been shorted to one word
            if(twoWordCommand.length > 1){
                if(playerEntry.startsWith(twoWordCommand[0])|| playerEntry.startsWith(twoWordCommand[1])){
                    return fightActions[i];
                }
                //checks for shorted version of the second word in two-command
                if(playerEntry.length() <= 8){
                    if(playerEntry.startsWith(twoWordCommand[1].substring(0,1))) {
                        return fightActions[i];
                    }
                }
            }

            //user can enter number of command based on position in list
            if(playerEntry.equals(Integer.toString(i+1))){
                return fightActions[i];
            }


        }

        //if none of the cases match, the entered command is invalid
        return INVALID_COMMAND;
    }


    public static void usePotion(Player player, Enemy monster, GameMap map){

        if(player.getHasPotion()){
            player.usePotion();
            System.out.println("You have used a healing potion and increased your hp to " + player.getHealth() + "!");
        }
        else{
            System.out.println("You do not have any potions!");
            System.out.println("Defeat enemies to find potions.");
        }

        if(monster.getHealth() > 0){
            executeFightCommand(player, map, monster);
        }

    }





}
