package sentimentanalysisusingposratio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.Query_after_tagged;
import static sentimentanalysisusingposratio.TrigramInputProcessing.FinalAnser;


public class ProcessingOutputFile {

    public ProcessingOutputFile() throws FileNotFoundException, IOException {

        File file = new File("Query.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String s = "", aa = "";
        while ((s = br.readLine()) != null) {
            aa += s;
        }
        
        String aa1 = "";
        char ch = ' ';
        for (int i = 0; i < aa.length(); i++) {
            if (aa.charAt(i) == '‘') {
                aa1 += aa.charAt(i);
                aa1 += ch;
            } else if (aa.charAt(i) == '?' || aa.charAt(i) == '।' || aa.charAt(i) == ';' || aa.charAt(i) == ',' || aa.charAt(i) == '’' || aa.charAt(i) == '.' || aa.charAt(i) == '(' || aa.charAt(i) == ')' || aa.charAt(i) == '—' || aa.charAt(i) == '?' || aa.charAt(i) == '।') {
                aa1 += ch;
                aa1 += aa.charAt(i);
                aa1 += ch;
            } else {
                aa1 += aa.charAt(i);
            }
        }
        
        StringTokenizer st = new StringTokenizer(aa1," ");
        String ans="";
        int k=0;
        while(st.hasMoreElements())
        {
            if( k>=FinalAnser.length() ) break;
            String sss = st.nextToken();
//            System.out.println(sss);
            if (sss.equals(",") || sss.equals(",") || sss.equals("।") || sss.equals("‘") || sss.equals("’") || sss.equals("?") || sss.equals("!") || sss.equals("(") || sss.equals(")") || sss.equals("-") || sss.equals("/") || sss.equals(".") || sss.equals("."))
            {
                Query_after_tagged+=sss;
                Query_after_tagged+=" ";
            }
            else
            {
                Query_after_tagged+=sss;
                Query_after_tagged+=" ";
                String xx="";
                xx = String.valueOf(FinalAnser.charAt(k));
                Query_after_tagged+=xx;
                k++;
                Query_after_tagged+=" ";
            }
        }
        
//        System.out.println(ans);
        Query_after_tagged+="।";
//        File output = new File("output.txt");
//        FileWriter fw = new FileWriter(output);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(Query_after_tagged);
//        bw.close();
        
        System.out.println(Query_after_tagged);

    }

}
