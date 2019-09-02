package cn.cash360.util;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * @time 2017/7/14 14:44
 * @desc
 */

public class TrustManager implements X509TrustManager {

    public static TrustManager getInstance() {
        return new TrustManager();
    }


    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public void setTrust(OkHttpClient.Builder httpClientBuilder){
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new javax.net.ssl.TrustManager[]{this}, new SecureRandom());
            HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            /**
             * 支持https
             */
            httpClientBuilder.sslSocketFactory(sslContext.getSocketFactory(), new TrustManager());
            httpClientBuilder.hostnameVerifier(DO_NOT_VERIFY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
