import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class CryptUtil {

    public static String encrypt(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("UTF-8");
        StringBuilder result = new StringBuilder();
        final String base64Table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        int length = bytes.length;

        for (int i = 0; i < bytes.length; i += 3) {
            int b1 = bytes[i] & 0xFF;
            int b2 = (i + 1 < bytes.length) ? bytes[i + 1] & 0xFF : 0;
            int b3 = (i + 2 < bytes.length) ? bytes[i + 2] & 0xFF : 0;
            int combined = (b1 << 16) | (b2 << 8) | b3;

            int c1 = (combined >> 18) & 0x3F;
            int c2 = (combined >> 12) & 0x3F;
            int c3 = (combined >> 6) & 0x3F;
            int c4 = combined & 0x3F;

            result.append(base64Table.charAt(c1));
            result.append(base64Table.charAt(c2));
            if (i + 1 < length) {
                result.append(base64Table.charAt(c3));
            } else {
                result.append('=');
            }
            if (i + 2 < length) {
                result.append(base64Table.charAt(c4));
            } else {
                result.append('=');
            }
        }
        return result.toString();
    }

    public static String decrypt(String base64Text) throws UnsupportedEncodingException {
        String base64Table = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int paddingCount = 0;

        while (base64Text.endsWith("=")) {
            base64Text = base64Text.substring(0, base64Text.length() - 1);
            paddingCount++;
        }

        int length = base64Text.length();
        for (int i = 0; i < length; i += 4) {
            int c1 = base64Table.indexOf(base64Text.charAt(i));
            int c2 = base64Table.indexOf(base64Text.charAt(i + 1));
            int c3 = (i + 2 < length) ? base64Table.indexOf(base64Text.charAt(i + 2)) : 0;
            int c4 = (i + 3 < length) ? base64Table.indexOf(base64Text.charAt(i + 3)) : 0;
            int combined = (c1 << 18) | (c2 << 12) | (c3 << 6) | c4;

            int b1 = (combined >> 16) & 0xFF;
            int b2 = (combined >> 8) & 0xFF;
            int b3 = combined & 0xFF;

            byteStream.write(b1);
            if ((i + 2) < length || paddingCount < 2) byteStream.write(b2);
            if ((i + 3) < length || paddingCount < 1) byteStream.write(b3);
        }

        return byteStream.toString("UTF-8");
    }
}
