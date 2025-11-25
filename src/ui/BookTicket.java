package ui;

import model.Database;
import model.Trip;
import model.Ticket;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class BookTicket extends JDialog {
    public BookTicket(Frame owner) {
        super(owner, "Book Ticket", true);
        setSize(420, 260);
        setLocationRelativeTo(owner);

        JPanel p = new JPanel(new GridLayout(5,1,6,6));
        List<Trip> trips = Database.getAllTrips();
        if (trips.isEmpty()) {
            p.add(new JLabel("No trips available. Add a trip first."));
            add(p);
            return;
        }
        String[] tripLabels = trips.stream().map(t -> t.getId() + "|" + t.toString()).toArray(String[]::new);
        JComboBox<String> tripCombo = new JComboBox<>(tripLabels);
        JTextField name = new JTextField();
        JTextField seats = new JTextField("1");

        p.add(labeled("Select Trip", tripCombo));
        p.add(labeled("Passenger Name", name));
        p.add(labeled("Seats", seats));

        JButton book = new JButton("Book");
        book.addActionListener(e -> {
            int idx = tripCombo.getSelectedIndex();
            if (idx < 0) return;
            Trip trip = trips.get(idx);
            String nm = name.getText().trim();
            int s;
            try { s = Integer.parseInt(seats.getText().trim()); } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Seats must be number", "Error", JOptionPane.ERROR_MESSAGE); return; }
            if (nm.isEmpty() || s <= 0) { JOptionPane.showMessageDialog(this, "Enter valid name and seats", "Validation", JOptionPane.WARNING_MESSAGE); return; }
            Ticket t = new Ticket(UUID.randomUUID().toString(), trip.getId(), nm, s);
            Database.addTicket(t);
            JOptionPane.showMessageDialog(this, "Ticket booked successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        p.add(book);
        add(p);
    }

    private JPanel labeled(String label, JComponent comp) {
        JPanel panel = new JPanel(new BorderLayout(6,6));
        panel.add(new JLabel(label), BorderLayout.WEST);
        panel.add(comp, BorderLayout.CENTER);
        return panel;
    }
}
