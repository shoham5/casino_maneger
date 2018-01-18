package com.example.shoham.loginscreen;


/**
 * Created by shoham on 1/16/2018.
 */


    import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

    import org.hamcrest.MatcherAssert;
    import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
    import org.mockito.Mockito;
    import org.mockito.runners.MockitoJUnitRunner;

    import android.content.Context;
    import android.content.SharedPreferences;

    import com.example.shoham.loginscreen.FIreBaseApp;
    import com.example.shoham.loginscreen.R;

@RunWith(MockitoJUnitRunner.class)
    public class UserValtest {

        private static final String FAKE_STRING = "123456";

        @Mock
        Context mMockContext;

        @Test
        public void emailTest() {
            // Given a mocked Context injected into the object under test...
            Mockito.when(mMockContext.getString(R.string.password))
                    .thenReturn(FAKE_STRING);
            FIreBaseApp Fire = new FIreBaseApp();


            // ...when the string is returned from the object under test...
            boolean result=Fire.passwordVal(FAKE_STRING);
            // ...then the result should be the expected one.
            MatcherAssert.assertThat(FAKE_STRING,result );
        }
    }