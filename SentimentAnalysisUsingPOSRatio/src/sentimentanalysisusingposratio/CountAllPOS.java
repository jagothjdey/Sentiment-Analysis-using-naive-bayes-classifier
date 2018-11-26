package sentimentanalysisusingposratio;

import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_adjective;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_con;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_noun;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_pronoun;
import static sentimentanalysisusingposratio.SentimentAnalysisUsingPOSRatio.total_verb;

public class CountAllPOS {

    public void totalnumberofPOS(String par)
    {
        int i,noun_ratio=0,pronoun_ratio=0,adjective_ratio=0,verb_ratio=0,con_ratio=0;
        for(i=0;i<par.length();i++)
        {
            if( par.charAt(i)=='N' ) noun_ratio++;
            else if(par.charAt(i)=='P') pronoun_ratio++;
            else if(par.charAt(i)=='A') adjective_ratio++;
            else if(par.charAt(i)=='V') verb_ratio++;
            else if(par.charAt(i)=='C') con_ratio++;
        }
        int total = noun_ratio+pronoun_ratio+adjective_ratio+verb_ratio+con_ratio;

        total_noun = (double)noun_ratio/total;
        total_pronoun = (double)pronoun_ratio/total;
        total_adjective = (double)adjective_ratio/total;
        total_verb = (double)verb_ratio/total;
        total_con = (double)con_ratio/total;
    }
}
