import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        SyllableGenerator lang = new SyllableGenerator();
        System.out.println("\n\n");
        try {
            Scanner scanner = new Scanner(new File("bin/corpus/words_alpha.txt"));
			while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                lang.read(word);
			}
            scanner.close();
        } 
        catch (FileNotFoundException e) {
			e.printStackTrace();
        }
        
        for(int i = 0; i <= 100; i++){
            System.out.println(lang.generate());
        }
    }
}