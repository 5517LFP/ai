package com.example.ai.hunanturst;

/**
 * @author wjh on 18-5-31.
 */
public class PreloanBaseApiPost {

  public static final String mer_code = "";
  public static final String produt_code = "";             //  固定RRH004

  /**
   * 内网测试：192.168.0.222:9084
   */

  public static final String Contract_Applay="http://192.168.0.222:9084/preloan/api/asset/v1/contract/"+mer_code+"/"+produt_code;//用信申请接口
  public static final String Credit_Applay="http://192.168.0.222:9084/preloan/api/credit/v1/credit-application/"+mer_code+"/"+produt_code;//授信申请接口
  public static final String Credit_Select = "http://192.168.0.222:9084/preloan/api/credit/v1/credit-application/query/"+mer_code+"/"+produt_code;//授信查询接口
  public static final String Contract_Select = "http://192.168.0.222:9084/preloan/api/asset/v1/contract/query/"+mer_code+"/"+produt_code;//用信查询接口
  public static final String Loan_Applay = "http://192.168.0.222:9084/preloan/api/remittance/v1/remittance-application/"+mer_code+"/"+produt_code;//放款申请接口
  public static final String Loan_Applay_Select = "http://192.168.0.222:9084/preloan/api/remittance/v1/remittance-application/query/"+mer_code+"/"+produt_code;//放款查询接口
  public static final String Repayment_Plan_Push = "http://192.168.0.222:9084/preloan/api/asset/v1/assetSet/"+mer_code+"/"+produt_code;  //还款计划推送接口
  public static final String Repayment_Plan_Select = "http://192.168.0.222:9084/preloan/api/asset/v1/assetSet/query/"+mer_code+"/"+produt_code;  //还款计划查询接口
  public static final String File_upload="http://192.168.0.222:9084/zuul/preloan/api/asset/v1/file/upload/"+ mer_code+ "/"+produt_code;//附件上传接口
  public static final String File_Select="http://192.168.0.222:9084/zuul/preloan/api/asset/v1/file/getFiles/"+ mer_code+ "/"+produt_code;//附件查询接口



}
