package pos.tagger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import static pos.tagger.TrigramInputProcessing.FinalAnser;

public class ProcessingOutputFile {

    public ProcessingOutputFile() {
    }

    public String getFinalTag(String mainStr, String triStr) throws FileNotFoundException, IOException {
        String aa = mainStr;
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
        StringTokenizer st = new StringTokenizer(aa1, " ");
        String ans = "";
        int k = 0;
        while (st.hasMoreElements()) {
            if (k >= FinalAnser.length()) {
                break;
            }
            String sss = st.nextToken();
            if (sss.equals(",") || sss.equals(",") || sss.equals("।") || sss.equals("‘") || sss.equals("’") || sss.equals("?") || sss.equals("!") || sss.equals("(") || sss.equals(")") || sss.equals("-") || sss.equals("/") || sss.equals(".") || sss.equals(".")) {
                ans += sss;
                ans += " ";
            } else {
                ans += sss;
                ans += " ";
                String xx = "";
                xx = String.valueOf(FinalAnser.charAt(k));
                ans += xx;
                k++;
                ans += " ";
            }
        }

        return ans;
    }
}
