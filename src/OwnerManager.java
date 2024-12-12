import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Klassen OwnerManager hanterar en lista av ägare och tillhörande djur.
 *
 * Funktionalitet:
 * - Lägga till nya ägare i systemet.
 * - Hitta en ägare baserat på telefonnummer.
 * - Byta ägare för ett specifikt djur och uppdatera ägarens lista.
 * - Välja ett djur från en ägare för ägarbyte.
 * - Flytta ett djur från en ägares lista till en annan ägare.
 * - Hämta en kopia av alla ägare.
 * - Ställa in en ny lista av ägare.
 */
public class OwnerManager {
    private List<Owner> owners = new ArrayList<>();

    public void addOwner(Owner owner) {
        owners.add(owner);
    }

    public Owner findOwner(String phone) {
        for (Owner owner : owners) {
            // System.out.println("Jämför med: " + owner.getPhone()); // ENDAST FÖR DEBUG
            if (owner.getPhone().trim().equals(phone.trim())) {
                return owner;
            }
        }
        return null;
    }

    public void changePetOwner(String oldOwnerPhone, FileHandler fileHandler) {
        Scanner scanner = new Scanner(System.in);

        Owner oldOwner = findOwner(oldOwnerPhone);
        if (oldOwner == null) {
            System.out.println("Ingen ägare med detta telefonnummer hittades." + oldOwnerPhone);
            return;
        }

        Animal petGettingNewOwner = selectPetFromOwner(oldOwner);
        if (petGettingNewOwner == null) {
            System.out.println("Inget giltigt djur valdes.");
            return;
        }

        System.out.println("Ange telefonnumret för det nya ägaren: ");
        String newOwnerPhone = scanner.nextLine();

        Owner newOwner = findOwner(newOwnerPhone);
        if (newOwner == null) {
            System.out.println("Ingen ägare hittad med detta telefonnummer: " + newOwnerPhone);
            System.out.println("Byter ägare...");
            if (newOwner == null) {
                System.out.println("Fel vi byte av ägare.");
                return;
            }
        }

        transferOwnershipForAnimal(oldOwner, newOwner, petGettingNewOwner);

        // UPPDATERAR TEXTFIL!
        fileHandler.saveOwners(owners);

        System.out.println("Djuret " + petGettingNewOwner.getName() + " har bytt ägare från "
                + oldOwner.getName() + " till " + newOwner.getName() + ".");
    }

    public Animal selectPetFromOwner(Owner owner) {
        Scanner scanner = new Scanner(System.in);

        // OM ÄGARE HAR FLERA DJUR
        if (owner.getAnimals().isEmpty()) {
            System.out.println(owner.getName() + " har inga husdjur som kan bytas.");
            return null;
        }

        System.out.println("Vilket husdjur ska byta ägare? ");
        while (true) {
            for (Animal animal : owner.getAnimals()) {
                System.out.println("– " + animal.getName() + ".");
            }

            String selectedPet = scanner.nextLine().trim();
            for (Animal animal : owner.getAnimals()) {
                if (animal.getName().equalsIgnoreCase(selectedPet)) {
                    return animal;
                }
            }
            System.out.println("Djuret finns inte. Försök igen.");
        }
    }

    public void transferOwnershipForAnimal(Owner oldOwner, Owner newOwner, Animal petGettingNewOwner) {
        oldOwner.getAnimals().remove(petGettingNewOwner);
        newOwner.getAnimals().add(petGettingNewOwner);
    }

    // Returnerar en kopia av listan
    public List<Owner> getAllOwners() {
        return new ArrayList<>(owners);
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

}