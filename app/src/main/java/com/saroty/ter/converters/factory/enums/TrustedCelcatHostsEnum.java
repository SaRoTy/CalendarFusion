package com.saroty.ter.converters.factory.enums;

/**
 * Created by root on 09/03/15.
 */
public enum TrustedCelcatHostsEnum
{
    CELCATFSI_UPSTLSE_FR("celcatfsi.ups-tlse.fr");
    private String host;

    private TrustedCelcatHostsEnum(String host)
    {
        this.host = host;
    }

    public static boolean contains(String host)
    {
        for (TrustedCelcatHostsEnum e : TrustedCelcatHostsEnum.values())
        {
            if (host.equals(e.getHost()))
                return true;
        }
        return false;
    }

    public String getHost()
    {
        return host;
    }
}