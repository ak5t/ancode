package com.an.thread.asyntask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.an.R;

public class AsynTaskExecutorActivity extends Activity {

	private static ExecutorService SINGLE_TASK_EXECUTOR;
	private static ExecutorService LIMITED_TASK_EXECUTOR;
	private static ExecutorService FULL_TASK_EXECUTOR;

	static {
		SINGLE_TASK_EXECUTOR = (ExecutorService) Executors
				.newSingleThreadExecutor();
		LIMITED_TASK_EXECUTOR = (ExecutorService) Executors
				.newFixedThreadPool(3);
		FULL_TASK_EXECUTOR = (ExecutorService) Executors.newCachedThreadPool();
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asyntask_executor);
		ListView taskListView = (ListView) findViewById(R.id.task_list);
		taskListView.setAdapter(new TaskAdapter(this, 9));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	private class TaskAdapter extends BaseAdapter {

		private Context mContext;
		private LayoutInflater mFactory;
		private int mTaskCount;
		List<SimpleAsyncTask> mTaskList;

		public TaskAdapter(Context context, int taskCount) {
			mContext = context;
			mFactory = LayoutInflater.from(mContext);
			mTaskCount = taskCount;
			mTaskList = new ArrayList<SimpleAsyncTask>(taskCount);
		}

		@Override
		public int getCount() {
			return mTaskCount;
		}

		@Override
		public Object getItem(int position) {
			return mTaskList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = mFactory.inflate(R.layout.task_layout, null);
				viewHolder = new ViewHolder();
				viewHolder.titleView = (TextView) convertView
						.findViewById(R.id.task_name);
				viewHolder.progressbar = (ProgressBar) convertView
						.findViewById(R.id.task_progress);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.titleView.setText("Task #" + position);
			SimpleAsyncTask simpleTask = new SimpleAsyncTask(
					viewHolder.progressbar);

			/*
			 * It only supports five tasks at most. More tasks will be scheduled
			 * only after first five finish. In all, the pool size of AsyncTask
			 * is 5, at any time it only has 5 threads running.
			 */
			// task.execute();
			// use AsyncTask#SERIAL_EXECUTOR is the same to #execute();
			// task.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
			// use AsyncTask#THREAD_POOL_EXECUTOR is the same to older version
			// #execute() (less than API 11)
			// but different from newer version of #execute()
			// task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			// one by one, same to newer version of #execute()
			// task.executeOnExecutor(SINGLE_TASK_EXECUTOR);
			// execute tasks at some limit which can be customized
			// task.executeOnExecutor(LIMITED_TASK_EXECUTOR);
			// no limit to thread pool size, all tasks run simultaneously
			simpleTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
//            simpleTask.execute();
			mTaskList.add(simpleTask);
			return convertView;
		}

	}

	static class ViewHolder {
		TextView titleView;
		ProgressBar progressbar;

	}

	class SimpleAsyncTask extends AsyncTask<Void, Integer, Void> {
		ProgressBar mProgressBar;

		public SimpleAsyncTask(ProgressBar progressBar) {
			mProgressBar = progressBar;
		}

		@Override
		protected Void doInBackground(Void... params) {
			int prog = 1;
			while (prog < 101) {
				SystemClock.sleep(100);
				publishProgress(prog);
				prog++;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			mProgressBar.setProgress(values[0]);
			super.onProgressUpdate(values);
		}

	}

}
