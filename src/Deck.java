import java.util.ArrayList;
import java.util.Random;

public class Deck extends Blackjack {
    //Skapar en arraylist för att ha alla mina kort.
    private ArrayList<Card> cards;

    //Här skapar jag en tom hand.
    public Deck(){
        this.cards = new ArrayList<Card>();
    }

    //Här skapar jag ett helt kortlek genom att använda en for lop man kan också göra det för varje kort men en for lop är ju snabbast och enklast.
    public void createFullDeck(){
        //Values har jag så att den går igenom alla mina values i min enum i for lopen.
        for(Suit cardSuit : Suit.values()){
            //Samma sak här den går igenom all mina kort värden och nu så har vi totalt 52 korti de här for lopsen.
            for(Value cardValue : Value.values()){
                //Här skapar den en ny kort som innehåller en av från vilken typ av kort och vilket värde.
                //Här använder vi också av vår kod från card klassen.
                this.cards.add(new Card(cardSuit, cardValue));
            }

        }
    }
    //Här ska den välja random kort ur kortleken.
    public void shuffle(){
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        //Här använder jag en randomizer så den väljer slumpigt vilket kort.
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
            tmpDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }

        this.cards = tmpDeck;
    }

    public String toString(){
        String cardListOutput ="";
        // Här gör vi en for-loop för att iterera över varje kort i handen.
        for(Card aCard : this.cards){
            cardListOutput +=  "\n" + aCard.toString();// Lägger till en strängrepresentation av varje kort i cardListOutput
        }
        return cardListOutput;// Returnerar den resulterande strängen med kortvärden
    }
    //används för att ta bort ett kort från handen.
    public void removeCard(int i){
        this.cards.remove(i);
    }
    //Hämatar ett kort från handen.
    public Card getCard(int i){
        return this.cards.get(i);
    }
    //används för att lägga till kort i handen.
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }

    //Dras ett kort från kortleken
    public void draw(Deck comingFrom){
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }
    //används för att returnera antalet kort i en kortlek (hand)
    public int deckSize(){
        return this.cards.size();
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();

        // Flytta alla kort från den nuvarande leken till den angivna leken (moveTo)
        for(int i = 0; i< thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));// Lägg till kortet i moveTo-leken genom att använda getCard() för att hämta kortet från den nuvarande leken och addCard() för att lägga till det i moveTo-leken.
        }
        // Ta bort alla kort från den nuvarande leken
        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);// Anropa removeCard() för att ta bort kortet från den nuvarande leken. Vi tar bort kortet vid index 0 eftersom när vi tar bort ett kort, skiftas resten av korten ett steg uppåt i listan.
        }
    }
    //Här ska den returna värdet på korten tillsammans så vi vet hur mycket värde vi har.
    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;
        //alla värden på korten
        for(Card aCard : this.cards){
            switch(aCard.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }
        //Här gör vi en kod för aces eftersom de har två olika värden antingen 1 eller 11 så vi måste ta reda på vad vi ska göra med de
        for(int i = 0; i < aces; i++){

            if(totalValue > 10){
                totalValue += 1;
            }
            else{
                totalValue += 11;
            }

        }
        return totalValue;


    }


}
