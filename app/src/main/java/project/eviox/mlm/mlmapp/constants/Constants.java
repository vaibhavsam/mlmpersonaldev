package project.eviox.mlm.mlmapp.constants;

import java.util.Random;

public class Constants {

    public static final String SP_NAME ="MLM_SOFT" ;
    public static final String SP_EMPEMAIL = "sp_email";
    public static final String SP_PASSWORD = "sp_password";
    public static final String SP_USER_ID ="sp_user_id";
    public static final String SP_USER_NAME = "sp_user_name";
    public static final String SP_USER_CONTACT = "sp_user_contact";
    public static final String SP_USER_POSITION ="sp_user_position";
    public static final String SP_REGISTRATION ="sp_registration_date";
    public static final String EXTRA_CUSTOMER_INFO = "CUSTOMER_INFO";
    public static final String EXTRA_CUSTOMER_INFO_c = "CUSTOMER_INFO_c";
    public static final String SP_MEMBER_NAME = "member_name";
    public static final String urlUpload = "http://192.168.1.105/uploadimage.php";

    public final static String drop_down_for_plans[] = {"Select plan","Silver Rs 500","Gold Rs 900","Platinum Rs 1400"};

    public static String reand(){

        Random r = new Random(); // just create one and keep it around
        String alphabet = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        final int N = 8;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        String randomName = sb.toString();

        System.out.println("rand"+randomName);
        return randomName;
    }

}
