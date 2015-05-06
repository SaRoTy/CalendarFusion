package com.saroty.ter.converters.factory.enums;

/**
 * Created by Arthur on 03/05/2015.
 */
public enum TrustedAdeHostsEnum
{
    EDT_INPTOULOUSE_FR("edt.inp-toulouse.fr");
    private String host;

    private TrustedAdeHostsEnum(String host)
    {
        this.host = host;
    }

    public static boolean contains(String host)
    {
        for (TrustedAdeHostsEnum e : TrustedAdeHostsEnum.values())
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
