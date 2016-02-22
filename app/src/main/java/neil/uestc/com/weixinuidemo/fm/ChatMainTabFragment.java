package neil.uestc.com.weixinuidemo.fm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import neil.uestc.com.weixinuidemo.R;
import neil.uestc.com.weixinuidemo.adapter.ChatMsgAdapter;
import neil.uestc.com.weixinuidemo.bean.ChatMsgItem;

/**
 * Created by Neil on 2015/12/15.
 */
public class ChatMainTabFragment extends Fragment {
    private View mRootView;

    private ListView lvMsg;
    private EditText etMsg;
    private Button btnSend;
    private ChatMsgAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.tab01, container, false);
        initViews();
        initDatas();
        lvMsg.setAdapter(adapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = etMsg.getText().toString();
                addMsgToLV(msg);
                etMsg.setText("");
            }
        });
        return mRootView;
    }


    private void initDatas() {
        adapter.add(new ChatMsgItem(1, "我是Neil,你是？"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new ChatMsgAdapter(getContext(), R.layout.chat_msg,
                R.id.id_tvChatMsg);

    }

    private void addMsgToLV(String msg) {
        if (msg.equals("")) {
            Toast.makeText(getContext(), "内容为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter.add(new ChatMsgItem(0, msg));
    }

    private void initViews() {
        lvMsg = (ListView) mRootView.findViewById(R.id.id_lvChatMsgDsp);
        btnSend = (Button) mRootView.findViewById(R.id.id_btnSend);
        etMsg = (EditText) mRootView.findViewById(R.id.id_etMsg);
    }

}
