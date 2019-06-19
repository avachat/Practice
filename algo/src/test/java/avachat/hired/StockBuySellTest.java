package avachat.hired;

import org.junit.Test;

import static org.testng.Assert.*;

public class StockBuySellTest {


    @Test
    public void testAll () {

        assertEquals(StockBuySell.solution(new long[] {10, 2, 22, 6, 5, 35, 4, 3, 43, 1, 52}), 51);
        assertEquals(StockBuySell.solution(new long[] {10, 2, 22, 6, 5, 35, 4, 3, 43, 52}), 50);
        assertEquals(StockBuySell.solution(new long[] {0, 10, -1, 10}), 11);
        assertEquals(StockBuySell.solution(new long[] {6, 0, -1, 10}), 11);
        assertEquals(StockBuySell.solution(new long[] {2, 2, 2, 0}), 0);
        assertEquals(StockBuySell.solution(new long[] {2, 2, 2, 2}), 0);
        assertEquals(StockBuySell.solution(new long[] {-1, -1, -1, -1}), 0);
        assertEquals(StockBuySell.solution(new long[] {0, 0, 0}), 0);
        assertEquals(StockBuySell.solution(new long[] {3, 2,1}), 0);
        assertEquals(StockBuySell.solution(new long[] {2,1}), 0);
        assertEquals(StockBuySell.solution(new long[] {1}), 0);
        assertEquals(StockBuySell.solution(new long[] {}), 0);

    }

}