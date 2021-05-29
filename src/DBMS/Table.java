package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Table {
    public static int createTable(String databaseName, String tableName, RecordManager recordManager) throws Exception {
        String pathName = "src/Database/" + databaseName + "/" + tableName + ".dbf";
        File newTable = new File(pathName);
        if (newTable.exists())
            return 1;   //Existed

        else {
            if (newTable.createNewFile()) {
                RecordManager.writeRecordManager(newTable, recordManager);
                return 0;   //Success
            }

            else return -1;     //Error
        }
    }

    public static int insert(String[] attributeNames, String[] value, String databaseName, String tableName) throws Exception{
        File table = new File("src/Database/" + databaseName + "/" + tableName + ".dbf");
        if (!table.exists())
            return -1;
        FileWriter valueWriter = new FileWriter(table, true);
        RecordManager recordManager = RecordManager.readRecordManager(table);
        ArrayList<String> attributeNamesList = new ArrayList<>(Arrays.asList(attributeNames));
        for (String[] attributeInfo:recordManager.schema){
            if (attributeNamesList.contains(attributeInfo[0])){
                valueWriter.write(value[attributeNamesList.indexOf(attributeInfo[0])]);
            }else {
                valueWriter.write("NULL");
            }
            valueWriter.write(" ");
        }
        valueWriter.write("\n");
        valueWriter.close();
        return 0;
    }
}
