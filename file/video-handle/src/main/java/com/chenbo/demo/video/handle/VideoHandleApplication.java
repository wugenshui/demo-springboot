package com.chenbo.demo.video.handle;

import org.springframework.util.StopWatch;
import ws.schild.jave.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

/**
 * @author : chenbo
 * @date : 2021-01-29
 */
public class VideoHandleApplication {
    public static void main(String[] args) {
        try {
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File desktop = fsv.getHomeDirectory();
            File source = new File(desktop.getPath() + "/1.mp4");
            File target = new File(desktop.getPath() + "/2.mp4");

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            zipVideo(source, target);
            stopWatch.stop();
            System.out.println("压缩视频耗时" + stopWatch.getTotalTimeSeconds());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void zipVideo(File source, File target) throws IOException {
        try {
            // 音频编码设置
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(new Integer(64000));
            audio.setChannels(new Integer(1));
            audio.setSamplingRate(new Integer(22050));

            // 视频编码设置
            VideoAttributes video = new VideoAttributes();
            video.setCodec("mpeg4");
            video.setBitRate(new Integer(160000));
            video.setFrameRate(new Integer(15));
            // video.setSize(new VideoSize(100, 150));

            // 视频转码编码设置
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setFormat("mp4");
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);

            // 编码器
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
