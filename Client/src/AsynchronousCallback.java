import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AsynchronousCallback implements AsyncCallback {
    @Override
    public void handleResult(Object o, URL url, String s) {

    }

    @Override
    public void handleError(Exception e, URL url, String s) {
        e.printStackTrace();
    }
}