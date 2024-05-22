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
import uk.ac.leedsbeckett.lti.LtiException;
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
  static final long NONCE_LIFETIME = 10000L;
  
  static final Random random = new Random( System.currentTimeMillis() );

  
  
  public static final int LAUNCH_TYPE_NORMAL = 0;
  public static final int LAUNCH_TYPE_DEEP_LINK = 1;
  
  final String id;
  String nonce;  // not final
  long nonceTimestamp;
  final long timestamp;
  final ClientLtiConfigurationKey clientKey;  
  
  private int launchType;
  private String personId;
  private String personName;
  private String platformName;
  private LtiRoleClaims roles;
  
  /**
   * Construct a new state object for a given tool and prepare it for 
   * authentication.
   * 
   * @param clientKey A key that identifies the LTI client and its security provider.
   */
  public LtiState( ClientLtiConfigurationKey clientKey )
  {
    this.clientKey = clientKey;
    id = UUID.randomUUID().toString();
    generateNewNonce();
    timestamp = System.currentTimeMillis();
  }
 
  public void generateNewNonce()
  {
    byte[] noncebytes = new byte[32];
    random.nextBytes( noncebytes );
    BigInteger bignonce = new BigInteger( 1, noncebytes );
    StringBuilder sb = new StringBuilder();
    sb.append( bignonce.toString( 16 ) );
    while ( sb.length() < 64 )
      sb.append( "0" );
    nonce = sb.toString();
    nonceTimestamp = System.currentTimeMillis();
  }
  
  /**
   * Check that the claimed nonce matches the one that was last
   * generated and that it was presented in a timely way.
   * 
   * @param claimedNonce The claimed nonce being validated.
   * @throws uk.ac.leedsbeckett.lti.LtiException Thrown if the nonce doesn't match.
   */
  public void validateNonce( String claimedNonce )
          throws LtiException
  {
    if ( !nonce.equals( claimedNonce ) )
      throw new LtiException( "Possible attempt at security breach - invalid LTI state nonce." );
    long now = System.currentTimeMillis();
    if ( (now - nonceTimestamp) > NONCE_LIFETIME )
      throw new LtiException( "Possible attempt at security breach - too much time elapsed between sending and receiving a nonce." );
  }
  
  /**
   * Issuer of the client key.
   * 
   * @return The value of the issuer name.
   */
  public String getIssuer()
  {
    return clientKey.getIssuerName();
  }

  /**
   * The ID of the client key, set by the issuer.
   * 
   * @return The value of the client id;
   */
  public String getClientId()
  {
    return clientKey.getClientId();
  }

  /**
   * Get the whole client key as generated by the issuer.
   * 
   * @return The client key.
   */
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
   * Find the timestamp of the creation of the state object.
   * 
   * @return Timestamp as a long.
   */
  public long getTimestamp()
  {
    return timestamp;
  }

  /**
   * Is this normal launch or deep linking launch?
   * 
   * @return Integer indicating type.
   */
  public int getLaunchType()
  {
    return launchType;
  }

  /**
   * Set the type (from launch claims message type)
   * 
   * @param launchType Use constant of this class
   */
  public void setLaunchType( int launchType )
  {
    this.launchType = launchType;
  }
  
  /**
   * The ID of the person who is launching the resource.
   * 
   * @return The person ID.
   */
  public String getPersonId()
  {
    return personId;
  }

  /** 
   * Set the ID of the person who is launching the resource.
   * 
   * @param personId  The person ID.
   */
  public void setPersonId( String personId )
  {
    this.personId = personId;
  }
  
  /**
   * The natural name of the person who is launching the resource.
   * 
   * @return The name of the person.
   */
  public String getPersonName() {
    return personName;
  }

  /**
   * Set the natural name of the person who is launching the resource.
   * @param personName The name value to set.
   */
  public void setPersonName(String personName) {
    this.personName = personName;
  }

  /**
   * Get name of launching platform.
   * 
   * @return Name of the platform.
   */
  public String getPlatformName() {
    return platformName;
  }

  /**
   * Set the name of the launching platform.
   * @param platformName The value of the name to set.
   */
  public void setPlatformName(String platformName) {
    this.platformName = platformName;
  }

  /**
   * Get an object that describes all of the roles the launching platform 
   * has set for the person who is launching the resource.
   * 
   * @return The Role claims object.
   */
  public LtiRoleClaims getRoles() {
    return roles;
  }

  /**
   * Sets the roles object for the person.
   * 
   * @param roles The object to set.
   */
  public void setRoles(LtiRoleClaims roles) {
    this.roles = roles;
  }
  
  
}
