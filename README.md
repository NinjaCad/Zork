# Zork (Replit Java build)

This repository is a **Replit-based Java project** that builds and runs a text adventure game inspired by **Zork**.

## What’s in here
- Java source files (`*.java`) that implement the game logic
- Replit configuration (`.replit`, `replit.nix`)
- Some compiled artifacts (`*.class`) and a backup file (see notes)

## How to run
### On Replit
This repo already includes Replit config:
- **Entrypoint:** `Main.java`
- **Run command:** `java -classpath .:target/dependency/* Main` (from `.replit`)

Click **Run** in Replit.

### Locally (basic)
If you have a JDK installed:

```bash
javac *.java
java Main
```

## Main classes / files
- **`Main.java`** — Program entrypoint; calls `Commands.runGame()`.
- **`Commands.java`** — Core command parsing + game loop (this appears to be the main driver of gameplay).
- **`Room.java`** — Room/environment logic.
- **`InventoryItem.java`** — Base class for items with `name`, `description`, and `weight`.
- **`Key.java`** — `InventoryItem` subclass intended to represent a key (room + direction fields).
- **`Food.java`** — Stub for a `Food` item type (currently marked TODO).

## Repo notes
- The repo contains `*.class` files (compiled Java bytecode) such as `Main.class`, `Commands.class`, etc.
- The repo contains a backup/temporary-looking file: **`.Room.java.2848114809~`**.

If you want, I can also:
- add a `.gitignore` for Java/Replit builds to avoid committing `*.class` and editor backup files, and/or
- expand this README with gameplay instructions and a list of supported commands (after inspecting `Commands.java`).
