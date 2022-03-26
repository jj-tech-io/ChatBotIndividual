import java.util.Scanner;

public class Patterns {

  //ChatBot chatBot = new ChatBot();
  Person user1 = new Person();

  public String getWelcome(){
    return "Hello I'm ChatBot!\nWhat is your name?";
  }

  public String getIntro() {
    return " is a beautiful name :). \n How can I be of assistance?\n eg.reply: recommendation, rec, book";
  }

  public String getLit(String sentence) {
    String response = sentence;
    if (findKeyword(response, "recommendation") >= 0 || findKeyword(response, "rec") >= 0 ||
            findKeyword(response, "book") >= 0) {
      response = "What type of literature are you interested in?";
    }
    return " ok what to begin.\n" + response;
  }
  public String getRobot(String sentence) {
    String response = sentence;
    if (findKeyword(response, "robot") >= 0 || findKeyword(response, "human") >= 0 ||
            findKeyword(response, "bot") >= 0) {
      response = " ,yes I am a robot, but I'm a good one.\nLet me prove it. How can I help you\n";
    }
    if (findKeyword(response, "joke") >= 0 || findKeyword(response, "jk") >= 0 ||
            findKeyword(response, "laugh") >= 0) {
      response = ", lol you're funny!\n";
    }
    if (findKeyword(response, "annoying") >= 0 || findKeyword(response, "suck") >= 0 ||
            findKeyword(response, "boring")>= 0 || findKeyword(response, "bad") >= 0)  {
      response = ", I'm sorry you feel that way!\n";
    }
    if (findKeyword(response, "annoying") >= 0 || findKeyword(response, "suck") >= 0 ||
            findKeyword(response, "boring")>= 0 || findKeyword(response, "bad") >= 0)  {
      response = ", I'm sorry you feel that way!\n";
    }
    if (findKeyword(response, "who made you") >= 0 || findKeyword(response, "your maker") >= 0 )  {
      response = "Group 27, enrolled in COSC 310!\n";
    }
    if (findKeyword(response, "language") >= 0 || findKeyword(response, "speak") >= 0 )  {
      response = "The only language I speak is english\n";
    }
    if (findKeyword(response, "weather") >= 0 || findKeyword(response, "rainy") >= 0 )  {
      response = "Its beautiful at the moment. Nice and Sunny\n";
    }
    if (findKeyword(response, "how are you") >= 0)  {
      response = "I'm having a great day so far!\nI like to feel useful, tell me what I can do for you\n";
    }
    if (findKeyword(response, "where is your store") >= 0)  {
      response = "The store is currently virtual!\nThere Will be an update when we have more information\n";
    }
    if (findKeyword(response, "I wish I could tell you") >= 0) {
      response = "how awesome things are about to be for you!\n";
    }

    return response;
  }
  public String getGenre(String sentence) {
    String response = sentence;
    if(findKeyword(response, "made up") >= 0 || findKeyword(response, "novels") >= 0 ||
            findKeyword(response, "story") >= 0 || findKeyword(response, "fable") >= 0 ||
            findKeyword(response, "creative writing") >= 0 || findKeyword(response, "narration") >= 0 ||
            findKeyword(response, "fiction") >= 0){

      int max = 3;
      int randNum = (int)(Math.random() * max);
      return switch (randNum) {
        case 0 -> ", here's a taste of the fictional genres in our library!\nAction\nFantasy\nHorror\nMystery\nScience Fiction";
        case 1 -> ", here's a few creative stories!\nThriller\nRomance\nChildren\nComedy";
        case 2 -> ", these novels seem right for you!\nCrime\nParanormal\nWesterns\nAdventure";
        default -> {
          yield "";
          }
        };
      }else if(findKeyword(response, "non fiction") >= 0 || findKeyword(response, "non-fiction") >= 0||
            findKeyword(response, "nonfiction") >= 0 || findKeyword(response, "factual") >= 0||
            findKeyword(response, "true") >= 0 || findKeyword(response, "literal") >= 0){
            int max = 3;
            int randNum = (int)(Math.random() * max);
            return switch (randNum) {
              case 0 -> "Here's a taste of the factual genres in our library!\nHistory\nJournalism\nPhilosophy\nPolitics & Social Sciences";
              case 1 -> "Here's a few fictional genres!\nReligion & Spirituality\nScience\nBiographies\nBusiness & Economics";
              case 2 -> "Just for you fictional genres!\nHealth & Wellness\nSelf Help\nTravel Guides\nCookbooks\nLanguage";
              default -> {
                  yield "";
                  }
            };
          }else response = getRandomResponse() + "\n Please try again!";
    return "Oops! Looks like you typed in a genre that is either:\n1.No longer in stock unavailable genre\nor\n2.Not in our inventory" + response;
  }

//Book information


  public boolean getGenreType(String sentence) {
    String response = sentence;
    if (findKeyword(response, "scifi") >= 0 || findKeyword(response, "science fiction") >= 0 ||
            findKeyword(response, "sci fi") >= 0 || findKeyword(response, "sci-fi") >= 0 ||
            findKeyword(response, "science-fiction") >= 0 || findKeyword(response, "futurism") >= 0 ||
            findKeyword(response, "skiffy") >= 0) {

    }
    System.out.print("");
    return false;
  }
     /*
      int max = 3;
      int randNum = (int)(Math.random() * max);
      return switch (randNum) {
        case 0 -> "Here's a taste of the fictional genres in our library!\nAction\nFantasy\nHorror\nMystery\nScience Fiction";
        case 1 -> "Here's a few creative stories!\nThriller\nRomance\nChildren\nComedy";
        case 2 -> "Just novels for you!\nCrime\nParanormal\nWesterns\nAdventure";
        default -> {
          yield "";
        }
      };
    }else if(findKeyword(response, "non fiction") >= 0 || findKeyword(response, "non-fiction") >= 0||
            findKeyword(response, "nonfiction") >= 0 || findKeyword(response, "factual") >= 0||
            findKeyword(response, "true") >= 0 || findKeyword(response, "literal") >= 0){
      int max = 3;
      int randNum = (int)(Math.random() * max);
      return switch (randNum) {
        case 0 -> "Here's a taste of the factual genres in our library!\nHistory\nJournalism\nPhilosophy\nPolitics & Social Sciences";
        case 1 -> "Here's a few fictional genres!\nReligion & Spirituality\nScience\nBiographies\nBusiness & Economics";
        case 2 -> "Just for you fictional genres!\nHealth & Wellness\nSelf Help\nTravel Guides\nCookbooks\nLanguage";
        default -> {
          yield "";
        }
      };
    }else response = getRandomResponse() + "\n Please try again!";
    return "Oops! Looks like you typed in a genre that is either:\n1.No longer in stock unavailable genre\nor\n2.Not in our inventory" + response;
  }
*/





    //Agent responds based on user's response
  public String getResponse(String sentence){
    String response = sentence;
    if(findKeyword(response , "fiction") >= 0){
      response = " Great choice!\n Which genre are you interested in?\n eg. ";
    }else{
      response = getRandomResponse();
    }
    return " here is what I found\n" + response;
  }
  //Default Responses
  private String getRandomResponse(){
    // generate random numbers within 0 to 10
    int max = 11;
    int randNum = (int) Math.floor((Math.random() * max));

    return switch (randNum) {
      case 0 -> " There are around 130 million published books.";
      case 1 -> "Did you know More human twins are being born now than ever before!";
      case 2 -> "The most sold book is the Bible.";
      case 3 -> "The longest book in the world is ‘Remembrance of Things Past’.";
      case 4 -> "The most expensive book in the world is ‘the Codex Leicester’.";
      case 5 -> "The first ever story written was ‘The Epic of Gilgamesh’.";
      case 6 -> "The largest collection consists of 1.5 million books!";
      case 7 -> "You can read books in lots of different ways including e-books and audiobooks!";
      case 8 -> "The person who draws pictures in books is called an illustrator.";
      case 9 -> "Johannes Gutenberg invented the printing press.";
      case 10 -> "In the Harvard Library, there are three books suspected to be bound in human skin.";
      default -> {
        yield "";
      }
    };
    }

/*
This method is used to convert all letters withing a given word/phase to lowercase.
It checks a keyword is not being recognized as a sub string of another keyword.
eg. 'ok' being recognized in place of 'took'
*/

  private int findKeyword(String sentence, String word, int startPos){
    //removing white unnecessary white spaces in a sentence, for better sentence recognition
    String phrase = sentence.trim();
    //Identifies starting position of each word
    int position = phrase.toLowerCase().indexOf(word.toLowerCase(), startPos);

    //main loop to double checks keyword substring mistakes
    while (position >= 0){
    //Find the string of length 1 before and after the keyword
      String before = " ", after = " ";

      if(position>0){
        before = phrase.substring(position-1, position).toLowerCase();
      }
      if(position+word.length() < phrase.length()){
        after = phrase.substring(position+word.length(), position+word.length()+1).toLowerCase();
      }
      //If before and after are not substrings of a given word, it is the keyword entered
      if(((before.compareTo("a") < 0) || (before.compareTo("z") > 0))
        && ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))){
          return position;
      }
      // Checking each word position within a sentence to ensure all words are verified
      position = phrase.indexOf(word.toLowerCase(), position+1);
  }
  return -1;
}
//Searches for a specific word within a phrase.
// It also checks for substring conditions for the start to end of a given phase
  private int findKeyword(String sentence, String word){

    return findKeyword(sentence, word, 0);
  }
}
