package winterknight;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import winterknight.strings.StringError;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Thread(new Game()).start();
        });
    }

    public static void exit() {
        System.exit(0);
    }

    public static void exitWithError(String error) {
        JOptionPane.showMessageDialog(null, error, StringError.ERROR.getValue(), JOptionPane.ERROR_MESSAGE);
        Main.exit();
    }

}
