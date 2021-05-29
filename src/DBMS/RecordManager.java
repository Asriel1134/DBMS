package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class RecordManager {
    public String[][] schema;
    public int[] layout;

    public RecordManager(String[][] schema){
        this.schema = schema;       //Schema[] Attributes   Schema[][0] AttributeName   Schema[][1] DataType of Attribute   Schema[0][2] Length of Attribute
        layout = new int[schema.length + 1];
        int location = 0;
        for(int i=1; i< layout.length; i++){
            layout[i] = location;
            location += Integer.parseInt(schema[i-1][2]);
        }
        layout[0] = location;
    }

    public static void writeRecordManager(File file, RecordManager recordManager) throws Exception{
        FileWriter recordManagerWriter = new FileWriter(file, true);
        recordManagerWriter.write(recordManager.schema.length + " ");
        for (String[] Attribute:recordManager.schema)
            for (String AttributeInfo:Attribute){
                recordManagerWriter.write(AttributeInfo + " ");
            }
        recordManagerWriter.write("\n");
        recordManagerWriter.close();
    }

    public static RecordManager readRecordManager(File file) throws Exception{
        Scanner recordManagerScanner = new Scanner(file);
        int size = recordManagerScanner.nextInt();
        String[][] schema = new String[size][3];
        for (int i=0; i<size; i++) {
            for (int j = 0; j < 3; j++) {
                schema[i][j] = recordManagerScanner.next();
            }
        }
        return new RecordManager(schema);
    }
}
