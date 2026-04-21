import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExplorerSearchTest {
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

    // Add more tests here!
    // Come up with varied cases
    @Test
    public void testReachableArea_fullboxed() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,3,3},
            {1,1,1,2,3,0},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(1, actual);
    }

    @Test
    public void testReachableArea_AllReachableArea() {
        int[][] island = {
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,0},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(30, actual);
    }

    @Test
    public void testReachableArea_AllMaze() {
        int[][] island = {
            {2,1,2,1,1,1},
            {3,1,3,1,3,1},
            {2,1,2,1,2,1},
            {3,1,3,1,3,1},
            {2,1,1,1,2,0},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(17, actual);
    }

    @Test
    public void testReachableArea_OneRowStraightTo() {
        int[][] island = {
            {0,1,1,1,1,1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(6, actual);
    }

    @Test
    public void testReachableArea_OneColumnStraightTo() {
        int[][] island = {
            {0},
            {1},
            {1},
            {1},
            {1},
            {1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(6, actual);
    }
    @Test
    public void testReachableArea_twoColumnSomeBlocked() {
        int[][] island = {
            {0,1},
            {2,1},
            {2,1},
            {2,1},
            {2,1},
            {2,1}
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(7, actual);
    }

    @Test
    public void testReachableArea_stairCase() {
        int[][] island = {
            {2,2,2,3,3,3},
            {1,1,1,2,2,3},
            {2,2,1,1,2,2},
            {2,2,2,1,1,1},
            {2,2,2,2,2,0},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(9, actual);
    }

    @Test
    public void testExplorerLocation_centerOfGrid(){
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] expected = {1, 1};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerLocation_topOfGrid(){
        int[][] island = {
            {1, 0, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        int[] expected = {0, 1};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerLocation_bottomOfGrid(){
        int[][] island = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 0, 1}
        };
        int[] expected = {2, 1};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerLocation_leftOfGrid(){
        int[][] island = {
            {1, 1, 1},
            {0, 1, 1},
            {1, 1, 1}
        };
        int[] expected = {1, 0};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerLocation_rightOfGrid(){
        int[][] island = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 1, 1}
        };
        int[] expected = {1, 2};
        assertArrayEquals(expected, ExplorerSearch.explorerLocation(island));
    }

    @Test
    public void testExplorerLocation_notFound(){
        int[][] island = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.explorerLocation(island);
        });

        assertEquals("No explorer present", exception.getMessage());
    }

    @Test
    public void testPossibleMoves_allDirectionsOpen() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
    }
    @Test
    public void testPossibleMoves_EveryDirectionBlocked() {
        int[][] island = {
            {2, 3, 2},
            {2, 0, 2},
            {2, 3, 2}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        
        assertTrue(moves.isEmpty());
       
    }

    @Test
    public void testPossibleMoves_partialEdge() {
        int[][] enclosure = {
            {0, 1, 1}
        };
        int[] location = {0, 0};
        List<int[]> moves = ExplorerSearch.possibleMoves(enclosure, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1")); // only right
    }

    

    @Test
    public void testPossibleMoves_topOnlyBlocked() {
        int[][] island = {
            {2, 2, 2},
            {1, 0, 1},
            {2, 1, 2}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("1,0")); // left
        assertTrue(moveSet.contains("1,2")); // right
        assertTrue(moveSet.contains("2,1")); // down

       
    }

    @Test
    public void testPossibleMoves_rightOnlyBlocked() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 2},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,0")); // left
    }
    
    @Test
    public void testPossibleMoves_leftOnlyBlocked() {
        int[][] island = {
            {1, 1, 1},
            {2, 0, 1},
            {1, 1, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("2,1")); // down
        assertTrue(moveSet.contains("1,2")); // right
    }

    @Test
    public void testPossibleMoves_DownOnlyBlocked() {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 2, 1}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moves.size());
        assertTrue(moveSet.contains("0,1")); // up
        assertTrue(moveSet.contains("1,2")); // right
        assertTrue(moveSet.contains("1,0")); // left
    }

    @Test
    public void testPossibleMoves_LeftAndRightOpen() {
        int[][] island = {
            {2, 2, 2},
            {1, 0, 1},
            {2, 2, 2}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("1,2")); // right
        assertTrue(moveSet.contains("1,0")); // left
       
    }

    @Test
    public void testPossibleMoves_UpAndDownOpen() {
        int[][] island = {
            {2, 1, 2},
            {2, 0, 2},
            {2, 1, 2}
        };
        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("0,1")); // top
        assertTrue(moveSet.contains("2,1")); // down
       
    }

    private Set<String> toSet(List<int[]> list) {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) {
            set.add(arr[0] + "," + arr[1]);
        }
        return set;
    }
}
