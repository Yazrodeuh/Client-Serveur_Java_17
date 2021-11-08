package fr.android.projetJeux.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectOutputStreamRefactor extends ObjectOutputStream {


    public ObjectOutputStreamRefactor(OutputStream out) throws IOException {
        super(out);
    }

    protected ObjectOutputStreamRefactor() throws IOException, SecurityException {
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        super.writeStreamHeader();
    }
}
