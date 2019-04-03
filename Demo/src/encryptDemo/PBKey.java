package encryptDemo;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/9
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class PBKey {

    private byte[] publicKey;

    public PBKey() {
    }

    public PBKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
