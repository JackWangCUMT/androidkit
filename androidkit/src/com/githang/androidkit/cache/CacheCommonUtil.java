/*
 * @(#)CacheCommonUtil.java		       Project:androidkit
 * Date:2013-11-6
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
package com.githang.androidkit.cache;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/**
 * 与缓存相关的公共工具类。
 * 
 * @author Geek_Soledad <a target="_blank" href=
 *         "http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=XTAuOSVzPDM5LzI0OR0sLHM_MjA"
 *         style="text-decoration:none;"><img src=
 *         "http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"
 *         /></a>
 */
public class CacheCommonUtil {

	// Creates a unique subdirectory of the designated app cache directory.
	// Tries to use external
	// but if not mounted, falls back on internal storage.
	/**
	 * the methoc is referenced from
	 * http://developer.android.com/training/displaying
	 * -bitmaps/cache-bitmap.html.
	 * 
	 * 获取缓存目录的路径。它会优先获取外部存储的缓存路径，如果获取失败再获取内部缓存路径。在这个过程中，并不会创建缓存目录。
	 * 
	 * @param context
	 * @param uniqueName
	 *            缓存的子文件夹名。
	 * @return 返回缓存路径。
	 */
	public static File getDiskAppCacheDir(Context context, String uniqueName) {
		// Check if media is mounted or storage is built-in, if so, try and use
		// external cache dir
		// otherwise use internal cache dir
		final File externalCache = getExternalCacheDir(context);
		final String cachePath = (externalCache == null) ? context.getCacheDir().getPath()
				: externalCache.getPath();

		return new File(cachePath + File.separator + uniqueName);
	}

	/**
	 * 获取应用程序在外部存储设备的缓存目录
	 * 
	 * @param context
	 * @return 返回应用程序在外部存储设备的缓存目录抽象路径
	 */
	public static File getExternalCacheDir(Context context) {
		final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
		return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
	}

	/**
	 * 检查给定的路径有多大的可用空间。
	 * 
	 * @param path
	 *            给定的路径。
	 * @return 返回给定路径的可用空间大小。
	 */
	public static long getUsableSpace(File path) {
		final StatFs stats = new StatFs(path.getPath());
		return stats.getBlockCount() * stats.getAvailableBlocks();
	}
}
