import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Program {
    private static double   calculateSimilarityLevel(HashMap<String, Integer> map1,
                                                        HashMap<String, Integer> map2,
                                                        HashSet<String> set) {
        double      simLevel = 0;
        long        nmrt = 0;
        double      dnmrt1 = 0;
        double      dnmrt2 = 0;
        Integer     cnt1;
        Integer     cnt2;
        for (String dic : set) {
            cnt1 = map1.containsKey(dic) ? map1.get(dic) : 0;
            cnt2 = map2.containsKey(dic) ? map2.get(dic) : 0;
            nmrt += cnt1 * cnt2;
            dnmrt1 += cnt1 * cnt1;
            dnmrt2 += cnt2 * cnt2;
        }
        try {
            simLevel = nmrt / (Math.sqrt(dnmrt1) * Math.sqrt(dnmrt2));
        } catch (ArithmeticException e) {
            e.getMessage();
        }
        return (simLevel);
    }

    private static void     fillSetAndMaps(String[] strings,
                                            HashMap<String, Integer> map,
                                            HashSet<String> set) {
        for (String key : strings) {
            map.compute(key, (k, v) -> (v == null) ?  1 : v + 1);
            set.add(key);
        }
    }

    private static void     parseFile(HashMap<String, Integer> map1,
                                        HashMap<String, Integer> map2,
                                        HashSet<String> set,
                                        String[] arr) {
       try {
            BufferedReader reader1 = new BufferedReader(new FileReader(arr[0]));
            BufferedReader reader2 = new BufferedReader(new FileReader(arr[1]));
            String line;
            while((line = reader1.readLine()) != null)
                fillSetAndMaps(line.split("\\s+"), map1, set);
            while((line = reader2.readLine()) != null)
                fillSetAndMaps(line.split(" "), map2, set);
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
    }

    public static void      main(String[] args) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        if (args.length != 2) {
            System.err.println("Incorrect arguments!\nEx.: java Program fileName1 fileName2");
            System.exit(-1);
        }
        parseFile(map1, map2, set, args);
        if (map1.size() == 0 && map2.size() == 0) {
            System.out.printf("%.3f\n", 1.0);
        } else if(map1.size() == 0 || map2.size() == 0) {
            System.out.printf("%.3f\n", 0.0);
        } else {
            System.out.printf("%.3f\n", calculateSimilarityLevel(map1, map2, set));
        }
    }
}
