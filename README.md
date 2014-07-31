# androidkit简介 #


- android开发工具包，灵活小巧，低侵入，帮助提高android应用开发效率。
- 基于android 2.1，通用于目前绝大多数的android项目。


androidkit使用的官方QQ群：249418215。

androidkit的邮箱：<a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=exoVHwkUEh8QEg8kHx4NOx0UAxYaEhdVGBQW" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"/></a>。很乐意与大家探讨androidkit相关的问题。亦欢迎大家对本项目进行fork。

本项目暂时暂停做release的jar包，如果想使用新的功能，可以从develop分支中导入项目到AndroidStudio，然后编译并导出jar包。



----------

# 使用范例 #


## 1.UI绑定模块(uibind包) ##

```java

    // 这里添加注解，指定对应的id
    @AndroidView(id = R.id.home_result_upload)
    private TextView mTextUpload;
    // 资源的绑定，指定id，类型
    @AndroidRes(id = R.string.result_scan, type = ResType.STRING)
    private String mStringScan;
    // 对AdapterView的子类还可以绑定onCreateContextMenu，onItemClick等的事件监听。
    @AndroidView(id = R.id.user_listView, onCreateContextMenu = "listViewContextMenu", onItemClick = "onListItemClick")
    private ListView mUserListView;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 调用此方法将对控件、事件进行绑定
        UIBindUtil.bind(this, R.layout.activity_home);
        // 调用此方法将对资源如String, StringArray, Drawable等资源对象进行绑定。
        ResBindUtil.bindAllRes(this);
    }

   // 这里对应着上面的mUserListView的onCreateContextMenu方法名。
    public void listViewContextMenu(ContextMenu menu, View v,
                    ContextMenuInfo menuInfo) {
        menu.add(0, DELETE, 1, "删除");
    }

   // 这里对应着上面的mUserListView的onItemClick方法名。
    public void onListItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
        mUserListView.showContextMenuForChild(arg1);
    }

   // 对View的setOnClickListener事件进行绑定，这样不再需要先声明变量。
    @OnClick(viewId = { R.id.home_scan, R.id.home_user_manager })
    public void onButtonClick(View v) {
        switch (v.getId()) {
        case R.id.home_scan:
            break;
        case R.id.home_user_manager:
            startActivity(new Intent(this, UserManagerActivity.class));
            break;
        default:
            break;
        }
    }
``` 
## 2.HTTP模块(http包) ##

目前只封装了简单的HTTP请求功能，提供了GET/POST/PUT/DELETE等相关的静态方法调用，并尽量让代码更简洁。

示例：

注意，使用Http模块功能需要添加以下权限：
```xml

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

不带参数并且阻塞式的请求：
```java

    try {
        String result = Http.get(uri);
        System.out.println(result);
    } catch (IOException e) {
        e.printStackTrace();
    }
```

如果不想自己处理异常，这里还提供让你代码更简洁的方法：
```java

    String reslut = Http.getIgnoreException(uri);
    System.out.println(reslut);
```
当然，更高版本的android系统是要求必须在非UI线程中进行网络访问的操作的，而且为了有更好的用户体验，我也建议用异步方式：
```java

	HttpListener l = new HttpListener() {         
	    @Override
	    public void onFinish(String arg0) {
            System.out.println(arg0);
	    }
	    
	    @Override
	    public void onFailed(String arg0) {
            System.out.println("error:" + arg0);
    	}
    };
    Http.getOnAsyn("http://bbs.gdou.edu.cn/", l);
```
带参数的POST请求：
```java

	BasicParams params = new BasicParams();
    params.put(paramName, paramValue);
    try {
        return Http.post(uri, params);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
```

如果要获取的不是String，而是InputStream对象，例如是想获取图片并显示出来。

```java

	InputStream is = null;
	try {
		is = Http.getInputStream(uri);
		return BitmapFactory.decodeStream(is);
	} catch (IOException e) {
		log.w(e);
	} finally {
		IOUtils.closeQuietly(is);
	}
	return null;
```

以下方法可以直接获取Bitmap：

```java

	Bitmap bitmap = Http.getBitmap("http://static.oschina.net/uploads/user/113/227618_50.jpg");
```

## 3.UI库(uilibs包) ##

UI库主要是封装一些控件、及UI显示相关的代码。
如：颜色选择器ColorPickerDialog。
还有ios的圆角listview风格，使用如下：
```java

	// 在Adapter的子类的构造方法中增加RoundParams参数
	public LocalAdapter(Context context, RoundParams params) {
		super();
		mContext = context;
		mParams = params;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 在这里创建view，
		//SwitcherTextView view = new SwitcherTextView(mContext);
		// 然后在返回view前进行调用
		RoundListAdapter.setItemBackground(position, view, mParams,
				getCount());
		return view;
```	

## 4.工具库(utils包) ##

再按一次返回键退出，两句代码搞定
```java

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                    ExitDoubleClick.getInstance(this).doDoubleClick(1500, "再按一次返回键退出");
                    return true;
            }
            return super.onKeyDown(keyCode, event);
    }
```
像qq客户端启动时那样的程序开启动画,同样是几句代码搞定，只需继承一个类：
```java

public class MyIntroActivity extends IntroActivity {
    @Override
    protected Class<?> nextActivity() {
            return MainActivity.class;
    }

    @Override
    protected void setIntroResources(List<IntroImgResource> resources) {
            // 这里加上要展示的图片，最后一个参数为是否拉伸图片。
            IntroImgResource resource = new IntroImgResource(R.drawable.logo, 1500,0.3f, false);
            resources.add(resource);
    }

    @Override
    protected void runOnBackground() {
            // 如果需要在程序开启时初始化数据，可以这里执行。
    }
}
```
还有其他工具类，如SHA1加解密字符串，字节数组与16进制字符串的转换，判断网络连接状况，对象文件写入读取，摘要算法，文件拷贝、读取等等。。