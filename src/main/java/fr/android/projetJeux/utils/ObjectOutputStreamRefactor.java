package fr.android.projetJeux.utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectOutputStreamRefactor extends ObjectOutputStream {

    /**
     * @param out
     * @throws IOException
     */
    public ObjectOutputStreamRefactor(OutputStream out) throws IOException {
        super(out);
    }

    /**
     * @throws IOException
     * @throws SecurityException
     */
    protected ObjectOutputStreamRefactor() throws IOException, SecurityException {
    }

    @Override
    protected void writeStreamHeader() {
    }
}
