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

    public static Notes getNote(String value){
        switch (value){
            case "10": return Ten;
            case "50": return HalfHundred;
            case "100": return OneHundred;
            case "500": return FiveHundred;
            case "1000": return OneThousand;
            case "5000": return FiveThousand;
        }
        return null;
    }

}
