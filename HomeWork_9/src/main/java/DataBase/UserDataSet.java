package DataBase;

public class UserDataSet extends DataSet {
    private String name;
    private int age;

    public UserDataSet(){}

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + this.getId() +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
