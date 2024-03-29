package com.example.openbook.Activity;

import android.content.Context;
import android.content.Intent;

import com.example.openbook.Data.ChattingData;
import com.example.openbook.Data.MyData;
import com.example.openbook.Data.TableList;
import com.example.openbook.Data.TicketData;

import java.util.ArrayList;
import java.util.HashMap;

public class SendToPopUp {

    public void sendToPopUpChatting(Context context, MyData myData,
                                    HashMap<String, ChattingData> chattingDataHashMap,
                                    HashMap<String, TicketData> ticketDataHashMap,
                                    ArrayList<TableList> tableLists,
                                    String fcmData){

        Intent intent = new Intent(context, PopUpChatting.class);
        intent.putExtra("myData", myData);
        intent.putExtra("chattingData", chattingDataHashMap);
        intent.putExtra("ticketData", ticketDataHashMap);
        intent.putExtra("tableList", tableLists);
        intent.putExtra("fcmData", fcmData);

        context.startActivity(intent);

    }

    public void sendToPopUpGift(Context context, MyData myData,
                                HashMap<String, ChattingData> chattingDataHashMap,
                                HashMap<String, TicketData> ticketDataHashMap,
                                ArrayList<TableList> tableLists,
                                String from, String menuName){

        Intent intent = new Intent(context, PopUpGift.class);

        intent.putExtra("myData", myData);
        intent.putExtra("chattingData", chattingDataHashMap);
        intent.putExtra("ticketData", ticketDataHashMap);
        intent.putExtra("tableList", tableLists);
        intent.putExtra("from", from);
        intent.putExtra("menuName", menuName);

        context.startActivity(intent);


    }
}
