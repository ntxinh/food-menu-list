package com.github.nguyentrucxinh.foodmenulist.service.impl;

import com.github.nguyentrucxinh.foodmenulist.service.GoogleCloudStorageService;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
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
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class GoogleCloudStorageServiceImpl implements GoogleCloudStorageService {

    private static final Logger LOGGER = Logger.getLogger(GoogleCloudStorageServiceImpl.class.getName());

    @Autowired
    private Environment environment;

    private static Storage storage = null;

    @Value("${google-cloud-platform.google-cloud-storage.bucket-name}")
    private String bucketName;

    @Value("${google-cloud-platform.service-account-credentials.file-name}")
    private String serviceAccountCredentialsFileName;

    /**
     * Uploads a file to Google Cloud Storage to the bucket specified in the BUCKET_NAME
     * environment variable, appending a timestamp to end of the uploaded filename.
     */
    @Override
    public Blob uploadAndGetMediaLink(MultipartFile multipartFile, String directoryPath) {

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
            LOGGER.info("Upload " + fileName + " to " + directoryPath + " failed!");
            return null;
        }

        LOGGER.info("Upload " + fileName + " to " + directoryPath + "completed!");

        // Return Blob
        BlobId blobId = blobInfo.getBlobId();
        return storage.get(blobId);
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
                File file = ResourceUtils.getFile("classpath:" + serviceAccountCredentialsFileName);
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

    @Override
    public Blob readFile(BlobId blobId) {
        return storage.get(blobId);
    }

    @Override
    public void createBlob() {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("bucket", "blob_name");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
        Blob blob = storage.create(blobInfo, "Hello, Cloud Storage!".getBytes(UTF_8));
    }

    @Override
    public void updateBlob() {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("bucket", "blob_name");
        Blob blob = storage.get(blobId);
        if (blob != null) {
            byte[] prevContent = blob.getContent();
            System.out.println(new String(prevContent, UTF_8));
            WritableByteChannel channel = blob.writer();
            try {
                channel.write(ByteBuffer.wrap("Updated content".getBytes(UTF_8)));
                channel.close();
            } catch (IOException e) {
                LOGGER.info("Throw IOException: ");
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
    }
}
