package cosinesimilarity;

import static cosinesimilarity.CosineSimilarity.Tokenized_comment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileRead {

    public static String filereadingMethod(String readfilename ) {

        File file = new File(readfilename);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String aa = "", s = "";
        try {
            while ((s = br.readLine()) != null) {
                aa += s;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileRead.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aa;
    }

    public static void Comment_Tokenizer(String aa, String delimeter) {
        StringTokenizer st = new StringTokenizer(aa, delimeter);
        while (st.hasMoreTokens()) {
            Tokenized_comment.add((String) st.nextElement());
        }
    }
}
