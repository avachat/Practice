package avachat.algo;

/**
 * Keep accepting next int.
 * After every int, give out a rolling average of last N entries, as well as max of last N entries.
 * Created by avachat on 8/3/15.
 */
public class RollingAverage {

    private int[] buffer;
    private int nextPosn;
    private boolean flagBufferReady;
    private int currentSum;
    private double average;
    private int max;
    private int maxAt;

    /**
     * Assuming bufferSize more than 1
     * @param bufferSize
     */
    public RollingAverage (int bufferSize) {
        buffer = new int[bufferSize];
        this.nextPosn = 0;
        this.flagBufferReady = false;
        this.average = 0;
        this.max = Integer.MIN_VALUE;
    }

    public void putNextInt (int num) {

        // keep adding till buffer is not full
        if ( ! flagBufferReady ) {
            buffer[nextPosn] = num;
            currentSum += num;

            if ( num >= max) {
                max = num;
                maxAt = nextPosn;
            }

            nextPosn++;

            // after nextPosn is incremented, it reflects the count of elements
            average = currentSum*1.0 / nextPosn;

            if ( nextPosn == buffer.length) {
                flagBufferReady = true;
                nextPosn = 0;
            }
            return;
        }

        //remove existing element from sum and add the new element
        currentSum -= buffer[nextPosn];
        currentSum += num;
        average = currentSum*1.0 / buffer.length;

        buffer[nextPosn] = num;

        // if the current max is being overwritten, a new max has to be found
        if ( num >= max) {
            max = num;
            maxAt = nextPosn;
        }
        else if ( maxAt == nextPosn) {
            // find new max
            int tempMax = buffer[nextPosn];
            int index = nextPosn + 1;
            if (index == buffer.length) {
                index = 0;
            }
            while ( index != nextPosn) {
                if (buffer[index] >= tempMax) {
                    tempMax = buffer[index];
                    maxAt = index;
                }
                index++;
                if (index == buffer.length) {
                    index = 0;
                }
            }
            max = tempMax;
        }

        nextPosn++;
        if (nextPosn == buffer.length) {
            nextPosn = 0;
        }
    }

    public int getMax() {
        return max;
    }

    public int getMaxAt() {
        return maxAt;
    }

    public double getAverage() {
        return average;
    }

    public String getAverageAndMAx () {
        StringBuilder strBuf = new StringBuilder();
        strBuf.append ("ready=");
        strBuf.append(flagBufferReady);
        strBuf.append(";");
        strBuf.append("average=");
        strBuf.append(average);
        strBuf.append(";");
        strBuf.append("max=");
        strBuf.append(max);
        strBuf.append(";");
        strBuf.append("maxAt=");
        strBuf.append(maxAt);

        return strBuf.toString();
    }

}
