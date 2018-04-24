import java.util.ArrayList;
import java.util.Scanner;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Card {
    int rank;
    Color color;
    boolean faceDown;

    public Card(int rank, Color color, boolean faceDown) {
        this.rank = rank;
        this.color = color;
        this.faceDown = faceDown;
    }
    public int getRank() {
        return rank;
    }
    public Color getColor() {
        return color;
    }
    public boolean getFaceDown(){
        return faceDown;
    }
    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
    }
    public boolean isSameColor(Card card) {
        return this.getColor().equals(card.getColor());
    }
    public boolean isSameRank(Card card) {
        return this.getRank() == card.getRank();
    }
    // public boolean checkIfCardIsRed(){
    //     if (suit == 1 || suit == 2){
    //         return true;
    //     }
    //     else{
    //         return false;
    //     }
    // }

    public void turnCard(){
        // if (faceDown) {faceDown = false;}
        // else{faceDown = true;}
        faceDown = !faceDown;
        
    }

    public ArrayList<String> readASCIIfromFile() {
        ArrayList<String> lines = new ArrayList<>();
        String fileName; 
        if (faceDown){fileName = "cardASCII/cardback.txt";}
        else{fileName = "cardASCII/" + rank + ".txt";}

        
        try{
            Scanner fileReaded = new Scanner(new File(fileName));
            
            while (fileReaded.hasNextLine()){
                lines.add(fileReaded.nextLine());
                //cardImage += fileReaded.nextLine() + "\n";
            }
    
            fileReaded.close();        
            
        }
        catch (FileNotFoundException e){System.out.println("file not found");}

        return lines;
    }
    
    public String toString() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        String cardImageInColor;
        String cardImage = "";
        ArrayList<String> cardLines = readASCIIfromFile();
        

        for (int i = 0; i < cardLines.size(); i++){
            cardImage += cardLines.get(i) + "\n";
        }

        if (faceDown){
            cardImageInColor = ANSI_BLUE + cardImage + ANSI_RESET;
        }
        else{
            if (this.getColor().equals(Color.RED)){
                cardImageInColor = ANSI_RED + cardImage + ANSI_RESET;
            }
            else{
                cardImageInColor = ANSI_BLACK + cardImage + ANSI_RESET;
            }
        }

        

        return cardImageInColor;
    }
    public enum Color {
        RED,
        BLACK;
    }
}