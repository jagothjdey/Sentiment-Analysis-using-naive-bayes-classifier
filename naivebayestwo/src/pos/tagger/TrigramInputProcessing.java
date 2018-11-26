package pos.tagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class TrigramInputProcessing {

    public static ArrayList<String> pattern = new ArrayList<>();
    public static ArrayList<String> UnknownPatterns = new ArrayList<>();
    public static ArrayList<String> temp_FinalAnser = new ArrayList<>();
    public static String FinalAnser = "";
    public static Map<String, Double> mp = new HashMap<>();
    private String strValue, mainStr;

    public String getMainStr() {
        return mainStr;
    }

    public void setMainStr(String mainStr) {
        this.mainStr = mainStr;
    }

    TrigramInputProcessing() {
    }

    public TrigramInputProcessing(String mainStr, String strv) {
        this.strValue = strv;
        this.mainStr = mainStr;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public String N_Gram_Model() throws FileNotFoundException, IOException {

        ArrayList<String> arr = new ArrayList<>();
        String aa = this.getStrValue(), s;

        StringTokenizer st = new StringTokenizer(aa, "।");

        while (st.hasMoreElements()) {
            s = "";
            s = st.nextToken();
            arr.add(s);
        }

        aa = "";
        s = "";
        for (int i = 0; i < arr.size(); i++) {
            aa = arr.get(i);
            s = "";
            s += "S";
            for (int j = 0; j < aa.length(); j++) {
                if (aa.charAt(j) == 'N' || aa.charAt(j) == 'A' || aa.charAt(j) == 'V' || aa.charAt(j) == 'P' || aa.charAt(j) == 'C' || aa.charAt(j) == '#') {
                    s += aa.charAt(j);
                }
            }
            s += "E";
            pattern.add(s);
        }
        String ans = mapping_value();
        return ans;
    }

    public String mapping_value() throws FileNotFoundException, IOException {
        File file = new File("extarnalFile/dependableFile/_probability.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String aa = "", s = "";
        ArrayList<String> str = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            aa = "";
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '-' || s.charAt(i) == '=') {
                    continue;
                }
                aa += s.charAt(i);
            }
            str.add(aa);
        }
        String value = "";
        aa = "";
        double val;
        for (int i = 0; i < str.size(); i++) {
            aa = "";
            aa += str.get(i).charAt(0);
            aa += str.get(i).charAt(1);
            aa += str.get(i).charAt(2);
            value = str.get(i).substring(3, str.get(i).length());
            val = Double.parseDouble(value);
            mp.put(aa, val);
        }
        double maxv = 0.0;
        char ch = ' ';

        for (int i = 0; i < pattern.size(); i++) {
            aa = pattern.get(i);
            for (int j = 0; j < aa.length(); j++) {
                if (aa.charAt(j) == '#') {
                    if (j == 1) {
                        String temp;
                        temp = aa.substring(0, 1) + 'N' + aa.substring(2);
                        aa = temp;
                    } else {
                        String ss = "";
                        ss += aa.charAt(j - 2);
                        ss += aa.charAt(j - 1);
                        Set keys = mp.keySet();
                        maxv = 0.0;
                        for (Iterator k = keys.iterator(); k.hasNext();) {
                            String key = (String) k.next();
                            if (key.substring(0, 2).equals(ss)) {
                                double key_value = mp.get(key);
                                if (maxv < key_value) {
                                    maxv = key_value;
                                    ch = key.charAt(2);
                                    String temp;
                                    temp = aa.substring(0, j) + ch + aa.substring(j + 1);
                                    aa = temp;
                                }
                            }
                        }
                    }
                }
            }
            temp_FinalAnser.add(aa);
        }
        for (int i = 0; i < temp_FinalAnser.size(); i++) {
            String bb = "", temp = "";
            bb = temp_FinalAnser.get(i);
            temp = bb.substring(1, bb.length() - 1);
            FinalAnser += temp;
        }

        //System.out.println("Final Ans: " + FinalAnser);
        ProcessingOutputFile processingoutputfile = new ProcessingOutputFile();
        String finalAns = processingoutputfile.getFinalTag(this.getMainStr(), FinalAnser);
        //System.out.println("Tri_FinalAns: " + finalAns);
        return finalAns;
    }
}
