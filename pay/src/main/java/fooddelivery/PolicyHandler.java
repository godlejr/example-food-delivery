package fooddelivery;

import fooddelivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{


    @Autowired OrderTableRepository OrderTableRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenever주문canceled_결재cancel함(@Payload 주문canceled 주문canceled){

        if(주문canceled.isMe()){
            System.out.println("##### listener 결재cancel함 : " + 주문canceled.toJson());

            OrderTable OrderTable = new OrderTable();

            OrderTable.setstatus("cancel");

            OrderTableRepository.save(OrderTable);
        }
    }

}
