package sentimentanalysisusingposratio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class GrepppingMethod {

    public void grepppingMethod(String path) throws FileNotFoundException, IOException {
//        System.out.println(MainClass.input_file_path);
//        String readfilename="ReadFile/"+"read"+ String.valueOf(loop)+".txt";
        String writefilename = "OutputUsingGreppingMethod.txt";

        File file = new File(path);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GrepppingMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);

        String aa = "", s = "";

        try {
            while ((s = br.readLine()) != null) {
                aa += s;
            }
        } catch (IOException ex) {
            Logger.getLogger(GrepppingMethod.class.getName()).log(Level.SEVERE, null, ex);
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

        ArrayList<String> arr = new ArrayList<String>(20000);
        StringTokenizer st = new StringTokenizer(aa1, " ");

        String ans = "";
        while (st.hasMoreElements()) {
            ans = (String) st.nextToken();
            arr.add(ans);
        }

        ans = "";
        String temp = "";
        int flag = 0;
        ArrayList<String> store = new ArrayList<String>(20000);
        for (int i = 0; i < arr.size(); i++) {
            flag = 0;
            ans = arr.get(i);

            file = new File("WordList_Of_Different_PartsOfSpeach/Noun.txt");
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

            file = new File("WordList_Of_Different_PartsOfSpeach/Sorbonam.txt");
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

            file = new File("WordList_Of_Different_PartsOfSpeach/Adjective.txt");
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

            file = new File("WordList_Of_Different_PartsOfSpeach/Abboy.txt");
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

            file = new File("WordList_Of_Different_PartsOfSpeach/Verb.txt");
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

            file = new File("WordList_Of_Different_PartsOfSpeach/Punctions.txt");
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

        File output = new File(writefilename);
        output.createNewFile();
        FileWriter fw = null;
        try {
            fw = new FileWriter(output);
        } catch (IOException ex) {
            Logger.getLogger(GrepppingMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter writer = new BufferedWriter(fw);

        ans = "";
        for (int i = 0; i < store.size(); i++) {
            ans += store.get(i);
            ans += " ";
        }
        writer.write(ans);
        writer.flush();
        writer.close();
        System.out.println(ans);
        
    }
}
