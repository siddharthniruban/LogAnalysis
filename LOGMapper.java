import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LOGMapper extends Mapper<Object, Text, Text, Text>{

    @Override
    protected void map(Object key, Text value,
                       Context context)
            throws IOException, InterruptedException {
        String[] tokens = value.toString().split(" ");
        String ip_address = tokens[0];
        String no_of_timestamp = tokens[3].replaceAll("\\[","").replaceAll("\\]","");

            context.write(new Text(ip_address),new Text(no_of_timestamp));

    }
}
