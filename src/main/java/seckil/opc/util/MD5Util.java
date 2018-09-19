package seckil.opc.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String string){
        return DigestUtils.md5Hex(string);
    }

    private static final String salt = "1a2b3c4d";


    public static String inputPassToFormPass(String inputpass){
        String str = salt.charAt(0)+salt.charAt(2)+inputpass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String FormPassToDBPass(String frompass,String salt){
        String str = salt.charAt(0)+salt.charAt(2)+frompass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input,String saltDB){
        String fromPass = inputPassToFormPass(input);
        String dbpass = FormPassToDBPass(fromPass,saltDB);
        return dbpass;
    }


}
