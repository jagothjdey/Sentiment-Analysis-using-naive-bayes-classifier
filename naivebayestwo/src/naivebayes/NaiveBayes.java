package naivebayes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import normalize.BiGram;
import normalize.NormalizeMain;
import pos.tagger.PosTagMain;
import stemmer.bengali.BengaliStemmer;

/**
 *
 * @author Sm19
 */
public class NaiveBayes {

    public ArrayList<String> posWord;
    public ArrayList<String> negWord;
    public ArrayList<String> allWord;
    public HashMap<String, Integer> posMap, negMap, allMap;
    public HashMap<String, Double> posConditionalProbablity, negConditionalProbablity;
    public String posPath, negPath;
    public double posClassProbability, negClassProbability;

    private double totalStatus, totalPosStatus, totalNegStatus;

    public NaiveBayes(String p, String n) {
        posPath = p;
        negPath = n;
        posWord = new ArrayList<>();
        negWord = new ArrayList<>();
        allWord = new ArrayList<>();
        posMap = new HashMap<>();
        negMap = new HashMap<>();
        allMap = new HashMap<>();
        posConditionalProbablity = new HashMap<>();
        negConditionalProbablity = new HashMap<>();
    }

    public void loadData() throws IOException {
        FileOpener posOpener = new FileOpener(posPath, 0);
        FileOpener negOpener = new FileOpener(negPath, 0);
        posWord = posOpener.open();
        negWord = negOpener.open();
        totalPosStatus = posOpener.getTotalStatus();
        totalNegStatus = negOpener.getTotalStatus();

        for (String str : posWord) {
            allWord.add(str);
            if (posMap.containsKey(str)) {
                posMap.replace(str, posMap.get(str) + 1);
            } else {
                posMap.put(str, 1);
            }
            if (allMap.containsKey(str)) {
                allMap.replace(str, allMap.get(str) + 1);
            } else {
                allMap.put(str, 1);
            }
        }
        for (String str : negWord) {
            allWord.add(str);
            if (negMap.containsKey(str)) {
                negMap.replace(str, negMap.get(str) + 1);
            } else {
                negMap.put(str, 1);
            }
            if (allMap.containsKey(str)) {
                allMap.replace(str, allMap.get(str) + 1);
            } else {
                allMap.put(str, 1);
            }
        }
        doAlphaWorks();
    }

    public double getTotalPosWord() {
        return posWord.size();
    }

    public double getTotalNegWord() {
        return negWord.size();
    }

    public double getUniquePosWord() {
        return posMap.size();
    }

    public double getUniqueNegWord() {
        return negMap.size();
    }

    public double getTotalWord() {
        return allWord.size();
    }

    public double getTotalUniqueWord() {
        return allMap.size();
    }

    public double getTotalPosStatus() {
        return totalPosStatus;
    }

    public double getTotalNegStatus() {
        return totalNegStatus;
    }

    public double getTotalStatus() {
        return getTotalNegStatus() + getTotalPosStatus();
    }

    public double getPosValue(String str) {
        if (posMap.containsKey(str)) {
            return posMap.get(str);
        } else {
            return 0;
        }
    }

    public double getNegValue(String str) {
        if (negMap.containsKey(str)) {
            return negMap.get(str);
        } else {
            return 0;
        }
    }

    public double getPosSmoothCost() {
        return 1.0 / (getTotalPosWord() + getTotalUniqueWord());
    }

    public double getNegSmoothCost() {
        return 1.0 / (getTotalNegWord() + getTotalUniqueWord());
    }

    public double getPosClassProbability() {
        return posClassProbability;
    }

    public void setPosClassProbability(double posClassProbability) {
        this.posClassProbability = posClassProbability;
    }

    public double getNegClassProbability() {
        return negClassProbability;
    }

    public void setNegClassProbability(double negClassProbability) {
        this.negClassProbability = negClassProbability;
    }

    public void doAlphaWorks() {
        posClassProbability = (getTotalPosStatus() * 1.0) / getTotalStatus();
        negClassProbability = (getTotalNegStatus() * 1.0) / getTotalStatus();

        for (Map.Entry<String, Integer> keys : allMap.entrySet()) {
            String str = keys.getKey();
            double posValue = (getPosValue(str) * 1.0 + 1) / (getTotalPosWord() + getTotalUniqueWord());
            double negValue = (getNegValue(str) * 1.0 + 1) / (getTotalNegWord() + getTotalUniqueWord());
            posConditionalProbablity.put(str, posValue);
            negConditionalProbablity.put(str, negValue);
        }
    }

    //All....... for this function....
    public DoublePair getScore(String testStatus) throws IOException {

        double posScore = 1.0, negScore = 1.0;
        ArrayList<String> queryWord = doProcess(testStatus);
        posScore = posClassProbability;
        
        for (String str : queryWord) {
            double tmpScore;
            if (posConditionalProbablity.containsKey(str)) {
                tmpScore = posConditionalProbablity.get(str);
            } else {
                tmpScore = getPosSmoothCost();
            }
            posScore = posScore * tmpScore;
        }

        negScore = negClassProbability;
        for (String str : queryWord) {
            double tmpScore;
            if (negConditionalProbablity.containsKey(str)) {
                tmpScore = negConditionalProbablity.get(str);
            } else {
                tmpScore = getNegSmoothCost();
            }
            negScore = negScore * tmpScore;
        }

        //System.out.println("totalStatus: " + getTotalStatus() + " totalPosStatus: " + getTotalPosStatus() + " totalNegStatus: " + getTotalNegStatus());
        //System.out.println("totalWord: " + getTotalWord() + " totalPosWord: " + getTotalPosWord()+" totalNegWord: "+getTotalNegWord());
        return new DoublePair(posScore, negScore);
    }

    private ArrayList<String> doProcess(String status) throws IOException {

        status = "#" + status + "#";
        StringTokenizer tTk = new StringTokenizer(status, "#");
        while (tTk.hasMoreTokens()) {
            status = tTk.nextToken();
        }
        PosTagMain posTagMain = new PosTagMain();
        status = posTagMain.getTagWords(status);

        System.out.println("after postag: " + status);

        NormalizeMain normalizeMain = new NormalizeMain(status);
        status = normalizeMain.getNormalizeStr();

        System.out.println("After Norma: " + status);

        BengaliStemmer stemmer = new BengaliStemmer("/home/sm19/Desktop/Final Year Thesis/"
                + "Thesis_4_2_works/Project/NaiveBayesTwo/extarnalFile/stammer/");
        status = stemmer.findRoot(status);

        System.out.println("After Stemmer: " + status);

        BiGram biGram = new BiGram(status);
        ArrayList<String> statusWord = new ArrayList<>();
        //statusWord.addAll(biGram.getBackwardBigram());
        statusWord.addAll(biGram.getForwardBigram());
        return statusWord;
    }
}
