import android.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Narrator {
    public static MediaPlayer player = new MediaPlayer();

    public Narrator(InputStream is) {
        File temp;
        FileInputStream fis;
        FileOutputStream fos;
        byte[] buffer;
        int len = 0;

        try {
            temp = File.createTempFile("narrator", "mp3");
            temp.deleteOnExit();
            fos = new FileOutputStream(temp);
            buffer = new byte[1024];

            while((len = is.read(buffer)) != -1)
                fos.write(buffer, 0, len);

            fis = new FileInputStream(temp);
            player.setDataSource(fis.getFD());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    public void stop() {
        player.stop();
    }
}
