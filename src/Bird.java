/**
 * Klassen Bird representerar ett husdjur av typen fågel.
 * Den är en subklass till Animal och implementerar den abstrakta
 * metoden makeSound() för att ge ett specifikt ljud (Voff-voff)
 * för en fågel. Konstruktorn kräver namn, mat och medicin som
 * parametrar för att skapa ett kattobjekt.
 */

public class Bird extends Animal {
    public Bird(String name, String food, String medication) {
        super(name, food, medication);
    }

    @Override
    public void makeSound() {
        System.out.println("\033[1m\033[3m –– Kvitter-kvitter! –– \033[0m");
    }
}

