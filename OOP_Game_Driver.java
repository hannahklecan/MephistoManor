
public class OOP_Game_Driver{

    public static final int ROOMS_TO_CLEAR = 2;
    public static final int MAX_DAMAGE = 20;
    public static final int MIN_DAMAGE = 1;
    //only an even # of rooms will be allowed
    public static int MAX_ROOMS = 6;
    
    public static int MIN_TO_EXIT = 3;

    public static final String [] spellText = {"Exorcism: expulsion spell effective against demonic creatures.",
            "Chaos Sigil: spell that seals chaotic evil energies.",
            "Spiritual Purge: removes life force from spiritual entities.",
            "Servitor: summons psychological entity that deals damage to enemy forces."};
    public static final String [] shortSpell = {"e", "cs", "sp", "s"};

    //n s e w
    public static final int [] directionNumbers = {1,2,3,4};
    public static final String [] direction = {"n","s","e","w"};

    public static void main (String [] args){

        Player player = new Player();

        displayWelcome();

        player.setName(IR4.getString("Fellow occultist, what do you wish to be known as?"));

        System.out.println("Mephisto Manor is not a place for the faint of heart, only those most dedicated to unearthing the mysteries of the occult should enter.");
        
        System.err.println("WARNING: You must defeat at least " + MIN_TO_EXIT + " enemies to exit the manor ALIVE.");
        
        System.out.println("If you are certain you wish to test your abilities, choose a spell to defend yourself against the sinister forces that await inside: ");

        Spell playerSpell = new Spell();

        setFirstSpell(player, playerSpell);
        player.addToInventory(playerSpell);

        //player.displayInventory();

        //if spell name is still not set, player entered "exit" so program should skip the main game loop
        if(!playerSpell.getItemName().equals("none")){

            startGame(player);
        }



        //eventually game will yield some sort of results, score based on defeated monsters etc.
        //displayGameResults();


    }//end of main

    public static void displayWelcome(){

        System.out.println("Good evening, and welcome to...");

        System.err.println("         ,---.  ,---.  .-. .-.,-.   .---.  _______  .---.             .--.  .-. .-. .---.  ,---.   ");
        System.err.println("|\\    /| | .-'  | .-.\\ | | | ||(|  ( .-._)|__   __|/ .-. )  |\\    /| / /\\ \\ |  \\| |/ .-. ) | .-.\\  ");
        System.err.println("|(\\  / | | `-.  | |-' )| `-' |(_) (_) \\     )| |   | | |(_) |(\\  / |/ /__\\ \\|   | || | |(_)| `-'/  ");
        System.err.println("(_)\\/  | | .-'  | |--' | .-. || | _  \\ \\   (_) |   | | | |  (_)\\/  ||  __  || |\\  || | | | |   (   ");
        System.err.println("| \\  / | |  `--.| |    | | |)|| |( `-'  )    | |   \\ `-' /  | \\  / || |  |)|| | |)|\\ `-' / | |\\ \\  ");
        System.err.println("| |\\/| | /( __.'/(     /(  (_)`-' `----'     `-'    )---'   | |/| ||_|  (_)/(  (_) )---'  |_| \\)\\ ");
        System.err.println("'-'  '-'(__)   (__)   (__)                         (_)      '-'  '-'       (__)    (_)         (__)");
    }
 public static void displayQuit(Player player){

        if(!player.getCanExit()) {
            System.err.println("      +-+-+-+ +-+-+-+-+-+");
            System.err.println("      |Y|O|U| |D|I|E|D|!|");
            System.err.println("      +-+-+-+ +-+-+-+-+-+");

            System.out.println("         _,.-------.,_          ");
            System.out.println("     ,;~'             '~;,      ");
            System.out.println("   ,;                     ;,    ");
            System.out.println("  ;                         ;   ");
            System.out.println(" ,'                         ',  ");
            System.out.println(",;                           ;, ");
            System.out.println("; ;      .           .      ; ; ");
            System.out.println("| ;   ______       ______   ; | ");
            System.out.println("|  `/~\"     ~\" . \"~     \"~\\'  | ");

            //glowing red eyes
            System.out.print("|  ~  ");
            System.err.print(",-~~~^~,");
            System.out.print(" | ");
            System.err.print(",~^~~~-,");
            System.out.print("  ~  | \n");
            System.out.print(" |   ");
            System.err.print("|........}");
            System.out.print(":");
            System.err.print("{........|");
            System.out.print("   |  \n");
            System.out.print(" |   ");
            System.err.print("l......./");
            System.out.print(" | ");
            System.err.print("\\.......! ");
            System.out.print("  |  \n");
            System.out.print(" .~  ");
            System.err.print("(__,.--\"");
            System.out.print(" .^. ");
            System.err.print("\"--.,__)");
            System.out.print("  ~.  \n");

            System.out.println(" |     ---;' / | \\ `;---     |  ");
            System.out.println("  \\__.       \\/^\\/       .__/   ");
            System.out.println("   V| \\                 / |V    ");
            System.out.println("    | |T~\\___!___!___/~T| |     ");
            System.out.print("    | |`");
            System.err.print("IIII_I_I_I_IIII");
            System.out.print("'| |     \n");
            System.out.print("    |  \\,");
            System.err.print("III I I I III");
            System.out.print(",/  |     \n");
            System.out.println("     \\   `~~~~~~~~~~'    /      ");
            System.out.println("       \\   .       .   /        ");
            System.out.println("         \\.    ^    ./          ");
            System.out.println("           ^~~~^~~~^            ");


        }
        else{

            System.out.println(player.getName()+ ", you truly are a master of the occult. \nYou have defeated the foulest of creatures within this manor \nand have unlocked the skills to transcend this earthly plane!");

            System.out.println("\nMonsters Defeated: " + player.getMonstersDefeated());
            System.out.println("Remaining Health: " + player.getHealth());
            System.err.println("FINAL SCORE: " + player.getMonstersDefeated() * player.getHealth());

            System.out.println("                     ,____");
            System.out.println("                     |---.\\");
            System.out.println("             ___     |    `");
            System.out.println("            / .-\\  ./=)");
            System.out.println("           |  | |_/\\/|");
            System.out.println("           ;  |-;| /_|");
            System.out.println("          / \\_| |/ \\ |");
            System.out.println("         /      \\/\\( |");
            System.out.println("         |   /  |` ) |");
            System.out.println("         /   \\ _/    |");
            System.out.println("        /--._/  \\    |");
            System.out.println("        `/|)    |    /");
            System.out.println("          /     |   |");
            System.out.println("        .'      |   |");
            System.out.println("       /         \\  |");
            System.out.println("      (_.-.__.__./  |");

            System.out.println();

            System.err.println("     +-+-+-+-+-+-+-+");
            System.err.println("     |G|O|O|D|B|Y|E|");
            System.err.println("     +-+-+-+-+-+-+-+");

        }
 }
        


    public static void startGame(Player player){

        //creates new map for each game
        GameMap map = new GameMap();

        System.out.println("\nNow that you are equipped with a spell, you may enter the manor. \nYou cross the decrepit threshold and a black cat hisses as it brushes past your legs.");
        System.out.println("The front door locks behind you with an ominous click.");
        System.out.println("Beware, there could be danger at every turn in this house...\n");

        //GameCommandControl.displayCommands();
        map.displayMap();
        String playerEntry = GameCommandControl.getCommandFromPlayer();
        playerEntry = GameCommandControl.executeCommand(playerEntry, player, map);

        while(!playerEntry.equals("quit")) {

            playerEntry = GameCommandControl.getCommandFromPlayer();
            playerEntry = GameCommandControl.executeCommand(playerEntry, player, map);
        }


        displayQuit(player);

    }


    public static void setFirstSpell (Player player, Spell playerSpell){

        //displays available spells
        for(int i = 0; i < spellText.length;i++){
            System.out.println("- " + (i+1) + ".)" +spellText[i]);
        }

        setValidSpell(player, playerSpell);

        //if no spell was set, player entered "exit"
        if(playerSpell.getItemName().equalsIgnoreCase("none")){
            System.err.println("So you've chosen the easy way out, begone from this place!");
        }

    }

    //TODO: code for variations of short hand spells (exo, serv, chaos, etc) and potential typos
    public static void setValidSpell (Player player, Spell playerSpell){

        String sp = IR4.getString("- type the name (or #) of your spell OR type quit to flee this place and never return:");
        sp = sp.trim();

        for(int i = 0; i < spellText.length; i++){

            String[] spellStrings = spellText[i].split(":", 2);
            //System.out.println(spellStrings[0]);

            if(sp.equalsIgnoreCase(spellStrings[0]) || sp.equalsIgnoreCase(shortSpell[i]) || sp.equals(Integer.toString(i+1))){
                playerSpell.setItemName(spellStrings[0]);
                playerSpell.setItemDescription(spellStrings[1]);
                System.out.println("You have chosen the " + playerSpell.getItemName() + " spell, a wise choice " + player.getName() + ".");
            }
            //checks at the end of the loop only
            if((playerSpell.getItemName().equals("none")) && i == spellText.length-1 && !sp.equalsIgnoreCase("quit")){
                sp = IR4.getString("invalid spell entered, re-type spell or type quit to run away: ");
                sp = sp.trim();
                i = -1; //reset loop
            }
        }

    }//end set valid spell


}//end of driver class


