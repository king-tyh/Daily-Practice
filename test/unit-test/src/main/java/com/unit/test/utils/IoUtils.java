package com.unit.test.utils;

import java.io.Closeable;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IoUtils {
    private IoUtils() {
    }

    /**
     * IO流关闭
     *
     * @param closeable Closeable是可以关闭的数据的源或目标。 调用close方法来释放对象持有的资源(例如打开的文件)。
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.warn("close io fail ", e);
            }
        }
    }

    /**
     * IO流批量关闭
     *
     * @param closeables Closeable是可以关闭的数据的源或目标。 调用close方法来释放对象持有的资源(例如打开的文件)。
     */
    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            close(closeable);
        }
    }
}
