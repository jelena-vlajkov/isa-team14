package com.atlaspharmacy.atlaspharmacy.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AmazonClient {

    private AmazonS3 _s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String _endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String _bucketName;
    @Value("${amazonProperties.accessKey}")
    private String _accessKey;
    @Value("${amazonProperties.secretKey}")
    private String _secretKey;
@PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this._accessKey, this._secretKey);
       this._s3Client = AmazonS3ClientBuilder.standard()
            .withRegion("eu-central-1")
            .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException
    {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private String genUniqueFilename(MultipartFile file)
    {
        return new Date().getTime() + "-" + file.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileToS3Bucket(String filename, File file)
    {
        this._s3Client.putObject(new PutObjectRequest(this._bucketName, filename, file)
            .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multiPartFile)
    {
        String fileUrl = "";
        try 
        {
            File file = convertMultiPartToFile(multiPartFile);
            String filename = genUniqueFilename(multiPartFile);
            fileUrl = this._endpointUrl + "/" + this._bucketName + "/" + filename;
            uploadFileToS3Bucket(filename, file);
            file.delete();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return fileUrl;
    }
}