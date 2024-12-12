/**
 * Huvudklassen Reception som startar applikationen för Djurdagiset.
 * Den skapar en instans av ReceptionController och anropar start-metoden
 * för att köra programmet och hantera användarens interaktion med applikationen.
 */

public class Reception {
    public static void main(String[] args) {
        ReceptionController reception = new ReceptionController();
        reception.start();
    }
}