package naivebayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import normalize.BiGram;
import normalize.NormalizeMain;
import pos.tagger.PosTagMain;
import stemmer.bengali.BengaliStemmer;

/**
 *
 * @author Sm19
 */
class FileOpener {

    public String path;
    BufferedReader reader = null;
    private double totalStatus;
    ArrayList<String> word;
    public PosTagMain posTagMain;

    public FileOpener(String path, double totalS) {
        this.path = path;
        totalStatus = totalS;
        this.word = new ArrayList<>();
        posTagMain = new PosTagMain();
    }

    public ArrayList<String> open() throws IOException {
        File file = new File(path);
        try {
            reader = new BufferedReader(new FileReader(file));
            String status;
            String pre = "";
            while ((status = reader.readLine()) != null) {
                status = status.trim();
                int len = status.length();
                if (len == 0) {
                    continue;
                }
                if (status.charAt(len - 1) != '#') { // tag of corpas
                    pre = status;
                } else {
                    doProcess(pre + status);
                    pre = "";
                }
            }
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return this.word;
    }

    // do what you want......in your corpus.
    private void doProcess(String status) throws IOException {

        totalStatus++; // total status count
        status = status + "#";
        //System.out.println("Statue ১: " + status);
        StringTokenizer tTk = new StringTokenizer(status, "#");
        while (tTk.hasMoreTokens()) {
            status = tTk.nextToken();
        }

        status = posTagMain.getTagWords(status);
        
        //System.out.println("after postag: "+status);
        
        NormalizeMain normalizeMain = new NormalizeMain(status);
        status = normalizeMain.getNormalizeStr();
        
        //System.out.println("After Norma: "+status);

        BengaliStemmer stemmer = new BengaliStemmer("/home/sm19/Desktop/Final Year Thesis/"
                + "Thesis_4_2_works/Project/NaiveBayesTwo/extarnalFile/stammer/");
        status = stemmer.findRoot(status);
        
        //System.out.println("After Stemmer: "+status);
        
        BiGram biGram = new BiGram(status);
        //word.addAll(biGram.getBackwardBigram());
        word.addAll(biGram.getForwardBigram());
    }

    public double getTotalStatus() {
        return totalStatus;
    }
}
