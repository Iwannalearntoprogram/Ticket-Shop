import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {
        super("Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a custom JPanel for the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Draw the background image with reduced opacity
                Image image = new ImageIcon("cine.png").getImage();
                float opacity = 0.5f; // Adjust the opacity (0.0f for fully transparent, 1.0f for fully opaque)
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Create a separate JPanel for the Username and Password fields
        JPanel loginPanel = new JPanel();
        loginPanel.setOpaque(false); // Make it transparent
        loginPanel.setLayout(new GridBagLayout());

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton regButton = new JButton("Register");

        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new RegGUI();
            }
        });

        // Styling for the Login button
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(30, 144, 255)); // A different background color
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true));

        // Styling for the Register button
        regButton.setForeground(Color.WHITE);
        regButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        regButton.setBackground(new Color(50, 50, 50)); // A slightly lighter background color
        regButton.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50), 2, true));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                if (checkCredentials(username, password)) {
                    // Credentials are correct, perform the login action
                    JOptionPane.showMessageDialog(LoginGUI.this, "Login Successful!");
                } else {
                    // Credentials are incorrect, show an error message
                    JOptionPane.showMessageDialog(LoginGUI.this, "Invalid username or password.");
                }
            }
        });

        // Add components to the loginPanel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(new JLabel("Username:"), constraints);

        constraints.gridy = 1;
        loginPanel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        loginPanel.add(usernameField, constraints);

        constraints.gridy = 1;
        loginPanel.add(passwordField, constraints);

        constraints.gridy = 2;
        loginPanel.add(loginButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        loginPanel.add(regButton, constraints);

         // Add the backgroundPanel and loginPanel to the main contentPane
         setLayout(new BorderLayout());
         backgroundPanel.setLayout(new BorderLayout());
         add(backgroundPanel, BorderLayout.CENTER);
         add(loginPanel, BorderLayout.SOUTH);
 
         setVisible(true);

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginGUI::new);
    }

    private boolean checkCredentials(String username, char[] password) {
        Map<String, String> credentials = readCredentialsFromFile();
        if (credentials.containsKey(username)) {
            String storedPassword = credentials.get(username);
            return String.valueOf(password).equals(storedPassword);
        }
        return false;
    }

    private Map<String, String> readCredentialsFromFile() {
        Map<String, String> credentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            String currentUsername = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    currentUsername = line.substring("Username: ".length());
                } else if (line.startsWith("Password: ") && currentUsername != null) {
                    String password = line.substring("Password: ".length());
                    credentials.put(currentUsername, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }
}

class RegGUI extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField addressField;
    private JTextField newUsernameField;
    private JPasswordField newPasswordField;

    public RegGUI() {
        super("Register");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a custom JPanel with a background image and opacity
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Set the background color to a dark shade with some opacity
                g.setColor(new Color(0, 0, 0, 180)); // Adjust the opacity (0 for fully transparent, 255 for fully opaque)
                g.fillRect(0, 0, getWidth(), getHeight());

                // Draw the background image (you can set the path to your image)
                Image image = new ImageIcon("cine.png").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        nameField = new JTextField(20);
        ageField = new JTextField(20);
        addressField = new JTextField(20);
        newUsernameField = new JTextField(20);
        newPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");

        // Styling for the Register button
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(30, 144, 255)); // A different background color
        registerButton.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String address = addressField.getText();
                String newUsername = newUsernameField.getText();
                char[] newPassword = newPasswordField.getPassword();

                if (age.isEmpty()) {
                    JOptionPane.showMessageDialog(RegGUI.this, "Age field is required.");
                    return;
                }

                int ageValue;
                try {
                    ageValue = Integer.parseInt(age);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(RegGUI.this, "Age must be a number.");
                    return;
                }

                if (ageValue < 15) {
                    JOptionPane.showMessageDialog(RegGUI.this, "Age must be 15 or older.");
                    return;
                }

                // Save the registered account to a text file
                saveToTextFile(name, age, address, newUsername, String.valueOf(newPassword));

                // Show a message dialog for successful registration
                JOptionPane.showMessageDialog(RegGUI.this, "Registration Successful!");

                // Close the RegGUI window and go back to the LoginGUI
                dispose();
                new LoginGUI();
            }
        });

        // Add components to the custom JPanel
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Name:"), constraints);

        constraints.gridy = 1;
        panel.add(new JLabel("Age:"), constraints);

        constraints.gridy = 2;
        panel.add(new JLabel("Address:"), constraints);

        constraints.gridy = 3;
        panel.add(new JLabel("Username:"), constraints);

        constraints.gridy = 4;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(nameField, constraints);

        constraints.gridy = 1;
        panel.add(ageField, constraints);

        constraints.gridy = 2;
        panel.add(addressField, constraints);

        constraints.gridy = 3;
        panel.add(newUsernameField, constraints);

        constraints.gridy = 4;
        panel.add(newPasswordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(registerButton, constraints);

        add(panel);
        setVisible(true);
    }

    private void saveToTextFile(String name, String age, String address, String username, String password) {
        try (FileWriter writer = new FileWriter("accounts.txt", true)) {
            writer.write("Name: " + name + "\n");
            writer.write("Age: " + age + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Username: " + username + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("---------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}