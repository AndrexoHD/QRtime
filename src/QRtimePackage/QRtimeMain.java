package QRtimePackage;

import java.awt.Dimension;

public class QRtimeMain {

    public static void main(String[] args) throws Exception {
        // Window:
        QRtimeFrame frame = new QRtimeFrame();

        // Content of Window:
        QRCode qrCode = new QRCode(frame);
        frame.add(qrCode);
    }
}
