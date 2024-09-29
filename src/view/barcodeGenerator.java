package view;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.Code128Writer;
import java.io.File;
import javax.swing.JOptionPane;

public class barcodeGenerator {

    public static String Barcode="5";
    public static File  file =new File( "src\\reports\\barcode.jpg");

    public barcodeGenerator(String Barcode) {
        barcodeGenerator.Barcode=Barcode;
    }
    public static void generate() {
        try {
        Code128Writer writer = new Code128Writer();
        BitMatrix matrix = writer.encode(Barcode, BarcodeFormat.CODE_128, 500, 300);
        MatrixToImageWriter.writeToPath(matrix, "jpg", file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
