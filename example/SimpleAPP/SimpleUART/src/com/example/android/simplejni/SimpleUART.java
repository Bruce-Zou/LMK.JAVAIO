/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.simplejni;

import java.io.FileDescriptor;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lmk.ShellUtils;
import com.lmk.ShellUtils.CommandResult;
import com.lmk.hardware;
import android.util.Log;
import android.widget.Toast;

public class SimpleUART extends Activity {
    /** Called when the activity is first created. */
    int fd0 = -1;
	private static final String TAG = "UartDetailFragment";
	public static String send;
	//private ReadThread mReadThread;
	//private boolean run_flag;

	private FileDescriptor mFd;
	public TextView textview;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textview = (TextView)this.findViewById(R.id.textview);
    String[] commands = new String[] { "chmod 0777 /sys/class/gpio/export",
					   "chmod 0777 /sys/class/gpio/unexport",
					   "chmod 0777 /dev/spidev*",
					   "chmod 0777 /dev/ttyS*",
					   "chmod 0777 /dev/i2c-*"};
 
    CommandResult result = ShellUtils.execCommand(commands, true);
    
	fd0 = hardware.open("/dev/ttyS0",hardware.O_RDWR);
	mFd = hardware.setUartMode(fd0,115200,8,'N',1); 
	hardware.UartSendSring(mFd,"123654");
	//run_flag = true;
	//mReadThread = new ReadThread();
	//mReadThread.start();
	      
 	final Handler handler = new Handler();      
    Runnable task = new Runnable(){
         public void run() {   
             handler.postDelayed(this,10);//设置延迟时间，此处是time_interval秒
             //需要执行的代码
			char value = hardware.UartReceiveChar();//hardware.
			if(value != '@'){
				Log.d(TAG, "receive char ---------------:" + value);
			 TextView textview1 = (TextView)findViewById(R.id.textview);
			 String Svalue= value + "";
			 textview1.setText(Svalue); 
				}
         }    
     };
     handler.postDelayed(task,10);//延迟调用
}
/*
	private class ReadThread extends Thread {

		@Override
		public void run() {
			super.run();
			while (run_flag) {
				char value = hardware.UartReceiveSring();//hardware.
				if(value != '@'){
					Log.d(TAG, "receive char ---------------:" + value);
					TextView textview1 = (TextView)findViewById(R.id.textview);	
					 textview1.setText("value"); 
				}
			}
			return;
		}
	}
*/	
}
