package DBMS;

import java.io.File;

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

        if (SQL.equals("select * from stud;")){
            if (database == null)
                return "Please select a database.";

            String tableName = "stud";

            String pathName = "src/Database/" + database + "/" + tableName + ".dbf";
            File table = new File(pathName);
            if (!table.exists())
                return "Table " + tableName + " does not exist.";

            Stage.Select.Select select = new Stage.Select.Select(Select.selectAll(database ,tableName));
            return "Select successfully.";
        }

        if (SQL.equals("select name from stud where field='id' and value='1';")){
            if (database == null)
                return "Please select a database.";

            String tableName = "stud";

            String pathName = "src/Database/" + database + "/" + tableName + ".dbf";
            File table = new File(pathName);
            if (!table.exists())
                return "Table " + tableName + " does not exist.";

            String[][] requirement = {
                    {"id", "1"},                //可以有多个条件
            };
            String[] attribute = {"name"};      //可以获取多个属性  若 attribute = {"*"} 则获取所有属性

            Stage.Select.Select select = new Stage.Select.Select(Select.select(database, tableName, requirement, attribute));
            return "Select successfully.";
        }

        if (SQL.equals("update stud set value='李同学' where field='name' and value='刘同学';")){
            if (database == null)
                return "Please select a database.";

            String tableName = "stud";
            String oldValue = "刘同学";
            String newValue = "李同学";
            String attribute = "name";

            int flag = Update.update(database, tableName, attribute, oldValue, newValue);
            if (flag == -1)
                return "Table " + tableName + " does not existed.";
            else if (flag == 0)
                return "Update successfully.";
            else
                return "Update failed.";
        }

        if (SQL.equals("delete from stud where field='id' and value='1';")){
            if (database == null)
                return "Please select a database.";

            String tableName = "stud";
            String value = "1";
            String attribute = "id";

            int flag = Delete.delete(database, tableName, attribute, value);
            if (flag == -1)
                return "Table " + tableName + " does not existed.";
            else if (flag == 0)
                return "Update successfully.";
            else
                return "Update failed.";
        }

        return "Error.";
    }
}
