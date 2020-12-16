package Challenge16;

public class TicketRule {
    private String name;
    private long cond1l;
    private long cond1u;
    private long cond2l;
    private long cond2u;

    public TicketRule(String name, long cond1l, long cond1u, long cond2l, long cond2u) {
        this.name = name;
        this.cond1l = cond1l;
        this.cond1u = cond1u;
        this.cond2l = cond2l;
        this.cond2u = cond2u;
    }

    public boolean validate(long num){
        return ((num >= cond1l) && (num <= cond1u)) || ((num >= cond2l) && (num <= cond2u));
    }

    @Override
    public String toString() {
        return "TicketRule{" +
                "name='" + name + '\'' +
                ", cond1l=" + cond1l +
                ", cond1u=" + cond1u +
                ", cond2l=" + cond2l +
                ", cond2u=" + cond2u +
                '}';
    }
}
