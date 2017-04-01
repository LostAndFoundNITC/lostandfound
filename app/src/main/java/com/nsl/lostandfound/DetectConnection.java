
/**
*\brief This is the DetectConnection file.This contains the function that detects if internet is active or not.
*It is in the package com.nsl.lostandfound;
*/

package com.nsl.lostandfound;
/**
*Below are all imported classes/interfaces.
*/
import android.content.Context;
import android.net.ConnectivityManager;

/**\class class with name DetectConnection is being created here.
*/
public class DetectConnection {
        /**\fn checkInternetConnection function
	*\brief Return true;If Internet is active else false;
	*/
    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager con_manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (con_manager.getActiveNetworkInfo() != null
                && con_manager.getActiveNetworkInfo().isAvailable()
                && con_manager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
