import DataBase.DBServiceHibernateImpl;
import DataSet.AddressDataSet;
import DataSet.PhoneDataSet;
import DataSet.UserDataSet;

import java.util.Arrays;

public class Main {

    public static final String SEPARATOR_LINE = "_______________________________________________";

    public static void main(String[] args) {
        try {
            new Main().run();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void run() throws Exception {
        DBServiceHibernateImpl dbServiceHibernate = new DBServiceHibernateImpl();
        System.out.println("Local status: " + dbServiceHibernate.getLocalStatus());

        UserDataSet uds1 = new Main().createUser("Vadim Kurbatov", 28, "Moscow, Chertanovo",
                "+7-926-336-88-86", "+7-977-785-58-11");
        UserDataSet uds2 = new Main().createUser("Name Surname", 25, "Moscow, Bibirevo",
                "+7-926-111-11-11", "+7-977-222-22-22", "+7-977-333-33-33");

        dbServiceHibernate.save(uds1);
        dbServiceHibernate.save(uds2);

        System.out.println(SEPARATOR_LINE);
        System.out.println(dbServiceHibernate.load(2));
        System.out.println(SEPARATOR_LINE);
        System.out.println(dbServiceHibernate.getAllUsers());
        System.out.println(SEPARATOR_LINE);
        System.out.println(dbServiceHibernate.readByName("Vadim Kurbatov"));
        System.out.println(SEPARATOR_LINE);
        dbServiceHibernate.shutdown();
    }

    private UserDataSet createUser(String name, int age, String address, String... phone){
        AddressDataSet ads = new AddressDataSet(address);
        UserDataSet uds = new UserDataSet(name, age, ads);

        Arrays.asList(phone).forEach( number -> {
            uds.addPhone(new PhoneDataSet(number));
        });
        return uds;
    }
}
