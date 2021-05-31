package DBMS;


public class Test {
    public static void main(String[] args) throws Exception{
        SQLHandler("delete from stud where field='id' and value='1';", "student");
    }

    public static String SQLArrange(String sentence) {
        String ws = sentence.substring(0, sentence.lastIndexOf(";"));                     //去掉;
        String qs = ws.trim();                                                              //删除头尾空格
        String rs = qs.toLowerCase();                                                       //转小写
        String ts = rs.replaceAll("\\s{2,}", " ");                                        //		去掉多余的空格
        return ts.replaceFirst("( ;)$", ";");
    }

    public static String SQLHandler(String SQL, String databaseName) throws Exception{                                                   //第一个单词
        String SQLArranged=SQLArrange(SQL);
        String[] SQLSplit = SQLArranged.split("\\s+");
        return switch (SQLSplit[0]) {
            case "create" -> createHandler(SQLSplit, databaseName);
            case "drop" -> dropHandler(SQLSplit, databaseName);
            case "insert" -> insertHandler(SQLSplit, databaseName);
            case "select" -> selectHandler(SQLSplit, databaseName);
            case "update" -> updateHandler(SQLSplit, databaseName);
            case "delete" -> deleteHandler(SQLSplit, databaseName);
            default -> "Error.";
        };
    }

    public static String createHandler(String[] SQLSplit, String databaseName) throws Exception{                                //创建//
        switch(SQLSplit[1]) {
            case "database":
                String database = SQLSplit[2];
                int flag = Database.createDatabase(database);
                if (flag == 1)
                    return "Database " + databaseName + " has existed.";
                else if (flag == 0)
                    return "Database " + databaseName + " created successfully.";
                else
                    return "Create Database " + databaseName + "failed.";

            case "table":
                if (databaseName == null)
                    return "Please select a database.";

                StringBuilder noSplit = new StringBuilder();
                for (int i=2; i<SQLSplit.length; i++){
                    noSplit.append(SQLSplit[i]);
                    noSplit.append(" ");
                }
                SQLSplit[2] = String.valueOf(noSplit);

                String tableName = SQLSplit[2].substring(0, SQLSplit[2].indexOf('('));

                String tableFormat = SQLSplit[2].substring(SQLSplit[2].indexOf('(') + 1, SQLSplit[2].lastIndexOf(')'));
                String[] tableFormatSplit = tableFormat.split(",");
                String[][] schema = new String[tableFormatSplit.length][3];

                for (int i=0; i<tableFormatSplit.length; i++){
                    schema[i][0] = tableFormatSplit[i].split(" ")[0];
                    schema[i][1] = tableFormatSplit[i].split(" ")[1].substring(0, tableFormatSplit[i].split(" ")[1].indexOf('('));
                    schema[i][2] = tableFormatSplit[i].split(" ")[1].substring(tableFormatSplit[i].split(" ")[1].indexOf('(') + 1 , tableFormatSplit[i].split(" ")[1].lastIndexOf(')'));
                }

                RecordManager recordManager = new RecordManager(schema);
                int flag1 = Table.createTable(databaseName, tableName, recordManager);
                if (flag1 == 1)
                    return "Table " + tableName + " has existed.";
                else if (flag1 == 0)
                    return "Table " + tableName + " created successfully.";
                else
                    return "Create Table " + tableName + "failed.";

            default:
                return "Error";
        }
    }

    public static String dropHandler(String[] SQLSplit, String databaseName) throws Exception{                                     //创建//
        switch(SQLSplit[1]) {
            case "database":
                String database = SQLSplit[2];

                int flag = Database.dropDatabase(database);
                if (flag == 1)
                    return "Database " + database + " does not exist.";
                else if (flag == 0)
                    return "Database " + database + " dropped successfully.";
                else
                    return "Drop Database " + database + "failed.";

            case "table":
                String tableName = SQLSplit[2];

                int flag1 = Table.dropTable(databaseName, tableName);
                if (flag1 == 1)
                    return "Table " + tableName + " does not exist.";
                else if (flag1 == 0)
                    return "Table " + tableName + " dropped successfully.";
                else
                    return "Drop Table " + tableName + "failed.";
            default:
                return "Error.";
        }
    }

    public static String insertHandler(String[] SQLSplit, String databaseName) throws Exception{                                     //创建//
        if(SQLSplit[1].equals("into") && SQLSplit[3].equals("values")) {
            String tableName = SQLSplit[2].substring(0, SQLSplit[2].indexOf('('));

            String tableFormat = SQLSplit[2].substring(SQLSplit[2].indexOf('(') + 1, SQLSplit[2].length() - 1);
            String tableValue = SQLSplit[4].substring(SQLSplit[4].indexOf('(') + 1, SQLSplit[4].length() - 1);

            String[] tableFormatSplit = tableFormat.split(",");
            String[] tableValueSplit = tableValue.split(",");

            String[] attributeNames = new String[tableFormatSplit.length];
            String[] value = new String[tableValueSplit.length];

            for (int i=0; i<tableFormatSplit.length; i++){
                attributeNames[i] = tableFormatSplit[i];
                value[i] = tableValueSplit[i].substring(1, tableFormatSplit[i].length());
            }

            int flag = Table.insert(attributeNames, value, databaseName, tableName);
            if (flag == -1)
                return "Table " + tableName + " does not existed.";
            else if (flag == 0)
                return "Insert successfully.";
            else
                return "Insert failed.";
        }
        else return "Error.";
    }

    public static String updateHandler(String[] SQLSplit, String databaseName) throws Exception{                      //更新//
        if(SQLSplit[2].equals("set") && SQLSplit[4].equals("where")) {
            String tableName = SQLSplit[1];
            String newValue = SQLSplit[3].substring(SQLSplit[3].indexOf('\'')+1, SQLSplit[3].length()-1);
            String attribute = SQLSplit[5].substring(SQLSplit[5].indexOf('\'')+1, SQLSplit[5].length()-1);
            String oldValue = SQLSplit[7].substring(SQLSplit[7].indexOf('\'')+1, SQLSplit[7].length()-1);

            int flag = Update.update(databaseName, tableName, attribute, oldValue, newValue);
            if (flag == -1)
                return "Table " + tableName + " does not existed.";
            else if (flag == 0)
                return "Update successfully.";
            else
                return "Update failed.";
        }
        else return "Error.";
    }

    public static String selectHandler(String[] SQLSplit, String databaseName) throws Exception{                      //查询//
        if (SQLSplit[1].equals("*") && SQLSplit[2].equals("from") && SQLSplit.length<5){
            String tableName = SQLSplit[3];
            Stage.Select.Select select = new Stage.Select.Select(Select.selectAll(databaseName, tableName));
            return "Select successfully.";
        }

        else if(SQLSplit[2].equals("from")) {
            String tableName = SQLSplit[3];
            String[] attribute = SQLSplit[1].split(",");
            String[][] requirement = new String[1][2];
            requirement[0][0] = SQLSplit[5].substring(SQLSplit[5].indexOf('\'')+1, SQLSplit[5].length()-1);
            requirement[0][1] = SQLSplit[7].substring(SQLSplit[7].indexOf('\'')+1, SQLSplit[7].length()-1);

            Stage.Select.Select select = new Stage.Select.Select(Select.select(databaseName, tableName, requirement, attribute));
            return "Select successfully.";
        }
        else return "Error";
    }

    public static String deleteHandler(String[] SQLSplit, String databaseName) throws Exception{                       //删除//
        if(SQLSplit[1].equals("from") && SQLSplit[3].equals("where")) {
            String tableName = SQLSplit[2];
            String attribute = SQLSplit[4].substring(SQLSplit[4].indexOf('\'')+1, SQLSplit[4].length()-1);
            String value = SQLSplit[6].substring(SQLSplit[6].indexOf('\'')+1, SQLSplit[6].length()-1);

            int flag = Delete.delete(databaseName, tableName, attribute, value);
            if (flag == -1)
                return "Table " + tableName + " does not existed.";
            else if (flag == 0)
                return "Update successfully.";
            else
                return "Update failed.";
        }
        else return "Error.";
    }
}
