/**
Key Class

A Key is an InventoryItem that has:
  the name of a room it works in 
  the direction it opens

Be sure to include the appropriate constructors, methods, and update the toString 
*/

//TODO  Create the Key class
class Key extends InventoryItem{
  private String room;
  private String direction;
  
  public Key(String name, String description, double weight, String room, String direction) {
    super(name, description, weight);
    this.room = room;
    this.direction = direction;
  }
}