package esper54.chapter3;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.EventType;
import com.espertech.esper.client.UpdateListener;

import java.util.Map;

/**
 * Created by liwangchun on 16/10/30.
 */
public class WithdrawalBatchpPropertiesListener implements UpdateListener {
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        System.out.println("newEvents:"+newEvents+";    oldEvents:"+oldEvents);
        if (newEvents!=null){
            System.out.println("           batch new events");
            soutEventMessage(newEvents);
        }
        if (oldEvents!=null){
            System.out.println("           batch old events");
            soutEventMessage(oldEvents);
        }
    }
    private void soutEventMessage(EventBean[] events){
        System.out.println("Events Length:" + events.length);
        for (int i=0;i<events.length;i++)
        {
            EventType eventType = events[0].getEventType();
            String[] properties = eventType.getPropertyNames();
            System.out.print("event " + i + " properties name:");
            for (int j=0;properties!=null && j<properties.length;j++)
            {
                if (j!=properties.length-1)
                {
                    System.out.print(properties[j]+",");
                }else{
                    System.out.println(properties[j]);
                }

            }
            Map<String,Object>   eventMap = (Map<String,Object>) events[i].getUnderlying();
            System.out.println("by map  amount:" + eventMap.get("amount")+"account:"+eventMap.get("account"));
            System.out.println("by get method only amount:" + events[i].get("amount")+";account:"+events[i].get("account"));
        }
    }
}
