import java.util.Scanner;
import java.util.Arrays;

class Main {

    private static final int        UNIQ_CHAR_AMOUNT = 65535;
    private static final int        CHOS_CHAR_AMOUNT = 10;

    public static int   getCharPosition(char c, char[] arr, int arrLen) {

        int         i = 0;

        while (i < arrLen) {
            if (c == arr[i]) {
                return (i);
            }
            i++;
        }
        return (-1);
    }

    public static void  insertCharInArr(char c, char[] arr, int pos, int len) {

        char[]      temp = new char[len + 1];
        boolean     isInserted = false;

        for (int i = 0, i <= len, i++) {
            if (i == pos) {
                temp[i] = c;
                isInserted = true;
            }
            else {
                if (isInserted) {
                    temp[i] = arr[i + 1];
                }
                else {
                    temp[i] = arr[i];
                }
            }
        }
    }

    public static int   addCharToArr(char c, char[] arr, int arrLen) {
    
/*        for (int i = 0; i < arrLen; i++) {
            if (c < arr[i]) {
                insertCharInArr(c, arr, i, arrLen);
                return (i);
            }
        }
*/        arr[arrLen] = c;
        return (arrLen + 1);
    }

    public static void  incArrElementValue(int[] arr, int elemPos) {
    
        arr[elemPos] += 1;
    }

    public static void  main(String[] args) {

        Scanner     scan                = new Scanner(System.in);
        String      inputLine           = scan.nextLine();
        long        len                 = inputLine.length();
        char        srcCharArr[]        = inputLine.toCharArray();

        char[]      uniqCharArr         = new char[UNIQ_CHAR_AMOUNT];
        int[]       uniqCharCountArr    = new int[UNIQ_CHAR_AMOUNT];
        int[]       chosenCharArr       = new int[CHOS_CHAR_AMOUNT];
        int         uniqArrLen          = 0;
        int         chosArrLen          = 0;

        int         i                   = 0;

        for (int j = 0; j < UNIQ_CHAR_AMOUNT; j++) {
            uniqCharCountArr[j] = 0;
        }

        while (i < len) {

            int     charPosition;

            charPosition = getCharPosition(srcCharArr[i], uniqCharArr, uniqArrLen);
            if (charPosition < 0) {
                uniqArrLen = addCharToArr(srcCharArr[i], uniqCharArr, uniqArrLen);
                incArrElementValue(uniqCharCountArr, uniqArrLen - 1);
            }
            else {
                incArrElementValue(uniqCharCountArr, charPosition);
            }
            int ppp = getCharPosition(srcCharArr[i], uniqCharArr, uniqArrLen);
            System.out.println(i + " pos:" + ppp + ", char:" + uniqCharArr[ppp] + ", count:" + uniqCharCountArr[ppp]);
            i++;
        }

        //sortArr(chosenCharArr);
        //displayHistogram();
    }
}