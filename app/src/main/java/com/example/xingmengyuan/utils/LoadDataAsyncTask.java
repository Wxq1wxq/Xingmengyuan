package com.example.xingmengyuan.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

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
    }

    public interface  OnGetNetDataListener{
        public void onSuccess(String json);
    }
    @Override
    protected String doInBackground(String... strings) {
        HttpUtils.getJsonFromNet(strings[0]);
        return null;
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
        if(isShowDialog){
            dialog.dismiss();
        }
        listener.onSuccess(s);
    }
}
