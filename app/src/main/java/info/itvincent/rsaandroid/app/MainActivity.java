package info.itvincent.rsaandroid.app;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import info.itvincent.rsaandroid.RSAAndroid;
import info.itvincent.rsaandroid.RSAException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RSAAndroid";
    private HandlerThread mHandlerThead;
    private Handler mHandler;
    private PublicKey mPublicKey;
    private PrivateKey mPrivateKey;;
    private HandlerThread mHandlerTheadPri;
    private Handler mHandlerPri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews(savedInstanceState);

        mHandlerThead = new HandlerThread("rsa");
        mHandlerThead.start();
        mHandler = new Handler(mHandlerThead.getLooper());

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Cipher cipher = null;
                String modulus = "ooBPPPT73gRXqOEZmzWLCPVCg5zEjta3WLyMBSHgglSCsntYAbN9rGFPC5SWytlBBQd0bs4zTdUwdZ7hmZSzfrD0t8/RaMLdVO/cZJlIXUO2Q/ebMLsljxOQQC+Ca6g3Ew8oOQvzSsJif+WWCQuMXtpY5vFExLIiEsogQZulpcWOa+xb3jxA3Aiw/BxCrWbM/nNBbZjFZGy/FB4y/wO131Qj2F8YpN1kPfxUGXkRV1sDD8YOgVxv8eqkPa56wEXAh1meMa4eOILZHJ5IGydUWqu3A6s7AD+oBIOj6jFe75QxucRwam94agQbukIGR2n18AQeS8V7p1En0NCv9aAt3Q==";
                String exp = "AQAB";
                byte[] expBytes ;
                byte[] modBytes ;
                //try { dd


                expBytes = Base64.decode(exp.getBytes() , Base64.DEFAULT);
                modBytes = Base64.decode(modulus.getBytes() , Base64.DEFAULT);



                BigInteger modules = new BigInteger(1, modBytes);
                BigInteger exponent = new BigInteger(1, expBytes);
                try {

                    try {

                        RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modules, exponent);
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");


                        mPublicKey = keyFactory.generatePublic(pubSpec);




                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e){

                }
            }
        });





        // Generate Private Key
        mHandlerTheadPri = new HandlerThread("rsa");
        mHandlerTheadPri.start();
        mHandlerPri = new Handler(mHandlerTheadPri.getLooper());

        mHandlerPri.post(new Runnable() {
            @Override
            public void run() {
                Cipher cipher = null;
                String modulus = "ooBPPPT73gRXqOEZmzWLCPVCg5zEjta3WLyMBSHgglSCsntYAbN9rGFPC5SWytlBBQd0bs4zTdUwdZ7hmZSzfrD0t8/RaMLdVO/cZJlIXUO2Q/ebMLsljxOQQC+Ca6g3Ew8oOQvzSsJif+WWCQuMXtpY5vFExLIiEsogQZulpcWOa+xb3jxA3Aiw/BxCrWbM/nNBbZjFZGy/FB4y/wO131Qj2F8YpN1kPfxUGXkRV1sDD8YOgVxv8eqkPa56wEXAh1meMa4eOILZHJ5IGydUWqu3A6s7AD+oBIOj6jFe75QxucRwam94agQbukIGR2n18AQeS8V7p1En0NCv9aAt3Q==";
                String dExp = "GrbpT+YeefNQzeyjnwaAYuqfEq/1CF54KdhlVHqrRAHMsrbgUvdFE8+zhP3ahGKO89qz43t2p7cCjnOeNvdhQtSohoddr9FJPatpGgixAtQc5LOSCcLae9kFf+fPkw9GbpWxUR+L0aB/PPNrZPVHEFjpAJ5tZcC7JUehYefKpaO/pDzsQZ81exOSXsioFCeWszwp9xJO+kNu7h/sb3dGpRmXR+iQHZN8E7Fs/aYVgg2T4819g8kH02jfrqLBgtnCwFreA8RXQ5reCsgauEDg1PMx6UJ0VnzOgYtxBti7i4UUwTRwtRImnq9QTFTMjVSSrrpJpAOt78K7YpH3ufAOqQ==";
                byte[] dExpBytes ;
                byte[] modBytes ;
                //try { d


                dExpBytes = Base64.decode(dExp.getBytes() , Base64.DEFAULT);
                modBytes = Base64.decode(modulus.getBytes() , Base64.DEFAULT);



                BigInteger modules = new BigInteger(1, modBytes);
                BigInteger dExponent = new BigInteger(1, dExpBytes);
                try {

                    try {

                        RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(modules , dExponent);
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");

                        mPrivateKey = keyFactory.generatePrivate(privateSpec);




                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (NoSuchProviderException e) {
                        e.printStackTrace();
                    }


                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


    }

    private void initViews(Bundle savedInstanceState) {
        findViewById(R.id.encode_button).setOnClickListener(mOnClickListener);
        findViewById(R.id.decode_button).setOnClickListener(mOnClickListener);
        findViewById(R.id.button_copy).setOnClickListener(mOnClickListener);


    }



    private View.OnClickListener mOnClickListener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.encode_button:


                    EditText editText = (EditText) findViewById(R.id.encodeRaw_edit);
                    String raw = editText.getText().toString();
                    try {
                        byte[] result ;

                        result = RSAAndroid.encode(raw.getBytes("UTF-8"), mPublicKey);
                        String base64 = new String(Base64.encode(result, Base64.DEFAULT));

                        EditText editTextOutput = (EditText)findViewById(R.id.encodeOutput_edit);
                        editTextOutput.setText(base64);
                    } catch (RSAException e) {
                        Log.e(TAG, "", e);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                    break;
                case R.id.decode_button:
                    EditText editTextDecode = (EditText) findViewById(R.id.decodeRaw_edit);
                    String rawDecode = editTextDecode.getText().toString();
                    try {
                        byte[] result ;
                        byte[] _raw ;

                        EditText editTextDecodeOutput = (EditText) findViewById(R.id.decodeOutput_edit);

                        _raw = Base64.decode(rawDecode.getBytes() , Base64.DEFAULT);
                        result = RSAAndroid.decode(_raw, mPrivateKey);

                        String _result = null;
                        try {
                            _result = new String(result, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        editTextDecodeOutput.setText(_result);
                    } catch (RSAException e) {
                        Log.e(TAG, "", e);
                    }
                    break;
                case R.id.button_copy:
                    EditText from = (EditText)findViewById(R.id.encodeOutput_edit);
                    EditText to = (EditText) findViewById(R.id.decodeRaw_edit);
                    to.setText(from.getText().toString());
                    break;
            }
        }
    };
}
