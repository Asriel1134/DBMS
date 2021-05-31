package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Delete {
    public static int delete(String databaseName, String tableName, String attribute, String value) throws Exception{
        String pathName = "src/Database/" + databaseName + "/" + tableName + ".dbf";
        File tableFile = new File(pathName);

        if (!tableFile.exists()){
            return -1;
        }

        Scanner tableScanner = new Scanner(tableFile);
        ArrayList<String> tableList = new ArrayList<>();
        while (tableScanner.hasNextLine()){
            tableList.add(tableScanner.nextLine());
        }
        String[][] tableArray = Select.selectAll(databaseName, tableName);

        int index = 0;
        for (int i = 0; i < tableArray[0].length; i++) {                   //得到属性位置
            if (tableArray[0][i].equals(attribute)) {
                index = i;
                break;
            }
        }

        int count = 0;
        for (int i=1; i<tableArray.length; i++){
            if (tableArray[i][index].equals(value)) {
                    tableList.remove(i - count);
                    count += 1;
            }
        }

        FileWriter tableWriter = new FileWriter(tableFile);
        for (String line:tableList){
            tableWriter.write(line);
            tableWriter.write("\n");
        }
        tableWriter.close();

        return 0;
    }
}
