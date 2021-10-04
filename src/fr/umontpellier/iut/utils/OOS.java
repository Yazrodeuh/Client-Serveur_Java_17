package fr.umontpellier.iut.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class OOS extends ObjectOutputStream {

    public OOS(OutputStream o) throws IOException
    {
        super(o);
    }

    @Override
    protected void writeStreamHeader() {
    }
}
