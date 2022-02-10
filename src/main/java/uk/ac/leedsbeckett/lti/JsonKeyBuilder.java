/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti;

import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.io.Decoders;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author jon
 */
public class JsonKeyBuilder
{
  public static PublicKey build( JsonNode node ) throws LtiException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException
  {
    String keyType = node.get( "kty" ).asText();
    if ( !"RSA".equals( keyType ) )
      throw new LtiException( "Only RSA public keys are supported in the config.json file." );
    
    String e = node.get( "e" ).asText().trim();  // URL safe base64 encoding
    String n = node.get( "n" ).asText().trim();  // URL safe base64 encoding
    
    byte[] expBytes = Decoders.BASE64URL.decode( e );
    byte[] modBytes = Decoders.BASE64URL.decode( n );

    BigInteger modulo   = new BigInteger(1, modBytes);
    BigInteger exponent = new BigInteger(1, expBytes);

    KeyFactory factory = KeyFactory.getInstance("RSA");
    Cipher cipher = Cipher.getInstance("RSA");
    String input = "test";

    RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modulo, exponent);
    return factory.generatePublic(pubSpec);
  }
}
