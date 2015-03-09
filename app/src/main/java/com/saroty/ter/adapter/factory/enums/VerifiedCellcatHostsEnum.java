package com.saroty.ter.adapter.factory.enums;

/**
 * Created by root on 09/03/15.
 */
public enum VerifiedCellcatHostsEnum
{
    CELLCATFSI_UPSTLSE_FR("celcatfsi.ups-tlse.fr");
    private String host;

    private VerifiedCellcatHostsEnum(String host)
    {
        this.host = host;
    }

    public String getHost()
    {
        return host;
    }

    public static boolean contains(String host)
    {
        for(VerifiedCellcatHostsEnum e : VerifiedCellcatHostsEnum.values())
        {
            if(host == e.getHost()) return true;
        }
        return false;
    }
}