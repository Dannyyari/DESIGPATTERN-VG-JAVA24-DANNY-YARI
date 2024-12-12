/**
 * Animal är en abstrakt basklass som representerar generella egenskaper och beteenden
 * för djur i systemet. Klassen implementerar IAnimal och inkluderar:
 *
 * - Fält för namn, matvanor och medicinering, samt ett check-in-status.
 * - Getter-metoder för att hämta information om djuret.
 * - En abstrakt metod `makeSound()` som implementeras i specifika djurklasser.
 * - En metod `getInfo()` som returnerar en sammanfattning av djurets egenskaper.
 *
 * Klassen är avsedd att utökas av specifika djurtyper (t.ex. Hund, Katt, Fågel),
 * vilka implementerar unika beteenden.
 */

public abstract class Animal implements IAnimal {
    private final String name;
    private final String food;
    private final String medication;
    private boolean checkedIn = false;

    public Animal(String name, String food, String medication) {
        this.name = name;
        this.food = food;
        this.medication = medication;
    }

    public String getName() {
        return name;
    }

    public String getFood() {
        return food;
    }

    public String getMedication() {
        return medication;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public abstract void makeSound();

    public String getInfo() {
        return "Namn: " + getName() + " (" + getClass().getSimpleName() + "), Mat: " + getFood() + ", Medicin: " + getMedication();
    }
}
