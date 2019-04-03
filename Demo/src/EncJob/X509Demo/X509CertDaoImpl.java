package EncJob.X509Demo;

import java.io.*;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import org.bouncycastle.x509.X509V3CertificateGenerator;

/**
 * @Author : Scott
 * @Date :   2019/1/6 13:33
 */
public class X509CertDaoImpl implements X509Dao{

//    public static final String Default_keyType="PKCS12";
    public static final String Default_keyType="jks";
    public static final String Default_KeyPairGenerator="RSA";
    public static final String Default_Signature="SHA1withRSA";
    public static final String cert_type="X509";
    public static final Integer Default_KeySize=2048;

    public static final String EXTENDSION_OID = "2.5.29.10";

    static {
        // 系统添加BC加密算法 以后系统中调用的算法都是BC的算法
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public void createCert(String issuer,Date notBefore,Date notAfter,String certDestPath,
                           BigInteger serial,String keyPassword,String alias) throws Exception{
        //产生公私钥对
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(Default_KeyPairGenerator);
        kpg.initialize(Default_KeySize);
        KeyPair keyPair = kpg.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // 组装证书
        X500Name issueDn = new X500Name(issuer);
        X500Name subjectDn = new X500Name(issuer);
        //组装公钥信息
        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo
                .getInstance(new ASN1InputStream(publicKey.getEncoded())
                        .readObject());

        X509v3CertificateBuilder builder = new X509v3CertificateBuilder(
                issueDn, serial, notBefore, notAfter, subjectDn,
                subjectPublicKeyInfo);
        //证书的签名数据
        ContentSigner sigGen = new JcaContentSignerBuilder(Default_Signature).build(privateKey);
        X509CertificateHolder holder = builder.build(sigGen);
        byte[] certBuf = holder.getEncoded();
        X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance(cert_type).generateCertificate(new ByteArrayInputStream(certBuf));
        // 创建KeyStore,存储证书
        KeyStore store = KeyStore.getInstance(Default_keyType);
        store.load(null, null);
        store.setKeyEntry(alias, keyPair.getPrivate(),
                keyPassword.toCharArray(), new Certificate[] { certificate });
        FileOutputStream fout =new FileOutputStream(certDestPath);
        store.store(fout, keyPassword.toCharArray());
        fout.close();
    }

    /**
     * 生成证书
     * @param applicant
     * @param notBefore
     * @param notAfter
     * @param keyPassword
     * @param alias
     * @return
     */
    private static byte[] generatePKCCert(String applicant, Date notBefore,Date notAfter, String keyPassword,String alias) {
        X509Certificate cert = null;
        X509V3CertificateGenerator certGen=new X509V3CertificateGenerator();

        try {
            // 生成RSA公私钥对
            KeyPairGenerator kpg = null;
            // 采用 RSA 非对称算法加密
            kpg = KeyPairGenerator.getInstance("RSA");
            // 初始化为2048 位        
            kpg.initialize(2048);
            KeyPair keyPair = kpg.generateKeyPair();
            // 公钥        
            PublicKey pubKey = keyPair.getPublic();
            // 私钥        
            PrivateKey priKey = keyPair.getPrivate();
            // 公钥
            certGen.setPublicKey(pubKey);
            // 设置序列号
            certGen.setSerialNumber(new BigInteger("12345678"));
            // 设置颁发者信息
            Hashtable kwMapIssuer = new Hashtable();
            Vector localVector = new Vector();
            kwMapIssuer.put(X509Principal.C,"CN");
            localVector.addElement(X509Principal.C);

            kwMapIssuer.put(X509Principal.CN,"ADMIN");
            localVector.addElement(X509Principal.CN);
            kwMapIssuer.put(X509Principal.E, "532164710@qq.com");
            localVector.addElement(X509Principal.E);

            certGen.setIssuerDN(new X509Principal(localVector, kwMapIssuer));
            //  设置申请者信息
            certGen.setSubjectDN(new X509Name(applicant));
            // 设置有效期
            certGen.setNotBefore(notBefore);
            certGen.setNotAfter(notAfter);
            // 设置扩展域，密钥用途
            certGen.addExtension(X509Extension.keyUsage, false, new KeyUsage(KeyUsage.digitalSignature));

            // 签名算法
            certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
            cert = certGen.generateX509Certificate(priKey, "BC");
        }catch (Exception e) {
            System.out.println(e.getClass() + e.getMessage());
        }
        try {
            return cert.getEncoded();
        }catch (CertificateEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    static public void writeFile(String name, byte[] data) {
        if (data == null){
            return;
        }
        FileOutputStream fop = null;
        try {
            fop = new FileOutputStream(new File(name));
            fop.write(data);
            fop.close();

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 读取X509证书
     * @param fileName
     * @return
     * @throws Exception
     */
    static public X509Certificate readX509Cert(String fileName) throws Exception{
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate)cf.generateCertificate(new FileInputStream(fileName));
        return cert;
    }

    private static byte[] generateACCert(String applicant, Date notBefore,Date notAfter,
                                        String keyPassword,String alias,String fileName, String userType) {

        X509Certificate cert = null;
        X509V3CertificateGenerator certGen=new X509V3CertificateGenerator();

        try {
            // 生成RSA公私钥对
            KeyPairGenerator kpg = null;
            // 采用 RSA 非对称算法加密
            kpg = KeyPairGenerator.getInstance("RSA");
            // 初始化为2048 位        
            kpg.initialize(2048);
            KeyPair keyPair = kpg.generateKeyPair();
            // 公钥        
            PublicKey pubKey = keyPair.getPublic();
            // 私钥        
            PrivateKey priKey = keyPair.getPrivate();
            // 公钥
            certGen.setPublicKey(pubKey);
            // 设置序列号
            certGen.setSerialNumber(new BigInteger("12345678"));
            // 设置颁发者信息
            Hashtable kwMapIssuer = new Hashtable();
            Vector localVector = new Vector();
            kwMapIssuer.put(X509Principal.C,"CN");
            localVector.addElement(X509Principal.C);

            kwMapIssuer.put(X509Principal.CN,"ADMIN");
            localVector.addElement(X509Principal.CN);
            kwMapIssuer.put(X509Principal.E, "532164710@qq.com");
            localVector.addElement(X509Principal.E);

            certGen.setIssuerDN(new X509Principal(localVector, kwMapIssuer));
            //  设置申请者信息
            certGen.setSubjectDN(new X509Name(applicant));
            // 设置有效期
            certGen.setNotBefore(notBefore);
            certGen.setNotAfter(notAfter);
            // 设置扩展域，密钥用途
            certGen.addExtension(X509Extension.keyUsage, false, new KeyUsage(KeyUsage.digitalSignature));

            //设置扩展域属性值
            certGen.addExtension(EXTENDSION_OID,false,userType.getBytes());
            // 签名算法
            certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
            cert = certGen.generateX509Certificate(priKey, "BC");
        }catch (Exception e) {
            System.out.println(e.getClass() + e.getMessage());
        }
        try {
            return cert.getEncoded();
        }catch (CertificateEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成PKC证书
     * @param applicant
     * @param notBefore
     * @param notAfter
     * @param keyPassword
     * @param alias
     * @param fileName
     * @return
     */
    public static boolean genPKCCert(String applicant, Date notBefore,Date notAfter, String keyPassword,String alias,String fileName){
        byte[] crtBuf = generatePKCCert(applicant,notBefore,notAfter,keyPassword,alias);
        if(crtBuf == null){
            return false;
        }else {
           writeFile(fileName,crtBuf);
        }
        return true;
    }

    /**
     * 生成AC证书
     * @param applicant
     * @param notBefore
     * @param notAfter
     * @param keyPassword
     * @param alias
     * @param fileName
     * @param userType
     * @return
     */
    public static boolean genACCert(String applicant, Date notBefore,Date notAfter,
                                        String keyPassword,String alias,String fileName, String userType){
        byte[] crtBuf = generateACCert(applicant,notBefore,notAfter,keyPassword,alias,fileName,userType);
        if(crtBuf == null){
            return false;
        }else {
            writeFile(fileName,crtBuf);
        }
        return true;
    }

    /**
     * 证书校验
     * @param fileName
     * @return
     * @throws Exception
     */
    static public boolean verifyPKCCertification(String fileName) throws Exception{
        KeyPairGenerator kpg = null;
        // 采用 RSA 非对称算法加密
        kpg = KeyPairGenerator.getInstance("RSA");
        // 初始化为2048 位        
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        X509Certificate srcCert = readX509Cert(fileName);
        srcCert.verify(srcCert.getPublicKey());
        /*//公钥
        certGen.setPublicKey(srcCert.getPublicKey());
        //设置序列号
        certGen.setSerialNumber(srcCert.getSerialNumber());
        //设置颁发者信息
        certGen.setIssuerDN(new X509Name(srcCert.getIssuerDN().toString()));
        //设置申请者信息
        certGen.setSubjectDN(new X509Name(srcCert.getSubjectDN().toString()));
        //设置有效期
        certGen.setNotBefore(srcCert.getNotBefore());
        certGen.setNotAfter(srcCert.getNotAfter());
        // 设置扩展域，密钥用途
        certGen.addExtension(X509Extension.keyUsage, false, new KeyUsage(KeyUsage.digitalSignature));
        //签名算法
        certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
        cert = certGen.generateX509Certificate(priKey, "BC");

        byte[] certEncode = cert.getEncoded();
        //生成临时证书
        String tempFilePath = genTempCertPath(fileName);
        writeFile(tempFilePath,certEncode);

        X509Certificate tempCert = readX509Cert(tempFilePath);
        byte[] tempSign = tempCert.getSignature();
        byte[] srcSign = srcCert.getSignature();
        System.out.println(tempSign.equals(srcSign));*/


        return true;
    }


    /**
     * 校验AC证书
     * @param fileName
     * @return
     * @throws Exception
     */
    static public String verifyACCertification(String fileName) throws Exception{
        KeyPairGenerator kpg = null;
        // 采用 RSA 非对称算法加密

        kpg = KeyPairGenerator.getInstance("RSA");
        // 初始化为2048 位        
        kpg.initialize(2048);
        KeyPair keyPair = kpg.generateKeyPair();
        X509Certificate srcCert = readX509Cert(fileName);
        srcCert.verify(srcCert.getPublicKey());

        String userType = getAuthorityFronCert(fileName).trim();

        if(UserAuthority.ADMIN.getUserType().equals(userType)){
            return UserAuthority.ADMIN.getAuthValue();
        }else if(UserAuthority.USERLOGIN.getUserType().equals(userType)){
            return UserAuthority.USERLOGIN.getAuthValue();
        }else if(UserAuthority.VISITOR.getUserType().equals(userType)){
            return UserAuthority.VISITOR.getAuthValue();
        }else {
            return "AC证书校验失败";
        }

    }

    /**
     * 获取临时证书的存放路径
     * @param fileName
     * @return
     */
    private static String genTempCertPath(String fileName){
        int beginIndex = fileName.lastIndexOf(File.separator);
        int endIndex = fileName.indexOf(".");
        String resultBefor = fileName.substring(0,beginIndex+1);
        String resultAfter = fileName.substring(endIndex,fileName.length());
        String res = resultBefor + "temp" + resultAfter;
        return res;
    }


    /**
     * 从证书中获取属性：用户权限
     * @param fileName
     * @return
     * @throws Exception
     */
    static public String getAuthorityFronCert(String fileName) throws Exception{
        X509Certificate cert = readX509Cert(fileName);
        byte[] str = cert.getExtensionValue(EXTENDSION_OID);
        String result = new String(str);
        return result;
    }

    @Override
    public void printCert(String certPath, String keyPassword) throws Exception{
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(Default_keyType);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        System.out.println("keystore type=" + ks.getType());
        Enumeration enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements())
        {
            keyAlias = (String)enumas.nextElement();
            System.out.println("alias=[" + keyAlias + "]");
        }
        System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias,charArray );
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        System.out.println("cert class = " + cert.getClass().getName());
        System.out.println("cert = " + cert);
        System.out.println("public key = " + pubkey);
        System.out.println("private key = " + prikey);
    }


    @Override
    public  PublicKey getPublicKey(String certPath, String keyPassword) throws Exception{
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(Default_keyType);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        Enumeration enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements())
        {
            keyAlias = (String)enumas.nextElement();
            Certificate certificate = ks.getCertificate(keyAlias);

            return ks.getCertificate(keyAlias).getPublicKey();
        }
        return null;
    }
    @Override
    public  PrivateKey getPrivateKey(String certPath, String keyPassword) throws Exception{
        char[] charArray = keyPassword.toCharArray();
        KeyStore ks = KeyStore.getInstance(Default_keyType);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        Enumeration enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements())
        {
            keyAlias = (String)enumas.nextElement();
            Certificate certificate = ks.getCertificate(keyAlias);

            return (PrivateKey) ks.getKey(keyAlias, charArray);
        }
        return null;
    }


    @Override
    public void certDelayTo(Date endTime,String certPath,String password) throws Exception{

    }

    @Override
    public void changePassword(String certPath,String oldPwd,String newPwd) throws Exception{
        KeyStore ks = KeyStore.getInstance(Default_keyType);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, oldPwd.toCharArray());
        fis.close();
        FileOutputStream output = new  FileOutputStream(certPath);
        ks.store(output,newPwd.toCharArray());
        output.close();
    }

    @Override
    public void deleteAlias(String certPath,String password,String alias,String entry) throws Exception{
        char[] charArray = password.toCharArray();
        KeyStore ks = KeyStore.getInstance(Default_keyType);
        FileInputStream fis = new FileInputStream(certPath);
        ks.load(fis, charArray);
        fis.close();
        if(ks.containsAlias(alias)){
            ks.deleteEntry(entry);
            FileOutputStream output = new  FileOutputStream(certPath );
            ks.store(output,password.toCharArray());
            output.close();
        }else{
            throw new Exception("该证书未包含别名--->"+alias);
        }
    }


    public static void main(String[] args) throws Exception {
       /* X509CertDaoImpl impl=new X509CertDaoImpl();
        String issuer="C=CN,ST=BJ,L=BJ,O=testserver,OU=testserver,CN=testserver";
        String certDestPath="e://test.crt";
        BigInteger serial=BigInteger.valueOf(System.currentTimeMillis());
        String keyPassword="123456";
        String alias="test";
//        impl.createCert(issuer, new Date(), new Date("2017/09/27"), certDestPath, serial, keyPassword, alias);
        //impl.changePassword(certDestPath, "123", "123");
        //impl.createCert(issuer, new Date(), new Date("2017/09/27"), certDestPath, serial, keyPassword, alias);
        //未实现
//        impl.certDelayTo(new Date("2017/09/28"), certDestPath, keyPassword);
        impl.printCert(certDestPath, keyPassword);*/
        String applicant = "C=CN,ST=guangdong,L=shenzhen,O=EncGroup,OU=PKULAB,CN=yuhy";
       byte[] crtBuf = generatePKCCert(applicant,new Date(),new Date("2030/09/27"),"123456","yuhy");
       if(crtBuf != null){
//           writeFile("e://aaa.crt",crtBuf);
       }
//        readX509Cert("e://aaa.crt");

        boolean res = genACCert(applicant,new Date(),new Date("2031/09/27"),"123456","yhy",
                "e://admin.crt",UserAuthority.ADMIN.getUserType());
        System.out.println(res);
//        System.out.println(getAuthorityFronCert("e://bbb.crt").trim().equals(UserAuthority.ADMIN.getUserType()));

//        verifyPKCCertification("e:\\aaa.crt");

        System.out.println(verifyACCertification("e://admin.crt"));
//           System.out.println(genTempCertPath("e:\\aaa.crt"));

    }




}
