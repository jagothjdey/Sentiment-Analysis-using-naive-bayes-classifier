package normalize;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author sm19
 */
public class BiGram {

    private String mString;

    public BiGram() {
    }

    public BiGram(String mstr) {
        this.mString = mstr;
    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }

    public ArrayList<String> forwardBigram, BackwardBigram;

    public String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] message = md.digest(str.getBytes());
            BigInteger number = new BigInteger(1, message);
            String hashText = number.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getForwardBigram(String mStr) {
        mStr = mStr.trim();
        ArrayList<String> resArray = new ArrayList<>();
        ArrayList<String> tokenTag = new ArrayList<>();
        StringTokenizer tk = new StringTokenizer(mStr, " ");
        while (tk.hasMoreTokens()) {
            tokenTag.add(tk.nextToken());
        }
        if (tokenTag.size() < 3) {
            resArray.add(getMD5(tokenTag.get(0)));
            return resArray;
        }
        for (int i = 2; i < tokenTag.size(); i += 2) {
            String a = tokenTag.get(i - 2);
            String b = tokenTag.get(i);
            String resStr = getMD5(a + " " + b);
            //System.out.println("resFor: " + a + " " + b);
            resArray.add(resStr);
        }
        return resArray;
    }

    public ArrayList<String> getBackwardBigram(String mStr) {
        mStr = mStr.trim();
        ArrayList<String> resArray = new ArrayList<>();
        ArrayList<String> tokenTag = new ArrayList<>();
        StringTokenizer tk = new StringTokenizer(mStr, " ");
        while (tk.hasMoreTokens()) {
            tokenTag.add(tk.nextToken());
        }
        if (tokenTag.size() < 3) {
            resArray.add(getMD5(tokenTag.get(0)));
            return resArray;
        }
        for (int i = tokenTag.size() - 4; i >= 0; i -= 2) {
            String a = tokenTag.get(i);
            String b = tokenTag.get(i + 2);
            String resStr = getMD5(a + " " + b);
            //System.out.println("resBack: " + a + " " + b);
            resArray.add(resStr);
        }
        return resArray;
    }

    public ArrayList<String> getBackwardBigram() {
        return this.getBackwardBigram(this.getmString());
    }

    public ArrayList<String> getForwardBigram() {
        return this.getForwardBigram(this.getmString());
    }

    public static void main(String[] args) {
        String biStr = "She N is V very A good A girl P";
        System.out.println("MStr: " + biStr);
        BiGram biGram = new BiGram(biStr);
        ArrayList<String> res1 = biGram.getForwardBigram();
        ArrayList<String> res2 = biGram.getBackwardBigram();
    }
}
