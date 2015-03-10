package com.saroty.ter.adapter;

import com.saroty.ter.adapter.exception.AdapterConnectionException;
import com.saroty.ter.web.ssl.InsecureSSLSocketFactory;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;

/**
 * Created by Arthur on 09/03/2015.
 */
public abstract class Adapter implements IAdapter
{
    protected URL url;
    protected HttpClient httpClient;

    public Adapter(URL url, boolean trusted)
    {
        this.url = url;
        if (trusted)
            this.httpClient = createInsecureClient();
        else
            this.httpClient = new DefaultHttpClient();
    }

    protected HttpResponse loadUrl() throws AdapterConnectionException
    {
        try
        {
            return httpClient.execute(new HttpGet(url.toURI()));
        } catch (Exception e)
        {
            throw new AdapterConnectionException(url);
        }
    }

    private DefaultHttpClient createInsecureClient()
    {
        try
        {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            InsecureSSLSocketFactory sf = new InsecureSSLSocketFactory(trustStore);
            sf.setHostnameVerifier(InsecureSSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e)
        {
            return new DefaultHttpClient();
        }
    }
}