/**
 * Klassen Dog representerar ett husdjur av typen katt.
 * Den är en subklass till Animal och implementerar den abstrakta
 * metoden makeSound() för att ge ett specifikt ljud (Voff-voff)
 * för en hund. Konstruktorn kräver namn, mat och medicin som
 * parametrar för att skapa ett kattobjekt.
 */

public class Dog extends Animal {
    public Dog(String name, String food, String medication) {
        super(name, food, medication);
    }

    @Override
    public void makeSound() {
        System.out.println("\033[1m\033[3m –– Voff-voff! –– \033[0m");
    }
}
