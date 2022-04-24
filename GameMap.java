public class GameMap {

    private static boolean DEBUG_EXITS = false;

    //only an even # of rooms will be allowed
    private static int MAX_ROOMS = 8;
    private static final int [] directionNumbers = {1,2,3,4};
    private static final String [] directions = {"n","s","e","w"};


    private Room [][] gameMap;
    private Room currentRoom;
    private int r;
    private int c;

    GameMap(){
        this.r = 1;
        this.c = 1;
        this.gameMap = createMap();
        this.currentRoom = gameMap[r][c]; //initial room is in center/front of room cluster
    }

    public Room [][] getGameMap(){
        return gameMap;
    }
    public Room getCurrentRoom(){
        return currentRoom;
    }
    public int getRow(){return r;}
    public int getCol(){return c;}

    public void displayMap(){

        System.out.println("You are at the X");

        for(int x = 0; x < MAX_ROOMS;x++){
            System.out.print("---");
        }
        System.out.println("");

        for(int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {

                if(gameMap[i][j] == currentRoom) {
                    System.out.print("| X | ");
                }
                else{
                    System.out.print("|   | ");
                }
            }

            System.out.println("");
            for(int x = 0; x < MAX_ROOMS;x++){
                System.out.print("---");
            }

            System.out.println();
        }

    }

    public Room [][] createMap (){

        Room [][] gameMap = new Room[2][MAX_ROOMS/2];

        for(int i = 0; i < gameMap.length; i++){
            for(int j = 0; j < gameMap[i].length; j++){
                gameMap[i][j] = new Room();
                //creates an evenly spaced grid representing the location of each room
                //00 01 02
                //10 11 12
            }
        }

        determineExitPoints(gameMap);

        return gameMap;
    }

    //TODO: too many try/catches? possibly try to create a +1 around larger array to buffer out of bounds region if this is not best practice
    public void determineExitPoints(Room [][] gameMap){

        for(int i = 0; i <gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {

                try {
                    if (!gameMap[i + 1][j].getStatus()) {
                        gameMap[i][j].setSouthExit(true);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    gameMap[i][j].setSouthExit(false);
                }

                try {
                    if (!gameMap[i - 1][j].getStatus()) {
                        gameMap[i][j].setNorthExit(true);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    gameMap[i][j].setNorthExit(false);
                }

                try {
                    if (!gameMap[i][j+1].getStatus()) {
                        gameMap[i][j].setEastExit(true);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    gameMap[i][j].setEastExit(false);
                }

                try {
                    if (!gameMap[i][j-1].getStatus()) {
                        gameMap[i][j].setWestExit(true);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    gameMap[i][j].setWestExit(false);
                }


                //once each direction has been set, combine all exits into the Room objects boolean exits array
                gameMap[i][j].setExits();
            }
        }


        //checking if exits were set properly using the above method
        if(DEBUG_EXITS) {
            for (int i = 0; i < gameMap.length; i++) {
                for (int j = 0; j < gameMap[i].length; j++) {

                    System.out.println(i + " " + j);
                    System.out.println("n: " + gameMap[i][j].getNorth());
                    System.out.println("s: " + gameMap[i][j].getSouth());
                    System.out.println("e: " + gameMap[i][j].getEast());
                    System.out.println("w: " + gameMap[i][j].getWest());
                    System.out.println();
                }
                System.out.println();

            }
        }


    }

    public void displayExits(){

        System.out.println("Directions to move:");

        for (int i = 0; i <directions.length; i++){
            System.out.print("- "+ (i+1) + ".)" + directions[i]);
            if(this.currentRoom.getExits()[i]){
                System.out.print(" - exit available\n");
            }
            else{
                System.out.print(" - no exit\n");
            }
        }

    }

    public int getValidExit(){

        String direction = IR4.getString("Which direction do you want to go?");
        direction = direction.trim();
        direction = direction.toLowerCase();

        while (isInvalidEntry(direction, this.currentRoom) || isInvalidExit(direction, this.currentRoom)){
            direction = IR4.getString("Which direction do you want to go?");
            direction = direction.trim();
            direction = direction.toLowerCase();
        }

        return convertDirectionString(direction);
    }

    public boolean isInvalidEntry(String direction, Room currentRoom){

        for(int i=0; i<directions.length;i++) {
            if (direction.equals(directions[i])) {
                return false;
            }
            else if (direction.equals(Integer.toString(i+1))){
                return false;
            }
            else if(direction.startsWith(directions[i])){
                return false;
            }
        }

        if (direction.equals(Integer.toString(0))){
            return false;
        }

        System.err.println("Invalid entry, try again or type 0 to chose another option");
        return true;
    }

    public boolean isInvalidExit(String direction, Room currentRoom){

        int number = convertDirectionString(direction);

        if(number > 0 ) {
            if (currentRoom.getExits()[number-1]) {
                return false;
            }
        }
        if(number == 0){
            return false;
        }

        System.err.println("Invalid direction, try again or type 0 to chose another option");
        return true;
    }

    public int convertDirectionString(String direction){

        int num =-1;

        for (int i=0; i < directions.length;i++){
            if(direction.startsWith(directions[i]) || direction.equals(Integer.toString(i+1))){
                num = i+1;
            }
        }

        if(direction.equals(Integer.toString(0))){
            num = 0;
        }

        return num;
    }

    public void updateCurrentRoom(int direction) {

        switch (direction) {

            //north
            case 1:
                this.currentRoom = gameMap[r - 1][c];
                this.r = r-1;
                break;
            //south
            case 2:
                this.currentRoom = gameMap[r + 1][c];
                this.r = r + 1;
                break;
            //east
            case 3:
                this.currentRoom = gameMap[r][c + 1];
                this.c = c + 1;
                break;
            //west
            case 4:
                this.currentRoom = gameMap[r][c - 1];
                this.c = c - 1;
                break;

        }


    }

}
