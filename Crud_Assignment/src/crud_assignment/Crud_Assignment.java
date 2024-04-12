package crud_assignment;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.WriteAbortedException;
import java.io.FileWriter;
import java.util.HashSet;

public class Crud_Assignment {
    
    private static final String FILEPATH = "userData.txt";  //adjust this to change text file

    public static void main(String[] args) {
   
        
        Scanner input = new Scanner(System.in);
        
        
        int choice = 0;
        
       
        
        System.out.println("Welcome User!");
        
       
        System.out.println("\nIf you're a returning user, press 1 to login:");
        System.out.println("If you're a new user, press 2 to create an account:");

        try {
        choice = input.nextInt();
        }
        catch(Exception ex) {
            System.out.println("Input error");
        }

        String user = null;
        String pass = null;
        
        if (choice == 1) {
            System.out.println("\nPlease input your username:");
            
            try {
            user = input.next();
            user = user.trim();
            
            String dummyStr = input.nextLine();
            
            
            
            System.out.println("\nPlease input your password:");
            pass = input.next();
            pass = pass.trim();
            
            }
            catch(Exception ex) {
                System.out.println("Input error");
            }

            
            if (findUser(user, pass)) {
                
                System.out.println("\nYou've successfully logged in!");
                
            }
            
            else {
                System.out.println("Login failed, please try again.");
            }
        }
        
        else if (choice == 2) {
            System.out.println("Please create a username:");

            try {
            user = input.next();
            user = user.trim();
            
            if(!isDuplicate(user)) {
                
            
            
            String dummyStr = input.nextLine();
           
            
            System.out.println("Please create a password:");
            pass = input.next();
            pass = pass.trim();
            
            addUser(user, pass);
            }
            else {
                System.out.println("\nUsername already taken!");
            }
            }
            catch(Exception ex) {
                System.out.println("Input error");
            }

            
            
                   
        }
        
        
        
        
        

    }
    
    private static boolean isDuplicate(String user) {
        
                
        File dataFile = new File(FILEPATH);
        
            try {
                Scanner fileScan = new Scanner(dataFile);
            
                while (fileScan.hasNextLine()) {
                    
                    
                
                    String nextLine = fileScan.nextLine();
                    
                    
                    nextLine = nextLine.trim();
                
                    int commaIndex = -1;
                
                    for (int i = 0; i < nextLine.length(); i++) {
                        if (nextLine.charAt(i) == ',') {
                            commaIndex = i;
                            break;
                        }
                    }
                
                    if (commaIndex != -1) {
                        String thisUser = nextLine.substring(0, commaIndex);

                    
                        if (user.equals(thisUser)) {
                            return true;
                        }
                    }
                }
            }
            catch(FileNotFoundException ex) {
                    System.out.println("\nError, file not found!");
                    }
            
        
        
        
        
        
        
        
            return false;
    }
    
    private static void addUser(String user, String pass) {
        
        File dataFile = new File(FILEPATH);
        
        try { 
          
            FileWriter writer = new FileWriter(dataFile, true);
            
            String userStr = user + ",";
            userStr += pass;
            userStr += "\n";
            
            writer.append(userStr);
            writer.close();
            
        }
        
        catch(Exception ex) {
            
        }
        
    }
    
    private static boolean findUser(String user, String pass) { 
        
             
        File dataFile = new File(FILEPATH);
        
        try {
            Scanner fileScan = new Scanner(dataFile);
            
            while (fileScan.hasNextLine()) {
                
                String nextLine = fileScan.nextLine();
                nextLine = nextLine.trim();
                
                int commaIndex = -1;
                
                for (int i = 0; i < nextLine.length(); i++) {
                    if (nextLine.charAt(i) == ',') {
                        commaIndex = i;
                        break;
                    }
                }
                
                if (commaIndex != -1) {
                    String thisUser = nextLine.substring(0, commaIndex);
                    String thisPass = nextLine.substring(commaIndex+1);

                    
                    if (user.equals(thisUser) && pass.equals(thisPass)) {
                        return true;
                    }
                    
                    else if (user.equals(thisUser)) {
                        System.out.println("\nWrong password!");
                        return false;
                    }
                    

                }
                
            }
            
            System.out.println("\nUsername not found!");
            fileScan.close();
        }
        
        catch(FileNotFoundException ex) {
            System.out.println("Error, File not Found");
        }
        return false;
    }
    
    
}