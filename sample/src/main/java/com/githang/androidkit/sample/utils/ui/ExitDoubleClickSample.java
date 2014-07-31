/*
 * @(#)DoubleclickExitSample.java		       Project:com.sinaapp.msdxblog.andoridkit.sample
 * Date:2012-9-12
 *
 * Copyright (c) 2011 CFuture09, Institute of Software, 
 * Guangdong Ocean University, Zhanjiang, GuangDong, China.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.githang.androidkit.sample.utils.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.githang.androidkit.sample.R;
import com.githang.androidkit.utils.app.ExitDoubleClick;

/**
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ExitDoubleClickSample extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exit_double_click);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ExitDoubleClick.getInstance(this).doDoubleClick(1500, R.string.double_click_toast);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
