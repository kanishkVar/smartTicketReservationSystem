package ui;

import model.Trip;
import model.Database;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class AddTripPopup extends JDialog {
    public AddTripPopup(Frame owner) {
        super(owner, "Add Trip", true);
        setSize(360, 280);
        setLocationRelativeTo(owner);

        JPanel p = new JPanel(new GridLayout(6,1,6,6));
        JTextField source = new JTextField();
        JTextField dest = new JTextField();
        JTextField date = new JTextField(); // expected YYYY-MM-DD
        JTextField time = new JTextField(); // expected HH:MM

        p.add(labeled("Source", source));
        p.add(labeled("Destination", dest));
        p.add(labeled("Date (YYYY-MM-DD)", date));
        p.add(labeled("Time (HH:MM)", time));

        JButton add = new JButton("Add Trip");
        add.addActionListener(e -> {
            String s = source.getText().trim();
            String d = dest.getText().trim();
            String dt = date.getText().trim();
            String tm = time.getText().trim();
            if (s.isEmpty() || d.isEmpty() || dt.isEmpty() || tm.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Trip t = new Trip(UUID.randomUUID().toString(), s, d, dt, tm);
            Database.addTrip(t);
            JOptionPane.showMessageDialog(this, "Trip added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        p.add(add);
        add(p);
    }

    private JPanel labeled(String label, JComponent comp) {
        JPanel panel = new JPanel(new BorderLayout(6,6));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(comp, BorderLayout.CENTER);
        return panel;
    }
}
