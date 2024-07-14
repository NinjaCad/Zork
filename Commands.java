//TODO: Add the custom commands you made in Checkpoint 2 and your Execute commands if needed

import java.util.*;

/*
 * 
 * All green comments are tasks for these checkpoints
 * Note that this code will be used for both checkpoints 4 and 5.
 * Be sure to focus only on the checkpoint you are currently assigned to work on.
 * 
 */

/**
 * Creates the class Commands, 
 * There will only be one instance used by the game
 * therefore all of its methods are static to prevent multiple
 */

public class Commands {

/*
 * TODO: checkpoint 4 Tasks
 * 
 * add class level variables
 * Declare a two dimensional array called "map" it should be of type "Room"
 * Declare two variables of type int called "row" and "column"
 * Declare a Room object called currentRoom
 * 
 * note that these variables should be "static" because there should only be one world
 */

  static Room[][] map;
  static int row;
  static int column;
  static Room currentRoom;
  static boolean done = false;
  static boolean code = true;
  static boolean wood = true;
  static boolean door = true;

  static ArrayList<InventoryItem> inventory;

/**
 * runG
 
 name method
 * method responsible for running a console version of the game
 * this method returns nothing and has no parameters
 * it contains the while loop that is used to run the game
 */

  public static void runGame() {
    
/*
 * TODO: checkpoint 4 Tasks
 * 
 * initialize your map 
 * 
 * then construct each room of your world, 
 * be sure they match the correct locations of you map and have the appropriate 
 * passages set to true and false
 * Any spot in your map that is not used should be built with the default constructor
 * 
 * HINT: The upper left is row 0 and column 0
 */
    inventory = new ArrayList<InventoryItem>();
    InventoryItem hat = new InventoryItem("hat", "Small red basball cap", 1.0);
    Key key1 = new Key("lockpick", "small lockpick that can open the cell door", 3.0, "cell", "south");
    Key key2 = new Key("red key", "key that can open up the bedroom", 3.0, "living room", "west");
    Key  key3 = new Key("code", "code that can open the front door with the numbers 1234. You can use the code by writing \"input 1234\" or \"use 1234\"", 1.0, "living room", "south");
    Key  key5 = new Key("crowbar", "crowbar that can take the wood off the front door.", 1.0, "living room", "south");
    Key  key4 = new Key("keycard", "keycard that can open a secret room", 0.0, "credits", "east");
    
    map = new Room[4][3];
    map[0][0] = new Room(false, false, false, false, "Cell", "You are in a dark dusty room. There is a \"hat\" and \"lockpick\" on the floor. There is a locked door to the south. Type \"take lockpick\" to grab the lockpick then type \"use lockpick\" to open the door.", hat);
    map[0][0].addObject(key1);
    map[0][1] = new Room(false, true, true, false, "Dining Room", "You are in a dining room. There is a door to the east and south.");
    map[0][2] = new Room(false, false, false, true, "Kitchen", "You are in a kitchen. There is a door to the west and a \"red key\" in the corner.", key2);
    map[1][0] = new Room(true, true, true, false, "Hallway", "You are in a long hallway. There is a door to the north, south, and east.");
    map[1][1] = new Room(true, false, false, true, "Living Room", "You are in a living room. There is a door in all directions. The south door needs a code to open it and is boarded with planks and the east door is locked and needs a key");
    map[1][2] = new Room(false, true, false, true, "Bedroom", "You are in a bedroom. There is a door to the south and west and there is a \"code\" on the bed that says 1234. You can use the code by writing \"input 1234\" or \"use 1234\"", key3);
    map[2][0] = new Room(true, true, false, false, "Guard Room", "You are in the guard room. There is a door to the north and south");
    map[2][1] = new Room(true, true, false, false, "Outside", "YOU WIN! You made it outside and can now touch grass. The front door is in the north and the credits are south.");
    map[2][2] = new Room(true, false, false, false, "Bathroom", "You are in a bathroom. There is a door to the north and there is a \"crowbar\" on the floor.", key5);
    map[3][0] = new Room(true, false, true, false, "Secret Room", "You are in a secret room. There is a door to the north and a \"keycard\" on the ground that may or may not open a door when used in the credits.", key4);
    map[3][1] = new Room(true, false, false, false, "Credits", "Made by Caedmon Julian");
    map[3][2] = new Room(false, false, false, true, "Developer Room", "lol");

/*
 * TODO: checkpoint 4 Tasks
 * 
 * initialize the values for your variables "row" and "column"
 * to represent the starting location of your game, 
 * then set the currentRoom to the starting location
 * 
 */

    row = 0;
    column = 0;
    currentRoom = map[row][column];

    String userInput = "";
    String command = "";
    String item = "";
    Scanner s = new Scanner(System.in);

//TODO: When you have made your game work in the console
//change done to true to play your game in the JFrame		
    done = false;
    String output = "";
    if(!done){
      output = gameIntro();
      System.out.println(output);
    }

    while(!done){
      System.out.print("\n\n>>");      
      userInput = s.nextLine().toLowerCase();
      command = getFirstWord(userInput);
      item = getRestOfSentence(userInput);
      output = executeCommand(command, item);

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the currentRoom to the new location on the map
 * Hint: there is a new method for this, go to the bottom and make it
 * using the currentRoom object.
 */

      updateRoom();

      System.out.println(output +"\n");

      if (wood == false && code == false && door == true) {
        door = false;
        currentRoom.setCanGoSouth(true);
        System.out.println("You have opened the front door");
      }

      if(command.equals("quit"))
        done = true;
      }
      s.close();
  }

/**
 * gameIntro method 
 * used to set the scene to the opening of your game 
 * @return: a String that is the opening description to your game, 
 *          be sure to note the help option 
 *          (i.e. "type help for a list of commands").  
 */

//TODO: Update this to your Intro (copy and paste it here)	
  public static String gameIntro(){
    return "You have just been kidnapped and need to escape the house! Type \"look\" to look around."
        + "\n (Type \"help\" to see a list of commands)";
  }

/**
 * getFirstWord method
 * pulls the first word out of any phrase (1 or more words)
 * @param a string that contains the commands from the user
 * @return a string that is only the first word, 
 *         returns a blank string "" if nothing entered, 
 *         do not use print or println
 */

  public static String getFirstWord(String input){
    int spaceLocation = input.indexOf(" ");
    if(spaceLocation > 0)
      return input.substring(0,spaceLocation);
    if(input.length() > 0)
      return input;
    return "";
  }

/**
 * getRestOfSentence 
 * pulls everything after the first word and the space behind it
 * @param a string that contains the commands from the user
 * @return a string that returns everything after the first word 
 *         and the space behind it, returns a blank string "" if 1 word entry 
 *         or nothing entered. 
 *         Do not use print or println
 */

  public static String getRestOfSentence(String input){
    int spaceLocation = input.indexOf(" ");
    if(spaceLocation >= 0)
      return input.substring(spaceLocation+1);
    return "";
  }	


/**
 * north method
 * Method to state that you moved north, you will add movement functionality
 * in checkpoint 4
 * @return a string that states you moved North, do not use print or println	
 */

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "north" method to allow the player to move North only if possible
 * Be sure to both move the player north and state that the player moved North
 * If you cannot move North, then state that you cannot move North
 */

  public static String north(){
    if (currentRoom.getCanGoNorth()) {
      row--;
      return "You moved North.";
    } else {
      return "You cannot move North";
    }
  }

/**
 * south method
 * Method to state that you moved south, you will add movement functionality
 * in checkpoint 4
 * @return a string that states you moved South, do not use print or println
 */		

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "south" method to allow the player to move South only if possible
 * Be sure to move the player South and state that the player moved South
 * If you cannot move South, then state that you cannot move South
 */

  public static String south(){
    if (currentRoom.getCanGoSouth()) {
      row++;
      return "You moved South.";
    } else {
      return "You cannot move South";
    }
  }

/**
 * east method
 * Method to state the you moved East,  you will add movement functionality in 
 * checkpoint 4
 * @return a string that states you moved East, do not use print or println
 */

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "east" method to allow the player to move East only if possible
 * Be sure to both move the player East and state that the player moved East
 * If you cannot move East, then state that you cannot move East
 */

  public static String east(){
    if (currentRoom.getCanGoEast()) {
      column++;
      return "You moved East.";
    } else {
      return "You cannot move East";
    }
  }

/**
 * west method
 * Method to state the you moved West, you will add movement functionality in 
 * checkpoint 4
 * @return a string that states you moved West, do not use print or println
 */

/*
 * TODO: checkpoint 4 Task
 * 
 * Update the "west" method to allow the player to move West only if possible
 * Be sure to move the player West and state that the player moved West
 * If you cannot move West, then state that you cannot move West
 */

  public static String west(){
    if (currentRoom.getCanGoWest()) {
      column--;
      return "You moved West.";
    } else {
      return "You cannot move West";
    }
  }

/**
 * take method
 * Method to allow you to take an item, for now you will just state that 
 * "You took the <itemName>." where <itemName> is the actual name of the item.
 * 
 * example return statement:
 * "You took the apple." 
 * 
 * You will actually pick up items in checkpoint 5
 * 
 * @param takes in a String to determine what object was picked up
 * @return a String that states "You took the <itemName>", do not use print or println
 */

  public static String take(String itemName) {
    if (currentRoom.hasObject(itemName)) {
      inventory.add(currentRoom.getObject(itemName));
      currentRoom.removeObject(itemName);
      return "You took the " + itemName + ".";
    }
    return "Can not take the " + itemName + ".";
  }

/** 
 * drop method
 * Method to allow you to drop an item, for now you will just state that 
 * "You dropped the <itemName>." where <itemName> is the actual name of the item.
 * 
 * example return statement:
 * "You dropped the apple."
 * 
 * You will actually drop items in checkpoint 5
 * @param takes in a string to determine what object was dropped
 * @return a string that states "You dropped the <itemName>", do not use print or println 
 */

  public static String drop(String itemName) {
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).getName().equals(itemName)) {
        currentRoom.addObject(inventory.get(i));
        inventory.remove(i);
        return "You dropped the " + itemName + ".";
      }
    }
    return "You can not drop the " + itemName + ".";
  }

/** 
 * look method
 * Method to allow you to look around the room
 * In checkpoint3, this will change to the room description
 *  @return a string that states "You looked around the room", do not use print or println
 */	

/*  
 * TODO: checkpoint 4 task
 * 
 * update the "look" method to return the Name of the Room, 
 * then a new line, and then the room's description instead of its current output.
 */

  public static String look() {
    if(currentRoom.getName().equals("Guard Room")) {
      done = true;
      return "The guards have found you!";
    }
    if(currentRoom.getName().equals("Developer Room")) {
      String string = "";
      String[] cat = {
      "╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤ _             _ ╤╧╤╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤ | '-.       .-' | ╤╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤ \\'-.'-\"\"\"-'.-'/    _  ╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤╧╤ |= _:'.':_ =|    /:`) ╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤╧ \\ <O>   <O> /   /  / ╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤╧╤ |=   |_|   =|   |:'\\ ╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤╧ >\\:.  \"  .:/<    ) .| ╧╤╧╤╧",
      "╤╧╤╧╤╧╤╧╤╧ /'-._^_.-'\\    /.:/ ╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤╧ /::.     .::\\  /' / ╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤ .| '::.  .::'  |;.:/ ╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤ /`\\:.         .:/`\\( ╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤ |:. | ':.   .:' | .:| ╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧ | ` |:.;     ;.:| ` | ╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧ \\:.|  |:. .:|  |.:/ ╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧ \\ |:.|     |.:| / ╧╤╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤ /'|  |\\   /|  |`\\ ╤╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤ (,,/:.|.-'-.|.:\\,,) ╤╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤  (,,,/     \\,,,)  ╤╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤                 ╤╧╤╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤",
      "╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧",
      "╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤╧╤"
                      };
          for (int i = 0; i < cat.length; i++) {
            string += cat[i] + "\n";
          }
      return string;
    }
    return currentRoom.getName() + "\n" + currentRoom.getDescription();
  }

/** 
 * inventory method
 * Method to display the items in your inventory, for now state
 * "The current items in your inventory are:"
 * 
 * In checkpoint 5 you will create a String that consists of all of the 
 * items in your inventory
 *  @return the "The current items in your inventory are:", do not use print or println
 */

  public static String inventory() {
    String output = "";
    if(inventory==null)
      return "You don't have anything yet";
    for (int i = 0; i < inventory.size(); i++) {
      
        output += inventory.get(i).getName() + "\n ";
    }
    return "The current items in your inventory are: \n" + output;
  }

/**
 * examine method
 * Method that returns the description on an item in your inventory, for now state:
 * "Description of <itemName>" where <itemName> is the itemName
 * 
 * example return statement:
 * "Description of apple"
 * 
 * In checkpoint 5 you will return the description of the object you are examining
 * @param takes in a string to determine what item you want the description of
 * @return a string that is the description of the item, do not use print or println
 */

  public static String examine(String item) {
    if(item.equals("room")) {
      return currentRoom.getNamesOfObjectsInRoom();
    } else if(currentRoom.hasObject(item)) {
      return currentRoom.getObject(item).getDescription();
    } else {
      return "You can not examine the " + item;
    }
    
  }	

/**
 * eat method
 * Method that allows you to eat a Food object, for now state:
 * "You ate the <itemName>" where <itemName> is the name of the itemName
 * 
 * example return statement:
 * "You ate the apple."
 * 
 * In checkpoint 5, you will update this method to remove it from your inventory, 
 * but not drop it in the room.  Advanced: this could be useful for a health system
 * 
 * 
 * @param takes in a string to determine what item you want to eat
 * @return a string that states you ate the item, do not use print or println
 */	

  public static String eat(String item) {
    return "You can not eat the " + item + ".";
  }		

/**  
 * use method
 * Method that allows you to use an item it, for now state:
 * "You used the <itemName>", where <itemName> is the name of the item.
 * 
 * example return statement:
 * "You used the apple."
 * 
 * In checkpoint 5 this will be updated to only allow the player to use a Key
 * in the room where the key can open a door
 * 
 * @param takes in a string to determine what item you want to use
 * @return a string that You used the item, do not use print or println
 */

  public static String use(String item) {
    if (currentRoom.getName().equals("Cell") && hasObject("lockpick") && currentRoom.getCanGoSouth() == false && item.equals("lockpick")) {
      currentRoom.setCanGoSouth(true);
      return "You used the lockpick and the door swings open. Type \"south\" to go exit the cell";
    }
    if(currentRoom.getName().equals("Living Room") && hasObject("red key") && currentRoom.getCanGoEast() == false && item.equals("red key")) {
      currentRoom.setCanGoEast(true);
      return "You have open the door to the east";
    }
    if(currentRoom.getName().equals("Living Room") && code == true && item.equals("1234")) {
      code = false;
      return "You have inputed the correct code";
    }
    if(currentRoom.getName().equals("Living Room") && wood == true && hasObject("crowbar") && item.equals("crowbar")) {
      wood = false;
      return "You have taken the wood off the door";
    }
    if(currentRoom.getName().equals("Credits") && hasObject("keycard") && currentRoom.getCanGoEast() == false && item.equals("keycard")) {
      currentRoom.setCanGoEast(true);
      return "You have open the secret door to the east";
    }
    return "You can not use the " + item + ".";
  }

/**
 * help method
 * Method that returns all of the commands and what they do
 * 
 * example return output:
 * "Command      Example      Description
 *  take         take apple   used to pick up objects"
 * 
 * @return a String that contains all the command names, an example of use, and a description of what they do.  Be sure to consider readability of the player.  Do not use print or println.
 */

  public static String help() {
    return "The following commands are used to play the game\n"
        + "Command    Example      Description\n"
        + "North      North        Allows you to move North\n"
        + "South      south        Allows you to move South\n"
        + "East       east         allows you to move East\n"
        + "West       west         allows you to move West\n"
        + "Look       Look         provides a detailed descriptoin of the room\n"
        + "Take       take key     adds an item in a room to your inventory\n"
        + "Drop       drop key     removes an item from your inventory and leaves it in the room\n"
        + "Inventory  inventory    displays the names of items in your inventory\n"
        + "Examine    examine key  provides a detailed description of the specified item, it must be in your inventory or you can type room to examine items in the room\n"
        + "Eat        eat apple    Allows you to eat an edible item\n"
        + "Use        use key      Allows you to use the item\n"
        + "Help       help         displays the help menu\n"
        + "Quit       quit         player initiated end of game\n"
        + "Map        map          Gets the map of the area"
        + "Input      input        can input code";
  }

/**
 * quit Method
 * Method that states "You have decided to quit the game"
 * @return a message saying You have decided to quit the game, do not use print or println
 */

  public static String quit() {
    return "You have decided to quit the game.";
  }

/**
 * invalid method
 * Method called when a player tries a command that is not used in the game
 * @return message "I do not understand", do not use print or println
 */

  public static String invalid() {
    return "I do not understand.";
  }

/**
 * executeCommand method
 * This method uses the first word to call the appropriate method that you
 * made above.  Basically, this method manages complexity by hosting the 
 * logic to detemine which method should be used.
 * (for example, if the command is north, call the north() method). 
 * @param the String command used to check which method to call
 * @param the String item used for methods that interact with objects 
 * @return a String that is passed back from the appropriate method, 
 *         do not use print or println
 */

  public static String executeCommand(String command, String item) {
    if(command.equals("north"))
      return north();
    if(command.equals("south"))
      return south();
    if(command.equals("east"))
      return east();
    if(command.equals("west"))
      return west();
    if(command.equals("inventory"))
      return inventory();
    if(command.equals("look"))
      return look();
    if(command.equals("take"))
      return take(item);
    if(command.equals("drop"))
      return drop(item);
    if(command.equals("examine"))
      return examine(item);
    if(command.equals("eat"))
      return eat(item);
    if(command.equals("use"))
      return use(item);
    if(command.equals("help"))
      return help();
    if(command.equals("quit"))
      return quit();
    if(command.equals("map"))
      return getMap();
    if(command.equals("input"))
      return input(item);
    return invalid();
  }

/**  
 * updateRoom method
 * Method to update the room you are in (currentRoom)
 * this method uses the row and column values to 
 * update the currentRoom location
 * no parameters or return values
 */
/*
 * TODO: checkpoint 4 Task
 * 
 * make the updateRoom method
 */

  public static void updateRoom() {
    currentRoom = map[row][column];
  }

/**
 * 	getCurrentRoom method
 *  In order to work with the JFrame, we need a way to get the room
 *  create an accessor method that returns the currentRoom called getCurrentRoom
 *  @return the current room, but sure to update the room first
 */

/* 
 * TODO: checkpoint 4 Task
 * 
 * make the getCurrentRoom method
 */

  public static Room getCurrentRoom() {
    updateRoom();
    return map[row][column];
  }

  //DO NOT TOUCH
  public static void main(String[] args) {
    runGame();
  }

  public static String getMap(){
    String returnString = "\n";
    for(Room[] ar : map){
      for(Room r : ar){
        if (r == currentRoom){
          returnString += "🌸";
        }else{

          String rname = r.getName();
          if(rname=="Cell"){
            returnString += "🔒";
          } else if(rname=="Dining Room"){
            returnString += "🍴";
          } else if(rname=="Kitchen"){
            returnString += "🍳";
          } else if(rname=="Hallway"){
            returnString += "⏰";
          } else if(rname=="Living Room"){
            returnString += "📺";
          } else if(rname=="Bedroom"){
            returnString += "💻";
          } else if(rname=="Guard Room"){
            returnString += "💀";
          } else if(rname=="Outside"){
            returnString += "🎇";
          } else if(rname=="Bathroom"){
            returnString += "🚽";
          } else if(rname=="Secret Room"){
            returnString += "🔮";
          } else if(rname=="Credits"){
            returnString += "💬";
          } else if(rname=="Developer Room"){
            returnString += "❔";
          }

        }
      }
      returnString += "\n";
    }
    return returnString;
  }

  public static boolean hasObject(String objectName) {
    for (int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).getName().equals(objectName)) {
        return true;
      }
    }
    return false;
  }

  public static String input(String number) {
    if (number.equals("1234") && currentRoom.getName().equals("Living Room") && currentRoom.getCanGoSouth() == false) {
      code = false;
      return "You have successfully used the code";
    }
    else {
      return "Incorrect code";
    }
  }
}