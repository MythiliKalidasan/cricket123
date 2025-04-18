package com.example.mythi_000.cricket;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URLEncoder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Need Android environment mocks (Log, URLEncoder potentially) or use Robolectric.
// Since URLEncoder is static, we might face issues without PowerMock or Robolectric.
// For now, assume URLEncoder works in the test environment or manually encode for verification.
// Log calls will likely fail without Robolectric/mocking Android runtime.
// Focus on the interaction with HttpRequest.

@RunWith(MockitoJUnitRunner.class)
public class NewPlayerTest {

    @Mock
    HttpRequest mockHttpRequest; // Mock the dependency

    @Captor
    ArgumentCaptor<String> urlCaptor; // Captor for URL argument

    @Captor
    ArgumentCaptor<String> dataCaptor; // Captor for data argument

    // Class under test
    NewPlayer newPlayer;

    @Before
    public void setUp() {
        newPlayer = new NewPlayer();
        newPlayer.setHttpRequest(mockHttpRequest); // Inject the mock
    }

    @Test
    public void postData_callsHttpRequestSendPostWithCorrectUrlAndData() throws Exception {
        // Arrange
        String testNo = "123";
        String testName = "Test Player";
        String expectedUrl = "https://docs.google.com/forms/d/1cWQJsmGw-5MMvSEDxGxuQ939Xv94GM4oILazJg6J8JM/formResponse";

        // Manually URL-encode expected data for verification, assuming UTF-8
        // Use a known-good encoder if URLEncoder static call is problematic in test env
        String encodedNo = URLEncoder.encode(testNo, "UTF-8");
        String encodedName = URLEncoder.encode(testName, "UTF-8");
        String expectedData = "entry.1581232643=" + encodedNo + "&" + "entry.1181358226=" + encodedName;

        // Set the fields in the instance (since onClick listener isn't run)
        newPlayer.No = testNo;
        newPlayer.Name = testName;

        // Act
        newPlayer.postData();

        // Assert
        // Verify that sendPost was called exactly once on the mock HttpRequest
        verify(mockHttpRequest, times(1)).sendPost(urlCaptor.capture(), dataCaptor.capture());

        // Verify the captured arguments
        assertEquals("URL passed to sendPost is incorrect", expectedUrl, urlCaptor.getValue());
        assertEquals("Data passed to sendPost is incorrect", expectedData, dataCaptor.getValue());
    }

    @Test
    public void postData_handlesNullNoAndNameCorrectly() throws Exception {
        // Arrange
        String expectedUrl = "https://docs.google.com/forms/d/1cWQJsmGw-5MMvSEDxGxuQ939Xv94GM4oILazJg6J8JM/formResponse";
        // Manually URL-encode expected data for verification (empty strings)
        String encodedNo = URLEncoder.encode("", "UTF-8"); // "" for null
        String encodedName = URLEncoder.encode("", "UTF-8"); // "" for null
        String expectedData = "entry.1581232643=" + encodedNo + "&" + "entry.1181358226=" + encodedName;

        // Set fields to null
        newPlayer.No = null;
        newPlayer.Name = null;

        // Act
        newPlayer.postData();

        // Assert
        verify(mockHttpRequest, times(1)).sendPost(urlCaptor.capture(), dataCaptor.capture());
        assertEquals("URL passed to sendPost is incorrect (null case)", expectedUrl, urlCaptor.getValue());
        assertEquals("Data passed to sendPost is incorrect (null case)", expectedData, dataCaptor.getValue());
    }

    // Note: This test doesn't verify the Log calls or the Thread creation within NewPlayer.
    // It focuses purely on the interaction between postData and HttpRequest.sendPost.
}
