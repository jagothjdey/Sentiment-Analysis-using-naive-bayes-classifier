package naivebayes;

import java.io.IOException;
import java.util.StringTokenizer;
import stemmer.bengali.BengaliStemmer;

/**
 *
 * @author Sm19
 */
public class Main {

    public static void main(String args[]) throws IOException {

        String posPath = "extarnalFile/positiveNegativeCorpora/positive1.txt";
        String negPath = "extarnalFile/positiveNegativeCorpora/negative1.txt";
        NaiveBayes naiveBayes = new NaiveBayes(posPath, negPath);
        naiveBayes.loadData();
        
        String queryStr = "# আমরা বাংগালীরা কাজকে মুল্যায়ন করি না। #";
        DoublePair score = naiveBayes.getScore(queryStr);

        System.out.println("posScore: " + score.getPosScore() + " negScore: " + score.getNegScore());
        System.out.print("Query String: " + queryStr + " ========> ");
        if (score.getPosScore() >= score.getNegScore()) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }
}
