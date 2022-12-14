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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;
import java.security.Key;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the configuration of an LTI 1.3 client. I.e. information about
 * an LTI tool which has been advertised to the rest of the world.
 * 
 * @author jon
 */
public class ClientLtiConfiguration implements SigningKeyResolver
{
  static final Logger logger = Logger.getLogger( ClientLtiConfiguration.class.getName() );

  boolean bdefault;
  String clientId;
  String authLoginUrl;
  String authTokenUrl;
  final HashMap<String,KeyConfiguration> keyConfigurationMap = new HashMap<>();
  String[] deploymentIds;

  public ClientLtiConfiguration( String client_id )
  {
    this.clientId = client_id;
  }

  public boolean isDefault()
  {
    return bdefault;
  }

  void setDefault( boolean bdefault )
  {
    this.bdefault = bdefault;
  }

  public String getClientId()
  {
    return clientId;
  }

  void setClientId( String clientId )
  {
    this.clientId = clientId;
  }

  public String getAuthLoginUrl()
  {
    return authLoginUrl;
  }

  void setAuthLoginUrl( String authLoginUrl )
  {
    this.authLoginUrl = authLoginUrl;
  }

  public String getAuthTokenUrl()
  {
    return authTokenUrl;
  }

  void setAuthTokenUrl( String authTokenUrl )
  {
    this.authTokenUrl = authTokenUrl;
  }

  public KeyConfiguration getKeyConfiguration( String kid )
  {
    return keyConfigurationMap.get( kid );
  }

  void putKeyConfiguration( KeyConfiguration kc )
  {
    keyConfigurationMap.put( kc.getKid(), kc );
  }

  public String[] getDeploymentIds()
  {
    return deploymentIds;
  }

  void setDeploymentIds( String[] deploymentIds )
  {
    this.deploymentIds = deploymentIds;
  }    

  @Override
  public Key resolveSigningKey( JwsHeader jh, Claims claims )
  {
    return resolveSigningKey( jh );
  }

  @Override
  public Key resolveSigningKey( JwsHeader jh, String string )
  {
    return resolveSigningKey( jh );
  }

  private Key resolveSigningKey( JwsHeader jh )
  {
    logger.log(Level.FINE, "Looking for key with ID {0}", jh.getKeyId());
    KeyConfiguration kc = this.getKeyConfiguration( jh.getKeyId() );
    
    if ( kc == null )
    {
      logger.log( Level.SEVERE, "Key not found in client LTI configuration." );
      return null;
    }
    
    if ( kc.isEnabled() )
      return kc.getKey();
    
    logger.log( Level.INFO, "LTI signing key found but is not enabled in configuration." );
    return null;
  }  
}
