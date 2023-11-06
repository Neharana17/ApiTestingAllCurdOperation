package org.example.utils;

public class FillowUtil {
  public static String FilePath=System.getProperty("user.dir")+"/src/main/java/org/example/resource/TestData.xlsx";

    public static String fatchDatafromXLSX(String sheetName ,String id, String FieldName) throws FilloException {
        String value=null;
        Fillo fillo=new Fillo();
        Connection connection= fillo.getConnection(FilePath);
        String query = "Select * from " + sheetName + " " + "where ID='" + id + " ";
        Recordset recordset=connection.executeQuery(query);
        while (recordset.next()){
            value=recordset.getField(FieldName);
        }
        recordset.close();
        connection.close();
        return value;
    }
}
