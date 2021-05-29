package DBMS;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SignUp {
    public static boolean signUp(String username, String password) throws Exception{
        File accountFile = new File("src/Account/account.txt");
        Scanner accountScanner = new Scanner(accountFile);
        ArrayList<String> accountList = new ArrayList<>();
        while (accountScanner.hasNextLine()){
            accountList.add(accountScanner.nextLine());
        }
        String[][] accountSplit = new String[accountList.size()][2];
        for (int i=0; i<accountList.size(); i++){
            accountSplit[i] = accountList.get(i).split(" ");
        }
        for(String[] accountName:accountSplit){
            if(accountName[0].equals(username))
                return false;
        }
        FileWriter accountWriter = new FileWriter(accountFile, true);
        accountWriter.write(username + " " + password + "\n");
        accountWriter.close();
        return true;
    }
}
