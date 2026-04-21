import static org.junit.Assert.*;
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
        assertEquals(32, actual);
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
}
