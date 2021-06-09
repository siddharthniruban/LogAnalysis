import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Date;

public class LOGReducer extends Reducer<Text, Text, Text, Text>{

    @Override
    protected void reduce(Text key, Iterable<Text> values,
                          Context context)
            throws IOException, InterruptedException {

        Text timest = null;
        String ts1 = "01/Jan/1000:00:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss");
        Date parsedDate1 = null;
        try {
            parsedDate1 = dateFormat.parse(ts1.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp timestamp1 = new Timestamp(parsedDate1.getTime());
                Iterator<Text> valuesIt = values.iterator();
        while(valuesIt.hasNext()){
            timest = valuesIt.next();
            try {
                Date parsedDate = dateFormat.parse(timest.toString());
                Timestamp timestamp2 = new Timestamp(parsedDate.getTime());
                int b3 = timestamp1.compareTo(timestamp2);
                if(b3<0){
                    timestamp1=timestamp2;

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        context.write(key, new Text(new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss").format(timestamp1)));
    }
}
