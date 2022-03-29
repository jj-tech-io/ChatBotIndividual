package com.example.chatbot2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatBot {
	private String browseMovies = "browse movies";
	private String browseBooks = "browse books";
	private String trivia = "trivia";
	private String request = "request";
	Library library = new Library();
	Gallery gallery = new Gallery();
	private String p1 = "yes";
	private String p2 = "yeah";
	private String p3 = "yep";
	private String p4 = "yeet";
	private String p5 = "sure";
	private String p6 = "y";
	private ArrayList<String> positiveFeedBack = new ArrayList<>();
	private Person person;
	private ArrayList<String> statements = new ArrayList<>();
	GoogleNLP parse;
	String userReply;
	String reply;



	public boolean testReaction(String userReply) throws IOException {
		boolean happy = false;
		parse = new GoogleNLP(userReply);
		Float sentiment = parse.getSentiment();
		if (sentiment >0) {
			happy = true;
		}
		return happy;
	}


	public ChatBot() {
		person = new Person();
		statements.add("Hello, my name is Haro your personal entertainment assistant"); //0
		statements.add("Would you like to: browse books, browse movies, play trivia, or request an item?"); //1
		statements.add("You requested %s. Is that right?"); //2
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

	public String getObjective() throws IOException {
		String objective = "redo";
		parse = new GoogleNLP(userReply);
		List<String> reply = parse.getWords();
		for (String s : reply) {
			if (s.equalsIgnoreCase(browseMovies)) {
				objective = browseMovies;
			} else if (s.equalsIgnoreCase(browseBooks)) {
				objective = browseBooks;
			} else if (s.equalsIgnoreCase(trivia)) {
				objective = trivia;
			} else if (s.equalsIgnoreCase(request)) {
				objective = request;
			}

		}
		return objective;


	}
	//.next usage noted
	//PCA loop until user picks a book.
	public void loopGeneraTitle(Person person, PCA pca, ArrayList<String> suggest, boolean last) throws InterruptedException, IOException {
		boolean happy = false;
		boolean addToCart = false;
		boolean continueBrowsing = false;
		int loopNum = 0;
		for (String s: suggest) {
			if(loopNum>suggest.size()) {
				return;
			}
			getConsolation(loopNum);
			MainActivity.cbMsg = "Would you like to browse something in our "+s + " section?";
			MainActivity.getCBM();
			MainActivity.getUserIN();
			reply = MainActivity.userMsg;
			boolean yes = testReaction(reply); //can pass string here instead
			if (yes) {
				MainActivity.cbMsg = "That's great";
				MainActivity.getCBM();
				ArrayList<String> titles = library.getTitleList(library.getGeneraList(s));
				for (String t:titles) {
					MainActivity.cbMsg = "Can i suggest: "+t+ " ?";
					MainActivity.getCBM();
					MainActivity.getUserIN();

					reply = MainActivity.userMsg;
					addToCart = testReaction(reply);
					if(addToCart) {
						person.updateTempBookList(library.byTitle(t));
						MainActivity.cbMsg = "Added the book: " + t + " to checkout list. \n Continue browsing?";
						MainActivity.getCBM();
						MainActivity.getUserIN();
						reply = MainActivity.userMsg;
						continueBrowsing = testReaction(reply);
						if(continueBrowsing) {
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
		boolean happy = false;
		boolean addToCart = false;
		boolean continueBrowsing = false;
		int loopNum = 0;
		for (String s: suggest) {
			if(loopNum>suggest.size()) {
				return;
			}
			getConsolation(loopNum);
			MainActivity.cbMsg = "Would you like to browse something in our "+s + " section?";
			MainActivity.getCBM();
			MainActivity.getUserIN();
			reply = MainActivity.userMsg;
			boolean yes = testReaction(reply); //can pass string here instead
			if (yes) {
				MainActivity.cbMsg = "That's great";
				MainActivity.getCBM();
				//ArrayList<String> titles = library.getTitleList(library.getGeneraList(s));
				ArrayList<String> titles = gallery.getTitleList(gallery.getGeneraList(s));
				for (String m:titles) {
					MainActivity.cbMsg = "Can i suggest: "+m+ " ?";
					MainActivity.getCBM();
					MainActivity.getUserIN();
					reply = MainActivity.userMsg;
					yes = testReaction(reply); //can pass string here instead
					if (yes) {
						person.updateTempMovieList(gallery.byTitle(m));
						MainActivity.cbMsg = "Added the movie: " + m + " to checkout list. \n Continue browsing?";
						MainActivity.getCBM();
						MainActivity.getUserIN();
						reply = MainActivity.userMsg;
						continueBrowsing = testReaction(reply); //can pass string here instead
						if (continueBrowsing) {
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

	public String getRandom(String text) {

		String rand = "Did you know....";
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