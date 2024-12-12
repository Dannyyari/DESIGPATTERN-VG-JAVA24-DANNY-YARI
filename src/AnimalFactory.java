/**
 * AnimalFactory är en fabriksklass som ansvarar för att skapa instanser av olika djur.
 * Den använder sig av en central metod för att returnera rätt djurobjekt baserat på en angiven typ.
 * Detta följer fabriksmönstret (Factory Pattern) och gör koden mer flexibel och lättanpassad
 * när nya djurtyper behöver läggas till i systemet.
 */

public class AnimalFactory {

    public static Animal createAnimal(String type, String name, String food, String medication){
        switch (type.toLowerCase()) {
            case "katt":
                return new Cat(name, food, medication);
            case "hund":
                return new Dog(name, food, medication);
            case "fågel": return new Bird(name, food, medication);
            // Lägg till fler djurtyper om du har fler klasser
            default:
                throw new IllegalArgumentException("Okänd djurtyp: " + type);
        }
    }
}
