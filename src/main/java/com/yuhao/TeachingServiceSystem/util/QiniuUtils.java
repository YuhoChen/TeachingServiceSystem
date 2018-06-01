package com.yuhao.TeachingServiceSystem.util;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class QiniuUtils {
    private static Logger logger = Logger.getLogger(QiniuUtils.class);
    private static String ak;
    private static String sk;
    private static String domain;
    private static String bucket = "daxia";

    public QiniuUtils() {
    }

    public static String upload(byte[] data) {
        return upload(data, (String)null);
    }

    public static String upload(byte[] data, String originalFilename) {
        Auth auth = Auth.create(ak, sk);
        String upToken = auth.uploadToken(bucket);
        UploadManager uploadManager = new UploadManager();
        String key = originalFilename;
        if (StringUtils.isBlank(originalFilename)) {
            key = UUID.randomUUID().toString();
        }

        try {
            Response res = uploadManager.put(data, key, upToken);
            JSONObject json = JSONObject.parseObject(res.bodyString());
            String fileName = json.getString("key");
            String url = "http://" + domain + "/" + fileName;
            return url;
        } catch (QiniuException var11) {
            logger.error(var11, var11);
            Response r = var11.response;

            try {
                logger.error(r.bodyString());
            } catch (QiniuException var10) {
                ;
            }

            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Auth auth = Auth.create(ak, sk);
        BucketManager manager = new BucketManager(auth);
        manager.delete("daxia", "OAIA358A001_1.jpg");
    }

    public class MyRet {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;

        public MyRet() {
        }
    }
}

