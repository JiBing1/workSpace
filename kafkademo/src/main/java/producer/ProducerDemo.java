package producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerDemo {
	public static void main(String[] args) {
		Properties pros = new Properties();
		pros.put("bootstrap.servers", "127.0.0.1:9092");
		pros.put("acks", "all");
		pros.put("retries", 0);
		pros.put("batch.size", 16384);
		pros.put("linger.ms", 1);
		pros.put("buffer.memory", 33554432);
		pros.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		pros.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = null;
		try {
            producer = new KafkaProducer<String, String>(pros);
            for (int i = 0; i < 100; i++) {
                String msg = "Message " + i;
                producer.send(new ProducerRecord<String, String>("test", msg));
                System.out.println("Sent:" + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            producer.close();
        }

    }
}
