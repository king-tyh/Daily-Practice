package tool;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CSVReader {

    /**
     * 从CSV文件中读取多个字段,fields为空时读取全部字段
     *
     * @param file   csv文件路径
     * @param fields 需要读取的字段名列表
     * @throws IOException
     */
    public List<Map<String, String>> readData(String file, String[] fields) throws IOException {
        CsvReader csvReader = new CsvReader(file, ',', Charset.forName("UTF-8"));
        csvReader.readHeaders();
        String[] headers = csvReader.getHeaders();
        if(fields==null || fields.length<=0)
            fields = headers;
        List<Map<String, String>> datas = new LinkedList<>();
        // 读取每行的内容
        while (csvReader.readRecord()) {
            Map<String, String> data = new HashMap<>();
            for (String field : fields) {
                data.put(field, csvReader.get(field));
            }
            datas.add(data);
        }
        return datas;
    }

}
