package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Update {
    public static int update(String databaseName, String tableName, String attribute, String oldValue, String newValue) throws Exception{
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

        for (int i=1; i<tableArray.length; i++){
            if (tableArray[i][index].equals(oldValue)){
                tableArray[i][index] = newValue;
                StringBuilder newLine = new StringBuilder();
                for (int j=0; j<tableArray[i].length-1; j++){
                    newLine.append(tableArray[i][j]).append(" ");
                }
                newLine.append(tableArray[i][tableArray[i].length-1]);
                tableList.set(i, String.valueOf(newLine));
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
