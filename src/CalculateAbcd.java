import java.io.IOException;
import java.util.*;//获取输入需要


public class CalculateAbcd{
    public static void main(String[]args)throws IOException{
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入一个包含四个数的字符串，各数之间使用英文分号分隔，形如：a;b;c;d\n");
        String FakeAbcd=scanner.nextLine();
        //靓点解析：主动回收资源
        scanner.close();
        String[] abcd=FakeAbcd.split(";");

        double a=Double.parseDouble(abcd[0]);
        double b=Double.parseDouble(abcd[1]);
        double c=Double.parseDouble(abcd[2]);
        double d=Double.parseDouble(abcd[3]);
            //sin的official document:https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/lang/Math.html#sin(double)
            //sin⁡a×cos⁡b×√(c^d )
            double sina=Math.sin(a);
            double cosb=Math.cos(b);
            //ApiOfPow:https://docs.oracle.com/en/java/javase/23/docs/api/java.base/java/lang/Math.html#pow(double,double)
            double cd=Math.pow(c,d);
            double sqrtCd=Math.sqrt(cd);
                //集齐所有元素
                double result=sina*cosb*sqrtCd;
                int intResult=(int)Math.round(result);
                System.out.println("算数结果是："+intResult);
            }
}