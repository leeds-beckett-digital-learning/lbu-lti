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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.SigningKeyResolver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import uk.ac.leedsbeckett.lti.jwks.JwksSigningKeyResolver;


/**
 * Represents the configuration of an LTI tool. Loads from a JSON file.
 * Likely to be much changed in the future. Currently contains a number of
 * issuer configs which each contain a number of client configs.
 * 
 * @author jon
 */   
public interface LtiConfiguration
{  
  /**
   * Fetch a client config based on a key that identifies both the issuer
   * and the client.
   * 
   * @param clientkey A key with the IDs in.
   * @return The requested config or null if not found.
   */
  public ClientLtiConfiguration getClientLtiConfiguration( ClientLtiConfigurationKey clientkey );
  
  /**
   * Find the configuration for a particular tool given the ID of the
   * issuer and the client ID of the tool.
   * 
   * @param issuername The ID of the issuer.
   * @param client_id The ID of a client of the issuer.
   * @return A client configuration object or null if not found.
   */
  public ClientLtiConfiguration getClientLtiConfiguration( String issuername, String client_id );
}
