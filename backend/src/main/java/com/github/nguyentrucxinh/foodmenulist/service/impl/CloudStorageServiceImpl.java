package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.service.CloudStorageService;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {

    @Autowired
    private Environment environment;

    private static Storage storage = null;
    private static final String bucketName = "foodmenulist.appspot.com";

    /**
     * Extracts the file payload from an HttpServletRequest, checks that the file extension
     * is supported and uploads the file to Google Cloud Storage.
     */
    @Override
    public String getImageUrl(MultipartFile multipartFile) {
        final String fileName = multipartFile.getOriginalFilename();
        // Check extension of file
        if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
            final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
            for (String s : allowedExt) {
                if (extension.equals(s)) {
                    init();
                    try {
                        return uploadFile(multipartFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
//            throw new ServletException("file must be an image");
        }
        return null;
    }

    /**
     * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
     * environment variable, appending a timestamp to end of the uploaded filename.
     */
    @SuppressWarnings("deprecation")
    private String uploadFile(MultipartFile multipartFile) throws IOException {

        final String fileExtension = getFileExtension(multipartFile);
        final String fileName = multipartFile.getOriginalFilename().split("\\.")[0];

        DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
        DateTime dt = DateTime.now(DateTimeZone.UTC);
        String dtString = dt.toString(dtf);
        final String _fileName = fileName + dtString + "." + fileExtension;

        // the inputstream is closed by default, so we don't need to close it here
        BlobInfo blobInfo =
                storage.create(
                        BlobInfo
                                .newBuilder(bucketName, _fileName)
                                // Modify access list to allow all users with link to read file
                                .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                                .build(),
                        multipartFile.getInputStream());
        // return the public download link
        return blobInfo.getMediaLink();
    }

    private String getFileExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private void init() {
        //Check if Active profiles contains "dev"
        if (Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("dev")))) {
            String SERVICE_ACCOUNT_JSON_PATH = "/home/xinhnguyen/Downloads/foodmenulist-255d606b58b9.json";
            try {
                storage =
                        StorageOptions.newBuilder()
                                .setCredentials(
                                        ServiceAccountCredentials.fromStream(
                                                new FileInputStream(SERVICE_ACCOUNT_JSON_PATH)))
                                .build()
                                .getService();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Check if Active profiles contains "prod"
        else if (Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("prod")))) {
            storage = StorageOptions.getDefaultInstance().getService();
        }
    }
}
