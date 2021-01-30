package com.herokuapp.theinternet.baseTest.testUtilities;

import com.herokuapp.theinternet.baseTest.BaseTest;

public class TestUtilities extends BaseTest {

    //STATIC SLEEP
    protected void sleep(long m){
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
