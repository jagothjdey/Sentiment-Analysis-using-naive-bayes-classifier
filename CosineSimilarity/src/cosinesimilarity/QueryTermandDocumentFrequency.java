package cosinesimilarity;

import static cosinesimilarity.FindingTermandDocumentFrequency.NegativeDF;
import static cosinesimilarity.FindingTermandDocumentFrequency.NegativeIDF;
import static cosinesimilarity.FindingTermandDocumentFrequency.NegativeTF;
import static cosinesimilarity.FindingTermandDocumentFrequency.PositiveDF;
import static cosinesimilarity.FindingTermandDocumentFrequency.PositiveIDF;
import static cosinesimilarity.FindingTermandDocumentFrequency.PositiveTF;
import static cosinesimilarity.FindingTermandDocumentFrequency.frq1;
import static cosinesimilarity.FindingTermandDocumentFrequency.uniquefrq1;
import static cosinesimilarity.FindingTermandDocumentFrequency.uniquefrq2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class QueryTermandDocumentFrequency {

    public static ArrayList<String> frq3 = new ArrayList<>();
    public static ArrayList<String> uniquefrq3 = new ArrayList<>();
    public static Map<String, Integer> QueryTF = new HashMap();
    public static Map<String, Integer> QueryDF = new HashMap();
    public static Map<String, Double> QueryIDF = new HashMap();

    public static Void QueryTermandDoc(String aa) {
        FileRead fileread = new FileRead();
//        String aa = fileread.filereadingMethod("G:\\Thesis 4-2\\CosineSimilarity\\src\\DataFiles\\Query.txt");
        aa = aa.substring(1);
        StringTokenizer st = new StringTokenizer(aa, " ?!_.#,][(),-");
        while (st.hasMoreElements()) {
            frq3.add(st.nextToken());
        }
        for (int i = 0; i < frq3.size(); i++) {
            if (QueryTF.get(frq3.get(i)) == null) {
                QueryTF.put(frq3.get(i), 1);
            } else {
                QueryTF.put(frq3.get(i), QueryTF.get(frq3.get(i)) + 1);
            }
        }
        aa = "";
        for (int i = 0; i < frq3.size(); i++) {
            aa = frq3.get(i);
            frq3.set(i, "$");
            if (!frq3.contains(aa)) {
                uniquefrq3.add(aa);
            }
        }

        aa="";
        for(int i=0;i<uniquefrq3.size();i++)
        {
            aa=uniquefrq3.get(i);
            for(int j=0;j<uniquefrq1.size();j++)
            {
                if( aa.equals(uniquefrq1.get(j)) )
                {
                    QueryDF.put(aa,1);
                }
            }
        }

        aa="";
        for(int i=0;i<uniquefrq3.size();i++)
        {
            aa=uniquefrq3.get(i);
            for(int j=0;j<uniquefrq2.size();j++)
            {
                if( aa.equals(uniquefrq2.get(j)) )
                {
                    if( QueryDF.get(aa)!=null )QueryDF.put(aa,QueryDF.get(aa)+1);
                    else QueryDF.put(aa, 1);
                    break;
                }
            }
        }

        double var=0.0;

        aa="";
        for(int i=0;i<uniquefrq3.size();i++)
        {
            aa=uniquefrq3.get(i);
            if( QueryDF.get(aa)==null ) continue;
            var=(2.0*1.0)/(QueryDF.get(aa)*1.0);
            var = Math.log(var)/Math.log(2.0);
            QueryIDF.put(uniquefrq3.get(i), var);
        }
        
//        System.out.println(QueryTF);
//        System.out.println(QueryDF);
//        System.out.println(QueryIDF);
//        QueryTF.clear();
        
        return null;

    }
}
