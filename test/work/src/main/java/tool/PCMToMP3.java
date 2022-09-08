package tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PCMToMP3 {
    public byte[] getMp3BytesFromPcm(String pcmFile) {
        byte[] corpus = new byte[0];
        try {
            FileInputStream fis = new FileInputStream(pcmFile);
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
            fis = new FileInputStream(pcmFile);
            size = fis.read(buf);
            while (size != -1) {
                System.arraycopy(buf, 0, corpus, index, size);
                index += size;
                size = fis.read(buf);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return corpus;
    }

    public void getMp3FileFromPcm(String pcmFile, String mp3File) {
        try {
            FileInputStream fis = new FileInputStream(pcmFile);
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
            FileOutputStream fos = new FileOutputStream(mp3File);
            //write header
            fos.write(h,0,h.length);

            //write data stream
            fis = new FileInputStream(pcmFile);
            size = fis.read(buf);
            while (size != -1) {
                fos.write(buf,0,size);
                size = fis.read(buf);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
