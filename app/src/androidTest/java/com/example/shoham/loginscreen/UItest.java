//package com.example.shoham.loginscreen;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.support.test.InstrumentationRegistry;
//        import android.support.test.filters.SdkSuppress;
//        import android.support.test.runner.AndroidJUnit4;
//        import android.support.test.uiautomator.By;
//        import android.support.test.uiautomator.BySelector;
//        import android.support.test.uiautomator.UiDevice;
//        import android.support.test.uiautomator.UiObject2;
//        import android.support.test.uiautomator.Until;
//
//        import org.junit.Before;
//        import org.junit.runner.RunWith;
//
//        import static org.hamcrest.CoreMatchers.notNullValue;
//        import static org.junit.Assert.assertThat;
//        import static org.junit.Assert.fail;
//
//@RunWith(AndroidJUnit4.class)
//@SdkSuppress(minSdkVersion = 18)
//public class UItest {
//    private UiDevice mDevice;
//
//    @Before
//    public void before() {
//        // Initialize UiDevice instance
//        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//
//        assertThat(mDevice, notNullValue());
//
//        // Start from the home screen
//        mDevice.pressHome();
//
//    }
//
//    @org.junit.Test
//    public void test() throws InterruptedException {
//
//        openApp("com.example.shlez.synagogue");
//
//        UiObject2 uname = waitForObject(By.res("com.example.shlez.synagogue:id/txt_input_email"));
//        uname.setText("maor@app.com");
//
//
//        UiObject2 pass = waitForObject(By.res("com.example.shlez.synagogue:id/txt_input_password"));
//        pass.setText("123456");
//        Thread.sleep(8000);
//
//        UiObject2 login2 = waitForObject((By.text("Login")));
//  //      UiObject2 login = waitForObject((By.res("com.example.shlez.synagogue:id/btn_login")));
//        login2.click();
//        //Thread.sleep(3000);
//
//    }
//
//
//    private void openApp(String packageName) {
//        Context context = InstrumentationRegistry.getInstrumentation().getContext();
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        context.startActivity(intent);
//    }
//
//    private UiObject2 waitForObject(BySelector selector) throws InterruptedException {
//        UiObject2 object = null;
//        int timeout = 3000;
//        int delay = 200;
//        long time = System.currentTimeMillis();
//            while (object == null) {
//
//            object = mDevice.findObject(selector);
//            //Thread.sleep(delay);
//            if (System.currentTimeMillis() - timeout > time) {
//                fail();
//            }
//        }
//        return object;
//    }
//}
//
