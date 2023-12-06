import java.util.Arrays;

public class TimSort {

    private static final String TEMPO = "Tempo";

    public static void main(String[] args) {
        BaseDeDados baseDeDados = new BaseDeDados();
        int[] data = baseDeDados.readDataFromFile("dados100_mil.txt");

        long start = System.currentTimeMillis();

        timSort(data);

        long end = System.currentTimeMillis();
        imprimirTempo(end - start);
    }

    public static void timSort(int[] data) {
        int minRun = 32;
        for (int i = 0; i < data.length; i += minRun) {
            int lo = i;
            int hi = Math.min(i + minRun - 1, data.length - 1);
            insertionSort(data, lo, hi);
        }

        for (int i = minRun; i < data.length; i *= 2) {
            for (int lo = 0; lo < data.length - i; lo += 2 * i) {
                int mid = lo + i;
                int hi = Math.min(lo + 2 * i - 1, data.length - 1);

                merge(data, lo, mid, hi);
            }
        }
    }

    private static void merge(int[] data, int lo, int mid, int hi) {
        int[] left = Arrays.copyOfRange(data, lo, mid + 1);
        int[] right = Arrays.copyOfRange(data, mid + 1, hi + 1);

        int i = 0;
        int j = 0;
        int k = lo;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                data[k++] = left[i++];
            } else {
                data[k++] = right[j++];
            }
        }

        while (i < left.length) {
            data[k++] = left[i++];
        }

        while (j < right.length) {
            data[k++] = right[j++];
        }
    }

    private static void insertionSort(int[] data, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = data[i];
            int j = i - 1;

            while (j >= lo && data[j] > key) {
                data[j + 1] = data[j];
                j--;
            }

            data[j + 1] = key;
        }
    }

    private static void imprimirTempo(long duracao) {
        long milisegundos = (duracao % 1000) / 100;
        long segundos = (duracao / 1000) % 60;
        long minutos = (duracao / (1000 * 60)) % 60;
        long horas = (duracao / (1000 * 60 * 60)) % 24;
        String tempo = String.format("%02d:%02d:%02d:%02d", horas, minutos, segundos, milisegundos);
        System.out.println(TEMPO + ": " + tempo);
    }
}
