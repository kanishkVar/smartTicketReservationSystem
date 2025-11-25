package ui;

import model.Database;
import model.Ticket;
import model.Trip;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewTickets extends JDialog {
    public ViewTickets(Frame owner) {
        super(owner, "View Tickets", true);
        setSize(700, 350);
        setLocationRelativeTo(owner);

        List<Ticket> tickets = Database.getAllTickets();
        List<Trip> trips = Database.getAllTrips();
        Map<String, Trip> tripMap = trips.stream().collect(Collectors.toMap(Trip::getId, t -> t));

        String[] cols = {"Ticket ID","Passenger","Seats","Trip"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        for (Ticket t : tickets) {
            Trip tr = tripMap.get(t.getTripId());
            String tripLabel = tr == null ? "(deleted trip)" : tr.toString();
            model.addRow(new Object[]{t.getId(), t.getPassengerName(), t.getSeats(), tripLabel});
        }
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        JButton delete = new JButton("Delete Selected Ticket");
        delete.addActionListener(e -> {
            int r = table.getSelectedRow();
            if (r < 0) { JOptionPane.showMessageDialog(this, "Select a row first"); return; }
            String id = (String) model.getValueAt(r, 0);
            int ok = JOptionPane.showConfirmDialog(this, "Delete ticket?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                Database.deleteTicketById(id);
                model.removeRow(r);
            }
        });

        setLayout(new BorderLayout(6,6));
        add(sp, BorderLayout.CENTER);
        add(delete, BorderLayout.SOUTH);
    }
}
