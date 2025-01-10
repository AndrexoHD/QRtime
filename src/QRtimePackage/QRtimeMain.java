package QRtimePackage;

import java.awt.Dimension;

public class QRtimeMain {
    static int sizeInt = 350;
    static String size = sizeInt+"x"+sizeInt;

    public static void main(String[] args) throws Exception {
        // Window:
        QRtimeFrame frame = new QRtimeFrame();

        // Content of Window:
        QRCode qrCode = new QRCode(size);
        frame.add(qrCode);
        frame.setSize(new Dimension(sizeInt+20, sizeInt+40));
    }
}
