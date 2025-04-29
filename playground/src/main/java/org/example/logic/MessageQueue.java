package org.example.logic;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zlrui
 * @since 1.0
 */
public class MessageQueue {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 第一行为2N个正整数，代表发布者发送的N个消息的时刻和内容(为方便解折，消息内容也用正 整数表示Q)。
        // 第一个数字是第一个消息的发送时刻，第二个数字是第一个消息的内容，以此类推。
        // 用例保证发送时刻不会重复，但注意消息并没有按照发送时刻排列。
        String[] messages = sc.nextLine().split(" ");
        List<Msg> msgList= new ArrayList<>();
        for (int i = 0; i < messages.length; i+=2) {
            Msg msg = new Msg(Integer.parseInt(messages[i]), Integer.parseInt(messages[i+1]));
            msgList.add(msg);
        }

        Collections.sort(msgList, (o1, o2) -> o1.time - o2.time);

        //第二行为2M个正整数，代表M个消费者订阅和取消订阅的时刻。第一个数字是第一个消费者订阅的时刻，第二个数字是第一个消费者取消订阅的时刻，以此类推。
        // 用例保证每个消费者的取消订阅时刻大于订阅时刻，消费者按优先级升序排列。
        String[] consumers = sc.nextLine().split(" ");
        List<Cer> receivers = new ArrayList<>();
        LinkedList<Cer> reverseList = new LinkedList<>(receivers);
        for (int i = 0; i < consumers.length; i+=2) {
            Cer cer = new Cer(Integer.parseInt(consumers[i]), Integer.parseInt(consumers[i + 1]));
            receivers.add(cer);
            reverseList.addFirst(cer);
        }

        for (int k=0; k<msgList.size(); k++) {
            Msg msg = msgList.get(k);
            int msgTime = msg.time;

            Iterator<Cer> it = reverseList.iterator();
            while (it.hasNext()) {
                Cer cer = it.next();
                if (cer.end <= msgTime) {
                    it.remove();
                } else if (cer.end > msgTime && cer.start <= msgTime) {
                    cer.msgList.add(msg);
                    break;
                }
            }
        }

        // 打印输出
        for (int i=0; i<receivers.size(); i++) {
            List<Msg> lst = receivers.get(i).msgList;
            if (lst.size() > 0) {
                String collect = lst.stream().mapToInt(e -> e.content).mapToObj(String::valueOf).collect(Collectors.joining(" "));
                System.out.println(collect);
            } else {
                System.out.println("-1");
            }
        }
    }

    private static class Cer {
        int start;
        int end;

        List<Msg> msgList = new LinkedList<>();

        public Cer(int start, int end) {
            this.start = start;
            this.end = end;
        }


    }

    private static class Msg {
        private int time;
        private int content;

        public Msg(int time, int content) {
            this.time = time;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "time=" + time +
                    ", content=" + content +
                    '}';
        }
    }
}
