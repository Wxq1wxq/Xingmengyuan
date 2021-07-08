package com.example.xingmengyuan.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoadDataAsyncTask extends AsyncTask<String,Void,String> {
    Context context;
    OnGetNetDataListener listener;
    ProgressDialog dialog;
    boolean isShowDialog=false;

    private void initDialog(){
        dialog=new ProgressDialog(context);
        dialog.setTitle("提示信息");
        dialog.setMessage("正在加载中.....");
    }

    public LoadDataAsyncTask(Context context, OnGetNetDataListener listener,boolean isShowDialog) {
        this.context = context;
        this.listener = listener;
        this.isShowDialog=isShowDialog;
        initDialog();
    }

    public interface  OnGetNetDataListener{
        public void onSuccess(String json);
    }
    //运行在子线程当中，用于进行耗时操作，例如网络请求等
    @Override
    protected String doInBackground(String... strings) {
       String json= HttpUtils.getJsonFromNet(strings[0]);
        return json;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        if(isShowDialog){
            dialog.show();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("TAG", "LoadDataAsyncTask的onpostExecute执行 ");
        if(isShowDialog){
            dialog.dismiss();
        }
        Log.d("TAG", "listener onSuccess方法执行");
        Log.d("TAG", s);
        listener.onSuccess(s);
    }
}
