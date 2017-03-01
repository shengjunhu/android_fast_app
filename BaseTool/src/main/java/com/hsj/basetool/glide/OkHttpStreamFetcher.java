package com.hsj.basetool.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.util.ContentLengthInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpStreamFetcher implements DataFetcher<InputStream> {
    private final Call.Factory client;
    private final GlideUrl url;
    private InputStream stream;
    private ResponseBody responseBody;
    private volatile Call call;

    public OkHttpStreamFetcher(Call.Factory client, GlideUrl url) {
        this.client = client;
        this.url = url;
    }

    public InputStream loadData(Priority priority) throws Exception {
        Request.Builder requestBuilder = (new Request.Builder()).url(this.url.toStringUrl());
        Iterator request = this.url.getHeaders().entrySet().iterator();

        while(request.hasNext()) {
            Map.Entry response = (Map.Entry)request.next();
            String contentLength = (String)response.getKey();
            requestBuilder.addHeader(contentLength, (String)response.getValue());
        }

        Request request1 = requestBuilder.build();
        this.call = this.client.newCall(request1);
        Response response1 = this.call.execute();
        this.responseBody = response1.body();
        if(!response1.isSuccessful()) {
            throw new IOException("Request failed with code: " + response1.code());
        } else {
            long contentLength1 = this.responseBody.contentLength();
            this.stream = ContentLengthInputStream.obtain(this.responseBody.byteStream(), contentLength1);
            return this.stream;
        }
    }

    public void cleanup() {
        try {
            if(this.stream != null) {
                this.stream.close();
            }
        } catch (IOException var2) {
            ;
        }

        if(this.responseBody != null) {
            this.responseBody.close();
        }

    }

    public String getId() {
        return this.url.getCacheKey();
    }

    public void cancel() {
        Call local = this.call;
        if(local != null) {
            local.cancel();
        }

    }
}