package app.manaper.base.filesutils;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class CompressObject {
    private Bitmap image = null;
    private ByteArrayOutputStream byteStream = null;
    private byte[] bytes;

    public Bitmap getImage() {
        return image;
    }


    public void setImage(Bitmap image) {
        this.image = image;
    }

    public ByteArrayOutputStream getByteStream() {
        return byteStream;
    }

    public void setByteStream(ByteArrayOutputStream byteStream) {
        this.byteStream = byteStream;
    }


    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

}
