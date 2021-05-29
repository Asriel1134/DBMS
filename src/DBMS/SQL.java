package DBMS;

public class SQL {
    public static String SQLHandler(String SQL, String database) throws Exception{
        if (SQL.equals("create database student;")){
            String databaseName = "student";
            int flag = Database.createDatabase(databaseName);
            if (flag == 1)
                return "Database " + databaseName + " has existed.";
            else if (flag == 0)
                return "Database " + databaseName + " created successfully.";
            else
                return "Create Database " + databaseName + "failed.";
        }

        if (SQL.equals("drop database student;")){
            String databaseName = "student";
            int flag = Database.dropDatabase(databaseName);
            if (flag == 1)
                return "Database " + databaseName + " does not exist.";
            else if (flag == 0)
                return "Database " + databaseName + " dropped successfully.";
            else
                return "Drop Database " + databaseName + "failed.";
        }

        if (SQL.equals("create table stud(id int(10),name char(10),age int(10));")){
            if (database == null)
                return "Please select a database.";

            String tableName = "stud";
            String[][] schema = {
                    {"id", "int", "10"},
                    {"name", "char", "10"},
                    {"age", "int", "10"}
            };
            RecordManager recordManager = new RecordManager(schema);
            int flag = Table.createTable(database, tableName, recordManager);
            if (flag == 1)
                return "Table " + tableName + " has existed.";
            else if (flag == 0)
                return "Table " + tableName + " created successfully.";
            else
                return "Create Table " + tableName + "failed.";
        }

        if (SQL.equals("insert into stud(id,name,age)values('1','刘同学','18');")){
            if (database == null)
                return "Please select a database.";

            String[] attributeNames = {"id", "name", "age"};
            String[] value = {"1", "刘同学", "18"};
            String tableName = "stud";
            int flag = Table.insert(attributeNames, value, database, tableName);
            if (flag == -1)
                return "Table " + tableName + " does not existed.";
            else if (flag == 0)
                return "Insert successfully.";
            else
                return "Insert failed.";
        }
        return "Error.";
    }
}
