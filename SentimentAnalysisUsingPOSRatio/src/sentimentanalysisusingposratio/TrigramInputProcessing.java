package sentimentanalysisusingposratio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import sun.util.locale.StringTokenIterator;

public class TrigramInputProcessing {

    public static ArrayList<String> pattern = new ArrayList<String>();
    public static ArrayList<String> UnknownPatterns = new ArrayList<String>();
    public static ArrayList<String> temp_FinalAnser = new ArrayList<String>();
    public static String FinalAnser = "";
    public static Map<String, Double> mp = new HashMap<String, Double>();

    public static void N_Gram_Model() throws FileNotFoundException, IOException {
        ArrayList<String> arr = new ArrayList<String>();
        File file = new File("OutputUsingGreppingMethod.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String s = "", aa = "";
        while ((s = br.readLine()) != null) {
            aa += s;
        }
//        System.out.println(aa);

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
//        System.out.println(pattern);
        mapping_value();
    }

    public static void mapping_value() throws FileNotFoundException, IOException {
        File file = new File("Probability.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String aa = "", s = "";
        ArrayList<String> str = new ArrayList<String>();
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
//            System.out.println("map value is:" + aa + "    " + val);
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
//                        System.out.println(aa);
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
                                    temp = aa.substring(0, j)+ch+aa.substring(j+1);
                                    aa=temp;
                                }
                            }
                        }
                    }
                }
            }
            temp_FinalAnser.add(aa);
//            System.out.println(aa);
        }

        for (int i = 0; i < temp_FinalAnser.size(); i++) {
            String bb = "", temp = "";
            bb = temp_FinalAnser.get(i);
            temp = bb.substring(1, bb.length() - 1);
            FinalAnser += temp;
        }
//        System.out.println("Final Anser is:  "+FinalAnser);
        ProcessingOutputFile processingoutputfile = new ProcessingOutputFile();

    }
}



//        for (int i = 0; i < pattern.size(); i++) {
//            aa = pattern.get(i);
//            if (aa.length() < 3) {
//                continue;
//            }
//            s = "";
//            while (aa.contains("#")) {
//                for (int j = 0; j < aa.length(); j++) {
//                    if (aa.charAt(j) == '#') {
//                        System.out.println(aa);
//                        if (aa.charAt(1) == '#') {
//                            String temp = aa;
//                            aa = temp.substring(0, 1) + 'N' + temp.substring(2);
//                        } else {
//                            s = "";
//                            maxv = 0.0;
//                            if (aa.charAt(j) == '#') {
//                                if ((j - 2) < 0 || (j - 1) < 0) {
//                                    continue;
//                                }
//                                s += aa.charAt(j - 2);
//                                s += aa.charAt(j - 1);
//                                Set keys = mp.keySet();
//                                for (Iterator k = keys.iterator(); k.hasNext();) {
//                                    String key = (String) k.next();
//                                    if (key.substring(0, 2).equals(s)) {
//                                        double key_value = mp.get(key);
//                                        if (maxv < key_value) {
//                                            maxv = key_value;
//                                            ch = key.charAt(2);
//                                            String temp = aa;
//                                            aa = temp.substring(0, j) + ch + temp.substring(j + 1);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                temp_FinalAnser.add(aa);
////                System.out.println(temp_FinalAnser+" "+pattern);
//            }
//        }