package cosinesimilarity;

import static cosinesimilarity.PreparingForCosineSimilarity.Doc1;
import static cosinesimilarity.PreparingForCosineSimilarity.Doc2;
import static cosinesimilarity.PreparingForCosineSimilarity.Query;
import static cosinesimilarity.TakingAllQuery.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CosineSimilarity {
    
    public static Vector<String> Tokenized_comment = new Vector<String>();
    public static double similarity_to_doc1;
    public static double similarity_to_doc2;
    
//    public static void main(String[] args){
    public static void main(String[] args) {
       
        FileRead obj  = new FileRead();
        String aa = obj.filereadingMethod("G:\\Thesis 4-2\\CosineSimilarity\\src\\DataFiles\\Negative Data.txt");
        aa = aa.substring(1);
        FindingTermandDocumentFrequency findingtermanddocfrq = new FindingTermandDocumentFrequency();
        findingtermanddocfrq.Termfrequency(aa);
        findingtermanddocfrq.DocumentFrq();
        
        TakingAllQuery tk = new TakingAllQuery();
        tk.myfun();
        int pos=0,neg=0;
        for(int i=0;i<test.size();i++)
        {
//            System.out.println(test.get(i));  
            String a = test.get(i);
            QueryTermandDocumentFrequency qtf = new QueryTermandDocumentFrequency();
            qtf.QueryTermandDoc(a);

            PreparingForCosineSimilarity pfcs = new PreparingForCosineSimilarity();
            pfcs.making_vector();

           CS cs = new CS();
           similarity_to_doc1=cs.getCosineSimilarity(Doc1, Query);
           similarity_to_doc2 =cs.getCosineSimilarity(Doc2, Query);
           
           
           if (similarity_to_doc2 > similarity_to_doc1) {
               pos++;
                System.out.println("Positive");
            } else {
               neg++;
                System.out.println("Negative");
            }
           System.out.println(pos+"   "+neg);
          QueryTermandDocumentFrequency.QueryTF.clear();
        QueryTermandDocumentFrequency.QueryDF.clear();
        QueryTermandDocumentFrequency.QueryIDF.clear();
        QueryTermandDocumentFrequency.uniquefrq3.clear();
        QueryTermandDocumentFrequency.frq3.clear();
        Tokenized_comment.clear();
//        Doc1.clear();
//        Doc2.clear();
        Query.clear();        
        }
        
    }
}
