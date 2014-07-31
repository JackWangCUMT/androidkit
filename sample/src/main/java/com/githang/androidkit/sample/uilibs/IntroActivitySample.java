/*
 * @(#)IntroActivitySample.java		       Project:example
 * Date:2013-11-12
 *
 * Copyright (c) 2013 CFuture09, Institute of Software, 
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
package com.githang.androidkit.sample.uilibs;

import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.githang.androidkit.app.IntroActivity;
import com.githang.androidkit.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Geek_Soledad <a target="_blank" href=
 *         "http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=XTAuOSVzPDM5LzI0OR0sLHM_MjA"
 *         style="text-decoration:none;"><img src=
 *         "http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"
 *         /></a>
 */
public class IntroActivitySample extends IntroActivity {

	@Override
	protected void setIntroViews(IntroResource resource) {
		// TODO Auto-generated method stub
		List<ImageView> views = new ArrayList<ImageView>();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
		// 初始化引导图片列表
		int[] pics = { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3,
				R.drawable.guide_4 };
		for (int i = 0; i < pics.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(params);
			imageView.setImageResource(pics[i]);
			views.add(imageView);
			imageView.setScaleType(ScaleType.FIT_XY);// 图片是否拉伸满。
		}

		resource.views = views;
		resource.indicatorNoSelectedId = R.drawable.gray_point;
		resource.indicatorSelectedId = R.drawable.green_point;
	}

	@Override
	protected boolean showOnlyAtUpdate() {
		return false;
	}

	@Override
	protected boolean isFullScreen() {
		return true;
	}

	@Override
	protected void atEndButScrolled() {
		super.atEndButScrolled();
		Toast.makeText(this, "无法再向右移动了。。", Toast.LENGTH_SHORT).show();
	}
	
}
