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

import java.security.PublicKey;

/**
 * Represents the configuration of an LTI 1.3 client. I.e. information about
 * an LTI tool which has been advertised to the rest of the world.
 * 
 * @author jon
 */
public class ClientLtiConfiguration
{
  boolean bdefault;
  String clientId;
  String authLoginUrl;
  String authTokenUrl;
  PublicKey publicKey;
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

  public PublicKey getPublicKey()
  {
    return publicKey;
  }

  void setPublicKey( PublicKey publicKey )
  {
    this.publicKey = publicKey;
  }

  public String[] getDeploymentIds()
  {
    return deploymentIds;
  }

  void setDeploymentIds( String[] deploymentIds )
  {
    this.deploymentIds = deploymentIds;
  }    

  
}
