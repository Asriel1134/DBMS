package DBMS;

import java.io.File;

public class Database {
    public static int createDatabase(String databaseName){
        String pathName = "src/Database/" + databaseName;
        File databaseDir = new File(pathName);
        if (databaseDir.exists()){
            return 1;   //Existed
        }else {
            if (databaseDir.mkdir()){
                return 0;   //Success
            }
            else return -1;     //Error
        }
    }

    public static int dropDatabase(String databaseName){
        String pathName = "src/Database/" + databaseName;
        File databaseDir = new File(pathName);
        if (!databaseDir.exists()){
            return 1;   //Not Existed
        }else {
            if (databaseDir.delete()){
                return 0;   //Success
            }
            else return -1;     //Error ; not Null
        }
    }
}
