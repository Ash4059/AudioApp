package com.TuneWave.AudioApp.Service.Implementation;

import com.TuneWave.AudioApp.Firebase.Res;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.stereotype.Service;

@Service
public class DriveService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoogleCredential();

    /**
     * @return the path to the Google OAuth2 service account key file named
     *         "Credentials.json" in the current directory.
     */
    public static String getPathToGoogleCredential(){
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "Credential.json");
        return filePath.toString();
    }

    /**
     * Uploads a file to Google Drive in a specified folder and returns a response
     * detailing the upload status.
     *
     * @param file The file to be uploaded to Google Drive.
     * @return A Res object containing the status code, message, and URL of the
     *         uploaded file if successful, or error details if an exception occurs.
     */

    public Res uploadFileToDrive(File file) {
        String folderId = "1pNsu7eznezdx8R_hJtsPQeGT6lHlaIVJ";
        Res res = new Res();
        try {
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
            fileMetadata.setName(file.getName());
            fileMetadata.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("image/jpeg", file);
            com.google.api.services.drive.model.File uploadedFile = drive.files().create(fileMetadata, mediaContent)
                    .setFields("id").execute();
            String imageUrl = "https://drive.google.com/uc?export=view&id=" + uploadedFile.getId();
            System.out.println("Image url: " + imageUrl);
            file.delete();
            res.setStatus(200);
            res.setMessage("Image uploaded successfully");
            res.setUrl(imageUrl);
        } catch (IOException | GeneralSecurityException e) {
            System.out.println(e.getMessage());
            res.setStatus(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    /**
     * Creates a Google Drive service instance using a service account key file
     * located in the current directory. The key file is expected to be named
     * "Credentials.json". The service is created with the minimum scope of
     * DriveScopes.DRIVE.
     *
     * @return a Drive service instance
     * @throws IOException if an error occurs while reading the key file
     * @throws GeneralSecurityException if an error occurs while creating the
     *         credentials or initializing the service
     */
    private Drive createDriveService() throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = ServiceAccountCredentials.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        HttpRequestInitializer initializer = new HttpCredentialsAdapter(credentials);

        // Use credentials directly with Drive.Builder
        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                initializer)
                .build();
    }

}
