
/**
 * Matthew Rozendaal
 * Mod 8.2
 * 4/28/2026
 * This program creates three threads that print random letters, digits, and symbols to the console.
 * Each thread runs concurrently, and the output will be a mix of letters, digits, and symbols.
 */
public class MatthewThreeThreads {

    public static void main(String[] args) {
        // Create one thread for each character type.
        Thread lettersThread = new Thread(new LettersThread());
        Thread digitsThread = new Thread(new DigitsThread());
        Thread symbolsThread = new Thread(new SymbolsThread());

        // Start all threads so they run concurrently.
        lettersThread.start();
        digitsThread.start();
        symbolsThread.start();
    }

    static class LettersThread implements Runnable {

        @Override
        public void run() {
            // Print 10,000 random lowercase letters (a-z).
            for (int i = 0; i < 10000; i++) {
                // Convert a random number from 0-25 into a lowercase letter.
                char letter = (char) ('a' + (int) (Math.random() * 26));
                System.out.print(letter);
            }
        }
    }

    static class DigitsThread implements Runnable {

        @Override
        public void run() {
            // Print 10,000 random digits (0-9).
            for (int i = 0; i < 10000; i++) {
                // Convert a random number from 0-9 into a digit character.
                char digit = (char) ('0' + (int) (Math.random() * 10));
                System.out.print(digit);
            }
        }
    }

    static class SymbolsThread implements Runnable {

        // Allowed symbol characters for this thread.
        private final char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};

        @Override
        public void run() {
            // Print 10,000 random symbols from the array above.
            for (int i = 0; i < 10000; i++) {
                // Pick a random index and print the symbol at that position.
                char symbol = symbols[(int) (Math.random() * symbols.length)];
                System.out.print(symbol);
            }
        }
    }
}
