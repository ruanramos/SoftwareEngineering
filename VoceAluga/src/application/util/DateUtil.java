package application.util;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
	   /** Standard conversion date */
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    
    /** date formater */
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    /**
     * Return with a formated String. The
     * {@link DateUtil#DATE_PATTERN}  (date standard) that is use.
     * 
     * @param date The date returned with a String
     * @return String formated
     */
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    /**
     * Converts a String to the defined format {@link DateUtil # DATE_PATTERN}
     * for an object {@link LocalDate}.
     * 
     * Returns null if the String can not be converted.
     * 
     * @param dateString to date as String
     * @return the object date or null if it can not be converted
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
    * Check if the String is a valid date.
    *
    * @param dateString The date as String
    * @return true if String is a valid date
    */
    public static boolean validDate(String dateString) {
    	// Try to convert the String.
        return DateUtil.parse(dateString) != null;
    }
}
