package sentimentanalysisusingposratio;

import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.all_comment;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.negative;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.positive;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_adjective;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_con;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_noun;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_pronoun;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_verb;

public class TaggingCorpusData {
    public static void taggingcourpusdatamethod()
    {
        FileReadMethod filereadmethod = new FileReadMethod();
        CountAllPOS countallpos = new CountAllPOS();
       all_comment= filereadmethod.ReadFile("PositiveCommentAfterPOSTag.txt");
       countallpos.totalnumberofPOS(all_comment);
       positive.add(total_noun); positive.add(total_adjective); positive.add(total_verb);  positive.add(total_pronoun);  positive.add(total_con);
       System.out.println("Positve:");
       for(int i=0;i<positive.size();i++)
      {
          System.out.println(positive.elementAt(i));
      }
       all_comment =  filereadmethod.ReadFile("NegativeCommentAfterPOSTag.txt");
      countallpos.totalnumberofPOS(all_comment);
      negative.add(total_noun); negative.add(total_adjective); negative.add(total_verb); negative.add(total_pronoun);  negative.add(total_con);
         System.out.println("Negative:");
      for(int i=0;i<negative.size();i++)
      {
          System.out.println(negative.elementAt(i));
      }
    }
}
