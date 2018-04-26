import java.util.ArrayList;
import java.util.*;

// abstract class for class Player
public abstract class Player {

    public List<Card> cardsInHand;
    final String ANSI_RESET = "\u001B[0m";

    public abstract int pickCard(Deck deck, Card patternCard);

    public abstract int chooseOption(Deck deck, Card patternCard);

    public abstract void addCardsFromPile(Deck pileOnTable);

    public void move(Player opponent, Deck deck, Card patternCard) {
        int option = chooseOption(deck, patternCard);
        if (option == 1) {
            placeCardOnTop(deck, patternCard);
        } else {
            check(deck, opponent, patternCard);
        }
    }

    public void placeCardOnTop(Deck deck, Card patternCard) {
        int index = pickCard(deck, patternCard);
        Card chosenCard = getCardsInHand().get(index);
        chosenCard.setFaceDown(true);
        deck.addCardToPile(chosenCard);
        cardsInHand.remove(index);
    }

    public void drawCards(Deck deck) {
        List<Card> list = deck.getListOfCards();
        this.cardsInHand = new ArrayList<>();
        cardsInHand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            cardsInHand.add(list.get(i));
            list.remove(i);
        }
    }

    public void check(Deck pileOnTable, Player opponent, Card patternCard) {
        if (pileOnTable.getSizeOfPile() == 0) {
            System.out.println("Can't check your opponent's move, there hasn't been");
            return;
        } else {
            if (pileOnTable.isCardValid(patternCard)) {
                if (this.cardsInHand.size() + pileOnTable.getSizeOfPile() < 18) {
                    this.addCardsFromPile(pileOnTable);
                    pileOnTable.clearPile();
                }
            } else {
                opponent.addCardsFromPile(pileOnTable);
                pileOnTable.clearPile();
            }
        }
    }


    public List<Card> getCardsInHand() {
        return this.cardsInHand;
    }
    public void sortCards(){
        Collections.sort(cardsInHand, new Comparator<Card>() {
            public int compare(Card card1, Card card2) {
                return card1.getRank().compareTo(card2.getRank());
            }
        });
    }


    public void displayCardsInhand() {
        int allLines = 7; 
        
        displayCardIndexes();
        System.out.println();

        for (int currentLineNumber = 0; currentLineNumber < allLines; currentLineNumber++) {

            for (int i = 0; i < cardsInHand.size(); i++) {
                if (cardsInHand.get(i).faceDown) {
                    displayLineInBlue(i, currentLineNumber);
                } 
                else {
                    if (cardsInHand.get(i).getColor().equals(Color.RED)) {
                        displayLineInRed(i, currentLineNumber);
                    } else {
                        displayLineInBlack(i, currentLineNumber);
                    }
                }
            }
            System.out.print("\n");
        }
    }

    private void displayCardIndexes(){
        
        for (int cardIndex = 0; cardIndex < cardsInHand.size(); cardIndex++) {
            
            if (cardIndex < 10) {System.out.print(cardIndex + 1 + "       ");} 
            else {System.out.print(cardIndex + 1 + "      ");}
        }
    }
        
        
    private void displayLineInBlue(int i, int currentLineNumber){
        final String ANSI_BLUE = "\u001B[34m";

        System.out.print(ANSI_BLUE + cardsInHand.get(i).readASCIIfromFile().get(currentLineNumber) + " "
        + ANSI_RESET);
    }


    private void displayLineInRed(int i, int currentLineNumber){
        final String ANSI_RED = "\u001B[31m";

        System.out.print(ANSI_RED + cardsInHand.get(i).readASCIIfromFile().get(currentLineNumber) + " "
        + ANSI_RESET);
    }


    private void displayLineInBlack(int i, int currentLineNumber){
        final String ANSI_BLACK = "\u001B[30m";

        System.out.print(ANSI_BLACK + cardsInHand.get(i).readASCIIfromFile().get(currentLineNumber)
        + " " + ANSI_RESET);
    }

}