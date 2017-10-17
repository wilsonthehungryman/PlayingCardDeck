/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carddeck;

import java.util.Objects;

/**
 *
 * @author Wilson
 */
public class Card {

    private int value;
    private final String name;

    public Card(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public Card(String name) {
        this(name, 1);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getPoints() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPoint(int points) {
        this.value = points;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.value;
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Card other = (Card) obj;
        return this.name.compareTo(other.getName()) == 0 && this.value == other
                .getValue();
    }

    public Card copy() {
        return new Card(name, value);
    }

}
