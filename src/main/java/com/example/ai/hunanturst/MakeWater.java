package com.example.ai.hunanturst;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author wjh on 18-6-4.
 */
public class MakeWater {

  private String request_no;
  private String merchantCredit_no = "d9sxb35e-c4b6-4a3f-b4f6-c7ebae8a2130" ; //商户授信编号    c9sxb35e-c4b6-4a3f-b4f6-c7ebae8a0004
  private String merchantContract_no = "3fe9db97-27fb-4e53-9975-2684302bv30";

  private static final String privateKeyString = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMyYDfS68R/TqZT/CcKtU+2a+/sA+cG0JKs9R/nTC9gNbZtyzAwQrdEBQAKXzl3co9CwzZdiIgRX6ALKag/lYoKezY7UifF07vFyTM2gfpuP1ORh5Dt854n2do5+ovBYaLq+zq9NAlRktvcWAI+uVsgzEKADr4aRLiRapfZ71gU7AgMBAAECgYAWsHY7wc+j2/FemLoYYOiB3UI8n+sS1EuMwgsNZZ5Wo4aYSq7eV6svFphmsTctqZ5xMmpac4OaP7V3OcNxZ9r4tqhMANq00mNwNMCzcsPZ/wXE2+jGLy54OatuT908imRqLE9zLR5OxRtVc3nm5MHTidn8cr666pMzzKSDUkyzoQJBAOcp7Jfw+zXJh+PqgmgV0WGJDMs7Rk9juI7Qo0bFf6Lih7jXSL6iVAYqiJYX3Ts9ZjZVP117Id+lHcHIPjev7FcCQQDik1ZlgGNCGH/zgx1O+gWtP3KmqgLYiMUkdHduH8kzurthfR/zD2pXJenSWnQ5BsZQOhlVFH5KL+YOf+Mjmh+9AkEAyPMV/CN9jY1qtwNmZ6sHwD0ORSF7BoqOpn/SYDRRtzwrddCYKTgdyNpyr9+A7v15/CNxGQdwM+Vqj8lN5MTmswJAHl5BJjmfFCzUeX8JXpyERkRKyavf1cX/JnO1zjzUauqMUvTCY4GdbzDVtiwJh9swmXAwFQc6JhdlbmwVtZ/iwQJBAL9Vihy19dm+T9OXiutV1gS1abIsBwjeyZbx+jBrm+PJQCLkzZlFXELFg7z7qR3Iwrgh/CvWd7mo2Zv5SKzoncs=";
  private static final String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMmA30uvEf06mU/wnCrVPtmvv7APnBtCSrPUf50wvYDW2bcswMEK3RAUACl85d3KPQsM2XYiIEV+gCymoP5WKCns2O1InxdO7xckzNoH6bj9TkYeQ7fOeJ9naOfqLwWGi6vs6vTQJUZLb3FgCPrlbIMxCgA6+GkS4kWqX2e9YFOwIDAQAB";
  public void creditApplay1(String merchantCredit_no,String name,String certificateNo,String birthDate,String  mobile
      ,String gender,String permanentAddress,String applyAmount,String highestDiploma,String highestDegree
      ,String jobCategory,String job,String jobTitle, String organizationName,String organizationIndustry
      ,String annualIncome,String maritalStatus,String residenceAddress,String residenceCondition,String residenceTel) {
    try {

      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo",request_no);
      requestBody.put("productCode",PreloanBaseApiPost.produt_code);
      requestBody.put("merchantCreditNo", merchantCredit_no);
      requestBody.put("roleType", "0");        //自然人
      requestBody.put("callbackUrl", "http://www.baidu.com");
// 基础信息段
      Map<String, Object> baseInfoSectionMap = new HashMap<>();
      baseInfoSectionMap.put("name",name);                      //必填
      baseInfoSectionMap.put("certificateType", "0");                    //固定0身份证 必填
      baseInfoSectionMap.put("certificateNo","330501199207238018"); //必填
      baseInfoSectionMap.put("certificateIndate", "2028-01-04");
      baseInfoSectionMap.put("mobile", mobile);      //必填
      baseInfoSectionMap.put("email", "15671625566@164.com");
      baseInfoSectionMap.put("gender", "1");       //必填   1或2
      baseInfoSectionMap.put("birthDate", "19920723");      //必填  和身份证匹配
      baseInfoSectionMap.put("permanentAddress", "浙江省湖州市南浔区南浔镇鼓楼15号");      //必填
      baseInfoSectionMap.put("applyAmount", "20000");     //必填
//教育信息段

      Map<String, Object> eduInfoSectionMap = new HashMap<>();
      eduInfoSectionMap.put("highestDiploma", "1");       //必填 0-8
      eduInfoSectionMap.put("highestDegree", "4");        //必填 0-4
//职业信息段
      Map<String, Object> jobInfoSectionMap = new HashMap<>();
      jobInfoSectionMap.put("jobCategory", "3");    //必填 0-7
      jobInfoSectionMap.put("job", "2");  //必填   0-3 99
      jobInfoSectionMap.put("jobTitle", "0");   //必填  0-3  99
      jobInfoSectionMap.put("organizationName", "杭州随地付");   //必填
      jobInfoSectionMap.put("organizationTel","15671556601");
      jobInfoSectionMap.put("organizationIndustry", "14");      //必填 0-19
      jobInfoSectionMap.put("annualIncome", "20000");     //必填
      jobInfoSectionMap.put("organizationStartYear", "1994");
      jobInfoSectionMap.put("organizationPostalcode", "434158");
      jobInfoSectionMap.put("organizationAddress", "");
      jobInfoSectionMap.put("organizationProvince", "333333");
      jobInfoSectionMap.put("organizationCity", "");
//婚姻信息段
      Map<String, Object> marriageInfoSectionMap = new HashMap<>();


      marriageInfoSectionMap.put("maritalStatus", "0"); // 0 2 3 6 7 8 99
      marriageInfoSectionMap.put("spouseName", "mikd");
      marriageInfoSectionMap.put("spouseMobile", "15677445595");
      marriageInfoSectionMap.put("spouseCertificateType", "0");
      marriageInfoSectionMap.put("spouseCertificateNo","421024199801012264");
      marriageInfoSectionMap.put("spouseCertificateIndate", "2022-01-08");
      marriageInfoSectionMap.put("spouseOrganizationName","suidifu");
//居住信息段
      Map<String,Object> residenceInfoSectionMap = new HashMap<>();
      residenceInfoSectionMap.put("residenceProvince", "");
      residenceInfoSectionMap.put("residenceCity", "330100");
      residenceInfoSectionMap.put("residenceAddress", "浙江省湖州市南浔区南浔镇鼓楼15号");         //必填
      residenceInfoSectionMap.put("residencePostalcode", "330000");
      residenceInfoSectionMap.put("residenceCondition","4");       //必填 0-6 99
      residenceInfoSectionMap.put("residenceTel", "18814865770");      //必填
//银行卡信息段
      Map<String, Object> bankcardInfoSectionMap = new HashMap<>();
      bankcardInfoSectionMap.put("bankcardNo","46454554135");
      bankcardInfoSectionMap.put("bankName", "C10304");
      bankcardInfoSectionMap.put("bankBranchName", "杭州分行");
      bankcardInfoSectionMap.put("bankBranchProvince","330000");
      bankcardInfoSectionMap.put("bankBranchCity", "330100");
      bankcardInfoSectionMap.put("bankcardBindingMobile", "15671556669");
//联系人信息段
      List<Map<String, Object>> contactInfoSection = new ArrayList<>();
      Map<String, Object> contactInfoSectionMap1 = new HashMap<>();
      contactInfoSectionMap1.put("relationship","1");
      contactInfoSectionMap1.put("name", "cind2");
      contactInfoSectionMap1.put("mobile", "15671554480");
      contactInfoSectionMap1.put("certificateType", "0");
      contactInfoSectionMap1.put("certificateNo", "421024199511225570");
      contactInfoSectionMap1.put("certificateIndate", "2022-11-21");
      contactInfoSectionMap1.put("organizationName", "suidifu");
      contactInfoSection.add(contactInfoSectionMap1);

      Map<String, Object> contactInfoSectionMap2 = new HashMap<>();
      contactInfoSectionMap2.put("relationship","2");
      contactInfoSectionMap2.put("name", "windc");
      contactInfoSectionMap2.put("mobile", "13671554479");
      contactInfoSectionMap2.put("certificateType", "0");
      contactInfoSectionMap2.put("certificateNo", "422024199511225570");
      contactInfoSectionMap2.put("certificateIndate", "2022-11-22");
      contactInfoSectionMap2.put("organizationName", "suidifu");
      contactInfoSection.add(contactInfoSectionMap2);
      //保单信息段
      Map<String, Object> policyInfoSectionMap = new HashMap<>();
      policyInfoSectionMap.put("policyCorporationName", "中国人寿");
      policyInfoSectionMap.put("policyStatus", "0");
      policyInfoSectionMap.put("policyPaymentWay", "0");
      policyInfoSectionMap.put("policyValidDate", "2018-01-03");
//      policyInfoSectionMap.put("policyPaymentExpire", "3");                    //暂时不填,填了报错
      policyInfoSectionMap.put("policyPaymentStatus", "0");
      policyInfoSectionMap.put("policyAnnualPaymentAmount", "5160.12");
      //公基金信息段
      Map<String, Object> providentFundInfoSectionMap = new HashMap<>();
      providentFundInfoSectionMap.put("providentFundIdentityConsistent", "0");
      providentFundInfoSectionMap.put("providentFundAccountStatus", "0");
      providentFundInfoSectionMap.put("providentFundPaymentStatus", "0");
      providentFundInfoSectionMap.put("providentFundPaymentRecord", "1");
      providentFundInfoSectionMap.put("providentFundMonthBaseNum", "1600.12");
//附加内容
      Map<String ,Object> attachedContentSectionMap=new HashMap<>();
      attachedContentSectionMap.put("agricultureRelated","1");
      attachedContentSectionMap.put("ruralFinancialCustomer","1");
      attachedContentSectionMap.put("familyMonthlyIncome","100000");
      attachedContentSectionMap.put("flfcType","A09");
      attachedContentSectionMap.put("housePurchaseSituation","001");
      attachedContentSectionMap.put("economicNature","06");
      attachedContentSectionMap.put("industryType","");
      attachedContentSectionMap.put("mainGuaranteeMode","");
      attachedContentSectionMap.put("useDescription","");
      attachedContentSectionMap.put("industryTypeNew","");
//外围客户编号集
      List<Map<String,Object>> externalCustomerNoSection=new ArrayList<>();
      Map<String,Object> externalCustomerNoSectionMap1=new HashMap<>();
      externalCustomerNoSectionMap1.put("no1","");
      externalCustomerNoSectionMap1.put("no2","");
      externalCustomerNoSection.add(externalCustomerNoSectionMap1);
      Map<String,Object> externalCustomerNoSectionMap2=new HashMap<>();
      externalCustomerNoSectionMap2.put("no1","");
      externalCustomerNoSectionMap2.put("no2","");
      externalCustomerNoSection.add(externalCustomerNoSectionMap2);

      requestBody.put("baseInfoSection",baseInfoSectionMap);
      requestBody.put("eduInfoSection",eduInfoSectionMap);
      requestBody.put("jobInfoSection",jobInfoSectionMap);
      requestBody.put("marriageInfoSection",marriageInfoSectionMap);
      requestBody.put("residenceInfoSection",residenceInfoSectionMap);
      requestBody.put("bankcardInfoSection", bankcardInfoSectionMap);
//      requestBody.put("contactInfoSection",contactInfoSection);                 //暂时不填,填了报错
      requestBody.put("policyInfoSection",policyInfoSectionMap);
      requestBody.put("providentFundInfoSection",  providentFundInfoSectionMap);

      requestBody.put("attachedContentSection",attachedContentSectionMap);
      requestBody.put("externalCustomerNoSection",externalCustomerNoSection);
      String result = httpUtils(PreloanBaseApiPost.Credit_Applay,requestBody);
      System.out.println("-------------------------");
      System.out.println(result);
      System.out.println("-------------------------");
    }catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }
  public void testSave2(String merchantContract_no,String merchantCredit_no,String name,String principalSum,String interestSum
      ,String effectiveDate,String expirationDate,String interestRate,String payInterestCycle,String repaymentMod){
    try {

      System.out.println("effectiveDate:"+effectiveDate+"  expirationDate:"+expirationDate);
      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo", request_no);
      requestBody.put("productCode",PreloanBaseApiPost.produt_code );
      requestBody.put("merchantCreditNo",merchantCredit_no );
      requestBody.put("merchantContractNo", merchantContract_no);
      requestBody.put("vedaCreditNo", "");  //SX183341644752023552
      requestBody.put("txType", "0");      //必填     0
      requestBody.put("principalSum", "20000");  //1200.00 //必填
      requestBody.put("interestSum", "108.333333333333");   //10.12    //必填
      requestBody.put("costSum", "105");      //1210.12
      requestBody.put("signDate", "2018-03-04");   //2018-01-01
      requestBody.put("effectiveDate", "20180611");  //2018-01-01    //必填
      requestBody.put("expirationDate","20180711");  //    必填
      requestBody.put("valueDate", "2018-11-05");    //2018-11-01
      requestBody.put("dueDate", "2018-12-05");      //2018-0-01

      requestBody.put("interestRate", interestRate);     //0.1      //必填  大于4
      requestBody.put("interestRateCycle", "0");       //0
      requestBody.put("payInterestCycle", payInterestCycle);        // 付息周期-填1、3、6、12、0
      requestBody.put("repaymentMod", repaymentMod);            //0-2     不要填99
      requestBody.put("repaymentPeriodsCount", "3");   //3
      requestBody.put("intendedUse", "1");             //1
      requestBody.put("cooperationPlatformProductName", "融汇");  //100
      requestBody.put("cooperationPlatformGrade", "");       //
      requestBody.put("callbackUrl", "http://www.baidu.com");     //http://www.baidu.com

      List<Map<String, Object>> loanInfoSection = new ArrayList<>();
      Map<String, Object> loanInfoSectionMap = new HashMap<>();
      loanInfoSectionMap.put("payeeType","0");                   //0
      loanInfoSectionMap.put("payeeBankAccountNo","123455");      //123456
      loanInfoSectionMap.put("payeeBankAccountName",name);      //tom
      loanInfoSectionMap.put("payeeBankCode","cb1225");            //cb1221
      loanInfoSectionMap.put("payeeBankName","杭州银行");         //杭州银行
      loanInfoSectionMap.put("payeeBankCnapsCode","133410");      //123456
      loanInfoSectionMap.put("payeeBankProvince","浙江");          //浙江
      loanInfoSectionMap.put("payeeBankCity","杭州");              //杭州
      loanInfoSectionMap.put("loanAmount","10012.00");              //1000.12
      loanInfoSection.add(loanInfoSectionMap);



      List<Map<String, Object>> externalNoSection = new ArrayList<>();
      Map<String, Object> externalNoSectionMap = new HashMap<>();
      externalNoSectionMap.put("supervisionReportBusinessNo", "");  //123
      externalNoSection.add(externalNoSectionMap);

      Map<String,Object>attachedContentSectionMap= new HashMap<>();
      attachedContentSectionMap.put("agricultureRelated","");
      attachedContentSectionMap.put("ruralFinancialCustomer","");
      attachedContentSectionMap.put("familyMonthlyIncome","");
      attachedContentSectionMap.put("FLFCType","");
      attachedContentSectionMap.put("housePurchaseSituation","");
      attachedContentSectionMap.put("economicNature","");
      attachedContentSectionMap.put("industryType","");
      attachedContentSectionMap.put("mainGuaranteeMode","");
      attachedContentSectionMap.put("useDescription","");
      attachedContentSectionMap.put("industryTypeNew","");

      requestBody.put("loanInfoSection",loanInfoSection);
      requestBody.put("externalNoSection",externalNoSection);


      requestBody.put("attachedContentSection",attachedContentSectionMap);

      String result = httpUtils(PreloanBaseApiPost.Contract_Applay,requestBody);

      System.out.println("-------------------------");
      System.out.println(result);
      System.out.println("-------------------------");
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  @Test
  public void makeWater() throws  Exception{

    for (int i=50;i<51;i++){
      // 基础信息段
      String name="杨耀平";
      int year= ThreadLocalRandom.current().nextInt(0,10);
      int year1=year;
      int month=ThreadLocalRandom.current().nextInt(1,10);
      int month1=month;
      String certificateNo="421024199"+year+"0"+month+"082012";
      String birthDate="199"+year1+"-0"+month1+"-08";

      int mob=ThreadLocalRandom.current().nextInt(100,999);
      String mobile="15671625"+mob;

      int gende=ThreadLocalRandom.current().nextInt(1,3);
      String gender=""+gende;          //1或2

      String [] citys={"杭州","义乌","金华","衢州"};
      int j =(int)(Math.random()*citys.length);
      String permanentAddress=citys[j];

      int amount=ThreadLocalRandom.current().nextInt(100,999);
      String applyAmount="10"+amount+".00";
      //教育信息段
      int ploma=ThreadLocalRandom.current().nextInt(0,9);
      String highestDiploma=""+ploma;  //必填 0-8

      int degree=ThreadLocalRandom.current().nextInt(0,5);
      String highestDegree=""+degree;    //必填 0-4

      //职业信息段
      int category=ThreadLocalRandom.current().nextInt(0,8);
      String jobCategory=""+category;     //必填 0-7

      String [] jobs={"0","1","2","3","99"};
      int x =(int)(Math.random()*jobs.length);
      String job=jobs[x];              //必填   0-3 99

      String [] titles={"0","1","2","3","99"};
      int y =(int)(Math.random()*titles.length);
      String jobTitle=titles[y];         //必填  0-3  99


      String organizationName="杭州随地付网络技术有限公司";

      int industry=ThreadLocalRandom.current().nextInt(0,20);
      String organizationIndustry=""+industry  ;//必填 0-19

      int income=ThreadLocalRandom.current().nextInt(100,999);
      String annualIncome="200"+income+".00";

      //婚姻信息段
      String [] status={"0","2","3","6","7","8","99"};
      int z =(int)(Math.random()*status.length);
      String maritalStatus=status[z];      // 0 2 3 6 7 8 99

      //居住信息段
      String [] area={"萧山","滨江","西湖","上城","下沙","桐庐"};
      int m =(int)(Math.random()*area.length);
      String residenceAddress=area[m];

      String [] condition={"0","1","2","3","4","5","6","99"};
      int w =(int)(Math.random()*condition.length);
      String residenceCondition=condition[w];   //必填 0-6 99

      int tel=ThreadLocalRandom.current().nextInt(100,999);
      String residenceTel="15671556"+tel;



      //
      int sum=ThreadLocalRandom.current().nextInt(10000,99999);
      String principalSum=""+sum;

      int sum1=ThreadLocalRandom.current().nextInt(1000,3000);
      String interestSum=""+sum1;

      int moneth1=ThreadLocalRandom.current().nextInt(1,6);
      int day=ThreadLocalRandom.current().nextInt(10,30);
      String effectiveDate="2018-0"+moneth1+"-"+day;

      int month2=ThreadLocalRandom.current().nextInt(6,10);
      String expirationDate="2018-0"+month2+"-"+day;

      String [] rates={"4.1","4.2","4.3","4.4","4.5","4.6","4.7","4.8","4.9","5.0","5.1"};
      int p =(int)(Math.random()*rates.length);
      String interestRate=rates[p];      //必填  大于4

      String [] cycle={"0","1","3","6","12"};
      int o=(int)(Math.random()*cycle.length);
      String payInterestCycle=cycle[o];     //付息周期-填1、3、6、12、0

      int mod=ThreadLocalRandom.current().nextInt(0,3);
      String repaymentMod=""+mod;        //0-2
      request_no = UUID.randomUUID().toString();

      String merchantCredit_no1=merchantCredit_no+i;
      String merchantContract_no1=merchantContract_no+i;
      System.out.println("merchantCredit_no1:"+merchantCredit_no1);
      System.out.println("merchantContract_no1:"+merchantContract_no1);
      creditApplay1(merchantCredit_no1, name, certificateNo, birthDate,  mobile
          , gender, permanentAddress, applyAmount, highestDiploma, highestDegree
          , jobCategory, job, jobTitle,  organizationName, organizationIndustry
          , annualIncome, maritalStatus, residenceAddress, residenceCondition, residenceTel);
      testSave2(merchantContract_no1,merchantCredit_no1, name, principalSum, interestSum
          , effectiveDate, expirationDate, interestRate, payInterestCycle, repaymentMod);
    }

  }

  private String httpUtils(String url, Map<String,Object> requestBody) throws Exception{
    HttpPost httpPost = new HttpPost(url);

    String envelopOriginal = getRandomAesKey();

    PublicKey publicKeyEnvelop  = KeyUtil.getPublicKey(publicKeyString);
    String envelop = KeyUtil.rsaEncrypt(envelopOriginal, publicKeyEnvelop);

    String request = JSON.toJSONString(requestBody);
    String requestDecrypted = KeyUtil.aesEncrypt(request, envelopOriginal);

    PrivateKey privateKey = KeyUtil.getPrivate(privateKeyString);
    String sign = KeyUtil.sign(requestDecrypted, privateKey);

    httpPost.setHeader(HeaderConstant.ENVELOPE, envelop);
    httpPost.setHeader(HeaderConstant.SIGN, sign);
    httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
    httpPost.setEntity(new ByteArrayEntity(requestDecrypted.getBytes()));
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

//        System.out.println(EntityUtils.toString(closeableHttpResponse.getEntity()));
    String response = 	EntityUtils.toString(closeableHttpResponse.getEntity());
    System.out.println(response);
    JSONObject jsonObject = JSONObject.parseObject(response);
    String data = jsonObject.getString("data");
    if (! StringUtils.isEmpty(data)) {

      String resposeEnvelope = closeableHttpResponse.getFirstHeader(HeaderConstant.ENVELOPE).getValue();

      String resposeSign = closeableHttpResponse.getFirstHeader(HeaderConstant.SIGN).getValue();

      String aseKey = KeyUtil.rsaDecrypt(resposeEnvelope, privateKey);

      Boolean verify = KeyUtil.verify(response, resposeSign, publicKeyEnvelop);

      if(verify) {

        String deData = KeyUtil.aesDecrypt(data,aseKey);
        jsonObject.put("data", deData);
      }

    }
    return jsonObject.toJSONString();
  }

  /**
   * 将byte[] 转换成字符串
   */
  private static String byte2Hex(byte[] srcBytes) {
    StringBuilder hexRetSB = new StringBuilder();
    for (byte b : srcBytes) {
      String hexString = Integer.toHexString(0x00ff & b);
      hexRetSB.append(hexString.length() == 1 ? 0 : "").append(hexString);
    }
    return hexRetSB.toString();
  }
  /**
   * 随机生成秘钥 64bit 密钥长度为16位
   */
  private static String getRandomAesKey() {
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());
    byte[] bys = new byte[8];
    random.nextBytes(bys);
    String s = byte2Hex(bys);
    System.out.println("秘钥 ： " + s);
    return s;
  }

}
