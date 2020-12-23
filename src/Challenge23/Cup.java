package Challenge23;

public class Cup {
    private Cup next;
    long value;

    public Cup(Cup next, long value) {
        this.next = next;
        this.value = value;
    }

    public Cup getNext() {
        return next;
    }

    public void setNext(Cup next) {
        this.next = next;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "next=" + next.getValue() +
                ", value=" + value +
                '}';
    }
}
