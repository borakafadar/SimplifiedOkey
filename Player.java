public class Player {
    String playerName;
    Tile[] playerTiles;
    int numberOfTiles;

    public Player(String name) {
        setName(name);
        playerTiles = new Tile[15]; // there are at most 15 tiles a player owns at any time
        numberOfTiles = 0; // currently this player owns 0 tiles, will pick tiles at the beggining of the game
    }

    /*
     * removes and returns the tile in given index
     */
    public Tile getAndRemoveTile(int index) {
        
        Tile tile = playerTiles[index];
        playerTiles[index]=null;
        
        Tile first;
        for(int i=index;i<playerTiles.length-1;i++){
            first=playerTiles[i];
            Tile second=playerTiles[i+1];
            playerTiles[i]=second;
            playerTiles[i+1]=first;
        }
        if(tile != null){
            numberOfTiles--;
        }
        
        return tile;
    }

    /*
     * adds the given tile to the playerTiles in order
     * should also update numberOfTiles accordingly.
     * make sure playerTiles are not more than 15 at any time
     */
    public void addTile(Tile t) {
        
        for(int i=0;i<numberOfTiles-1;i++){
            if(t!=null){
                if(t.compareTo(playerTiles[i])==-1 || t.compareTo(playerTiles[i])==0){ //t < playerTiles[i]
                    shiftArray(t, i);
                    numberOfTiles++;
                    return;
                }
            }
        }
        playerTiles[numberOfTiles++]=t;

    }

    /*
     * checks if this player's hand satisfies the winning condition
     * to win this player should have 3 chains of length 4, extra tiles
     * does not disturb the winning condition
     * @return
     */
    public boolean isWinningHand() {

        Tile[] nonDupedTiles = new Tile[15];
        int nonDupedTilesCount=0;
        for(int i=0;i<numberOfTiles;i++){
            boolean inArray=false;
            for(Tile t : nonDupedTiles){
                if(t==null){
                    break;
                }
                if(t.compareTo(playerTiles[i])==0 && t!=null){
                    inArray=true;
                }
            }
            if(!inArray && playerTiles[i]!=null){
                nonDupedTiles[nonDupedTilesCount++]=playerTiles[i];
            }
        }

        int correctChains=0;
        int currentChain=0;

        for(int i=1;i<nonDupedTilesCount;i++){
            Tile t=nonDupedTiles[i-1];
            
            if(t.canFormChainWith(nonDupedTiles[i])){
                currentChain++;
                if(currentChain==3){
                    currentChain=0;
                    correctChains++;
                }
            }else{
                currentChain=0;
            }
        }

        if(correctChains==3){
            return true;
        }

        return false;
    }

    public int findPositionOfTile(Tile t) {
        int tilePosition = -1;
        for (int i = 0; i < numberOfTiles; i++) {
            if(playerTiles[i].compareTo(t) == 0) {
                tilePosition = i;
            }
        }
        return tilePosition;
    }

    public void displayTiles() {
        System.out.println(playerName + "'s Tiles:");
        for (int i = 0; i < numberOfTiles; i++) {
            System.out.print(playerTiles[i].toString() + " ");
        }
        System.out.println();
    }

    private void shiftArray(Tile added,int index){
        Tile[] temp = new Tile[playerTiles.length+1];

        for(int i=0;i<index;i++){
            temp[i]=playerTiles[i];
        }
        for(int i=index+1;i<temp.length;i++){
            temp[i]=playerTiles[i-1];
        }
        temp[index]=added;
        for(int i=0;i<playerTiles.length;i++){
            playerTiles[i]=temp[i];
        }
    }

    public Tile[] getTiles() {
        return playerTiles;
    }

    public void setName(String name) {
        playerName = name;
    }

    public String getName() {
        return playerName;
    }
}
