import java.util.Scanner;

public class Program {
    private static final int    UNIQ_CHAR_COUNT     = 65536;
    private static final int    CHOSEN_CHAR_COUNT   = 10;
    private static final int    HISTOGRAM_TOP_LEVEL = 10;

    private static void     sortArr(String line, int[][] resArr) {
        char[]  charArr = line.toCharArray();
        char[]  uniqCharArr = new char[UNIQ_CHAR_COUNT];
        int     max[] = new int[2];

        for (char c: charArr) {
            uniqCharArr[c] += 1;
        }
        for (int i = 0; i < CHOSEN_CHAR_COUNT; i++) {
            max[0] = 0;
            for (int j = 0; j < UNIQ_CHAR_COUNT; j++) {
                if (uniqCharArr[j] > max[0]) {
                    max[0] = uniqCharArr[j];
                    max[1] = j;
                }
            }
            if (max[0] == 0) {
                break ;
            }
            uniqCharArr[max[1]] = 0;
            resArr[0][i] = max[0];
            resArr[1][i] = max[1];
        }
    }

    public static void      printHistogram(int[][] chosenCharList) {
        int     max = 0;

        for (int i = 0; i < chosenCharList.length; i++) {
            if (chosenCharList[0][i] > max)
                max = chosenCharList[0][i];
        }
        for (int level = HISTOGRAM_TOP_LEVEL; level >= -1; --level) {
            for (int i = 0; i < CHOSEN_CHAR_COUNT && chosenCharList[0][i] > 0; ++i) {
                if (level == -1)
                    System.out.printf("%3c", (char)chosenCharList[1][i]);
                else if (level == HISTOGRAM_TOP_LEVEL * chosenCharList[0][i] / max)
                    System.out.printf("%3d", chosenCharList[0][i]);
                else if (level < HISTOGRAM_TOP_LEVEL * chosenCharList[0][i] / max)
                    System.out.printf("%3c", '#');
                else
                    System.out.printf("%c", ' ');
            }
            System.out.println();
        }   
    }

    public static void      main(String[] args) {
        Scanner     scan = new Scanner(System.in);
        int[][]     chosenCharList = new int[2][CHOSEN_CHAR_COUNT];
        sortArr(scan.nextLine(), chosenCharList);
        printHistogram(chosenCharList);
    }
}
