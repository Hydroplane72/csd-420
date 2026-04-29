
/**
 * Matthew Rozendaal
 * Mod 8.2
 * 4/28/2026
 * This program creates three threads that print random letters, digits, and symbols to the console. 
 * Each thread runs concurrently, and the output will be a mix of letters, digits, and symbols.
 */

public class MatthewThreeThreads {
    public static void main(String[] args) {
        Thread lettersThread = new Thread(new LettersThread());
        Thread digitsThread = new Thread(new DigitsThread());
        Thread symbolsThread = new Thread(new SymbolsThread());

        lettersThread.start();
        digitsThread.start();
        symbolsThread.start();
    }

    static class LettersThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                char letter = (char) ('a' + (int) (Math.random() * 26));
                System.out.print(letter);
            }
        }
    }

    static class DigitsThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                char digit = (char) ('0' + (int) (Math.random() * 10));
                System.out.print(digit);
            }
        }
    }

    static class SymbolsThread implements Runnable {
        private final char[] symbols = {'!', '@', '#', '$', '%', '&', '*'};

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                char symbol = symbols[(int) (Math.random() * symbols.length)];
                System.out.print(symbol);
            }
        }
    }
}
