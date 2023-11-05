import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(449, 600);

        // Background Image
        ImageIcon bgImage = new ImageIcon("bgpht.png");
        JLabel background = new JLabel(bgImage);
        background.setBounds(0, 0, 449, 600);

        // Avatar Image
        ImageIcon avatarImage = new ImageIcon("avatar.png");
        JLabel avatar = new JLabel(avatarImage);
        avatar.setBounds(175, 10, 100, 100);

        // Set the font
        Font openSansFont = new Font("Open Sans", Font.BOLD, 15);

        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 120, 80, 20);
        nameLabel.setFont(openSansFont);

        // Name Input (Oval Round Shape)
        JTextField nameField = createOvalTextField(100, 150, 200, 30, openSansFont);

        // Age Label
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(100, 190, 80, 20);
        ageLabel.setFont(openSansFont);

        // Age Input
        JTextField ageField = createOvalTextField(100, 220, 200, 30, openSansFont);

        // Address Label
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(100, 260, 80, 20);
        addressLabel.setFont(openSansFont);

        // Address Input
        JTextField addressField = createOvalTextField(100, 290, 200, 30, openSansFont);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 320, 80, 20);
        usernameLabel.setFont(openSansFont);

        // Username Input (Oval Round Shape)
        JTextField usernameField = createOvalTextField(100, 350, 200, 30, openSansFont);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 380, 80, 20);
        passwordLabel.setFont(openSansFont);

        // Password Input (Oval Round Shape)
        JPasswordField passwordField = createOvalPasswordField(100, 410, 200, 30, openSansFont);

        // Register Button
        JButton registerButton = createClickableButton("Register", 100, 460, 200, 30, openSansFont, Color.BLACK, Color.GRAY, Color.DARK_GRAY);

        // Adding components to the background label
        background.add(avatar);
        background.add(nameLabel);
        background.add(nameField);
        background.add(ageLabel);
        background.add(ageField);
        background.add(addressLabel);
        background.add(addressField);
        background.add(usernameLabel);
        background.add(usernameField);
        background.add(passwordLabel);
        background.add(passwordField);
        background.add(registerButton);

        // Register button click listener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String address = addressField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Perform validation, e.g., age check
                if (age.isEmpty() || Integer.parseInt(age) < 15) {
                    JOptionPane.showMessageDialog(frame, "Age must be 15 or above.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Save the registration information to a text file
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
                    writer.write("Name: " + name);
                    writer.newLine();
                    writer.write("Age: " + age);
                    writer.newLine();
                    writer.write("Address: " + address);
                    writer.newLine();
                    writer.write("Username: " + username);
                    writer.newLine();
                    writer.write("Password: " + password);
                    writer.newLine();
                    writer.write("- - - - - - - - - - - - - - - - - - - - - - - - - -");
                    writer.newLine();
                    writer.close();
                    JOptionPane.showMessageDialog(frame, "Registration successful!", "Registration", JOptionPane.INFORMATION_MESSAGE);
               
               
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error saving registration data.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
