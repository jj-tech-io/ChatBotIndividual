# Assignment4
Chat Bot Individual Project

## [Table of Contents]

<br/>Description
<br/>Getting Started
<br/>Dependencies
<br/>Installing
<br/>Help
<br/>Authors
<br/>Version History
<br/>Acknowledgments

## Description
Book, movie and trivia chat bot
![image](https://lucid.app/publicSegments/view/6d0c57e3-42de-4019-acc1-cb786ad8ae6d/image.png)

Chat bot for online library book suggestion, weather, web search and trivia:

Desk Top:
https://github.com/jj-tech-io/ChatBotIndividual/tree/master/ChatBotSwing

Android:
https://github.com/jj-tech-io/ChatBotIndividual/tree/master/ChatBotAndroid/app

Bot intelligently responds to natural language input and suggests generas based on 
Sentiment Analysis, Name Recognision: Google Cloud NLP API
Web Search: Google Custom Search API
Weather Reporting: Meta Weather API
Principle Component Analysis: 
https://github.com/COSC-310-Team-27/Assignment2/blob/main/Python_PCA/PCA_encode_decode.ipynb
and Euclidian proximity to nearest standard user vector: 
https://github.com/COSC-310-Team-27/Assignment2/blob/main/Console/src/com/company/PCA.java 
and a number of other features found in the console folder. For those who are more adventurous could partake in a quiz that will be more competitive in part 2.

## Getting Started
1. Pull repository or just the chatbotswing directory in main branch. 
2. If you're on Intellij, create a new maven project.
3. Copy and paste src from repository to project src folder, copy and paste pom.xml from repository to project pom.xml.


### Dependencies
Maven:
   <dependencies>
        <dependency>
            <groupId>edu.stanford.nlp</groupId>
            <artifactId>stanford-corenlp</artifactId>
            <version>4.4.0</version>
        </dependency>
        <dependency>
            <groupId>edu.stanford.nlp</groupId>
            <artifactId>stanford-corenlp</artifactId>
            <version>4.4.0</version>
            <classifier>models</classifier>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-nop</artifactId>
            <version>1.6.2</version>
            <scope>nlp2</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>3.8.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.opennlp</groupId>
            <artifactId>opennlp-tools</artifactId>
            <version>1.9.4</version>
        </dependency>

        <dependency>
            <groupId>com.google.cloud</groupId>
            <artifactId>google-cloud-language</artifactId>
            <version>1.14.0</version>
        </dependency>

        <dependency>
            <groupId>org.json</groupId>
            <artifactId>json</artifactId>
            <version>20220320</version>
        </dependency>


    </dependencies>

python dependencies (pip install via console):
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from autograd import grad 

### Class structure

## GUI.java
Handles user input and bot outputs

## WeatherDataService.java
API request generates weather report, requested by city name

## GoogleNLP.java
API requests carry out tokenization, sentiment analysis and name recognision

##GoogleResults.java
API for web search that outputs results one at a time to user

## patterns.java
Hasn't been integrated, is supposed to handle user input and bot outputs among other functions. Was supposed to do spell-checking and auto-correct to some extent. 

## person.java 
Is a projection of a user's preferences and data. Holds user's name, favourite book and genre, age, and occupation. 

## quizQuestion.java
Pulls questions from question class and returns it to caller. As well as gets answer from caller

## question.java
Contains a question's contents and their affiliated genre. 

## library.java
Contains all methods involving books, such as get random title, get random book by specific genre, get random book by specific author, get specific title, get all books in a specific genre and etc. 

## chatBot.java
Contains statements and questions that are commonly used. Handles talking between user and bot for all methods apart from some methods in library.  

## PCA.java
currently we have a hard coded "standard user matrix whose collumns represent likely users and combinations of highly rated generas. when a user enters their favorite genera a "one-hot encoded" vector is compared with the standard user matrix by finding the nearest neighbor in euclidian space. this nearest neighbor is used to generate possible generas the user will like. in the future we hope to fully implement logic similair to the jupyter notebook to reccommend books based on higher dimentional data using pca encoding and decoding of the one-hot encoded vector mentioned above. See TODO in PCA class (header)

## parse.java
parse is a class of methods that finds a certain required key element of the input string, it is currently not being utilized but will be useful in later editions of this software which we aim to make more intelligent by recognizing patterns and keys such as verbs and nouns in user input see TODO in getNoun()
## book.java
Contains the title, author, number of pages, genre of a book. 

### Installing
After following the steps in getting started section, there should be a symbol(notably small) in the top right of your coding space, suggesting a sync to install dependencies based on the pom.xml, click it. 

run java.Main()

### Executing program

After dependencies are installed, run GUI.java.
             
Bot will prompt user with generic questions to setup a user profile. After which user can choose from a number of features to checkout books. 
Through the main class, users can invoke features such as: 

(In quotations are the inputs the user needs to use to invoke the method)
* Recommend by random title - "random title" 
* Recommend by selecting a random book in a specific genre - "random book in genre"
* Search by title - "title" 
* Recommend random book by specific author - "author"
* Recommend random book that has same length as inputted number - "pages" 
* Recommend by PCA as aforementioned - "pca" 
* Take a quiz - "quiz"


## Help

List of limitations (Work in progress)
-Spell checking
-Cannot handle unexpected input types
-Error handling


## Authors

Contributors names and student numbers:

<br />Joel Johnson (37794112)
<br />Mahir Rahman (71811509)
<br />Matthew Halim (12588786)
<br />Nikita Korobkin (13290333)
<br />Richardo Brown (10142529)


## Version History

* 0.2
    * Various bug fixes and optimizations
    * See [commit change]() or See [release history]()
* 0.1
    * Initial Release

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)
* [dbader](https://github.com/dbader/readme-template)
* [zenorocha](https://gist.github.com/zenorocha/4526327)
* [fvcproductions](https://gist.github.com/fvcproductions/1bfc2d4aecb01a834b46)
* [DomPizzie](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)
* [Porter Stemmer algorithm](https://tartarus.org/martin/PorterStemmer/)
