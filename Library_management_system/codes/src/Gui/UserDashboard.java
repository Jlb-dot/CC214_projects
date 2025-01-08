package Gui;
import javax.swing.*;
import java.awt.*;
import DSA.Admin.USER_DB;
import DSA.Objects.users;


public class UserDashboard extends JFrame {
    private JTextField searchField;
    private JComboBox<String> sortByComboBox;
    private JPanel mainPanel;
    private int currentUserId;

    public void setCurrentUser(int userId) {
        this.currentUserId = userId;
    }

    public UserDashboard() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create left panel for buttons
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        leftPanel.setPreferredSize(new Dimension(150, 0));

        // Profile section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        JLabel profilePicture = new JLabel(new ImageIcon()); // Placeholder for profile picture
        profilePicture.setPreferredSize(new Dimension(100, 100));
        profilePicture.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JTextField nameField = new JTextField("Name");
        nameField.setMaximumSize(new Dimension(150, 25));

        profilePanel.add(profilePicture);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        profilePanel.add(nameField);
        profilePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create buttons
        JButton borrowButton = new JButton("Borrow");
        JButton returnButton = new JButton("Return");
        JButton profileButton = new JButton("Profile");
        JButton historyButton = new JButton("History");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to left panel
        leftPanel.add(profilePanel);
        leftPanel.add(borrowButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(returnButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(profileButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(historyButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(logoutButton);

        // Create top panel for search and sort
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        String[] sortOptions = {"Genre", "Alphabetical Order", "Date", "ISBN"};
        sortByComboBox = new JComboBox<>(sortOptions);
        sortByComboBox.setPreferredSize(new Dimension(150, 25));

        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(new JLabel("Sort by:"));
        topPanel.add(sortByComboBox);

        // Create center panel for book list
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Available Books"));
        JList<String> bookList = new JList<>(); // Placeholder for book list
        JScrollPane scrollPane = new JScrollPane(bookList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Add action listeners
        searchButton.addActionListener(e -> searchBooks());
        borrowButton.addActionListener(e -> borrowBook());
        returnButton.addActionListener(e -> returnBook());
        profileButton.addActionListener(e -> showProfile());
        historyButton.addActionListener(e -> showHistory());
        logoutButton.addActionListener(e -> logout());
        sortByComboBox.addActionListener(e -> sortBooks());
    }

    private void searchBooks() {
        // Implement search functionality
        String searchTerm = searchField.getText();
        // Add search logic here
    }

    private void borrowBook() {
        // Implement borrow functionality
        JOptionPane.showMessageDialog(this, "Borrow functionality to be implemented");
    }

    private void returnBook() {
        // Implement return functionality
        JOptionPane.showMessageDialog(this, "Return functionality to be implemented");
    }

    private void showProfile() {
        JDialog profileDialog = new JDialog(this, "User Profile", true);
        profileDialog.setSize(400, 300);
        profileDialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel detailsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create fields for user details
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField userIDField = new JTextField();
        JTextField emailField = new JTextField();

        // Get user data using your existing USER_DB class
        USER_DB userDb = new USER_DB();
        users currentUser = userDb.getUser(currentUserId);

        if (currentUser != null) {
            firstNameField.setText(currentUser.getFirstName());
            lastNameField.setText(currentUser.getLastName());
            userIDField.setText(String.valueOf(currentUser.getId()));
            emailField.setText(currentUser.getEmail());
        }

        // Make fields non-editable
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        userIDField.setEditable(false);
        emailField.setEditable(false);

        // Add fields with labels
        addLabelAndField(detailsPanel, "First Name:", firstNameField, gbc);
        addLabelAndField(detailsPanel, "Last Name:", lastNameField, gbc);
        addLabelAndField(detailsPanel, "User ID:", userIDField, gbc);
        addLabelAndField(detailsPanel, "Email:", emailField, gbc);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton editButton = new JButton("Edit Profile");
        JButton closeButton = new JButton("Close");

        buttonPanel.add(editButton);
        buttonPanel.add(closeButton);

        // Edit button action
        editButton.addActionListener(e -> {
            JTextField editFirstNameField = new JTextField(firstNameField.getText());
            JTextField editLastNameField = new JTextField(lastNameField.getText());
            JTextField editEmailField = new JTextField(emailField.getText());

            JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
            inputPanel.add(new JLabel("First Name:"));
            inputPanel.add(editFirstNameField);
            inputPanel.add(new JLabel("Last Name:"));
            inputPanel.add(editLastNameField);
            inputPanel.add(new JLabel("Email:"));
            inputPanel.add(editEmailField);

            int result = JOptionPane.showConfirmDialog(profileDialog,
                    inputPanel,
                    "Edit Profile",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                // Update the user object
                currentUser.setFirstName(editFirstNameField.getText());
                currentUser.setLastName(editLastNameField.getText());
                currentUser.setEmail(editEmailField.getText());

                // Update in database
                if (USER_DB.add(currentUser)) { // Using your existing add method to update
                    firstNameField.setText(editFirstNameField.getText());
                    lastNameField.setText(editLastNameField.getText());
                    emailField.setText(editEmailField.getText());
                    JOptionPane.showMessageDialog(profileDialog,
                            "Profile updated successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(profileDialog,
                            "Error updating profile",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        closeButton.addActionListener(e -> profileDialog.dispose());

        mainPanel.add(detailsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        profileDialog.add(mainPanel);
        profileDialog.setVisible(true);
    }

    // Helper method remains the same
    private void addLabelAndField(JPanel panel, String labelText, JTextField field, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);

        field.setPreferredSize(new Dimension(200, 25));
        gbc.insets = new Insets(0, 5, 15, 5);
        panel.add(field, gbc);
    }

    private void showHistory() {
        // Implement history view
        JOptionPane.showMessageDialog(this, "History view to be implemented");
    }

    private void logout() {
        // Implement logout functionality
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void sortBooks() {
        // Implement sort functionality
        String selectedSort = (String) sortByComboBox.getSelectedItem();
        // Add sorting logic here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDashboard userDashboard = new UserDashboard();
            userDashboard.setVisible(true);
        });
    }
}