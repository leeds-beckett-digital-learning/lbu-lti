/*
 * Copyright 2023 maber01.
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
package uk.ac.leedsbeckett.lti.messages;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.security.Key;

/**
 *
 * @author maber01
 */
public class LtiMessageDeepLinkingResponse
{
  String kid;
  Key privateKey;
  Key publicKey;
        
  JwtBuilder builder;

  public LtiMessageDeepLinkingResponse( String kid, Key privateKey, Key publicKey )
  {
    this.kid = kid;
    this.privateKey = privateKey;
    this.publicKey = publicKey;
    
    builder = Jwts.builder()
    .setHeaderParam( "alg", "RS256" )
    .setHeaderParam( "kid", kid )
    .signWith( privateKey );
  }
  
  public void addClaim( String name, Object value )
  {
    builder.claim( name, value );
  }
  
  public String build()
  {
    String s = builder.signWith(privateKey ).compact();
    
    // Under dev, parse to check
//    Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(publicKey ).build().parseClaimsJws( s );
//    String thingy = jws.getBody().get( "justtesting", String.class );
//    if ( !"thingy".equals( thingy ) )
//      throw new IllegalArgumentException( "Claims became mangled." );
    
    return s;
  }
}
