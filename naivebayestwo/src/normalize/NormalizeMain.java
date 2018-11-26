package normalize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author sm19
 */
public class NormalizeMain {

    private String mainStr;

    public NormalizeMain() {
    }

    public NormalizeMain(String mainStr) {
        this.mainStr = mainStr;
    }

    public String getMainStr() {
        return mainStr;
    }

    public void setMainStr(String mainStr) {
        this.mainStr = mainStr;
    }

    public int getNumAdjective(String mStr) {
        int cnt = 0;
        for (int i = 0; i < mStr.length(); i++) {
            if (mStr.charAt(i) == 'A') {
                cnt++;
            }
        }
        return cnt;
    }

    public String getNormalizeStr(String mStr) throws FileNotFoundException, IOException {

        int numOfAdjective = this.getNumAdjective(mStr);

        //System.out.println("Numof Adj আদজাচতিভে: : " + numOfAdjective);
        if (numOfAdjective==0 || numOfAdjective > 1) {
            return mStr;
        }
        // file open
        File nFile = new File("extarnalFile/valance_shifter.txt");
        File biporithWordFile = new File("extarnalFile/biporithWord.txt");
        BufferedReader reader = new BufferedReader(new FileReader(nFile));
        BufferedReader readerBip = new BufferedReader(new FileReader(biporithWordFile));

        // stores
        ArrayList<String> nStr, mArrayStr;
        HashMap<String, String> biporithWord;
        nStr = new ArrayList<>();
        mArrayStr = new ArrayList<>();
        biporithWord = new HashMap<>();

        // valance_shifter file read
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.isEmpty()) {
                continue;
            }
            nStr.add(str);
        }

//        for (String s : nStr) {
//            System.out.println("না নি: " + s);
//        }

        // biporithWord file read and mapping.......
        while ((str = readerBip.readLine()) != null) {
            StringTokenizer sTk = new StringTokenizer(str, " ");
            int cnt = sTk.countTokens();
            if (cnt == 2) {
                String a = sTk.nextToken();
                String b = sTk.nextToken();
                biporithWord.put(a, b);
                biporithWord.put(b, a);
            }
        }

//        for (Map.Entry<String, String> map : biporithWord.entrySet()) {
//            System.out.println("Key: " + map.getKey() + " Valu: " + map.getValue());
//        }
        // main string tokenize
        StringTokenizer tk = new StringTokenizer(mStr, " ।");
        while (tk.hasMoreTokens()) {
            mArrayStr.add(tk.nextToken());
        }
        if (mArrayStr.isEmpty()) {
            return mStr;
        }

//        for (String s : mArrayStr) {
//            System.out.println("মাররায়ঃ: " + s);
//        }

        boolean naAtRight = false;
        if (mArrayStr.size() < 2) {
            return mStr;
        }

        /// check negative shifter word ase ki na?
        String lastStr = mArrayStr.get(mArrayStr.size() - 2);
        //System.out.println("Last : "+lastStr);
        for (String s : nStr) {
            if (lastStr == null ? s == null : lastStr.equals(s)) {
                naAtRight = true;
            }
        }
        if (!naAtRight) {
            return mStr;
        }
        //System.out.println("না রাইট: " + naAtRight);

        // transformation.....
        boolean biPaise = true;
        for (int i = 0; i < mArrayStr.size() && biPaise; i++) {
            if (i + 1 < mArrayStr.size()) {
                if (mArrayStr.get(i + 1).contains("A")) {

                    String biStr;
                    if (biporithWord.containsKey(mArrayStr.get(i))) {
                        biStr = biporithWord.get(mArrayStr.get(i));
                        mArrayStr.set(i, biStr);
                    } else {
                        biPaise = false;
                    }
                }
            }
        }

        //System.out.println("পাইসে: " + biPaise);

        // 'na' cutting.....
        String res = new String();
        int len = (biPaise) ? (mArrayStr.size()) - 2 : (mArrayStr.size());
        for (int i = 0; i < len; i++) {
            res += " " + mArrayStr.get(i);
        }
        return res;
    }

    public String getNormalizeStr() throws FileNotFoundException, IOException {
        return getNormalizeStr(this.mainStr);
    }

    public static void main(String[] args) throws IOException {
        String str = "প্রেম V মানুষকে N শান্তি N দেয় P কিন্তু N স্বস্তি V দেয় V না N";
        NormalizeMain normalizeMain = new NormalizeMain(str);
        String res = normalizeMain.getNormalizeStr(str);
        System.out.println("Res: " + res);
    }
}
