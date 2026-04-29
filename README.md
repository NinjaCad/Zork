# 🏠 Escape the House (Zork Final Chapter)

## 📖 Overview

**Escape the House** is a text-based adventure game inspired by classic Zork-style gameplay. The player must explore rooms, collect items, and solve puzzles in order to escape a house after being kidnapped.

The game is played entirely through console commands, where the player navigates rooms, interacts with objects, and unlocks new areas.

---

## 🎮 How to Play

When you start the game, you’ll be placed in a room and given a prompt:

```
>>
```

Type commands to interact with the world.

### 🔑 Objective

Escape the house by:

* Exploring rooms
* Collecting useful items
* Solving puzzles (keys, codes, tools)
* Unlocking the path to the outside

---

## 🧭 Commands

| Command   | Example       | Description                   |
| --------- | ------------- | ----------------------------- |
| north     | `north`       | Move north                    |
| south     | `south`       | Move south                    |
| east      | `east`        | Move east                     |
| west      | `west`        | Move west                     |
| look      | `look`        | View current room description |
| take      | `take key`    | Pick up an item               |
| drop      | `drop key`    | Drop an item                  |
| inventory | `inventory`   | View your items               |
| examine   | `examine key` | View item description         |
| eat       | `eat apple`   | Eat a food item               |
| use       | `use key`     | Use an item                   |
| input     | `input 1234`  | Enter a code                  |
| map       | `map`         | Display the map               |
| help      | `help`        | Show commands                 |
| quit      | `quit`        | Exit the game                 |

---

## 🗺️ Game Features

### 🧩 Puzzle Mechanics

* Locked doors require keys or codes
* Some doors require multiple steps (e.g., remove wood + enter code)
* Items must be used in the correct room

### 🎒 Inventory System

* Stores collected items using an `ArrayList`
* Supports multiple item types:

  * **Key** → unlock doors
  * **Food** → edible items (extendable)
  * **Item** → general objects

### 🏠 Room System

* Each room acts like a container for items
* Rooms have directional movement (north, south, east, west)
* Some rooms contain puzzles or special behaviors

### 🧪 Special Mechanics

* Hidden rooms (Secret Room)
* Multiple unlock conditions (code + crowbar)
* Win and lose conditions:

  * ✅ Win: Reach "Outside"
  * ❌ Lose: Enter "Guard Room"

---

## 🧱 Project Structure

### Core Classes

* `Commands` → Handles game logic and user input
* `Room` → Represents each location
* `InventoryItem` → Base class for all items
* `Key` → Unlocks doors
* `Food` → Consumable items

---

## ▶️ How to Run

1. Open the project in your Java IDE (e.g., IntelliJ, Eclipse)
2. Run the `Commands` class
3. Play through the console

---

## 🧭 Walkthrough (Winning Path)

| Command                     | Room                  | Result                   |
| --------------------------- | --------------------- | ------------------------ |
| look                        | Cell                  | See lockpick             |
| take lockpick               | Cell                  | Pick up lockpick         |
| use lockpick                | Cell                  | Unlock south door        |
| south                       | Cell → Hallway        | Move to hallway          |
| east                        | Hallway → Living Room | Enter living room        |
| west                        | Living Room → Hallway | (Explore optional paths) |
| north → east → take red key | Kitchen               | Get red key              |
| return to Living Room       | Living Room           |                          |
| use red key                 | Living Room           | Unlock east door         |
| east                        | Bedroom               |                          |
| take code                   | Bedroom               | Learn code (1234)        |
| return to Living Room       | Living Room           |                          |
| south → take crowbar        | Bathroom              |                          |
| return to Living Room       | Living Room           |                          |
| use crowbar                 | Living Room           | Remove wood              |
| input 1234                  | Living Room           | Enter code               |
| south                       | Outside               | 🎉 YOU WIN               |

---

## 💡 Tips

* Always use `look` when entering a new room
* Use `inventory` often to track items
* Some puzzles require multiple steps
* If something doesn’t work, try using the item in a different room
