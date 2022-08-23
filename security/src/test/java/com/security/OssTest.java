package com.security;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class OssTest {
    public static void main(String[] args) throws FileNotFoundException {
        String method = method(new File("C:\\qq相册\\1649057143253.jpg"));
        System.out.println(method);

    }

    public static String method(File file) throws FileNotFoundException {

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
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
        String name = file.getName();
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
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileUrl, new FileInputStream(file),objectMetadata);


            ossClient.putObject(putObjectRequest);

            return fileUrl;
        } catch (Exception oe) {
            return "fail";
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }


    }
}
