package neil.uestc.com.weixinuidemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import neil.uestc.com.weixinuidemo.fm.ChatMainTabFragment;
import neil.uestc.com.weixinuidemo.fm.ContactMainTabFragment;
import neil.uestc.com.weixinuidemo.fm.FriendMainTabFragment;
import neil.uestc.com.weixinuidemo.view.BadgeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager vp;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> list;

    private TextView chatTV;
    private TextView friendTV;
    private TextView contactTV;

    private LinearLayout mChatWrapper;
    private BadgeView mBadgeView;

    private ImageView tablineIV;

    private int mScreen1_3;
    private int currentPageIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initTabline();
    }

    private void initTabline() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        tablineIV = (ImageView) findViewById(R.id.id_tablineIV);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tablineIV.getLayoutParams();
        lp.width = mScreen1_3;
        tablineIV.setLayoutParams(lp);
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.id_viewPager);
        chatTV = (TextView) findViewById(R.id.id_chatTV);
        friendTV = (TextView) findViewById(R.id.id_friendTV);
        contactTV = (TextView) findViewById(R.id.id_contactTV);
        list = new ArrayList<>();
        mChatWrapper = (LinearLayout) findViewById(R.id.id_chatTVWrapper);


        Fragment tab01 = new ChatMainTabFragment();
        Fragment tab02 = new FriendMainTabFragment();
        Fragment tab03 = new ContactMainTabFragment();
        list.add(tab01);
        list.add(tab02);
        list.add(tab03);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };

        vp.setAdapter(fragmentPagerAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("TABLINE", "position: " + position + "\tpositionOffset: " + positionOffset + "\tpositionOffsetPixels: " + positionOffsetPixels);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tablineIV.getLayoutParams();
                if (currentPageIndex == 0 && position == 0) {//0->1(0->1)
                    lp.leftMargin = (int) ((positionOffset) * mScreen1_3
                            + currentPageIndex * mScreen1_3);
//
//                    Log.e("TABLINE0 0",
//                            "\tcurrentPageIndex:" + currentPageIndex +
//                                    "\tposition:" + position +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tcurrentPageIndex: " + currentPageIndex +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tleftMargin: " + lp.leftMargin);

                } else if (currentPageIndex == 1 && position == 0) {//1->0(1->0)
                    lp.leftMargin = (int) (currentPageIndex * mScreen1_3
                            + (positionOffset - 1) * mScreen1_3);
//                    Log.e("TABLINE1 0",
//                            "\tcurrentPageIndex:" + currentPageIndex +
//                                    "\tposition:" + position +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tcurrentPageIndex: " + currentPageIndex +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tleftMargin: " + lp.leftMargin);

                } else if (currentPageIndex == 1 && position == 1) {//1->2(0->1)
                    lp.leftMargin = (int) (currentPageIndex * mScreen1_3
                            + (positionOffset) * mScreen1_3);
//                    Log.e("TABLINE1 1",
//                            "\tcurrentPageIndex:" + currentPageIndex +
//                                    "\tposition:" + position +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tcurrentPageIndex: " + currentPageIndex +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tleftMargin: " + lp.leftMargin);

                } else if (currentPageIndex == 2 && position == 1) {//2->1(1->0)
                    lp.leftMargin = (int) (currentPageIndex * mScreen1_3
                            + (positionOffset - 1) * mScreen1_3);
//                    Log.e("TABLINE2 1",
//                            "\tcurrentPageIndex:" + currentPageIndex +
//                                    "\tposition:" + position +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tcurrentPageIndex: " + currentPageIndex +
//                                    "\tpositionOffset: " + positionOffset +
//                                    "\tleftMargin: " + lp.leftMargin);

                }
                tablineIV.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                initTextColor();
                switch (position) {
                    case 0:
                        if (mBadgeView != null) {
                            mChatWrapper.removeView(mBadgeView);
                        }
                        mBadgeView = new BadgeView(MainActivity.this);
                        mBadgeView.setBadgeCount(7);
                        mChatWrapper.addView(mBadgeView);
                        chatTV.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        friendTV.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        contactTV.setTextColor(Color.parseColor("#008000"));
                        break;
                }
                currentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initTextColor() {
        chatTV.setTextColor(Color.BLACK);
        friendTV.setTextColor(Color.BLACK);
        contactTV.setTextColor(Color.BLACK);
    }
}
