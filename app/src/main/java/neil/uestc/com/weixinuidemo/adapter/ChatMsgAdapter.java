package neil.uestc.com.weixinuidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import neil.uestc.com.weixinuidemo.R;
import neil.uestc.com.weixinuidemo.bean.ChatMsgItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neil on 2016/2/22.
 */
public class ChatMsgAdapter extends ArrayAdapter<ChatMsgItem> {
    private List<ChatMsgItem> datas = new ArrayList<>();
    private Context context;

    public ChatMsgAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.datas.size();
    }

    @Override
    public ChatMsgItem getItem(int position) {
        return this.datas.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ChatMsgItem item = datas.get(position);
        TextView tv;

        if (view == null) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.chat_msg, parent, false);
        }
        tv = (TextView) view.findViewById(R.id.id_tvChatMsg);
        tv.setText(item.getMsg());

        if (item.getMsgType() == 1)//接收到的消息
            setViewForRecedMsg(tv);
        else if (item.getMsgType() == 0)//发送的消息
            setViewForSentMsg(tv);

        return view;
    }

    @Override
    public void add(ChatMsgItem object) {
        this.datas.add(object);
        super.add(object);
    }

    public void setViewForRecedMsg(TextView tv) {
        tv.setBackground(context.getResources().getDrawable(R.drawable.bubble_received));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    }

    public void setViewForSentMsg(TextView tv) {
        tv.setBackground(context.getResources().getDrawable(R.drawable.bubble_sent));
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) tv.getLayoutParams();
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    }
}
