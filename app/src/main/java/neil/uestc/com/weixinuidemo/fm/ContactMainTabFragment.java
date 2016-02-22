package neil.uestc.com.weixinuidemo.fm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import neil.uestc.com.weixinuidemo.R;

/**
 * Created by Neil on 2015/12/15.
 */
public class ContactMainTabFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab03,container,false);
    }
}
