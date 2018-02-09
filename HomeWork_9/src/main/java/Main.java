import DataBase.*;
import Executor.Executor;

import java.sql.Connection;
import java.util.List;

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
        UserDataSet uds1 = new UserDataSet("Vadim", 28);
        UserDataSet uds2 = new UserDataSet("NeVadim", 29);
        UserDataSet uds3 = new UserDataSet("SovsemNeVadim", 30);
        UserDataSet uds4 = new UserDataSet("TozheNeVadim", 27);

        try (DBService dbService = new DBServiceImpl()){
            dbService.prepareTables();
            System.out.println(dbService.getMetaData());
            dbService.save(uds1);
            dbService.save(uds2);
            dbService.save(uds3);
            dbService.save(uds4);

            System.out.println(SEPARATOR_LINE);
            System.out.println("Get some rows by id:\n");
            System.out.println(dbService.load(1, UserDataSet.class));
            System.out.println(dbService.load(4, UserDataSet.class));
            System.out.println(SEPARATOR_LINE);

            System.out.println("Get all rows from table:\n");

            List<UserDataSet> userDataSetList = dbService.getAllUsers(UserDataSet.class);

            for (DataSet uds : userDataSetList) {
                System.out.println(uds.toString());
            }

            dbService.dropTables();
        }
    }
}
