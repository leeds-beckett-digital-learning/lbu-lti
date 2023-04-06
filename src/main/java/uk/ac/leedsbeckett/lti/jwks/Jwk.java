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
package uk.ac.leedsbeckett.lti.jwks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leedsbeckett.lti.LtiException;
import uk.ac.leedsbeckett.lti.config.JsonKeyBuilder;

/**
 *
 * @author maber01
 */
public class Jwk implements Serializable
{
  private final String kty;
  private final String e;
  private final String kid;
  private final String alg;
  private final String n;
  
  @JsonIgnore
  private transient PublicKey key = null;

  @JsonIgnore
  private transient boolean keyFailed = false;

  public Jwk( 
          @JsonProperty("kty") String kty, 
          @JsonProperty("e")   String e, 
          @JsonProperty("kid") String kid, 
          @JsonProperty("alg") String alg, 
          @JsonProperty("n")   String n )
  {
    this.kty = kty;
    this.e = e;
    this.kid = kid;
    this.alg = alg;
    this.n = n;
  }

  public String getKty()
  {
    return kty;
  }

  public String getE()
  {
    return e;
  }

  public String getKid()
  {
    return kid;
  }

  public String getAlg()
  {
    return alg;
  }

  public String getN()
  {
    return n;
  }
  
  public PublicKey getKey()
  {
    if ( key != null )
      return key;

    // Was key build attempted before and failed?
    if ( keyFailed )
      return null;
    
    try
    {
      key = JsonKeyBuilder.build( this );
      return key;
    }
    catch ( Exception ex )
    {
      keyFailed = true;
      Logger.getLogger( Jwk.class.getName() ).log( Level.SEVERE, null, ex );
      return null;
    }
  }
}
