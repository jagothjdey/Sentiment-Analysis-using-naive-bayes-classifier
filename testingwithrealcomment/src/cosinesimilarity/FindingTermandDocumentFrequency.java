package cosinesimilarity;

import static cosinesimilarity.QueryTermandDocumentFrequency.QueryDF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class FindingTermandDocumentFrequency {
    
    public static ArrayList<String> uniquefrq1 = new ArrayList<>();
    public static ArrayList<String> uniquefrq2 = new ArrayList<>();
    public static ArrayList<String> frq1 = new ArrayList<>();
    public static ArrayList<String> frq2 = new ArrayList<>();
    public static Map<String, Integer> NegativeTF = new HashMap();
    public static Map<String, Integer> PositiveTF = new HashMap();
    public static Map<String, Integer> NegativeDF = new HashMap();
    public static Map<String, Integer> PositiveDF = new HashMap();
    public static Map<String, Double> PositiveIDF = new HashMap();
    public static Map<String, Double> NegativeIDF = new HashMap();

    public static void Termfrequency(String comment) {
        StringTokenizer st = new StringTokenizer(comment, " ?!_.#,][(),-");
        while (st.hasMoreElements()) {
            frq1.add(st.nextToken());
        }
        for (int i = 0; i < frq1.size(); i++) {
            if (NegativeTF.get(frq1.get(i)) == null) {
                NegativeTF.put(frq1.get(i), 1);
            } else {
                NegativeTF.put(frq1.get(i), NegativeTF.get(frq1.get(i)) + 1);
            }
        }

        FileRead fileread = new FileRead();
        String aa = fileread.filereadingMethod("G:\\Thesis 4-2\\CosineSimilarity\\src\\DataFiles\\Positive Data.txt");
        aa = aa.substring(1);
        st = new StringTokenizer(aa, " ?!_.#,][(),-");
        while (st.hasMoreElements()) {
            frq2.add(st.nextToken());
        }
        for (int i = 0; i < frq2.size(); i++) {
            if (PositiveTF.get(frq2.get(i)) == null) {
                PositiveTF.put(frq2.get(i), 1);
            } else {
                PositiveTF.put(frq2.get(i), PositiveTF.get(frq2.get(i)) + 1);
            }
        }
    }

    public static void DocumentFrq() {

        String aa = "";
        for (int i = 0; i < frq1.size(); i++) {
            aa = frq1.get(i);
            frq1.set(i, "$");
            if (!frq1.contains(aa)) {
                uniquefrq1.add(aa);
            }
        }
        aa = "";
        for (int i = 0; i < frq2.size(); i++) {
            aa = frq2.get(i);
            frq2.set(i, "$");
            if (!frq2.contains(aa)) {
                uniquefrq2.add(aa);
            }
        }
               
        for (int i = 0; i < uniquefrq1.size(); i++) {
            NegativeDF.put(uniquefrq1.get(i), 1);
        }

        aa = "";
        for (int i = 0; i < uniquefrq1.size(); i++) {
            aa = uniquefrq1.get(i);
            for (int j = 0; j < uniquefrq2.size(); j++) {
                if (aa.equals(uniquefrq2.get(j))) {
                    if( NegativeDF.get(aa)!=null )NegativeDF.put(aa,NegativeDF.get(aa)+1);
                    else NegativeDF.put(aa, 1);
                    break;
                }
            }
        }
       
        
        for (int i = 0; i < uniquefrq2.size(); i++) {
            PositiveDF.put(uniquefrq2.get(i), 1);
        }

        aa = "";
        for (int i = 0; i < uniquefrq2.size(); i++) {
            aa = uniquefrq2.get(i);
            for (int j = 0; j < uniquefrq1.size(); j++) {
                if (aa.equals(uniquefrq1.get(j))) {
                    if( PositiveDF.get(aa)!=null )PositiveDF.put(aa,PositiveDF.get(aa)+1);
                    else PositiveDF.put(aa, 1);
                    break;
                }
            }
        }
        
        double var;
        for(int i=0;i<uniquefrq1.size();i++)
        {
            var=(2.0*1.0)/(NegativeDF.get(uniquefrq1.get(i))*1.0);
            var = Math.log(var)/Math.log(2.0);
            NegativeIDF.put(uniquefrq1.get(i), var);
        }
        
        var=0.0;
        for(int i=0;i<uniquefrq2.size();i++)
        {
            var=(2.0*1.0)/(PositiveDF.get(uniquefrq2.get(i))*1.0);
            var = Math.log(var)/Math.log(2.0);
            PositiveIDF.put(uniquefrq2.get(i), var);
        }
    }
}
