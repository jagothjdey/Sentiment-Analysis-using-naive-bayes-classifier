package sentimentanalysisusingposratio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileReadMethod {
    
    public  String ReadFile(String readfilename){;
        File file = new File(readfilename);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReadMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);
        String aa = "", s = "";
        try {
            while ((s = br.readLine()) != null) {
                aa += s;
            }
        } catch (IOException ex) {
            Logger.getLogger(FileReadMethod.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aa;
    }
}
