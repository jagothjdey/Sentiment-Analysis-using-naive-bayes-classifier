package naivebayes;

/**
 *
 * @author Sm19
 */
public class DoublePair {

    private double posScore;
    private double negScore;

    public DoublePair() {
    }

    public DoublePair(double posScore, double negScore) {
        this.posScore = posScore;
        this.negScore = negScore;
    }

    public double getPosScore() {
        return posScore;
    }

    public void setPosScore(double posScore) {
        this.posScore = posScore;
    }

    public double getNegScore() {
        return negScore;
    }

    public void setNegScore(double negScore) {
        this.negScore = negScore;
    }
}
