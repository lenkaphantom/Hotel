package view.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toedter.calendar.JCalendar;

public class OccupiedDatesPopup extends JDialog {

    private static final long serialVersionUID = 1L;
    private JCalendar calendar;

    public OccupiedDatesPopup(JFrame parent, List<LocalDate> occupiedDates) {
        super(parent, "Zauzeti datumi", true);
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);

        calendar = new JCalendar();

        JPanel panel = new JPanel();
        panel.add(calendar);

        add(new JScrollPane(panel));

        highlightOccupiedDates(occupiedDates);
        
        calendar.setDate(calendar.getDate());
        calendar.repaint();
    }

    private void highlightOccupiedDates(List<LocalDate> occupiedDates) {
        for (LocalDate date : occupiedDates) {
            Date utilDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            calendar.getDayChooser().addDateEvaluator(new OccupiedDateEvaluator(utilDate));
        }
    }

    private class OccupiedDateEvaluator implements com.toedter.calendar.IDateEvaluator {
        private Date date;

        public OccupiedDateEvaluator(Date date) {
            this.date = date;
        }

        @Override
        public boolean isSpecial(Date date) {
            return date.equals(this.date);
        }

        @Override
        public Color getSpecialForegroundColor() {
            return Color.BLACK;
        }

        @Override
        public Color getSpecialBackroundColor() {
            return Color.RED;
        }

        @Override
        public String getSpecialTooltip() {
            return "Zauzeto";
        }

        @Override
        public boolean isInvalid(Date date) {
            return false;
        }

        @Override
        public Color getInvalidForegroundColor() {
            return null;
        }

        @Override
        public Color getInvalidBackroundColor() {
            return null;
        }

        @Override
        public String getInvalidTooltip() {
            return null;
        }
    }
}
