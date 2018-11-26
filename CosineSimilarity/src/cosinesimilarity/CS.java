package cosinesimilarity;


import java.util.ArrayList;

public class CS {

    public static double getCosineSimilarity(  ArrayList<Double> docVector1, ArrayList<Double> docVector2) {

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;

        for (int i = 0; i < docVector1.size(); i++) 
        {
            if( i<docVector2.size() )
            {
                dotProduct += docVector1.get(i) * docVector2.get(i);  
                magnitude1 += Math.pow(docVector1.get(i), 2);
                magnitude2 += Math.pow(docVector2.get(i), 2);
            }
        }
        
        magnitude1 = Math.sqrt(magnitude1);
        magnitude2 = Math.sqrt(magnitude2);

        if (magnitude1 != 0.0 | magnitude2 != 0.0) {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } else {
            return 0.0;
        }
        return cosineSimilarity;
    }
}