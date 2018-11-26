package sentimentanalysisusingposratio;

import java.io.IOException;
import static java.lang.Math.abs;
import java.util.Scanner;
import java.util.Vector;

public class SentimentAnalysisUsingPOSRatio {
 
    public static String all_comment;
    public static String Query_after_tagged="";
    public static double total_noun,total_pronoun,total_adjective,total_verb,total_con;
    public static Vector<Double>positive = new Vector<Double>();
    public static Vector<Double>negative = new Vector<Double>();
    public static Vector<Double>Query = new Vector<Double>();
    
     public static void main(String[] args) throws IOException {
        
        TaggingCorpusData taggingcorpusdata = new TaggingCorpusData();
        taggingcorpusdata.taggingcourpusdatamethod();
         
         GrepppingMethod grp = new GrepppingMethod();
         grp.grepppingMethod("Query.txt");
         TrigramInputProcessing tgp = new TrigramInputProcessing();
         tgp.N_Gram_Model();
         
         CountAllPOS cnt = new CountAllPOS();
         cnt.totalnumberofPOS(Query_after_tagged);
        Query.add(total_noun); Query.add(total_adjective); Query.add(total_verb); Query.add(total_pronoun); Query.add(total_con);
         System.out.println("Query:");
        for(int i=0;i<Query.size();i++)
        {
            System.out.println(Query.elementAt(i));
        }
        double query_positive_dis=0.0,query_negative_dis=0.0;
        
        for(int i=0;i<positive.size();i++)
        {
            query_positive_dis+= abs(positive.elementAt(i)-Query.elementAt(i));
        }
        
         for(int i=0;i<negative.size();i++)
        {
            query_negative_dis+= abs(negative.elementAt(i)-Query.elementAt(i));
        }
         System.out.println(query_positive_dis+"           "+query_negative_dis);
         if( query_positive_dis<=query_negative_dis )
         {
             System.out.println("Positive");
         }
         else 
         {
             System.out.println("Negative");
         }
    }
}
