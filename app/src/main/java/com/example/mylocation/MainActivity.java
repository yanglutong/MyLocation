package com.example.mylocation;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.mylocation.utils.BitmapUtil;
import com.example.mylocation.utils.MyToast;
import com.example.mylocation.utils.MyUtils;
import com.example.mylocation.utils.MyViewPager;
import com.pedaily.yc.ycdialoglib.toast.ToastUtils;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private MyViewPager viewPager = null;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyUtils.getPermissions(this);

        //初始化
        BitmapUtil.init();
        //获取地图控件引用
        viewPager = findViewById(R.id.vp);
        final RadioButton bt1 = findViewById(R.id.bt1);
        final RadioButton bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener(view -> {
            viewPager.setCurrentItem(0);
            bt1.setTextColor(getResources().getColor(R.color.color_3853e8 ));
            bt2.setTextColor(getResources().getColor(R.color.black ));
        });
        bt2.setOnClickListener(view -> {
            viewPager.setCurrentItem(1);
            bt1.setTextColor(getResources().getColor(R.color.black ));
            bt2.setTextColor(getResources().getColor(R.color.color_3853e8 ));
        });
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new LocationFragment());
        fragments.add(new HomeGijFragment());
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                    //已经选中时候
                if(position==0){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        bt2.setBackground(getDrawable(R.color.preview_bottom_size));
//                        bt1.setBackground(getDrawable(R.color.colorPrimaryDark));
                        viewPager.setCurrentItem(0);
                    }
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        bt2.setBackground(getDrawable(R.color.colorPrimaryDark));
//                        bt1.setBackground(getDrawable(R.color.preview_bottom_size));
                        viewPager.setCurrentItem(1);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        bt2.setBackground(getDrawable(R.color.preview_bottom_size));
//        bt1.setBackground(getDrawable(R.color.colorPrimaryDark));
        viewPager.setCurrentItem(0);
    }

    private long mPressedTime = 0;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==100){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted 授予权限
                //处理授权之后逻辑
                ToastUtils.showToast("权限通过");
            } else {
                ToastUtils.showToast("没有权限");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {//重写返回键方法
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            MyToast.showToast("再按一次退出程序");
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapUtil.clear();
    }
}