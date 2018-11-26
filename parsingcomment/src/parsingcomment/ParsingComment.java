package parsingcomment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParsingComment {

    public static void main(String[] args) throws IOException {
        
        Vector<String>v = new Vector<String>();
        Vector<String>ans = new Vector<String>();
        Vector<String>ans1 = new Vector<String>();
        File file = new File("New Text Document.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParsingComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        
        String aa="",s="";
        
        try {
            while( (s=br.readLine())!=null )
            {         
                v.add(s);
            }
        } catch (IOException ex) {
            Logger.getLogger(ParsingComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<v.size();i++)
        {
            aa=v.elementAt(i);
            if( aa.length()<2 ) continue;
            else if( "by".equals(aa.substring(0, 2)) )
            {
                continue;
            }
            else if("Copy".equals(aa.substring(0, 4)))
            {
                continue;
            }
            else
            {
                ans.add(v.elementAt(i));
            }
        }
        for(int i=0;i<ans.size();i++)
        {
            System.out.println(ans.elementAt(i));
        }
        File file1 = new File("Test.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file1);
        } catch (IOException ex) {
            Logger.getLogger(ParsingComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        aa="";
        int j;
        for(int i=0;i<ans.size();i++)
        {
            aa=ans.elementAt(i);
            for(j=0;j<aa.length();j++)
            {
                if( aa.charAt(j)==')' || aa.charAt(j)==')' ) break;
            }
            if( j+1<aa.length()) aa = aa.substring(j+1,aa.length());
            ans1.add(aa);
        }
        
        BufferedWriter bw = new BufferedWriter(fw);
        for(int i=0;i<ans1.size();i++)
        {
            try {
                bw.write(ans1.elementAt(i));
                bw.newLine();
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(ParsingComment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bw.close();
    }
    
}
