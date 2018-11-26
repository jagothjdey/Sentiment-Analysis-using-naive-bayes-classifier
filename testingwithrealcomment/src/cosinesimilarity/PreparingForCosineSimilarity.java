
package cosinesimilarity;

import static cosinesimilarity.FindingTermandDocumentFrequency.NegativeIDF;
import static cosinesimilarity.FindingTermandDocumentFrequency.NegativeTF;
import static cosinesimilarity.FindingTermandDocumentFrequency.PositiveIDF;
import static cosinesimilarity.FindingTermandDocumentFrequency.PositiveTF;
import static cosinesimilarity.FindingTermandDocumentFrequency.uniquefrq1;
import static cosinesimilarity.FindingTermandDocumentFrequency.uniquefrq2;
import static cosinesimilarity.QueryTermandDocumentFrequency.QueryIDF;
import static cosinesimilarity.QueryTermandDocumentFrequency.QueryTF;
import static cosinesimilarity.QueryTermandDocumentFrequency.uniquefrq3;
import java.util.ArrayList;

public class PreparingForCosineSimilarity {
    
    public static ArrayList<Double>Doc1 = new ArrayList<Double>();
    public static ArrayList<Double>Doc2 = new ArrayList<Double>();
    public static ArrayList<Double>Query = new ArrayList<Double>();
    
    public static void making_vector(){
        
       double var;
       String aa="";
        for(int i=0;i<uniquefrq1.size();i++)
        {
            aa=uniquefrq1.get(i);
            if( NegativeTF.get(aa)==null ||  NegativeIDF.get(aa)==null ) continue;
            var=NegativeTF.get(aa)*NegativeIDF.get(aa);
            Doc1.add(var);
        }

        double var1;
        aa="";
        for(int i=0;i<uniquefrq2.size();i++)
        {
            aa=uniquefrq2.get(i);
            if( PositiveTF.get(aa)==null ||  PositiveIDF.get(aa)==null ) continue;
            var1=PositiveTF.get(aa)*PositiveIDF.get(aa);
            Doc2.add(var1);
        }

        double var2;
        aa="";
        for(int i=0;i<uniquefrq3.size();i++)
        {
            aa=uniquefrq3.get(i);
            if( QueryTF.get(aa)==null ||  QueryIDF.get(aa)==null ) continue;
            var1=QueryTF.get(aa)*QueryIDF.get(aa);
            Query.add(var1);
        }
    }
}
