package src.View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import src.Controller.EditController;
import src.Controller.TuilesList;

public class EditPanel extends JPanel {

    EditView editView;

    public EditPanel(TuilesList tuilesList) {

        JPanel panelEdit = new JPanel();
        panelEdit.setLayout(new BorderLayout());
        List<JPanel> panels = createPanels();

        InEditMenuPanel panelMenu = new InEditMenuPanel();

        panelEdit.add(panels.get(0), BorderLayout.NORTH);
        panelEdit.add(panels.get(1), BorderLayout.WEST);
        panelEdit.add(panels.get(2), BorderLayout.SOUTH);
        panelEdit.add(panels.get(3), BorderLayout.EAST);

        editView = new EditView(tuilesList);
        panelEdit.add(editView);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(panelMenu);
        this.add(panelEdit);
    }

    public List<JPanel> createPanels() {
        List<JPanel> panels = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            final int side = i;

            JPanel panel = new JPanel(new GridBagLayout());

            JButton add;
            JButton delete;

            BufferedImage buttonIcon;
            try {
                buttonIcon = ImageIO.read(new File("data/plus.png"));
                add = new JButton(new ImageIcon(buttonIcon));
                add.setBorder(BorderFactory.createEmptyBorder());
                add.setContentAreaFilled(false);
            } catch (IOException e1) {
                add = new MyJButton("+");
                e1.printStackTrace();
            }

            try {
                buttonIcon = ImageIO.read(new File("data/minus.png"));
                delete = new JButton(new ImageIcon(buttonIcon));
                delete.setBorder(BorderFactory.createEmptyBorder());
                delete.setContentAreaFilled(false);
            } catch (IOException e1) {
                delete = new MyJButton("-");
                e1.printStackTrace();
            }

            add.addActionListener(e -> {
                EditController.modifyPlateau(side, 1);
                editView.updateFrame();
            });

            delete.addActionListener(e -> {
                EditController.modifyPlateau(side, 0);
                editView.updateFrame();
            });

            if (i % 2 == 0) {
                panel.add(add, new GridBagConstraints());
                panel.add(delete, new GridBagConstraints());
            } else {
                JPanel verticalPanel = new JPanel();
                verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
                verticalPanel.add(add);
                verticalPanel.add(delete);
                panel.add(verticalPanel, new GridBagConstraints());
            }

            panels.add(panel);
        }
        return panels;
    }

    public EditView getView() {
        return editView;
    }

    public void setView(TuilesList tuilesList) {
        editView = new EditView(tuilesList);
    }

}
