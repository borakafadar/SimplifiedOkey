import java.util.Random;

public class OkeyGame {

    Player[] players;
    Tile[] tiles;

    Tile lastDiscardedTile;

    int currentPlayerIndex = 0;

    public OkeyGame() {
        players = new Player[4];
    }

    public void createTiles() {
        tiles = new Tile[112];
        int currentTile = 0;

        // two copies of each color-value combination, no jokers
        for (int i = 1; i <= 7; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[currentTile++] = new Tile(i,'Y');
                tiles[currentTile++] = new Tile(i,'B');
                tiles[currentTile++] = new Tile(i,'R');
                tiles[currentTile++] = new Tile(i,'K');
            }
        }
    }

    /*
     * TODO: distributes the starting tiles to the players
     * player at index 0 gets 15 tiles and starts first
     * other players get 14 tiles
     * this method assumes the tiles are already shuffled
     */
    public void distributeTilesToPlayers() {

    }

    /*
     * TODO: get the last discarded tile for the current player
     * (this simulates picking up the tile discarded by the previous player)
     * it should return the toString method of the tile so that we can print what we picked
     */
    public String getLastDiscardedTile() {
        return null;
    }

    
    public String getTopTile() {
        Tile tempTile=tiles[tiles.length-1];
        players[currentPlayerIndex].playerTiles[players[currentPlayerIndex].playerTiles.length]=tempTile;
        tiles[tiles.length-1]=null;
        return tempTile.toString();
    }

    
    public void shuffleTiles() {
        Random random = new Random();
        for(int i=tiles.length-1;i>0;i--){
            int randomIndex = random.nextInt(i+1);
            Tile tempTile =tiles[i];
            tiles[i]=tiles[randomIndex];
            tiles[randomIndex]=tempTile;
        }
    }

    /*
     * TODO: check if game still continues, should return true if current player
     * finished the game, use isWinningHand() method of Player to decide
     */
    public boolean didGameFinish() {
        return false;
    }

    public void pickTileForComputer() {
        Player currentPlayer = players[currentPlayerIndex];
        int discardedValue = lastDiscardedTile.getValue();
        int[] counts = new int[6];

        for(int i = 0; i<currentPlayer.getTiles().length; i++) {
            counts[currentPlayer.getTiles()[i].getValue()-1]++;
        }
        if(counts[discardedValue-1] >= 2 && counts[discardedValue-1] < 4) {
            getLastDiscardedTile();
            System.out.println(currentPlayer.getName() + " picked from discarded tile.");
        } else{
            getTopTile();
            System.out.println(currentPlayer.getName() + " picked from tiles.");
        }
    }  

    /*
     * TODO: Current computer player will discard the least useful tile.
     * this method should print what tile is discarded since it should be
     * known by other players. You may first discard duplicates and then
     * the single tiles and tiles that contribute to the smallest chains.
     */
    public void discardTileForComputer() {

    }

    public void discardTile(int tileIndex) {
        Player currentPlayer = players[currentPlayerIndex];
        lastDiscardedTile = currentPlayer.getAndRemoveTile(tileIndex);
        passTurnToNextPlayer();
    }

    public void displayDiscardInformation() {
        if(lastDiscardedTile != null) {
            System.out.println("Last Discarded: " + lastDiscardedTile.toString());
        }
    }

    public void displayCurrentPlayersTiles() {
        players[currentPlayerIndex].displayTiles();
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

      public String getCurrentPlayerName() {
        return players[currentPlayerIndex].getName();
    }

    public void passTurnToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % 4;
    }

    public void setPlayerName(int index, String name) {
        if(index >= 0 && index <= 3) {
            players[index] = new Player(name);
        }
    }

}
