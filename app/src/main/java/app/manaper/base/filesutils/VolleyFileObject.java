package app.manaper.base.filesutils;


public class VolleyFileObject {
    private String filePath,paramName;
    private int fileType;
    private CompressObject compressObject;


    public VolleyFileObject(String paramName , String filePath,int fileType) {
        this.paramName=paramName;
        this.filePath=filePath;
        this.fileType=fileType;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getFileType() {
        return fileType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setCompressObject(CompressObject compressObject) {
        this.compressObject = compressObject;
    }

    public CompressObject getCompressObject() {
        return compressObject;
    }
}
