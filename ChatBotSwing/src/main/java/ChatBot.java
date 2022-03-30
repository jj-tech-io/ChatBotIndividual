import java.io.IOException;
import java.util.*;

public class ChatBot {
	private String browseMovies = "browse movies";
	private String browseBooks = "browse books";
	private String trivia = "trivia";
	private String request = "request";
	Library library = new Library();
	Gallery gallery = new Gallery();
	Scanner sc = new Scanner(System.in);
	private String p1 = "yes";
	private String p2 = "yeah";
	private String p3 = "yep";
	private String p4 = "yeet";
	private String p5 = "sure";
	private String p6 = "y";
	private ArrayList<String> positiveFeedBack = new ArrayList<>();
	private Person person;
	private ArrayList<String> statements = new ArrayList<>();
	String reply;
	public ChatBot(){
		statements.add("Hello, my name is Haro your personal entertainment assistant"); //0
		statements.add("I would recommend the following book(s): "); //1
		statements.add("May I suggest %s(type yes/y to add to checkout, otherwise type no/n): "); //2
		statements.add("Today you've checked out the following book(s): "); //3
		statements.add("Today you've checked out the following movie(s): "); //4
		statements.add("Would you require additional service? (Type yes/y for more features)"); //5
		positiveFeedBack.add(p1);
		positiveFeedBack.add(p2);
		positiveFeedBack.add(p3);
		positiveFeedBack.add(p4);
		positiveFeedBack.add(p5);
		positiveFeedBack.add(p6);

	}
	public static int getInt() {
		return 2;
	}
	public Float testReaction(String reply) throws IOException {
		Float sentiment = 0.1f;
		GoogleNLP parse = new GoogleNLP(reply);
		ArrayList<Float> sentList = parse.getSentimentList();
		if(sentList.size()>0) {
			sentiment = sentList.get(0);
		}
		return sentiment;
	}
	public void weatherSearchReport() throws InterruptedException, IOException {
		boolean weatherLoop = true;
		while (weatherLoop) {
			GUI.cbMsg = "What day and place would you like weather for?";
			GUI.getCBM(GUI.cbMsg);
			GUI.getUserIN();
			reply = GUI.userMsg;
			WeatherDataService weatherDataService = new WeatherDataService();
			weatherDataService.getJson("https://www.metaweather.com/api/location/44418/");
			ArrayList<String> weekForcast = weatherDataService.weatherLookAhead;
			if (reply.contains("tomorrow")) {
				GUI.cbMsg = "Tomorrow's weather is: " + weekForcast.get(0) + "\n Any other time/place you would like to search?";
				GUI.getCBM(GUI.cbMsg);
			} else {
				String m = "This week looks as follows: \n";
				GUI.cbMsg = m + weekForcast+ "\n Any other time/place you would like to search?";
				GUI.getCBM(GUI.cbMsg);
			}
			GUI.getUserIN();
			reply = GUI.userMsg;
			Float continueBrowsing = testReaction(reply);
			if (continueBrowsing < 0f) weatherLoop = false;
		}

	}
	//.next usage noted
	//PCA loop until user picks a book.
	public void loopGeneraTitle(Person person,PCA pca, ArrayList<String> suggest, boolean last) throws InterruptedException, IOException {
		boolean happy = false;
		Float addToCart = 0f;
		Float continueBrowsing = 0f;
		int loopNum = 0;
		for (String s: suggest) {
			if(loopNum>suggest.size()) {
				return;
			}
			getConsolation(loopNum);
			GUI.cbMsg = "Would you like to browse something in our "+s + " section?";
			GUI.getCBM(GUI.cbMsg);
			GUI.getUserIN();
			reply = GUI.userMsg;
			Float yes = testReaction(reply); //can pass string here instead
			if (yes>0f) {
				GUI.cbMsg = "That's great";
				GUI.getCBM(GUI.cbMsg);
				ArrayList<String> titles = library.getTitleList(library.getGeneraList(s));
				for (String t:titles) {
					GUI.cbMsg = "Can i suggest: "+t+ " ?";
					GUI.getCBM(GUI.cbMsg);
					GUI.getUserIN();
					GUI.getUserIN();
					reply = GUI.userMsg;
					addToCart = testReaction(reply);
					if(addToCart>0f) {
						person.updateTempBookList(library.byTitle(t));
						GUI.cbMsg = "Added the book: " + t + " to checkout list. \n Continue browsing?";
						GUI.getCBM(GUI.cbMsg);
						GUI.getUserIN();
						reply = GUI.userMsg;
						continueBrowsing = testReaction(reply);
						if(continueBrowsing<0f) {
							return;
						}
						else {
							continue;
						}
					}
				}
			}
			loopNum++;
		}
	}
	public void loopGeneraTitleMovie(Person person, PCA pca, ArrayList<String> suggest, boolean last) throws InterruptedException, IOException {

		Scanner sc = new Scanner(System.in);

		int loopNum = 0;
		for (String s: suggest) {
			if(loopNum>suggest.size()) {
				return;
			}
			GUI.cbMsg = "Would you like to browse something in our "+s + " section?";
			GUI.getCBM(GUI.cbMsg);
			GUI.getUserIN();
			reply = GUI.userMsg;
			Float yes = testReaction(reply); //can pass string here instead
			if (yes>0f) {
				GUI.cbMsg = "Thats great";
				GUI.getCBM(GUI.cbMsg);
				ArrayList<String> titles = gallery.getTitleList(gallery.getGeneraList(s));
				for (String m:titles) {
					GUI.cbMsg = "Can i suggest: "+m+ " ?";
					GUI.getCBM(GUI.cbMsg);
					GUI.getUserIN();
					GUI.getUserIN();
					reply = GUI.userMsg;
					yes = testReaction(reply); //can pass string here instead
					if (yes>0f) {
						person.updateTempMovieList(gallery.byTitle(m));
						GUI.cbMsg = "Added the movie: " + m + " to checkout list. \n Continue browsing?";
						GUI.getCBM(GUI.cbMsg);
						GUI.getUserIN();
						reply = GUI.userMsg;
						Float continueBrowse = testReaction(reply); //can pass string here instead
						if (continueBrowse<0f) {
							return;
						}
						else {
							continue;
						}
					}
				}
			}
			loopNum++;
		}

	}

	public String getAutonomous(String text) {
		Patterns patterns = new Patterns();
		String rand = patterns.getRobot(text);
		return rand;
	}
	public String getConsolation(int loopNum) {

		String consolation = " ";
		switch (loopNum) {
			case(1): {
				consolation = "Searching...";
				break;
			}
			case(2): {
				consolation = "Ok, Searching...";
				break;
			}
			case(3): {
				consolation = "Ok, lets try, Searching...";
				break;
			}
			default: {
				consolation = "Searching";
			}

		}
		return consolation;
	}

	public String getStatement(int i) {
		String q = "";

		if (statements.size() >i ) {
			q = statements.get(i);
			if (q.contains("%s")) {
				q = String.format(q,"");
			}
			return q;

		}
		else
			return "I can't think of anything else";
	}

	private ArrayList<Person> people = new ArrayList<>();

	public String askByName(String question, String name) {
		String out = String.format(question,name);
		return out;
	}

}