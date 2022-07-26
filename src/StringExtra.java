import java.util.Arrays;


public class StringExtra {
    public static void main(String[] args) {
        String line = "1On1ce up341on a time a Wolf was lapping at" + " a spring on a hillside,when,looking up,what should he" +
                " see but a Lamb just beginning to drink a little lower down";
        String lineString=lineToString(line);
        String[] words = stringToArrayWords(lineString);
        String[] ABC = priceWords(words);
        String[] stringToAbc = stringToAbc(words, ABC);
        for (int i = 0; i < stringToAbc.length; i++) {
            System.out.println(stringToAbc[i]);
        }
    }

    public static  String lineToString(String line){
        return  line.replaceAll("\\d", "");
    }
    public static String stringToLow(String line) {

        return line.toLowerCase().replaceAll("[^a-zA-Z]", " ").replaceAll("\\s++", " ");
    }

    public static String[] stringToArrayWords(String lineNew) {
        return stringToLow(lineNew).split("\\s");
    }

    public static String[] priceWords(String[] words) {
        String[] price = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            String numb = "" + words[i].charAt(0);
            price[i] = numb;

        }
        Arrays.sort(price);
        String[] abc = new String[words.length];
        int count = 0;
        for (int j = 0; j < price.length; j++)
            for (int k = 0; k < price.length; k++) {
                if (j == k) {
                    abc[count] = price[j];
                    count++;
                }
                if ((j != k) & (price[j].equals(price[k])))
                    break;
            }
        return Arrays.copyOf(abc, count);
    }

    public static String[] stringToAbc(String[] words, String[] ABC) {
        Arrays.sort(words);
        String[] duplicateReplace = new String[words.length];
        int[] countWords = new int[words.length];
        int coun = 1;
        for (int p = coun - 1; p < words.length; p++) {
            for (int l = coun - 1; l < words.length; l++) {
                if (words[p].equals(words[l])) {
                    countWords[p]++;
                }
            }
            coun += countWords[p];
        }
        int count = 0;
        for (int j = 0; j < words.length; j++) {
            for (int k = 0; k < words.length; k++) {
                if (j == k) {
                    duplicateReplace[count] = words[j];
                    count++;
                }
                if ((j != k) & (words[j].equals(words[k])))
                    break;
            }
        }
        duplicateReplace = Arrays.copyOf(duplicateReplace, count);
        int[] countWordsRem = new int[count];
        int x = 0;
        for (int m = 0; m < countWords.length; m++) {
            if (countWords[m] != 0) {
                countWordsRem[x] = countWords[m];
                x++;
            }
        }
        String[] lineRep = new String[ABC.length];
        int s = 0;
        for (int i = 0; i < ABC.length; i++) {
            lineRep[i] = ABC[i].toUpperCase() + ": \n";
            while (s < count) {
                if (duplicateReplace[s].charAt(0) == (ABC[i].charAt(0))) {
                    lineRep[i] += duplicateReplace[s] + " " + countWordsRem[s] + "\n";
                    s++;
                } else break;
            }
        }
        return lineRep;
    }

}
