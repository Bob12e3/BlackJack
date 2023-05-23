public class Card extends Deck {

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value){
        // Konstruktorn för att skapa ett kort med angiven svit (suit) och värde (value).


        this.value = value;// Tilldelar värdet till den privata instansvariabeln value.
        this.suit = suit;// Tilldelar sviten till den privata instansvariabeln suit.

    }
    //Här gör jag så att den returnar vilket typ av kort det är och dens valuta som en string alltså text.
    public String toString(){
            return this.suit.toString() + "-" + this.value.toString();
    }
    //Här kollar den värdet på kortet.Det här behöver vi för att kolla hur många poäng dealern och spelaren har.
    public Value getValue(){
        return this.value;
    }

}
