package app.manaper.base.filesutils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import app.manaper.R;
import app.manaper.base.constantsutils.Codes;
import app.manaper.base.constantsutils.Params;


@SuppressLint("NewApi")
public class FileOperations {
    private static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else {
                    contentUri = MediaStore.getMediaScannerUri();
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int columnIndex = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


    public String getFileAsString(final Context context, final Uri uri) {
        return fileToString(getPath(context, uri));
    }

    private String fileToString(String selectedPath) {
        FileInputStream inputStream = null;
        String str_image = "";
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        try {
            inputStream = new FileInputStream(new File(selectedPath));

            int bufferSize = 16777216;
            byte[] buffer = new byte[bufferSize];

            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            str_image = Base64.encodeToString(byteBuffer.toByteArray(), 0);

        } catch (Exception ex) {
            ex.getStackTrace();
        }

        return str_image;
    }

    public static byte[] fileToBytes(String selectedPath) {
        FileInputStream inputStream;

        File file = new File(selectedPath);
        int size = (int) file.length();
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        try {
            inputStream = new FileInputStream(new File(selectedPath));


            byte[] buffer = new byte[size];

            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer;

        } catch (Exception ex) {
            ex.getStackTrace();
        }

        return null;
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    private static Uri specialCameraSelector(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, new Date(System.currentTimeMillis()).toString() + "photo", null);
        return Uri.parse(path);
    }


    public static VolleyFileObject getVolleyFileObject(Context context, Intent data, String paramName, int fileType) {

        if (data == null) return null;

        Uri dataUrl = data.getData();
        if (fileType == Codes.FILE_TYPE_IMAGE) {
            if (dataUrl == null) {
                dataUrl = FileOperations.specialCameraSelector(context, (Bitmap) Objects.requireNonNull(Objects.requireNonNull(data.getExtras()).get("data")));
            }
            if (dataUrl == null) {
                return null;
            }
        }

        String filePath = getPath(context, dataUrl);

        CompressObject compressObject;


        Log.e("FilePath", " >> " + filePath);
        if (fileType == Codes.FILE_TYPE_IMAGE) {

            compressObject = new ImageCompression().compressImage(filePath);
            Log.e("FilePathAfterCompress", " >> " + filePath);
        } else {
            compressObject = new CompressObject();
            compressObject.setBytes(fileToBytes(filePath));
        }


        VolleyFileObject volleyFileObject = new VolleyFileObject(paramName, filePath, fileType);
        volleyFileObject.setCompressObject(compressObject);


        return volleyFileObject;

    }


    public static void pickImage(final Context context) {
        String choiceString[] = new String[]{"Gallery", "Camera"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Select image from");
        dialog.setItems(choiceString,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        if (which == 0) {
                            intent = new Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        } else {
                            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        }
                        ((AppCompatActivity) context).startActivityForResult(Intent.createChooser(intent, "Select profile picture"), Codes.FILE_TYPE_IMAGE);
                    }
                }).show();

    }

    public static void pickFile(final Context context) {

        Intent intent;
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");

        ((AppCompatActivity) context).startActivityForResult(Intent.createChooser(intent, "Select File"), Codes.FILE_TYPE_PDF);


    }

}
