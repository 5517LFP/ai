package com.example.ai.hunanturst;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//import com.umpay.api.log.SysOutLogger;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import com.demo2do.core.entity.Result;
import com.demo2do.core.utils.JsonUtils;
import com.zufangbao.sun.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.util.StreamUtils;

import javax.xml.soap.SOAPPart;

//@RunWith(SpringRunner.class)
@Slf4j
public class PreLoanTest {

  String requestNO = UUID.randomUUID().toString();


  private static final String privateKeyString = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMyYDfS68R/TqZT/CcKtU+2a+/sA+cG0JKs9R/nTC9gNbZtyzAwQrdEBQAKXzl3co9CwzZdiIgRX6ALKag/lYoKezY7UifF07vFyTM2gfpuP1ORh5Dt854n2do5+ovBYaLq+zq9NAlRktvcWAI+uVsgzEKADr4aRLiRapfZ71gU7AgMBAAECgYAWsHY7wc+j2/FemLoYYOiB3UI8n+sS1EuMwgsNZZ5Wo4aYSq7eV6svFphmsTctqZ5xMmpac4OaP7V3OcNxZ9r4tqhMANq00mNwNMCzcsPZ/wXE2+jGLy54OatuT908imRqLE9zLR5OxRtVc3nm5MHTidn8cr666pMzzKSDUkyzoQJBAOcp7Jfw+zXJh+PqgmgV0WGJDMs7Rk9juI7Qo0bFf6Lih7jXSL6iVAYqiJYX3Ts9ZjZVP117Id+lHcHIPjev7FcCQQDik1ZlgGNCGH/zgx1O+gWtP3KmqgLYiMUkdHduH8kzurthfR/zD2pXJenSWnQ5BsZQOhlVFH5KL+YOf+Mjmh+9AkEAyPMV/CN9jY1qtwNmZ6sHwD0ORSF7BoqOpn/SYDRRtzwrddCYKTgdyNpyr9+A7v15/CNxGQdwM+Vqj8lN5MTmswJAHl5BJjmfFCzUeX8JXpyERkRKyavf1cX/JnO1zjzUauqMUvTCY4GdbzDVtiwJh9swmXAwFQc6JhdlbmwVtZ/iwQJBAL9Vihy19dm+T9OXiutV1gS1abIsBwjeyZbx+jBrm+PJQCLkzZlFXELFg7z7qR3Iwrgh/CvWd7mo2Zv5SKzoncs=";
  private static final String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMmA30uvEf06mU/wnCrVPtmvv7APnBtCSrPUf50wvYDW2bcswMEK3RAUACl85d3KPQsM2XYiIEV+gCymoP5WKCns2O1InxdO7xckzNoH6bj9TkYeQ7fOeJ9naOfqLwWGi6vs6vTQJUZLb3FgCPrlbIMxCgA6+GkS4kWqX2e9YFOwIDAQAB";

  private int n= ThreadLocalRandom.current().nextInt(0,10000);
  /*private String mer_code = "paipai";
  private String produt_code = "G00000";*/

  private String merchantCredit_no ="d9sxb35e-c4b6-4a3f-b4f6-c7ebae8a13135" ; //商户授信编号    c9sxb35e-c4b6-4a3f-b4f6-c7ebae8a0004
  private String merchantContract_no ="3fe9db97-27fb-4e53-9975-2684303bv35";//商户借款合同编号   3fe9db97-27fb-4e53-9975-2684306ca004
  private String request_no = UUID.randomUUID().toString();
  private String vedaContract_no = "";   //24f8e93e-7649-4424-8e32-32fad78d8d57
  private String merchantLoanOrder_no ="DISB201804130000758"+n;   //97004



  // 基础信息段
  private String name="top";
  int year=ThreadLocalRandom.current().nextInt(0,10);
  int year1=year;
  int month=ThreadLocalRandom.current().nextInt(1,10);
  int month1=month;
  private String certificateNo="421024199"+year+"0"+month+"082012";
  private String birthDate="199"+year1+"-0"+month1+"-08";

  int mob=ThreadLocalRandom.current().nextInt(100,999);
  private String mobile="15671625"+mob;

  int gende=ThreadLocalRandom.current().nextInt(1,3);
  private String gender=""+gende;          //1或2

  String [] citys={"杭州","义乌","金华","衢州"};
  int i =(int)(Math.random()*citys.length);
  private String permanentAddress=citys[i];

  int amount=ThreadLocalRandom.current().nextInt(100,999);
  private String applyAmount="10"+amount+".00";
  //教育信息段
  int ploma=ThreadLocalRandom.current().nextInt(0,9);
  private String highestDiploma=""+ploma;  //必填 0-8

  int degree=ThreadLocalRandom.current().nextInt(0,5);
  private String highestDegree=""+degree;    //必填 0-4

  //职业信息段
  int category=ThreadLocalRandom.current().nextInt(0,8);
  private String jobCategory=""+category;     //必填 0-7

  String [] jobs={"0","1","2","3","99"};
  int x =(int)(Math.random()*jobs.length);
  private String job=jobs[x];              //必填   0-3 99

  String [] titles={"0","1","2","3","99"};
  int y =(int)(Math.random()*titles.length);
  private String jobTitle=titles[y];         //必填  0-3  99


  private String organizationName="杭州随地付网络技术有限公司";

  int industry=ThreadLocalRandom.current().nextInt(0,20);
  private String organizationIndustry=""+industry  ;//必填 0-19

  int income=ThreadLocalRandom.current().nextInt(100,999);
  private String annualIncome="200"+income+".00";

  //婚姻信息段
  String [] status={"0","2","3","6","7","8","99"};
  int z =(int)(Math.random()*status.length);
  private String maritalStatus=status[z];      // 0 2 3 6 7 8 99

  //居住信息段
  String [] area={"萧山","滨江","西湖","上城","下沙","桐庐"};
  int m =(int)(Math.random()*area.length);
  private String residenceAddress=area[m];

  String [] condition={"0","1","2","3","4","5","6","99"};
  int w =(int)(Math.random()*condition.length);
  private String residenceCondition=condition[w];   //必填 0-6 99

  int tel=ThreadLocalRandom.current().nextInt(100,999);
  private String residenceTel="15671556"+tel;



  //
  int sum=ThreadLocalRandom.current().nextInt(10000,99999);
  private String principalSum=""+sum;

  int sum1=ThreadLocalRandom.current().nextInt(1000,3000);
  private String interestSum=""+sum1;

  int moneth1=ThreadLocalRandom.current().nextInt(1,6);
  int day=ThreadLocalRandom.current().nextInt(10,30);
  private String effectiveDate="2018-0"+month1+"-"+day;

  int month2=ThreadLocalRandom.current().nextInt(6,10);
  private String expirationDate="2018-0"+month2+"-"+day;

  String [] rates={"4.1","4.2","4.3","4.4","4.5","4.6","4.7","4.8","4.9","5.0","5.1"};
  int p =(int)(Math.random()*rates.length);
  private String interestRate=rates[p];      //必填  大于4

  String [] cycle={"0","3","6","12","1"};
  int o=(int)(Math.random()*cycle.length);
  private String payInterestCycle=cycle[o];     //付息周期-填1、3、6、12、0

  int mod=ThreadLocalRandom.current().nextInt(0,3);
  private String repaymentMod=""+mod;        //0-2

  //授信申请接口
  //签名:EAwLsuMaazS+5cMaknBnpUteyaLniTXbUK21GMKsdNh6pegaN/oscdduxRzl2baavl13Rv5pjCtUXX4jKVg3BKXbwHw5u+ktpNSiXY2Okk90G7VjiR73OqIdqeE8muPKRUJqyQPByHDXI2f6eCLG7aM0sdNzvNNX2dovycJIk7Q=

  public void creditApplay(String merchantCredit_no, String applyAmount) {

    try {

      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo",request_no = UUID.randomUUID().toString());//请求编号
      requestBody.put("productCode",PreloanBaseApiPost.produt_code);//产品代码
      requestBody.put("merchantCreditNo", merchantCredit_no);//商户授信编号
      requestBody.put("roleType", "0"); //角色分类 0:自然人 1:法人
      requestBody.put("callbackUrl", "http://www.baidu.com");
// 基础信息段
      Map<String, Object> baseInfoSectionMap = new HashMap<>();
      /*baseInfoSectionMap.put("name",name);                      //必填
      baseInfoSectionMap.put("certificateType", "0");                    //固定0身份证 必填
      baseInfoSectionMap.put("certificateNo",certificateNo); //必填
      baseInfoSectionMap.put("certificateIndate", "2028-01-04");
      baseInfoSectionMap.put("mobile", mobile);      //必填
      baseInfoSectionMap.put("email", "15671625566@164.com");
      baseInfoSectionMap.put("gender", gender);       //必填   1或2
      baseInfoSectionMap.put("birthDate", birthDate);      //必填  和身份证匹配
      baseInfoSectionMap.put("permanentAddress", permanentAddress);      //必填
      baseInfoSectionMap.put("applyAmount", applyAmount);     //必填*/

      baseInfoSectionMap.put("name","于怡悦");                      //必填
      baseInfoSectionMap.put("certificateType", "0");                    //固定0身份证 必填
      baseInfoSectionMap.put("certificateNo","411423198303141397"); //必填
      baseInfoSectionMap.put("certificateIndate", "");
      baseInfoSectionMap.put("mobile", "");      //必填
      baseInfoSectionMap.put("email", "");
      baseInfoSectionMap.put("gender", "");       //必填   1或2
      baseInfoSectionMap.put("birthDate", "");      //必填  和身份证匹配
      baseInfoSectionMap.put("permanentAddress", "");      //必填
      baseInfoSectionMap.put("applyAmount", applyAmount);     // 申请额度
//教育信息段

      Map<String, Object> eduInfoSectionMap = new HashMap<>();
      /*eduInfoSectionMap.put("highestDiploma", highestDiploma);       //必填 0-8
      eduInfoSectionMap.put("highestDegree", highestDegree);        //必填 0-4*/
      eduInfoSectionMap.put("highestDiploma", "");       //必填 0-8
      eduInfoSectionMap.put("highestDegree", "");        //必填 0-4
//职业信息段
      Map<String, Object> jobInfoSectionMap = new HashMap<>();
      /*jobInfoSectionMap.put("jobCategory", jobCategory);    //必填 0-7
      jobInfoSectionMap.put("job", job);  //必填   0-3 99
      jobInfoSectionMap.put("jobTitle", jobTitle);   //必填  0-3  99
      jobInfoSectionMap.put("organizationName", organizationName);   //必填
      jobInfoSectionMap.put("organizationTel","15671556601");
      jobInfoSectionMap.put("organizationIndustry", organizationIndustry);      //必填 0-19
      jobInfoSectionMap.put("annualIncome", annualIncome);     //必填
      jobInfoSectionMap.put("organizationStartYear", "1994");
      jobInfoSectionMap.put("organizationPostalcode", "434158");
      jobInfoSectionMap.put("organizationAddress", "萧山区");
      jobInfoSectionMap.put("organizationProvince", "浙江省");
      jobInfoSectionMap.put("organizationCity", "杭州市");*/
      jobInfoSectionMap.put("jobCategory", "");    //必填 0-7
      jobInfoSectionMap.put("job", "");  //必填   0-3 99
      jobInfoSectionMap.put("jobTitle", "");   //必填  0-3  99
      jobInfoSectionMap.put("organizationName", "");   //必填
      jobInfoSectionMap.put("organizationTel","");
      jobInfoSectionMap.put("organizationIndustry", "");      //必填 0-19
      jobInfoSectionMap.put("annualIncome", "");     //必填
      jobInfoSectionMap.put("organizationStartYear", "");
      jobInfoSectionMap.put("organizationPostalcode", "");
      jobInfoSectionMap.put("organizationAddress", "");
      jobInfoSectionMap.put("organizationProvince", ""); //中文文本
      jobInfoSectionMap.put("organizationCity", "");
//婚姻信息段
      Map<String, Object> marriageInfoSectionMap = new HashMap<>();


      /*marriageInfoSectionMap.put("maritalStatus", maritalStatus); // 0 2 3 6 7 8 99
      marriageInfoSectionMap.put("spouseName", "mikd");
      marriageInfoSectionMap.put("spouseMobile", "15677445595");
      marriageInfoSectionMap.put("spouseCertificateType", "0");
      marriageInfoSectionMap.put("spouseCertificateNo","421024199801012264");
      marriageInfoSectionMap.put("spouseCertificateIndate", "2022-01-08");
      marriageInfoSectionMap.put("spouseOrganizationName","suidifu");*/
      marriageInfoSectionMap.put("maritalStatus", ""); // 0 2 3 6 7 8 99
      marriageInfoSectionMap.put("spouseName", "");
      marriageInfoSectionMap.put("spouseMobile", "");
      marriageInfoSectionMap.put("spouseCertificateType", "");
      marriageInfoSectionMap.put("spouseCertificateNo","");
      marriageInfoSectionMap.put("spouseCertificateIndate", "");
      marriageInfoSectionMap.put("spouseOrganizationName","");
//居住信息段
      /*Map<String,Object> residenceInfoSectionMap = new HashMap<>();
      residenceInfoSectionMap.put("residenceProvince", "浙江省");
      residenceInfoSectionMap.put("residenceCity", "杭州市");
      residenceInfoSectionMap.put("residenceAddress", residenceAddress);         //必填
      residenceInfoSectionMap.put("residencePostalcode", "434411");
      residenceInfoSectionMap.put("residenceCondition",residenceCondition);       //必填 0-6 99
      residenceInfoSectionMap.put("residenceTel", residenceTel);      //必填*/
      Map<String,Object> residenceInfoSectionMap = new HashMap<>();
      residenceInfoSectionMap.put("residenceProvince", "");
      residenceInfoSectionMap.put("residenceCity", "");
      residenceInfoSectionMap.put("residenceAddress", "");         //必填
      residenceInfoSectionMap.put("residencePostalcode", "");
      residenceInfoSectionMap.put("residenceCondition","");       //必填 0-6 99
      residenceInfoSectionMap.put("residenceTel", "");      //必填
//银行卡信息段
      Map<String, Object> bankcardInfoSectionMap = new HashMap<>();
      /*bankcardInfoSectionMap.put("bankcardNo","46454554135");
      bankcardInfoSectionMap.put("bankName", "建设银行");
      bankcardInfoSectionMap.put("bankBranchName", "杭州分行");
      bankcardInfoSectionMap.put("bankBranchProvince","浙江");
      bankcardInfoSectionMap.put("bankBranchCity", "杭州");
      bankcardInfoSectionMap.put("bankcardBindingMobile", "15671556669");*/
      bankcardInfoSectionMap.put("bankcardNo","");
      bankcardInfoSectionMap.put("bankName", "");
      bankcardInfoSectionMap.put("bankBranchName", "");
      bankcardInfoSectionMap.put("bankBranchProvince","610800");
      bankcardInfoSectionMap.put("bankBranchCity", "330000");
      bankcardInfoSectionMap.put("bankcardBindingMobile", "13839610907");
//联系人信息段
      List<Map<String, Object>> contactInfoSection = new ArrayList<>();
      Map<String, Object> contactInfoSectionMap1 = new HashMap<>();
      /*contactInfoSectionMap1.put("relationship","1");
      contactInfoSectionMap1.put("name", "cind2");
      contactInfoSectionMap1.put("mobile", "15671554480");
      contactInfoSectionMap1.put("certificateType", "0");
      contactInfoSectionMap1.put("certificateNo", "421024199511225570");
      contactInfoSectionMap1.put("certificateIndate", "2022-11-21");
      contactInfoSectionMap1.put("organizationName", "suidifu");
      contactInfoSection.add(contactInfoSectionMap1);*/
      contactInfoSectionMap1.put("relationship","");
      contactInfoSectionMap1.put("name", "");
      contactInfoSectionMap1.put("mobile", "");
      contactInfoSectionMap1.put("certificateType", "");
      contactInfoSectionMap1.put("certificateNo", "");
      contactInfoSectionMap1.put("certificateIndate", "");
      contactInfoSectionMap1.put("organizationName", "");
      contactInfoSection.add(contactInfoSectionMap1);

      Map<String, Object> contactInfoSectionMap2 = new HashMap<>();
      /*contactInfoSectionMap2.put("relationship","2");
      contactInfoSectionMap2.put("name", "windc");
      contactInfoSectionMap2.put("mobile", "13671554479");
      contactInfoSectionMap2.put("certificateType", "0");
      contactInfoSectionMap2.put("certificateNo", "422024199511225570");
      contactInfoSectionMap2.put("certificateIndate", "2022-11-22");
      contactInfoSectionMap2.put("organizationName", "suidifu");
      contactInfoSection.add(contactInfoSectionMap2);*/

      contactInfoSectionMap2.put("relationship","");
      contactInfoSectionMap2.put("name", "");
      contactInfoSectionMap2.put("mobile", "");
      contactInfoSectionMap2.put("certificateType", "");
      contactInfoSectionMap2.put("certificateNo", "");
      contactInfoSectionMap2.put("certificateIndate", "");
      contactInfoSectionMap2.put("organizationName", "");
      contactInfoSection.add(contactInfoSectionMap2);
      //保单信息段
      Map<String, Object> policyInfoSectionMap = new HashMap<>();
      /*policyInfoSectionMap.put("policyCorporationName", "中国人寿");
      policyInfoSectionMap.put("policyStatus", "0");
      policyInfoSectionMap.put("policyPaymentWay", "0");
      policyInfoSectionMap.put("policyValidDate", "2018-01-03");
//      policyInfoSectionMap.put("policyPaymentExpire", "3");                    //暂时不填,填了报错
      policyInfoSectionMap.put("policyPaymentStatus", "0");
      policyInfoSectionMap.put("policyAnnualPaymentAmount", "5160.12");*/

      policyInfoSectionMap.put("policyCorporationName", "");
      policyInfoSectionMap.put("policyStatus", "0");
      policyInfoSectionMap.put("policyPaymentWay", "0");
      policyInfoSectionMap.put("policyValidDate", "");
//      policyInfoSectionMap.put("policyPaymentExpire", "3");                    //暂时不填,填了报错
      policyInfoSectionMap.put("policyPaymentStatus", "0");
      policyInfoSectionMap.put("policyAnnualPaymentAmount", "");

      //公基金信息段
      Map<String, Object> providentFundInfoSectionMap = new HashMap<>();
      /*providentFundInfoSectionMap.put("providentFundIdentityConsistent", "0");
      providentFundInfoSectionMap.put("providentFundAccountStatus", "0");
      providentFundInfoSectionMap.put("providentFundPaymentStatus", "0");
      providentFundInfoSectionMap.put("providentFundPaymentRecord", "1");
      providentFundInfoSectionMap.put("providentFundMonthBaseNum", "1600.12");*/

      providentFundInfoSectionMap.put("providentFundIdentityConsistent", "");
      providentFundInfoSectionMap.put("providentFundAccountStatus", "");
      providentFundInfoSectionMap.put("providentFundPaymentStatus", "");
      providentFundInfoSectionMap.put("providentFundPaymentRecord", "");
      providentFundInfoSectionMap.put("providentFundMonthBaseNum", "");

//附加内容
      Map<String ,Object> attachedContentSectionMap=new HashMap<>();
//      attachedContentSectionMap.put("1","1");
//      attachedContentSectionMap.put("2","1");
//      attachedContentSectionMap.put("3","1000");
      attachedContentSectionMap.put("fu","234324");




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


      requestBody.put("attachedContentSection",JsonUtils.toJsonString(attachedContentSectionMap));
//      requestBody.put("attachedContentSection","123123213");

      requestBody.put("externalCustomerNoSection",JsonUtils.toJsonString(externalCustomerNoSection));
      String result = httpUtils(PreloanBaseApiPost.Credit_Applay,requestBody);
      System.out.println(result);
    }catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  //授信查询接口
  public String creditApplaySelect(String merchantCredit_no){
    String vedaCreditNo = "";

    try {

      Map<String,Object> requestBody = new HashMap<>();
      requestBody.put("requestNo",request_no);//请求编号
      requestBody.put("productCode",PreloanBaseApiPost.produt_code);//产品代码
      requestBody.put("merchantCreditNo",merchantCredit_no);//商户授信编号

      String result = httpUtils(PreloanBaseApiPost.Credit_Select, requestBody);
//      System.out.println("响应结果:" + result);
      Map maps = JSON.parseObject(result);
      Map data = JSON.parseObject((String) maps.get("data"));
      System.out.println(data);
      System.out.println(data.get("vedaCreditNo"));
      vedaCreditNo  = (String) data.get("vedaCreditNo");




    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }

    return vedaCreditNo;
  }




  //用信申请接口
  public void testSave(String merchantCredit_no, String merchantContract_no, String vedaCreditNo, String principalSum){
    try {

      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo", request_no);
      requestBody.put("productCode",PreloanBaseApiPost.produt_code );
      requestBody.put("merchantCreditNo",merchantCredit_no );
      requestBody.put("merchantContractNo", merchantContract_no);
      requestBody.put("vedaCreditNo", vedaCreditNo);  //SX183341644752023552
      requestBody.put("txType", "0");      //必填     0
      requestBody.put("principalSum", principalSum);  //1200.00 //必填
      requestBody.put("interestSum", "10.2");   //10.12    //必填
      requestBody.put("costSum", "1210.12");      //1210.12
      requestBody.put("signDate", "2018-01-01");   //2018-01-01
      requestBody.put("effectiveDate", "2018-03-01");  //2018-01-01    //必填
      requestBody.put("expirationDate","2018-05-01");  //    必填
      requestBody.put("valueDate", "2018-07-01");    //2018-11-01
      requestBody.put("dueDate", "11");      //2018-0-01

      requestBody.put("interestRate", "4.2");     //0.1      //必填  大于4
      requestBody.put("interestRateCycle", "0");       //0
      requestBody.put("payInterestCycle", "60");        // 付息周期-填1、3、6、12、0
      requestBody.put("repaymentMode", "0");            //0-2     不要填99
      requestBody.put("repaymentPeriodsCount", "3");   //3
      requestBody.put("intendedUse", "3");             //1
      requestBody.put("cooperationPlatformProductName", "xxx");  //100
      requestBody.put("cooperationPlatformGrade", "");       //
      requestBody.put("callbackUrl", "http://www.baidu.com");     //http://www.baidu.com

      List<Map<String, Object>> loanInfoSection = new ArrayList<>();
      Map<String, Object> loanInfoSectionMap = new HashMap<>();

      loanInfoSectionMap.put("payeeType","3");                   //0
      loanInfoSectionMap.put("payeeBankAccountNo","64568498");      //123456
      loanInfoSectionMap.put("payeeBankAccountName","");      //tom
      loanInfoSectionMap.put("payeeBankCode","");            //cb1221
      loanInfoSectionMap.put("payeeBankName","");         //杭州银行
      loanInfoSectionMap.put("payeeBankCnapsCode","");      //123456
      loanInfoSectionMap.put("payeeBankProvince","112111");          //浙江
      loanInfoSectionMap.put("payeeBankCity","135665");              //杭州
      loanInfoSectionMap.put("loanAmount",principalSum);              //1000.12
      loanInfoSection.add(loanInfoSectionMap);



      List<Map<String, Object>> externalNoSection = new ArrayList<>();
      Map<String, Object> externalNoSectionMap = new HashMap<>();
      externalNoSectionMap.put("supervisionReportBusinessNo", "");  //123
      Map<String, Object> externalNoSectionMap2 = new HashMap<>();
      externalNoSectionMap2.put("supervisionReportBusinessNo", "");
      externalNoSection.add(externalNoSectionMap);


      Map<String,Object>attachedContentSectionMap= new HashMap<>();
      attachedContentSectionMap.put("agricultureRelated","1");
      attachedContentSectionMap.put("ruralFinancialCustomer","2");
      attachedContentSectionMap.put("familyMonthlyIncome","1000");
      attachedContentSectionMap.put("flfcType","");
      attachedContentSectionMap.put("housePurchaseSituation","005");
      attachedContentSectionMap.put("economicNature","05");
      attachedContentSectionMap.put("industryType","A01");
      attachedContentSectionMap.put("mainGuaranteeMode","005");
      attachedContentSectionMap.put("useDescription","ssss");
      attachedContentSectionMap.put("industryTypeNew","A011");

      requestBody.put("loanInfoSection",loanInfoSection);
      requestBody.put("externalNoSection",JsonUtils.toJsonString(externalNoSection));
      requestBody.put("attachedContentSection",JsonUtils.toJsonString(attachedContentSectionMap));

      String result = httpUtils(PreloanBaseApiPost.Contract_Applay,requestBody);

      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }




  //用信查询接口
  public String testSelect(String merchantContract_no){
    String vedaContractNo="";
    try {

      Map<String,Object> requestBody = new HashMap<>();
      requestBody.put("requestNo",request_no);
      requestBody.put("productCode",PreloanBaseApiPost.produt_code);
      requestBody.put("merchantContractNo",merchantContract_no);

      String result = httpUtils(PreloanBaseApiPost.Contract_Select, requestBody);

      JSONObject maps = JSON.parseObject(result);
      System.out.println( maps.getString("data"));
      String str = JSONObject.parseObject(maps.getString("data")).toJSONString();
      System.out.println("str========="+ str);

      vedaContractNo  = (String) JSON.parseObject(str).get("vedaContractNo");
      System.out.println( "vedaContractNo:"+vedaContractNo);
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
    return vedaContractNo;

  }




  // 放款申请接口
  public void loanApplay(String merchantLoanOrder_no, String merchantContract_no,String vedaContractNo, String loanAmount){
    try{

      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo",UUID.randomUUID().toString()); //请求编号          //2d839adb-15ee-4788-8e89-db771997fab4
      requestBody.put("productCode", PreloanBaseApiPost.produt_code);//产品代码
      requestBody.put("merchantLoanOrderNo", merchantLoanOrder_no);//商户放款订单号
      requestBody.put("merchantContractNo",merchantContract_no);//商户借款合同编号
      requestBody.put("vedaContractNo",vedaContractNo );//五维借款合同编号
      requestBody.put("loanAmount", loanAmount);
      requestBody.put("loanStrategy", "0");
      requestBody.put("remark", "花花");
      requestBody.put("auditor", "");
      requestBody.put("auditTime", "2018-01-01 12:01:54");
      requestBody.put("callbackUrl","");


      List<Map<String, Object>> loanDetail = new ArrayList<>();
      Map<String, Object> loanDetailMap = new HashMap<>();
      loanDetailMap.put("detailRecordNo", UUID.randomUUID().toString());
      loanDetailMap.put("payeeType", "0");
      loanDetailMap.put("payeeBankAccountNo", "6216600100005555337");
      loanDetailMap.put("payeeBankAccountName", "杭州"); //必填
      loanDetailMap.put("payeeBankCode", "C10920");
      loanDetailMap.put("detailRecordNo", "00001");//必填
      loanDetailMap.put("payeeBankName", "");
      loanDetailMap.put("payeeBankCnapsCode", "");
      loanDetailMap.put("payeeCertificateNo", "");
      loanDetailMap.put("payeeBankProvince", "");
      loanDetailMap.put("payeeBankCity", "111111");
      loanDetailMap.put("loanAmount", loanAmount);
      loanDetailMap.put("planExeDate", "2017-01-01 12:01:54");
      loanDetail.add(loanDetailMap);
      requestBody.put("loanDetail", loanDetail);

      String result = httpUtils(PreloanBaseApiPost.Loan_Applay, requestBody);
//      System.out.println(JSONObject.toJSON(requestBody));
      System.out.println(result);

//      System.out.println("merchantLoanOrder_no:"+merchantLoanOrder_no);     //DISB2018041300007583317

    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();

    }
  }




  //放款查询接口
  public void loanApplaySelect(String merchantContract_no, String merchantLoanOrderNo, String vedaContractNo){              //remittance
    try{

      Map<String, Object> requestBody = new HashMap<>();

      requestBody.put("requestNo",request_no);
      requestBody.put("productCode", PreloanBaseApiPost.produt_code);
      requestBody.put("merchantContractNo",merchantContract_no );//商户借款合同编号
      requestBody.put("merchantLoanOrderNo",merchantLoanOrderNo);//商户放款订单号

      requestBody.put("vedaContractNo", vedaContractNo);
      //requestBody.put("remittanceApplicationUuid","751ba5c1-3461-45a8-b665-b4c27ca259ee");

      String result = httpUtils(PreloanBaseApiPost.Loan_Applay_Select, requestBody);
//      System.out.println(result);
      Map maps = JSON.parseObject(result);
      Map data = JSON.parseObject((String) maps.get("data"));
      System.out.println("响应数据:"+ data);
      System.out.println(data.get("applicationDetailVOS"));

    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();

    }
  }

/*  private String catchVedaContractNo() throws Exception{
    String url = "http://cbhb.5veda.net/preloan/api/remittance/v1/remittance-application/query/"+ mer_code+ "/"+produt_code;
//	          String url = "http://127.0.0.1:8740/preloan/api/remittance/v1/remittance-application/query/paipai/1";
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("requestNo",request_no);
    requestBody.put("productCode", PreloanBaseApiPost.produt_code);
    requestBody.put("merchantContractNo",merchantContract_no );
    requestBody.put("merchantLoanOrderNo",merchantLoanOrder_no );
    //requestBody.put("vedaContractNo", vedaContract_no);
    //requestBody.put("remittanceApplicationUuid","751ba5c1-3461-45a8-b665-b4c27ca259ee");
    String result = httpUtils(url, requestBody);
    Map maps = JSON.parseObject(result);
    Map data = JSON.parseObject((String) maps.get("data"));
    System.out.println(data.get("vedaContractNo"));
    return data.get("vedaContractNo").toString();
  }*/

  //还款计划推送接口
  public void repaymentPlanPush(String merchantContract_no, String costDetailAmount, String merchantRepaymentPlanNo, String repaymentDate){          //asset
    try{

      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo",request_no);
      requestBody.put("productCode", PreloanBaseApiPost.produt_code);
      requestBody.put("merchantContractNo", merchantContract_no);//商户借款合同编号
     	//requestBody.put("vedaContractNo", "24f8e93e-7649-4424-8e32-32fad78d8d57");  //五维借款合同编号
      /*requestBody.put("callbackUrl", "http://www.baidu.com");*/
      requestBody.put("callbackUrl", "");

      //costdetail
      List<Map<String,Object>> costDetail = new ArrayList<>();
      Map<String,Object> costDetailMap = new HashMap<>();
      costDetailMap.put("costDetailType", "0");
      costDetailMap.put("costDetailAmount", costDetailAmount);//明细金额
      costDetail.add(costDetailMap);

      Map<String,Object> costDetailMap1 = new HashMap<>();
      costDetailMap1.put("costDetailType", "1");
      costDetailMap1.put("costDetailAmount", "0");
      costDetail.add(costDetailMap1);

      //asset
      List<Map<String,Object >> assetSet = new ArrayList<>();
      Map<String, Object> assetSetMap = new HashMap<>();
      assetSetMap.put("merchantRepaymentPlanNo", merchantRepaymentPlanNo);//商户还款计划编号
      assetSetMap.put("repaymentDate", repaymentDate);//计划还款日期
      assetSetMap.put("costDetail", costDetail);//费用集
      assetSet.add(assetSetMap);



      requestBody.put("assetSet", assetSet);
      String result = httpUtils(PreloanBaseApiPost.Repayment_Plan_Push, requestBody);

      System.out.println(result);

    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();
    }
  }

  //还款计划查询接口
  public void repaymentPlanSelect(String merchantContract_no){
    try{

      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo", request_no);
      requestBody.put("productCode", PreloanBaseApiPost.produt_code);
      requestBody.put("merchantContractNo", merchantContract_no);

      String result = httpUtils(PreloanBaseApiPost.Repayment_Plan_Select, requestBody);
      System.out.println("------------------");
      System.out.println(result);
      System.out.println("-------------------");


    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail();

    }

  }


  //附件上传接口
  public void fileUpload(String merchantContract_no, String merchantCredit_no) throws  Exception{

    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    builder.addTextBody("requestNo",request_no);
    builder.addTextBody("productCode", "G00000");
    builder.addTextBody("merchantCreditNo", merchantCredit_no);
    builder.addTextBody("vedaCreditNo", "");
    builder.addTextBody("merchantContractNo", merchantContract_no);
    builder.addTextBody("vedaContractNo", "");
    builder.addTextBody("fileType", "0");
    builder.addTextBody("fileName", "");
    builder.addTextBody("fileRemoteAddress", "");
    builder.addBinaryBody("file", new File("/Users/fugengzhen/workspace/earth/src/test/java/com/zufangbao/earth/cucumber/fgz/preloan/KeyUtil.java"));
    HttpEntity httpEntity = builder.build();

      ByteArrayOutputStream out = new ByteArrayOutputStream((int)httpEntity.getContentLength());

      httpEntity.writeTo(out);

      String requestBody = out.toString();

//      String requestBody = StreamUtils.copyToString(httpEntity.getContent(), Charset.forName("utf-8"));

    String  aesKey = KeyUtil.getAesKey();
    PublicKey publicKey = KeyUtil.getPublicKey(publicKeyString);
    String envelope = KeyUtil.rsaEncrypt(aesKey, publicKey);
    System.out.println("the envlope is " + envelope);

    String responseEncrypt = KeyUtil.aesEncrypt(requestBody, aesKey);
    System.out.println("the request body is " + responseEncrypt);

    PrivateKey privateKey = KeyUtil.getPrivate(privateKeyString);
    String sign = KeyUtil.sign(responseEncrypt, privateKey);
    System.out.println("the sign is " + sign);





    httpUtils(PreloanBaseApiPost.File_upload, sign, responseEncrypt, envelope);

    /*try {
      Map<String,Object> requestBody=new HashMap();
      requestBody.put("requestNo",request_no);
      requestBody.put("productCode","G00000");
      requestBody.put("merchantCreditNo",merchantCredit_no);
      requestBody.put("vedaCreditNo","");
      requestBody.put("merchantContractNo",merchantContract_no);
      requestBody.put("vedaContractNo","");
      requestBody.put("fileType","0");
      requestBody.put("fileName","fff");
      requestBody.put("fileRemoteAddress","");
      requestBody.put("file",new File("/home/zsh2014/zk/earth/src/test/java/com/zufangbao/preloan/MakeWater.java"));
      String result = httpUtils(PreloanBaseApiPost.File_upload, requestBody);
      System.out.println("------------------");
      System.out.println(result);
      System.out.println("-------------------");
    }catch (Exception e){
      e.printStackTrace();
      Assert.fail();
    }*/


  }
//附件查询接口
  @Test
  public void fileSelect(String merchantContract_no, String merchantCredit_no)throws Exception{
    String productCode="G00000";
    String vedaContractNo="HT202104940343349248";
    //String vedaCreditNo="SX190832289539051520";
    try {
      Map<String,Object> requestBody=new HashMap();
      requestBody.put("requestNo",request_no);
      requestBody.put("productCode",PreloanBaseApiPost.produt_code);
      requestBody.put("merchantCreditNo",merchantCredit_no);
     // requestBody.put("vedaCreditNo","");
      requestBody.put("merchantContractNo",merchantContract_no);
      requestBody.put("vedaContractNo",vedaContractNo);
      requestBody.put("fileType","999");
      System.out.println("111111");
      String result = httpUtils(PreloanBaseApiPost.File_Select, requestBody);
      System.out.println("------------------");
      System.out.println(result);
      System.out.println("-------------------");
    }catch (Exception e){
      e.printStackTrace();
      Assert.fail();
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
    httpPost.setHeader(HeaderConstant.SIGN, sign);//multipart/form-data
    httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
    httpPost.setEntity(new ByteArrayEntity(requestDecrypted.getBytes()));
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

//        System.out.println(EntityUtils.toString(closeableHttpResponse.getEntity()));
    String response = 	EntityUtils.toString(closeableHttpResponse.getEntity());
    System.out.println(response);
    JSONObject jsonObject = JSONObject.parseObject(response);
    String data = jsonObject.getString("data");
    System.out.println("data:"+data);
    if (! StringUtils.isEmpty(data)) {

      String resposeEnvelope = closeableHttpResponse.getFirstHeader(HeaderConstant.ENVELOPE).getValue();

      String resposeSign = closeableHttpResponse.getFirstHeader(HeaderConstant.SIGN).getValue();

      String aseKey = KeyUtil.rsaDecrypt(resposeEnvelope, privateKey); //rsaDecrpt

      Boolean verify = KeyUtil.verify(response, resposeSign, publicKeyEnvelop);

      if(verify) {

        String deData = KeyUtil.aesDecrypt(data,aseKey);    //aesDescrypt
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
      baseInfoSectionMap.put("certificateNo",certificateNo); //必填
      baseInfoSectionMap.put("certificateIndate", "2028-01-04");
      baseInfoSectionMap.put("mobile", mobile);      //必填
      baseInfoSectionMap.put("email", "15671625566@164.com");
      baseInfoSectionMap.put("gender", gender);       //必填   1或2
      baseInfoSectionMap.put("birthDate", birthDate);      //必填  和身份证匹配
      baseInfoSectionMap.put("permanentAddress", permanentAddress);      //必填
      baseInfoSectionMap.put("applyAmount", applyAmount);     //必填
//教育信息段

      Map<String, Object> eduInfoSectionMap = new HashMap<>();
      eduInfoSectionMap.put("highestDiploma", highestDiploma);       //必填 0-8
      eduInfoSectionMap.put("highestDegree", highestDegree);        //必填 0-4
//职业信息段
      Map<String, Object> jobInfoSectionMap = new HashMap<>();
      jobInfoSectionMap.put("jobCategory", jobCategory);    //必填 0-7
      jobInfoSectionMap.put("job", job);  //必填   0-3 99
      jobInfoSectionMap.put("jobTitle", jobTitle);   //必填  0-3  99
      jobInfoSectionMap.put("organizationName", organizationName);   //必填
      jobInfoSectionMap.put("organizationTel","15671556601");
      jobInfoSectionMap.put("organizationIndustry", organizationIndustry);      //必填 0-19
      jobInfoSectionMap.put("annualIncome", annualIncome);     //必填
      jobInfoSectionMap.put("organizationStartYear", "1994");
      jobInfoSectionMap.put("organizationPostalcode", "434158");
      jobInfoSectionMap.put("organizationAddress", "萧山区");
      jobInfoSectionMap.put("organizationProvince", "浙江省");
      jobInfoSectionMap.put("organizationCity", "杭州市");
//婚姻信息段
      Map<String, Object> marriageInfoSectionMap = new HashMap<>();


      marriageInfoSectionMap.put("maritalStatus", maritalStatus); // 0 2 3 6 7 8 99
      marriageInfoSectionMap.put("spouseName", "mikd");
      marriageInfoSectionMap.put("spouseMobile", "15677445595");
      marriageInfoSectionMap.put("spouseCertificateType", "0");
      marriageInfoSectionMap.put("spouseCertificateNo","421024199801012264");
      marriageInfoSectionMap.put("spouseCertificateIndate", "2022-01-08");
      marriageInfoSectionMap.put("spouseOrganizationName","suidifu");
//居住信息段
      Map<String,Object> residenceInfoSectionMap = new HashMap<>();
      residenceInfoSectionMap.put("residenceProvince", "浙江省");
      residenceInfoSectionMap.put("residenceCity", "杭州市");
      residenceInfoSectionMap.put("residenceAddress", residenceAddress);         //必填
      residenceInfoSectionMap.put("residencePostalcode", "434411");
      residenceInfoSectionMap.put("residenceCondition",residenceCondition);       //必填 0-6 99
      residenceInfoSectionMap.put("residenceTel", residenceTel);      //必填
//银行卡信息段
      Map<String, Object> bankcardInfoSectionMap = new HashMap<>();
      bankcardInfoSectionMap.put("bankcardNo","46454554135");
      bankcardInfoSectionMap.put("bankName", "建设银行");
      bankcardInfoSectionMap.put("bankBranchName", "杭州分行");
      bankcardInfoSectionMap.put("bankBranchProvince","浙江");
      bankcardInfoSectionMap.put("bankBranchCity", "杭州");
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

      requestBody.put("baseInfoSection",baseInfoSectionMap);
      requestBody.put("eduInfoSection",eduInfoSectionMap);
      requestBody.put("jobInfoSection",jobInfoSectionMap);
      requestBody.put("marriageInfoSection",marriageInfoSectionMap);
      requestBody.put("residenceInfoSection",residenceInfoSectionMap);
      requestBody.put("bankcardInfoSection", bankcardInfoSectionMap);
//      requestBody.put("contactInfoSection",contactInfoSection);                 //暂时不填,填了报错
      requestBody.put("policyInfoSection",policyInfoSectionMap);
      requestBody.put("providentFundInfoSection",  providentFundInfoSectionMap);
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

      System.out.println("合同开始日期effectiveDate:"+effectiveDate+"合同到期日期expirationDate:"+expirationDate);
      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("requestNo", request_no);
      requestBody.put("productCode",PreloanBaseApiPost.produt_code );
      requestBody.put("merchantCreditNo",merchantCredit_no );
      requestBody.put("merchantContractNo", merchantContract_no);
      requestBody.put("vedaCreditNo", "");  //SX183341644752023552
      requestBody.put("txType", "0");      //必填     0
      requestBody.put("principalSum", principalSum);  //1200.00 //必填
      requestBody.put("interestSum", interestSum);   //10.12    //必填
      requestBody.put("costSum", "105");      //1210.12
      requestBody.put("signDate", "2018-03-04");   //2018-01-01
      requestBody.put("effectiveDate", effectiveDate);  //2018-01-01    //必填
      requestBody.put("expirationDate",expirationDate);  //    必填
      requestBody.put("valueDate", "2018-11-05");    //2018-11-01
      requestBody.put("dueDate", "2018-11-15");      //2018-0-01

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
      requestBody.put("loanInfoSection",loanInfoSection);
      requestBody.put("externalNoSection",externalNoSection);

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

    for (int i=50;i<110;i++){
      // 基础信息段
      String name="top";
      int year=ThreadLocalRandom.current().nextInt(0,10);
      int year1=year;
      int month=ThreadLocalRandom.current().nextInt(1,10);
      int month1=month;
       String certificateNo="421024199"+year+"0"+month+"082012";
       String birthDate="199"+year1+"-0"+month1+"-08T00:00:00.000Z";

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
      creditApplay1(merchantCredit_no1, name, certificateNo, birthDate,  mobile
          , gender, permanentAddress, applyAmount, highestDiploma, highestDegree
          , jobCategory, job, jobTitle,  organizationName, organizationIndustry
          , annualIncome, maritalStatus, residenceAddress, residenceCondition, residenceTel);
      testSave2(merchantContract_no1,merchantCredit_no1, name, principalSum, interestSum
          , effectiveDate, expirationDate, interestRate, payInterestCycle, repaymentMod);
    }

  }

  public static void main(String[] args) {
    for (int i = 0 ; i <= 50 ;i++){
      String [] cycle={"0","1","3","6","12"};
      int o=(int)(Math.random()*cycle.length);
      String payInterestCycle=cycle[o];     //付息周期-填1、3、6、12、0
      System.out.println(payInterestCycle);
    }
  }
  private String httpUtils(String url, String sign, String requestDecrypted, String envelop) throws Exception{
    HttpPost httpPost = new HttpPost(url);

//    String envelopOriginal = getRandomAesKey();
//
    PublicKey publicKeyEnvelop  = KeyUtil.getPublicKey(publicKeyString);
//    String envelop = KeyUtil.rsaEncrypt(envelopOriginal, publicKeyEnvelop);

//        String request = JSON.toJSONString(requestBody);
//        String requestDecrypted = KeyUtil.aesEncrypt(request, envelopOriginal);

    PrivateKey privateKey = KeyUtil.getPrivate(privateKeyString);
//        String sign = KeyUtil.sign(requestDecrypted, privateKey);

    httpPost.setHeader(HeaderConstant.ENVELOPE, envelop);
    httpPost.setHeader(HeaderConstant.SIGN, sign);
    httpPost.setHeader("Content-Type", "data/form");
    httpPost.setEntity(new ByteArrayEntity(requestDecrypted.getBytes()));
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);

//        System.out.println(EntityUtils.toString(closeableHttpResponse.getEntity()));
    String response = 	EntityUtils.toString(closeableHttpResponse.getEntity());
    JSONObject jsonObject = JSONObject.parseObject(response);
    String data = jsonObject.getString("data");
    if (! StringUtils.isEmpty(data)) {

      String resposeEnvelope = closeableHttpResponse.getFirstHeader(HeaderConstant.ENVELOPE).getValue();

      String resposeSign = closeableHttpResponse.getFirstHeader(HeaderConstant.SIGN).getValue();

      String aseKey = KeyUtil.rsaDecrypt(resposeEnvelope, privateKey);

      Boolean verify = KeyUtil.verify(response, resposeSign, publicKeyEnvelop);

      if(verify) {

        String deData = KeyUtil.aesDecrypt(data,aseKey);
        jsonObject.put("data:", deData);
      }

    }
    return jsonObject.toJSONString();
  }
}
