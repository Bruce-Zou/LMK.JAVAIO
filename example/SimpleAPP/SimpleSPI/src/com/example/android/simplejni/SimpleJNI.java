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
    int fd0 = -1,fd1 = -1;
    int[] value;
    

    	private int spi_mode = 0;
	private int spi_bits = 8;
	private int spi_delay = 0;
	private int spi_speed = 500000;
	private int spi_byte_order = hardware.LSBFIRST;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);

        String[] commands = new String[] { "chmod 0777 /sys/class/gpio/export",
					   "chmod 0777 /sys/class/gpio/unexport",
					   "chmod 0777 /dev/spidev*",
					   "chmod 0777 /dev/i2c-dev"};
 
        CommandResult result = ShellUtils.execCommand(commands, true);

	fd0 = hardware.open("/dev/spidev0.0",hardware.O_RDWR);

	at45BufWriteI();
        tv.setText("------------------------------------------------------------------");
        value = at45BufReadI();
	tv.setText("value[5]="+value[5]);

        setContentView(tv);
    }

        public int[] at45BufReadI(){
                int rBuf[] = {0xd4, 0xff, 0x00, 0x02, 0xff, 0xff}; // read data from buffer
                return hardware.transferArray(fd0,rBuf,0,5000000,8);
        }

	public void at45BufWriteI(/*byte val*/){

		int wBuf[] = {0x84, 0xff, 0x00, 0x02,0xff}; // write data to buffer    
                wBuf[4] = 0x65;
        	hardware.transferArray(fd0,wBuf,0,5000000,8);
        }

    	public void setBitOrder(int fd, int order) {
		if (fd < 0) {
			return ;
		}
		spi_byte_order = hardware.MSBFIRST;
		if(spi_byte_order == hardware.LSBFIRST) {
			spi_mode |=  hardware.SPI_LSB_FIRST;
		} else {
			spi_mode &= ~hardware.SPI_LSB_FIRST;
		}
		hardware.setSPIBitOrder(fd,spi_byte_order);

	}

	public void setDataMode(int fd, int mode) {
		if (fd < 0) {
			return ;
		}
		switch(mode)
		{
	        case hardware.SPI_MODE0:
	            spi_mode &= ~(hardware.SPI_CPHA|hardware.SPI_CPOL);
	            break;
	        case hardware.SPI_MODE1:
	            spi_mode &= ~(hardware.SPI_CPOL);
	            spi_mode |= (hardware.SPI_CPHA);
	            break;
	        case hardware.SPI_MODE2:
	            spi_mode |= (hardware.SPI_CPOL);
	            spi_mode &= ~(hardware.SPI_CPHA);
	            break;
	        case hardware.SPI_MODE3:
	            spi_mode |= (hardware.SPI_CPHA|hardware.SPI_CPOL);
	            break;
	        default:
	            Log.e("Lee", "error data mode");
		}
		
		hardware.setSPIMode(fd, spi_mode);
	}

}

