//TODO: Create the class here, be sure to pay attention to the name of the file  

public class InventoryItem {

  /*
  TODO:  Create 3 instance variables:  name, description, weight
  be sure to select the most appropriate type for each
  Note: that weight can be a decimal
  */

  private String name;
  private String description;
  private double weight;

  /**
   * InventoryItem class constructor
   * 
   * @param: a string to set the name of an InventoryItem ojbect
   * @param: a string to set the description of a InventoryItem ojbect
   * @param: a double to set the name of the weight of an InventoryItem 
   */

  //TODO: make the InventoryItem constructor according to the description above

  public InventoryItem(String name, String description, double weight) {
    this.name = name;
    this.description = description;
    this.weight = weight;
  }

  /**
   * Mutator method called "setName"
   * 	This method is used to change the name of a InventoryItem ojbect
   * 	@param: a String to update the name of a InventoryItem ojbect
  */

  //TODO: make the setName method

  public void setName(String name) {
    this.name = name;
  }

  /**
   * 	Mutator method called "setDescription"
   * 	This method is used to change the description of a InventoryItem ojbect
   * 	@param: a String to update the description of a InventoryItem ojbect
  */

  //TODO: make the setDescription method

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Mutator method called "setWeight"
   * This method is used to change the weight of a InventoryItem ojbect
   * @param: a double to update the weight of a InventoryItem ojbect
  */	

  //TODO: make the setWeight method

  public void setWeight(double weight) {
    this.weight = weight;
  }

  /**
   * Accessor method called "getName"
   * This method is used to retrieve the name of a InventoryItem ojbect
   * @return: a String that is the name of a InventoryItem ojbect
  */

  //TODO: make the getName method

  public String getName() {
    return name;
  }

  /**
   * Accessor method called "getDescription"
   * This method is used to retrieve the description of a InventoryItem ojbect
   * @return: a String that is the description of a InventoryItem ojbect
  */	

  //TODO: make the getDescription method

  public String getDescription() {
    return description;
  }

  /**
   * Accessor method called "getWeight"
   * This method is used to retrieve the weight of a InventoryItem ojbect
   * @return: a double that is the weight of a InventoryItem ojbect
  */	

  //TODO: make the getWeight method

  public double getWeight() {
    return weight;
  }

  /**
   * Accessor method called "toString", overwrites the default toString method
   * This method is used to retrieve all of the characteristics of a InventoryItem ojbect
   * @return: a String that is all of the characteristics of a InventoryItem ojbect in the following format
   name:description:weight
  */		

  //TODO: make the toString method

  public String toString() {
    return name + ":" + description + ":" + weight;
  }
}