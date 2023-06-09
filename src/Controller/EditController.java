package src.Controller;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import src.Convertion;
import src.Model.Connexion;
import src.Model.Plateau;
import src.View.EditPanel;
import src.View.EditView;

public class EditController extends MouseInputAdapter {

    private static Plateau plateau;
    private static EditView editView;
    private static EditPanel editPanel;
    private static int level;

    public EditController(int n) {
        level = n;
        plateau = new Plateau(n, true);
        editPanel = new EditPanel(plateau.getTuiles());
        editView = editPanel.getView();
        editView.addMouseListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int[] coords = editView.getTuileCoords(e.getX(), e.getY());
        if (coords == null)
            return;

        if (editView.clickInCenter(e.getX(), e.getY(), coords)) {
            plateau.toggleComposant(coords);
            editView.repaint();
        } else {
            Connexion connexion = editView.getNearestConnexion(e.getX(), e.getY(), coords);
            plateau.toggleConnexion(coords, connexion);
            editView.repaint();
        }
    }

    public EditPanel getView() {
        return editPanel;
    }

    // enums
    public static void modifyPlateau(int side, int action) {
        plateau.modifyData(side, action);
        editView.updateFrame();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(editView);
        frame.pack();
    }

    public static void updateFile() {
        if (plateau.checkWin()) {
            Convertion.updateFile(level, plateau.getTuiles());
        }
    }

    public static boolean checkWin() {
        return plateau.checkWin();
    }

    public static void switchGeometry() {
        plateau.switchGeometry();
        editView.updateFrame();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(editView);
        frame.pack();
    }

}
