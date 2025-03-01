# SimplifiedOkey - Spring 2025
This project is a simplified version of the original Okey game which is implemented as a console game. It has a bunch of simple rules and is played by a human and three other computer opponents. The main focus of the game is to complete a winning hand consisting of three chains of length 4, with specific rules about tile.


## Key Features:
- **Tiles**: There are 112 tiles in total, with 4 colors consisting of Black(K), Red(R), Blue(B) and Yellow(Y) and 7 numbers (1 - 7) for each color. Each tile appears 4 times.

- **Game Rules**:
  - No false or normal jokers.
  - Chains can only be made using different colors of the same number.
  - Players need to create three chains of length 4 to win.
  - The game ends with a tie if no player wins and no tiles are left to draw.
-**Gameplay**:
  - The game starts with 14 tiles for the computer players and 15 for the human player.
  - Players can either draw a tile from the stack or the tile discarded by the previous player.
- **Turn Logic**: Players take turns drawing tiles, if it's beneficial, discard a tile. The computer makes decisions based on the game state.
- **Winning**: A player wins when they have three distinct chains of length 4.

The game can be played with 4 computer players for a fully automated experience or with a human player taking turns. The program displays players' hands, actions, and game status.
