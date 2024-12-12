import java.util.ArrayList;
import java.util.List;

/**
 * Klassen Owner representerar en ägare med namn, telefonnummer och en lista av djur.
 *
 * Funktionalitet:
 * - Lagrar information om ägaren (namn och telefonnummer).
 * - Hanterar en lista över djur som tillhör ägaren.
 * - Lägger till nya djur i listan.
 * - Söker efter ett djur baserat på namn.
 * - Ger åtkomst till listan av djur.
 */

public class Owner {
    private String name;
    private String phone;
    private final List<Animal> animals = new ArrayList<>();

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Owner() {
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public Animal getAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }
    public List<Animal> getAnimals() {
        return animals;
    }

}
