package DataSet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "table_user")
public class UserDataSet extends DataSet{
    @Column (name = "name")
    private String name;
    @Column (name = "age")
    private int age;
    @OneToOne (cascade = CascadeType.ALL)
    private AddressDataSet address;
//    mappedBy = "uds" - name of the field, that we reference on in PhoneDataSet
    @OneToMany (mappedBy = "uds", cascade = CascadeType.ALL)//, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<PhoneDataSet> phones = new ArrayList<>();

    public UserDataSet(){}

    public UserDataSet(String name, int age, AddressDataSet address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public UserDataSet(int id, String name, int age, AddressDataSet address) {
        super.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }


    public void addPhone(PhoneDataSet phone){
        this.phones.add(phone);
//      Else we need to bound phone with this object
        phone.setUserDataSet(this);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}
