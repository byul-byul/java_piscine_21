import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {

    private static final String     inputFuleName = "signatures.txt";
    private static final String     outputFuleName = "result.txt";
    private static final String     successResMsg = "PROCESSED";
    private static final String     failResMsg = "UNDEFINED";

    private static String   defineFileSignature(String fPath, int bytesCount) throws IOException {
        StringBuilder   strBld = new StringBuilder();
        try (InputStream inFile = new FileInputStream(fPath)) {
            for (int i = 0; i < bytesCount; i++) {
                int oneByte = inFile.read();
                strBld.append(Character.forDigit((oneByte >> 4) & 0xF, 16));
                strBld.append(Character.forDigit((oneByte) & 0xF, 16));
            }
        } catch (IOException e) {
            throw e;
        }
        return (strBld.toString().toUpperCase());
    }

    private static String   getFileSignatureAndWriteToOutFile(String fPath, ArrayList<String[]> signs, String outFileName) {
        try (OutputStream outStream = new FileOutputStream(outFileName, true)) {
            for (String[] arr : signs) {
                String fileSignature = defineFileSignature(fPath, arr[0].length() / 2);
                if (arr[0].equals(fileSignature)) {
                    for (int i = 0; i < arr[1].length(); i++) {
                        outStream.write(arr[1].charAt(i));
                    }
                    outStream.write((int)'\n');
                    return (successResMsg);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return (failResMsg);
    }

    private static void     collectSignaturesFromFile(ArrayList<String[]> signList, String inFileName) {
        StringBuilder       strBld = new StringBuilder();

        try (InputStream inStream = new FileInputStream(inFileName)) {
            while (inStream.available() > 0) {
                char chr = (char)inStream.read();
                if (chr == '\n') {
                    int commaIndex = strBld.indexOf(",");
                    signList.add(new String[]{strBld.substring(commaIndex + 2).replace(" ", ""), strBld.substring(0, commaIndex)});
                    strBld.setLength(0);
                }
                else {
                    strBld.append(chr);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void      main(String[] args) {

        String              filePath;
        Scanner             scan = new Scanner(System.in);
        ArrayList<String[]> signatureList = new ArrayList<>();

        collectSignaturesFromFile(signatureList, inputFuleName);
        while (!(filePath = scan.next()).equals("42")) {
            System.out.println(getFileSignatureAndWriteToOutFile(filePath, signatureList, outputFuleName));
        }
        scan.close();
    }
}
