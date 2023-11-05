import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BubbleButton extends JButton {
    public BubbleButton(String text) {
        super(text);
        setFont(new Font("Sans-serif", Font.BOLD, 16));
        setForeground(Color.BLACK);
        setBackground(null); // No button background
        setFocusPainted(false);
        setFocusable(false);
        setBorderPainted(false);
        setPreferredSize(new Dimension(800, 20));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(255, 215, 0)); // Change background color on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(null); // No background color when not hovering
            }
        });

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action when the button is clicked
                // For example, navigate to the home page
            }
        });
    }
}