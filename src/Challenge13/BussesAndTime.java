package Challenge13;

import java.util.HashMap;
import java.util.HashSet;

public class BussesAndTime {
    private long earliest;
    private HashSet<Long> busses;
    private HashMap<Long,Long> busOffset;

    public BussesAndTime(long earliest, HashSet<Long> busses, HashMap<Long, Long> busOffset) {
        this.earliest = earliest;
        this.busses = busses;
        this.busOffset = busOffset;
    }

    public long getEarliest() {
        return earliest;
    }

    public HashSet<Long> getBusses() {
        return busses;
    }

    public HashMap<Long, Long> getBusOffset() {
        return busOffset;
    }

    @Override
    public String toString() {
        return "BussesAndTime{" +
                "earliest=" + earliest +
                ", busses=" + busses +
                ", busOffset=" + busOffset +
                '}';
    }
}
