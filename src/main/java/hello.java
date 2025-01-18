import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class hello {

    static int someProblemInput;
    static int[] anotherProblemInput;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        // Lire le contenu du fichier et l'afficher
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            System.out.println(line);
        }
        reader.close();


        solve();
    }

    static void solve() {
        // the code that solved the problem
        // ...
        // print the output
        System.out.println("The answer is 42");
    }



}