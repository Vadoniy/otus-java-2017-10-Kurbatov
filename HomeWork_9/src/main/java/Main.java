import DataBase.ConnectionHelper;
import DataBase.DBService;
import DataBase.DBServiceImpl;
import DataBase.UserDataSet;
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
            Connection connection = ConnectionHelper.getConnection();
            dbService.prepareTables();
            System.out.println(dbService.getMetaData());
            Executor executor = new Executor(connection);
            executor.save(uds1);
            executor.save(uds2);
            executor.save(uds3);
            executor.save(uds4);
            System.out.println(SEPARATOR_LINE);
            System.out.println("Get some rows by id:\n");
            System.out.println(executor.load(1, UserDataSet.class));
            System.out.println(executor.load(4, UserDataSet.class));
            System.out.println(SEPARATOR_LINE);

            System.out.println("Get all rows from table:\n");
            List<UserDataSet> userDataSetList = dbService.getAllUsers();

            for (UserDataSet uds : userDataSetList) {
                System.out.println(uds.toString());
            }

            System.out.println(SEPARATOR_LINE);

            dbService.dropTables();
        }
    }
}
