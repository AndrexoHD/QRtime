package QRtimePackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("deprecation")
public class QRCode extends JPanel {

    private BufferedImage image;
    protected String size;

    public QRCode(String size) {
        this.size = size;
        startTimer();
    }

    private void loadImage(String size, String data) {
        try {
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
                loadImage(size, data);
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
