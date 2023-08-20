package com.employee.Employee.Model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateTimeFormattingExample {
    
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");

        String formattedDate = dateTime.format(dateFormatter);
        String formattedTime = timeFormatter.format(Date.from(dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));

        String daySuffix = getDaySuffix(dateTime.getDayOfMonth());

        return formattedDate.replaceFirst("-(\\d{1,2})-", "-" + daySuffix + " $1-") + " " + formattedTime;
    }

    public static String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}
