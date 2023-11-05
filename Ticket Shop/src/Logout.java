import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logout extends Mainframe{

    public Logout(){

        boolean matched = false;

        JFrame frame = new JFrame("Menu Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 100);
        frame.setResizable(false);

        JPanel panel = new JPanel();

        JLabel confirmation = new JLabel();
        confirmation.setText("Are you sure you want to Logout?");

        JButton yes = new JButton("YES");
        yes.setBounds(10, 200, 100, 30);
        yes.setFocusable(false);
        yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                confirm=true;
               
                frame.dispose();
                new LoginGUI();
                
            }
        });

        JButton no = new JButton("NO");
        no.setBounds(190, 200, 100, 30);
        no.setFocusable(false);
        no.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               boolean matched = true;
                frame.dispose();

                
            }
        });


        if (matched){

            new Mainframe();

        }


        panel.add(confirmation);
        panel.add(yes);
        panel.add(no);
    
        frame.add(panel);

        frame.setVisible(true);
    }

}
    