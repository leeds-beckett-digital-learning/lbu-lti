/*
 * Copyright 2022 Leeds Beckett University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.leedsbeckett.lti.config;

import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.io.Decoders;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.NoSuchPaddingException;
import uk.ac.leedsbeckett.lti.LtiException;

/**
 * This class takes a JSON representation of the components of an RSA public key 
 * and produces a standard java.security.PublicKey object.
 * 
 * @author jon
 */
public class JsonKeyBuilder
{
  /**
   * Takes a node from within a JSON document, read various properties and
   * return a PublicKey object.
   * 
   * @param node The JSON node containing the key's component parts.
   * @return A Java Security representation of the key which can be used for message validation.
   * @throws LtiException Thrown if the key is not RSA type.
   * @throws NoSuchAlgorithmException Should never be thrown.
   * @throws NoSuchPaddingException Only thrown if the key is dodgy.
   * @throws InvalidKeySpecException Only thrown if the key is dodgy.
   * @throws InvalidKeyException Only thrown if the key is dodgy.
   */
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

    RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(modulo, exponent);
    return factory.generatePublic(pubSpec);
  }
}
