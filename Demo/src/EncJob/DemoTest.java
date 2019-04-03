package EncJob;

import EncJob.X509Demo.X509CertDaoImpl;

import java.util.Date;

/**
 * Created by Scott on 2019/1/6
 */
public class DemoTest {

    public static void main(String[] args) throws Exception{
        //生成PKC证书
        String applicantYU = "C=CN,ST=guangdong,L=shenzhen,O=EncGroup,OU=PKULAB,CN=yuhy";
        String fileNameYU = "e://yuhy.crt";
        Date afterDateYU = new Date("2030/09/27");
        String passYU = "123456";
        String aliasYU = "yuhy";
        boolean isSuc = X509CertDaoImpl.genPKCCert(applicantYU,new Date(),afterDateYU,passYU,aliasYU,fileNameYU);
        if(isSuc){
            System.out.println("证书已生成，证书：" + fileNameYU);
        }
        //校验：
//        System.out.println("校验结果：" + X509CertDaoImpl.verifyCertification(fileNameYU));

        //生成AC证书
        //1、




    }

}
