import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import static java.awt.Color.black;

public class GUI<JTimer> implements ActionListener {
    //
    static PCA pca;
    static GoogleNLP parse;
    static String browseMovies = "movies";
    static String browseBooks = "books";
    static String trivia = "trivia";
    static String request = "request";
    static String objective = "";
    static String sentiment = "Neutral";
    static String userMsg;
    static String cbMsg;
    static boolean IN = true;
    static boolean outterRun = true;
    static boolean innerRun = true;
    static Trivia myTrivia;
    //
    Timer timer;
    int cb_user = 0;
    int numClicks = 0;
    JPanel top;
    JPanel bot;
    GridLayout mainGrid = new GridLayout(2,1);
    GridLayout topgrid = new GridLayout(1,1);
    GridLayout botgrid = new GridLayout(3,2);
    static ImageIcon img;
    JFrame frame;
    JButton btnSend;
    JPanel panel;

    static JLabel emptyT1 = new JLabel("   ");
    static JLabel emptyT2 = new JLabel("   ");
    static JLabel emptyB1 = new JLabel("   ");
    static JLabel emptyB2 = new JLabel("   ");
    static JTextArea textArea;
    JScrollPane scrollPane;
    static JTextArea textInput;
    int TOP = 300;
    int BOT = 300;
    int LEFT = 300;
    int RIGHT = 300;

    public GUI() {
        emptyT1.setSize(new Dimension(500, 20));
        emptyT2.setSize(new Dimension(100, 20));
        emptyB1.setSize(new Dimension(500, 20));
        emptyB2.setSize(new Dimension(100, 20));
        top = new JPanel();
        bot = new JPanel();
        top.setLayout(topgrid);
        botgrid.setHgap(10);
        botgrid.setVgap(10);
        topgrid.setHgap(10);
        topgrid.setVgap(10);
        bot.setLayout(botgrid);
        top.setPreferredSize(new Dimension(800,400));
        bot.setPreferredSize(new Dimension(800,100));
        textArea = new JTextArea(100,6);
        textArea.setAutoscrolls(true);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        top.add(scrollPane);
        bot.add(emptyT1);
        bot.add(emptyT2);
        textInput = new JTextArea();
        textInput.setPreferredSize(new Dimension(500, 40));
        bot.add(textInput);
        btnSend = new JButton("Send");
        btnSend.setPreferredSize(new Dimension(80, 40));
        btnSend.setBackground(new Color(0,204,255));
        btnSend.addActionListener(this);
        bot.add(btnSend,Component.LEFT_ALIGNMENT);
        bot.add(emptyB1);
        bot.add(emptyB2);
        img=new ImageIcon("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\bot.png");
        frame = new JFrame();
        frame.setIconImage(img.getImage());
        frame.setPreferredSize(new Dimension(850,550));
        frame.setTitle("ChatBot");
        frame.add(top, BorderLayout.NORTH);
        frame.add(bot);
        frame.setBackground(new Color(0,204,255));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new GUI();
        Library library = new Library();
        ChatBot chatBot = new ChatBot();
        Person user1 = new Person();
        getCBM(chatBot.getStatement(0));
        while(outterRun) {
            while(innerRun) {
                getCBM("Would you like to: browse books, browse movies, play trivia, or request an item?");
                getUserIN();
                parse = new GoogleNLP(userMsg);
                ArrayList<String> option = parse.getWords();
                if (option.contains(browseMovies)) {
                    getCBM("You have selected: browse movies, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = browseMovies;
                        break;
                    } else {
                        continue;
                    }
                } else if (option.contains(browseBooks)) {

                    getCBM("You have selected: browse books, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = browseBooks;
                        break;
                    } else {
                        continue;
                    }
                } else if (option.contains(trivia)) {
                    getCBM("You have selected: trivia, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = trivia;
                        break;
                    } else {
                        continue;
                    }
                } else if (option.contains(request)) {
                    getCBM("You have selected: request a specific item, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = request;
                        break;
                    } else {
                        continue;
                    }
                }
            }
            System.out.println("Done initial branch: " + objective);
            outterRun = true;
            innerRun = true;
            while(innerRun) {
                if (objective.equalsIgnoreCase(trivia)) {
                    getCBM("Now starting Trivia :)");
                    myTrivia = new Trivia();
                    myTrivia.play();
                } else if (objective.equalsIgnoreCase(browseBooks)) {
                    cbMsg = "What is your favorite genera?";
                    getCBM(cbMsg);
                    getUserIN();
                    user1.setFavoriteGenera(userMsg);
                    pca = new PCA(user1.getUserVector());
                    user1.setUserVector();
                    user1.setPcaVector(pca.getStandardUser());
                    user1.setTopThree(pca.getTopThree());
                    chatBot.loopGeneraTitle(user1, pca, pca.getTopThree(), false);
                } else if (objective.equalsIgnoreCase(browseMovies)) {
                    cbMsg = "What is your favorite genera?";
                    getCBM(cbMsg);
                    getUserIN();
                    user1.setFavoriteGenera(userMsg);
                    pca = new PCA(user1.getUserVector());
                    user1.setUserVector();
                    user1.setPcaVector(pca.getStandardUser());
                    user1.setTopThree(pca.getTopThree());
                    chatBot.loopGeneraTitleMovie(user1, pca, pca.getTopThree(), false);
                } else if (objective.equalsIgnoreCase(request)) {
                    cbMsg = "What book would you like to search for?";
                    getCBM(cbMsg);
                    getUserIN();
                    parse = new GoogleNLP(userMsg);
                    ArrayList<String> words = parse.getWords();
                    boolean hit = false;
                    String search = " ";
                    for(String word : words) {
                        System.out.println(word);
                        if(library.byTitle(word).getTitle()!=null) {
                            hit = true;
                            search = word;
                        }
                    }
                    if(hit) {
                        cbMsg = "Added " + search + " to cart";
                        getCBM(cbMsg);
                    }
                    else {
                        cbMsg = "Sorry, we don't have that one";
                        getCBM(cbMsg);
                    }
                }
                cbMsg = "Thank you for using this service, would you like to continue browsing?";
                getCBM(cbMsg);
                getUserIN();
                Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                if (yes>0f) {
                    innerRun = true;
                    outterRun = true;
                    break;
                } else {
                    innerRun = false;
                    outterRun = false;
                    break;
                }
            }
        }
    }
    public static void getCBM(String m) throws InterruptedException {
        Thread.sleep(10);
        cbMsg = "Chat Bot: "+m+"\n";
        textArea.append(cbMsg);
    }
    public static void getUserIN() throws InterruptedException {
        while(IN) {
            Thread.sleep(10);
        }
        userMsg = textInput.getText().toLowerCase(Locale.ROOT);
        textArea.append("User: "+userMsg + "\n");
        textInput.setText("");
        IN = true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        IN = false;
    }
}
