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

import java.io.Serializable;

/**
 * This is a key for use in HashMaps to identify a particular configuration.
 * It maps based on the issuer AND the client.
 * 
 * @author jon
 */
public class ClientLtiConfigurationKey implements Serializable
{
  final String issuerName; 
  final String clientId;

  /**
   * Construct using values for the two final fields.
   * 
   * @param issuerName Name of the issuer of the credentials.
   * @param clientId ID of the client that the issuer provides credentials for.
   */
  public ClientLtiConfigurationKey(String issuerName, String clientId)
  {
    this.issuerName = issuerName;
    this.clientId   = clientId;
  }

  /**
   * Get the issuer name from this key.
   * 
   * @return Value of the issuer name.
   */
  public String getIssuerName()
  {
    return issuerName;
  }

  /**
   * Get the client ID from this key.
   * 
   * @return Value of the client ID.
   */
  public String getClientId()
  {
    return clientId;
  }
}
