
package cosinesimilarity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TakingAllQuery {
    
    public static Vector<String>test = new Vector<String>();
    
    public static void myfun(){
        File file = new File("C:\\Users\\Rajib-pc\\Desktop\\CosineSimilarity\\src\\DataFiles\\Test.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TakingAllQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        
        String aa="",s="";
        
        try {
            while( (s=br.readLine())!=null )
            {
                aa+=s;
            }
        } catch (IOException ex) {
            Logger.getLogger(TakingAllQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        s="";
        StringTokenizer st = new StringTokenizer(aa, "#");
        while( st.hasMoreElements() )
        {
            s=st.nextToken();
            test.add(s);
        }
        
    }
}
