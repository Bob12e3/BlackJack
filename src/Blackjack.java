import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        System.out.println("Välkommen till BlackJack!");
        //skapar nytt deck
        Deck playingDeck = new Deck();
        //skapar alla 52 kort vi tillkalar detta fråna deck.java
        playingDeck.createFullDeck();
        //läger till funktionen från deck.java gör så att vi kan blanda korten
        playingDeck.shuffle();
        //Skapa en Hand för spelaren
        Deck playerDeck = new Deck();
        //Skapa en Hand för dealern
        Deck dealerDeck = new Deck();
        //spelarens pengar
        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        //Här går spelet i en loop så den ska fortsätta till spelaren har slut på pengar.
        while(playerMoney > 0){
            //fortsätter spelet
            //här ska den ta emot hur mycket spelaren vill beta
            System.out.println("Du har" + playerMoney +"Kr hur mycket vill pengar vill du spela om?");
            double playerBet = 0;
            try {
                playerBet = userInput.nextDouble();
            } catch (Exception e) {
                System.out.println("Felaktig inmatning. Försök igen.");
                userInput.nextLine(); // Rensa inmatningsbufferten
                continue;
            }
            //Här gör jag så att spelaren inte ska kunna spela om mer pengar än vad han har vi vill inte ha något fusk i detta spel.
            if(playerBet > playerMoney){
                System.out.println("Kan du inte räkna eller du har inte så mycket pengar din fatti lapp");
                break;
            }
             //använder vi för att sätta om rundan har slutat eller inte(endround = true;)
            boolean endRound = false;

            //Här börjar spelet dela ut kort
            //I blackjack så ska spelaren få två kort samt dealern men först får spelaren sina kort.
            //spelaren ska få två kort det är därför koden körs två gånger.
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //samma sak för dealern
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            //här skriver vi ut värdet på din hand samt vilka kort du har sen samma sak på dealerns
            while(true){
                System.out.println("Din hand");
                //Skriver ut dina kort
                System.out.println(playerDeck.toString());
                //ditt värde totalt
                System.out.println("Värdet på din hand är: " + playerDeck.cardsValue());
                //Skriver ut dealerns kort ena ska vara gömd eftersom det funkar så i blackjack
                System.out.println("Dealerns hand: " + dealerDeck.getCard(0).toString() + " och [Gömd]") ;

                //Efter spelaren har fått sin hand så måste hen bestämma om de vill stanna eller om de vill ha ett till kort.
                System.out.println("Vill du (1)Hit eller (2)Stanna?");
                //Gör så att om man skriver in en felaktig response så ska den skriva ut felaktig inmatning och sen får man försöka igen.
                int response = 0;
                try {
                    response = userInput.nextInt();
                } catch (Exception e) {
                    System.out.println("Felaktig inmatning. Försök igen.");
                    userInput.nextLine(); // Rensa inmatningsbufferten
                    continue;
                }

                //Om de väljer att Hita
                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("Du fick: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    // Här skriver jag en kod för om de går över värdet 21 så förlorar de
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust. Ditt nuvarande värde är: " + playerDeck.cardsValue());
                        //Här tar de ifrån hur mycket pengar du valde att gå in med eftersom du förlorade.
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                //Om spelaren väljer att stanna så ska den breaka.
                if(response==2){
                    break;
                }

            }

            //Här ska dealerns andra kort visas
            System.out.println("Dealerns hand: " + dealerDeck.toString());
            //Här skriver vi en kod så att den kollar om dealern har större värde än spelaren
            if((dealerDeck.cardsValue() > playerDeck.cardsValue())&& endRound == false){
                System.out.println("Dealern vann!");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Här skriver vi en kod för om dealern ska ha ett till kort elr inte.
            while((dealerDeck.cardsValue() < 17) && endRound == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            //Här ska dealerns värde stå
            System.out.println("Dealerns värde är: " + dealerDeck.cardsValue());
            //Koden om dealerns värde är för hög
            if((dealerDeck.cardsValue() > 21)&& endRound == false){
                System.out.println("Dealern värde är för hög! Du vann!");
                playerMoney += playerBet;
                endRound = true;
            }

            //Om det blir lika
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
                System.out.println("Det blev lika");
                endRound = true;
            }
             //Om spelarens värde är större än dealerns så ska du vinna annars(else) så ska du förlora.
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("Du vann handen!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if(endRound == false){
                System.out.println("Dealern vann.");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Flyttar alla kort som används
            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("Slut på handen.");

        }

        System.out.println("Tyvärr du har slut på pengar din fatti lapp");
    }
}