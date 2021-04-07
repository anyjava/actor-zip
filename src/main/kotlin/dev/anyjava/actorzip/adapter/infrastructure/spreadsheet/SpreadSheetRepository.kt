package dev.anyjava.actorzip.adapter.infrastructure.spreadsheet

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import java.io.*


class SpreadSheetRepository {
//    "AIzaSyByZpbogzq8d1AhVJA5Kif-ePe-R4zBQQ4"

    private val APPLICATION_NAME = "Google Sheets API Java Quickstart"
    private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
    private val TOKENS_DIRECTORY_PATH = "tokens"

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private val SCOPES = listOf(SheetsScopes.SPREADSHEETS_READONLY)
    private val CREDENTIALS_FILE_PATH = "/secret/credentials.json"


    fun run() {
        // Build a new authorized API client service.
        val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//        https://docs.google.com/spreadsheets/d/1dbQKIutd6DydYogTAiyDPJ57vKH82FSFcqhkb2dxyo0/edit#gid=0
        val spreadsheetId = "1dbQKIutd6DydYogTAiyDPJ57vKH82FSFcqhkb2dxyo0"
        val range = "시트1!A2:E2"
        val service = Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getServiceAccountAuthorize())
            .setApplicationName(APPLICATION_NAME)
            .build()
        val response = service.spreadsheets().values()
            .get(spreadsheetId, range)
            .execute()
        val values = response.getValues()
        if (values == null || values.isEmpty()) {
            println("No data found.")
        } else {
            println("Name, Major")
            for (row in values) {
                // Print columns A and E, which correspond to indices 0 and 4.
                println("${row.get(0)}, ${row.get(4)}")
            }
        }

    }

    @Throws(IOException::class)
    private fun getCredentials(HTTP_TRANSPORT: HttpTransport): Credential? {
        // Load client secrets.
        val inputStream: InputStream = SpreadSheetRepository::class.java.getResourceAsStream(CREDENTIALS_FILE_PATH)
            ?: throw FileNotFoundException("Resource not found: $CREDENTIALS_FILE_PATH")
        val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, InputStreamReader(inputStream))

        // Build flow and trigger user authorization request.
        val flow = GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES
        )
            .setDataStoreFactory(FileDataStoreFactory(File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")
    }

    /**
     * Google Credential 정보를 가지고 Google Sheet서비스를 초기화 한다.
     *
     * @return 인증이 통과된 Sheets API client service
     * @throws IOException
     */
    @Throws(IOException::class)
    fun getSheetsService(authMode: AuthMode, httpTransport: HttpTransport): Sheets? {
        var credential: Credential? = null
        if (authMode === AuthMode.OAUTH20) {
            credential = getCredentials(httpTransport)
        } else if (authMode === AuthMode.SERVICE_ACCOUNT) {
            credential = getServiceAccountAuthorize()
        }
        return Sheets.Builder(httpTransport, JSON_FACTORY, credential)
            .setApplicationName(APPLICATION_NAME)
            .build()
    }

    /**
     * Service Account용 credentail 생성
     * @return Credential object.
     * @throws IOException
     */
    @Throws(IOException::class)
    fun getServiceAccountAuthorize(): Credential? {
        val `in`: InputStream = SpreadSheetRepository::class.java.getResourceAsStream("/secret/service.json")
//        return DefaultCredentialProvider()
        return GoogleCredential.fromStream(`in`)
            .createScoped(SCOPES)
    }

}
/**
 * 인증 모드 2개
 */
enum class AuthMode {
    OAUTH20, SERVICE_ACCOUNT
}
