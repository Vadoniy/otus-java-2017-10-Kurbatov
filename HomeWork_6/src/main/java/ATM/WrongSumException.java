package ATM;

class WrongSumException extends Exception {
    private int number;

    public WrongSumException(String message, int num){
        super(message);
        this.number = num;
    }
}
