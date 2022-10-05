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

import java.util.HashMap;

/**
 * Represents the configuration of the issuer of the client configuration. An
 * issuer can issue multiple LTI 1.3 tools.
 * 
 * @author jon
 */
public class IssuerLtiConfiguration
{
  String issuer;
  HashMap<String,ClientLtiConfiguration> clientmap = new HashMap<>();

  /**
   * Construct an 'empty' issuer using an issuer name.
   * 
   * @param issuer 
   */
  public IssuerLtiConfiguration( String issuer )
  {
    this.issuer = issuer;
  }

  /**
   * Get the name of this issuer.
   * 
   * @return The name value;
   */
  public String getIssuerLtiConfiguration()
  {
    return issuer;
  }

  /**
   * Add another client config to this issuer config.
   * 
   * @param client The client config to add.
   */
  public void putClientLtiConfiguration( ClientLtiConfiguration client )
  {
    clientmap.put( client.getClientId(), client );
  }

  /**
   * Get a client config from this issuer based on the client ID.
   * 
   * @param client_id The ID of the desired client.
   * @return Either null or the identified client ID.
   */
  public ClientLtiConfiguration getClientLtiConfiguration( String client_id )
  {
    return clientmap.get( client_id );
  }  
}
