package Challenge18;

public class Operation {
    private char typ;
    private long num;

    public Operation(char typ, long num) {
        this.typ = typ;
        this.num = num;
    }

    public Operation(char typ) {
        this.typ = typ;
        this.num = 0;
    }

    public Operation(long num) {
        this.num = num;
        this.typ = ' ';
    }

    public void setTyp(char typ) {
        this.typ = typ;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public long eval(long num2){
        if(typ == '+'){
            return num + num2;
        }
        if(typ == '*'){
            return num * num2;
        }
        if(typ == ' '){
            return num;
        }
        return 0;  // TODO: Throw exception or something
    }

    public char getTyp() {
        return typ;
    }

    public long getNum() {
        return num;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "typ=" + typ +
                ", num=" + num +
                '}';
    }
}
