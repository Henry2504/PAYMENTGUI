import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentGUI {
    private JFrame frame;
    private JTextField amountField;
    private JRadioButton cardButton, cashButton;
    private JButton processButton;
    private JTextArea displayArea;

    public PaymentGUI() {
        buildWindow();
    }

    private void buildWindow() {
        frame = new JFrame("Payment Processing System");
        frame.setSize(480, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        frame.add(buildInputPanel(), BorderLayout.NORTH);
        frame.add(buildDisplayPanel(), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Payment Details"));

        panel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        panel.add(amountField);

        panel.add(new JLabel("Payment Method:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        cardButton = new JRadioButton("Card", true);
        cashButton = new JRadioButton("Cash");
        ButtonGroup group = new ButtonGroup();
        group.add(cardButton);
        group.add(cashButton);
        radioPanel.add(cardButton);
        radioPanel.add(cashButton);
        panel.add(radioPanel);

        processButton = new JButton("Process Payment");
        processButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });
        panel.add(new JLabel(""));
        panel.add(processButton);

        return panel;
    }

    private JPanel buildDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Payment Details & Receipt"));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void processPayment() {
        String amountText = amountField.getText().trim();

        if (amountText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a payment amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(frame, "Amount must be greater than zero.");
                return;
            }

            // polymorphism: method is declared as PaymentMethod, but holds a
            // CardPayment or CashPayment object, so the correct processPayment() runs
            PaymentMethod method;
            if (cardButton.isSelected()) {
                method = new CardPayment();
            } else {
                method = new CashPayment();
            }

            String processingMessage = method.processPayment(amount);
            String receipt = method.printReceipt();
            displayArea.setText(processingMessage + "\n\n" + receipt);

        } catch (NumberFormatException ex) {
            // catches specifically the case where amountText is not a valid number
            JOptionPane.showMessageDialog(frame, "Please enter a valid number for the amount.");
        }
    }

    public static void main(String[] args) {
        new PaymentGUI();
    }
}
