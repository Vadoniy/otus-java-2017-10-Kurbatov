package DataSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Vadoniy on 09.02.2018.
 */

@Entity
@Table (name = "table_address")
public class AddressDataSet extends DataSet {

    @Column (name = "address")
    private String address;

    public AddressDataSet(){}

    public AddressDataSet(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void AddressDataSet(String address){
        this.address = address;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "address='" + address + '\'' +
                '}';
    }
}
