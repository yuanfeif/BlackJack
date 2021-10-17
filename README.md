# BlackJack

## Team Members


|     Name     |     E-mail     |   BU ID   |
| :------------: | :--------------: | :---------: |
| Feifan Yuan | ffyuan@bu.edu | U61632796 |
|  Yifan Guo  | guoyf1@bu.edu | U68321686 |
| Ziyang Sheng | shengzy@bu.edu | U95910439 |

## Instructions on compiling and running this project

### Compile

run `javac *.java `.

### Execute

run `java Main` .

## Description of classes

1. Main
   * This class runs the whole project
2. Game
   * This class controls process of whole game
   * including generating different games and choosing which game to play
3. Runner(abstract)
   * This class runs a game
   * including several steps a game has
4. BJRunner
   * This class runs a BlackJack Game
   * extends the class Runner
5. TERunner
   * This class runs a TriantaEna Game
   * extends the class Runner
6. Client(abstract)
   * This class judges a game
   * including judging who is the winner and calculating the balance of each player after each round of a game
7. BJClient
   * This class judges a BlackJack Game
   * extends the class Client
8. TEClient
   * This class judges a TriantaEna Game
   * extends the class Client
9. Player(abstrct)
   * This class represents a player
   * including all the attributes and methods of a player has in different games
10. BJPlayer
    * This class represents a BlackJack player
    * extends the class Player
11. TEPlayer
    * This class represents a TriantaEna player
    * extends the class Player
12. Dealer(abstract)
    * This class represents a dealer
    * including all all the attributes and methods of a dealer has in different games
13. BJDealer
    * This class represents a BlackJack dealer
    * extends the class Dealer
14. TEDealer
    * This class represents a TriantaEna dealer
    * extends the class Dealer
15. Card
    * This class represents cards of a game
    * manage attributes of a card
16. Deck
    * This class represents deck of a game
    * including several cards
17. Hand
    * This class represents a hand of a player
    * manage a hand when playing including handValue and so on

## HighLights of this project

### 1. Concise Main

* ```java
  public class Main {
      public static void main(String[] args) {
          // select the game
          Game game = new Game();
          game.select();
      }
  }
  ```

### 2. Game stops

* We will print the balance of each player after each round of a game.
* For BlackJack Game, we design that those who have 0 or negative balance cannot enter next round
  and for those who still have enough balance, we will ask them if they are willing to continue.

### 3. Suit visualization

* We realize the visualization of suits which will optimize the user experience.

### 4. Team work

* We use Git for team work and use **GitHub Flow** as our work flow.

### 5. Scalability and extendibility

* The design of this project uses some abstract classes to provide the possibility of scalability and extendibility.
  * e.g. We use abstract class Player to represent a abstract conception of the player and use BJPlayer and TEPlayer to implement specific players in defferentr games.

### 6. Input Validation checking

* We will check when the player is playing (We assumes the user input is in good faith).
* We may improve validation checking for bad input if necessary.

### 7. Code standard

* We use Alibaba Java Coding Guidelines in this project.
  * ClassName in UpperCamelCase; methodName, parameterName in lowerCamelCase.
* We add Javadoc annotations for most classes, methods and variables.
