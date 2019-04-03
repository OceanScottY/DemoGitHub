package encryptDemo;

/**
 * @文件描述： 用户认证加密、解密
 * @创建者： 于海洋
 * @创建日期： 2019/2/28
 * @版权声明： PKUSZ
 * @缩进/编码： tabstop=4 utf-8
 */
public class UserAuthEncrypt {

    private String publicKeyPath;
    private String privateKeyPath;
    private String keyPath;

    public UserAuthEncrypt(){
        super();
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public void setPublicKeyPath(String publicKeyPath) {
        this.publicKeyPath = publicKeyPath;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    /**
     * 使用默认的公私钥进行初始化
     * @return
     *          返回加密后的byte数组
     */
//    public byte[] decode(){
//
//
//    }

}
