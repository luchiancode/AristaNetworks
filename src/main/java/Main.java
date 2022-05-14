import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        {
            TreeMap<String, Pair<Integer, List<Integer>>> concordance = new TreeMap<>();

            try {
                FileInputStream fis = new FileInputStream("src/main/input.txt");
                Scanner sc = new Scanner(fis);

                int lineCount = 1;
                while (sc.hasNextLine()) {

                    String[] response = sc.nextLine().split("\\s+|(?=\\W\\p{Punct}|\\p{Punct}\\W)|(?<=\\W\\p{Punct}|\\p{Punct}\\W})");
                    for (String word : response){
                        if(!word.equals(".")  && !word.equals(",") && !word.equals(" ") && !word.equals(":") && !word.equals("")){
                            String s = word.toLowerCase();
                            if(concordance.get(s) != null) {
                                List<Integer> list = new ArrayList<>();
                                list = concordance.get(s).getValue();
                                list.add(lineCount);
                                Pair<Integer, List<Integer>> pair = new Pair<Integer, List<Integer>>(concordance.get(s).getKey() + 1, list);
                                concordance.put(s, pair);
                            }
                            else {
                                List<Integer> list = new ArrayList<>();
                                list.add(lineCount);
                                Pair<Integer, List<Integer>> pair = new Pair<Integer, List<Integer>>(1, list);
                                concordance.put(s, pair);
                            }
                        }
                    }
                    lineCount ++;

                }
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int line = 0;
            for (Map.Entry<String, Pair<Integer, List<Integer>>> entry : concordance.entrySet()){
                System.out.println(str(line) + ". " + entry.getKey() + " {" + entry.getValue().getKey() + ":" + Arrays.toString(entry.getValue().getValue().toArray()).replace("[", "").replace("]", "") + "}");
                line++;
            }
        }
    }

    static String str(int i) {
        return i < 26 ? String.valueOf((char)(97 + i % 26)) : String.valueOf((char)(97 + i % 26) + String.valueOf((char)(97 + i % 26)));
    }
}