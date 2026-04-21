import java.util.ArrayList;
import java.util.List;

public class ExplorerSearch {

    /**
     * Returns how much land area an explorer can reach on a rectangular island.
     * 
     * The island is represented by a rectangular int[][] that contains
     * ONLY the following nunbers:
     * 
     * '0': represents the starting location of the explorer
     * '1': represents a field the explorer can walk through
     * '2': represents a body of water the explorer cannot cross
     * '3': represents a mountain the explorer cannot cross
     * 
     * The explorer can move one square at a time: up, down, left, or right.
     * They CANNOT move diagonally.
     * They CANNOT move off the edge of the island.
     * They CANNOT move onto a a body of water or mountain.
     * 
     * This method should return the total number of spaces the explorer is able
     * to reach from their starting location. It should include the starting
     * location of the explorer.
     * 
     * For example
     * 
     * @param island the locations on the island
     * @return the number of spaces the explorer can reach
     */
    public static int reachableArea(int[][] island) {
        // Implement your method here!
        // Please also make more test cases
        // I STRONGLY RECOMMEND testing some helpers you might make too
        // int[] startLocation = explorerLocation(island);
        return -1;
    }

    public static List<int[]> possibleMoves(int[][] island, int[] location){
        int currentRow = location[0];
        int currentColumn = location[1];

        List<int[]> validLocations = new ArrayList<>();

        //up
        int newRow = currentRow -1;
        int newColumn = currentColumn;
        
        if (newRow >= 0 && (island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3)) {
            validLocations.add(new int[]{newRow, newColumn});
        }
        
        //down
        newRow = currentRow + 1;
        newColumn = currentColumn;
        
        if (newRow < island.length && (island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3)) {
            validLocations.add(new int[]{newRow, newColumn});
        }

        //left
        newRow = currentRow;
        newColumn = currentColumn - 1;
        
        if(newColumn >= 0 && (island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3)){
            validLocations.add(new int[]{newRow, newColumn});
        }

        //right
        newRow = currentRow;
        newColumn = currentColumn + 1;
        
        if (newColumn < island[0].length && (island[newRow][newColumn] != 2 && island[newRow][newColumn] != 3)) {
            validLocations.add(new int[]{newRow, newColumn});
        }

        return validLocations;
    }

    public static int[] explorerLocation(int[][] island){
        for(int row = 0; row < island.length; row++){
            for(int col = 0; col < island[0].length; col++){
                if(island[row][col] == 0){
                    return new int[] {row, col};
                }
            }
        }
        throw new IllegalArgumentException("No explorer present");
    }
}
