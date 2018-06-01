package com.yuhao.TeachingServiceSystem.service;

import com.yuhao.TeachingServiceSystem.util.MyWebUtils;
import com.yuhao.TeachingServiceSystem.util.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import net.coobird.thumbnailator.Thumbnails;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

@Service
public class ImageService {
    private static int UPLOAD_TYPE_LOCAL = 1;
    private static int UPLOAD_TYPE_CDN = 2;
    // 1 is save to local, 2 is to cdn
    @Value(value = "${image.upload.type}")
    private int uploadType;

    @Value(value = "${image.upload.path}")
    private String imagePath;

    public static String getImageFullPath(String imageName) {
        return getImageFullPath(MyWebUtils.getCurrentRequest(), imageName);
    }
    public static String getImageFullPath(HttpServletRequest request, String imageName) {
        if (StringUtils.isBlank(imageName)) {
            return imageName;
        }
        if (imageName.toLowerCase().startsWith("http")) {
            return imageName;
        }
        return request.getSession().getAttribute("ctx") + "/image/" + imageName;
    }
    public static String getOrCreateSmallImage(String path, int width) throws IOException {
        File existedSmallFile = new File(getSmallFileName(path, width));
        if (existedSmallFile.exists()) {
            return existedSmallFile.getPath();
        }

        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        // 需要转换的文件为桌面上的1.png
        Thumbnails.of(file).
                width(width) // 生成图片的格式为png
                // .outputQuality(0.8f) //生成质量为80%
                // .scale(0.5f) //缩小50%
                // 输出到桌面5文件
                // .toFile("/Users/kevin/Documents/tmp_200");
                .toFile(existedSmallFile);
        return existedSmallFile.getPath();
    }

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String path = getOrCreateSmallImage("d:/me.jpg", 150);
        System.out.println("took " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(path);
    }

    public static String getSmallFileName(String path, int width) {
        String suffix = path.substring(path.lastIndexOf("."));
        path = path.substring(0, path.lastIndexOf(".")) + "_" + width + suffix;
        return path;
    }

    /**
     * 上传图片并返回完整访问路径
     * @return
     */
    public String saveImage(String fileName, byte[] data) {
        if (uploadType == UPLOAD_TYPE_LOCAL) {
            if (StringUtils.isBlank(fileName) || !fileName.contains(".")) { // 为空或没有后缀
                fileName = UUID.randomUUID().toString();
            } else {
                fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            }

            File dir = new File(imagePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!dir.exists()) {
                throw new RuntimeException("无法创建图片上传路径" + imagePath);
            }
            try {
                FileUtils.writeByteArrayToFile(new File(dir, fileName), data);
                return MyWebUtils.getCtx() + "/image/" + fileName;
            } catch (IOException e) {
                throw new RuntimeException("保存图片失败:" + e.getMessage(), e);
            }
        } else if (uploadType == UPLOAD_TYPE_CDN) {
            return QiniuUtils.upload(data, fileName);
        } else {
            throw new RuntimeException("图片上传配置错误");
        }
    }

    public String getImagePath() {
        return imagePath;
    }
}
