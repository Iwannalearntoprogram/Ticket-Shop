import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class LoginGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(449, 600);

        // Background Image
        ImageIcon bgImage = new ImageIcon("bgpht.png");
        JLabel background = new JLabel(bgImage);
        background.setBounds(0, 0, 449, 600);

        // Set the font
        Font openSansFont = new Font("Open Sans", Font.BOLD, 15);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 120, 80, 20);
        usernameLabel.setFont(openSansFont);

        // Username Input (Oval Round Shape)
        JTextField usernameField = createOvalTextField(100, 150, 200, 30, openSansFont);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 190, 80, 20);
        passwordLabel.setFont(openSansFont);

        // Password Input (Oval Round Shape)
        JPasswordField passwordField = createOvalPasswordField(100, 220, 200, 30, openSansFont);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 270, 100, 30);
        loginButton.setFont(openSansFont);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setContentAreaFilled(false);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(210, 270, 100, 30);
        registerButton.setFont(openSansFont);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setContentAreaFilled(false);

        // Adding components to the background label
        background.add(usernameLabel);
        background.add(usernameField);
        background.add(passwordLabel);
        background.add(passwordField);
        background.add(loginButton);
        background.add(registerButton);

        // Add background label to the frame
        frame.add(background);

        frame.setLayout(null); // No layout manager
        frame.setVisible(true);
    }

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
}
