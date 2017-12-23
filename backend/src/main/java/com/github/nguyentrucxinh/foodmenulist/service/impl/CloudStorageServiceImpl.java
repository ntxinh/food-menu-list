package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.config.GoogleCloudStorageConstants;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {

    private static final Logger LOGGER = Logger.getLogger(CloudStorageServiceImpl.class.getName());

    @Autowired
    private Environment environment;

    private static Storage storage = null;

    @Value("${google-cloud-platform.google-cloud-storage.bucket-name}")
    private String bucketName;

    @Value("${google-cloud-platform.service-account.file-name}")
    private String serviceAccountFileName;

    /**
     * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
     * environment variable, appending a timestamp to end of the uploaded filename.
     */
    @Override
    public String uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath) {

        init();

        final String fileExtensionInput = getFileExtension(multipartFile);
        final String fileNameInput = multipartFile.getOriginalFilename().split("\\.")[0];

        DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
        DateTime dt = DateTime.now(DateTimeZone.UTC);
        String dtString = dt.toString(dtf);
        final String fileName = fileNameInput + dtString + "." + fileExtensionInput;

        // the InputStream is closed by default, so we don't need to close it here
        BlobInfo blobInfo = null;
        try {
            blobInfo = storage.create(
                    BlobInfo
                            .newBuilder(bucketName, directoryPath + fileName)
                            // Modify access list to allow all users with link to read file
                            .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                            .build(),
                    multipartFile.getBytes());
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        if (blobInfo == null) {
            LOGGER.info("Upload file to Google Cloud Storage failed!");
            return null;
        }

        // return the public download link
        return blobInfo.getMediaLink();
    }

    private String getFileExtension(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    /**
     * Init Google Cloud Storage with credential
     */
    private void init() {
        //Check if Active profiles contains "dev"
        if (Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("dev")))) {
            try {
                File file = ResourceUtils.getFile("classpath:" + serviceAccountFileName);
                storage =
                        StorageOptions.newBuilder()
                                .setCredentials(
                                        ServiceAccountCredentials.fromStream(
                                                new FileInputStream(file)))
                                .build()
                                .getService();
            } catch (IOException e) {
                LOGGER.info("Throw IOException: ");
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
        //Check if Active profiles contains "prod"
        else if (Arrays.stream(environment.getActiveProfiles()).anyMatch(
                env -> (env.equalsIgnoreCase("prod")))) {
            storage = StorageOptions.getDefaultInstance().getService();
        }
    }
}
