package DBMS;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SignIn {
    public static boolean signIn(String username, String password) throws Exception{
        Scanner accountScanner = new Scanner(new File("src/Account/account.txt"));
        ArrayList<String> accountList = new ArrayList<>();
        while (accountScanner.hasNextLine()){
            accountList.add(accountScanner.nextLine());
        }
        return accountList.contains(username + " " + password);
    }
}
