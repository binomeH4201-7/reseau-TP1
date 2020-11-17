import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class GraphicalInterface extends JFrame {
    protected final static int WIDTH = 1420;
    protected final static int HEIGHT = 850;

    /**
     * Déclaration des objets graphiques
     */
    private final JPanel textualContainer;
    private final JButton btnCancel;
    private final JLabel lblHelp;

    public GraphicalInterface() {
        super();

        //Containers initialisation
        this.textualContainer = null;

        //Button initialisation, addition to container
        this.btnCancel = null;

        //Label initialisation, addition to container
        this.lblHelp = null;
    }

    public class LoadRequestListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {

        }

    }
}
