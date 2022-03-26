import java.util.ArrayList;
import java.util.Arrays;

public class Gallery {
    private ArrayList<Movie> movieList = new ArrayList<>();

    public Gallery() {
        movieList.add(new Movie("Eternals", "Action", 2021, 2.62));
        movieList.add(new Movie("Dune", "Sci-Fi", 2021, 2.58));
        movieList.add(new Movie("Perfect Blue", "Drama", 1998, 2));
        movieList.add(new Movie("Howl's Moving Castle - The Animation", "Fantasy", 2004, 2));
        movieList.add(new Movie("The Fellowship of The Rings", "Fantasy", 2001, 3));
        movieList.add(new Movie("Slender Man", "Horror", 2018, 1.6));
        movieList.add(new Movie("Titanic", "Romance", 1997, 3));
        movieList.add(new Movie("E.T", "Sci-Fi", 1982, 2));
        movieList.add(new Movie("Hathaway's Flash part 1", "Sci-Fi", 2021, 1.5));
        movieList.add(new Movie("Scary movie", "Comedy", 2000, 1.5));
        movieList.add(new Movie("LA 92", "Education", 2017, 2));
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }


    //Return arrayList of movies by specific genre in gallery.
    public ArrayList<Movie> getGeneraList(String genera) {
        ArrayList<Movie> generaList = new ArrayList<Movie>();
        for (Movie m : movieList) {
            if (m.getGenre().equalsIgnoreCase(genera)) { //Don't use == to compare strings in Java, please. Unless you're comparing if they're pointing to the same object.
                generaList.add(m);
            }
        }
        return generaList;
    }

    //Specially made for callers that requires a list instead of arraylist
    public String[] getAllGeneras() {
        String[] allGenres;
        ArrayList<Movie> genres = new ArrayList<>();
        for (Movie m : movieList) {
            if (!checkDup(m, genres)) {
                genres.add(m);
            }
        }
        allGenres = new String[genres.size()];
        for (int i = 0; i < allGenres.length; i++) {
            allGenres[i] = genres.get(i).getGenre();
        }
        return allGenres;
    }

    //Return arrayList of strings of movie titles in gallery.
    public ArrayList<String> getTitleList(ArrayList<Movie> movies) {
        ArrayList<String> titles = new ArrayList<>();
        for (Movie m : movies) {
            titles.add(m.getTitle());
        }
        return titles;
    }


    public Movie byTitle(String s) { //In the future return list, from which user can pick from.
        Movie m = new Movie();
        ArrayList<Movie> temp = new ArrayList<Movie>();
        int a = 1;
        int ran = 0;
        for (int i = 0; i < getMovieList().size(); i++) {
            if (s.equalsIgnoreCase(getMovieList().get(i).getTitle())) {
                temp.add(getMovieList().get(i));
            }
        }
        if (temp.size() > 0) {
            ran = (int) (Math.random() * temp.size());
            m = temp.get(ran);
        }

        return m;
    }

    //Checks for dupes in get genre methods
    //Returns true if a duplicate is found and false otherwise
    public boolean checkDup(Movie in, ArrayList<Movie> out) {
        for (int i = 0; i < out.size(); i++) {
            if (in.getGenre().equalsIgnoreCase(out.get(i).getGenre())) {
                return true;
            }
        }
        return false;
    }
}
