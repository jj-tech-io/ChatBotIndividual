import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

public class GUI<JTimer> extends JFrame implements ActionListener {
    //jj
    static PCA pca;
    static GoogleNLP parse;
    static String browseMovies = "movies";
    static String browseBooks = "books";
    static String trivia = "trivia";
    static String request = "request";
    static String weather = "weather";
    static String search = "search";
    static String objective = "";
    static String sentiment = "Neutral";
    static String userMsg;
    static String cbMsg;
    static boolean IN = true;
    static boolean outterRun = true;
    static boolean innerRun = true;
    static Trivia myTrivia;
    static ImageIcon img;
    static BufferedImage botPicture;
    static int lines = 0;
    JFrame frame;
    JButton btnSend;
    JPanel panel;


    static JTextArea textArea;
    static JScrollPane scrollPane;
    static JTextArea textInput;

    static JLabel picBot;
    static JLabel picTemp;
    static JLabel picWeather;
    static JLabel picSun;
    static JLabel picRain;
    static JLabel picCloud;
    static JLabel picMix;
    static JPanel topPanel;
    static JLabel weatherLabel;
    static String current, high, low;
    public GUI(String title) throws IOException {

        super(title);
        this.setSize(850,650);
        this.setLocation(100,100);
        //JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.BLACK);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        //components
        textArea = new JTextArea(100,6);
        textArea.setAutoscrolls(true);
        textArea.setFont(new Font("segoi",1,14));
        textArea.setForeground(Color.BLACK);
        scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.setAutoscrolls(true);
        textInput = new JTextArea();
        textInput.setBackground(Color.WHITE);
        textInput.setFont(new Font("segoi",1,18));
        btnSend = new JButton("Send");
        btnSend.setBackground(Color.decode("#ccff33"));
        btnSend.addActionListener(this::actionPerformed);
        high = "";
        low = "";

        weatherLabel = new JLabel(current);
        weatherLabel.setFont(new Font("segoi",1,18));
        weatherLabel.setForeground(Color.BLACK);




        botPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\bot.png"));
        BufferedImage haroPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\haro.png"));
        BufferedImage sunPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\sun.png"));
        BufferedImage rainPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\rain.png"));
        BufferedImage cloudPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\cloudy.png"));
        BufferedImage mixPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\sunANDrain.png"));
        BufferedImage thermometerPicture = ImageIO.read(new File("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\thermometer.png"));
        JLabel picHaro = new JLabel(new ImageIcon(haroPicture));
        picHaro.setSize(new Dimension(80,80));
        picBot = new JLabel(new ImageIcon(botPicture));
        picTemp = new JLabel(new ImageIcon(thermometerPicture));
        picSun = new JLabel(new ImageIcon(sunPicture));
        picCloud = new JLabel(new ImageIcon(cloudPicture));
        picMix = new JLabel(new ImageIcon(mixPicture));
        picRain = new JLabel(new ImageIcon(rainPicture));
        picWeather = new JLabel();

        //Panel Top
        topPanel = new JPanel();
        topPanel.setBorder(new LineBorder(Color.BLACK,1));
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setLayout(new FlowLayout(5));
        topPanel.setPreferredSize(new Dimension(650,150));
        topPanel.add(picHaro,BorderLayout.PAGE_START);
        topPanel.add(weatherLabel,BorderLayout.CENTER);
        topPanel.add(picTemp,BorderLayout.PAGE_END);
        //topPanel.add(picWeather);


        //topPanel.add(picRain,BorderLayout.CENTER);
        mainContainer.add(topPanel,BorderLayout.NORTH);

        //panel middle
        JPanel middlePanel = new JPanel();
        middlePanel.setBorder(new LineBorder(Color.BLACK,1));
        middlePanel.setLayout(new FlowLayout(4,4,4));
        middlePanel.setBackground(Color.BLACK);
        mainContainer.add(scrollPane,BorderLayout.CENTER);

        //Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new LineBorder(Color.BLACK,4));
        bottomPanel.setLayout(new GridLayout(1,4,5,1));
        bottomPanel.setBackground(Color.BLACK);

        textInput.setColumns(2);
        bottomPanel.add(textInput);
        bottomPanel.add(btnSend);
        bottomPanel.setPreferredSize(new Dimension(650,75));
        //bottomPanel.add(bottomPanel, BorderLayout.CENTER);
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.setBorder(new LineBorder(Color.BLACK,1));
        mainContainer.add(bottomPanel, BorderLayout.SOUTH);

        img=new ImageIcon("C:\\Users\\JJ\\IdeaProjects\\ChatBotSwing\\src\\main\\resources\\bot.png");
        this.setIconImage(img.getImage());

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        WeatherDataService weatherDataService = new WeatherDataService();
        //weatherDataService.getJson("https://jsonplaceholder.typicode.com/albums");
        weatherDataService.getJson("vancouver");
        current = weatherDataService.currentWeather;
        GUI gui = new GUI("Chat Bot");


        gui.setVisible(true);
        Library library = new Library();
        ChatBot chatBot = new ChatBot();
        Person user1 = new Person();
        getCBM(chatBot.getStatement(0));
        while(outterRun) {
            while(innerRun) {
                getCBM("Would you like to: \nbrowse books, browse movies, search websites, search weather, play trivia, or request an item?");
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
                }
                else if (option.contains(weather)) {
                    getCBM("You have selected: weather, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = weather;
                        break;
                    } else {
                        continue;
                    }
                }else if (option.contains(request)) {
                    getCBM("You have selected: request a specific item, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = request;
                        break;
                    } else {
                        continue;
                    }
                } else if (option.contains(search)) {
                    getCBM("You have selected: search websites, is that right?");
                    getUserIN();
                    Float yes = chatBot.testReaction(userMsg); //can pass string here instead
                    if (yes>0f) {
                        objective = search;
                        break;
                    } else {
                        continue;
                    }
                }
            }
            //System.out.println("Done initial branch: " + objective);
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
                }
                else if (objective.equalsIgnoreCase(weather)) {
                    chatBot.weatherSearchReport();
                    weatherLabel.updateUI();
                }else if (objective.equalsIgnoreCase(search)) {
                    chatBot.searchWeb();

                }else if (objective.equalsIgnoreCase(request)) {
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
                cbMsg = "Thank you for using this service, would you like to continue?";
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
        lines++;
        Thread.sleep(10);

        cbMsg = "Haro: "+m+"\n";
        textArea.append(cbMsg);
    }
    public static void getUserIN() throws InterruptedException {
        lines++;
        while(IN) {
            Thread.sleep(10);
        }
        userMsg = textInput.getText().toLowerCase(Locale.ROOT);
        textArea.append("User: "+userMsg + "\n");
        textInput.setText("");
        IN = true;
    }
    public JLabel getWeatherIcon() throws IOException {
        WeatherDataService weather = new WeatherDataService();
        ArrayList<String> w = weather.getJson("vancouver");
        String tomorrow = w.get(0);
        picWeather = picSun;
        if(tomorrow.contains("rain")) {
            picWeather = picRain;
        }
        else if(tomorrow.contains("cloud")) {
            picWeather = picCloud;
        }
        else if(tomorrow.contains("sun")&&tomorrow.contains("rain")) {
            picWeather = picMix;
        }
        else if(tomorrow.contains("sun")) {
            picWeather = picSun;
        }
        System.out.println(tomorrow);
        return picWeather;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        weatherLabel.setText(current);
        IN = false;
    }

}
