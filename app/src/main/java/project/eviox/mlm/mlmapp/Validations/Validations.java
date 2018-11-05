package project.eviox.mlm.mlmapp.Validations;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Validations {
    public static boolean isValidString(String... str){
        boolean result = false;
        for(int i=0; i<str.length;i++){
            if(str[i] == null || str[i].isEmpty() || str[i].equals("null")){
                System.out.println("false");
                return   false;
            }
        }
        return true;
    }

    public static double getFormattedPrice(double price){
        DecimalFormat dtime = new DecimalFormat("#.##");
        double formatedPrice= Double.valueOf(dtime.format(price));
        return formatedPrice;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return (cm.getActiveNetworkInfo() != null) && cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static  String getFormattedtDateTime(String str){

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Date date = null;
            date = format.parse(str);
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
            String formatedDate = df.format(date);
            System.out.println(formatedDate); // Sat Jan 02 00:00:00 GMT 2010
            return formatedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  String getFormattedtDate(String str){

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = null;
            date = format.parse(str);
            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formatedDate = df.format(date);
            System.out.println(formatedDate); // Sat Jan 02 00:00:00 GMT 2010
            return formatedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getCurrentTimeStamp(){
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            String currentDateTime = (dateFormat.format(new Date())); // Find todays date
            long millis = System.currentTimeMillis();
            return String.valueOf((millis));
        } catch (Exception e) {
            e.printStackTrace();

            return (null);
        }
    }

    public static String getStatus(String status){
        if(status.equals("0")){
            return  "Waiting for recommendation";
        }
        else if(status.equals("1")){
            return  "not recommended";
        }
        else if(status.equals("2")){
            return "recommended";
        }
        else if(status.equals("3")){
            return "Waiting for processing request";
        }
        else if(status.equals("4")){
            return "processed successfully";
        }
        else if(status.equals("5")){
            return "closed ";
        }
        return  "Waiting for recommendation";
    }

    public static boolean isValidPanCard(String str){
        String pattern = "((([a-zA-Z]{5})\\d{4})[a-zA-Z]{1})";
        if (str.matches(pattern)) {
            System.out.println("Valid Pan Card Number");
            return true;
        }

        return false;
    }
    public static boolean isValidPhone(String str){
        String number = "^((\\+[1-9]?[0-9])|0)?[7-9][0-9]{9}$";
        if(str.matches(number )){
            System.out.println("Valid Mobile Number");
            return true ;
        }
        return false;
    }
    public static boolean isValidVoterId(String str){
        String number="^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";
        if(str.matches(number)){
            System.out.println("Valid Voter ID Card");
            return true;
        }
        return false;

    }
    public static boolean isValidPassport(String str){
        String passport="(([a-zA-Z]{1})\\d{7})";
        if(str.matches(passport)){
            System.out.println("Valid Passport Number");
            return true;
        }
        return false;
    }
    public static boolean isValidAadharCard(String str){
        String addhar="^[2-9]{1}[0-9]{11}$";
        if(str.matches(addhar)){
            System.out.println("Valid addhar Card Details");
            return true;
        }
        return false;
    }
    public static boolean isValidDrivingLicense(String str){
        String driving="(?:[A-Z]{5}[*]?|[A-Z]{4}[*]|[A-Z]{3}[*]{2}|[A-Z]{2}[*]{3}|[A-Z][*]{4})[A-Z][A-Z*]\\d{3}[A-Z\\d]{2}";
        if(str.matches(driving)){
            System.out.println("Valid Driving Licence");
            return true;
        }
        return false;
    }
    public static boolean isValidEmail(String str){
        String email="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(str.matches(email)){
            System.out.println("Valid Email Address");
            return true;
        }
        return false;
    }
    public static boolean isValidChassisNumber(String str){
        String chassisNumber="[^\\Wioq]{17}";
        if(str.matches(chassisNumber)){
            System.out.println("Valid Chassis Number");
            return true;
        }
        return false;

    }

    public static boolean isValidVehicleNumber(String str){
        String vehicleNumberRegex = "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";
        if(str.matches(vehicleNumberRegex)){
            System.out.println("Valid vehicle registration number");
            return true;
        }
        System.out.println("INValid vehicle registration number");
        return false;
    }

    public static boolean isValidName(String str){
        String nameRegex = "^[A-Za-z]*$";
        if(str.matches(nameRegex)){
            System.out.println("Name is valid");
            return true;
        }
        System.out.println("Name is Invalid");
        return false;
    }

}
