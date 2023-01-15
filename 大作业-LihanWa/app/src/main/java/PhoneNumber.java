public class PhoneNumber {
    private int register, areaCode, lastFour;
    public PhoneNumber(int reg, int area, int last) {
        register = reg;
        areaCode = area;
        lastFour = last;
    }
    public String toString() {
        return "(" + register + ") " + areaCode + "-" + lastFour;
    }
}

