import java.awt.*;
import java.util.List;
import java.util.Scanner;
/**
 * AnimalManager hanterar operationer för att administrera djur och deras ägare, inklusive:
 * - Checka in och ut djur samt uppdatera deras status.
 * - Lägg till nya djur och registrera deras data (t.ex. matvanor, medicinering).
 * - Lista djur och deras status med koppling till respektive ägare.
 * - Spara och läsa ägar- och djurdata via FileHandler.
 *
 * Klassen är avsedd att användas tillsammans med OwnerManager och FileHandler
 * och möjliggör användarinteraktion genom terminalinmatning.
 */
public class AnimalManager {
    private final Scanner scanner = new Scanner(System.in);
    private final FileHandler fileHandler = new FileHandler();
    private final OwnerManager ownerManager;

    public AnimalManager(OwnerManager ownerManager) {
        this.ownerManager = ownerManager;
    }

    public void checkInAnimal(Owner owner) {
        System.out.print("Ange djurets namn: ");
        String name = scanner.nextLine();
        Animal animal = owner.getAnimalByName(name);

        if (animal != null) {
            if (animal.isCheckedIn()) {
                System.out.println("Djuret " + name + " är redan incheckad.");
            } else {
                animal.setCheckedIn(true);
                System.out.println(name + " har checkats in.");
                Toolkit.getDefaultToolkit().beep();
                fileHandler.saveOwners(ownerManager.getAllOwners());
            }
        } else {
            System.out.println("Djuret hittades inte. Vill du registrera det? (Ja/Nej)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("JA")) {
                addAnimal(owner);
            }
        }
    }

    public void checkOutAnimal(Owner owner) {
        System.out.print("Ange djurets namn: ");
        String name = scanner.nextLine();
        Animal animal = owner.getAnimalByName(name);
        if (animal != null) {
            if (!animal.isCheckedIn()) {
                System.out.println("Djuret " + name + " är inte incheckat.");
            } else {
                animal.setCheckedIn(false);
                System.out.println(name + " har hämtats.");
                animal.makeSound();
                fileHandler.saveOwners(ownerManager.getAllOwners());
            }
        } else {
            System.out.println("Djuret hittades inte.");
        }
    }

    public void listAnimals(List<Owner> owners) {
        for (Owner owner : owners) {
            for (Animal animal : owner.getAnimals()) {
                String animalType = animal.getClass().getSimpleName(); // VILKEN TYP AV DJUR
                String status = animal.isCheckedIn() ? "Incheckad" : "Ej incheckad";
                System.out.println(
                        "Ägare: " + owner.getName() +
                                ", Tele: " + owner.getPhone() +
                                ", " + animalType +
                                ", Namn: " + animal.getName() +
                                ", Mat: " + animal.getFood() +
                                ", Medicin: " + animal.getMedication() +
                                ", Status: " + status
                );
            }
        }
    }
    // Tidigare lösning - Direkt instansiering av djurobjekt
    public void addAnimal(Owner owner) {
        System.out.print("Ange djurets namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange matvanor: ");
        String food = scanner.nextLine();
        System.out.print("Ange medicin: ");
        String medication = scanner.nextLine();
        System.out.print("Ange djurtyp (Hund, Katt, Fågel): ");
        String type = scanner.nextLine().toLowerCase();

        Animal animal;
        switch (type) {
            case "hund":
                animal = new Dog(name, food, medication);
                break;
            case "katt":
                animal = new Cat(name, food, medication);
                break;
            case "fågel":
                animal = new Bird(name, food, medication);
                break;
            default:
                System.out.println("Ogiltig djurtyp. Djuret registrerades inte.");
                return;
        }

        owner.addAnimal(animal);
        System.out.println("Djuret " + name + " har lagts till hos ägaren " + owner.getName() + ".");
    }

    public void listAnimalInfo(Owner owner) {
        if (owner.getAnimals().isEmpty()) {
            System.out.println(owner.getName() + " har inga registrerade djur.");
            return;
        }

        //System.out.println("Djur hos ägaren " + owner.getName() + ":");
        for (Animal animal : owner.getAnimals()) {
            System.out.println(animal.getInfo()); // Använd getInfo() för att hämta djurets information
        }
    }
    //---------- VG DEL---------------
    // Ny lösning - Användning av Factory Method
    public void addAnimalFromFactory(Owner owner) {
        System.out.print("Ange djurets namn: ");
        String name = scanner.nextLine();
        System.out.print("Ange matvanor: ");
        String food = scanner.nextLine();
        System.out.print("Ange medicin: ");
        String medication = scanner.nextLine();
        System.out.print("Ange djurtyp (Hund, Katt, Fågel): ");
        String type = scanner.nextLine().toLowerCase();

        Animal animal = AnimalFactory.createAnimal(type,name,food,medication);

        owner.addAnimal(animal);
        System.out.println("Djuret " + name + " har lagts till hos ägaren " + owner.getName() + ".");
    }
    //----------- VG DEL----------------
    public void countCheckedInAnimals(List<Owner> owners) {
        int checkedInCount = 0;

        // Iterera över alla ägare och deras djur
        for (Owner owner : owners) {
            for (Animal animal : owner.getAnimals()) {
                // Kontrollera om djuret är incheckat
                if (animal.isCheckedIn()) {
                    checkedInCount++;
                }
            }
        }

        // Skriv ut antalet incheckade djur
        System.out.println("Antal incheckade djur: " + checkedInCount);
    }

}