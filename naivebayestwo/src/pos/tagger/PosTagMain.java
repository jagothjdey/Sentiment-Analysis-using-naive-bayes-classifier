package pos.tagger;

import java.io.IOException;
/**
 *
 * @author sm19
 */
public class PosTagMain {

    private String mainStr;

    public PosTagMain() {
    }

    public PosTagMain(String mainStr) {
        this.mainStr = mainStr;
    }
    

    public String getMainStr() {
        return mainStr;
    }

    public void setMainStr(String mainStr) {
        this.mainStr = mainStr;
    }

    public String getTagWords(String mainStr) throws IOException {

        GrepppingMethod grepppingMethod = new GrepppingMethod(mainStr);
        String greppingAns = grepppingMethod.getTagByGrepping();

        //System.out.println("In main ans: " + greppingAns);

        TrigramInputProcessing trigramInputProcessing = new TrigramInputProcessing(mainStr, greppingAns);
        String finalAns = trigramInputProcessing.N_Gram_Model();

        //System.out.println("FinalAns: " + finalAns);
        return finalAns;
    }

    public String getTagWords() throws IOException {
        return getTagWords(this.getMainStr());
    }

    public static void main(String[] args) throws IOException {

        String mainStr = "ami ar amai";
        PosTagMain posTagMain = new PosTagMain(mainStr);
        String res=posTagMain.getTagWords();
        System.out.println("Res: "+res);
    }
}

