import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("输入要加密的文本");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        scanner.close(); // 手动资源回收

        String encrypted = CryptUtil.encrypt(text);
        System.out.println("base64加密后的文本: " + encrypted);

        String decrypted = CryptUtil.decrypt(encrypted);
        System.out.println("base64解密结果: " + decrypted);
    }
}
