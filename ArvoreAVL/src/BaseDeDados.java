import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class BaseDeDados {
    private static final String FILE_NAME_100K = "dados100_mil.txt";

    public int[] choose(int qtdNumeros) {
        if (qtdNumeros != 100000) {
            System.err.println("Quantidade de números não suportada: " + qtdNumeros);
            return null;
        }

        File file = new File(FILE_NAME_100K);

        try (Scanner scanner = new Scanner(file)) {
            String[] lines = scanner.nextLine().split(", ");
            lines[0] = lines[0].split("\\[")[1];
            lines[lines.length - 1] = lines[lines.length - 1].split("\\]")[0];

            int[] integers = new int[lines.length];
            for (int i = 0; i < lines.length; i++) {
                integers[i] = Integer.parseInt(lines[i]);
            }

            return integers;

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
            return null;
        }
    }
}
