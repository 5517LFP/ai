package com.example.ai.hunanturst;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

/**
 * @author dafuchen
 *         2018/3/14
 */
public class KeyUtil {
  private static final String ALGORITHM_FOR_SIGNATURE = "MD5withRSA";
  private static final String PROVIDER = "BC";
  private static final String ALGORITHM_FOR_KEY_FACTORY = "RSA";
  private static final String ALGORITHM_FOR_KEY_AES = "AES";
  private static final String PINGAN_ONE_KEY_STORE_TYPE = "jceks";
  private static final String PINGAN_ONE_ALGORITHM = "DESede/ECB/PKCS5Padding";
  private static final String PINGAN_ONE_KEY_ALIAS = "DESede.encryptKey";

  /**
   * 得到公钥信息
   * @param key
   * @return
   * @throws Exception
   */
  public static PublicKey getPublicKey(String key) throws Exception {
    byte[] decodeKeyBytes = Base64.getDecoder().decode(key);
    X509EncodedKeySpec spec =
        new X509EncodedKeySpec (decodeKeyBytes);
    KeyFactory kf = KeyFactory.getInstance(ALGORITHM_FOR_KEY_FACTORY);
    return kf.generatePublic(spec);
  }

  /**
   * 得到私钥信息
   * @param key
   * @return
   * @throws Exception
   */
  public static PrivateKey getPrivate(String key) throws Exception {
    byte[] decodeKeyBytes = Base64.getDecoder().decode(key);
    PKCS8EncodedKeySpec spec =
        new PKCS8EncodedKeySpec(decodeKeyBytes);
    KeyFactory kf = KeyFactory.getInstance(ALGORITHM_FOR_KEY_FACTORY);
    return kf.generatePrivate(spec);
  }

  /**
   * 验证签名信息
   * @param content
   * @param sign
   * @param rsaPublicKey
   * @return
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws SignatureException
   */
  public static Boolean verify (String content, String sign, PublicKey rsaPublicKey) throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    Signature signature = Signature.getInstance(ALGORITHM_FOR_SIGNATURE, PROVIDER);
    signature.initVerify(rsaPublicKey);

    byte[] messageByte = content.getBytes();
    signature.update(messageByte);

    return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
  }

  /**
   * 签名
   * @param content
   * @param privateKey
   * @return
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws SignatureException
   */
  public static String sign(String content, PrivateKey privateKey) throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    Signature signature = Signature.getInstance(ALGORITHM_FOR_SIGNATURE, PROVIDER);
    signature.initSign(privateKey);
    signature.update(content.getBytes());
    byte[] signed = signature.sign();
    return Base64.getEncoder().encodeToString(signed);
  }

  /**
   * rsa 加密
   * @param data
   * @param publicKey
   * @return
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchProviderException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public static String rsaEncrypt(String data, PublicKey publicKey) throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance(ALGORITHM_FOR_KEY_FACTORY, PROVIDER);
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    byte[] encryptedData = cipher.doFinal(data.getBytes());
    return Base64.getEncoder().encodeToString(encryptedData);
  }

  /**
   * rsa 解密
   * @param data
   * @param privateKey
   * @return
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchProviderException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public static String rsaDecrypt(String data, PrivateKey privateKey) throws Exception {
    Security.addProvider(new BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance(ALGORITHM_FOR_KEY_FACTORY, PROVIDER);
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    byte[] decodedBytes = Base64.getDecoder().decode(data);
    byte[] original = cipher.doFinal(decodedBytes);
    return new String(original);
  }

  /**
   * AES 加密
   * @param data
   * @param secret
   * @return
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchProviderException
   */
  public static String aesEncrypt(String data, String secret) throws Exception {
    Key key = new SecretKeySpec(secret.getBytes(), ALGORITHM_FOR_KEY_AES);
    Security.addProvider(new BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance(ALGORITHM_FOR_KEY_AES, PROVIDER);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] encryptedData = cipher.doFinal(data.getBytes());
    return Base64.getEncoder().encodeToString(encryptedData);
  }

  /**
   * AES 解密
   * @param data
   * @param secret
   * @return
   * @throws NoSuchPaddingException
   * @throws NoSuchAlgorithmException
   * @throws NoSuchProviderException
   * @throws InvalidKeyException
   * @throws BadPaddingException
   * @throws IllegalBlockSizeException
   */
  public static String aesDecrypt(String data, String secret) throws Exception {
    Key key = new SecretKeySpec(secret.getBytes(), ALGORITHM_FOR_KEY_AES);
    Security.addProvider(new BouncyCastleProvider());
    Cipher cipher = Cipher.getInstance(ALGORITHM_FOR_KEY_AES, PROVIDER);
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] decodedBytes = Base64.getDecoder().decode(data);
    byte[] original = cipher.doFinal(decodedBytes);
    return new String(original);
  }

  /**
   * DES 解密
   * @param data
   * @param path
   * @param password
   * @return
   * @throws Exception
   */
  public static String desDecrypt(String data, String path, String password) throws Exception {
    SecretKey secretKey = createSecretKey(path, password);
    Cipher cipher = Cipher.getInstance(PINGAN_ONE_ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    data = data.replace("\r\n", "");
    byte[] decodedBytes = Base64.getDecoder().decode(data.getBytes());
    byte[] decryptBytes = cipher.doFinal(decodedBytes);
    return new String(decryptBytes);
  }

  /**
   * DES加密
   * @param data
   * @param path
   * @param password
   * @return
   */
  public static String desEncrypt(String data, String path, String password) throws Exception {
    SecretKey secretKey = createSecretKey(path, password);
    Cipher cipher = Cipher.getInstance(PINGAN_ONE_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encryptBytes = cipher.doFinal(data.getBytes());
    byte[] base64Bytes = Base64.getEncoder().encode(encryptBytes);
    return new String(base64Bytes);
  }

  /**
   * 得到同步的秘钥
   * @return
   */
  public static String getAesKey() {
    Random random = new Random();
    random.setSeed(System.currentTimeMillis());
    byte[] bys = new byte[8];
    random.nextBytes(bys);
    return Hex.toHexString(bys);
  }

  /**
   * 得到MD5的值
   * @param data
   * @return
   */
  public static String md5(String data) {
    Digest md5Digest = new MD5Digest();
    byte[] bytes = data.getBytes();
    md5Digest.update(bytes, 0, bytes.length);
    byte[] digest = new byte[md5Digest.getDigestSize()];
    md5Digest.doFinal(digest, 0);
    return new String(Hex.encode(digest));
  }

  /**
   *  得到secretKey
   * @param path
   * @param password
   * @return
   * @throws Exception
   */
  private static SecretKey createSecretKey(String path, String password) throws Exception {
    KeyStore jceks = KeyStore.getInstance(PINGAN_ONE_KEY_STORE_TYPE);
    File keyFile = new File(path);
    InputStream keyInputStream = new FileInputStream(keyFile);
    jceks.load(keyInputStream, password.toCharArray());
    KeyStore.SecretKeyEntry encryptKey = (KeyStore.SecretKeyEntry) jceks.getEntry(PINGAN_ONE_KEY_ALIAS,
        new KeyStore.PasswordProtection(password.toCharArray()));
    return encryptKey.getSecretKey();
  }
}