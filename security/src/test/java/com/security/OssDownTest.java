package com.security;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class OssDownTest {
    public static void main(String[] args) {
        String mehtod = mehtod();
        System.out.println(mehtod);
    }
    public static String mehtod(){
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-chengdu.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tDzLgN8JfcQxPNHJUun";
        String accessKeySecret = "YTsGltC0PSgyZfT0tLiidht69G7l8D";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "huanglongoss";

        String objectName = "2022/08/13/4aa624283b2f45f7b8b6717865742e42.jpg";
        String pathName = "C:\\Users\\23500\\Desktop\\ss";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
            // 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
            ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(pathName));
       return "成功";
        }  catch (Exception ce) {
           return "fail";
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
    }

