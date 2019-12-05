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
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

/**
 * @author wjh on 18-6-6.
 */
public class CheckCreditApplay {

  private static final String privateKeyString = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMyYDfS68R/TqZT/CcKtU+2a+/sA+cG0JKs9R/nTC9gNbZtyzAwQrdEBQAKXzl3co9CwzZdiIgRX6ALKag/lYoKezY7UifF07vFyTM2gfpuP1ORh5Dt854n2do5+ovBYaLq+zq9NAlRktvcWAI+uVsgzEKADr4aRLiRapfZ71gU7AgMBAAECgYAWsHY7wc+j2/FemLoYYOiB3UI8n+sS1EuMwgsNZZ5Wo4aYSq7eV6svFphmsTctqZ5xMmpac4OaP7V3OcNxZ9r4tqhMANq00mNwNMCzcsPZ/wXE2+jGLy54OatuT908imRqLE9zLR5OxRtVc3nm5MHTidn8cr666pMzzKSDUkyzoQJBAOcp7Jfw+zXJh+PqgmgV0WGJDMs7Rk9juI7Qo0bFf6Lih7jXSL6iVAYqiJYX3Ts9ZjZVP117Id+lHcHIPjev7FcCQQDik1ZlgGNCGH/zgx1O+gWtP3KmqgLYiMUkdHduH8kzurthfR/zD2pXJenSWnQ5BsZQOhlVFH5KL+YOf+Mjmh+9AkEAyPMV/CN9jY1qtwNmZ6sHwD0ORSF7BoqOpn/SYDRRtzwrddCYKTgdyNpyr9+A7v15/CNxGQdwM+Vqj8lN5MTmswJAHl5BJjmfFCzUeX8JXpyERkRKyavf1cX/JnO1zjzUauqMUvTCY4GdbzDVtiwJh9swmXAwFQc6JhdlbmwVtZ/iwQJBAL9Vihy19dm+T9OXiutV1gS1abIsBwjeyZbx+jBrm+PJQCLkzZlFXELFg7z7qR3Iwrgh/CvWd7mo2Zv5SKzoncs=";
  private static final String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMmA30uvEf06mU/wnCrVPtmvv7APnBtCSrPUf50wvYDW2bcswMEK3RAUACl85d3KPQsM2XYiIEV+gCymoP5WKCns2O1InxdO7xckzNoH6bj9TkYeQ7fOeJ9naOfqLwWGi6vs6vTQJUZLb3FgCPrlbIMxCgA6+GkS4kWqX2e9YFOwIDAQAB";

  private String merchantCredit_no = "" ;
  private String request_no = UUID.randomUUID().toString();
  private String roleType="0";    //0或1

  public void creditApplay(String name,String certificateType,String certificateNo,String certificateIndate,String mobile
               ,String email,String gender,String birthDate,String permanentAddress,String applyAmount,String highestDiploma
               ,String highestDegree,String jobCategory,String job,String jobTitle,String organizationName,String organizationTel
               ,String organizationIndustry,String annualIncome,String organizationStartYear,String organizationPostalcode
               ,String organizationAddress,String organizationProvince,String organizationCity,String maritalStatus,String spouseName
               ,String spouseMobile,String spouseCertificateType,String spouseCertificateNo,String spouseCertificateIndate
               ,String spouseOrganizationName,String residenceProvince,String residenceCity,String residenceAddress,String residencePostalcode
               ,String residenceCondition,String residenceTel,String bankcardNo,String bankName,String bankBranchName,String bankBranchProvince
               ,String bankBranchCity,String bankcardBindingMobile,String relationship,String relationName,String relationMobile
               ,String relationCertificateType,String relationCertificateNo,String relationCertificateIndate,String relationOrganizationName
               ,String policyCorporationName,String policyStatus,String policyPaymentWay,String policyValidDate,String policyPaymentDeadline
               ,String policyPaymentStatus,String policyAnnualPaymentAmount,String providentFundIdentityConsistent,String providentFundAccountStatus
               ,String providentFundPaymentStatus,String providentFundPaymentRecord,String providentFundMonthBaseNum,String callbackUrl){

    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("requestNo",request_no);
    requestBody.put("productCode",PreloanBaseApiPost.produt_code);
    requestBody.put("merchantCreditNo", merchantCredit_no);
    requestBody.put("roleType", roleType);        //自然人
    requestBody.put("callbackUrl", callbackUrl);
// 基础信息段
    Map<String, Object> baseInfoSectionMap = new HashMap<>();
    baseInfoSectionMap.put("name",name);
    baseInfoSectionMap.put("certificateType", certificateType);
    baseInfoSectionMap.put("certificateNo",certificateNo);
    baseInfoSectionMap.put("certificateIndate", certificateIndate);
    baseInfoSectionMap.put("mobile",mobile);
    baseInfoSectionMap.put("email", email);
    baseInfoSectionMap.put("gender", gender);
    baseInfoSectionMap.put("birthDate",birthDate);
    baseInfoSectionMap.put("permanentAddress", permanentAddress);
    baseInfoSectionMap.put("applyAmount", applyAmount);
//教育信息段

    Map<String, Object> eduInfoSectionMap = new HashMap<>();

    eduInfoSectionMap.put("highestDiploma", highestDiploma);
    eduInfoSectionMap.put("highestDegree", highestDegree);
//职业信息段
    Map<String, Object> jobInfoSectionMap = new HashMap<>();
    jobInfoSectionMap.put("jobCategory", jobCategory);
    jobInfoSectionMap.put("job", job);
    jobInfoSectionMap.put("jobTitle", jobTitle);
    jobInfoSectionMap.put("organizationName", organizationName);
    jobInfoSectionMap.put("organizationTel",organizationTel);
    jobInfoSectionMap.put("organizationIndustry", organizationIndustry);
    jobInfoSectionMap.put("annualIncome", annualIncome);
    jobInfoSectionMap.put("organizationStartYear",organizationStartYear);
    jobInfoSectionMap.put("organizationPostalcode",organizationPostalcode);
    jobInfoSectionMap.put("organizationAddress", organizationAddress);
    jobInfoSectionMap.put("organizationProvince", organizationProvince);
    jobInfoSectionMap.put("organizationCity", organizationCity);
//婚姻信息段
    Map<String, Object> marriageInfoSectionMap = new HashMap<>();
    marriageInfoSectionMap.put("maritalStatus", maritalStatus); // 0 2 3 6 7 8 99
    marriageInfoSectionMap.put("spouseName", spouseName);
    marriageInfoSectionMap.put("spouseMobile", spouseMobile);
    marriageInfoSectionMap.put("spouseCertificateType", spouseCertificateType);
    marriageInfoSectionMap.put("spouseCertificateNo",spouseCertificateNo);
    marriageInfoSectionMap.put("spouseCertificateIndate",spouseCertificateIndate);
    marriageInfoSectionMap.put("spouseOrganizationName",spouseOrganizationName);
//居住信息段
    Map<String,Object> residenceInfoSectionMap = new HashMap<>();
    residenceInfoSectionMap.put("residenceProvince", residenceProvince);
    residenceInfoSectionMap.put("residenceCity",residenceCity);
    residenceInfoSectionMap.put("residenceAddress",residenceAddress);         //必填
    residenceInfoSectionMap.put("residencePostalcode",residencePostalcode);
    residenceInfoSectionMap.put("residenceCondition",residenceCondition);       //必填 0-6 99
    residenceInfoSectionMap.put("residenceTel",residenceTel);
//银行卡信息段
    Map<String, Object> bankcardInfoSectionMap = new HashMap<>();
    bankcardInfoSectionMap.put("bankcardNo",bankcardNo);
    bankcardInfoSectionMap.put("bankName", bankName);
    bankcardInfoSectionMap.put("bankBranchName",bankBranchName);
    bankcardInfoSectionMap.put("bankBranchProvince",bankBranchProvince);
    bankcardInfoSectionMap.put("bankBranchCity",bankBranchCity);
    bankcardInfoSectionMap.put("bankcardBindingMobile",bankcardBindingMobile);
//联系人信息段
    List<Map<String, Object>> contactInfoSection = new ArrayList<>();
    Map<String, Object> contactInfoSectionMap1 = new HashMap<>();
    contactInfoSectionMap1.put("relationship",relationship);
    contactInfoSectionMap1.put("name",relationName);
    contactInfoSectionMap1.put("mobile",relationMobile);
    contactInfoSectionMap1.put("certificateType",relationCertificateType);
    contactInfoSectionMap1.put("certificateNo",relationCertificateNo);
    contactInfoSectionMap1.put("certificateIndate",relationCertificateIndate);
    contactInfoSectionMap1.put("organizationName",relationOrganizationName);
    contactInfoSection.add(contactInfoSectionMap1);

    Map<String, Object> contactInfoSectionMap2 = new HashMap<>();
    contactInfoSectionMap2.put("relationship",relationship);
    contactInfoSectionMap2.put("name",relationName);
    contactInfoSectionMap2.put("mobile",relationMobile);
    contactInfoSectionMap2.put("certificateType",relationCertificateType);
    contactInfoSectionMap2.put("certificateNo",relationCertificateNo);
    contactInfoSectionMap2.put("certificateIndate",relationCertificateIndate);
    contactInfoSectionMap2.put("organizationName",relationOrganizationName);
    contactInfoSection.add(contactInfoSectionMap2);
//保单信息段
    Map<String, Object> policyInfoSectionMap = new HashMap<>();
    policyInfoSectionMap.put("policyCorporationName",policyCorporationName);
    policyInfoSectionMap.put("policyStatus",policyStatus);
    policyInfoSectionMap.put("policyPaymentWay",policyPaymentWay);
    policyInfoSectionMap.put("policyValidDate",policyValidDate);
//      policyInfoSectionMap.put("policyPaymentExpire", "3");                    //暂时不填,填了报错
    policyInfoSectionMap.put("policyPaymentStatus",policyPaymentStatus);
    policyInfoSectionMap.put("policyAnnualPaymentAmount", policyAnnualPaymentAmount);
//公基金信息段
    Map<String, Object> providentFundInfoSectionMap = new HashMap<>();
    providentFundInfoSectionMap.put("providentFundIdentityConsistent",providentFundIdentityConsistent);
    providentFundInfoSectionMap.put("providentFundAccountStatus",providentFundAccountStatus);
    providentFundInfoSectionMap.put("providentFundPaymentStatus",providentFundPaymentStatus);
    providentFundInfoSectionMap.put("providentFundPaymentRecord",providentFundPaymentRecord);
    providentFundInfoSectionMap.put("providentFundMonthBaseNum",providentFundMonthBaseNum);

    requestBody.put("baseInfoSection",baseInfoSectionMap);
    requestBody.put("eduInfoSection",eduInfoSectionMap);
    requestBody.put("jobInfoSection",jobInfoSectionMap);
    requestBody.put("marriageInfoSection",marriageInfoSectionMap);
    requestBody.put("residenceInfoSection",residenceInfoSectionMap);
    requestBody.put("bankcardInfoSection", bankcardInfoSectionMap);
//      requestBody.put("contactInfoSection",contactInfoSection);                 //暂时不填,填了报错
    requestBody.put("policyInfoSection",policyInfoSectionMap);
    requestBody.put("providentFundInfoSection",  providentFundInfoSectionMap);
    String result="";
    try {
      result = httpUtils(PreloanBaseApiPost.Credit_Applay,requestBody);
      System.out.println("-------------------------");
      System.out.println(result);
      System.out.println("-------------------------");
    }catch (Exception e){
       throw new RuntimeException("");
    }

    System.out.println("-------------------------");
    System.out.println(result);
    System.out.println("-------------------------");

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
