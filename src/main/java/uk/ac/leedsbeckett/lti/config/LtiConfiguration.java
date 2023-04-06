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
public class LtiConfiguration
{
  static final Logger logger = Logger.getLogger( LtiConfiguration.class.getName() );

  String strpathconfig;
  HashMap<String,IssuerLtiConfiguration> issuermap = new HashMap<>();  
  String rawconfig;
  
  JwksSigningKeyResolver jwksResolver = null;

  
  public LtiConfiguration()
  {
  }

  public JwksSigningKeyResolver getJwksSigningKeyResolver()
  {
    return jwksResolver;
  }

  public void setJwksSigningKeyResolver( JwksSigningKeyResolver jwksResolver )
  {
    this.jwksResolver = jwksResolver;
  }
  
  
  
  public List<String> getAllJksUrls()
  {
    ArrayList<String> list = new ArrayList<>();
    for ( IssuerLtiConfiguration iss : issuermap.values() )
      for ( IssuerLtiConfiguration.ClientLtiConfiguration client : iss.clientmap.values() )
      {
        if ( client.getAuthJwksUrl() != null )
          list.add( client.getAuthJwksUrl() );
      }
    return list;
  }
  
  /**
   * Fetch a client config based on a key that identifies both the issuer
   * and the client.
   * 
   * @param clientkey A key with the IDs in.
   * @return The requested config or null if not found.
   */
  public IssuerLtiConfiguration.ClientLtiConfiguration getClientLtiConfiguration( ClientLtiConfigurationKey clientkey )
  {
    return getClientLtiConfiguration( clientkey.getIssuerName(), clientkey.getClientId() );
  }  
  
  /**
   * Find the configuration for a particular tool given the ID of the
   * issuer and the client ID of the tool.
   * 
   * @param issuername The ID of the issuer.
   * @param client_id The ID of a client of the issuer.
   * @return A client configuration object or null if not found.
   */
  public IssuerLtiConfiguration.ClientLtiConfiguration getClientLtiConfiguration( String issuername, String client_id )
  {
    IssuerLtiConfiguration issuer = issuermap.get( issuername );
    if ( issuer == null ) return null;
    return issuer.getClientLtiConfiguration( client_id );
  }

  /**
   * The original JSON formatted text that was most recently loaded.
   * 
   * @return JSON formatted text.
   */
  public String getRawConfiguration()
  {
    return rawconfig;
  }
  
  /**
   * The file name (path) that was last used to load configuration.
   * 
   * @return Path to file.
   */
  public String getConfigFileName()
  {
    return strpathconfig;
  }
  
  /**
   * Load a configuration file in JSON format.
   * 
   * @param strpathconfig The file name (path) to load.
   */
  public void load( String strpathconfig )
  {
    this.strpathconfig = strpathconfig;
    issuermap.clear();
    
    try
    {
      rawconfig = FileUtils.readFileToString( new File( strpathconfig ), StandardCharsets.UTF_8 );      
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser( rawconfig );
      JsonNode node = mapper.readTree(parser);
      if ( node.isObject() )
      {
        logger.fine( "LtiConfiguration loading base JSON object." );
        JsonNode issuersnode = node.get( "issuers" );
        if ( issuersnode!=null && issuersnode.isArray() )
          for ( Iterator<JsonNode> it = issuersnode.elements(); it.hasNext(); )
            loadIssuer( it.next() );
      }
    }
    catch ( FileNotFoundException ex )
    {
      Logger.getLogger(LtiConfiguration.class.getName() ).log( Level.SEVERE, null, ex );
    } catch ( IOException ex )
    {
      Logger.getLogger(LtiConfiguration.class.getName() ).log( Level.SEVERE, null, ex );
    }
  }
  
  /**
   * Load an issuer from a node within the JSON file.
   * 
   * @param issuernode The node containing an issuer.
   */
  void loadIssuer( JsonNode issuernode )
  {
    String name = issuernode.get( "name" ).asText();
    logger.log(Level.FINE, "LtiConfiguration.loadIssuer() name = {0}", name );
    JsonNode clients = issuernode.get( "clients" );
    IssuerLtiConfiguration issuer = new IssuerLtiConfiguration( name );
    issuermap.put( name, issuer );
    if ( !clients.isArray() )
      return;
    for ( Iterator<JsonNode> it = clients.elements(); it.hasNext(); )
    {
      JsonNode clientnode = it.next();
      if ( clientnode.isObject() )
        loadClient( issuer, clientnode );
    }
  }
  
  /**
   * Load a client (tool) from within an issuer (e.g. blackboard.com) configuration.
   * 
   * @param issuer The issuer to load into.
   * @param clientnode The JSON node containing the config.
   */
  void loadClient( IssuerLtiConfiguration issuer, JsonNode clientnode )
  {
    String clientid = clientnode.get( "client_id" ).asText();
    logger.log(Level.FINE, "LtiConfiguration.loadClient() client_id = {0}", clientid );
    IssuerLtiConfiguration.ClientLtiConfiguration client = issuer.createClientLtiConfiguration( clientid );
    
    client.setDefault(        clientnode.get( "default" ).asBoolean()          );
    client.setAuthLoginUrl(   clientnode.get( "auth_login_url" ).asText()      );
    client.setAuthTokenUrl(   clientnode.get( "auth_token_url" ).asText()      );
    client.setAuthJwksUrl(    clientnode.get( "auth_jwks_url" ).asText()      );

    if ( clientnode.has( "keys" ) )
    {
      JsonNode keysnode = clientnode.get( "keys" );
      if ( keysnode.isArray() )
      {
        for ( Iterator<JsonNode> it = keysnode.elements(); it.hasNext(); )
        {
          JsonNode knode = it.next();
          if ( !knode.isObject() )
            continue;
          try
          {
            client.putKeyConfiguration( JsonKeyBuilder.build( knode ) );
          }
          catch ( Exception e )
          {
            logger.log( Level.SEVERE, "Unable to load public key for this client.", e );
          }
        }
      }
    }    
    JsonNode depnodea = clientnode.get( "deployment_ids" );
    ArrayList<String> list = new ArrayList<>();
    if ( depnodea.isArray() )
    {
      for ( Iterator<JsonNode> it = depnodea.elements(); it.hasNext(); )
      {
        JsonNode depnode = it.next();
        if ( depnode.isTextual() && !StringUtils.isEmpty( depnode.asText() ) )
          list.add( depnode.asText() );
      }
    }
    client.setDeploymentIds( list.toArray( new String[list.size()] ) );
    issuer.putClientLtiConfiguration( client );
  }   


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
     * @param issuer Value of the issuer this config represents.
     */
    public IssuerLtiConfiguration( String issuer )
    {
      this.issuer = issuer;
    }

    public ClientLtiConfiguration createClientLtiConfiguration( String s )
    {
      return new ClientLtiConfiguration( s );
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

    /**
     * Represents the configuration of an LTI 1.3 client. I.e. information about
     * an LTI tool which has been advertised to the rest of the world.
     * 
     * @author jon
     */
    public class ClientLtiConfiguration implements SigningKeyResolver
    {
      boolean bdefault;
      String clientId;
      String authLoginUrl;
      String authTokenUrl;
      String authJwksUrl;
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

      public String getAuthJwksUrl()
      {
        return authJwksUrl;
      }

      public void setAuthJwksUrl( String authJwksUrl )
      {
        this.authJwksUrl = authJwksUrl;
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

        if ( kc != null )
        {
          if ( kc.isEnabled() )
            return kc.getKey();

          logger.log( Level.INFO, "LTI signing key found but is not enabled in configuration." );
          return null;
        }

        if ( jwksResolver == null )
        {
          logger.log( Level.SEVERE, "Key not found in client LTI configuration. No Jwks resolver." );
          return null;
        }
        if ( authJwksUrl == null )
        {
          logger.log( Level.SEVERE, "No JWKS URL to search for keys." );
          return null;
        }

        logger.log( Level.FINE, "Looking for key in Jwks resolver." );
        return jwksResolver.resolveSigningKey( authJwksUrl, jh.getKeyId() );
      }  
    }


  }
}
