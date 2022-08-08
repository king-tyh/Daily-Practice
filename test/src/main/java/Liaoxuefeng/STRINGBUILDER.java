package Liaoxuefeng;

public class STRINGBUILDER {
    protected void print(){
        System.out.print("Test print");
    }
    public static void main(String[] args) {
        String[] fields = { "name", "position", "salary" };
        String table = "employee";
        String insert = buildInsertSql(table, fields);
        System.out.println(insert);
        String s = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
        System.out.println(s.equals(insert) ? "测试成功" : "测试失败");
    }

    static String buildInsertSql(String table, String[] fields) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(table).append(" (");
        for(String x:fields)
            sb.append(x).append(", ");
        sb.delete(sb.length()-2,sb.length());
        sb.append(") VALUES (?, ?, ?)");


        return sb.toString();
    }
}
