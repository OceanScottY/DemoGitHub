package encryptDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nucypher.crypto.pkcrypto.eccEncryption;
import org.spongycastle.jce.interfaces.ECPublicKey;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import com.xiaomi.infra.ec.*;


/**
 * Created by Scott on 2019/2/25
 */
public class EncryptDemo {

    /**
     * 得到string的字节数组，并且该字节数组的长度是16的倍数，
     * @param
     * @return
     */
    public static byte[] getStrBytes(byte[] srcByte){
        byte[] res = null;
        int realLen = srcByte.length;
        //如果不是16的倍数，用空格补全
        if((realLen+4)%16 != 0){
            int dValue = 16 - (realLen+4)%16;
            int afterLen = realLen +4 + dValue;
            //补齐  16的倍数
            res = new byte[afterLen];

        }

        return null;
    }

    /**
     * 测试加密解密
     * @throws Exception
     */
    public static void testEncode() throws Exception{
        String keyPath = System.getProperty("user.dir") + "/mykeys";

        System.out.println(System.getProperty("user.dir"));
        eccEncryption ec1 = new eccEncryption();
        ec1.init(keyPath);

        eccEncryption ec2 = new eccEncryption();
        ec2.init(keyPath);



        String s = "this is EncryptExample and the length of s is 16 de beishu";

        ECPublicKey publicKey2 = ec2.getECPublicKey();
        byte[] res = null;
        try{
            res = ec1.Encrypt(s.getBytes("utf-8"), publicKey2);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        byte[] decode = ec2.Decrypt(res);
        System.out.println(new String(decode,"utf-8"));
    }

    /**
     * 测试解密
     * @throws Exception
     */
    public static void testDecode() throws Exception{

        String path1 = System.getProperty("user.dir") + "/mykeys1";
        String path2 = System.getProperty("user.dir") + "/mykeys2";

        eccEncryption ec1 = new eccEncryption();
        ec1.init(path1);
        eccEncryption ec2 = new eccEncryption();
        ec2.init(path2);
        String s = "this is DecryptExample and the length of s is 16 de beishu";
        ECPublicKey publicKey2 = ec2.getECPublicKey();

        byte[] ciphertext = ec1.Encrypt(s.getBytes("utf-8"), publicKey2);
        ec2.Decrypt(ciphertext);
        AtomicInteger a = new AtomicInteger(3);

    }


    /**
     * 测试签名
     */
    public static void testSignture() throws Exception{

        String path1 = System.getProperty("user.dir") + "/mykeys1";
        String path2 = System.getProperty("user.dir") + "/mykeys2";

        eccEncryption ec1 = new eccEncryption();
        ec1.init(path1);

        eccEncryption ec2 = new eccEncryption();
        ec2.init(path2);

        String s = "this is signatureExample";
        byte[] afterSign =  ec1.sign(s.getBytes("utf-8"));
        System.out.println("&&& end &&&");

    }


    /**
     * 测试验证签名
     * @throws Exception
     */
    public static void testSignatureVerify() throws Exception{

        String path1 = System.getProperty("user.dir") + "/mykeys1";
        String path2 = System.getProperty("user.dir") + "/mykeys2";

        eccEncryption ec1 = new eccEncryption();
        ec1.init(path1);
        eccEncryption ec2 = new eccEncryption();
        ec2.init(path2);

        String s = "this is signatureVerifyExample";
        byte[] sign = ec1.sign(s.getBytes("utf-8"));
        ECPublicKey publicKey1 = ec1.getECPublicKey();
        boolean res = ec2.verify(s.getBytes("utf-8"), sign, publicKey1);
        System.out.println(res);

    }



    public static void testReEncryptAndDecryptExample()throws Exception{

        /*String path1 = System.getProperty("user.dir") + "/mykeys1";
        String path2 = System.getProperty("user.dir") + "/mykeys2";

        eccEncryption ec1 = new eccEncryption();
        ec1.init(path1);
        eccEncryption ec2 = new eccEncryption();
        ec2.init(path2);
        String s = "this is signatureVerifyExample";
        byte[] cipertext = ec1.Encrypt(s.getBytes("utf-8"));
        ECPublicKey publicKey2 = ec2.getECPublicKey();
        ReencryptKey rekeyObject = ec1.generateReEncryptionKey(publicKey2);
        ec2.ReDecrypt(cipertext, rekeyObject);*/
    }

    public static int reverse(){
        int x = 1534236469;

        int maxint = 2147483647;
        int minint = -2147483648;
        long param = x;
        if(x < 0)
            param = -x;
        long result = 0;
        while(param != 0){
            result = result * 10 + param % 10;
            param = param / 10;
        }
        if(x < minint || x > maxint || result < minint || result > maxint){
            return 0;
        }
        if(x < 0){
            return -(int)result;
        }else{
            return (int)result;
        }
    }

    public static int[] plusOne(int[] digits) {
        int carrage = 0;
        for(int i=digits.length-1; i >=0 ; i--){
            digits[i] = digits[i] + carrage;
            if(digits[i] > 9){
                digits[i] -= 10;
                carrage = 1;
            }else{
                carrage = 0;
            }
            if(carrage == 0){
                break;
            }
        }
        if(carrage == 1){
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for(int i=0; i<digits.length; i++){
                res[i+1] = digits[i];
            }
            return res;
        }else{
            return digits;
        }
    }

    public static int romanToInt(String s) {
        Map<Character,Integer> romanMap = new HashMap();
        romanMap.put('M',1000);
        romanMap.put('D',500);
        romanMap.put('C',100);
        romanMap.put('L',50);
        romanMap.put('X',10);
        romanMap.put('V',5);
        romanMap.put('I',1);

        int pre =romanMap.get(s.charAt(s.length()-1));
        int current = 0;
        int result = pre;
        for(int i=1; i<s.length(); i++){
            current = romanMap.get(s.charAt(i-1));
            if(current >= pre){
                result += current;
            }else{
                result -= current;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{

        String s = "IV";
        System.out.println(romanToInt(s));


        /*int[] nums1 = {9,9,9,9,9,9,9,9};
        int[] nums2 = {1,2,3};
        System.out.println(Arrays.toString(plusOne(nums2)));*/

//        eccEncryption ec1 = new eccEncryption();
//        ec1.init( System.getProperty("user.dir") + "/mykeys1");
//
        ObjectMapper mapper = new ObjectMapper();
//        B b = new B(3, "于海洋", ec1.publicKey.getEncoded());
//        String res = mapper.writeValueAsString(b);
//        System.out.println(res);
//        B afterB = mapper.readValue(res, B.class);
//        System.out.println("sdfas");

//        ByteBuffer bb = ByteBuffer.allocate(4);
//        bb.asIntBuffer().put(256 * 256 * 256);
//        System.out.println(Arrays.toString(bb.array()));




//        testEncode();

        /*String publicKey = "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEPdCv62+duwdEp6r0+3CTilmqCigXLqIQPn4WBQva3Z2CLqLT8NHaNAzPBJ8uZ3GpELXvm40raygeafez0YsfmA==";
        String content = "A4DryjnWYeQTve+Np0g/uvxkFq6P1I00tBz9Z7xnRQDAAwN7oFCJjzBiZ9k1hC3k/SqUrpTIH/6woTfhQa5rUeFCAvDwyZwjkxD+3p3FO07sknU4XdhgNfSEdczlGSCH" +
                "4fErA5kW0/2RHxTWAnaP2CJB9MyWt8q4l38//dKqHlO0+5giAguaozam2NMfyeRHSJxq2q4xYhNzCoPTOdO5LHHbyFvGAhjeos5SQg9zddLkyzPUnsH7/itYk590rRponpf5ut6wA5" +
                "3x08s3kw9f7rq1JiwiccskIJLEFwAsFTRbi11YyMf9AlXD12InfXV5H7mK3D/ZKepacvDWzyiPNUVQFkmmwGGOAlijGDrm1t9rlCTTMyFJPaS76F3Ou8PslAgFf8uwkCNCA5PwVyT0f" +
                "20Xq8P3f66Iz1v8qXk07wQJznv4EXqaoP/3AxFvHCIEk2Ote7ZF0Ntnxon9TSxj/MptqIoKaU7TYU4SApEAEwOnCCdM321B6OagYxRvukrJhNWfMf4YoSV6qMFBAkwvUiOEaMhsarWB" +
                "HM++X8SY4pzFih1kCxTd7muq700sA5GFt7OBEFbMaEsAYUd/RkqFvF7ekEfPalDjTOy//0bXAppVQy6f+1unPERbUBu0jiOjWhGWu3SmAiFAOMTpjq9aAjo+VBPyWrnqQTFkkpm+v5lJ1" +
                "tclfCadbApx6lwgJEuhA/UomaKwgb0gHZdAJ5AjJDGbUivRSBXyuMPx2fsC1rZfA2rKJW1EhX4o9HPLLQBLMgGGjJOtfW52K0EI1NZa/JRRArXbSVN2nzmsprvaJ6FFVhAkEeO190lvR/5" +
                "BNOKVzbWnA5LfMspF0eiZaCtz37XIZDGd73pzPJ5JdmLNOWCA9zjpAgaJLJZ17VjZPl8f7x5OCgdv8vL6hXrrVqtIa/SqLQ3IA3FvthMrMjMVZrtCMN05pR9LTcGQpgTvXU2x9Kca2VUJA" +
                "89czJfu+PzA7rPhQbGeWqg4rGEgjNwz42LpoC1EuZRUA/WNoVNtuyGGr6lLvvO0sDeerJjvJafH9UM9P5yQw06f";
        eccEncryption ec1 = new eccEncryption();

        PBKey publicK = mapper.readValue(publicKey, PBKey.class);

        ec1.init( System.getProperty("user.dir") + "/mykeys1");*/
//        byte[] pkByte = ec
//        byte[] decode = ec1.Decrypt(content.getBytes("utf-8"),publicK.getPublicKey());




        /*Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        int numCount = 0, numBig = 0, numMin = 0;
        char[] arr = content.toCharArray();
        for(int i=0; i<arr.length; i++){
            if(arr[i] < 'z' && arr[i] > 'a'){
                numMin++;
            }
            if(arr[i] < 'Z' && arr[i] > 'A'){
                numBig++;
            }
            if(arr[i] <= '9' && arr[i] >= '0'){
                numCount++;
            }
        }
        System.out.println("大写：" + numBig + ",  小写：" + numMin + ",  数字：" + numCount);*/

    }

    public static void showDir(File file, int deep){
//        System.out.println(System.getProperty("user.dir"));
//        String dirPath = "test/A/B/C";
        if(file.isFile()){
            System.out.println("您给定的是一个文件夹");
        }else {
            File[] files = file.listFiles();
            for(int i=0; i<files.length; i++){
                for(int j=0; j<deep; j++){
                    System.out.print("|---");
                }
                System.out.println(files[i].getName());

                if(files[i].isDirectory()){
                    showDir(files[i],deep+1);
                }

            }
        }
    }
}
