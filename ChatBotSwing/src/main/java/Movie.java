public class Movie {
    private String title;
    private String genre;
    private int year;
    private double duration;

    Movie(){

    }

    Movie(String t, String g, int y, double d){
        title = t;
        genre = g;
        year = y;
        duration = d;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }

    public void setYear(int y){ year = y;}
    public int getYear() {
        return year;
    }

    public void setDuration(double dur) {duration = dur;}
    public double getDuration(){ return duration; }


    public String getMovieDetails(){
        return "Title: " + title + "\t\tGenre: " + genre +  "\t\tYear: " + year + "\t\tDuration: " + duration;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}