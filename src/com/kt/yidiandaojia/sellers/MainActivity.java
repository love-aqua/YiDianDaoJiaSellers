package com.kt.yidiandaojia.sellers;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kt.yidiandaojia.sellers.fragment.FragmentTabAdapter;
import com.kt.yidiandaojia.sellers.fragment.ItemsFragment;
import com.kt.yidiandaojia.sellers.fragment.MyFragment;
import com.kt.yidiandaojia.sellers.fragment.MyItemFragment;
import com.kt.yidiandaojia.sellers.fragment.MyOrderFragment;

/**
 * 首页的tabActivity
 * @author kenzhao
 *
 */
public class MainActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    public static RadioGroup rgs;
    public List<Fragment> fragments = new ArrayList<Fragment>();

    private  FragmentTabAdapter tabAdapter;
    public static View view;
    public static Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        view = findViewById(R.id.ll_main);
        context = MainActivity.this;

        fragments.add(new MyOrderFragment());
        fragments.add(new MyItemFragment());
        fragments.add(new ItemsFragment());
        fragments.add(new MyFragment());

        rgs = (RadioGroup) findViewById(R.id.tabs_rg);

        tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener(){
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                System.out.println("Extra---- " + index + " checked!!! ");
            }
        });
        
    }
    
    /**
	 * 监听返回--是否退出程序
	 */ 
	private static Boolean isExit = false;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		boolean flag = true;
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			Timer tExit = null;  
		    if (isExit == false) {  
		        isExit = true; // 准备退出  
		        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();  
		        tExit = new Timer();  
		        tExit.schedule(new TimerTask() {  
		            @Override  
		            public void run() {  
		                isExit = false; // 取消退出  
		            }  
		        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
		  
		    } else {  
		        finish();  
		        System.exit(0);  
		    }  
		}else if(keyCode == KeyEvent.KEYCODE_MENU){
			
		}else{
			flag = super.onKeyDown(keyCode, event);
		}
		return flag;
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}