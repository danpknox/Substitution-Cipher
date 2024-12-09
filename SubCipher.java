import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        //cipher-text only attack
        String inputFile = "inputText.txt";  // Input file with large text
        String outputFile = "encryptedText.txt";  // Output file for encrypted text

        try {
            // Read the original text from the file
            String originalText = readFile(inputFile).toLowerCase();

            // Encrypt the text from the file
            String encryptedText = encryptText(originalText, plaintext, key);

            // Write the encrypted text to a new file
            writeFile(outputFile, encryptedText);

            // Calculate frequency of each letter in the original and encrypted texts
            Map<Character, Double> originalFrequency = calculateFrequencyPercentage(originalText, plaintext);
            Map<Character, Double> encryptedFrequency = calculateFrequencyPercentage(encryptedText, plaintext);

            // Print the frequency percentages for both texts
            System.out.println("Original text letter frequency: " + originalFrequency);
            System.out.println("Encrypted text letter frequency: " + encryptedFrequency);

        } catch (IOException e) {
            System.out.println("Error reading or writing files: " + e.getMessage());
        }
    }

    // Function to read the content of a text file
    public static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    // Function to write text to a file
    public static void writeFile(String filePath, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(content);
        writer.close();
    }

    // Function to encrypt the given text
    public static String encryptText(String text, List<Character> plaintext, List<Character> key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (plaintext.contains(currentChar)) {
                int index = plaintext.indexOf(currentChar);
                encryptedText.append(key.get(index));
            } else {
                // Preserve non-letter characters (spaces, punctuation)
                encryptedText.append(currentChar);
            }
        }
        return encryptedText.toString();
    }

    // Function to decrypt the given text
    public static String decryptText(String text, List<Character> plaintext, List<Character> key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (key.contains(currentChar)) {
                int index = key.indexOf(currentChar);
                decryptedText.append(plaintext.get(index));
            } else {
                // Preserve non-letter characters (spaces, punctuation)
                decryptedText.append(currentChar);
            }
        }
        return decryptedText.toString();
    }
}
