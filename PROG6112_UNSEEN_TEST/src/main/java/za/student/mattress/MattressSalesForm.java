package za.student.mattress;

import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

/** Simple Swing form for recording a mattress sale. */
public class MattressSalesForm extends JFrame {
    private final JTextField customerNameField = new JTextField();
    private final JComboBox<String> productCombo = new JComboBox<>(new String[] {
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final JTextField priceField = new JTextField();

    public MattressSalesForm() {
        setTitle("Product App");
        setSize(440, 260);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Customer Name:"));
        panel.add(customerNameField);
        panel.add(new JLabel("Select Product:"));
        panel.add(productCombo);
        panel.add(new JLabel("Enter Price:"));
        panel.add(priceField);

        JButton processButton = new JButton("PROCESS");
        panel.add(new JLabel(""));
        panel.add(processButton);
        add(panel);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        processButton.addActionListener(e -> processSale());
    }

    private void processSale() {
        String customerName = customerNameField.getText().trim();
        String productName = (String) productCombo.getSelectedItem();
        String priceText = priceField.getText().trim();

        if (customerName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the customer name and product price.",
                    "Missing information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price <= 0) {
                JOptionPane.showMessageDialog(this, "Price must be more than 0.");
                return;
            }

            ProductCalculations calculation = new ProductCalculations(price);
            String result = String.format(
                    "Customer Name: %s%nProduct: %s%nVAT: R %.2f%nCOMMISSION: R %.2f%nTOTAL: R %.2f",
                    customerName, productName, calculation.GetVat(),
                    calculation.GetCommission(), calculation.GetTotal());

            saveToFile(customerName, productName, calculation);
            JOptionPane.showMessageDialog(this, result, "Sale details",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the price.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "The sale could not be saved: " + ex.getMessage());
        }
    }

    private void saveToFile(String customer, String product, ProductCalculations calculation)
            throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt", true))) {
            writer.println("DATA LOG");
            writer.println("****************************************");
            writer.println("Customer Name: " + customer);
            writer.println("Product: " + product);
            writer.printf("VAT: R %.2f%n", calculation.GetVat());
            writer.printf("COMMISSION: R %.2f%n", calculation.GetCommission());
            writer.printf("TOTAL: R %.2f%n", calculation.GetTotal());
            writer.println("****************************************");
        }
    }

    private void clearForm() {
        customerNameField.setText("");
        productCombo.setSelectedIndex(0);
        priceField.setText("");
        customerNameField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MattressSalesForm().setVisible(true));
    }
}
