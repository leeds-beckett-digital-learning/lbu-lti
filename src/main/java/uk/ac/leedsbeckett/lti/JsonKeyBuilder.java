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
