package com.titan.tserver.util

import org.jetbrains.annotations.TestOnly
import sun.misc.BASE64Encoder
import java.security.MessageDigest
import java.io.UnsupportedEncodingException
import java.security.NoSuchAlgorithmException


/**
 * 加密算法
 */
class EncryptUtil {
    /**利用MD5进行加密
     * @param str  待加密的字符串
     * *
     * @return  加密后的字符串
     * *
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * *
     * @throws UnsupportedEncodingException
     */
    companion object {
        @TestOnly
        @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
        fun EncoderByMd5(str: String): String {
            //确定计算方法
            val md5 = MessageDigest.getInstance("MD5")
            val base64en = BASE64Encoder()
            //加密后的字符串
            val newstr = base64en.encode(md5.digest(str.toByteArray(charset("utf-8"))))
            return newstr
        }
    }


    /**利用SHA1进行加密
     * @param str  待加密的字符串
     * *
     * @return  加密后的字符串
     * *
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * *
     * @throws UnsupportedEncodingException
     */
    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun EncoderBySHA1(str: String): String {
        //确定计算方法
        val md5 = MessageDigest.getInstance("SHA-1")
        val base64en = BASE64Encoder()
        //加密后的字符串
        val newstr = base64en.encode(md5.digest(str.toByteArray(charset("utf-8"))))
        return newstr
    }

    /**判断用户密码是否正确
     * @param newpasswd  用户输入的密码
     * *
     * @param oldpasswd  数据库中存储的密码－－用户密码的摘要
     * *
     * @return
     * *
     * @throws NoSuchAlgorithmException
     * *
     * @throws UnsupportedEncodingException
     */
    @Throws(NoSuchAlgorithmException::class, UnsupportedEncodingException::class)
    fun checkpassword(newpasswd: String, oldpasswd: String): Boolean {
        return EncoderByMd5(newpasswd) == oldpasswd
    }
}