import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RailwayStationFrame frame = new RailwayStationFrame();
            frame.setVisible(true);
        });
    }
}