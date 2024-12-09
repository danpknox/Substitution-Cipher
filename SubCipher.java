import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class SubCipher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // Create an ArrayList of alphabet in order
        ArrayList<Character> plaintext = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            plaintext.add(ch);
        }

        // print plaintext
        System.out.println("plaintext: " + plaintext);

        // create array for key, at first contains plaintext
        ArrayList<Character> key = new ArrayList<>(plaintext);

        // shuffle to randomize key
        Collections.shuffle(key);

        // Print key
        System.out.println("key list: " + key);
        
        //prompt user for message
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter message: ");
        String message = scan.next();
        System.out.println("Message: " + message);
        
        //encryption process - for loop to run through each letter of message
        
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i<message.length(); i++) {
        	//find what number in the alphabet each letter is
        	int n = plaintext.indexOf(message.charAt(i));
        	//System.out.println(n);
        	char m = key.get(n);
        	//System.out.println(m);
        	encrypted.append(m);
        }
        System.out.println("Encrypted message: " + encrypted);
        
        //decryption
        System.out.println("Enter encrypted message to be decoded: ");
        String encryptedM = scan.next();
        System.out.println("Encrypted Message: " + encryptedM);
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i<encryptedM.length(); i++) {
        	int n = key.indexOf(encryptedM.charAt(i));
        	//System.out.println(n);
        	char m = plaintext.get(n);
        	//System.out.println(m);
        	decrypted.append(m);
        }
        System.out.println("Decrypted Message:" + decrypted);
    }
}
