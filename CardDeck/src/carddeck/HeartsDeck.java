/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

/**
 *
 * @author Wilson
 * @param <C> specific type of PlayingCard
 */
public class HeartsDeck<C extends PlayingCard> extends CardDeck<C> {

    private int players;
    private PlayingCardComparator comparator;

    public HeartsDeck(int players, PlayingCardComparator comparator) {
        super();
        this.players = players;
        this.comparator = comparator;
        orderingBy = comparator.getCardComparator();
    }

    public int getPlayers() {
        return players;
    }

    public PlayingCardComparator getComparator() {
        return comparator;
    }

    public void setComparator(PlayingCardComparator comparator) {
        this.comparator = comparator;
    }

    public HeartsDeck(int players) {
        this(players, new PlayingCardComparator(Suit.values(), Face.values()));
    }

    public HeartsDeck() {
        this(4);
    }

    public void createDeck() {
        for (Suit suit : Suit.values())
            for (Face face : Face.values())
                if (face != Face.JOKER)
                    add((C) new PlayingCard(face, suit));
        //TODO: logic to remove cards based on number of players (or adjust loop)
    }

    public HeartsHand<C>[] deal() {
        HeartsHand<C>[] hands = new HeartsHand[players];
        for (int i = 0; i < hands.length; i++)
            hands[i] = new HeartsHand<C>(size / players);

        while (size > 0)
            for (HeartsHand h : hands){
//                C card = remove();
//                System.out.println(h.getSize() + " " + size + " " + card.toString());
                h.insert(remove());
            }

        return hands;
    }

    public HeartsHand<C>[] shuffleAndDeal() {
        shuffle();
        return deal();
    }
}
