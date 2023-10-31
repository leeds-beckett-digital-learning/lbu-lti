/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uk.ac.leedsbeckett.lti.registration;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 *
 * @author jon
 */
public class ConsumerConfiguration
{
  static final Logger logger = Logger.getLogger(ConsumerConfiguration.class.getName() );
  
  final String rawconfig;

  String issuer;
  String registrationEndpoint;
  String jwksUri;
  
  
  public ConsumerConfiguration( String rawconfig ) throws IOException
  {
    this.rawconfig = rawconfig;
    load();
  }
  
  final void load() throws IOException
  {
      ObjectMapper mapper = new ObjectMapper();
      JsonFactory factory = mapper.getFactory();
      JsonParser parser = factory.createParser( rawconfig );
      JsonNode rootnode = mapper.readTree(parser);
      if ( rootnode.isObject() )
      {
        logger.fine( "LtiConfiguration loading base JSON object." );

        JsonNode node;
        
        node = rootnode.get( "issuer" );
        if ( node!=null && node.isTextual() )
          issuer = node.asText();

        node = rootnode.get( "registration_endpoint" );
        if ( node!=null && node.isTextual() )
          registrationEndpoint = node.asText();

        node = rootnode.get( "jwks_uri" );
        if ( node!=null && node.isTextual() )
          jwksUri = node.asText();
      }
  }

  public String getIssuer()
  {
    return issuer;
  }

  public String getRegistrationEndpoint()
  {
    return registrationEndpoint;
  }

  public String getJwksUri()
  {
    return jwksUri;
  }
  
  
  
}
