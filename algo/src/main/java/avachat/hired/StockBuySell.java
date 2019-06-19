package avachat.hired;

public class StockBuySell {

    // find the maximum profit to be made by buying and selling the stock only once

    public static long solution(long[] prices) {
        // Type your solution here

        // we need at least 2 values
        if ( (null == prices) || (prices.length < 2)) {
            return 0;
        }

        long maxProfit = 0; // max profit seen so far
        long buyAt = prices[0]; // first buying opportunity is at index 0

        // start from index 1
        for (int i = 1; i < prices.length; i++) {

            long currentPrice = prices[i];

            // if the price is same as buyAt, move on
            if (currentPrice == buyAt) {
                continue;
            }

            // if current price is better than buyAt
            // this price is better for following values
            if ( currentPrice < buyAt) {
                buyAt = currentPrice;
                continue;
            }

            // may be we can sell at this price
            long profit = currentPrice - buyAt;
            if (profit > maxProfit) {
                maxProfit = profit;
            }

        }

        return maxProfit;
    }

}
