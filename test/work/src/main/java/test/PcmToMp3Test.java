package test;

import lombok.extern.slf4j.Slf4j;
import tool.PCMToMP3;

import java.io.*;

@Slf4j
public class PcmToMp3Test {
    public static void main(String[] args) throws Exception {
        PCMToMP3 tool = new PCMToMP3();
        //byte[] corpus;
        //corpus = tool.getMp3BytesFromPcm("work/src/main/resources/20220905165437544672C8631420006B.pcm");
        //FileOutputStream fos = new FileOutputStream("work/src/main/resources/20220905165437544672C8631420006B.mp3");
        //fos.write(corpus);
        //fos.close();
        tool.getMp3FileFromPcm("work/src/main/resources/20220905165437544672C8631420006B.pcm",
                "work/src/main/resources/20220905165437544672C8631420006B.mp3");
    }


}


