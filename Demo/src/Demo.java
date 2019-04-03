import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

public class Demo {

    public volatile static long timeStamp;

    /**
     * 二分法查找
     * @param array
     *              数组
     * @param key
     *              关键值
     * @return
     *              返回值
     */
    public static boolean halfSearch(int[] array, int key){
        int min = 0;
        int max = array.length - 1;
        int mid = (min + max)/2;
        int i =0;
        while(array[mid] != key){
            System.out.println("查找次数：" + ++i);
            if(key > array[mid]){
                min = mid + 1;
            }
            if(key < array[mid]){
                max = mid - 1;
            }
            if(max < min){
                return false;
            }
            mid = (min + max)/2;
        }
        return true;
    }

    public static void testHalfSearch(){
        int[] array = {1,2,3,4,5,6,23,43,56,76};
        System.out.println(halfSearch(array,43));
    }

    public static  void  main(String[] args){
        testHalfSearch();
/*        int a =0 ;
        bk1:
            for(int j=0; j<10; j++){
                System.out.println(" j = " + j);
                bk2:   for(int m=0; m<5; m++){
                    System.out.println(" m = " + m);
                    if(j == 3){
                        break bk1;
                    }
                }
            }


            System.out.println("程序被打断");*/


        /*StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();//获取本地所有网络接口
            while (en.hasMoreElements()) {//遍历枚举中的每一个元素
                NetworkInterface ni= (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumInetAddr = ni.getInetAddresses();
                while (enumInetAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumInetAddr.nextElement();
//                    System.out.println("name:" + inetAddress.getHostName().toString());
//                    System.out.println("ip:" + inetAddress.getHostAddress().toString()+"\n");
//                    System.out.println();
//                    System.out.println(inetAddress.getLocalHost().toString());
                    if (!inetAddress.isLoopbackAddress()  && !inetAddress.isLinkLocalAddress()
                            && !inetAddress.isSiteLocalAddress()) {
                        sb.append("name:" + inetAddress.getHostName().toString()+"\n");
                        sb.append("ip:" + inetAddress.getHostAddress().toString()+"\n");
                    }
//                    System.out.println(" &&&&& " + inetAddress.toString());
                }
            }

        } catch (SocketException e) {

        }catch (Exception e){

        }
        System.out.println("所有的IP地址是：\n" + sb.toString());*/


    }
    static class CacheDemo{
        int id;
        String name;

        public CacheDemo(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "CacheDemo{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


    private static boolean execLocalNetworkClose() {
        boolean result = false;
        String device = getEthDeviceName();
        if (device == null || device.length() == 0) {
            System.out.println("can not get device.");
            return false;
        }
        String command = "ifconfig " + device + " down";
        System.out.println("Close network, the command is :" + command);
        try {
            BufferedReader br = runLocal(command);
            String line = br.readLine();
            System.out.println("line is " + line);
            while (line != null) {
                System.out.println("result is :" + line);
                if (line.contains("successfully") || line.contains("成功") || line.length() == 0) {
                    br.close();
                    result = true;
                    break;
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            //log
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static String getEthDeviceName() {
        String command = "ip link";
        try {
            String line;
            BufferedReader br = runLocal(command);
            while ((line = br.readLine()) != null) {
                String pre = line.substring(0, 20);
                String[] fragments = pre.split(":");
                if (fragments.length >= 2) {
                    String mayName = fragments[1].trim();
                    if (mayName.startsWith("eth") || mayName.startsWith("ens") || mayName.startsWith("eno")) {
                        br.close();
                        return mayName;
                    }
                }
            }
        } catch (Exception e) {
            //log
            e.printStackTrace();
        }
        return "";
    }


    private static BufferedReader runLocal(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

        return br;
    }


    private static void execLocalNetworkOpen() {
        while (true) {
            String device = getEthDeviceName();
            if (device == null || device.length() == 0) {
                //log
                System.out.println("exec network open failed. Can not get DEVICE");
                try {
                    Thread.sleep(2000);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            String command = "ifconfig " + device + " up";
            System.out.println("Open network, the command is :" + command);
            try {
                BufferedReader br = runLocal(command);
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Result of executing command is " + line);
                    if (line.contains("successfully") || line.length() == 0) {
                        br.close();
                        return;
                    }
                }
                if (line == null) {
                    return;
                }
            } catch (Exception e) {
                //log
                e.printStackTrace();
            }
        }
    }

}
