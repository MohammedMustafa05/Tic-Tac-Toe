public class Data {
    private String configuration;
    private int score;

    public Data(String config, int score){
        this.configuration = config;
        this.score = score;
    }

    public String getConfiguration(){
        return configuration;
    }

    public int getScore(){
        return score;
    }
}
