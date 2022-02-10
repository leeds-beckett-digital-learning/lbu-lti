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

package uk.ac.leedsbeckett.lti;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Represents the configuration of an LTI tool. Loads from a JSON file.
 * Likely to be much changed in the future.
 * 
 * @author jon
 */
public class LtiConfiguration
{
  String strpathconfig;
  HashMap<String,Issuer> issuermap = new HashMap<>();
  
  String rawconfig;
  
  StringBuilder log = new StringBuilder();
  

  /**
   * Returns the debugging log message.
   * 
   * @return A string message.
   */
  public String getLog()
  {
    return log.toString();
  }
  
  /**
   * Find the configuration for a particular tool given the ID of the
   * issuer and the client ID of the tool.
   * 
   * @param issuername The ID of the issuer.
   * @param client_id The ID of a client of the issuer.
   * @return A client configuration object.
   */
  public Client getClient( String issuername, String client_id )
  {
    Issuer issuer = issuermap.get( issuername );
    if ( issuer == null ) return null;
    return issuer.getClient( client_id );
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
        log.append( "\nLoading base JSON object.\n" );
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
    log.append( "Loading issuer " + name + "\n" );
    JsonNode clients = issuernode.get( "clients" );
    Issuer issuer = new Issuer( name );
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
  void loadClient( Issuer issuer, JsonNode clientnode )
  {
    log.append( "client_id = " + clientnode.get( "client_id" ).asText()  + "\n");
    Client client = new Client( clientnode.get( "client_id" ).asText() );
    
    client.setDefault(        clientnode.get( "default" ).asBoolean()          );
    client.setAuthLoginUrl(   clientnode.get( "auth_login_url" ).asText()      );
    client.setAuthTokenUrl(   clientnode.get( "auth_token_url" ).asText()      );
    
    try
    {
      client.setPublicKey( JsonKeyBuilder.build( clientnode.get( "public_key" ) ) );
    }
    catch ( Exception e )
    {
      e.printStackTrace();
      client.setPublicKey(  null );
      log.append( "\nUnable to load public key for this client.\n" );
      log.append( e.getMessage() );
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
    issuer.putClient( client );
  } 
  
  
  /**
   * Class represents the configuration related to a particular issuer, 
   * e.g. from blackboard.com. 
   */
  public class Issuer
  {
    String issuer;
    HashMap<String,Client> clientmap = new HashMap<>();

    public Issuer( String issuer )
    {
      this.issuer = issuer;
    }
    
    public void putClient( Client client )
    {
      clientmap.put(client.clientId, client );
    }
    
    public Client getClient( String client_id )
    {
      return clientmap.get( client_id );
    }
  }
  
  /**
   * Represents the configuration of a specific client of an issuer. For
   * example, this tool as defined by an application entry on 
   * developer.blackboard.com
   */
  public class Client
  {
    boolean bdefault;
    String clientId;
    String authLoginUrl;
    String authTokenUrl;
    PublicKey publicKey;
    String[] deploymentIds;
    
    public Client( String client_id )
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
}
