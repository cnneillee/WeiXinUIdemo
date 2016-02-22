An successful demo
主要模仿的是微信的界面
1、通过一个ViewPager+三个Fragment来实现
2、Fragment上方有一个tabLine的ImageView，当滑动ViewPager时，tabLine会显示在相应的模块下面
3、2中的滑动效果比较难实现，源代码：
@Override
public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tablineIV.getLayoutParams();
    if (currentPageIndex == 0 && position == 0) {//0->1(0->1)
        lp.leftMargin = (int) ((positionOffset) * mScreen1_3
            + currentPageIndex * mScreen1_3);
    } else if (currentPageIndex == 1 && position == 0) {//1->0(1->0)
        lp.leftMargin = (int) (currentPageIndex * mScreen1_3
            + (positionOffset - 1) * mScreen1_3);
    } else if (currentPageIndex == 1 && position == 1) {//1->2(0->1)
        lp.leftMargin = (int) (currentPageIndex * mScreen1_3
            + (positionOffset) * mScreen1_3);
    } else if (currentPageIndex == 2 && position == 1) {//2->1(1->0)
        lp.leftMargin = (int) (currentPageIndex * mScreen1_3
            + (positionOffset - 1) * mScreen1_3);
    }
    tablineIV.setLayoutParams(lp);
}
