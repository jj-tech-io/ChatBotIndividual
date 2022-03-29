import opennlp.tools.tokenize.SimpleTokenizer;

public class OpenNLP {
    String [] tokens;
    String text;
    public OpenNLP(String text) {
        this.text = text;
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        tokens = tokenizer.tokenize(text);
    }



//    /**
//     * method to find locations in the sentence
//     * @throws IOException
//     */
//    public ArrayList<String> findName() throws IOException {
//        ArrayList<String> names = new ArrayList<>();
//        InputStream is = new FileInputStream("C:\\Users\\JJ\\IdeaProjects\\OpenNLP\\src\\en-ner-person.bin");
//
//        // load the model from file
//        TokenNameFinderModel model = new TokenNameFinderModel(is);
//        // feed the model to name finder class
//        NameFinderME nameFinder = new NameFinderME(model);
//        Span nameSpans[] = nameFinder.find(tokens);
//        POSModel posModel = new POSModel(is);
//        POSTaggerME posTagger = new POSTaggerME(posModel);
//        String tags[] = posTagger.tag(tokens);
//        for (String tag: tags) {
//            if(tag.contains("person")) {
//                System.out.println(tag);
//            }
//        }
//        // nameSpans contain all the possible entities detected
//        int j = 0;
//        for(Span s: nameSpans){
//            System.out.print(s.toString());
//            System.out.print("  :  ");
//            // s.getStart() : contains the start index of possible name in the input string array
//            // s.getEnd() : contains the end index of the possible name in the input string array
//            names.add(tokens[j]);
//            j++;
//            for(int index=s.getStart();index<s.getEnd();index++){
//                System.out.print(tokens[index]+" ");
//
//            }
//
//
//            System.out.println();
//        }
//        return names;
//    }
//
//    /**
//     * method to find locations in the sentence
//     * @throws IOException
//     */
//    public void findLocation() throws IOException {
//        InputStream is = new FileInputStream("C:\\Users\\JJ\\IdeaProjects\\OpenNLP\\src\\en-ner-person.bin");
//
//        // load the model from file
//        TokenNameFinderModel model = new TokenNameFinderModel(is);
//        is.close();
//
//        // feed the model to name finder class
//        NameFinderME nameFinder = new NameFinderME(model);
//
//        // input string array
//        String[] sentence = new String[]{
//                "John",
//                "Smith",
//                "is",
//                "from",
//                "Atlanta",
//                "."
//        };
//
//        Span nameSpans[] = nameFinder.find(sentence);
//
//        // nameSpans contain all the possible entities detected
//        for(Span s: nameSpans){
//            System.out.print(s.toString());
//            System.out.print("  :  ");
//            // s.getStart() : contains the start index of possible name in the input string array
//            // s.getEnd() : contains the end index of the possible name in the input string array
//            for(int index=s.getStart();index<s.getEnd();index++){
//                System.out.print(sentence[index]+" ");
//            }
//            System.out.println();
//        }
//    }
}

