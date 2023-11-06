import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoginGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(449, 600);
        frame.setResizable(false);

        // Background Image
        ImageIcon bgImage = new ImageIcon("bgpht.png");
        JLabel background = new JLabel(bgImage);
        background.setBounds(0, 0, 449, 600);

        // Avatar Image
        ImageIcon avatarImage = new ImageIcon("avatar.png");
        JLabel avatar = new JLabel(avatarImage);
        avatar.setBounds(175, 10, 100, 100);
        background.add(avatar);

        // Set the font
        Font openSansFont = new Font("Open Sans", Font.BOLD, 15);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 120, 80, 20);
        usernameLabel.setFont(openSansFont);
        background.add(usernameLabel);

        // Username Input (Oval Round Shape)
        JTextField usernameField = createOvalTextField(100, 150, 200, 30, openSansFont);
        background.add(usernameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 190, 80, 20);
        passwordLabel.setFont(openSansFont);
        background.add(passwordLabel);

        // Password Input (Oval Round Shape)
        JPasswordField passwordField = createOvalPasswordField(100, 220, 200, 30, openSansFont);
        background.add(passwordField);

        // Login Button
        JButton loginButton = createClickableButton("Login", 100, 270, 100, 30, openSansFont, Color.BLACK, Color.GRAY, Color.DARK_GRAY);
        background.add(loginButton);

        // Register Button
        JButton registerButton = createClickableButton("Register", 210, 270, 100, 30, openSansFont, Color.BLACK, Color.GRAY, Color.DARK_GRAY);
        background.add(registerButton);

        // Login button click listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement login functionality here
                // Example: Redirect to another window or perform login action
            }
        });

        // Register button click listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement registration functionality here
                // Example: Redirect to registration window or perform registration action
            }
        });

        // Add background label to the frame
        frame.add(background);

        frame.setLayout(null); // No layout manager
        frame.setVisible(true);
    }

    // Oval Text Field creation method (same as in RegGUI)
    private static JTextField createOvalTextField(int x, int y, int width, int height, Font font) {
        JTextField textField = new JTextField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(new Color(0, 0, 0, 0)); // Transparent background
                    g2d.fill(new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
                    g2d.setColor(getForeground());
                    super.paintComponent(g2d);
                    g2d.dispose();
                } else {
                    super.paintComponent(g);
                }
            }
        };
        textField.setBounds(x, y, width, height);
        textField.setOpaque(false);
        textField.setFont(font);
        return textField;
    }

    // Oval Password Field creation method (same as in RegGUI)
    private static JPasswordField createOvalPasswordField(int x, int y, int width, int height, Font font) {
        JPasswordField passwordField = new JPasswordField(20) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(new Color(0, 0, 0, 0)); // Transparent background
                    g2d.fill(new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
                    g2d.setColor(getForeground());
                    super.paintComponent(g2d);
                    g2d.dispose();
                } else {
                    super.paintComponent(g);
                }
            }
        };
        passwordField.setBounds(x, y, width, height);
        passwordField.setOpaque(false);
        passwordField.setFont(font);
        return passwordField;
    }

    // Clickable Button creation method (same as in RegGUI)
    private static JButton createClickableButton(String text, int x, int y, int width, int height, Font font, Color textColor, Color hoverColor, Color clickColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(clickColor); // Clicked color
                } else if (getModel().isRollover()) {
                    g.setColor(hoverColor); // Hover color
                } else {
                    g.setColor(new Color(0, 0, 0, 0)); // Transparent background
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15); // Rounded rectangle
                super.paintComponent(g);
            }
        };
        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(textColor);
        return button;
    }
}
