package DBMS;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Select {
    public static String[][] selectAll(String databaseName, String tableName) throws Exception{
        String pathName = "src/Database/" + databaseName + "/" + tableName + ".dbf";
        File table = new File(pathName);
        RecordManager recordManager = RecordManager.readRecordManager(table);
        ArrayList<String[]> result = new ArrayList<>();
        String[] resultAttributes = new String[recordManager.schema.length];
        for (int i=0; i<recordManager.schema.length; i++){
            resultAttributes[i] = recordManager.schema[i][0];
        }
        result.add(resultAttributes);

        Scanner tableScanner = new Scanner(table);
        ArrayList<String> tableLine = new ArrayList<>();
        while (tableScanner.hasNextLine()){
            tableLine.add(tableScanner.nextLine());
        }
        tableLine.remove(0);
        for (String line:tableLine){
            result.add(line.split(" "));
        }

        String[][] output = new String[result.size()][];
        for (int i=0; i<result.size(); i++){
            output[i] = result.get(i);
        }

        return output;
    }

    public static String[][] select(String databaseName, String tableName, String[][] requirement, String[] attributes) throws Exception{
        String[][] table = selectAll(databaseName, tableName);
        String[] tableAttribute = table[0];
        String[][] tableData = new String[table.length-1][table[0].length];
        System.arraycopy(table, 1, tableData, 0, table.length - 1);

        for (String[] require : requirement) {
            int index = 0;
            ArrayList<String[]> result = new ArrayList<>();

            for (int j = 0; j < tableAttribute.length; j++) {                   //得到属性位置
                if (tableAttribute[j].equals(require[0])) {
                    index = j;
                    break;
                }
            }

            for (String[] tableDatum : tableData) {                             //得到符合条件的数据
                if (tableDatum[index].equals(require[1])) {
                    result.add(tableDatum);
                }
            }

            String[][] data = new String[result.size()][];                      //转为String[][]
            for (int j = 0; j < result.size(); j++) {
                data[j] = result.get(j);
            }

            tableData = data;
        }

        if (!attributes[0].equals("*")){
            String[] newTableAttribute = new String[attributes.length];
            String[][] newTableData = new String[tableData.length][attributes.length];

            for (int i=0; i< attributes.length; i++){
                int index = 0;

                for (int j = 0; j < tableAttribute.length; j++) {                   //得到属性位置
                    if (tableAttribute[j].equals(attributes[i])) {
                        index = j;
                        break;
                    }
                }

                newTableAttribute[i] = tableAttribute[index];
                for (int j=0; j< tableData.length; j++){
                    newTableData[j][i] = tableData[j][index];
                }
            }

            tableAttribute = newTableAttribute;
            tableData = newTableData;
        }

        String[][] result = new String[tableData.length+1][];                  //拼接表头
        result[0] = tableAttribute;
        System.arraycopy(tableData, 0, result, 1, tableData.length);

        return result;
    }
}
