import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Generator lang = new Generator();
        System.out.println("\n\n");
        try {
            Scanner scanner = new Scanner(new File("bin/corpus/corpus.txt"));
			while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                lang.readWord(word);
			}
            scanner.close();
        } 
        catch (FileNotFoundException e) {
			e.printStackTrace();
        }
        
        for(int i = 0; i <= 100; i++){
            System.out.println(lang.generateWord());
        }
    }
}