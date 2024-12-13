import java.util.Scanner;
/**
 * Klassen ReceptionView hanterar användargränssnittet för Djurdagiset.
 * Den ansvarar för att visa menyer och meddelanden till användaren samt ta emot användarens input.
 *
 * Funktionalitet:
 * - Visar huvudmenyn med alternativ för att välja åtgärder för djur och ägare.
 * - Tar emot och returnerar användarens input från tangentbordet.
 * - Visar meddelanden till användaren.
 */
public class ReceptionView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        displayMessage("\nVälkommen till Djurdagiset!\n");
        displayMessage("1. Lämna Djur");
        displayMessage("2. Hämta Djur");
        displayMessage("3. Visa Ägare & Djur");
        displayMessage("4. Registrera Ny Ägare(Tidigare MVC design)");
        displayMessage("5. Byt Ägare");
        displayMessage("6. Information Om Djur");
        displayMessage("7. Registrera Ny ägare (Factory design)");
        displayMessage("8. Avsluta");
        System.out.print("\nVälj Ett Alternativ: ");
    }

    public String getInput() {
        return scanner.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

}
