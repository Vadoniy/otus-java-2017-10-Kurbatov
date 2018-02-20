package DataSet;

import javax.persistence.*;

/**
 * Created by Vadoniy on 12.02.2018.
 */

@Entity
@Table (name = "table_phone") //table name, that will be created or should be used
public class PhoneDataSet extends DataSet{

//  name is a name of column, that we bound this field in parent table.
//  name = "phones" - name of the field, that we reference on in UserDataSet
    @ManyToOne
    @JoinColumn (name = "user_id") //column name, that will be created or should be used with references to id of parent table
    private UserDataSet uds;

    @Column (name = "phoneNumber") //Column name, that will be created or should be used
    private String phoneNumber;

    public PhoneDataSet() {}

    public PhoneDataSet(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserDataSet(UserDataSet uds) {
        this.uds = uds;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
