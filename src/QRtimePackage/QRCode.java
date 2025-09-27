package QRtimePackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("deprecation")
public class QRCode extends JPanel {

    private BufferedImage image;
    private String size;
    private QRtimeFrame frame;

    public QRCode(QRtimeFrame frame) {
        this.frame = frame;
        startTimer();
    }

    private void loadImage(String data) {
        try {
            int frameWidth = frame.getWidth();
            int frameHeight = frame.getHeight();
            if (frameWidth > 1000 || frameHeight > 1000) {
                frameWidth = 1000;
                frameHeight = 1000;
            }
            int newSize = Math.min(frameWidth, frameHeight);
            frame.setSize(newSize, newSize+23);
            int minSize = frameWidth < frameHeight ? frameWidth : frameHeight; // Could've used Math.min() but i'm just based.
            size = (int)(minSize-10)+"x"+(int)(minSize-10);
            String urlString = "https://api.qrserver.com/v1/create-qr-code/?size="+size+"&data="+data;
            URL url = new URL(urlString);
            BufferedImage newImage = ImageIO.read(url);
            if (image != null) {
                image.flush();
            }
            image = newImage;

            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimeObject time = TimeObject.getTimeObject();
                String data = "Current Time: " + time.toString();
                loadImage(data);
            }
        },0, 1000);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
