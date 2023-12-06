import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BaseDeDados {

    public int[] readDataFromFile(String fileName) {
        File file = new File(fileName);
        int[] data = null;

        try (Scanner scanner = new Scanner(file)) {
            int size = scanner.nextInt();
            data = new int[size];

            for (int i = 0; i < size; i++) {
                data[i] = scanner.nextInt();
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
        }

        return data;
    }
}
