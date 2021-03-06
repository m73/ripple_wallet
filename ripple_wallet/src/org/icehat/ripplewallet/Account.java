package org.icehat.ripplewallet;

import android.app.Activity;
import android.os.Bundle;

import org.json.JSONObject;
import org.json.JSONException;

/** Keeps wallet information for the logged in session.
 *  Activities that deal with the account inherit from this class.
 *
 *  @author Matthías Ragnarsson
 *  @author Pétur Karl Ingólfsson
 *  @author Daniel Eduardo Pinedo Quintero
 *  @author Sigyn Jónsdóttir
 */
public class Account extends Activity {

    protected static JSONObject blob;
    public SharedResources resources;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = (SharedResources) getApplicationContext();
    }
    
    /** Sends a request for account information to the server.
     *  Note: Currently not working. Here for future implementation.
     *  
     *  @param address Address of account.
     */
    public void getAccountInfo(String address) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("command", "account_info");
        json.put("account", address);
        resources.client.sendMessage(json);
    }
    
    /** Gets IOUs balance and trust line addresses with respective limits.
     *  @param address Address of account.
     */
    public void getAccountLines(String address) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("command", "account_lines");
        json.put("account", address);
        resources.client.sendMessage(json);
        resources.client.onMessage(json);
    }
    
    /** Extracts and returns the string inside a JSON response to account_info call.
     *  Note: Currently not in use. Here for future implementation.
     *
     *  @param message JSON response to account_info. 
     *  @return The amount of XRPs specified in message.
     */
    public static String parseBalance(String message) throws JSONException {
        JSONObject json = new JSONObject(message);
        return json.getJSONObject("result")
                   .getJSONObject("account_data")
                   .get("Balance").toString();
    }
}
