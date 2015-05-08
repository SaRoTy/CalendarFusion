package com.saroty.ter.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Arthur on 07/05/2015.
 */
public class SerializableUtils
{
    public static byte[] serializableToByteArray(Serializable serializable) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        out = new ObjectOutputStream(bos);

        out.writeObject(serializable);

        out.close();
        bos.close();
        return bos.toByteArray();
    }

    public static Object serializableFromByteArray(byte[] bytes) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;

        in = new ObjectInputStream(bis);

        bis.close();
        in.close();
        return in.readObject();
    }
}
