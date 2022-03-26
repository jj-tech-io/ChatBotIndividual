public class question {
    public String genre;
    public int positivePoints;
    public String content;

    public question(String g, int p, String c){
        genre = g;
        positivePoints = p;
        content = c;
    }

    @Override
    public String toString() {
        return this.content;
    }
}