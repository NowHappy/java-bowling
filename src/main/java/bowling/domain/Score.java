package bowling.domain;

public class Score {
    private final ScoreValue score;
    private final ScoreBonus bonus;

    private Score(ScoreValue score, ScoreBonus bonus) {
        this.score = score;
        this.bonus = bonus;
    }

    public static Score init() {
        return new Score(ScoreValue.init(), ScoreBonus.init());
    }

    public static Score of(int score) {
        return new Score(ScoreValue.of(score), ScoreBonus.display());
    }

    public static Score next(ScoreValue score, ScoreBonus bonusCount) {
        return new Score(score, bonusCount);
    }

    public static Score strike() {
        return new Score(ScoreValue.of(Pin.MAX_PIN_COUNT), ScoreBonus.max());
    }

    public Score next() {
        return next(score, ScoreBonus.init());
    }

    public boolean canCalculate() {
        return bonus.noLeft();
    }

    public int getScore() {
        return this.score.getScore();
    }

    public Score toScore(int score) {
        return next(ScoreValue.of(score), bonus);
    }

    public Score addScore(Score previous) {
        return Score.next(score.add(previous.score), previous.bonus.decrease());
    }

    public ScoreValue addScoreValue(Score previous) {
        return score.add(previous.score);
    }

}
