package cosinesimilarity;

import static cosinesimilarity.PreparingForCosineSimilarity.Doc1;
import static cosinesimilarity.PreparingForCosineSimilarity.Doc2;
import static cosinesimilarity.PreparingForCosineSimilarity.Query;
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
    public static void MainMethod(String a) {
       
        FileRead obj  = new FileRead();
        String aa = obj.filereadingMethod("G:\\Thesis 4-2\\CosineSimilarity\\src\\DataFiles\\Negative Data.txt");
        aa = aa.substring(1);
        FindingTermandDocumentFrequency findingtermanddocfrq = new FindingTermandDocumentFrequency();
        findingtermanddocfrq.Termfrequency(aa);
        findingtermanddocfrq.DocumentFrq();
        
        QueryTermandDocumentFrequency qtf = new QueryTermandDocumentFrequency();
        qtf.QueryTermandDoc(a);
        
        PreparingForCosineSimilarity pfcs = new PreparingForCosineSimilarity();
        pfcs.making_vector();
        
       CS cs = new CS();
       similarity_to_doc1=cs.getCosineSimilarity(Doc1, Query);
       similarity_to_doc2 =cs.getCosineSimilarity(Doc2, Query);
    }
}
