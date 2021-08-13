package cn.wolfcode.wolf2w.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.obs.services.ObsClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class OBSUploadUtil {
	//huaweicloud域名
	public static final String ALI_DOMAIN = "https://wolf2w.obs.cn-south-1.myhuaweicloud.com/";

	public static String uploadAli(MultipartFile file) throws Exception {
		//生成文件名称
		String uuid = UUID.randomUUID().toString();
		String orgFileName = file.getOriginalFilename();//获取真实文件名称 xxx.jpg
		String ext = "." + FilenameUtils.getExtension(orgFileName);//获取拓展名字.jpg
		String fileName = uuid + ext;//xxxxxsfsasa.jpg

		// Endpoint以杭州为例，其它Region请按实际情况填写。
//		String endpoint = "http://oss-cn-guangzhou.aliyuncs.com";
		String endPoint = "https://obs.cn-south-1.myhuaweicloud.com/";

		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，
		// 请登录 https://ram.console.aliyun.com 创建。
		String ak = "*** Provide your Access Key ***";
		String sk = "*** Provide your Secret Key ***";
//		String accessKeyId = "LTAI4FygtEGrZts8Q6rmgsgW";
//		String accessKeySecret = "IwYr0Ezr3YFbcMo0YteuSlbvaY7Sl1";

		// 创建ObsClient实例
		ObsClient obsClient = new ObsClient(ak, sk, endPoint);
		// 创建OSSClient实例。
//		OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId,accessKeySecret);

		// 上传文件流。
//		ossClient.putObject("wolf2w-50", fileName, file.getInputStream());
		obsClient.putObject("wolf2w", "objectname", new File("fileName"));
		// fileName为待上传的本地文件路径，需要指定到具体的文件名

		// 关闭OSSClient。
//		ossClient.shutdown();

		return ALI_DOMAIN + fileName;
	}


}
























