package ATM;

enum Notes {
    FiveThousand(5000),
    OneThousand(1000),
    FiveHundreed(500),
    OneHundreed(100),
    HalfHundreed(50),
    Ten(10);

    private final int value;

    Notes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
