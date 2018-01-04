package ATM;

public enum Notes {
    FiveThousand(5000),
    OneThousand(1000),
    FiveHundred(500),
    OneHundred(100),
    HalfHundred(50),
    Ten(10);

    private final int value;

    Notes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
