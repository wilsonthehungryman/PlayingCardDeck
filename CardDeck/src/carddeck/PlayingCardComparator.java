/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Comparator;

/**
 *
 * @author Wilson
 */
public class PlayingCardComparator {

    final int[] suitMap;
    final int[] faceMap;

    Comparator<Suit> suitComparator;
    Comparator<Face> faceComparator;
    Comparator<PlayingCard> cardComparator;

    public PlayingCardComparator(Suit[] suits, Face[] faces) {
        suitMap = new int[Suit.values().length];
        faceMap = new int[Face.values().length];
        createSuitsComparator(suits);
        createFaceComparators(faces);

        createCardComparator();
    }

    private void createSuitsComparator(Suit[] suits) {
        int value = suitMap.length + 1;
        for (Suit suit : suits) {
            suitMap[suit.getValue()] = value;
            value--;
        }
        suitComparator = (s1, s2) -> suitMap[s1.getValue()] - suitMap[s2
                .getValue()];
    }

    private void createFaceComparators(Face[] faces) {
        int value = faceMap.length + 1;
        for (Face face : faces) {
            faceMap[face.getValue()] = value;
            value--;
        }
        faceComparator = (f1, f2) -> faceMap[f1.getValue()] - faceMap[f2
                .getValue()];
    }

    private void createCardComparator() {
        cardComparator = (c1, c2) -> {
            int suitC = suitComparator.compare(c1.getSuit(), c2.getSuit());
            if (suitC != 0)
                return suitC;
            return faceComparator.compare(c1.getFace(), c2.getFace());
        };
    }

    public int compareSuit(Suit s1, Suit s2) {
        return suitComparator.compare(s1, s2);
    }

    public int compareFace(Face f1, Face f2) {
        return faceComparator.compare(f1, f2);
    }

    public int compareCard(PlayingCard c1, PlayingCard c2) {
        return cardComparator.compare(c1, c2);
    }

    public Comparator<Suit> getSuitComparator() {
        return suitComparator;
    }

    public Comparator<Face> getFaceComparator() {
        return faceComparator;
    }

    public Comparator<PlayingCard> getCardComparator() {
        return cardComparator;
    }
}
