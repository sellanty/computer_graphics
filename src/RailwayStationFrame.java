import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RailwayStationFrame extends JFrame {
    public RailwayStationFrame() {
        setTitle("Железнодорожный Вокзал - Интерактивная Графика");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton animateButton = new JButton("Запустить анимацию");
        JButton regenerateButton = new JButton("Новая генерация");

        animateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.toggleAnimation();
            }
        });

        regenerateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.regenerateScene();
            }
        });

        controlPanel.add(animateButton);
        controlPanel.add(regenerateButton);

        add(controlPanel, BorderLayout.SOUTH);
    }
}