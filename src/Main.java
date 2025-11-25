import javax.swing.*;
import ui.AddTripPopup;
import ui.BookTicket;
import ui.ViewTickets;
import model.Database;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Database.init();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Smart Ticket Reservation System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 450);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.insets = new Insets(10,10,10,10);

            JLabel title = new JLabel("Smart Ticket Reservation System");
            title.setFont(new Font("SansSerif", Font.BOLD, 20));
            c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
            panel.add(title, c);

            JButton addTripBtn = new JButton("Add Trip");
            addTripBtn.addActionListener(e -> new AddTripPopup(frame).setVisible(true));

            JButton bookTicketBtn = new JButton("Book Ticket");
            bookTicketBtn.addActionListener(e -> new BookTicket(frame).setVisible(true));

            JButton viewTicketsBtn = new JButton("View Tickets");
            viewTicketsBtn.addActionListener(e -> new ViewTickets(frame).setVisible(true));

            c.gridwidth = 1;
            c.gridx = 0; c.gridy = 1;
            panel.add(addTripBtn, c);
            c.gridx = 1; c.gridy = 1;
            panel.add(bookTicketBtn, c);
            c.gridx = 0; c.gridy = 2;
            panel.add(viewTicketsBtn, c);

            frame.getContentPane().add(panel);
            frame.setVisible(true);
        });
    }
}
