package src.View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

//setPrefered... et pack le frame de View

public class Menu extends JPanel {
    public Menu() {
        this.setPreferredSize(new Dimension(400, 300));

        this.setLayout(new GridBagLayout());
        JButton banque1 = new MyJButton("Banque 1");
        JButton banque2 = new MyJButton("Banque 2");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        this.add(banque1, gbc);
        gbc.gridy++;
        this.add(banque2, gbc);

        banque1.addActionListener(e -> {
            View.switchPanel(1);
        });

        banque2.addActionListener(e -> {
            View.switchPanel(2);
        });
    }

}
