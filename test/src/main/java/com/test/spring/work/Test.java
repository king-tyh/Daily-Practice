package com.test.spring.work;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class Test {
    public static void main(String[] args) throws Exception {
        byte[] corpus = getMp3FromPcm("src/main/resources/20220905165437544672C8631420006B.pcm");
        FileOutputStream fos = new FileOutputStream("src/main/resources/20220905165437544672C8631420006B.mp3");
        fos.write(corpus);
        fos.close();
        System.out.println(corpus.length);
        //convertAudioFiles("src/main/resources/20220905165437544672C8631420006B.pcm","src/main/resources/20220905165437544672C8631420006B.mp3");
    }

    public static void convertAudioFiles(String src, String target) throws Exception {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);
        //计算长度
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();
        //填入参数，比特率等等。这里用的是16位单声道 8000 hz
        WaveHeader header = new WaveHeader();
        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.FmtHdrLength = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 16000;
        header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLength = PCMSize;
        byte[] h = header.getHeader();
        //WAV标准，头部应该是44字节
        assert h.length == 44;
        //write header
        fos.write(h, 0, h.length);
        //write data stream
        fis = new FileInputStream(src);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        System.out.println("PCM Convert to MP3 OK!");
    }

    public static byte[] getMp3FromPcm(String src) {
        byte[] corpus = new byte[0];
        try {
            FileInputStream fis = new FileInputStream(src);
            //计算长度
            byte[] buf = new byte[1024 * 4];
            int size = fis.read(buf);
            int PCMSize = 0;
            while (size != -1) {
                PCMSize += size;
                size = fis.read(buf);
            }
            fis.close();

            //存放MP3数据的数组
            corpus = new byte[PCMSize + 44];

            //填入参数，比特率等等。这里用的是16位单声道 8000 hz
            WaveHeader header = new WaveHeader();
            //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
            header.fileLength = PCMSize + (44 - 8);
            header.FmtHdrLength = 16;
            header.BitsPerSample = 16;
            header.Channels = 1;
            header.FormatTag = 0x0001;
            header.SamplesPerSec = 16000;
            header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
            header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
            header.DataHdrLength = PCMSize;
            byte[] h = header.getHeader();
            //WAV标准，头部应该是44字节
            assert h.length == 44;
            int index = 0;
            //write header
            System.arraycopy(h, 0, corpus, index, h.length);
            index += h.length;
            //write data stream
            fis = new FileInputStream(src);
            size = fis.read(buf);
            System.out.println("index: " + index + " , size:0" + size);
            while (size != -1) {
                System.out.println("index: " + index + " , size:0" + size);
                System.arraycopy(buf, 0, corpus, index, size);
                index += size;
                size = fis.read(buf);
            }
            fis.close();
            log.info("PCM Convert to MP3 OK!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return corpus;
    }
}

class WaveHeader {
    public final char[] fileID = {'R', 'I', 'F', 'F'};
    public int fileLength;
    public char[] wavTag = {'W', 'A', 'V', 'E'};
    ;
    public char[] FmtHdrID = {'f', 'm', 't', ' '};
    public int FmtHdrLength;
    public short FormatTag;
    public short Channels;
    public int SamplesPerSec;
    public int AvgBytesPerSec;
    public short BlockAlign;
    public short BitsPerSample;
    public char[] DataHdrID = {'d', 'a', 't', 'a'};
    public int DataHdrLength;

    public byte[] getHeader() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WriteChar(bos, fileID);
        WriteInt(bos, fileLength);
        WriteChar(bos, wavTag);
        WriteChar(bos, FmtHdrID);
        WriteInt(bos, FmtHdrLength);
        WriteShort(bos, FormatTag);
        WriteShort(bos, Channels);
        WriteInt(bos, SamplesPerSec);
        WriteInt(bos, AvgBytesPerSec);
        WriteShort(bos, BlockAlign);
        WriteShort(bos, BitsPerSample);
        WriteChar(bos, DataHdrID);
        WriteInt(bos, DataHdrLength);
        bos.flush();
        byte[] r = bos.toByteArray();
        bos.close();
        return r;
    }

    private void WriteShort(ByteArrayOutputStream bos, int s) throws IOException {
        byte[] myByte = new byte[2];
        myByte[1] = (byte) ((s << 16) >> 24);
        myByte[0] = (byte) ((s << 24) >> 24);
        bos.write(myByte);
    }


    private void WriteInt(ByteArrayOutputStream bos, int n) throws IOException {
        byte[] buf = new byte[4];
        buf[3] = (byte) (n >> 24);
        buf[2] = (byte) ((n << 8) >> 24);
        buf[1] = (byte) ((n << 16) >> 24);
        buf[0] = (byte) ((n << 24) >> 24);
        bos.write(buf);
    }

    private void WriteChar(ByteArrayOutputStream bos, char[] id) {
        for (int i = 0; i < id.length; i++) {
            char c = id[i];
            bos.write(c);
        }
    }

}
