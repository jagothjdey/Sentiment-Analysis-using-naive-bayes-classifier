package pos.tagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GrepppingMethod {

    private String strValu;

    public GrepppingMethod(String strValue) throws FileNotFoundException, IOException {
        this.strValu = strValue;
    }

    public String getStrValu() {
        return strValu;
    }

    public void setStrValu(String strValu) {
        this.strValu = strValu;
    }

    public String getTagByGrepping() throws FileNotFoundException, IOException {
        String aa = this.getStrValu();
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

        ArrayList<String> arr = new ArrayList<>(20000);
        StringTokenizer st = new StringTokenizer(aa1, " ");

        String ans = "";
        while (st.hasMoreElements()) {
            ans = (String) st.nextToken();
            arr.add(ans);
        }

        File file;
        FileReader fr = null;
        BufferedReader br = null;
        ans = "";
        String temp = "", s = "";
        int flag = 0;
        ArrayList<String> store = new ArrayList<>(20000);
        String pathRoot="extarnalFile/WordList_Of_Different_PartsOfSpeach/";
        for (int i = 0; i < arr.size(); i++) {
            flag = 0;
            ans = arr.get(i);

            file = new File(pathRoot+"Noun.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            s = "";
            while ((s = br.readLine()) != null) {
                if (s.equals(ans)) {
                    flag = 1;
                    store.add(ans + " " + "N");
                    if (flag == 1) {
                        break;
                    }
                }
            }
            if (flag == 1) {
                continue;
            }

            file = new File(pathRoot+"Sorbonam.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            s = "";

            while ((s = br.readLine()) != null) {
                if (s.equals(ans)) {
                    flag = 2;
                    store.add(ans + " " + "P");
                    if (flag == 2) {
                        break;
                    }
                }
            }
            if (flag == 2) {
                continue;
            }

            file = new File(pathRoot+"Adjective.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            s = "";
            while ((s = br.readLine()) != null) {
                if (s.equals(ans)) {
                    flag = 3;
                    store.add(ans + " " + "A");
                    if (flag == 3) {
                        break;
                    }
                }
            }
            if (flag == 3) {
                continue;
            }

            file = new File(pathRoot+"Abboy.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            s = "";
            while ((s = br.readLine()) != null) {
                if (s.equals(ans)) {
                    flag = 4;
                    store.add(ans + " " + "C");
                    if (flag == 4) {
                        break;
                    }
                }
            }
            if (flag == 4) {
                continue;
            }

            file = new File(pathRoot+"Verb.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            s = "";
            while ((s = br.readLine()) != null) {
                if (s.equals(ans)) {
                    flag = 5;
                    store.add(ans + " " + "V");
                    if (flag == 5) {
                        break;
                    }
                }
            }
            if (flag == 5) {
                continue;
            }

            file = new File(pathRoot+"Punctions.txt");
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            s = "";
            while ((s = br.readLine()) != null) {
                if (s.equals(ans)) {
                    flag = 6;
                    store.add(ans + " ");
                    if (flag == 6) {
                        break;
                    }
                }
            }
            if (flag == 6) {
                continue;
            }

            if (flag == 0) {
                store.add(" " + ans + " " + "#" + " ");
            }

        }

        ans = "";
        for (String store1 : store) {
            ans += store1;
            ans += " ";
        }
        System.out.println("Grepping Ans: " + ans);
        return ans;
    }
}
