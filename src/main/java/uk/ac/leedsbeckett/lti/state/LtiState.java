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

package uk.ac.leedsbeckett.lti.state;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;
import uk.ac.leedsbeckett.lti.claims.LtiRoleClaims;
import uk.ac.leedsbeckett.lti.config.ClientLtiConfigurationKey;

/**
 * A state which represents a launch session with the tool. I.e. for a
 * specific user of the platform that initiates the launch.  The same
 * biological person may use the same web browser to access a second 
 * platform to launch the same tool. This needs a separate LTI state 
 * because this is a different platform user session using different
 * user credentials.
 * 
 * @author jon
 */
public class LtiState implements Serializable
{
  static final Random random = new Random( System.currentTimeMillis() );

  final String id;
  String nonce;  // not final
  final long timestamp;
  final ClientLtiConfigurationKey clientKey;  
  
  private String personName;
  private String platformName;
  private LtiRoleClaims roles;
  
  /**
   * Construct a new state object for a given tool and prepare it for 
   * authentication.
   * 
   * @param clientKey
   */
  public LtiState( ClientLtiConfigurationKey clientKey )
  {
    this.clientKey = clientKey;
    id = UUID.randomUUID().toString();
    byte[] noncebytes = new byte[32];
    random.nextBytes( noncebytes );
    BigInteger bignonce = new BigInteger( 1, noncebytes );
    StringBuilder sb = new StringBuilder();
    sb.append( bignonce.toString( 16 ) );
    while ( sb.length() < 64 )
      sb.append( "0" );
    nonce = sb.toString();
    timestamp = System.currentTimeMillis();
  }
  
  public String getIssuer()
  {
    return clientKey.getIssuerName();
  }

  public String getClientId()
  {
    return clientKey.getClientId();
  }

  public ClientLtiConfigurationKey getClientKey()
  {
    return clientKey;
  }
  
  /**
   * Get the unique ID of this state object.
   * 
   * @return The unique ID.
   */
  public String getId()
  {
    return id;
  }

  /**
   * Get the randomly generated nonce for use in authentication.
   * 
   * @return The nonce in string format.
   */
  public String getNonce()
  {
    return nonce;
  }

  /**
   * Clear the nonce after it is no longer needed to reduce the
   * impact of a hacking attempt.
   * 
   */
  public void clearNonce()
  {
    nonce = null;
  }

  /**
   * Find the timestamp of the creation of the state object.
   * 
   * @return Timestamp as a long.
   */
  public long getTimestamp()
  {
    return timestamp;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  public String getPlatformName() {
    return platformName;
  }

  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  public LtiRoleClaims getRoles() {
    return roles;
  }

  public void setRoles(LtiRoleClaims roles) {
    this.roles = roles;
  }
  
  
}
