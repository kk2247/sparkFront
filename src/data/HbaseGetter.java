package data;

import entity.Key;
import entity.Search;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * @author KGZ
 * @date 2019/1/4 10:52
 */
@WebServlet("/search")
public class HbaseGetter extends HttpServlet {
    Configuration config = HBaseConfiguration.create();

    public HbaseGetter(){
        config.set("hbase.zookeeper.quorum", "172.17.11.250,172.17.11.251,172.17.11.252");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.master", "172.17.11.246:16000");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String key=req.getParameter("s");
        try {
            Connection connection = ConnectionFactory.createConnection(config);
            List<String> keys=queryKey(connection,key);
            List<Search> search=querySearch(connection,key);
            connection.close();
            req.setAttribute("keys",keys);
            req.setAttribute("search",search);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getHbase(String key){


    }

    /**
     * 通过关键字获得关联词目录数据
     * @param connection
     * @param keyword
     * @return
     * @throws IOException
     */
    private List<String> queryKey(Connection connection,String keyword) throws IOException {
        Table table = connection.getTable(TableName.valueOf("key"));
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        ArrayList<String> links=new ArrayList<>();
        for (Result result : results) {
            Key key=new Key();
            String link= Bytes.toString(CellUtil.cloneValue(result.rawCells()[0])).trim();
            String word=Bytes.toString(CellUtil.cloneValue(result.rawCells()[1])).trim();
            if(keyword.trim().equals(word)){
                String[] strings=link.split("##");
                for(String str:strings){
                    links.add(str.trim());
                }
                key.setLink(links);
                key.setWord(word);
                break;
            }
        }
        return links;
    }

    /**
     * 通过关键字获得关键字目录
     * @param connection
     * @param keyword
     * @return
     * @throws IOException
     */
    private List<Search> querySearch(Connection connection ,String keyword) throws IOException {
        Table table = connection.getTable(TableName.valueOf("search"));
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        ArrayList<Search> list=new ArrayList<>();
        for (Result result : results) {
            Search search=new Search();
            String content=Bytes.toString(CellUtil.cloneValue(result.rawCells()[0])).trim();
            String key=Bytes.toString(CellUtil.cloneValue(result.rawCells()[1])).trim();
            String title=Bytes.toString(CellUtil.cloneValue(result.rawCells()[2])).trim();
            String url=Bytes.toString(CellUtil.cloneValue(result.rawCells()[3])).trim();
            if(key.equals(keyword.trim())){
                search.setKey(key);
                search.setTitle(title);
                search.setUrl(url);
                list.add(search);
            }
        }
        return list;
    }

}
