package com.security.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.security.utils.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class OssFileController {
    @PostMapping("/upload")
    public Message upload(@RequestPart("file") MultipartFile file){
        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        String accessKeyId = "LTAI5tDzLgN8JfcQxPNHJUun";
        String accessKeySecret = "YTsGltC0PSgyZfT0tLiidht69G7l8D";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "huanglongoss";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //创建日期目录
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        String datepath = dateFormat.format(new Date());
        /**创建文件路径*/
        String name = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String suffixName = name.substring(name.lastIndexOf("."));
        String newName=uuid+suffixName;
        String fileUrl=datepath+"/"+newName;
        try {
            // 填写字符串。
            String content = "Hello OSS";

            //解决访问图片路径时导致直接下载问题
            /*阿里云默认存储图片的请求头为 image/jpeg,此时打开图片地址就会直接下载。将请求头改为image/jpg，*/
            ObjectMetadata objectMetadata=new ObjectMetadata();
            objectMetadata.setContentType("image/jpg");
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileUrl, file.getInputStream(),objectMetadata);

            // 上传字符串。
            ossClient.putObject(putObjectRequest);
            return Message.success().add("url","https://huanglongoss.oss-cn-chengdu.aliyuncs.com/"+fileUrl);
        } catch (Exception oe) {
            return Message.fail();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
