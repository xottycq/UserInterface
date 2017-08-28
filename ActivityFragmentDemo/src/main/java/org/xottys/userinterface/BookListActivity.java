package org.xottys.userinterface;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class BookListActivity extends Activity implements
		BookListFragment.Callbacks
{
	// 定义一个旗标，用于标识该应用是否支持大屏幕
	private boolean mTwoPane;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 指定加载R.layout.activity_book_list对应的界面布局文件
		// 但实际上该应用会根据屏幕分辨率家在不同的界面布局文件
		// 如果加载的界面布局文件中包含ID为book_detail_container的组件
	if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
			setContentView(R.layout.activity_book_twopane);
		else
			setContentView(R.layout.activity_book_list);

		if (findViewById(R.id.book_detail_container) != null)
		{
			mTwoPane = true;
			((BookListFragment) getFragmentManager()
				.findFragmentById(R.id.book_list))
				.setActivateOnItemClick(true);
		}
	}

	@Override
	public void onItemSelected(Integer id)
	{
		if (mTwoPane)
		{
			// 创建Bundle，准备向Fragment传入参数
			Bundle arguments = new Bundle();
			arguments.putInt(BookDetailFragment.ITEM_ID, id);
			// 创建BookDetailFragment对象
			BookDetailFragment fragment = new BookDetailFragment();
			// 向Fragment传入参数
			fragment.setArguments(arguments);
			// 使用fragment替换book_detail_container容器当前显示的Fragment
			getFragmentManager().beginTransaction()
					.replace(R.id.book_detail_container, fragment).commit();

		}
		else
		{
			// 创建启动BookDetailActivity的Intent
			Intent detailIntent = new Intent(this, BookDetailActivity.class);
			// 设置传给BookDetailActivity的参数
			detailIntent.putExtra(BookDetailFragment.ITEM_ID, id);
			// 启动Activity
			startActivity(detailIntent);
		}
	}

//	@Override
//	public void onConfigurationChanged(Configuration conf) {
//		super.onConfigurationChanged(conf);
//		if (conf.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//			setContentView(R.layout.activity_book_twopane);
//			mTwoPane = true;
//			((BookListFragment) getFragmentManager()
//					.findFragmentById(R.id.book_list))
//					.setActivateOnItemClick(true);
//			Toast.makeText(this, "横屏模式", Toast.LENGTH_SHORT).show();
//		} else if (conf.orientation == Configuration.ORIENTATION_PORTRAIT){
//			Toast.makeText(this, "竖屏模式", Toast.LENGTH_SHORT).show();
//			setContentView(R.layout.activity_book_list);
//		}
//	}
}
