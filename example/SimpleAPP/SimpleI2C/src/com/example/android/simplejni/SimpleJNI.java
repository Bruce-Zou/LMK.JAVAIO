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

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.lmk.hardware;
import com.lmk.ShellUtils;
import com.lmk.ShellUtils.CommandResult;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.CharBuffer;

public class SimpleJNI extends Activity {
    /** Called when the activity is first created. */
    int fd0 = -1;
    int val=0;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);

        String[] commands = new String[] { "chmod 0777 /sys/class/gpio/export",
					   "chmod 0777 /sys/class/gpio/unexport",
					   "chmod 0777 /dev/spidev*",
					   "chmod 0777 /dev/i2c-*"};
 
        CommandResult result = ShellUtils.execCommand(commands, true);
	fd0 = hardware.open("/dev/i2c-1",hardware.O_RDWR);//open i2c-dev devices
	hardware.setI2CSlaveAddr(fd0,0x65,1); //set i2c address
	hardware.setI2CTimeout(fd0,100); //set i2c tomeout
	hardware.setI2CRetries(fd0,5); //set i2c acks

	val = hardware.I2CReadByte(fd0,0xA0);//read a byte by reg[0xa0]
	
	tv.setText("------------------------------------------------------------------"+"sss"+(val&0xFF));
        setContentView(tv);
    }
}

